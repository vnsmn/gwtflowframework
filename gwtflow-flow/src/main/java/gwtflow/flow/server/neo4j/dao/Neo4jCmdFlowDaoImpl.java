package gwtflow.flow.server.neo4j.dao;

import gwtflow.flow.server.domain.Cmd;
import gwtflow.flow.server.neo4j.domain.*;

import java.io.IOException;
import java.util.*;

class Neo4jCmdFlowDaoImpl extends Neo4jFlowDaoImpl<Cmd> {

    public Neo4jCmdFlowDaoImpl() {
    }

    @Override
    public void create(Cmd newCmd) throws IOException {
        createEntity(newCmd);
    }

    CmdEntity createEntity(Cmd newCmd) throws IOException {
        if (!findByName(newCmd.getId()).isEmpty()) {
            return null;
        }
        CmdEntity cmdEntity = new CmdEntity(newCmd.getId());
        for (String ptId : newCmd.getPoints()) {
            cmdEntity.getPoints().add(new PropEntity("POINTID", ptId));
        }
        return cmdRepository.save(cmdEntity);
    }

    @Override
    public void update(Cmd dirtyInstance) throws IOException {

    }

    @Override
    public void read(String id) throws IOException {

    }

    @Override
    public void delete(Cmd instance) throws IOException {
        CmdEntity vent = cmdRepository.findByName(instance.getId());
        if (vent != null) {
            cmdRepository.delete(vent);
        }
    }

    @Override
    public List<Cmd> readAll() throws IOException {
        Iterator<CmdEntity> it = cmdRepository.findAll().iterator();
        List<Cmd> cmds = new ArrayList<>();
        while (it.hasNext()) {
            cmds.add(toCmd(it.next()));
        }
        return cmds;
    }

    @Override
    public void deleteAll() throws IOException {
        cmdRepository.deleteAll();
    }

    public List<Cmd> findByName(String name) {
        CmdEntity cmdEntity = cmdRepository.findByName(name);
        return cmdEntity == null
            ? Collections.EMPTY_LIST
            : Arrays.asList(toCmd(cmdEntity));
    }

    Cmd toCmd(CmdEntity cmdEnt) {
        Cmd cmd = new Cmd(cmdEnt.getName());
        for (PropEntity pt : cmdEnt.getPoints()) {
            cmd.getPoints().add(pt.getValue());
        }
        return cmd;
    }
}