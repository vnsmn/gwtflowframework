package gwtflow.datasource.sqliteparser.expression;

public class OrderExpression extends IdentifierExpression {
    public enum DIRECT {ASC, DESC};
    private DIRECT direct = DIRECT.ASC;

    public OrderExpression(String columnName) {
        super(columnName);
    }

    public OrderExpression(String columnName, String tableName) {
        super(columnName, tableName);
    }

    public DIRECT getDirect() {
        return direct;
    }

    public void setDirect(DIRECT direct) {
        this.direct = direct;
    }

    public Object getResult() {
        return "10";
    }
}
