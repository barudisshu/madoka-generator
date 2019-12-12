package org.cplier.codegen.service.impl.table;

import org.cplier.codegen.service.BaseTableService;
import org.springframework.stereotype.Service;

@Service("oracle")
public class OracleTableServiceImpl extends BaseTableService {

  @Override
  protected String getDriverClassName() {
    return "oracle.jdbc.driver.OracleDriver";
  }
}
