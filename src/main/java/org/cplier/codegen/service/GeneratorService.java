package org.cplier.codegen.service;

import org.cplier.codegen.model.TableItem;

import java.util.zip.ZipOutputStream;

public interface GeneratorService {

  void generateZip(String url, String username, String password, String packageName, String[] tableNames, String zipPath);

  void generateZip(String url, String username, String password, String packageName, String[] tableNames, ZipOutputStream zos);

  void generateZip(String url, String username, String password, String packageName, TableItem[] tableItems, String zipPath);

  void generateZip(String url, String username, String password, String packageName, TableItem[] tableItems, ZipOutputStream zos);
}
