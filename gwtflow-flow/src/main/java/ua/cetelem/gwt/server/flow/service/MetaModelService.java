package ua.cetelem.gwt.server.flow.service;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.kernel.EmbeddedGraphDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ua.cetelem.gwt.server.flow.dao.CmdFlowDao;
import ua.cetelem.gwt.server.flow.dao.InfDbFlowDao;
import ua.cetelem.gwt.server.flow.dao.PointFlowDao;
import ua.cetelem.gwt.server.flow.dao.ViewFlowDao;
import ua.cetelem.gwt.server.flow.domain.*;
import ua.cetelem.gwt.server.flow.xml.ImportHelper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.FileTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MetaModelService {
    private Resource flowResource;
    private Map<String, View> viewMap = new HashMap<>();
    private Map<String, Point> pointMap = new HashMap<>();

    @Autowired
    private ViewFlowDao<View> viewFlowDao;

    @Autowired
    private PointFlowDao<Point> pointFlowDao;

    @Autowired
    private CmdFlowDao<Cmd> cmdFlowDao;

    @Autowired
    private InfDbFlowDao<InfDb> infDbFlowDao;

    @Autowired
    private GraphDatabaseService graphDatabaseService;

    public void setFlowResource(Resource flowResource) throws IOException {
        this.flowResource = flowResource;
    }

    synchronized public void save() throws IOException {
        Container container = ImportHelper.execute(flowResource.getInputStream());
        viewFlowDao.deleteAll();
        pointFlowDao.deleteAll();
        cmdFlowDao.deleteAll();
        for (View view : container.getViews()) {
            viewFlowDao.create(view);
        }
        for (Point point : container.getPoints()) {
            pointFlowDao.create(point);
        }
        refreshMap(container);
    }

    synchronized public void load() throws IOException {
        if (canImport()) {
            save();
        }
        Container container = new Container(viewFlowDao.readAll(), pointFlowDao.readAll());
        refreshMap(container);
    }

    synchronized public View getViewById(String id) {
        return viewMap.get(id);
    }

    synchronized public Point getPointById(String id) {
        return pointMap.get(id);
    }

    private void refreshMap(Container container) {
        viewMap.clear();
        pointMap.clear();
        for (View view : container.getViews()) {
            viewMap.put(view.getId(), view);
        }
        for (Point point : container.getPoints()) {
            pointMap.put(point.getId(), point);
        }
    }

    public void deleteDb() throws IOException {
        File file = new File(((EmbeddedGraphDatabase) graphDatabaseService).getStoreDir());
        //File file = new File("/tmp/data/");
        recursiveDeleteDb(file);
    }

    private void recursiveDeleteDb(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                for (File child : file.listFiles()) {
                    recursiveDeleteDb(child);
                }
            }
            file.delete();
        }
    }

    synchronized public boolean canImport() throws IOException {
        File file = flowResource.getFile();
        String actSize = Long.toString(Files.size(file.toPath()));
        FileTime lastModifiedTime = Files.getLastModifiedTime(file.toPath(), new LinkOption[0]);
        String actLastModifiedTime =  Long.toString(lastModifiedTime.toMillis());

        List<InfDb> infDbs = infDbFlowDao.readAll();
        if (infDbs.isEmpty()) {
            infDbFlowDao.deleteAll();
            InfDb infDb = new InfDb("neo4j");
            infDb.getProperties().setProperty("size", actSize);
            infDb.getProperties().setProperty("date", actLastModifiedTime);
            infDbFlowDao.create(infDb);
            return true;
        }
        InfDb infDb = infDbs.get(0);
        String expSize = infDb.getProperties().getProperty("size");
        String expLastModifiedTime = infDb.getProperties().getProperty("date");
        if (!expSize.equals(actSize) || !expLastModifiedTime.equals(actLastModifiedTime)) {
            infDbFlowDao.deleteAll();
            infDb = new InfDb("neo4j");
            infDb.getProperties().setProperty("size", actSize);
            infDb.getProperties().setProperty("date", actLastModifiedTime);
            infDbFlowDao.create(infDb);
            return true;
        }
        return false;
    }

}
