package gwtflow.flow.server.neo4j.dao;

import org.springframework.beans.factory.annotation.Autowired;
import gwtflow.flow.server.domain.Cmd;
import gwtflow.flow.server.domain.Point;
import gwtflow.flow.server.domain.View;
import gwtflow.flow.server.neo4j.domain.CmdEntity;
import gwtflow.flow.server.neo4j.domain.PointEntity;
import gwtflow.flow.server.neo4j.domain.PropEntity;

import java.io.IOException;
import java.util.*;

class Neo4jPointFlowDaoImpl extends Neo4jFlowDaoImpl<Point> {

    @Autowired
    private Neo4jCmdFlowDaoImpl neo4jCmdFlowDao;

    public Neo4jPointFlowDaoImpl() {
    }

    @Override
    public void create(Point newPoint) throws IOException {
        PointEntity pointEntity = new PointEntity(newPoint.getId());
        pointEntity.setName(newPoint.getId());
        for (Map.Entry ent : newPoint.getProperties().entrySet()) {
            pointEntity.getProps().add(new PropEntity(ent.getKey().toString(), ent.getValue().toString()));
        }
        for (Cmd cmd : newPoint.getCmds()) {
            pointEntity.getCmds().add(neo4jCmdFlowDao.createEntity(cmd));
        }
        pointRepository.save(pointEntity);
    }

    @Override
    public void update(Point dirtyPoint) throws IOException {
        PointEntity pt = pointRepository.findByName(dirtyPoint.getId());
        for (Cmd cmd : dirtyPoint.getCmds()) {
            CmdEntity cmdEntity = cmdRepository.findByName(cmd.getId());
            pt.getCmds().add(cmdEntity);
        }
        pointRepository.save(pt);
    }

    @Override
    public void read(String id) throws IOException {

    }

    @Override
    public void delete(Point instance) throws IOException {
        PointEntity vent = pointRepository.findByName(instance.getId());
        if (vent != null) {
            pointRepository.delete(vent);
        }
    }

    @Override
    public void deleteAll() throws IOException {
        pointRepository.deleteAll();
    }

    @Override
    public List<Point> readAll() throws IOException {
        Iterator<PointEntity> it = pointRepository.findAll().iterator();
        List<Point> points = new ArrayList<>();
        while (it.hasNext()) {
            points.add(toPoint(it.next()));
        }
        return points;
    }

    public List<View> findByName(String name) {
        PointEntity pointEntity = pointRepository.findByName(name);
        return pointEntity == null
                ? Collections.EMPTY_LIST
                : Arrays.asList(toPoint(pointEntity));
    }

    Point toPoint(PointEntity pent) {
        Point point = new Point(pent.getName());
        for (PropEntity prop : pent.getProps()) {
            point.getProperties().put(prop.getName(), prop.getValue());
        }
        for (CmdEntity cmdEnt : pent.getCmds()) {
            point.getCmds().add(neo4jCmdFlowDao.toCmd(cmdEnt));
        }
        return point;
    }
}