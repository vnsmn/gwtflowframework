package ua.cetelem.gwt.server.flow.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cmd extends Base {
    private Collection<String> points = new ArrayList<>();

    public Cmd(String id) {
        super(id);
    }

    public Collection<String> getPoints() {
        return points;
    }

    @Override
    public void dump() {
        super.dump();
    }
}
