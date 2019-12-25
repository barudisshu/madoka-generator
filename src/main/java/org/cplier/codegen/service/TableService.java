package org.cplier.codegen.service;

import org.cplier.codegen.model.Table;

public interface TableService {

  Table getTable(String url, String username, String password, String tableName) throws Exception;
}
