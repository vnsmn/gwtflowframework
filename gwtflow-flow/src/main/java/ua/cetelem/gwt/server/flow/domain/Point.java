package ua.cetelem.gwt.server.flow.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Point extends Base {
    private List<Cmd> cmds = new ArrayList<>();

    public Point(String id) {
        super(id);
    }

    @Override
    public void dump() {
        super.dump();
    }

    public List<Cmd> getCmds() {
        return cmds;
    }
}
