package ua.cetelem.gwt.server.flow.dao;


import java.util.List;

public interface ViewFlowDao<View> extends AbstractFlowDao<View> {
    List<View> findByName(String name);
}
