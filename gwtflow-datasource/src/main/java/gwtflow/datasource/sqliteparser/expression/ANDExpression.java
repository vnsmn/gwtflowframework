package gwtflow.datasource.sqliteparser.expression;

public class ANDExpression extends BaseExpression<Boolean> {
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

    public Boolean getResult() {
        if (left == null || left.getResult() == null) {
            return false;
        }
        if (right == null || right.getResult() == null) {
            return false;
        }
        return ((Boolean) left.getResult()) && ((Boolean) right.getResult());
    }
}
