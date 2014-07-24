package gwtflow.flow.server.dao;


import java.util.List;

public interface PointFlowDao<Point> extends AbstractFlowDao<Point> {
    List<Point> findByName(String name);
}
