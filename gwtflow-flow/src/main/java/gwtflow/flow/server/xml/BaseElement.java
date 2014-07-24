package gwtflow.flow.server.xml;

import org.simpleframework.xml.Attribute;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseElement implements Serializable {
    private BaseElement parent;
    private Map<String, BaseElement> relationIds = new HashMap<>();

    private String canonicalId;

    @Attribute(name = "id", required = false)
    private String id;

    abstract public void visit(Visiter visiter);

    final public BaseElement getParent() {
        return parent;
    }

    final public void setParent(BaseElement parent) {
        this.parent = parent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, BaseElement> getRelationIds() {
        return relationIds;
    }

    public void setRelationIds(Map<String, BaseElement> relationIds) {
        this.relationIds = relationIds;
    }

    public String getCanonicalId() {
        return canonicalId;
    }

    public void setCanonicalId(String canonicalId) {
        this.canonicalId = canonicalId;
    }
}
