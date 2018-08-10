package com.wangcw.framework.base.pagination;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;
import java.util.Properties;


/**
 * EasyMyBatis Pagination plugin
 * 
 * <pre>
 * <code>
 * 	&lt;plugins&gt;
 *		 &lt;plugin interceptor="cn.easyproject.easymybatis.pagination.EasyMybatisPaginationPlugin"&gt;
 *		 		&lt;!-- required; ORACLE, ORACLE_12C, SQLSERVER, SQLSERVER_2012, MYSQL --&gt;
 *		 		&lt;property name="dialect" value="MYSQL"/&gt;
 *		 &lt;/plugin&gt;
 *	&lt;/plugins&gt;
 * </code>
 * </pre>
 * 
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }),
		@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
				RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class }),
		@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
				RowBounds.class, ResultHandler.class }) })
public class EasyMybatisPaginationPlugin implements Interceptor {

	public static Logger LOG = LoggerFactory.getLogger("base");

	private int dialect = -1;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object intercept(Invocation invocation) throws Throwable {
		if (invocation.getTarget() instanceof StatementHandler) {
			RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation.getTarget();
			Object args = statementHandler.getBoundSql().getParameterObject();
			if (args != null && args.getClass().equals(PageBean.class)) {
				PageBean pb = (PageBean) args;
				Connection connection = (Connection) invocation.getArgs()[0];
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					pstmt = connection
							.prepareStatement(pb.getAutoCountSQL().replaceAll("\\#\\{[\\ ]*(\\S*)[\\ ]*\\}", "?"));
					statementHandler.parameterize(pstmt);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						int totalRecord = rs.getInt(1);
						pb.setRowsCount(totalRecord);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						if (rs != null)
							rs.close();
						if (pstmt != null)
							pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				// Statement statement = statementHandler.prepare(connection,
				// transactionTimeout);
				// statementHandler.parameterize(statement);
				// List list = statementHandler.query(statement, null);
				// pb.setData(list);
			}
			Object result = invocation.proceed();
			return result;
		} else if (invocation.getTarget() instanceof Executor) {
			if (invocation.getArgs() != null && invocation.getArgs().length > 2 && invocation.getArgs()[1] != null
					&& invocation.getArgs()[1].getClass().equals(PageBean.class)) {
				// Pagination
				PageBean pb = (PageBean) invocation.getArgs()[1];
				Executor ex = (Executor) invocation.getTarget();
				Connection connection = (Connection) ex.getTransaction().getConnection();

				// if not set dialect
				if (pb.getDialect() == -1) {
					// check default dialect parameter
					if (dialect == -1) {
						// Auto Identification dialect
						DatabaseMetaData dbmd = connection.getMetaData();
						String productName = dbmd.getDatabaseProductName().replace(" ", "").toLowerCase();
						String version = dbmd.getDriverVersion().replace(" ", "").toLowerCase();

						if (productName.contains("oracle") && version.startsWith("12.")) {
							dialect = PageBean.ORACLE_12C_DIALECT;
						} else if (productName.contains("oracle")) {
							dialect = PageBean.ORACLE_DIALECT;
						} else if (productName.contains("mysql")) {
							dialect = PageBean.MYSQL_DIALECT;
						} else if (productName.contains("sqlserver") && version.startsWith("sqlserver2012")) {
							dialect = PageBean.SQLSERVER_2012_DIALECT;
						} else if (productName.contains("sqlserver")) {
							dialect = PageBean.SQLSERVER_DIALECT;
						} else {
							throw new EasyMyBatisPaginationException("Sorry, the database '"
									+ dbmd.getDatabaseProductName()
									+ "' now not support(Only support: oracle12c, oracle, sqlserver2012, sqlserver, mysql). Please contact 'inthinkcolor@gmail.com' or edit source code write yourself support!");
						}

					}

					pb.setDialect(dialect);
				}
			}

			Object result = invocation.proceed();

			if (invocation.getArgs() != null && invocation.getArgs().length > 2 && invocation.getArgs()[1] != null
					&& invocation.getArgs()[1].getClass().equals(PageBean.class)) {
				// Pagination
				PageBean pb = (PageBean) invocation.getArgs()[1];
				pb.setData((List) result);
			}
			return result;
		} else {
			return invocation.proceed();
		}
	}

	public Object plugin(Object target) {
		if (target instanceof StatementHandler) {
			return Plugin.wrap(target, this);
		} else if (target instanceof Executor) {
			return Plugin.wrap(target, this);
		} else {
			return target;
		}
	}

	public void setProperties(Properties properties) {
		Object dialectValue = properties.get("dialect");
		if (dialectValue == null) {
			LOG.warn("EasyMyBatisPagination - the defualt 'dialect' parameter is not set!");
			return;
		}
		String dialectValueStr = dialectValue.toString();
		if (dialectValueStr.equalsIgnoreCase("ORACLE")) {
			dialect = PageBean.ORACLE_DIALECT;
		} else if (dialectValueStr.equalsIgnoreCase("ORACLE_12C")) {
			dialect = PageBean.ORACLE_12C_DIALECT;
		} else if (dialectValueStr.equalsIgnoreCase("SQLSERVER")) {
			dialect = PageBean.SQLSERVER_DIALECT;
		} else if (dialectValueStr.equalsIgnoreCase("SQLSERVER_2012")) {
			dialect = PageBean.SQLSERVER_2012_DIALECT;
		} else if (dialectValueStr.equalsIgnoreCase("MYSQL")) {
			dialect = PageBean.MYSQL_DIALECT;
		} else {
			LOG.warn("EasyMyBatisPagination - Unkonw defualt 'dialect' parameter value '" + dialectValueStr + "'!");
			// throw new EasyMyBatisPaginationException("the dialect '" +
			// dialect + "' unkonw!");
		}
	}
}