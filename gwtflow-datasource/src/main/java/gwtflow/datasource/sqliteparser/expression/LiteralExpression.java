package gwtflow.datasource.sqliteparser.expression;

public class LiteralExpression extends BaseExpression<Object> {
    private Object literal;

    public LiteralExpression(Object literal) {
        this.literal = literal;
    }

    public Object getLiteral() {
        return literal;
    }

    public Object getResult() {
        return literal;
    }
}
