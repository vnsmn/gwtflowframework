package gwtflow.flow.server.xml;

import org.springframework.util.StringUtils;
import gwtflow.flow.server.domain.View;
import gwtflow.flow.server.xml.domains.*;

import java.io.File;
import java.util.*;

public class ViewFlowBuilder {
    private Map<String, BaseElement> globalElementMap = new HashMap<>();
    private Map<String, View> viewMap = new HashMap<>();
    private List<View> rootViews = new ArrayList();

    public ViewFlowBuilder(Map<String, BaseElement> globalElementMap) {
        this.globalElementMap = globalElementMap;
    }

    public void addNode(Node rootNode) {
        TreeMap<String, BaseElement> treeMap = new TreeMap(new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        });
        treeMap.putAll(rootNode.getRelationIds());
        for (Map.Entry<String, BaseElement> ent : treeMap.entrySet()) {
            String vid = getViewIdBy(rootNode.getCanonicalId() + "/" + ent.getKey());
            View currentView = createOrGetView(vid);
            if (ent.getValue() instanceof Data) {
                Data dt = (Data) ent.getValue();
                currentView.addProperty(dt.getKey().name(), dt.getValue());
            } else if (ent.getValue() instanceof Node) {
            } else if (ent.getValue() instanceof Port) {
                Port pt = (Port) ent.getValue();
                currentView.addPort(pt.getName(), pt.getValue());
            } else if (ent.getValue() instanceof Hyperedge) {
            } else if (ent.getValue() instanceof Endpoint) {
                Endpoint ed = (Endpoint) ent.getValue();
                Node n = ed.getNode().startsWith("/")
                    ? (Node) globalElementMap.get(ed.getNode())
                    : (Node) rootNode.getRelationIds().get(ed.getNode());
                String viewId = n == null ? null : n.getCanonicalId();
                currentView.addViewBox(createOrGetView(viewId).getId(), ed.getPort(), ed.getIndex());
            }
        }
        rootViews.add(createOrGetView(rootNode.getCanonicalId()));
    }

    public Collection<View> build() {
        return viewMap.values();
    }

    private String getViewIdBy(String id) {
        File f = new File(id).getParentFile();
        int i = 100;
        while (f != null && i-- > 0) {
            if (globalElementMap.get(f.getAbsolutePath()) instanceof Node) {
                return globalElementMap.get(f.getAbsolutePath()).getCanonicalId();
            }
            f = f.getParentFile();
        }
        return null;
    }

    private View createOrGetView(String viewId) {
        if (StringUtils.isEmpty(viewId)) {
            return null;
        }
        View view = viewMap.get(viewId);
        if (view == null) {
            view = new View(viewId);
            viewMap.put(viewId, view);
        }
        return view;
    }
}
