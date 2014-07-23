package ua.cetelem.gwt.server.flow.xml.domains;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.Validate;
import org.springframework.util.StringUtils;
import ua.cetelem.gwt.server.flow.xml.BaseElement;
import ua.cetelem.gwt.server.flow.xml.Visiter;

import java.util.Collections;
import java.util.List;

@Root(name = "endpoint")
public class Endpoint extends BaseElement {
    @Attribute(name = "node", required = true)
    private String node;

    @Attribute(name = "port", required = false)
    private String port = "";

    @Attribute(name = "index", required = false)
    private Integer index = 0;

    public String getNode() {
        return node;
    }

    public String getPort() {
        return port;
    }

    public Integer getIndex() {
        return index;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    @Override
    public void visit(Visiter visiter) {
        visiter.execute(this);
    }

    @Validate
    protected void validate() {
        if (StringUtils.isEmpty(getId())) {
            throw new RuntimeException("id is null");
        }
    }
}
