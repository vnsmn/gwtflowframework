package ua.cetelem.gwt.server.flow.neo4j.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import ua.cetelem.gwt.server.flow.neo4j.domain.View2ViewEntity;
import ua.cetelem.gwt.server.flow.neo4j.domain.ViewEntity;

public class View2ViewRepositoryImpl {
    @Autowired
    private Neo4jOperations template;

    public View2ViewEntity findView2ViewByName(String name) {
        return template.lookup(View2ViewEntity.class, "name", name).to(View2ViewEntity.class).singleOrNull();
    }
}
