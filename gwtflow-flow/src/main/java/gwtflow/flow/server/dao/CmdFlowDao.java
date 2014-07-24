package gwtflow.flow.server.dao;


import java.util.List;

public interface CmdFlowDao<Cmd> extends AbstractFlowDao<Cmd> {
    List<Cmd> findByName(String name);
}
