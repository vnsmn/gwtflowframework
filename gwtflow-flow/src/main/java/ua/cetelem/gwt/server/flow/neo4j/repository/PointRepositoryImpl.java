package ua.cetelem.gwt.server.flow.neo4j.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import ua.cetelem.gwt.server.flow.neo4j.domain.*;

import java.util.HashMap;
import java.util.Map;

public class PointRepositoryImpl {

    @Autowired
    private Neo4jOperations template;

    public PointEntity findByName(String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        String q = "START v1=node(*) MATCH (v1)--(v2) WHERE v1.name?={name} RETURN v1 LIMIT 1";
        return template.query(q, params).to(PointEntity.class).singleOrNull();
    }
}
