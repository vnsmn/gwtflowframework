package ua.cetelem.gwt.server.flow.xml;

import gwtflow.flow.server.xml.ImportHelper;
import org.junit.Test;

public class ImportHelperTest {
    @Test
    public void test1() throws Exception {
        ImportHelper.execute("flow4.xml");
    }
}
