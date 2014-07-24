package gwtflow.flow.server.xml;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import gwtflow.flow.server.service.MetaModelService;

import java.io.IOException;

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
