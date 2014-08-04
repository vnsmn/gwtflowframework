package gwtflow.datasource.sqliteparser.expression;

import gwtflow.datasource.Model;

public class LiteralExpression extends BaseExpression {
    private Object literal;

    public LiteralExpression(Object literal) {
        this.literal = literal;
    }

    public Object getLiteral() {
        return literal;
    }

    @Override
    public Object getResult(Model tabs) {
        return null;
    }
}
