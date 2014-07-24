package gwtflow.flow.server.neo4j.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import gwtflow.flow.server.neo4j.domain.CmdEntity;

public interface CmdRepository extends GraphRepository<CmdEntity> {
    CmdEntity findByName(String name);
}
