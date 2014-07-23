package ua.cetelem.gwt.server.flow.xml.domains;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import ua.cetelem.gwt.server.flow.xml.BaseElement;
import ua.cetelem.gwt.server.flow.xml.Visiter;

@Root(name = "key")
public class Key extends BaseElement {
    @Attribute(name = "for")
    private String forAttr;
    @Attribute(name = "attr.name")
    private Constants.ATTR_NAME nameAttr;
    @Attribute(name = "attr.type")
    private String typeAttr;
    @Element(name = "default", required = false)
    private String content;

    @Override
    public void visit(Visiter visiter) {
        visiter.execute(this);
    }
}
