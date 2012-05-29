package trionesII.sql;

import trionesII.util.StringMethod;
import trionesII.Debug;

/**
* SQL语法函数库（创建于 2003.03.09）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
提供与标准SQL语法相关的静态方法。
</pre>
<DT><B>使用说明：</B><DD>
<pre>
略
</pre>
<DT><B>注意事项：</B><DD>
<pre>
略
</pre>
<DT><B>展望未来：</B><DD>
<pre>
略
</pre>
<DT><B>联系方式：</B><DD>
<pre>
email：wyq@triok.com
电  话:021-68672222-2013
</pre>
</DL>
* @author	吴勇庆
* @version 2003.03.18
*/

public class Syntax
{	
/**版本号（创建于 2003.03.18）.
<DL>
<DT><B>说明：</B><DD>
<pre>
VERSION = "2003.03.18"
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.18.
</pre>
</DL>
*/
	public static final String VERSION = "2003.03.18";	

/**
* 列名分隔符（创建于 2003.03.06）.
<DL>
<DT><B>说明：</B><DD>
<pre>
FIELD_APART = ","
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.06.
</pre>
</DL>
*/
	public final static String FIELD_APART = ",";
	
/**
* 绑定值占位符（创建于 2003.03.17）.
<DL>
<DT><B>说明：</B><DD>
<pre>
BIND_SYNTAX = "?"
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.17.
</pre>
</DL>
*/
	
public static String BIND_SYNTAX = "?";
	

	
/**
* 生产预编译INSERT语句（创建于 2003.03.17）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.17.
</pre>
</DL>
* @param table 表或视图名.
* @param colNames 列名.
* @return 预编译INSERT语句.
*/
	static String prepareInsert(String table,String[] colNames)
	{	String ls_cols = "",ls_vals = "";
		
		for(int i=0;i<colNames.length;i++)
		{	if(i>0)
			{	ls_cols += FIELD_APART;
				ls_vals += FIELD_APART;
			}
			ls_cols += colNames[i];
			ls_vals += BIND_SYNTAX;		
		}
		
		return toINSERT(table,ls_cols,ls_vals);
	}
	
	/**
	 * 生成预编译的UPDATE子句（创建于 2003.03.17）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.17.
	</pre>
	</DL>
	@param table 表或视图名.
	@param colNames 列名.
* @return 预编译的UPDATE语句.
*/
	static String prepareUpdate(String table,String[] colNames)
	{	String ls_cols = "",ls_vals = "",ls_expressions = "";
		
		for(int i=0;i<colNames.length;i++)
		{	if(i>0) ls_expressions += FIELD_APART;
			ls_expressions += colNames[i] + " = " + BIND_SYNTAX;
		}
		
		return toUPDATE(table,ls_expressions);	
	}

	/**
	 * 生成预编译的DELETE语句（创建于 2003.03.17）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.17.
	</pre>
	</DL>
	@param table 表或视图名.
* @return 预编译DELETE语句.
*/
	static String prepareDelete(String table)
	{	return toDELETE(table);
	}
	

