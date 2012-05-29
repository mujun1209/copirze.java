package triones.sql;

import triones.util.StringMethod;
import triones.util.ReflectMethod;
import triones.util.ArrayMethod;

import java.lang.reflect.Field;
import java.util.List;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
* 数据库宏定义函数库（创建于 2003.03.02）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
DBMS主要根据各类数据库各自的特点，提供统一的静态方法，使基于DBMS开发的程序可以跨数据库平台。
</pre>
<DT><B>使用说明：</B><DD>
<pre>
无
</pre>
<DT><B>名词解释：</B><DD>
<pre>
无
</pre>
<DT><B>注意事项：</B><DD>
<pre>
需JDK1.3（或更高版本）支持。
</pre>
<DT><B>展望未来：</B><DD>
<pre>
无
</pre>
<DT><B>联系方式：</B><DD>
<pre>
 email：wyq@triok.com 
 电  话:021-68672222-2013
</pre>
</DL>
*@author	吴勇庆
*@version 2003.03.05
*/
public class DBMS
{	
/**版本号（创建于 2003.03.09）.
<DL>
<DT><B>说明：</B><DD>
<pre>
VERSION = "2003.03.15"
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.09.
</pre>
</DL>
*/
public static String VERSION = "2003.03.15";

/**
* 日期格式（创建于 2003.03.14）.
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
创建于 2003.03.14.
</pre>
</DL>
*/
	private static SimpleDateFormat FORMAT_DATETIME = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");

/**<img src="important.gif" width="20" height="20" border="0">数据库变量名的最大长度（创建于 2003.03.31）.
<DL>
<DT><B>说明：</B><DD>
<pre>
各个数据库厂商对数据库中的库表、字段等名称都有各自的长度限制。
建议在设计库表相关类和属性名称时，将总长度设为小于当前值。
当前值可修改，目前是以ORACL为参考标准的。
NAMING_LENGTH = 30
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.31.
</pre>
</DL>
*/
public static int NAMING_LENGTH = 30;

/**<img src="important.gif" width="20" height="20" border="0">支持的数据库平台类型（创建于 2003.03.02）.
<DL>
<DT><B>说明：</B><DD>
<pre>
当Connector以JDBC模式连接数据库时,SUPPORTED_DBMS作为配置属性DBMS的取值范围
SUPPORTED_DBMS = {"odbc","oracle","mssqlserver","mysql","sybsqlserver","db2"}
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.02.
修改于 2003.03.03 - 增加 "sybsqlserver".
修改于 2003.03.06 - 增加 "db2".
</pre>
</DL>
* @see #ODBC
	@see #ORACLE
	@see #MSSQLSERVER
	@see #MYSQL
	@see #SYBSQLSERVER
*/
public static String[] SUPPORTED_DBMS = {"odbc","oracle","mssqlserver","mysql","sybsqlserver","db2","postgresql","jtds-sqlserver"};

/**用ODBC驱动的数据库（创建于 2003.03.02）.
<DL>
<DT><B>说明：</B><DD>
<pre>
ODBC = 0
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.02.
</pre>
</DL>

*/
	public static final int ODBC = 0;

/**ORACLE数据库（创建于 2003.03.02）.
<DL>
<DT><B>说明：</B><DD>
<pre>
目前支持ORACLE8

ORACLE = 1
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.02.
</pre>
</DL>

*/
	public static final int ORACLE = 1;

/**MICROSOFT SQLSERVER数据库（创建于 2003.03.02）.
<DL>
<DT><B>说明：</B><DD>
<pre>
目前支持SQLSERVER 2000

MSSQLSERVER = 2
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.02.
</pre>
</DL>

*/
	public static final int MSSQLSERVER = 2;

/**MY SQL数据库（创建于 2003.03.02）.
<DL>
<DT><B>说明：</B><DD>
<pre>
MYSQL = 3
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.02.
</pre>
</DL>

*/
	public static final int MYSQL = 3;
/**Sybase SQLServer数据库（创建于 2003.03.07）.
<DL>
<DT><B>说明：</B><DD>
<pre>
SYBSQLSERVER = 4
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.07.
</pre>
</DL>

*/
	public static final int SYBSQLSERVER = 4;
	
/**IBM的DB2数据库（创建于 2003.03.06）.
<DL>
<DT><B>说明：</B><DD>
<pre>
DB2 = 5
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
* 
*/
	public static final int DB2 = 5;
/**IBM的DB2数据库（创建于 2003.03.06）.
<DL>
<DT><B>说明：</B><DD>
<pre>
POSTGRESQL = 6
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
* 
*/
	public static final int POSTGRESQL = 6;
/**JTDS的SQLServer数据库（创建于 2009.10.15）.
<DL>
<DT><B>说明：</B><DD>
<pre>
POSTGRESQL = 6
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
* 
*/
	public static final int JTDS_SQLSERVER = 7;
	/**
	 * 取数据库类型的名称（创建于 2003.03.10）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//ls_dbms将等于"sybsqlserver"
	String ls_dbms = DBMS.getDBMSName(DBMS.SYBSQLSERVER);	
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.10.
	</pre>
	</DL>
* @param dbms 数据库类型.
* @return 数据库类型名称.
*/
	public static String getDBMSName(int dbms)
	{	return isSupportedDBMS(dbms)? SUPPORTED_DBMS[dbms]:"unsupported DBMS"; 
	}
	/**
	 * 判断是否支持的数据库类型（创建于 2003.03.02）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	判断数据库类型是否为SUPPORT_DBMS中支持的.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//布尔变量lb_supported的值为false
	int li_dbms = -1;	
	boolean lb_supported = DBMS.isSupportedDBMS(li_dbms);
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.02.
	</pre>
	</DL>
* @param dbms DBMS类型.
* @return true 代表合法，false代表不合法.
*/
	
