package org.cplier.codegen.service;

import org.cplier.codegen.model.TableItem;

public interface GeneratorService {

  void generateZip(String[] tableNames, String zipPath);

  void generateZip(TableItem[] tableItems, String zipPath);
}
