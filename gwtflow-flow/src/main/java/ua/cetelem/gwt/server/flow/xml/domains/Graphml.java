package ua.cetelem.gwt.server.flow.xml.domains;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.Commit;
import org.springframework.util.StringUtils;
import ua.cetelem.gwt.server.flow.xml.BaseElement;
import ua.cetelem.gwt.server.flow.xml.Visiter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Root(name = "graphml")
public class Graphml extends BaseElement {
    @Attribute
    private String schemaLocation;

    @ElementList(inline = true, required = false)
    private List<Include> includes;

    @ElementList(inline = true, required = false)
    private List<Node> nodes;

    @ElementList(inline = true, required = false)
    private List<Key> keyis;

    @ElementList(inline = true, required = false)
    private List<Data> datas;

    @ElementList(inline = true, required = false)
    private List<Graph> graphs;

    public List<Include> getIncludes() {
        return includes == null ? Collections.<Include>emptyList() : includes;
    }

    public List<Node> getNodes() {
        return nodes == null ? Collections.EMPTY_LIST : nodes;
    }

    public List<Key> getKeyis() {
        return keyis == null ? Collections.EMPTY_LIST : keyis;
    }

    public List<Data> getDatas() {
        return datas == null ? Collections.EMPTY_LIST : datas;
    }

    public List<Graph> getGraphs() {
        return graphs == null ? Collections.EMPTY_LIST : graphs;
    }

    @Commit
    protected void commit() {
        if (StringUtils.isEmpty(getId())) {
            setId("/");
        }
    }

    @Override
    public void visit(Visiter visiter) {
        visiter.execute(this);
        visiter.pushElement(this);
        List baseElements = new ArrayList(getNodes());
        baseElements.addAll(getKeyis());
        baseElements.addAll(getDatas());
        baseElements.addAll(getGraphs());
        for (Object el : baseElements) {
            ((BaseElement) el).visit(visiter);
        }
        visiter.popElement(this);
    }
}
