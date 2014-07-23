package ua.cetelem.gwt.server.flow.neo4j.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ua.cetelem.gwt.server.flow.neo4j.domain.PointEntity;
import ua.cetelem.gwt.server.flow.neo4j.domain.View2ViewEntity;
import ua.cetelem.gwt.server.flow.neo4j.domain.ViewEntity;

import java.util.List;

public interface PointRepository extends GraphRepository<PointEntity> {
    PointEntity findByName(String name);
}
