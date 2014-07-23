package ua.cetelem.gwt.server.flow.neo4j.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import ua.cetelem.gwt.server.flow.neo4j.domain.CmdEntity;
import ua.cetelem.gwt.server.flow.neo4j.domain.PointEntity;

public interface CmdRepository extends GraphRepository<CmdEntity> {
    CmdEntity findByName(String name);
}
