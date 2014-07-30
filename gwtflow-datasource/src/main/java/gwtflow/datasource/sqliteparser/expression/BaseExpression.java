package gwtflow.datasource.sqliteparser.expression;

import java.util.List;
import java.util.Map;

abstract public class BaseExpression<T> {
    public T getResult() {
        return null;
    }
    public Object getResult(Map<String, List<Map<String, Object>>> tabs) {
        return null;
    }
}
