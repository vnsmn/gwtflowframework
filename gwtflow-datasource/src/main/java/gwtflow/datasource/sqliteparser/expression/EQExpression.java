package gwtflow.datasource.sqliteparser.expression;

import gwtflow.datasource.Model;

public class EQExpression extends BaseExpression {
    private BaseExpression left;
    private BaseExpression right;
    private LogicalOperationHandler handler = new LogicalOperationHandler();

    public EQExpression(BaseExpression left, BaseExpression right) {
        this.left = left;
        this.right = right;
    }

    public BaseExpression getLeft() {
        return left;
    }

    public BaseExpression getRight() {
        return right;
    }

    public Object getResult(Model tabs) {
        return handler.getResult(tabs, getLeft(), getRight(), LogicalOperationHandler.OPER_TYPE.EQUAL);
    }
}
