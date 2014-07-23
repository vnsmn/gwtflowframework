package ua.cetelem.gwt.server.flow.dao.impl;

import ua.cetelem.gwt.server.flow.dao.AbstractFlowDao;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

public class FlowDaoImpl<T> implements AbstractFlowDao<T>, FinderExecutor<T> {

    private Class<T> type;
    private AbstractFlowDao delegateFlowDao;

    public FlowDaoImpl(Class<T> type, AbstractFlowDao delegateFlowDao) {
        this.type = type;
        this.delegateFlowDao = delegateFlowDao;
    }

    @Override
    public void create(T newInstance) throws IOException {
        delegateFlowDao.create(newInstance);
    }

    @Override
    public void update(T dirtyInstance) throws IOException {
        delegateFlowDao.update(dirtyInstance);
    }

    @Override
    public void read(String id) throws IOException {
        delegateFlowDao.read(id);
    }

    @Override
    public void delete(T instance) throws IOException {
        delegateFlowDao.delete(instance);
    }

    @Override
    public void deleteAll() throws IOException {
        delegateFlowDao.deleteAll();
    }

    @Override
    public void bind(String id1, String id2) throws IOException {
        delegateFlowDao.bind(id1, id2);
    }

    @Override
    public List<T> readAll() throws IOException {
        return delegateFlowDao.readAll();
    }

    @Override
    public List<T> executeFinder(Method method, Object[] queryArgs) {
        return delegateFlowDao.executeFinder(method, queryArgs);
    }
}