	/**
	 * 生成预编译的SELECT子句（创建于 2003.03.17）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.17.
	</pre>
	</DL>
	@param table 表或视图名.
	@param computes 查询表达式.
* @return 预编译查询语句.
*/
	static String prepareSelect(String table,String[] computes)
	{	String ls_cols = "",ls_col;
			
		for(int i=0;i<computes.length;i++)
		{	if(i>0) ls_cols += FIELD_APART;
			ls_cols += computes[i];			
		}
		
		return toSELECT(table,ls_cols);
	}
	

/**
* 关系符常量,代表"and"关系（创建于 2003.03.06）.
<DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.06.
</pre>
</DL>
*/
	public static final int CNDRELATION_AND = 0;
/**
* 关系符常量,代表"or"关系（创建于 2003.03.06）.
<DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.06.
</pre>
</DL>
*/
	public static final int CNDRELATION_OR = 1;
/**
* 关系符表达式清单（创建于 2003.03.06）.
<DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.06.
</pre>
</DL>
*/
	static final String[] CNDRELATION_SYNTAX = {" AND "," OR "};	
	
/**
* 排序符，顺序模式（创建于 2003.03.06）.
<DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.06.
</pre>
</DL>
*/
	public static final int SORT_ASC = 0;
/**
* 排序符，逆序模式（创建于 2003.03.06）.
<DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.06.
</pre>
</DL>
*/
	public static final int SORT_DESC = 1;
/**
* 排序表达式清单（创建于 2003.03.06）.
<DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.06.
</pre>
</DL>
*/
	static final String[] SORT_SYNTAX = {" ASC"," DESC"};


	
/**
* 不含条件表达式的查询语句（创建于 2003.03.17）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
computeSyntax中如果有多个列名或表达式,之间用FIELD_APART分隔.
table中如果有多个表，之间也用FIELD_APART分隔.

如果table为null或空字符串，则返回不含"FROM" 的SELECT子句.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.17.
</pre>
</DL>
* @param table 数据所在表或视图.
* @param computeSyntax 被检索的一组列名或计算列表达式.
* @return 查询语句.
*/
	static String toSELECT(String table,String computeSyntax)
	{	return "SELECT " + computeSyntax + (StringMethod.isValid(table)?" FROM " + table:"");
	}
	

	
/**
* 不含条件表达式的删除语句（创建于 2003.03.17）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.17.
</pre>
</DL>
* @param table 数据所在表或视图.
* @return 删除语句.
*/
	static String toDELETE(String table)
	{	return "DELETE FROM " + table;
	}
	

	
/**
* 新增语句（创建于 2003.03.17）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
colSyntax和valSyntax中如果存在多个域，之间用FIELD_APART分隔.
如果colSyntax为null或无效字符串，则返回不包括colSyntax的INSERT语句。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.17.
</pre>
</DL>
* @param table 数据所在表或视图.
* @param colSyntax 列表达式.
* @param valSyntax 值表达式.
* @return 新增语句.
*/
	static String toINSERT(String table,String colSyntax,String valSyntax)
	{	return "INSERT INTO " + table + 
								(StringMethod.isValid(colSyntax)?"(" + colSyntax + ")":"") + " VALUES(" + valSyntax + ")";
	}
	

	
/**
* 不含条件表达式的修改语句（创建于 2003.03.17）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
setSyntax中如果存在多个域，之间用FIELD_APART分隔.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.17.
</pre>
</DL>
* @param table 数据所在表或视图.
* @param setSyntax 列赋值表达式.
* @return 修改语句.
*/
	static String toUPDATE(String table,String setSyntax)
	{	return "UPDATE " + table + 
									" SET " + setSyntax;	
	}
	

	
/**
* 条件语句（创建于 2003.03.17）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
cndSyntax中如果有多个表达式，之间用CNDRELATION_SYNTAX中的元素分隔.

如果cndSyntax为null或无效字符串，则返回空字符串.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.17.
</pre>
</DL>
* @param cndSyntax 条件表达式.
* @return 条件语句.
*/
	static String toWHERE(String cndSyntax)
	{	return (StringMethod.isValid(cndSyntax)?" WHERE " + cndSyntax:"");
	}
	

	
/**
* 排序语句（创建于 2003.03.17）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
sortSyntax中用于表示排序模式的用SORT_SYNTAX中的元素，如果有多个，用FIELD_APART分隔.
如果sortSyntax为null或无效字符串，则返回空字符串.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.17.
</pre>
</DL>
* @param sortSyntax 排序表达式.
* @return 排序语句.
*/
	static String toORDERBY(String sortSyntax)
	{	return (StringMethod.isValid(sortSyntax)?" ORDER BY " + sortSyntax:"");
	}
	

	
/**
* 分组语句（创建于 2003.03.17）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
groupSyntax中如果有多个分组表达式，用FIELD_APART分隔.
如果groupSyntax为null或无效字符串，则返回空字符串.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.17.
</pre>
</DL>
* @param groupSyntax 分组表达式.
* @return 分组语句.
*/
	static String toGROUPBY(String groupSyntax)
	{	return (StringMethod.isValid(groupSyntax)?" GROUP BY " + groupSyntax:"");
	}
	

	
/**
* 取预编译用的绑定值占位符列表（创建于 2003.03.17）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
返回形式：?,?,?...
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.17.
</pre>
</DL>
* @param num 占位符个数.
* @return 占位符列表.
*/
	static String toBindSyntax(int num)
	{ return StringMethod.fill(Syntax.BIND_SYNTAX + Syntax.FIELD_APART,num - 1) + Syntax.BIND_SYNTAX;
	}
	
	

	
/**
 * 在条件语句（不含WHERE关键字）中附加条件表达式（创建于 2003.03.08）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
条件表达式直接采用SQL语义描述，附加条件与原条件语句的整体之间为并列关系。 
本方法不对附加条件的有效性进行验证。

cndRelation的值为:CNDRELATION_AND、CNDRELATION_OR
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.08.
</pre>
</DL>
* @param cndSyntax 条件语句.
* @param cndRelation 附加条件与条件语句之间的关系.
* @param cndExpression 附加条件表达式.
* @return 新条件语句.
*/
	static String appendCondition(String cndSyntax,int cndRelation,String cndExpression)
	{ if(!StringMethod.isValid(cndExpression)) return cndSyntax;
		if(!StringMethod.isValid(cndSyntax)) return cndExpression;
		
		return "(" + cndSyntax + ")" + CNDRELATION_SYNTAX[cndRelation] + cndExpression;		
	}
	

	
/**
* 判断关系符的合法性（创建于 2003.03.18）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.18.
</pre>
</DL>
* @param cndRelation 关系符.
* @return true代表合法,false代表非法.
*/
static boolean isValidCndRelation(int cndRelation)
{	return (CNDRELATION_AND <= cndRelation && cndRelation <= CNDRELATION_OR);
}
/**
* 判断排序符的合法性（创建于 2003.03.18）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.18.
</pre>
</DL>
* @param cndRelation 关系符.
* @return true代表合法,false代表非法.
*/
static boolean isValidSort(int mode)
{ return (mode >= SORT_ASC && mode <= SORT_DESC);
}
	

	
/**
* 验证条件关系符的合法性（创建于 2003.03.18）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
如果不合法会抛IllegalArgumentException
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.18.
</pre>
</DL>
* @param cndRelation 条件关系符.
*/
	static void verifyCndRelation(int cndRelation)
	{	if(!isValidCndRelation(cndRelation)) throw new IllegalArgumentException(cndRelation + " is not valid CNDRELATION");	
	}
/**
* 验证排序符的合法性（创建于 2003.03.18）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
如果不合法会抛IllegalArgumentException
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.18.
</pre>
</DL>
* @param cndRelation 排序符.
*/
	static void verifySort(int mode)
	{	if(!isValidSort(mode)) throw new IllegalArgumentException(mode + " is not valid SORT");	
	}
	

	
/**
* 是否为查询语句（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.13.
</pre>
</DL>
* @param sql 数据库操作命令.
* @return true代表是查询语句，false代表不是.
*/
	static boolean isSQLSelect(String sql)
	{	String ls_sql = (sql + "").toUpperCase();
		return (ls_sql.indexOf("SELECT ")>=0);
	}
	

	
/**
* 取查询语句中的库表（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
如果不是查询语句或语句不正确，则返回空字符串。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.13.
</pre>
</DL>
* @param sql 查询语句.
* @return 库表.
*/
	static String getSQLSelectTable(String sql)
	{	int li_pos;
		String ls_sql = (sql + "").toUpperCase();
		String ls_table = "";

		li_pos = ls_sql.indexOf(" FROM ");
		
		if(li_pos > 0)
		{ ls_table = sql.substring(li_pos + 6);
		
			ls_sql = ls_table.toUpperCase();
			li_pos = ls_sql.indexOf(" WHERE ");
			if(li_pos < 0) li_pos = ls_sql.indexOf(" GROUP BY ");
			if(li_pos < 0) li_pos = ls_sql.indexOf(" ORDER BY ");
			
			if(li_pos > 0) ls_table = ls_table.substring(0,li_pos);
			
			ls_table = ls_table.trim();
		}
		
		return ls_table;
	}
/**
* 取查询语句中的列名清单（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
如果不是查询语句或语句不正确，则返回空字符串。
列名清单中如有空字符串元素，则说明查询语句不正确。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.13.
</pre>
</DL>
* @param sql 查询语句.
* @return 列名清单.
*/
	static String[] getSQLSelectFields(String sql)
	{	int li_pos;
		String ls_sql = (sql + "").toUpperCase();
		String ls_fields = "";

		li_pos = ls_sql.indexOf("SELECT ");
		
		if(li_pos >= 0)
		{ ls_fields = sql.substring(li_pos + 7);
		
			ls_sql = ls_fields.toUpperCase();
			li_pos = ls_sql.indexOf(" FROM ");
			
			if(li_pos > 0) ls_fields = ls_fields.substring(0,li_pos);
		}
		
		//Debug.println(ls_fields);
		String[] lfields = StringMethod.toChilds(ls_fields,',');
		
		//过滤空格
		for(int i=0;i<lfields.length;i++)
		{	lfields[i] = lfields[i].trim();
		}
		
		return lfields;
	}
}

