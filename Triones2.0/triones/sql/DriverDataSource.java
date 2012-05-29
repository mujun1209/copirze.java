package triones.sql;

import triones.util.StringMethod;
import triones.util.ArrayMethod;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.PrintWriter;

/**驱动数据源对象（创建于2003.10.06）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
将DriverManager和DataSource相结合，使之能以DataSource的方式提供数据连接。
目前支持ODBC、ORACLE、MS SQLSERVER、MYSQL、SYB SQLSERVER等数据库
</pre>
<DT><B>使用说明：</B><DD>
<pre>
略
</pre>
<DT><B>名词解释：</B><DD>
<pre>
略
</pre>
<DT><B>注意事项：</B><DD>
<pre>
需JDK1.3或更高版本支持;
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
*@author	吴勇庆
*@version 2003.10.06
 @see javax.sql.DataSource
*/
public class DriverDataSource //extends DBResource
	implements javax.sql.DataSource
{
	/**版本号（创建于 2003.10.06）.
<DL>
<DT><B>说明：</B><DD>
<pre>
VERSION = "2003.10.06"
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.10.06.
</pre>
</DL>
*/
	public static final String VERSION = "2003.10.06";

/**<img src="new2.gif" width="35" height="15" border="0">当前支持的数据库驱动程序（创建于2003.10.06）.
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
创建于 2003.10.06.
修改于 2003.10.08 - 新增Sybase Sqlserver.
修改于 2003.10.16 - 新增DB2.
修改于 2009.10.15 - 新增jtds SqlServer.
</pre>
</DL>
* @see #SUPPORTED_PROTOCOL
*/
	public static String[] SUPPORTED_DRIVER = {"sun.jdbc.odbc.JdbcOdbcDriver",
												"oracle.jdbc.driver.OracleDriver",
												"com.microsoft.jdbc.sqlserver.SQLServerDriver",
												"org.gjt.mm.mysql.Driver",
												"com.sybase.jdbc2.jdbc.SybDriver",
												"COM.ibm.db2.jdbc.net.DB2Driver",
												"org.postgresql.Driver",
												"net.sourceforge.jtds.jdbc.Driver"
											   };
/**<img src="new2.gif" width="35" height="15" border="0">与驱动程序对应的协议名称（创建于2003.10.06）.
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
创建于 2003.10.06.
修改于 2003.10.08 - 新增Sybase Sqlserver.
修改于 2003.10.16 - 新增DB2.
修改于 2009.10.15 - 新增jtds SqlServer.
</pre>
</DL>
* @see #SUPPORTED_DRIVER
*/
	public static String[] SUPPORTED_PROTOCOL = {"odbc",
												"oracle:thin",
												"microsoft:sqlserver",
												"mysql",
												"sybase:Tds",
												"db2",
												"postgresql",
												"jtds:sqlserver"
												};
	

/**
* 数据库路径（创建于 2003.10.06）.
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
创建于 2003.10.06.
</pre>
</DL>
*/
	private String _URL;
/**
* 登录用户（创建于 2003.10.06）.
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
创建于 2003.10.06.
</pre>
</DL>
*/
	private String _User;
/**
* 登录密码（创建于 2003.10.06）.
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
创建于 2003.10.06.
</pre>
</DL>
*/
	private String _Password;

	/**
	 * 构造方法（创建于2003.10.06）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	根据支持的数据库类型、数据库地址、登录用户、登录密码构造数据源.
	一般与getConnection()配合使用。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.10.06.
	</pre>
	</DL>	
* @param dbms 数据库类型.
* @param datasrc 数据库地址.
* @param user 登录用户.
* @param password 登录密码.
* @see #DriverDataSource(int,String)
	@see #DriverDataSource(String,String)
	@see #DriverDataSource(String,String,String,String)
*/
	public DriverDataSource(int dbms,String datasrc,String user,String password)
	{	_URL = toURL(dbms,datasrc);
		_User = user;
		_Password = password;
		try
		{	Class.forName(SUPPORTED_DRIVER[dbms]);		
		}
		catch(ClassNotFoundException e)
		{// will not happen
		}
	}	
	/**
	 * 构造方法（创建于2003.10.06）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	根据支持的数据库类型、数据库地址构造数据源.
	一般与getConnection(String,String)配合使用。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.10.06.
	</pre>
	</DL>
* @param dbms 数据库类型.
* @param datasrc 数据库地址.
* @see #DriverDataSource(int,String,String,String)
	@see #DriverDataSource(String,String)
	@see #DriverDataSource(String,String,String,String)
*/
	public DriverDataSource(int dbms,String datasrc)
	{	_URL = toURL(dbms,datasrc);
		try
		{	Class.forName(SUPPORTED_DRIVER[dbms]);		
		}
		catch(ClassNotFoundException e)
		{// will not happen
		}
	}
	

	/**
	 * 构造方法（创建于2003.10.06）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	根据支持的数据库驱动类、数据库的URL、登录用户、登录密码等信息构造数据源.
	一般与getConnection()配合使用。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.10.06.
	</pre>
	</DL>
* @param driverName 数据库驱动类名.
* @param url 数据库的URL.
* @param user 登录用户.
* @param password 登录密码.
* @exception ClassNotFoundException 指定的数据库驱动类不存在.
* @see #DriverDataSource(int,String,String,String)
	@see #DriverDataSource(int,String)
	@see #DriverDataSource(String,String)
*/
	public DriverDataSource(String driverName,String url,String user,String password)
		throws ClassNotFoundException
	{	Class.forName(driverName);
		_URL = url;
		_User = user;
		_Password = password;
	}
		/**
	 * 构造方法（创建于2003.10.06）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	根据支持的数据库驱动类、数据库的URL等信息构造数据源.
	一般与getConnection(String,String)配合使用。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.10.06.
	</pre>
	</DL>
* @param driverName 数据库驱动类名.
* @param url 数据库的URL.
* @exception ClassNotFoundException 指定的数据库驱动类不存在.
* @see #DriverDataSource(int,String,String,String)
	@see #DriverDataSource(int,String)
	@see #DriverDataSource(String,String,String,String)
*/
	public DriverDataSource(String driverName,String url)
		throws ClassNotFoundException
	{	Class.forName(driverName);
		_URL = url;
	}
	/**
	 * 创建一个新的数据库连接实例（创建于2003.10.06）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	根据默认配置连接数据库
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.10.06.
	</pre>
	</DL>
* @return 数据库连接实例.
* @exception SQLException 连接失败.
* @see #getConnection(String,String)
*/
	public Connection getConnection()
		throws SQLException
	{	if(StringMethod.isValid(_User))
			return DriverManager.getConnection(_URL,_User,_Password);
		else
			return DriverManager.getConnection(_URL);
	}
	

	/**
	 * 创建一个新的数据库连接实例（创建于2003.10.06）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	根据登录用户和密码连接数据库
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.10.06.
	</pre>
	</DL>
* @param user 登录用户.
* @param password 登录密码.
* @return 数据库连接实例.
* @exception SQLException 连接失败.
* @see #getConnection()
*/
	public Connection getConnection(String user,String password)
		throws SQLException
	{	return DriverManager.getConnection(_URL,user,password);
	}
	

	/**
	 * 获取当前设定的登录响应超时时间（创建于2003.10.06）.
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
	创建于 2003.10.06.
	</pre>
	</DL>
* @return 当前设定的登录响应超时时间（按秒）.
* @see #setLoginTimeout(int)
*/
	public int getLoginTimeout() 
	{	return DriverManager.getLoginTimeout();
	}
	

	/**
	 * 设置登录响应超时的时间（创建于2003.10.06）.
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
	创建于 2003.10.06.
	</pre>
	</DL>
* @param seconds 登录响应超时（按秒）.
* @see #getLoginTimeout()
*/
	public void setLoginTimeout(int seconds)
	{	DriverManager.setLoginTimeout(seconds);
	}
	

	/**
	 * 获取当前的日志输出对象（创建于2003.10.06）.
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
	创建于 2003.10.06.
	</pre>
	</DL>
* @return 日志输出对象.
* @see #setLogWriter(PrintWriter)
*/
	public PrintWriter getLogWriter()
	{	return DriverManager.getLogWriter();
	}
	

	/**
	 * 设置日志输出对象（创建于2003.10.06）.
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
	创建于 2003.10.06.
	</pre>
	</DL>
* @param out 日志输出对象.
* @see #getLogWriter()
*/
	public void setLogWriter(PrintWriter out)
	{	DriverManager.setLogWriter(out);
	}
	
	/**
	 * 根据支持的数据库类型和数据库地址生成数据库的URL（创建于2003.10.06）.
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
	创建于 2003.10.06.
	</pre>
	</DL>
* @param dbms 数据库类型.
* @param datasrc 数据库地址.
* @return 数据库的URL.
*/
	private static String toURL(int dbms,String datasrc)
	{	if(!DBMS.isSupportedDBMS(dbms)) throw new IllegalArgumentException("不支持的DBMS：" + dbms);
		return "jdbc:" + SUPPORTED_PROTOCOL[dbms] + ":" + datasrc;
	}
	

	
/**
* 判断指定的数据库驱动类是否有效（创建于 2003.10.11）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
即使是同一个数据库类型，也可能有多种数据库驱动类。为了避免出现开发人员错误地使用的驱动类
建议在应用DriverDataSource编程之前调用本方法验证驱动类的有效性。
驱动类名必须是全路径的.
false代表该数据驱动类还没有登记，某些特殊操作无法执行，如Transaction.setBlob(...).
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.10.11.
</pre>
</DL>
* @param driverName 数据库驱动类名.
* @return true代表有效，false代表无效.
* @see DBMS
*/
	public static boolean isValidDriver(String driverName)
	{	return (findDBMS(driverName)>=0);
	}
/**
* 根据数据库驱动类名查找其对应的数据库类型（创建于 2003.10.11）.
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
创建于 2003.10.11.
</pre>
</DL>
* @param driverName 数据库驱动类名.
* @return 数据库类型标识，－1代表该驱动类未被注册.
* @see DBMS
*/
	public static int findDBMS(String driverName)
	{	return ArrayMethod.findItem(SUPPORTED_DRIVER,driverName);
	}
}

