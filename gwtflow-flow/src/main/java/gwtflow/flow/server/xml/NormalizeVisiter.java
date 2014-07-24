package gwtflow.flow.server.xml;

import org.springframework.util.StringUtils;
import gwtflow.flow.server.xml.domains.Graphml;

import java.util.HashMap;
import java.util.Map;

public class NormalizeVisiter implements Visiter {
    private CollectionVisiter collectionVisiter;
    private Map<String, BaseElement> globalElementMap = new HashMap<>();

    public NormalizeVisiter(CollectionVisiter collectionVisiter) {
        this.collectionVisiter = collectionVisiter;
    }

    @Override
    public void pushElement(BaseElement el) {
    }

    @Override
    public void popElement(BaseElement el) {
    }

    @Override
    public void execute(BaseElement el) {
        if (StringUtils.isEmpty(el.getId())) {
            return;
        }
        if (el instanceof Super && ((Super) el).hasSuper()) {
            Super aSuper = (Super) el;
            if (collectionVisiter.getElementMap().containsKey(aSuper.getSuperId())) {
                BaseElement sel = collectionVisiter.getElementMap().get(aSuper.getSuperId()).values().iterator().next();
                sel.visit(this);
                Map ids = el.getRelationIds();
                el.setRelationIds(new HashMap<String, BaseElement>());
                el.getRelationIds().putAll(sel.getRelationIds());
                el.getRelationIds().putAll(ids);
            }
        }
        for (Map.Entry<String, BaseElement> ent : el.getRelationIds().entrySet()) {
            String prefix = el instanceof Graphml ? "/" : el.getCanonicalId() + "/";
            globalElementMap.put(prefix + ent.getKey(), ent.getValue());
        }
    }

    public Map<String, BaseElement> getGlobalElementMap() {
        return globalElementMap;
    }

    public CollectionVisiter getCollectionVisiter() {
        return collectionVisiter;
    }
}
