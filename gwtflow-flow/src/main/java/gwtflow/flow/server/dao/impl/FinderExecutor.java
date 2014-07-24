package gwtflow.flow.server.dao.impl;

import java.lang.reflect.Method;
import java.util.List;

public interface FinderExecutor<T> {
    List<T> executeFinder(Method method, Object[] queryArgs);
}
