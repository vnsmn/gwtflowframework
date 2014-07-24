package gwtflow.flow.server.xml.domains;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.Validate;
import org.springframework.util.StringUtils;
import gwtflow.flow.server.xml.BaseElement;
import gwtflow.flow.server.xml.Super;
import gwtflow.flow.server.xml.Visiter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Root(name = "node")
public class Node extends BaseElement implements Super {
    @Attribute(name = "type", required = false)
    private String type;

    @Attribute(name = "super", required = false)
    private String superAttr;

    @ElementList(inline = true, required = false)
    private List<Data> datas;

    @ElementList(inline = true, required = false)
    private List<Graph> graphs;

    @ElementList(inline = true, required = false)
    private List<Port> ports;

    public String getType() {
        return type;
    }

    public String getSuperId() {
        return superAttr;
    }

    public boolean hasSuper() {
        return !StringUtils.isEmpty(superAttr);
    }

    public List<Data> getDatas() {
        return datas == null ? Collections.EMPTY_LIST : datas;
    }

    public List<Graph> getGraphs() {
        return graphs == null ? Collections.EMPTY_LIST : graphs;
    }

    public List<Port> getPorts() {
        return ports == null ? Collections.EMPTY_LIST : ports;
    }

    @Override
    public void visit(Visiter visiter) {
        visiter.execute(this);
        visiter.pushElement(this);
        List baseElements = new ArrayList(getDatas());
        baseElements.addAll(getGraphs());
        baseElements.addAll(getPorts());
        for (Object el : baseElements) {
            ((BaseElement) el).setParent(this);
            ((BaseElement) el).visit(visiter);
        }
        visiter.popElement(this);
    }

    @Validate
    protected void validate() {
        if (StringUtils.isEmpty(getId())) {
            throw new RuntimeException("id is null");
        }
    }
}
