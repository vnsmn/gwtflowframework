package gwtflow.datasource.sqliteparser.expression;

import gwtflow.datasource.Model;

public class ANDExpression extends BaseExpression {
    private BaseExpression left;
    private BaseExpression right;

    public ANDExpression(BaseExpression left, BaseExpression right) {
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
