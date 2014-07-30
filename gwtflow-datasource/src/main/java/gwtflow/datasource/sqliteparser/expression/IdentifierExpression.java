package gwtflow.datasource.sqliteparser.expression;

import gwtflow.datasource.Schema;

import javax.naming.OperationNotSupportedException;
import java.util.List;
import java.util.Map;

public class IdentifierExpression extends BaseExpression {
    private String columnName;
    private String tableName;

    public IdentifierExpression(String columnName) {
        this.columnName = columnName;
    }

    public IdentifierExpression(String columnName, String tableName) {
        this.columnName = columnName;
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Override
    public Object getResult(Schema tabs) {
        return null;
    }
}