	public static boolean isSupportedDBMS(int dbms)
	{	return ((0 <= dbms) && (dbms < SUPPORTED_DBMS.length));			
	}
	

	
/**
* 查找数据库类型名称对应的DBMS枚举值（创建于 2003.03.11）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
-1代表该名称尚未登记.
已登记的数据库类型名参见SUPPORTED_DBMS.
数据库类型名忽略大小写.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.11.
</pre>
</DL>
* @param name 数据库类型名.
* @return DBMS枚举值.
* 
*/
	static int findDBMS(String name)
	{	return ArrayMethod.findItemIgnoreCase(SUPPORTED_DBMS,name);
	}
/**<img src="important.gif" width="20" height="20" border="0">可自动转换的变量类型（创建于 2003.03.02）.
<DL>
<DT><B>说明：</B><DD>
<pre>
可自动进行数据库变量类型转换的的JAVA变量类型
</pre>
<DT><B>示例：</B><DD>
<pre>
SUPPORTED_FIELD_TYPE = {"java.lang.Double","java.lang.Long",
"java.lang.Integer","java.lang.Float",
"java.lang.Byte","java.lang.Char","java.lang.String",
"java.lang.Boolean","java.lang.Short",
"double","long","int","float","byte","char","boolean","short",
"[B","java.math.BigDecimal","java.sql.Date","java.sql.Time","java.sql.Timestamp",
"java.util.Date"}
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.02.
修改于 2003.03.09 - 增加 "java.util.Date".
</pre>
</DL>
* @see #isSupportedFieldType(Class)
*/
	static String [] SUPPORTED_FIELD_TYPE = {"java.lang.Double","java.lang.Long",
													"java.lang.Integer","java.lang.Float",
													"java.lang.Byte","java.lang.Char","java.lang.String",
													"java.lang.Boolean","java.lang.Short",
													"double","long","int","float","byte","char","boolean","short",
													"[B","java.math.BigDecimal","java.sql.Date","java.sql.Time","java.sql.Timestamp",
													"java.util.Date"};
													
	/**
	* 是否为可自动转换类型的域（创建于 2003.03.02）.
		<P><DL>
		<DT><B>说明：</B><DD>
		<pre>
		判断是否为可自动进行数据库变量类型转换的的JAVA变量类型，参见SUPPORTED_FIELD_TYPE.
		</pre>
		<DT><B>示例：</B><DD>
		<pre>
		无
		</pre>
		<DT><B>日志：</B><DD>
		<pre>
		创建于 2003.03.02.
		</pre>
		</DL>
	* @param fieldType 域类型.
	* @return 是或不是JDBC支持的数据库变量类型.
	*/	
	public static boolean isSupportedFieldType(Class fieldType)
	{	return (ArrayMethod.findItem(SUPPORTED_FIELD_TYPE,fieldType.getName())>=0);
	}
	
	/**数据库中的常量表（创建于 2003.03.02）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	CONST_TABLE = {"","dual","","db","systable",""}
	可以根据需要修改CONST_TABLE[]中的值。	
	主要用于DBTrans.getCompute系列方法
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//设置ODBC的默认常量表为system
	DBMS.CONST_TABLE[DBMS.ODBC] = "system";
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.02.
	修改于 2003.03.11 - 增加sybase常量表.
	</pre>
	</DL>		
	*/
	public static String[] CONST_TABLE = {"","dual","","db","systable",""};
	
