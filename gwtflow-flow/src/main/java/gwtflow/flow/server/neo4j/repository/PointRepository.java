package gwtflow.flow.server.neo4j.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import gwtflow.flow.server.neo4j.domain.PointEntity;

public interface PointRepository extends GraphRepository<PointEntity> {
    PointEntity findByName(String name);
}
