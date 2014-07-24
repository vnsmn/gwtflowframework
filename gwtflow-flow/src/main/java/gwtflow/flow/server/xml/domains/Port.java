package gwtflow.flow.server.xml.domains;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;
import org.simpleframework.xml.core.Commit;
import org.springframework.util.StringUtils;
import gwtflow.flow.server.xml.BaseElement;
import gwtflow.flow.server.xml.Visiter;

@Root(name = "port")
public class Port extends BaseElement {
    @Attribute(name = "name")
    private String name;
    @Text
    private String value;

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @Commit
    protected void commit() {
        if (StringUtils.isEmpty(getId())) {
            setId(name);
        }
    }

    @Override
    public void visit(Visiter visiter) {
        visiter.execute(this);
    }
}
