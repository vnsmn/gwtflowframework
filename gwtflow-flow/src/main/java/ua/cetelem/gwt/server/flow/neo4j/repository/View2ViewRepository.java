package ua.cetelem.gwt.server.flow.neo4j.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import ua.cetelem.gwt.server.flow.neo4j.domain.View2ViewEntity;

public interface View2ViewRepository extends GraphRepository<View2ViewEntity> {
    View2ViewEntity findView2ViewByName(String name);
}
