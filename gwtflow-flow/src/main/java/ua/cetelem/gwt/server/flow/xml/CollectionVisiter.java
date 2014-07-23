package ua.cetelem.gwt.server.flow.xml;

import org.springframework.util.StringUtils;
import ua.cetelem.gwt.server.flow.xml.domains.*;

import java.util.*;

public class CollectionVisiter implements Visiter {
    private Map<String, Map<String, BaseElement>> elementMap = new TreeMap<>();
    private Stack<BaseElement> stack = new Stack();
    private List<Graph> graphs = new ArrayList<>();
    private List<Node> nodes = new ArrayList<>();
    private List<Data> datas = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();
    private List<Key> keys = new ArrayList<>();
    private List<Port> ports = new ArrayList<>();
    private List<BaseElement> all = new ArrayList<>();


    public CollectionVisiter() {
    }

    @Override
    public void pushElement(BaseElement el) {
        stack.add(el);
    }

    @Override
    public void popElement(BaseElement el) {
        stack.pop();
    }

    public void execute(BaseElement el) {
        BaseElement parent = stack.isEmpty() ? null : stack.peek();
        if (parent != null) {
            el.setParent(parent);
        }

        if (el instanceof Graph) {
            graphs.add((Graph) el);
        } else if (el instanceof Node) {
            nodes.add((Node) el);
        } else if (el instanceof Data) {
            datas.add((Data) el);
        } else if (el instanceof Edge) {
            edges.add((Edge) el);
        } else if (el instanceof Key) {
            keys.add((Key) el);
        } else if (el instanceof Port) {
            ports.add((Port) el);
        }
        all.add(el);

        if (StringUtils.isEmpty(el.getId())) {
            return;
        }

        BaseElement current = el.getParent();
        String id = el.getId();
        Map<String, BaseElement> idMap = new HashMap<>();
        idMap.put(id, el);
        while (current != null) {
            current.getRelationIds().put(id, el);
            if (current instanceof Graphml) {
                id = "/" + id;
            } else if (!StringUtils.isEmpty(current.getId())) {
                id = current.getId() + "/" + id;
            }
            if (!(current instanceof Graphml) && !StringUtils.isEmpty(current.getId()))
                idMap.put(id, el);
            current = current.getParent();
        }
        el.setCanonicalId(id);
        elementMap.put(id, idMap);
    }

    public Map<String, Map<String, BaseElement>> getElementMap() {
        return elementMap;
    }

    public List<BaseElement> all() {
        return all;
    }

    public List<Graph> getGraphs() {
        return graphs;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public List<Data> getDatas() {
        return datas;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public List<Key> getKeys() {
        return keys;
    }

    public List<Port> getPorts() {
        return ports;
    }
}
