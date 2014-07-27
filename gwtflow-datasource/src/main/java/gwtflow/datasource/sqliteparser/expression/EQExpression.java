package gwtflow.datasource.sqliteparser.expression;

public class EQExpression extends BaseExpression<Boolean> {
    private BaseExpression left;
    private BaseExpression right;

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

    public Boolean getResult() {
        if (left == null || left.getResult() == null) {
            return false;
        }
        if (right == null || right.getResult() == null) {
            return false;
        }
        return left.getResult().equals(right.getResult());
    }
}
