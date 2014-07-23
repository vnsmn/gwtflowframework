package ua.cetelem.gwt.server.flow.neo4j.domain;

import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class ViewEntity {
    @GraphId
    Long id;
    String name;

    public ViewEntity() {
    }

    public ViewEntity(String name) {
        this.name = name;
    }

    @RelatedTo(type = "VIEW2VIEWBOX")
    @Fetch
    Set<ViewBoxEntity> viewBoxs = new HashSet<>();

    @RelatedTo(type = "VIEW2PROP")
    @Fetch
    Set<PropEntity> props = new HashSet<>();

    @RelatedTo(type = "VIEW2PORT")
    @Fetch
    Set<PropEntity> ports = new HashSet<>();

    public Set<PropEntity> getProps() {
        return props;
    }

    public Set<PropEntity> getPorts() {
        return ports;
    }

    public Set<ViewBoxEntity> getViewBoxs() {
        return viewBoxs;
    }

    @Override
    public String toString() {
        return String.format("%s", name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ViewEntity view = (ViewEntity) o;
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