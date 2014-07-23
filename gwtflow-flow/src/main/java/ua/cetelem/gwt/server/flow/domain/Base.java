package ua.cetelem.gwt.server.flow.domain;

import java.util.Map;
import java.util.Properties;

abstract public class Base {
    private String id;
    private Properties properties = new Properties();

    public Base(String id) {
        this.id = id;
    }

    public void addProperty(String key, String value) {
        properties.put(key, value);
    }

    public Properties getProperties() {
        return properties;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Base base = (Base) o;

        if (id != null ? !id.equals(base.id) : base.id != null) return false;
        if (properties != null ? !properties.equals(base.properties) : base.properties != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (properties != null ? properties.hashCode() : 0);
        return result;
    }

    public void dump() {
        System.out.println("*******************************");
        System.out.println(getClass().getName());
        System.out.println("id = " + getId());

        System.out.println("----- properties -----");
        for (Map.Entry<Object, Object> ent : getProperties().entrySet()) {
            System.out.println("     " + ent.getKey() + "=" + ent.getValue());
        }
    }
}
