package gwtflow.flow.server.neo4j.dao;

import gwtflow.flow.server.domain.View;
import gwtflow.flow.server.neo4j.domain.PropEntity;
import gwtflow.flow.server.neo4j.domain.ViewBoxEntity;
import gwtflow.flow.server.neo4j.domain.ViewEntity;

import java.io.IOException;
import java.util.*;

class Neo4jViewFlowDaoImpl extends Neo4jFlowDaoImpl<View> {

    public Neo4jViewFlowDaoImpl() {
    }

    @Override
    public void create(View newView) throws IOException {
        ViewEntity viewEntity = new ViewEntity(newView.getId());
        viewEntity.setName(newView.getId());
        for (Map.Entry ent : newView.getProperties().entrySet()) {
            viewEntity.getProps().add(new PropEntity(ent.getKey().toString(), ent.getValue().toString()));
        }
        for (Map.Entry<String, String> ent : newView.getPorts().entrySet()) {
            viewEntity.getPorts().add(new PropEntity(ent.getKey(), ent.getValue()));
        }
        for (View.ViewBox ent : newView.getViewBoxs()) {
            ViewBoxEntity viewBoxEntity = new ViewBoxEntity(ent.getViewId(), ent.getPort(), ent.getIndex());
            viewBoxRepository.save(viewBoxEntity);
            viewEntity.getViewBoxs().add(viewBoxEntity);
        }
        viewRepository.save(viewEntity);
    }

    @Override
    public void update(View dirtyInstance) throws IOException {
    }

    @Override
    public void read(String id) throws IOException {
    }

    @Override
    public void delete(View instance) throws IOException {
        ViewEntity vent = viewRepository.findByName(instance.getId());
        if (vent != null) {
            viewRepository.delete(vent);
        }
    }

    @Override
    public void deleteAll() throws IOException {
        viewRepository.deleteAll();
    }

    @Override
    public List<View> readAll() throws IOException {
        Iterator<ViewEntity> it = viewRepository.findAll().iterator();
        List<View> views = new ArrayList<>();
        while (it.hasNext()) {
            views.add(toView(it.next()));
        }
        return views;
    }

    public List<View> findByName(String name) {
        ViewEntity viewEntity = viewRepository.findByName(name);
        return viewEntity == null
                ? Collections.EMPTY_LIST
                : Arrays.asList(toView(viewEntity));
    }

    private View toView(ViewEntity vent) {
        View view = new View(vent.getName());
        for (PropEntity prop : vent.getProps()) {
            view.getProperties().put(prop.getName(), prop.getValue());
        }
        for (PropEntity prop : vent.getPorts()) {
            view.getPorts().put(prop.getName(), prop.getValue());
        }
        for (ViewBoxEntity vbent : vent.getViewBoxs()) {
            view.addViewBox(vbent.getName(), vbent.getPort(), vbent.getIndex());
        }
        return view;
    }
}