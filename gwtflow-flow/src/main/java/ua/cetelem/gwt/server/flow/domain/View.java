package ua.cetelem.gwt.server.flow.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class View extends Base {
    private Collection<ViewBox> viewBoxs = new ArrayList();
    private Map<String, String> ports = new HashMap();

    public View(String id) {
        super(id);
    }

    public Collection<ViewBox> getViewBoxs() {
        return viewBoxs;
    }

    public void addViewBox(String viewId, String port, Integer index) {
        this.viewBoxs.add(new ViewBox(viewId, port, index));
    }

    public Map<String, String> getPorts() {
        return ports;
    }

    public void addPort(String name, String value) {
        ports.put(name, value);
    }

    @Override
    public void dump() {
        super.dump();
    }

    public class ViewBox {
        private String viewId;
        private String port;
        private Integer index;

        private ViewBox(String viewId, String port, Integer index) {
            this.viewId = viewId;
            this.port = port;
            this.index = index;
        }

        public String getViewId() {
            return viewId;
        }

        public String getPort() {
            return port;
        }

        public Integer getIndex() {
            return index;
        }
    }
}