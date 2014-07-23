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

@Root(name = "edge")
public class Edge extends BaseElement {
    @Attribute(name = "source")
    private String source;

    @Attribute(name = "target")
    private String target;

    @Attribute(name = "sourceport", required = false)
    private String sourceport;

    @Attribute(name = "targetport", required = false)
    private String targetport;

    @ElementList(inline = true, required = false)
    private List<Data> datas;

    public List<Data> getDatas() {
        return datas == null ? Collections.EMPTY_LIST : datas;
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    public String getSourceport() {
        return sourceport;
    }

    public String getTargetport() {
        return targetport;
    }

    @Override
    public void visit(Visiter visiter) {
        visiter.execute(this);
        visiter.pushElement(this);
        for (Data data : getDatas()) {
            data.visit(visiter);
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
