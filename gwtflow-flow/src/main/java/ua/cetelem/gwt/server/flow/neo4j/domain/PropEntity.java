package ua.cetelem.gwt.server.flow.neo4j.domain;

import org.springframework.data.neo4j.annotation.*;

import java.util.Set;

@NodeEntity
public class PropEntity {
    @GraphId
    Long id;

    @Indexed
    String name;
    String value;

    public PropEntity() {
    }

    public PropEntity(String name, String value) {
        this.name = name;
        this.value = value;
    }


    @RelatedTo(type = "VIEW2PROP")
    @Fetch
    Set<PropEntity> props;

    public Set<PropEntity> getProps() {
        return props;
    }


    @Override
    public String toString() {
        return String.format("%s", name);
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PropEntity view = (PropEntity) o;
        if (id == null) return super.equals(o);
        return id.equals(view.id);

    }

    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : super.hashCode();
    }
}