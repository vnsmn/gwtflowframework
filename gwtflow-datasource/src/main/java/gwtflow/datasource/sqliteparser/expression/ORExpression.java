package gwtflow.datasource.sqliteparser.expression;

import gwtflow.datasource.Model;

public class ORExpression extends BaseExpression {
    private BaseExpression left;
    private BaseExpression right;

    public ORExpression(BaseExpression left, BaseExpression right) {
        this.left = left;
        this.right = right;
    }

    public BaseExpression getLeft() {
        return left;
    }

    public BaseExpression getRight() {
        return right;
    }

    @Override
    public Object getResult(Model tabs) {
        return super.getResult(tabs);
    }
}
