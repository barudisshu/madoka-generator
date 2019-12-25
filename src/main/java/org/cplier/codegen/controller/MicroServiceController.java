package org.cplier.codegen.controller;

import lombok.extern.slf4j.Slf4j;
import org.cplier.codegen.annotation.OnCreate;
import org.cplier.codegen.annotation.OnUpdate;
import org.cplier.codegen.entity.MicroService;
import org.cplier.codegen.service.GeneratorService;
import org.cplier.codegen.service.MicroServiceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.zip.ZipOutputStream;

@Slf4j
@Controller
@Validated
public class MicroServiceController {

  @Resource MicroServiceService microServiceService;

  @Resource GeneratorService generatorService;

  @GetMapping("/")
  public String homePage(Model model) {
    model.addAttribute("microServices", microServiceService.findAll());
    return "index";
  }

  @GetMapping("add")
  public String addPage(MicroService microService, Model model) {
    model.addAttribute("microService", microService);
    return "add-service";
  }

  @PostMapping("/add")
  public String addService(
      @Validated(OnCreate.class) MicroService microService, BindingResult result, Model model) {
    if (result.hasErrors()) {
      return "add-service";
    }

    microServiceService.save(microService);
    return "redirect:/";
  }

  @PostMapping("/update/{id}")
  public String updateService(
      @PathVariable("id") long id,
      @Validated(OnUpdate.class) MicroService microService,
      BindingResult result,
      Model model) {
    if (result.hasErrors()) {
      microService.setId(id);
      return "edit-service";
    }

    microServiceService.save(microService);
    return "redirect:/";
  }

  @GetMapping("/edit/{id}")
  public String editService(@PathVariable("id") long id, Model model) {
    MicroService edit =
        microServiceService
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid service Id:" + id));
    model.addAttribute("microService", edit);
    return "edit-service";
  }

  @GetMapping("/delete/{id}")
  public String deleteService(@PathVariable("id") long id, Model model) {
    MicroService microService =
        microServiceService
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid service Id:" + id));
    microServiceService.delete(microService);
    return "redirect:/";
  }

  @GetMapping(value = "/gen/{id}", produces = "application/zip")
  public void generateCode(@PathVariable("id") long id, HttpServletResponse response)
      throws IOException {

    MicroService service =
        microServiceService
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid service Id:" + id));
    String serviceName = service.getName();
    String filename = serviceName.concat(".zip");

    String[] tableNames = service.getTableNames().split(",");

    // setting headers
    response.setStatus(HttpServletResponse.SC_OK);
    response.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

    ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());

    String url =
        "jdbc:mysql://localhost/"
            .concat(service.getIdentification())
            .concat("?characterEncoding=utf-8");
    String username = "root";
    String password = "root";

    generatorService.generateZip(
        url, username, password, service.getPacket(), tableNames, zipOutputStream);
  }
}
