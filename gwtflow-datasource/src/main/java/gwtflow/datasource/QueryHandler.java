package gwtflow.datasource;

import gwtflow.datasource.sqliteparser.SQLiteBaseListener;
import gwtflow.datasource.sqliteparser.SQLiteLexer;
import gwtflow.datasource.sqliteparser.SQLiteParser;
import gwtflow.datasource.sqliteparser.expression.*;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * select tab.col
 * select tab.col as alias
 * select col
 * select col as alias
 */
public class QueryHandler {
    //private List<ParserRuleContext> parserRuleContextStack = new ArrayList<ParserRuleContext>();
    private LinkedList<TerminalNode> terminalNodeStack = new LinkedList<TerminalNode>();
    private LinkedList<ColumnExpression> selectExpressionStack = new LinkedList<ColumnExpression>();
    private LinkedList<TableExpression> tableExpressionStack = new LinkedList<TableExpression>();
    private LinkedList<TableExpression> joinExpressionStack = new LinkedList<TableExpression>();
    private LinkedList<BaseExpression> whereExpressionStack = new LinkedList<BaseExpression>();
    private LinkedList<BaseExpression> orderExpressionStack = new LinkedList<BaseExpression>();
    private LinkedList<BaseExpression> limitExpressionStack = new LinkedList<BaseExpression>();
    private LinkedList<BaseExpression> offsetExpressionStack = new LinkedList<BaseExpression>();

    List<ColumnExpression> getSelectExpressionStack() {
        return Collections.unmodifiableList(selectExpressionStack);
    }

    List<TableExpression> getTableExpressionStack() {
        return Collections.unmodifiableList(tableExpressionStack);
    }

