package gwtflow.flow.server.neo4j.domain;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class ViewBoxEntity {
    @GraphId
    Long id;
    String name;
    int index;
    String port;

    public ViewBoxEntity() {
    }

    public ViewBoxEntity(String name, String port, int index) {
        this.name = name;
        this.port = port;
        this.index = index;
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

    public String getPort() {
        return port;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ViewBoxEntity view = (ViewBoxEntity) o;
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