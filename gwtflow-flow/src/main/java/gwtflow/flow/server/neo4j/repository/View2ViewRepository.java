package gwtflow.flow.server.neo4j.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import gwtflow.flow.server.neo4j.domain.View2ViewEntity;

public interface View2ViewRepository extends GraphRepository<View2ViewEntity> {
    View2ViewEntity findView2ViewByName(String name);
}