    public void parse(String sql) {


        try {
//                //String sql = "select sel1.col1 as col2 from tab1 join tab2 on t1.c1 = t2.c2 where t11.c11 = 9 and t11.c22 = :test";
//                String sql = "select sel1 from tab1 where t11.c11 == 9";
//                //String sql = "select sel1, sel2 from tab1 where ((tbl.col1 == 9 or tbl.col1 == 10) and tbl.col2 == 10) order by t.c desc, c2 limit t.c offset 10";
//                //String sql = "select sel1 from tab1 where tbl.col1 == 9 order by t.c desc, c2 limit 10 offset 20";
//                //String sql = "select sel1 as s from tab1 as t limit t.c offset 10";
            ByteArrayInputStream bis = new ByteArrayInputStream(sql.getBytes());
            SQLiteLexer lexer = new SQLiteLexer(new ANTLRInputStream(bis));
            final SQLiteParser parser = new SQLiteParser(new CommonTokenStream(lexer));
            ParseTree tree = parser.parse();

            ParseTreeWalker.DEFAULT.walk(new SQLiteBaseListener() {

                private SQLiteParser.Column_nameContext column_nameContext;
                private SQLiteParser.Literal_valueContext literal_valueContext;
                private SQLiteParser.Table_nameContext table_nameContext;
                private SQLiteParser.Column_aliasContext column_aliasContext;
                private SQLiteParser.Table_aliasContext table_aliasContext;
                private SQLiteParser.Join_clauseContext join_clauseContext;

                public void exitEveryRule(@NotNull ParserRuleContext parserRuleContext) {
                    super.exitEveryRule(parserRuleContext);
                    System.out.println(parserRuleContext.toInfoString(parser));
                }

                //                    @Override
//                    public void exitSql_stmt(@NotNull SQLiteParser.Sql_stmtContext ctx) {
//                        super.exitSql_stmt(ctx);
//                    }
//
//                    @Override
//                    public void enterSelect_core(@NotNull SQLiteParser.Select_coreContext ctx) {
//                        super.enterSelect_core(ctx);
//                    }
//
//
                @Override
                public void enterTable_or_subquery(@NotNull SQLiteParser.Table_or_subqueryContext ctx) {
                    super.enterTable_or_subquery(ctx);
                    table_nameContext = null;
                    table_aliasContext = null;
                }

                @Override
                public void exitTable_or_subquery(@NotNull SQLiteParser.Table_or_subqueryContext ctx) {
                    super.exitTable_or_subquery(ctx);
                    TableExpression expression = new TableExpression()
                            .setTableName(table_nameContext.getText())
                            .setAlias(table_aliasContext == null ? null : table_aliasContext.getText());
                    tableExpressionStack.offerLast(expression);
                    table_nameContext = null;
                    table_aliasContext = null;
                }

                @Override
                public void exitTable_alias(@NotNull SQLiteParser.Table_aliasContext ctx) {
                    super.exitTable_alias(ctx);
                    table_aliasContext = ctx;
                }

                @Override public void enterJoin_clause(@NotNull SQLiteParser.Join_clauseContext ctx) {
                    super.enterJoin_clause(ctx);
                    join_clauseContext = null;
                }

                @Override public void exitJoin_clause(@NotNull SQLiteParser.Join_clauseContext ctx) {
                    super.exitJoin_clause(ctx);
                    join_clauseContext = null;
                }

                @Override
                public void enterResult_column(@NotNull SQLiteParser.Result_columnContext ctx) {
                    super.enterResult_column(ctx);
                    column_nameContext = null;
                    literal_valueContext = null;
                    table_nameContext = null;
                    column_aliasContext = null;
                }

                @Override
                public void exitResult_column(@NotNull SQLiteParser.Result_columnContext ctx) {
                    super.exitResult_column(ctx);
                    ColumnExpression expression;
                    try {
                        if (column_nameContext != null && literal_valueContext != null) {
                            throw new RuntimeException("exitResult_column : type = 1");
                        } else if (literal_valueContext != null) {
                            expression = new ColumnExpression();
                            expression.setLiteral(literal_valueContext.getText())
                                    .setAlias(ctx.column_alias() == null ? null : ctx.column_alias().getText());
                        } else if (column_nameContext != null) {
                            expression = new ColumnExpression();
                            expression.setColumnName(column_nameContext.getText())
                                    .setTableName(table_nameContext == null ? null : table_nameContext.getText())
                                    .setAlias(column_aliasContext == null ? null : column_aliasContext.getText());
                        } else {
                            throw new RuntimeException("exitResult_column : type = 2");
                        }
                        selectExpressionStack.add(expression);
                    } finally {
                        column_nameContext = null;
                        literal_valueContext = null;
                    }
                }

                @Override
                public void exitLiteral_value(@NotNull SQLiteParser.Literal_valueContext ctx) {
                    super.exitLiteral_value(ctx);
                    literal_valueContext = ctx;
                }

                @Override
                public void exitTable_name(@NotNull SQLiteParser.Table_nameContext ctx) {
                    super.exitTable_name(ctx);
                    table_nameContext = ctx;
                }

                @Override
                public void exitColumn_alias(@NotNull SQLiteParser.Column_aliasContext ctx) {
                    super.exitColumn_alias(ctx);
                    column_aliasContext = ctx;
                }

                @Override
                public void exitColumn_name(@NotNull SQLiteParser.Column_nameContext ctx) {
                    super.exitColumn_name(ctx);
                    column_nameContext = ctx;
                }
//                    @Override
//                    public void enterExpr(@NotNull SQLiteParser.ExprContext ctx) {
//                        super.enterExpr(ctx);
//                    }

                /**
                 * where
                 * and
                 * or
                 * identifier
                 * limit
                 * offset
                 *
                 * @param ctx
                 */
                @Override
                public void exitExpr(@NotNull SQLiteParser.ExprContext ctx) {
                    super.exitExpr(ctx);
                    if (findTerminalNode(SQLiteParser.K_WHERE) != null) {
                        if (isEnclose(ctx)) {
                            whereExpressionStack.offerLast(new EncloseExpression(whereExpressionStack.pollLast()));
                        } else if (!ctx.getTokens(SQLiteParser.EQ).isEmpty()) {
                            BaseExpression right = whereExpressionStack.pollLast();
                            BaseExpression left = whereExpressionStack.pollLast();
                            whereExpressionStack.offerLast(new EQExpression(left, right));
                        } else if (ctx.K_AND() != null) {
                            BaseExpression right = whereExpressionStack.pollLast();
                            BaseExpression left = whereExpressionStack.pollLast();
                            whereExpressionStack.offerLast(new ANDExpression(left, right));
                        } else if (ctx.K_OR() != null) {
                            BaseExpression right = whereExpressionStack.pollLast();
                            BaseExpression left = whereExpressionStack.pollLast();
                            whereExpressionStack.offerLast(new ORExpression(left, right));
                        } else if (ctx.column_name() != null && !ctx.column_name().isEmpty()) {
                            IdentifierExpression expression = new IdentifierExpression(ctx.column_name().getText());
                            if (ctx.table_name() != null && !ctx.table_name().isEmpty()) {
                                expression.setTableName(ctx.table_name().getText());
                            }
                            whereExpressionStack.offerLast(expression);
                        } else if (ctx.literal_value() != null && !ctx.literal_value().isEmpty()) {
                            whereExpressionStack.offerLast(new LiteralExpression(ctx.getText()));
                        }
                    } else if (findTerminalNode(SQLiteParser.K_LIMIT) != null) {
                        if (ctx.column_name() != null && !ctx.column_name().isEmpty()) {
                            IdentifierExpression expression = new IdentifierExpression(ctx.column_name().getText());
                            if (ctx.table_name() != null && !ctx.table_name().isEmpty()) {
                                expression.setTableName(ctx.table_name().getText());
                            }
                            limitExpressionStack.offerLast(expression);
                        } else if (ctx.literal_value() != null && !ctx.literal_value().isEmpty()) {
                            limitExpressionStack.offerLast(new LiteralExpression(ctx.getText()));
                        }
                        System.out.println();
                    } else if (findTerminalNode(SQLiteParser.K_OFFSET) != null) {
                        if (ctx.column_name() != null && !ctx.column_name().isEmpty()) {
                            IdentifierExpression expression = new IdentifierExpression(ctx.column_name().getText());
                            if (ctx.table_name() != null && !ctx.table_name().isEmpty()) {
                                expression.setTableName(ctx.table_name().getText());
                            }
                            offsetExpressionStack.offerLast(expression);
                        } else if (ctx.literal_value() != null && !ctx.literal_value().isEmpty()) {
                            offsetExpressionStack.offerLast(new LiteralExpression(ctx.getText()));
                        }
                        System.out.println();
                    }
                    //parserRuleContextStack.add(ctx);
                }

                @Override
                public void exitOrdering_term(@NotNull SQLiteParser.Ordering_termContext ctx) {
                    super.exitOrdering_term(ctx);
                    if (ctx.expr().column_name() != null && !ctx.expr().column_name().isEmpty()) {
                        OrderExpression expression = new OrderExpression(ctx.expr().column_name().getText());
                        if (ctx.expr().table_name() != null && !ctx.expr().table_name().isEmpty()) {
                            expression.setTableName(ctx.expr().table_name().getText());
                        }
                        expression.setDirect(ctx.K_DESC() != null ? OrderExpression.DIRECT.DESC : OrderExpression.DIRECT.ASC);
                        orderExpressionStack.offerLast(expression);
                    }
                }

//                    @Override
//                    public void exitFactored_select_stmt(@NotNull SQLiteParser.Factored_select_stmtContext ctx) {
//                        super.exitFactored_select_stmt(ctx);
//                    }

                public void visitTerminal(TerminalNode node) {
                    super.visitTerminal(node);
                    terminalNodeStack.add(node);
                    if (node.getSymbol().getType() == SQLiteParser.K_FROM) {
                        removeTerminalNode(SQLiteParser.K_SELECT, SQLiteParser.K_FROM);
                    } else if (node.getSymbol().getType() == SQLiteParser.K_WHERE) {
                        removeTerminalNode(SQLiteParser.K_SELECT, SQLiteParser.K_WHERE);
                    } else if (node.getSymbol().getType() == SQLiteParser.K_GROUP) {
                        removeTerminalNode(SQLiteParser.K_SELECT, SQLiteParser.K_GROUP);
                    } else if (node.getSymbol().getType() == SQLiteParser.K_HAVING) {
                        removeTerminalNode(SQLiteParser.K_SELECT, SQLiteParser.K_HAVING);
                    } else if (node.getSymbol().getType() == SQLiteParser.K_ORDER) {
                        removeTerminalNode(SQLiteParser.K_SELECT, SQLiteParser.K_ORDER);
                    } else if (node.getSymbol().getType() == SQLiteParser.K_LIMIT) {
                        removeTerminalNode(SQLiteParser.K_SELECT, SQLiteParser.K_LIMIT);
                    } else if (node.getSymbol().getType() == SQLiteParser.K_OFFSET) {
                        removeTerminalNode(SQLiteParser.K_SELECT, SQLiteParser.K_OFFSET);
                    }
                }

//                    @Override public void enterTable_name(@NotNull SQLiteParser.Table_nameContext ctx) {
//                        super.enterTable_name(ctx);
//                    }
//
//                    @Override
//                    public void enterSelect_stmt(@NotNull SQLiteParser.Select_stmtContext ctx) {
//                        super.enterSelect_stmt(ctx);
//                    }
//
//                    @Override
//                    public void exitSelect_stmt(@NotNull SQLiteParser.Select_stmtContext ctx) {
//                        super.exitSelect_stmt(ctx);
//                    }
//
//                    @Override
//                    public void enterSelect_or_values(@NotNull SQLiteParser.Select_or_valuesContext ctx) {
//                        super.enterSelect_or_values(ctx);
//                    }
//
//                    @Override
//                    public void exitSelect_core(@NotNull SQLiteParser.Select_coreContext ctx) {
//                        super.exitSelect_core(ctx);
//                    }

                private TerminalNode findTerminalNode(int type) {
                    Iterator<TerminalNode> it = terminalNodeStack.descendingIterator();
                    while (it.hasNext()) {
                        TerminalNode node = it.next();
                        if (node.getSymbol().getType() == type) {
                            return node;
                        }
                    }
                    return null;
                }

                private void removeTerminalNode(int startType, int endType) {
                    Iterator<TerminalNode> it = terminalNodeStack.descendingIterator();
                    boolean canRemove = false;
                    while (it.hasNext()) {
                        TerminalNode node = it.next();
                        if (node.getSymbol().getType() == endType) {
                            canRemove = true;
                        } else if (node.getSymbol().getType() == startType) {
                            return;
                        } else if (canRemove) {
                            it.remove();
                        }
                    }
                }

                private boolean isEnclose(SQLiteParser.ExprContext ctx) {
                    return ctx.expr() != null && !ctx.expr().isEmpty()
                            && ctx.start != null && ctx.start.getType() == SQLiteParser.OPEN_PAR
                            && ctx.stop != null && ctx.stop.getType() == SQLiteParser.CLOSE_PAR;
                }
            }, tree);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}