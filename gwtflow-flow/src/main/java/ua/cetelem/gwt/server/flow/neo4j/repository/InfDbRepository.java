package ua.cetelem.gwt.server.flow.neo4j.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import ua.cetelem.gwt.server.flow.neo4j.domain.InfDbEntity;

public interface InfDbRepository extends GraphRepository<InfDbEntity> {
}