	/**
	 * <img src="important.gif" width="20" height="20" border="0">将预编译SQL转换成SQL（创建于 2003.03.02）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	主要用于调试SQL语句,不建议作为实际SQL进行执行.
	values如为null则直接返回preparedSQL
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.02.
	修改于 2003.03.02 - 排除生成SQL时后半段丢失错误.
	修改于 2003.03.05 - 增加values为null时的处理.
	</pre>
	</DL>
* @param dbms 数据库类型.
* @param preparedSQL 预编译SQL语句.
* @param values 绑定值列表.
* @return 类标准SQL语句.
* @see #toSQL(int,String,List)
*/
public static String toSQL(int dbms,String preparedSQL,List values)
	{ int li_pos=0,i=0;
		String ls_head,ls_tail,ls_sql = "";
		
		ls_tail = preparedSQL;
		
		if(values!=null)
			while(i<values.size()&&li_pos!=-1)
			{	li_pos = ls_tail.indexOf(Syntax.BIND_SYNTAX);
				
				if(li_pos != -1)
				{	ls_head = ls_tail.substring(0,li_pos);
			    ls_tail = ls_tail.substring(li_pos + Syntax.BIND_SYNTAX.length());
			    ls_sql += ls_head + format(dbms,values.get(i));
					i++;
			  }
				
			}

		ls_sql += ls_tail;
		
		return ls_sql;
	}
	

	/**
	 * 将值转换为数据库中的形式（创建于 2003.03.16）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	将JAVA变量类型的数据值转换成对应的数据库变量类型的字符串格式，仅建议作调试用
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//ls_val的值将等于"TO_DATE("1998-02-03 10:22:22.01","yyyy-mm-dd hh24:mi:ss")
	Timestamp today = Timestamp.valueOf("1998-02-03 10:22:22.01");
	String ls_val = format(DBMS.ORACLE,today);
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.16.
	修改于 2003.03.24 - 修正空指针错误.
	</pre>
	</DL>
* @param dbms 数据库平台类型.
* @param obj 数据值.
* @return 与对象类型对应的数据库类型格式的字符串.
*/
	
	private static String format(int dbms,Object obj)
	{ if(obj!=null)
		{	String lClass = ReflectMethod.getClassName(obj);
			
			if(lClass.equals("[B"))
				throw new UnsupportedOperationException("byte[] is unsupported in disableBind mode!");
				
			if(lClass.equals("java.lang.String"))
				return formatVarchar(dbms,(String)obj);
			
			if(lClass.equals("java.util.Date")||lClass.equals("java.sql.Date"))
				return formatDate(dbms,(java.util.Date)obj);
				
			if(lClass.equals("java.sql.Time"))
				return formatTime(dbms,(Time)obj);
				
			if(lClass.equals("java.sql.Timestamp"))
				return formatDateTime(dbms,(Timestamp)obj);
		}
		
		return "" + obj;
	}
	/**
	 * 生成对应的数据库日期时间类型的字符串格式（创建于 2003.03.16）.
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
	创建于 2003.03.16.
	</pre>
	</DL>
* @param dbms 数据库平台类型.
* @param obj 日期时间型变量.
* @return 数据库的日期时间型格式.
*/
	private static String formatDateTime(int dbms,Timestamp obj)
	{	switch(dbms)
		{	case ODBC  :  return "{ts '" + obj + "'}";
			case ORACLE:	return "to_date('" + FORMAT_DATETIME.format(obj) + "','yyyy-mm-dd hh24:mi:ss')";
			default 	 :	return "'" + obj + "'";
		}
	}
	

	
/**
* 生成对应的数据库日期类型的字符串格式（创建于 2003.03.21）.
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
创建于 2003.03.21.
</pre>
</DL>
* @param dbms 数据库平台类型.
* @param obj 日期型变量.
* @return 数据库的日期型格式.
*/
	private static String formatDate(int dbms,java.util.Date obj)
	{	switch(dbms)
		{	case ODBC  :  return "{ts '" + obj + "'}";
			case ORACLE:	return "to_date('" + FORMAT_DATETIME.format(obj) + "','yyyy-mm-dd hh24:mi:ss')";
			default 	 :	return "'" + obj + "'";
		}
	}
/**
* 生成对应的数据库时间类型的字符串格式（创建于 2003.03.21）.
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
创建于 2003.03.21.
</pre>
</DL>
* @param dbms 数据库平台类型.
* @param obj 时间型变量.
* @return 数据库的时间型格式.
*/
	private static String formatTime(int dbms,Time obj)
	{	switch(dbms)
		{	case ODBC  :  return "{ts '" + obj + "'}";
			case ORACLE:	return "to_date('" + FORMAT_DATETIME.format(obj) + "','hh24:mi:ss')";
			default 	 :	return "'" + obj + "'";
		}
	}
	
/**
* 生成对应的数据库VARCHAR类型的字符串格式（创建于 2003.03.21）.
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
创建于 2003.03.21.
</pre>
</DL>
* @param dbms 数据库平台类型.
* @param obj 字符串型变量.
* @return 数据库的varchar表达式.
*/
	private static String formatVarchar(int dbms,String obj)
	{	switch(dbms)
		{	case ORACLE:	return "'" + StringMethod.replaceAll(obj,"'","''") + "'";
			default 	 :	return "'" + obj + "'";
		}
	}
	/**
	 * 生成有记录范围的SQL（创建于 2003.03.10）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	根据原始查询SQL，生成带有记录数范围的SQL语句，目前只支持ORACLE.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.10.
	</pre>
	</DL>
* @param dbms 数据库类型.
* @param sqlSelect 原始的查询SQL.
* @param beginIndex 从第几个记录开始，范围为1..记录总数.
* @param rows >0 向后取几条记录，<0 向前取几条记录.
* @return 设定记录数范围的SQL.
*/
	public static String toRangeSQL(int dbms,String sqlSelect,int beginIndex,int rows)
	{	switch(dbms)
		{	case ORACLE: return toOracleRangeSQL(sqlSelect,beginIndex,rows);
			default: throw new UnsupportedOperationException("the dbms = '" + getDBMSName(dbms) + "' can't use toRangeSQL");
		}
	}
	

