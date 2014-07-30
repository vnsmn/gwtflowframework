package gwtflow.datasource.sqliteparser.expression;

import java.util.*;

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

    public Object getResult(Map<String, List<Map<String, Object>>> tabs) {
        Iterator<Map<String, Object>> it1 = null;
        Iterator<Map<String, Object>> it2 = null;
        IdentifierExpression id1 = null;
        IdentifierExpression id2 = null;
        if (getLeft() instanceof IdentifierExpression) {
            id1 = (IdentifierExpression) getLeft();
            it1 = tabs.get(id1.getTableName()).iterator();
        }
        if (getRight() instanceof IdentifierExpression) {
            id2 = (IdentifierExpression) getRight();
            it2 = tabs.get(id2.getTableName()).iterator();
        }

        Map<String, List<Map<String, Object>>> calcTabs = new HashMap<String, List<Map<String, Object>>>();
        calcTabs.put(id1.getTableName(), new ArrayList<Map<String, Object>>());
        calcTabs.put(id2.getTableName(), new ArrayList<Map<String, Object>>());

        while (it1.hasNext()) {
            Map<String, Object> rows1 = it1.next();
            Object val1 = rows1.get(id1.getColumnName());
            while (it2.hasNext()) {
                Map<String, Object> rows2 = it2.next();
                Object val2 = rows2.get(id2.getColumnName());
                if (val1.equals(val2)) {
                    calcTabs.get(id1.getTableName()).add(rows1);
                    calcTabs.get(id2.getTableName()).add(rows1);
                }
            }
        }

        return calcTabs;
    }
}
