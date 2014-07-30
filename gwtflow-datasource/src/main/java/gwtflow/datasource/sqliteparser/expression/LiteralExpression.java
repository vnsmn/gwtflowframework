package gwtflow.datasource.sqliteparser.expression;

import gwtflow.datasource.Schema;

import java.util.List;
import java.util.Map;

public class LiteralExpression extends BaseExpression {
    private Object literal;

    public LiteralExpression(Object literal) {
        this.literal = literal;
    }

    public Object getLiteral() {
        return literal;
    }

    @Override
    public Object getResult(Schema tabs) {
        return null;
    }
}
