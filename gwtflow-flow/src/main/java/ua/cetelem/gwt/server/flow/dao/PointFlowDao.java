package ua.cetelem.gwt.server.flow.dao;


import java.util.List;

public interface PointFlowDao<Point> extends AbstractFlowDao<Point> {
    List<Point> findByName(String name);
}
