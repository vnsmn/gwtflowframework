package gwtflow.datasource;

import java.util.*;

public class Table {
    private String name;
    private List<Row> rows = new ArrayList<Row>();
    private Row currentRow = null;

    public Table(String name) {
        this.name = name;
    }

    public Table addRow() {
        currentRow = new Row();
        rows.add(currentRow);
        return this;
    }

    public Table addRow(Row row) {
        currentRow = row;
        rows.add(currentRow);
        return this;
    }

    public Table addColumn(String name, Object val) {
        currentRow.cols.put(name, val);
        return this;
    }

    public class Row {
        private Map<String, Object> cols = new HashMap<String, Object>();

        public Object getValue(String columnName) {
            return cols.get(columnName);
        }
    }

    public List<Row> getRows() {
        return Collections.unmodifiableList(rows);
    }
}
