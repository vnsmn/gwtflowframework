package gwtflow.flow.server.dao;

import gwtflow.flow.server.dao.impl.FinderExecutor;

import java.io.IOException;
import java.util.List;

public interface AbstractFlowDao<T> extends FinderExecutor<T> {
    void create(T newInstance) throws IOException;

    void update(T dirtyInstance) throws IOException;

    void read(String id) throws IOException;

    void delete(T instance) throws IOException;

    void deleteAll() throws IOException;

    void bind(String id1, String id2) throws IOException;

    List<T> readAll() throws IOException;
}
