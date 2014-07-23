package ua.cetelem.gwt.server.flow.neo4j.dao;

import ua.cetelem.gwt.server.flow.domain.InfDb;
import ua.cetelem.gwt.server.flow.domain.View;
import ua.cetelem.gwt.server.flow.neo4j.domain.InfDbEntity;
import ua.cetelem.gwt.server.flow.neo4j.domain.PropEntity;
import ua.cetelem.gwt.server.flow.neo4j.domain.ViewBoxEntity;
import ua.cetelem.gwt.server.flow.neo4j.domain.ViewEntity;

import java.io.IOException;
import java.util.*;

class Neo4jInfDbFlowDaoImpl extends Neo4jFlowDaoImpl<InfDb> {

    public Neo4jInfDbFlowDaoImpl() {
    }

    @Override
    public void create(InfDb newInfDb) throws IOException {
        InfDbEntity infDbEntity = new InfDbEntity(newInfDb.getId());
        infDbEntity.setName(newInfDb.getId());
        for (Map.Entry ent : newInfDb.getProperties().entrySet()) {
            infDbEntity.getProps().add(new PropEntity(ent.getKey().toString(), ent.getValue().toString()));
        }
        infDbRepository.save(infDbEntity);
    }

    @Override
    public void update(InfDb dirtyInstance) throws IOException {
    }

    @Override
    public void read(String id) throws IOException {
    }

    @Override
    public void delete(InfDb instance) throws IOException {
    }

    @Override
    public List<InfDb> readAll() throws IOException {
        Iterator<InfDbEntity> it = infDbRepository.findAll().iterator();
        List<InfDb> infDbs = new ArrayList<>();
        while (it.hasNext()) {
            infDbs.add(toInfDb(it.next()));
        }
        return infDbs;
    }

    @Override
    public void deleteAll() throws IOException {
        infDbRepository.deleteAll();
    }

    private InfDb toInfDb(InfDbEntity ent) {
        InfDb infDb = new InfDb(ent.getName());
        for (PropEntity prop : ent.getProps()) {
            infDb.getProperties().put(prop.getName(), prop.getValue());
        }
        return infDb;
    }
}