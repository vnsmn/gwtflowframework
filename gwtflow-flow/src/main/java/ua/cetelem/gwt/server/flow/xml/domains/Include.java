package ua.cetelem.gwt.server.flow.xml.domains;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "include")
final public class Include {
    @Attribute(name = "href")
    private String href;

    public String getHref() {
        return href;
    }
}
