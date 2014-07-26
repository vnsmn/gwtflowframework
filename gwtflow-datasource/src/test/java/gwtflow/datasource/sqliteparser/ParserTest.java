package gwtflow.datasource.sqliteparser;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

import java.io.ByteArrayInputStream;

public class ParserTest {

    // A counter that keeps track of the total amount of statements
    // parsed in the test below.
    private int totalStatements = 0;

    @Test
    public void test() {

            try {
                String sql = "select t1.c1 as c2 from t1 join t2 on t1.c1 = t2.c2 where t1.c1 = 1";
                ByteArrayInputStream bis = new ByteArrayInputStream(sql.getBytes());
                SQLiteLexer lexer = new SQLiteLexer(new ANTLRInputStream(bis));
                SQLiteParser parser = new SQLiteParser(new CommonTokenStream(lexer));
                ParseTree tree = parser.parse();

                ParseTreeWalker.DEFAULT.walk(new SQLiteBaseListener(){
                    @Override
                    public void enterSql_stmt(@NotNull SQLiteParser.Sql_stmtContext ctx) {
                        totalStatements++;
                    }

                    @Override
                    public void enterSelect_core(@NotNull SQLiteParser.Select_coreContext ctx) {
                        super.enterSelect_core(ctx);
                    }

                    @Override
                    public void enterColumn_name(@NotNull SQLiteParser.Column_nameContext ctx) {
                        super.enterColumn_name(ctx);
                    }

                    @Override
                    public void enterColumn_alias(@NotNull SQLiteParser.Column_aliasContext ctx) {
                        super.enterColumn_alias(ctx);
                    }

                    @Override
                    public void enterResult_column(@NotNull SQLiteParser.Result_columnContext ctx) {
                        super.enterResult_column(ctx);
                    }

                    @Override
                    public void enterExpr(@NotNull SQLiteParser.ExprContext ctx) {
                        super.enterExpr(ctx);
                    }

                    @Override public void enterTable_name(@NotNull SQLiteParser.Table_nameContext ctx) {
                        super.enterTable_name(ctx);
                    }

                    @Override
                    public void enterSelect_stmt(@NotNull SQLiteParser.Select_stmtContext ctx) {
                        super.enterSelect_stmt(ctx);
                    }

                    @Override
                    public void enterSelect_or_values(@NotNull SQLiteParser.Select_or_valuesContext ctx) {
                        super.enterSelect_or_values(ctx);
                    }
                }, tree);
            }
            catch (Exception e) {
                e.printStackTrace();
                return;
            }
    }
}
