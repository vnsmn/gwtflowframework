package gwtflow.flow.server.neo4j.domain;

import org.springframework.data.neo4j.annotation.*;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class PointEntity {
    @GraphId
    Long id;
    @Indexed
    String name;

    public PointEntity() {
    }

    public PointEntity(String name) {
        this.name = name;
    }

//    @RelatedToVia(type = Point2CmdEntity.POINT2CMD, direction = OUTGOING)
//    @Fetch
//    Iterable<Point2CmdEntity> point2cmds;

    @RelatedTo(type = "POINT2CMD")
    @Fetch
    Set<CmdEntity> cmds = new HashSet<>();

    @RelatedTo(type = "POINT2PROP")
    @Fetch
    Set<PropEntity> props = new HashSet<>();

//    public Collection<Point2CmdEntity> getPoint2Cmds() {
//        return IteratorUtil.asCollection(point2cmds);
//    }

    public Set<CmdEntity> getCmds() {
        return cmds;
    }

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

        PointEntity view = (PointEntity) o;
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