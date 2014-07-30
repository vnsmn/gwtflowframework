package gwtflow.datasource.sqliteparser.expression;

import gwtflow.datasource.Schema;

import java.util.List;
import java.util.Map;

public class EncloseExpression extends BaseExpression {
    private BaseExpression expression;

    public EncloseExpression(BaseExpression expression) {
        this.expression = expression;
    }

    @Override
    public Object getResult(Schema tabs) {
        return expression == null ? null : expression.getResult(tabs);
    }
}
