package org.cplier.codegen.service.impl.table;

import org.cplier.codegen.service.BaseTableService;
import org.springframework.stereotype.Service;

@Service("mysql")
public class MySQLTableServiceImpl extends BaseTableService {

  @Override
  protected String getDriverClassName() {
    return "com.mysql.cj.jdbc.Driver";
  }
}
