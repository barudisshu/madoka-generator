package org.cplier.codegen.service;

import org.cplier.codegen.model.Column;
import org.cplier.codegen.model.Table;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseTableService implements TableService {

  protected abstract String getDriverClassName();

  @Override
  public Table getTable(String url, String username, String password, String tableName) throws Exception {
    try (Connection connection = getConnection(url, username, password)) {
      Table table = getMetaDataTable(connection, tableName);
      if (table == null) {
        return null;
      }
      List<Column> columns = listMetaDataColumn(connection, tableName);
      table.setColumns(columns);
      return table;
    }
  }

  private Table getMetaDataTable(Connection connection, String tableNamePattern)
      throws SQLException {
    ResultSet resultSet =
        connection
            .getMetaData()
            .getTables(connection.getCatalog(), connection.getSchema(), tableNamePattern, null);
    if (resultSet.next()) {
      Table table = new Table();
      table.setTableName(resultSet.getString("TABLE_NAME"));
      table.setTableType(resultSet.getString("TABLE_TYPE"));
      table.setTableComment(resultSet.getString("REMARKS"));
      return table;
    }
    return null;
  }

  private List<Column> listMetaDataColumn(Connection connection, String tableNamePattern)
      throws SQLException {
    ResultSet resultSet =
        connection
            .getMetaData()
            .getColumns(connection.getCatalog(), connection.getSchema(), tableNamePattern, null);
    List<Column> columns = new ArrayList<>();
    while (resultSet.next()) {
      Column column = new Column();
      column.setTableName(resultSet.getString("TABLE_NAME"));
      column.setColumnName(resultSet.getString("COLUMN_NAME"));
      column.setDataType(resultSet.getString("TYPE_NAME"));
      column.setColumnSize(resultSet.getInt("COLUMN_SIZE"));
      column.setColumnComment(resultSet.getString("REMARKS"));

      String nullAble = resultSet.getString("IS_NULLABLE");
      if (nullAble != null) {
        column.setNullAble("YES".equals(nullAble));
      }
      String autoIncrement = resultSet.getString("IS_AUTOINCREMENT");
      if (autoIncrement != null) {
        column.setAutoIncrement("YES".equals(autoIncrement));
      }
      columns.add(column);
    }
    return columns;
  }

  private Connection getConnection(String url, String username, String password) throws SQLException, ClassNotFoundException {
    Class.forName(getDriverClassName());
    return DriverManager.getConnection(url, username, password);
  }
}
