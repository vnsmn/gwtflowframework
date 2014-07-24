package gwtflow.flow.server.neo4j.domain;

import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class InfDbEntity {
    @GraphId
    Long id;
    String name;

    public InfDbEntity() {
    }

    public InfDbEntity(String name) {
        this.name = name;
    }


    @RelatedTo(type = "INFO2PROP")
    @Fetch
    Set<PropEntity> props = new HashSet<>();

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

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InfDbEntity view = (InfDbEntity) o;
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