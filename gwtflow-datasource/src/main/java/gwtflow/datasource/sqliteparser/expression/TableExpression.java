package gwtflow.datasource.sqliteparser.expression;

import gwtflow.datasource.Model;

public class TableExpression extends BaseExpression {
    private String tableName;
    private String alias;

    public TableExpression() {
    }

    public String getTableName() {
        return tableName;
    }

    public TableExpression setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public String getAlias() {
        return alias;
    }

    public TableExpression setAlias(String alias) {
        this.alias = alias;
        return this;
    }

    @Override
    public Object getResult(Model tabs) {
        return null;
    }
}
