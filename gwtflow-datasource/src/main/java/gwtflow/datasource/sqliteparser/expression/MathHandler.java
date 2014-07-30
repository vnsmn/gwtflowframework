package gwtflow.datasource.sqliteparser.expression;

import javax.naming.OperationNotSupportedException;
import java.util.*;

public class MathHandler {
    public Object getResult(Map<String, List<Map<String, Object>>> tabs,
                            BaseExpression left, BaseExpression right) {
        Iterator<Map<String, Object>> it1 = null;
        Iterator<Map<String, Object>> it2 = null;
        IdentifierExpression id1 = null;
        IdentifierExpression id2 = null;
        LiteralExpression l1 = null;
        LiteralExpression l2 = null;

        if (left instanceof IdentifierExpression) {
            id1 = (IdentifierExpression) left;
            it1 = tabs.get(id1.getTableName()).iterator();
        } else if (left instanceof LiteralExpression) {
            l1 = (LiteralExpression) left;
        } else {
            throw new IllegalArgumentException();
        }

        if (right instanceof IdentifierExpression) {
            id2 = (IdentifierExpression) right;
            it2 = tabs.get(id2.getTableName()).iterator();
        } else if (left instanceof LiteralExpression) {
            l2 = (LiteralExpression) right;
        } else {
            throw new IllegalArgumentException();
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
