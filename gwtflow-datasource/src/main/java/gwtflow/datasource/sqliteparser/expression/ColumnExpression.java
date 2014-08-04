package gwtflow.datasource.sqliteparser.expression;

import gwtflow.datasource.Model;

public class ColumnExpression extends BaseExpression {
    private String columnName;
    private String tableName;
    private String alias;
    private String literal;

    public ColumnExpression() {
    }

    public String getColumnName() {
        return columnName;
    }

    public ColumnExpression setColumnName(String columnName) {
        this.columnName = columnName;
        return this;
    }

    public String getTableName() {
        return tableName;
    }

    public ColumnExpression setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public String getLiteral() {
        return literal;
    }

    public ColumnExpression setLiteral(String literal) {
        this.literal = literal;
        return this;
    }

    public String getAlias() {
        return alias;
    }

    public ColumnExpression setAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public boolean isLiteral() {
        return literal != null;
    }

    @Override
    public Object getResult(Model tabs) {
        return null;
    }
}
