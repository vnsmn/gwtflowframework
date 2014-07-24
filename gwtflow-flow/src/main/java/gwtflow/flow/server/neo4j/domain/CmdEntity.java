package gwtflow.flow.server.neo4j.domain;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class CmdEntity {
    @GraphId
    Long id;
    String name;

    @RelatedTo(type = "POINT2CMD", direction = Direction.OUTGOING)
    @Fetch
    Set<PropEntity> points = new HashSet<>();

    public CmdEntity() {
    }

    public CmdEntity(String name) {
        this.name = name;
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

    public Set<PropEntity> getPoints() {
        return points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CmdEntity view = (CmdEntity) o;
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