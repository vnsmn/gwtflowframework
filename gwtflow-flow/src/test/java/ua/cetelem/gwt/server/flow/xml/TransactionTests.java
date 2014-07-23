package ua.cetelem.gwt.server.flow.xml;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.cetelem.gwt.server.flow.dao.ViewFlowDao;
import ua.cetelem.gwt.server.flow.domain.View;
import ua.cetelem.gwt.server.flow.neo4j.domain.View2ViewEntity;
import ua.cetelem.gwt.server.flow.neo4j.domain.ViewEntity;
import ua.cetelem.gwt.server.flow.neo4j.repository.ViewRepository;
import ua.cetelem.gwt.server.flow.service.MetaModelService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author mh
 * @since 08.11.11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/app-spring-context.xml"})
@DirtiesContext
public class TransactionTests {
    static {
//        System.setProperty("neostore.nodestore.db.mapped_memory", "150M");
//        System.setProperty("neostore.relationshipstore.db.mapped_memory", "5G");
//        System.setProperty("neostore.propertystore.db.mapped_memory", "100M");
//        System.setProperty("neostore.propertystore.db.strings.mapped_memory", "130M");
//        System.setProperty("neostore.propertystore.db.arrays.mapped_memory", "130M");
//        System.setProperty("use_memory_mapped_buffers", "true");
//        System.setProperty("node_auto_indexing", "true");
//        System.setProperty("neostore.propertystore.db.index.keys.mapped_memory", "150M");
//        System.setProperty("neostore.propertystore.db.index.mapped_memory", "150M");
    }

    @Autowired
    private MetaModelService metaModelService;

    @BeforeClass
    static public void up() {
    }

    @Test
    public void testViewNode() throws IOException, InterruptedException {
       metaModelService.load();
    }
}
