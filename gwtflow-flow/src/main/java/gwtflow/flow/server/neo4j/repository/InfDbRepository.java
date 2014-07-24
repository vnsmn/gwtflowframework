package gwtflow.flow.server.neo4j.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import gwtflow.flow.server.neo4j.domain.InfDbEntity;

public interface InfDbRepository extends GraphRepository<InfDbEntity> {
}
