package ua.cetelem.gwt.server.flow.neo4j.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ua.cetelem.gwt.server.flow.neo4j.domain.View2ViewEntity;
import ua.cetelem.gwt.server.flow.neo4j.domain.ViewEntity;

import java.util.List;

public interface ViewRepository extends GraphRepository<ViewEntity> {
    ViewEntity findByName(String name);

    @Query("START view1=Node({0}) " +
            " MATCH view1-[r:VIEW2VIEW]->view2 " +
            " WHERE r.name = 'VIEW2VIEW'" +
            " RETURN r " +
            //" ORDER BY r.name DESC")
            "")
    List<View2ViewEntity> listView2View(ViewEntity viewEntity);

    @Query("START view1=node:ViewEntity(name={name}) " +
            " MATCH view1-[r:VIEW2VIEW]->view2 " +
            " WHERE r.name = 'VIEW2VIEW'" +
            " RETURN view2 " +
            //" ORDER BY r.name DESC")
            "")
    List<ViewEntity> listView(@Param("name") String id);
}
