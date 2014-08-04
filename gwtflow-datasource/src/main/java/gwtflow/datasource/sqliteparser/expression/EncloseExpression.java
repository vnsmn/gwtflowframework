package gwtflow.datasource.sqliteparser.expression;

import gwtflow.datasource.Model;

public class EncloseExpression extends BaseExpression {
    private BaseExpression expression;

    public EncloseExpression(BaseExpression expression) {
        this.expression = expression;
    }

    @Override
    public Object getResult(Model tabs) {
        return expression == null ? null : expression.getResult(tabs);
    }
}
