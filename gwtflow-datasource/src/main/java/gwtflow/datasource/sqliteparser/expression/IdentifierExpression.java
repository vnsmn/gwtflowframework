package gwtflow.datasource.sqliteparser.expression;

public class IdentifierExpression extends BaseExpression<Object> {
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

    public Object getResult() {
        return "10";
    }
}
