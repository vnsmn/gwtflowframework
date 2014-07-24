package gwtflow.flow.server.neo4j.domain;

import org.springframework.data.neo4j.annotation.*;

@RelationshipEntity(type = "VIEW2VIEW")
public class View2ViewEntity {
    public static final String VIEW2VIEW = "VIEW2VIEW";

    @GraphId
    Long id;
    @EndNode
    ViewEntity endViewNode;
    @StartNode
    ViewEntity startViewNode;

    @Indexed
    String name;

    public View2ViewEntity() {
    }

    public View2ViewEntity(ViewEntity start, ViewEntity end) {
        this.endViewNode = end;
        this.startViewNode = start;
        this.name = start.getName() + "-" + end.getName();
    }

    public ViewEntity getEndViewNode() {
        return endViewNode;
    }

    public void setEndViewNode(ViewEntity endViewNode) {
        this.endViewNode = endViewNode;
    }

    public ViewEntity getStartViewNode() {
        return startViewNode;
    }

    public void setStartViewNode(ViewEntity startViewNode) {
        this.startViewNode = startViewNode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s view %s", startViewNode.getId(), endViewNode.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        View2ViewEntity role = (View2ViewEntity) o;
        if (id == null) return super.equals(o);
        return id.equals(role.id);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : super.hashCode();
    }

}
