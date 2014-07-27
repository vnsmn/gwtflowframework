package gwtflow.datasource.sqliteparser.expression;

public class EncloseExpression extends BaseExpression<Object> {
    private BaseExpression expression;

    public EncloseExpression(BaseExpression expression) {
        this.expression = expression;
    }

    public Object getResult() {
        return expression == null ? null : expression.getResult();
    }
}