	/**
	 * 生成ORACLE的范围SQL语句表达式（创建于 2003.03.10）.
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
	创建于 2003.03.10.
	</pre>
	</DL>
* @param sqlSelect 原始查询SQL.
* @param beginIndex 从第几个记录开始，范围为1..记录总数.
* @param rows >0 向后取几条记录，<0 向前取几条记录.
* @return 设置查询范围的SQL语句.
*/
	private static String toOracleRangeSQL(String sqlSelect,int beginIndex,int rows)
	{	int li_min,li_max;
	
		li_min = Math.min(beginIndex,beginIndex + rows - 1);
		li_max = Math.max(beginIndex,beginIndex + rows - 1);
		
		return "SELECT * FROM (SELECT rownum AS prownum,pview.* FROM("
			+ sqlSelect	+ ") pview WHERE rownum<=" + li_max
			+ ") WHERE prownum>="	+ li_min; 
	}
	
	static String[] FUNCTIONS = {":substr(",":length("};
	static String[] FUNCTIONS_ORACLE = {"substr(","length("};
	static String[] FUNCTIONS_SYBSQLSERVER = {"substr(","length("};
	static String[] FUNCTIONS_MSSQLSERVER = {"substring(","len("};


	
/**
* 将含有伪代码的SQL语句转换成数据库的SQL语句（创建于 2003.03.18）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
伪代码主要指用于统一各数据库差异而设计的伪指令列表。
目前伪代码主要用于数据库内部函数，伪代码以：开始，以(结束.

以下为伪代码列表：
		<TABLE BORDER=2>
		<TR><TH>伪代码指令</TH><TH>ORACLE</TH><TH>SYBSQLSERVER</TH><TH>MSSQLSERVER</TH>
		<TR><TD>:substr</TD><TD>substr</TD><TD>substr</TD><TD>substring</TD>
		<TR><TD>:length</TD><TD>length</TD><TD>length</TD><TD>len</TD>
		</TABLE>
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
* @param dbms 数据库类型定义值.
* @param sql 含有伪指令的SQL语句.
* @return 转换后的SQL语句.
*/
public static String toRealSQL(int dbms,String sql)
	{	String[] functions;
		switch(dbms)
		{	case ORACLE:functions = FUNCTIONS_ORACLE;break;
			case SYBSQLSERVER:functions = FUNCTIONS_SYBSQLSERVER;break;
			case MSSQLSERVER:functions = FUNCTIONS_MSSQLSERVER;break;
			default: throw new UnsupportedOperationException("DBMS = " + dbms + " is not supported in toRealSQL(int,String)!");
		}
		
		return StringMethod.replaceAll(sql,FUNCTIONS,functions);
	}


}

