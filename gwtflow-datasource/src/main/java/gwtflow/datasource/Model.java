package gwtflow.datasource;

import java.util.*;

public class Model {
    private Map<String, Table> tableMap = new HashMap<String, Table>();
    static private Long currentKey = 0l;
    private List<Set<Row>> resultRows = new ArrayList<Set<Row>>();

    public Table addTable(String name) {
        Table table = new Table(name);
        tableMap.put(name, table);
        return table;
    }

    public Table get(String name) {
        return tableMap.get(name);
    }

    public class Table {
        private String name;
        private Set<Row> rows = new HashSet<Row>();

        public Table(String name) {
            this.name = name;
        }

        public Row addRow() {
            Row row = new Row(generateKey());
            rows.add(row);
            return row;
        }

        public Row addRow(Row row) {
            rows.add(row);
            return row;
        }

        public Set<Row> getRows() {
            return Collections.unmodifiableSet(rows);
        }
    }

    public class Row {
        final private UUID key;
        private Map<String, Object> cols = new HashMap<String, Object>();

        private Row(UUID key) {
            this.key = key;
        }

        public Object getValue(String columnName) {
            return cols.get(columnName);
        }

        public Row addColumn(String name, Object val) {
            cols.put(name, val);
            return this;
        }
    }

    public void addResultRows(Row ... row) {
        Set<Row> rows = new HashSet<Row>();
        rows.addAll(Arrays.asList(row));
        resultRows.add(rows);
    }

    public void addResultRows(List<Row> rows) {
        Set<Row> rowSet = new HashSet<Row>(rows);
        rows.addAll(rowSet);
        resultRows.add(rowSet);
    }

    public List<Set<Row>> getResult() {
        return resultRows;
    }

    private UUID generateKey() {
        if (Long.MAX_VALUE == currentKey) {
            currentKey = 0l;
        }
        return new UUID(System.nanoTime(), currentKey++);
    }
}
