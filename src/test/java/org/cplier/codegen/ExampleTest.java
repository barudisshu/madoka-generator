package org.cplier.codegen;

import org.cplier.codegen.constant.KeyConsts;
import org.cplier.codegen.model.TableItem;
import org.cplier.codegen.service.GeneratorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExampleTest {

  @Resource private GeneratorService generatorService;

  @Test
  public void test() {
    generatorService.generateZip(
        new TableItem[] {
          TableItem.newBuilder()
              .tableName("oauth2_user")
              .dynamicPathVariable(KeyConsts.CLASS_NAME, "TableA")
              .build(),
          new TableItem("oauth2_client")
        },
        "code.zip");
  }
}
