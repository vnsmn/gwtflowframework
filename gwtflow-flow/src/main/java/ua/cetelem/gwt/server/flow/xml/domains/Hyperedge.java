package ua.cetelem.gwt.server.flow.xml.domains;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.Validate;
import org.springframework.util.StringUtils;
import ua.cetelem.gwt.server.flow.xml.BaseElement;
import ua.cetelem.gwt.server.flow.xml.Visiter;

import java.util.Collections;
import java.util.List;

@Root(name = "hyperedge")
public class Hyperedge extends BaseElement {

    @ElementList(inline = true, required = false)
    private List<Endpoint> endpoints;

    public List<Endpoint> getEndpoints() {
        return endpoints == null ? Collections.EMPTY_LIST : endpoints;
    }

    @Override
    public void visit(Visiter visiter) {
        visiter.execute(this);
        visiter.pushElement(this);
        for (Endpoint endpoint : getEndpoints()) {
            endpoint.visit(visiter);
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
