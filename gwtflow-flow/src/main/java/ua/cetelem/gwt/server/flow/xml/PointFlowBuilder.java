package ua.cetelem.gwt.server.flow.xml;

import org.springframework.util.StringUtils;
import ua.cetelem.gwt.server.flow.domain.Cmd;
import ua.cetelem.gwt.server.flow.domain.Point;
import ua.cetelem.gwt.server.flow.domain.View;
import ua.cetelem.gwt.server.flow.xml.domains.*;

import java.util.*;

public class PointFlowBuilder {
    private Map<String, BaseElement> globalElementMap = new HashMap<>();
    private Map<String, Point> pointMap = new HashMap<>();

    public PointFlowBuilder(Map<String, BaseElement> globalElementMap) {
        this.globalElementMap = globalElementMap;
    }

    public void addNode(Node rootNode) throws Exception {
        TreeMap<String, BaseElement> treeMap = new TreeMap(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
        treeMap.putAll(rootNode.getRelationIds());
        Point srcPoint = createOrGetPoint(rootNode.getCanonicalId());
        for (Data data : rootNode.getDatas()) {
            srcPoint.addProperty(data.getKey().name(), data.getValue());
        }
        for (Graph graph : rootNode.getGraphs()) {
            for (Hyperedge h : graph.getHyperedges()) {
                Cmd cmd = new Cmd(rootNode.getCanonicalId() + "/" + h.getId());
                srcPoint.getCmds().add(cmd);
                for (Endpoint ed : h.getEndpoints()) {
                    String ptId = ed.getNode().startsWith("/")
                            ? ed.getNode()
                            : (StringUtils.isEmpty(ed.getNode())
                                ? rootNode.getCanonicalId()
                                : rootNode.getParent().getCanonicalId() + "/" + ed.getNode());
                    cmd.getPoints().add(ptId);
                }
            }
        }
    }

    public Collection<Point> build() throws Exception {
        return pointMap.values();
    }

    private Point createOrGetPoint(String id) {
        if (StringUtils.isEmpty(id)) {
            return null;
        }
        Point pt = pointMap.get(id);
        if (pt == null) {
            pt = new Point(id);
            pointMap.put(id, pt);
        }
        return pt;
    }
}
