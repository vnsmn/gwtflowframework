package gwtflow.flow.server.neo4j.dao;

import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import gwtflow.flow.server.dao.AbstractFlowDao;
import gwtflow.flow.server.dao.PointFlowDao;
import gwtflow.flow.server.dao.ViewFlowDao;
import gwtflow.flow.server.neo4j.repository.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Neo4jFlowDaoImpl<T> implements AbstractFlowDao<T> {

    @Autowired
    protected ViewRepository viewRepository;

    @Autowired
    protected PointRepository pointRepository;

    @Autowired
    protected CmdRepository cmdRepository;

    @Autowired
    protected ViewBoxRepository viewBoxRepository;

    @Autowired
    protected InfDbRepository infDbRepository;

    @Autowired
    protected GraphDatabaseService graphDatabaseService;

    @Autowired
    protected Neo4jTemplate template;

    public Neo4jFlowDaoImpl() {
    }

    @Override
    public void create(T newInstance) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(T dirtyInstance) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void read(String id) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> readAll() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(T instance) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() throws IOException {

    }

    @Override
    public List<T> executeFinder(Method method, Object[] queryArgs) {
        if (method.getDeclaringClass().isAssignableFrom(ViewFlowDao.class)
            || method.getDeclaringClass().isAssignableFrom(PointFlowDao.class)) {
            try {
                return (List<T>) invoke(method, queryArgs);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public void bind(String id1, String id2) throws IOException {
        throw new UnsupportedOperationException();
    }

    private Object invoke(Method method, Object[] queryArgs) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return getClass().getDeclaredMethod(method.getName(), method.getParameterTypes())
                .invoke(this, queryArgs);
    }
}
