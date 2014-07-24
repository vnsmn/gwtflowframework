package gwtflow.flow.server.xml.domains;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import gwtflow.flow.server.xml.BaseElement;
import gwtflow.flow.server.xml.Visiter;

import java.util.Collections;
import java.util.List;

@Root(name = "graph")
public class Graph extends BaseElement {

    @Attribute(name = "type", required = false)
    private Constants.POINT_TYPE type = Constants.POINT_TYPE.nested;

    @Attribute(name = "edgedefault", required = false, empty = "directed")
    private String edgedefault;

    @ElementList(inline = true, required = false)
    private List<Node> nodes;

    @ElementList(inline = true, required = false)
    private List<Data> datas;

    @ElementList(inline = true, required = false)
    private List<Edge> edges;

    @ElementList(inline = true, required = false)
    private List<Hyperedge> hyperedges;

    public Constants.POINT_TYPE getType() {
        return type;
    }

    public String getEdgedefault() {
        return edgedefault;
    }

    public List<Node> getNodes() {
        return nodes == null ? Collections.EMPTY_LIST : nodes;
    }

    public List<Data> getDatas() {
        return datas == null ? Collections.EMPTY_LIST : datas;
    }

    public List<Edge> getEdges() {
        return edges == null ? Collections.EMPTY_LIST : edges;
    }

    public List<Hyperedge> getHyperedges() {
        return hyperedges == null ? Collections.EMPTY_LIST: hyperedges;
    }

    @Override
    public void visit(Visiter visiter) {
        visiter.execute(this);
        visiter.pushElement(this);
        for (Data data : getDatas()) {
            data.visit(visiter);
        }
        for (Node node : getNodes()) {
            node.visit(visiter);
        }
        for (Edge edge : getEdges()) {
            edge.visit(visiter);
        }
        for (Hyperedge hyperedge : getHyperedges()) {
            hyperedge.visit(visiter);
        }
        visiter.popElement(this);
    }
}
