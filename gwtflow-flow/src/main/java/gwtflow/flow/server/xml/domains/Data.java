package gwtflow.flow.server.xml.domains;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;
import org.simpleframework.xml.core.Commit;
import org.springframework.util.StringUtils;
import gwtflow.flow.server.xml.BaseElement;
import gwtflow.flow.server.xml.Visiter;

@Root(name = "data")
public class Data extends BaseElement {

    @Attribute(name = "key")
    private Constants.DATA key;

    @Text
    private String value;


    public Constants.DATA getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Commit
    protected void commit() {
        if (StringUtils.isEmpty(getId())) {
            setId(key.name());
        }
    }

    @Override
    public void visit(Visiter visiter) {
        visiter.execute(this);
    }
}
