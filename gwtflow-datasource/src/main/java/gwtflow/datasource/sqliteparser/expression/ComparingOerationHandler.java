package gwtflow.datasource.sqliteparser.expression;

import gwtflow.datasource.Model;

import java.math.BigDecimal;
import java.util.*;

public class ComparingOerationHandler {
    public enum OPER_TYPE {
        LESS, MORE, EQUAL, LESS_EQUAL, MORE_EQUAL
    }

    public Object getResult(Model tabs,
                            BaseExpression left, BaseExpression right,
                            OPER_TYPE operType) {
        IdentifierExpression idExp1 = null;
        IdentifierExpression idExp2 = null;
        LiteralExpression ltExp1 = null;
        LiteralExpression ltExp2 = null;

        if (left instanceof IdentifierExpression) {
            idExp1 = (IdentifierExpression) left;
        } else if (left instanceof LiteralExpression) {
            ltExp1 = (LiteralExpression) left;
        } else {
            throw new IllegalArgumentException();
        }

        if (right instanceof IdentifierExpression) {
            idExp2 = (IdentifierExpression) right;
        } else if (left instanceof LiteralExpression) {
            ltExp2 = (LiteralExpression) right;
        } else {
            throw new IllegalArgumentException();
        }

        if (idExp1 != null && idExp2 != null) {
            return null;// getResultImpl(tabs, idExp1, idExp2, operType);
        } else if (idExp1 != null && ltExp2 != null) {
            return getResultImpl(tabs, idExp1, ltExp2, operType);
        } else if (idExp2 != null && ltExp1 != null) {
            return getResultImpl(tabs, idExp2, ltExp1, operType);
        } else if (ltExp1 != null && ltExp2 != null) {
            return getResultImpl(ltExp1, ltExp2, operType);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private Object getResultImpl(Model left, Model right, OPER_TYPE operType) {
        Model modifiedModel = new Model();
        for (Set<Model.Row> rows1 : left.getResult()) {
            for (Set<Model.Row> rows2 : right.getResult()) {
                if (resultContains(rows1, rows2)) {
                    List l = new ArrayList(rows1);
                    l.addAll(rows2);
                    modifiedModel.addResultRows(l);
                }
            }
        }
        return modifiedModel;
    }

    private boolean resultContains(Set<Model.Row> rows1, Set<Model.Row> rows2) {
        for (Model.Row row : rows1)
            if (rows2.contains(row)) {
                return true;
            }
        return false;
    }

    private Object getResultImpl(Model tabs,
                            IdentifierExpression left, LiteralExpression right,
                            OPER_TYPE operType) {

        Model calcTabs = new Model();
        calcTabs.addTable(left.getTableName());

        Object val2 = right.getLiteral();
        tabs.get(left.getTableName());
        for (Model.Row rows1 : tabs.get(left.getTableName()).getRows()) {
            Object val1 = rows1.getValue(left.getColumnName());
                if (operation(val1, val2, operType)) {
                    calcTabs.get(left.getTableName()).addRow(rows1);
                }
        }

        return calcTabs;
    }

    private Object getResultImpl(LiteralExpression left, LiteralExpression right, OPER_TYPE operType) {

        return operation(left.getLiteral(), right.getLiteral(), operType);
    }

    private boolean operation(Object val1, Object val2, OPER_TYPE operType) {
        if (val1 == null || val2 == null) {
            return false;
        }

        if (OPER_TYPE.EQUAL == operType) {
            return val1.equals(val2);
        } else if (OPER_TYPE.LESS == operType) {
            BigDecimal b1 = new BigDecimal(val1.toString());
            BigDecimal b2 = new BigDecimal(val1.toString());
            return b1.compareTo(b2) < 0 ? true : false;
        } else if (OPER_TYPE.MORE == operType) {
            BigDecimal b1 = new BigDecimal(val1.toString());
            BigDecimal b2 = new BigDecimal(val1.toString());
            return b1.compareTo(b2) > 0 ? true : false;
        } else if (OPER_TYPE.MORE_EQUAL == operType) {
            BigDecimal b1 = new BigDecimal(val1.toString());
            BigDecimal b2 = new BigDecimal(val1.toString());
            return b1.compareTo(b2) >= 0 ? true : false;
        } else if (OPER_TYPE.LESS_EQUAL == operType) {
            BigDecimal b1 = new BigDecimal(val1.toString());
            BigDecimal b2 = new BigDecimal(val1.toString());
            return b1.compareTo(b2) <= 0 ? true : false;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
