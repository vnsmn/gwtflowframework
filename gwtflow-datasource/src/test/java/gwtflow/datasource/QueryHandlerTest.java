package gwtflow.datasource;

import gwtflow.datasource.sqliteparser.expression.ColumnExpression;
import gwtflow.datasource.sqliteparser.expression.TableExpression;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

public class QueryHandlerTest {
    @Test
    public void testSelectLiteralParse() {
        String sql = "select 100 from tab1";
        QueryHandler handler = new QueryHandler();
        handler.parse(sql);
        LinkedList<ColumnExpression> l = new LinkedList(handler.getSelectExpressionStack());
        ColumnExpression c = l.pollLast();
        Assert.assertTrue(c.isLiteral());
        Assert.assertEquals("100", c.getLiteral());

        sql = "select 100 , 101 from tab1";
        handler = new QueryHandler();
        handler.parse(sql);
        l = new LinkedList(handler.getSelectExpressionStack());
        c = l.pollLast();
        Assert.assertTrue(c.isLiteral());
        Assert.assertEquals("101", c.getLiteral());
        c = l.pollLast();
        Assert.assertTrue(c.isLiteral());
        Assert.assertEquals("100", c.getLiteral());

        sql = "select 100 as c1, 101 from tab1";
        handler = new QueryHandler();
        handler.parse(sql);
        l = new LinkedList(handler.getSelectExpressionStack());
        c = l.pollLast();
        Assert.assertTrue(c.isLiteral());
        Assert.assertEquals("101", c.getLiteral());
        c = l.pollLast();
        Assert.assertTrue(c.isLiteral());
        Assert.assertEquals("100", c.getLiteral());
        Assert.assertEquals("c1", c.getAlias());
    }

    @Test
    public void testSelectColumnParse() {
        String sql = "select col1 from tab1";
        QueryHandler handler = new QueryHandler();
        handler.parse(sql);
        LinkedList<ColumnExpression> l = new LinkedList(handler.getSelectExpressionStack());
        ColumnExpression c = l.pollLast();
        Assert.assertTrue(!c.isLiteral());
        Assert.assertEquals("col1", c.getColumnName());

        sql = "select tab1.col1 as alias1 from tab2";
        handler = new QueryHandler();
        handler.parse(sql);
        l = new LinkedList(handler.getSelectExpressionStack());
        c = l.pollLast();
        Assert.assertTrue(!c.isLiteral());
        Assert.assertEquals("col1", c.getColumnName());
        Assert.assertEquals("tab1", c.getTableName());
        Assert.assertEquals("alias1", c.getAlias());

        sql = "select tab1.col1 as alias1, col2 as alias2, col3, 100 from tab1";
        handler = new QueryHandler();
        handler.parse(sql);
        l = new LinkedList(handler.getSelectExpressionStack());
        c = l.pollLast();
        Assert.assertTrue(c.isLiteral());
        Assert.assertEquals("100", c.getLiteral());
        c = l.pollLast();
        Assert.assertTrue(!c.isLiteral());
        Assert.assertEquals("col3", c.getColumnName());
        Assert.assertTrue(c.getTableName() == null);
        c = l.pollLast();
        Assert.assertTrue(!c.isLiteral());
        Assert.assertEquals("col2", c.getColumnName());
        Assert.assertTrue(c.getTableName() == null);
        Assert.assertEquals("alias2", c.getAlias());
        c = l.pollLast();
        Assert.assertTrue(!c.isLiteral());
        Assert.assertEquals("col1", c.getColumnName());
        Assert.assertEquals("tab1", c.getTableName());
        Assert.assertEquals("alias1", c.getAlias());
    }

    @Test
    public void testTableParse() {
        String sql = "select col1 from tab1";
        QueryHandler handler = new QueryHandler();
        handler.parse(sql);
        LinkedList<TableExpression> l = new LinkedList(handler.getTableExpressionStack());
        TableExpression t = l.pollLast();
        Assert.assertEquals("tab1", t.getTableName());

        sql = "select col1 from tab1 as alias1";
        handler = new QueryHandler();
        handler.parse(sql);
        l = new LinkedList(handler.getTableExpressionStack());
        t = l.pollLast();
        Assert.assertEquals("tab1", t.getTableName());
        Assert.assertEquals("alias1", t.getAlias());

        sql = "select col1 from tab1 as alias1, tab2 as alias2, tab3";
        handler = new QueryHandler();
        handler.parse(sql);
        l = new LinkedList(handler.getTableExpressionStack());
        t = l.pollLast();
        Assert.assertEquals("tab3", t.getTableName());
        Assert.assertTrue(t.getAlias() == null);
        t = l.pollLast();
        Assert.assertEquals("tab2", t.getTableName());
        Assert.assertEquals("alias2", t.getAlias());
        t = l.pollLast();
        Assert.assertEquals("tab1", t.getTableName());
        Assert.assertEquals("alias1", t.getAlias());
    }

    @Test
    public void testJoinParse() {
        String sql = "select col1 from tab1 join jn1 on jn1.col1 = tab1.col2";
        QueryHandler handler = new QueryHandler();
        handler.parse(sql);
        LinkedList<TableExpression> l = new LinkedList(handler.getTableExpressionStack());
        TableExpression t = l.pollLast();
        Assert.assertEquals("tab1", t.getTableName());
    }
}
