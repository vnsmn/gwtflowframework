package gwtflow.flow.server.xml;

import org.simpleframework.xml.core.Persister;
import gwtflow.flow.server.domain.Container;
import gwtflow.flow.server.xml.domains.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final public class ImportHelper {
    final static public String FLOW_DOMAIN_PATH = "gwtflow/domain/";

    private ImportHelper() {
    }

    synchronized static public Container execute(String  flowFileName) {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(FLOW_DOMAIN_PATH + flowFileName);
        return execute(is);
    }

    synchronized static public Container execute(InputStream flowInputStream) {
        Persister persister = new Persister();
        try {
            List<Graphml> graphmls = new ArrayList<>();
            parseRecursiveFlow(persister, flowInputStream, graphmls);
            return normalizeFlow(graphmls);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                flowInputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace(System.err);
            }
        }
    }

    static private Container normalizeFlow(List<Graphml> graphmls) throws Exception {
        CollectionVisiter visiter = new CollectionVisiter();
        NormalizeVisiter normalizeVisiter = new NormalizeVisiter(visiter);
        for (BaseElement el : graphmls) {
            el.visit(visiter);
            el.visit(normalizeVisiter);
        }
        return prepare(normalizeVisiter);
    }


    static private void parseRecursiveFlow(Persister persister, InputStream source, List<Graphml> graphmls) throws Exception {
        Graphml graphml = persister.read(Graphml.class, source);
        graphmls.add(graphml);
        List<Include> includes = graphml.getIncludes();
        if (includes != null) {
            Iterator<Include> includeIterator = includes.iterator();
            while (includeIterator.hasNext()) {
                Include include = includeIterator.next();
                InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(FLOW_DOMAIN_PATH + include.getHref());
                parseRecursiveFlow(persister, inputStream, graphmls);
            }
        }
    }

    private static Container prepare(NormalizeVisiter normalizeVisiter) throws Exception {
        Container domain = new Container();
        ViewFlowBuilder viewBuilder = new ViewFlowBuilder(normalizeVisiter.getGlobalElementMap());
        PointFlowBuilder pointBuilder = new PointFlowBuilder(normalizeVisiter.getGlobalElementMap());
        for (Graph el : normalizeVisiter.getCollectionVisiter().getGraphs()) {
            if (Constants.POINT_TYPE.view.equals(el.getType())) {
                for (Node vn : el.getNodes()) {
                    viewBuilder.addNode(vn);
                }
            }
            if (Constants.POINT_TYPE.point.equals(el.getType())) {
                for (Node pn : el.getNodes()) {
                    pointBuilder.addNode(pn);
                }
            }
        }
        domain.getViews().addAll(viewBuilder.build());
        domain.getPoints().addAll(pointBuilder.build());
        return domain;
    }
}
