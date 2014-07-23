package ua.cetelem.gwt.server.flow.dao;


import java.util.List;

public interface CmdFlowDao<Cmd> extends AbstractFlowDao<Cmd> {
    List<Cmd> findByName(String name);
}
