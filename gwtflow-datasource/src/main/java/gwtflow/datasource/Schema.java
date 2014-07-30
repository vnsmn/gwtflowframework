package gwtflow.datasource;

import java.util.HashMap;
import java.util.Map;

public class Schema {
    private Map<String, Table> tableMap = new HashMap<String, Table>();

    public Table addTable(String name) {
        Table table = new Table(name);
        tableMap.put(name, table);
        return table;
    }

    public Table get(String name) {
        return tableMap.get(name);
    }
}
