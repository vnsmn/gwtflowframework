package gwtflow.datasource.sqliteparser.expression;

import gwtflow.datasource.Schema;

import java.util.*;

public class EQExpression extends BaseExpression {
    private BaseExpression left;
    private BaseExpression right;
    private ExpressionHandler handler = new ExpressionHandler();

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

    public Object getResult(Schema tabs) {
        return handler.getResult(tabs, getLeft(), getRight(), ExpressionHandler.OPER_TYPE.EQUAL);
    }
}
