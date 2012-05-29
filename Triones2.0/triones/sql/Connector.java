package triones.sql;

import triones.util.StringMethod;
import triones.util.ArrayMethod;

import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**<img src="important.gif" width="35" height="25" border="0">数据库连接器（创建于 2003.03.19）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
&nbsp;&nbsp;&nbsp;&nbsp;企业级的数据库连接提供者，采用统一的数据库连接模式，支持直接用JDBC连接和
通过JNDI的连接池连接。非常适合分布式数据库应用中数据库连接驱动资源的共享。
</pre>
<DT><B>使用说明：</B><DD>
<pre>
    在建设与Web相关的数据库应用时经常会使用到JNDI连接池，但如果通过这种方式去做开发和
调试工作则非常麻烦，而用JDBC直接连接，则又需要额外编程，增加测试成本。

    采用Connector可以帮助你彻底摆脱这些烦恼，只需设定属性文件无需重复编程就可获得各种
各样的数据库连接，为软件实现跨数据库平台打下坚实基础。

假设有如下配置文件：e:\test.config

配置文件内容1（JDBC）：

DBMS=oracle
DataSource=@lp-svr:1521:orcl
User=lpsvr
Password=cws

配置文件内容2（JNDI_POOL）：

DBMS=weblogic
DataSource=t3://localhost:7001/MyDataSource

示例程序如下：

class Test
{

public static void main(String args[]) 
{	
	try
	{ Connector cnn = new Connector(new File("e:\\test.config"));
		
		//设定数据库类型,对于第一种配置方案可以忽略本操作
		cnn.setDBMS(DBMS.ORACLE);
		
    //获取数据库连接资源
		Transaction SQLCA = cnn.getTransaction();
		
		//打印当前时间
		System.out.println(SQLCA.getCompute("sysdate"));
		
		//释放数据库连接资源
		SQLCA.close();					
	}
	catch(Exception e)
	{ System.out.println(e);
	}
	
}

结果显示：
	
	2002-01-22 15:21:35.0
	
</pre>
<DT><B>注意事项：</B><DD>
<pre>
与数据库资源相关的配置属性（大小写敏感）定义如下:

<TABLE BORDER=2>
<TR><TH>属性</TH><TH>描述</TH><TH>JDBC示例</TH><TH>JNDI POOL示例</TH>
<TR><TD>DBMS</TD><TD>连接方式（参见CONNECT_TYPE的说明）</TD><TD>oracle</TD><TD>weblogic或weblogic/oracle</TD>
<TR><TD>DataSource</TD><TD>提供数据库资源的地址（在JDBC中代表数据库地址;在JNDI_POOL中为: Context.PROVIDER_URL/DataSource的JNDI名称）</TD><TD>@lp-svr:1521:orcl</TD><TD>t3://triones:80/MyDataSource</TD>
<TR><TD>User</TD><TD>登录用户名</TD><TD>可忽略</TD><TD>可忽略</TD>
<TR><TD>Password</TD><TD>登录口令</TD><TD>可忽略</TD><TD>可忽略</TD>
<TR><TD>AutoCommit</TD><TD>数据库事务自动提交模式(true/false,默认为原始值)</TD><TD>可忽略</TD><TD>可忽略</TD>
</TABLE>

需JDK1.3（或更高版本）支持;
JNDI命名需避免出现'/';
$可作为默认的Provider URL;
</pre>
<DT><B>展望未来：</B><DD>
<pre>
考虑提供给开发人员自定义数据库连接参数的接口。
</pre>
<DT><B>联系方式：</B><DD>
<pre>
 email：wyq@triok.com 
 电  话:021-68672222-2013
</pre>
</DL>
*@author	吴勇庆
*@version 2003.03.11
*@see DBMS
*/
public class Connector //extends DBResource
{	
/**版本号（创建于 2003.03.19）.
<DL>
<DT><B>说明：</B><DD>
<pre>
VERSION = "2003.03.11"
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.19.
</pre>
</DL>
*/
	public static final String VERSION = "2003.01.04";


/**
* 数据库连接方式名称（创建于 2003.03.19）.
<DL>
<DT><B>说明：</B><DD>
<pre>
用作以下常量的显示信息：
CONNECT_JDBC 对应于"JDBC"
CONNECT_JNDI_POOL 对应于"JNDI_POOL"
CONNECT_TYPE = {"JDBC","JNDI_POOL"}
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.19.
</pre>
</DL>
* @see #CONNECT_JDBC
	@see #CONNECT_JNDI_POOL
*/
	public static String[] CONNECT_TYPE = {"JDBC","JNDI_POOL"};	
	
/**
* JDBC数据库连接方式（创建于 2003.03.19）.
<DL>
<DT><B>说明：</B><DD>
<pre>
用于判断当前与连接数据库的方式是否为JDBC
CONNECT_JDBC = 0
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.19.
</pre>
</DL>
* @see #CONNECT_JNDI_POOL
	@see #getConnectType()
*/
	public static final int CONNECT_JDBC = 0;
	
/**
* JNDI POOL数据库连接方式（创建于 2003.03.19）.
<DL>
<DT><B>说明：</B><DD>
<pre>
用于判断当前与连接数据库的方式是否为JNDI连接池
CONNECT_JNDI_POOL = 1
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.19.
</pre>
</DL>
* @see #CONNECT_JDBC
	@see #getConnectType()
*/
	public static final int CONNECT_JNDI_POOL = 1;

/**
* 目录基础结构参数列表（创建于 2003.03.19）.
<DL>
<DT><B>说明：</B><DD>
<pre>
&nbsp;&nbsp;&nbsp;&nbsp;与目录基础结构(JNDI)类型对应的环境初始化参数,用于设定与SUPPORTED_JNDI对应
的INITIAL_CONTEXT_FACTORY
SUPPORTED_FACTORY = {"weblogic.jndi.WLInitialContextFactory"}

有关INITIAL_CONTEXT_FACTORY的知识请参见
	javax.naming.Context.INITIAL_CONTEXT_FACTORY
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.19.
</pre>
</DL>
* @see #SUPPORTED_JNDI
*/
	public static String[] SUPPORTED_FACTORY = {"weblogic.jndi.WLInitialContextFactory",
																							"com.ibm.websphere.naming.WsnInitialContextFactory"};
	public static String[] DEFAULT_PROVIDER_URL = {"t3://localhost:7001",
																									"corbaloc:rir:/NameServiceServerRoot"};
/**
* 支持的目录基础结构列表（创建于 2003.03.19）.
<DL>
<DT><B>说明：</B><DD>
<pre>
&nbsp;&nbsp;&nbsp;&nbsp;用于存放当前支持的(JNDI)类型名,当采用JNDI连接池方式连接数据库时,设定配置属性
DBMS的值.
SUPPORTED_JNDI = {"weblogic"}
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.19.
</pre>
</DL>
* @see #SUPPORTED_FACTORY
*/
	public static String[] SUPPORTED_JNDI = {"weblogic","websphere"};
	
/**
* weblogic的JNDI访问模式（创建于 2003.03.19）.
<DL>
<DT><B>说明：</B><DD>
<pre>
JNDI_WEBLOGIC = 0

目前经过测试的是WEBLOGIC6.0
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.19.
</pre>
</DL>
* @see #SUPPORTED_JNDI
  @see #SUPPORTED_FACTORY
	@see #JNDI_WEBSPHERE
*/
	public static int JNDI_WEBLOGIC = 0;	
	
	
/**
* WEBSPHERE的JNDI访问模式（创建于 2003.07.25）.
<DL>
<DT><B>说明：</B><DD>
<pre>
JNDI_WEBSPHERE = 1

目前经过测试的是WEBSPHERE5.0
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.07.25.
</pre>
</DL>
	@see #SUPPORTED_JNDI
  @see #SUPPORTED_FACTORY
* @see #JNDI_WEBLOGIC
*/
	public static int JNDI_WEBSPHERE = 1;
	
/**
* 自动提交模式（创建于 2003.03.19）.
<DL>
<DT><B>说明：</B><DD>
<pre>
默认值为false,会根据配置属性AutoCommit的值变化.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.19.
</pre>
</DL>
*/
	private boolean _AutoCommit = false;
	
/**
* 忽略AutoCommit的设置（创建于 2003.03.11）.
<DL>
<DT><B>说明：</B><DD>
<pre>
在应用中发现，经常需要不设定Connection的AutoCommit属性.
调用setAutoCommit(boolean)方法时,本属性为false.
默认值为true
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
* @see #ignoreAutoCommit()
*/
	
	private boolean _IgnoreAutoCommit = true;
	

	
/**
* <img src="important.gif" width="35" height="25" border="0">忽略AutoCommit属性（创建于 2003.03.11）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
采用本方法可以忽略通过setAutoCommit(boolean)设置的默认AutoCommit属性值,而采用连接资源本来的值.
调用setAutoCommit(boolean)会消除本方法所起的作用.
调用本方法不会改变通过setAutoCommit(boolean)设置的默认AutoCommit属性值.
</pre>
<DT><B>示例：</B><DD>
<pre>

...

Connector db = new Connector(new File("E:\\吴勇庆\\01_创作苑\\01_重用构件\\Java\\oracle.ini"));
System.out.println("初始状态下的autoCommit = " + db.getAutoCommit());
System.out.println("Transaction autoCommit = " + db.getTransaction().isAutoCommit());
System.out.println("Connection autoCommit = " + db.getConnection().getAutoCommit());

db.setAutoCommit(false);
System.out.println("设定当前默认的autoCommit = " + db.getAutoCommit());
System.out.println("Transaction autoCommit = " + db.getTransaction().isAutoCommit());
System.out.println("Connection autoCommit = " + db.getConnection().getAutoCommit());			

db.ignoreAutoCommit();
System.out.println("忽略当前默认的autoCommit = " + db.getAutoCommit());
System.out.println("Transaction autoCommit = " + db.getTransaction().isAutoCommit());
System.out.println("Connection autoCommit = " + db.getConnection().getAutoCommit());			
		
...

执行结果如下：
-------------------------------------------

初始状态下的autoCommit = false
Transaction autoCommit = true
Connection autoCommit = true
设定当前默认的autoCommit = false
Transaction autoCommit = false
Connection autoCommit = false
忽略当前默认的autoCommit = false
Transaction autoCommit = true
Connection autoCommit = true

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.11.
</pre>
</DL>
* @see #setAutoCommit(boolean)
	@see #getAutoCommit()
*/
	public void ignoreAutoCommit()
	{	_IgnoreAutoCommit = true;
	}

/**
* 来自JNDI或DriveManager的数据源（创建于 2003.03.19）.
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
创建于 2003.03.19.
</pre>
</DL>
* @see #getAutoCommit()
	@see #setAutoCommit(boolean)
*/
	private	DataSource _DataSource;

/**
* 当前连接数据库的方式（创建于 2003.03.19）.
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
创建于 2003.03.24.
</pre>
</DL>
* @see #CONNECT_JDBC
	@see #CONNECT_JNDI_POOL
*/
	private int _ConnectType;

/**
* 当前默认的数据库类型（创建于 2003.03.19）.
<DL>
<DT><B>说明：</B><DD>
<pre>
当采用JDBC方式连接数据库时,与配置属性DBMS的值相关.
取值范围参见 DBMS.<数据库类型>
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.19.
</pre>
</DL>
* @see DBMS#ORACLE
*/	
	private int _DBMS;

/**
 * <img src="important.gif" width="35" height="25" border="0">设置默认数据库类型（创建于 2003.03.19）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
此默认数据库类型将在使用方法getConnection()和getTransaction()时有效。
采用JNDI模式连接数据库时，DBMS默认为ODBC，可能导致某些方法不可用，可采用本方法强制设定DBMS类型。
</pre>
<DT><B>示例：</B><DD>
<pre>

...

Connector db = new Connector(new File("E:\\吴勇庆\\01_创作苑\\01_重用构件\\Java\\oracle.ini"));
System.out.println("初始状态下的 DBMS = " + DBMS.getDBMSName(db.getDBMS()));
System.out.println("Transaction DBMS = " + DBMS.getDBMSName(db.getTransaction().getDBMS()));
db.setDBMS(DBMS.ODBC);
System.out.println("设定默认的 DBMS = " + DBMS.getDBMSName(db.getDBMS()));
System.out.println("Transaction DBMS = " + DBMS.getDBMSName(db.getTransaction().getDBMS()));

Transaction sqlca = db.getTransaction(DBMS.ORACLE);
System.out.println("sqlca DBMS = " + DBMS.getDBMSName(sqlca.getDBMS()));

...

执行结果如下：
-------------------------------------------

初始状态下的 DBMS = oracle
Transaction DBMS = oracle
设定默认的 DBMS = odbc
Transaction DBMS = odbc
sqlca DBMS = oracle

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.19.
</pre>
</DL>
* @param dbms 数据库类型.
* @see #getDBMS()
	@throws IllegalArgumentException 不合法的数据库类型.
*/
	
	public void setDBMS(int dbms)
	{	if(!DBMS.isSupportedDBMS(dbms))
 			throw new IllegalArgumentException("数据库类型: " + dbms);		
		_DBMS = dbms;
	}	
	
/**
 * 取默认数据库类型（创建于 2003.03.19）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
此默认数据库类型将在使用方法getTransaction()时有效。
</pre>
<DT><B>示例：</B><DD>
<pre>

...

Connector db = new Connector(new File("E:\\吴勇庆\\01_创作苑\\01_重用构件\\Java\\oracle.ini"));
System.out.println("初始状态下的 DBMS = " + DBMS.getDBMSName(db.getDBMS()));

...

执行结果如下：
-------------------------------------------

初始状态下的 DBMS = oracle

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.19.
</pre>
</DL>
* @return dbms 数据库类型.
* @see #setDBMS(int)
*/
	public int getDBMS()
	{	return _DBMS;
	}
	

/**
 * <img src="important.gif" width="35" height="25" border="0">设置默认的事务提交模式（创建于 2003.03.27）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
&nbsp;&nbsp;&nbsp;&nbsp;通过本方法设置的提交模式将影响用getConnection([int])和getTransaction([int])
方法得到的数据库连接资源的AutoCommit属性.
</pre>
<DT><B>示例：</B><DD>
<pre>

...

Connector db = new Connector(new File("E:\\吴勇庆\\01_创作苑\\01_重用构件\\Java\\oracle.ini"));
System.out.println("初始状态下的autoCommit = " + db.getAutoCommit());
db.setAutoCommit(true);
System.out.println("设定当前默认的autoCommit = " + db.getAutoCommit());

...

执行结果如下：
-------------------------------------------

初始状态下的autoCommit = false
设定当前默认的autoCommit = true

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.27.
</pre>
</DL>
* @param autoCommit true代表事务自动提交，反之为false.
* @see #getAutoCommit()
	@see #ignoreAutoCommit()
*/
	public void setAutoCommit(boolean autoCommit)
	{	_AutoCommit = autoCommit;
		_IgnoreAutoCommit = false;
	}
/**
 * 取默认的事务提交模式（创建于 2003.03.27）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
取当前默认的数据库事务自动提交模式,构造时默认为true
</pre>
<DT><B>示例：</B><DD>
<pre>

...

Connector db = new Connector(new File("E:\\吴勇庆\\01_创作苑\\01_重用构件\\Java\\oracle.ini"));
System.out.println("初始状态下的autoCommit = " + db.getAutoCommit());
		
...

执行结果如下：
-------------------------------------------

初始状态下的autoCommit = false

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.27.
</pre>
</DL>
* @return true代表事务自动提交，反之为false.
* @see #setAutoCommit(boolean)
	@see #ignoreAutoCommit()
*/
	public boolean getAutoCommit()
	{	return _AutoCommit;
	}


	/**
	 * 是否为当前支持的的目录基础结构产品名（创建于 2003.03.19）.
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
	创建于 2003.03.19.
	</pre>
	</DL>
* @param jndi JNDI类型.
* @return 是否有效.
*/
	static boolean isSupportedJNDI(int jndi)
	{	return (jndi >= 0 && jndi < SUPPORTED_JNDI.length);
	}


	
/**
* 查找JNDI类型（创建于 2003.03.11）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
返回值-1代表该JNDI尚未登记.
已登记的JNDI参见SUPPORTED_JNDI.
查找时忽略大小写.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.03.
</pre>
</DL>
* @param jndiName JNDI类型名.
* @return JNDI类型枚举值.
*/
	static int findJNDI(String jndiName)
	{	return ArrayMethod.findItemIgnoreCase(SUPPORTED_JNDI,jndiName);
	}
	/**
	 * 是否为合法的数据库连接类型（创建于 2003.03.19）.
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
	创建于 2003.03.19.
	</pre>
	</DL>
* @param connectType 连接类型.
* @return 是否有效.
*/
	static boolean isValidConnectType(int connectType)
	{	if(connectType<CONNECT_JDBC||connectType>CONNECT_JNDI_POOL) return false;
		return true;
	}
	

	/**
	 * 根据配置属性构造对象（创建于 2003.03.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	关于属性的定义请参见类描述中的《使用说明》部分。
	至少包括DBMS和DataSource属性。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>	
	//设定配置属性
	Properties config = new Properties();
	config.setProperty("DBMS","oracle");
	config.setProperty("DataSource","@172.16.1.101:1521:osie");
	config.setProperty("User","oneservice");
	config.setProperty("Password","oneservice");
	
	//构造Connector
	Connector cnn = new Connector(config);
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.19.
	修改于 2003.03.27 - 增加对AutoCommit的支持.
	</pre>
	</DL>
* @param initParm 初始化属性值.
* @exception ClassNotFoundException 采用JDBC连接失败.
* @exception NamingException 采用JNDI_POOL连接失败.
	@exception IllegalArgumentException 不支持的DBMS参数定义.
	@see #Connector(File)
*/
	public Connector(Properties initParm)
		throws ClassNotFoundException,NamingException
	{	load(initParm);
	}
	
	/**
	 * <img src="important.gif" width="35" height="25" border="0">根据属性配置文件构造对象（创建于 2003.03.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	参见《使用说明》部分。
	至少包括DBMS和DataSource关键字。
	注意：在配置定义中不要有空格出现，否则会导致错误。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//JNDI POOL举例1
	DBMS=weblogic
	DataSource=t3://localhost:7001/MyDataSource
		
	//JNDI POOL举例2
	DBMS=websphere
	DataSource=corbaloc:rir:/NameServiceServerRoot/MyDataSource
		
	//JNDI POOL举例3
	DBMS=weblogic/oracle
	DataSource=t3://localhost:7001/MyDataSource
	
	//ORACLE举例
	DBMS=oracle
	DataSource=@172.16.1.101:1521:osie
	User=oneservice
	Password=oneservice
	
	//MS SQLSERVER举例
	DBMS=mssqlserver
	DataSource=//wonders:1433;DatabaseName=osie
	User=oneservice
	Password=oneservice
	
	//MY SQL举例
	DBMS=mysql
	DataSource=//triones/osie
	User=oneservice
	Password=oneservice
	
	//SYB SQLSERVER举例
	DBMS=sybsqlserver
	DataSource=lp-svr:5000/osie
	User=oneservice
	Password=oneservice
	
	//IBM DB2举例
	DBMS=db2
	DataSource=//lp-svr:6789/osie
	User=oneservice
	Password=oneservice
	
	//ODBC 举例
	先在ODBC中配置DSN为osie_db
	
	DBMS=odbc
	DataSource=osie_db
	User=osieuser
	Password=osie
	
	或者
	
	DBMS=odbc
	DataSource=osie_db;UID=osieuser;PWD=osie

	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.19.
	修改于 2003.03.27 - 增加对AutoCommit的支持.
	</pre>
	</DL>
* @param cfg 属性配置文件.
	@exception IllegalArgumentException 不支持的DBMS参数定义.
	@see #Connector(Properties)
*/
	public Connector(File cfg)
	{	try
		{	Properties lp = new Properties();
			FileInputStream lFIStream = new FileInputStream(cfg);
	    lp.load(lFIStream);
	    lFIStream.close();
	    load(lp);
		}
		catch(ClassNotFoundException e)
		{	System.out.println("JDBC Driver is not found");
		}
		catch(NamingException e)
		{	System.out.println("JNDI is not found");
		}
		catch(FileNotFoundException e)
		{	System.out.println(cfg + " is not found");
		}
		catch(IOException e)
		{	System.out.println(cfg + " can't be read");
		}
	}
	/**
	 * 根据属性设置构造对象（创建于 2003.03.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	参见《使用说明》部分。
	至少包括DBMS和DataSource关键字。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.19.
	修改于 2003.03.27 - 增加对AutoCommit的支持.
	</pre>
	</DL>
* @param initParm 初始化属性值.
* @exception ClassNotFoundException 采用JDBC连接失败.
* @exception NamingException 采用JNDI_POOL连接失败.
	@exception IllegalArgumentException 不支持的DBMS参数定义.
	@see #Connector(File)
	@see #Connector(Properties)
*/
	private void load(Properties initParm)
		throws ClassNotFoundException,NamingException
	{	String ls_dbms = initParm.getProperty("DBMS");
		String ls_datasource = initParm.getProperty("DataSource");
		String ls_user = initParm.getProperty("User");
		String ls_password = initParm.getProperty("Password");
		String ls_autocommit = initParm.getProperty("AutoCommit");
		
		int li_dbms;
		li_dbms = DBMS.findDBMS(ls_dbms);
		if(li_dbms>=0) //JDBC模式
		{	_ConnectType = CONNECT_JDBC;
			_DBMS = li_dbms;
		}			
		else//JNDI模式
		{	String ls_jndi = StringMethod.getChild(ls_dbms,1,'/');
			int li_jndi = findJNDI(ls_jndi);
			
			if(li_jndi>=0) 
			{	_ConnectType = CONNECT_JNDI_POOL;
				//设置DBMS
				ls_dbms = StringMethod.getChild(ls_dbms,2,'/');
				li_dbms = DBMS.findDBMS(ls_dbms);
				if(li_dbms>=0)
				{	_DBMS = li_dbms;
				}
			} 
			else
				throw new IllegalArgumentException("不支持的DBMS参数定义: " + ls_dbms);
		}
		
		//是否忽略AutoCommit
		_IgnoreAutoCommit = !StringMethod.isValid(ls_autocommit);
		
		if(!_IgnoreAutoCommit)
			_AutoCommit = ls_autocommit.equalsIgnoreCase("true");
			
		//初始化DataSource
		switch(_ConnectType)
		{	case CONNECT_JDBC:	
													_DataSource = new DriverDataSource(li_dbms,ls_datasource,ls_user,ls_password);
													break;															
			case CONNECT_JNDI_POOL:	
													_DataSource = (DataSource)getJNDIObject(li_dbms,ls_datasource);
													break;
		}
	}
	/**
	 * <img src="important.gif" width="35" height="25" border="0">JNDI POOL模式下的构造方法（创建于 2003.03.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//连接lp-svr服务器上的Weblogic提供的JNDI为FBIDataSource提供的连接池
	Connector cnn = new Connector("FBIDataSource","t3://lp-svr:7001","weblogic.jndi.WLInitialContextFactory");
	
	//采用本方法需显示设定数据库平台类型
	cnn.setDBMS(DBMS.ORACLE);
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.19.
	</pre>
	</DL>
* @param jndiName 连接池对象在JNDI服务中注册的名称.
* @param provider_url 提供JNDI服务的路径.
* @param initial_context_factory JNDI初始化目录规范.
* @exception NamingException 在JNDI服务中未找到注册对象.
* @see #Connector(int,String)
*/
	public Connector(String jndiName,String provider_url,String initial_context_factory)
		throws NamingException
	{	_ConnectType = CONNECT_JNDI_POOL;
		_DataSource = (DataSource)getJNDIObject(jndiName,provider_url,initial_context_factory);		
	}
	/**
	 * JNDI POOL模式下的构造方法（创建于 2003.03.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	连接模式为CONNECT_JNDI_POOL.
	采用已认证的JNDI服务类型。
	url的格式为：[JNDI服务路径]/[对象的JNDI注册名]
	注意：对象的JNDI名中不可包含: /
	</pre>
	<DT><B>示例：</B><DD>
	<pre>	
	//连接lp-svr服务器上的Weblogic提供的JNDI为FBIDataSource提供的连接池
	Connector cnn = new Connector(Connector.JNDI_WEBLOGIC,"t3://lp-svr:7001/FBIDataSource");
	//连接lp-svr服务器上的Websphere提供的JNDI为FBIDataSource提供的连接池
	Connector cnn = new Connector(Connector.JNDI_WEBLOGIC,"corbaloc:rir:/NameServiceServerRoot/FBIDataSource"
	//采用本方法需显示设定数据库平台类型
	cnn.setDBMS(DBMS.ORACLE);
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.19.
	</pre>
	</DL>
* @param jndi 已认证的JNDI服务类型.
* @param url 对象的JNDI注册路径.
* @exception NamingException 在JNDI服务中未找到注册对象.
* @see #Connector(String,String,String)
	@see #JNDI_WEBLOGIC
*/
	public Connector(int jndi,String url)
		throws NamingException
	{	_DataSource = (DataSource)getJNDIObject(jndi,url);
		_ConnectType = CONNECT_JNDI_POOL;
	}
	
	/**
	 * <img src="important.gif" width="35" height="25" border="0">JDBC模式下的构造方法（创建于 2003.03.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//连接lp-svr服务器上的ORACLE数据库
	Connector cnn = new Connector("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@lp-svr:1521:orcl","oneservice","oneservice");
	
	//采用本方法需显示设定数据库平台类型
	cnn.setDBMS(DBMS.ORACLE);
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.19.
	</pre>
	</DL>
	*@param driverName 数据库驱动程序类.
	*@param url 数据库路径.
	*@param user 登录用户.
	 @param password 登录密码
	 @see #Connector(int,String,String,String)
	*/
	public Connector(String driverName,String url,String user,String password)	
		throws ClassNotFoundException
	{	_ConnectType = CONNECT_JDBC;
		_DataSource = new DriverDataSource(driverName,url,user,password);
	}

	/**
	 * JDBC模式下的构造方法（创建于 2003.03.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	连接类型为CONNECT_JDBC.
	采用已认证的数据库驱动程序类型
	目前支持的数据库类型参见DBMS.SUPPORTED_DBMS的相关说明。
	</pre>
	<DT><B>示例 1</B><DD>
	<pre>
	
	...
	
	//用JDBC-ODBC桥连接数据库
	Connector cnn = new Connector(DBMS.ODBC,"javademo;UID=DBA;PWD=sql","","");
	
	...
	
	</pre>
	<DT><B>示例 2</B><DD>
	<pre>
	
	...
	
	//用JDBC连接oracle数据库
	Connector cnn = new Connector(DBMS.ORACLE,"@lp-svr:1521:orcl","cwstest","test");
	
	...
	
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.19.
	</pre>
	</DL>
	*@param dbms 数据库类型(协议).
	*@param datasrc 数据源路径,注，与URL不同，是URL最后部分.
	*@param user 登录用户.
	 @param password 登录密码
	 @see #Connector(String,String,String,String)
*/

	public Connector(int dbms,String datasrc,String user,String password)	
	{	_ConnectType = CONNECT_JDBC;
		_DataSource = new DriverDataSource(dbms,datasrc,user,password);
	}
	
/**
* 获取一个新的数据传输对象（创建于 2003.03.11）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
数据库类型参见getDBMS()
</pre>
<DT><B>示例：</B><DD>
<pre>

...

Connector DB = new Connector(new File("E:\\吴勇庆\\01_创作苑\\01_重用构件\\Java\\oracle.ini"));
Transaction sqlca = DB.getTransaction();	
DataStore dw= new DataStore("select u_id,U_NAME,u_email from fbi_user","U_ID");
dw.setTrans(sqlca);

...

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.03.
</pre>
</DL>
* @return 数据库传输对象.
	@exception SQLException 数据库连接失败。
* @see #getTransaction(String,String)
	@see Transaction
*/
	//public Transaction getTransaction()
	//	throws SQLException
	//{	DBTrans sqlca = new DBTrans(getConnection(),_DBMS);	
	//	sqlca.goPrivateConnection(true);
	//	return sqlca;
	//}
/**
* 获取一个新的数据传输对象（创建于 2003.03.11）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
数据库类型参见getDBMS()
</pre>
<DT><B>示例：</B><DD>
<pre>

...

Connector DB = new Connector(new File("E:\\吴勇庆\\01_创作苑\\01_重用构件\\Java\\oracle.ini"));
Transaction sqlca = DB.getTransaction("oneservice","oneservice");	
DataStore dw= new DataStore("select u_id,U_NAME,u_email from fbi_user","U_ID");
dw.setTrans(sqlca);

...

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.03.
</pre>
</DL>
* @param user 数据库登录用户.
* @param password 登录密码.
* @return 数据库传输对象.
	@exception SQLException 数据库连接失败。
* @see #getTransaction()
	@see Transaction
*/
	//public Transaction getTransaction(String user,String password)
	//	throws SQLException
	//{	DBTrans sqlca = new DBTrans(getConnection(user,password),_DBMS);	
	//	sqlca.goPrivateConnection(true);
	//	return sqlca;		
	//}

	/**
	 * <img src="important.gif" width="35" height="25" border="0">获取一个数据库连接实例（创建于 2003.03.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	常用于采用Connector(Properties)构造的Connector.
	主要针对所有的连接参数都齐备的情况。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	
	...
	
	//初始化数据库连接参数
	Connector cnn = new Connector(Connector.JNDI_WEBLOGIC,"t3://lp-svr:7001/FBIDataSource");
	//创建一个数据库连接实例
	Connection connection = cnn.getConnection();
	
	...
	
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.19.
	</pre>
	</DL>
* @return 数据库连接实例.
* @exception SQLException 连接失败.
	@see #getConnection(String,String)
	@see java.sql.Connection
*/
	public Connection getConnection()
		throws SQLException
	{ Connection lcnn = _DataSource.getConnection();
		if(!_IgnoreAutoCommit) lcnn.setAutoCommit(_AutoCommit);
		return lcnn;
	}
	/**
	 * 获取一个数据库连接实例（创建于 2003.03.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	常用于不明确指定user和password的Connector。
	主要针对同一个数据库的不同登录用户。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	
	...
	
	//初始化数据库连接参数
	Connector cnn = new Connector(DBMS.ORACLE,"@lp-svr:1521:orcl","","");
	//创建一个数据库连接实例
	Connection connection1 = cnn.getConnection("oneservice","oneservice");
	//创建另一个数据库连接实例
	Connection connection2 = cnn.getConnection("cwstest","test");
	
	...
	
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.19.
	</pre>
	</DL>
* @param user 数据库登录用户.
* @param password 登录密码.
* @return 数据库连接实例.
* @exception SQLException 数据库连接失败.
* @see #getConnection()
	@see java.sql.Connection
*/
	public Connection getConnection(String user,String password)
		throws SQLException
	{	Connection lcnn = _DataSource.getConnection(user,password);
		if(!_IgnoreAutoCommit) lcnn.setAutoCommit(_AutoCommit);
		return lcnn;
	}
	

	/**
	 * 获取当前数据库连接方式（创建于 2003.03.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	用于判断当前数据库连接方式为JDBC还是JNDI POOL.
	</pre>
	<DT><B>示例 1</B><DD>
	<pre>
	
	...
	
	//初始化数据库连接参数
	Connector cnn = new Connector(DBMS.ORACLE,"@lp-svr:1521:orcl","lpsvr","cws");
	//type的值等于CONNECT_JDBC
	int type = cnn.getConnectType();
	
	...
	
	</pre>
	<DT><B>示例 2</B><DD>
	<pre>
	
	...
	
	//初始化数据库连接参数
	Connector cnn = new Connector(Connector.JNDI_WEBLOGIC,"t3://lp-svr:7001/FBIDataSource");
	//type的值等于CONNECT_JNDI_POOL
	int type = cnn.getConnectType();
	
	...
	
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.19.
	</pre>
	</DL>
* @return 当前数据库的连接模式.
* @see #CONNECT_JDBC
	@see #CONNECT_JNDI_POOL
*/
	public int getConnectType()
	{	return _ConnectType;
	}
	
	
	/**
	 * 根据JNDI名称、JNDI服务路径、JNDI初始化目录结构引用对象（创建于 2003.03.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//获取lp-svr服务器上的Weblogic提供的JNDI为FBIDataSource提供的连接池
	DataSource src = (DataSource)getJNDIObject("FBIDataSource","t3://lp-svr:7001","weblogic.jndi.WLInitialContextFactory");
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.27.
	</pre>
	</DL>
* @param jndiName JNDI名称.
* @param provider_url 提供JNDI服务的路径.
* @param initial_context_factory 采用的JNDI初始化目录结构.
* @return 对应于JNDI名称的对象实例.
* @exception NamingException JNDI对应的对象实例不存在.
* @see #getJNDIObject(int,String)
*/
	static Object getJNDIObject(String jndiName,String provider_url,String initial_context_factory)
		throws NamingException
	{	Properties lp = new Properties();
		lp.setProperty(Context.INITIAL_CONTEXT_FACTORY,initial_context_factory);
		lp.setProperty(Context.PROVIDER_URL,provider_url);
		Context lContext = new InitialContext(lp);
		
		return lContext.lookup(jndiName);
	}
	

	/**
	 * 根据已认证的JNDI类型、对象的JNDI资源路径引用对象（创建于 2003.03.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	目前支持Weblogic和WebSphere
	url中$代表使用默认的Provider URL
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	
	...
	
	//连接lp-svr服务器上的Weblogic提供的JNDI为FBIDataSource提供的连接池
	DataSource src = (DataSource)getJNDIObject(JNDI_WEBLOGIC,"t3://lp-svr:7001/FBIDataSource");
	
	...
	
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.19.
	</pre>
	</DL>
* @param jndi JNDI类型.
* @param url 资源路径.
* @return 对应资源路径的对象实例.
* @exception NamingException JNDI对应的对象实例不存在.
* @see #getJNDIObject(String,String,String)
*/
	static Object getJNDIObject(int jndi,String url)
		throws NamingException
	{	if(!isSupportedJNDI(jndi)) throw new IllegalArgumentException("不支持的JNDI类型: " + jndi);
		
		url = StringMethod.replace(url,"$",DEFAULT_PROVIDER_URL[jndi]);
		
		int li_pos = url.lastIndexOf('/');
		String ls_jndiName = url.substring(li_pos + 1);
	 	String ls_provider_url = url.substring(0,li_pos);	
		
		return getJNDIObject(ls_jndiName,ls_provider_url,SUPPORTED_FACTORY[jndi]);
	}
	

	/**
	 * 转换成数据源（创建于 2003.03.03）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	可以利用Connector的特性转换成提供数据访问的DataSource.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	
	...
	
	//初始化数据库连接参数
	Connector cnn = new Connector(DBMS.ORACLE,"@lp-svr:1521:orcl","lpsvr","cws");
	//作为DataSource使用
	DataSource src = cnn.asDataSource();
	
	...
	
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.03.
	</pre>
	</DL>
* @return 数据源.
	@see javax.sql.DataSource
*/
	
	public DataSource asDataSource()
	{	return _DataSource;
	}
	

	
/**
* 根据Connection获取一个新的数据传输对象（创建于 2003.08.14）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
数据库类型参数值：DBMS.XXXX
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.08.14.
</pre>
</DL>
* @param cnn 数据库连接实例.
* @param goPrivate 私有化Connection，true代表Transaction关闭时同时释放Connection,false代表不同时释放.
* @param dbms 指定数据传输对象的数据库类型.
* @return 数据库传输对象实例.
* @see #getTransaction(Connection,boolean,int)
	@see DBMS
*/
	//public static Transaction getTransaction(Connection cnn,boolean goPrivate,int dbms)
	//{	DBTrans sqlca = new DBTrans(cnn,dbms);	
	//	sqlca.goPrivateConnection(goPrivate);
	//	return sqlca;
	//}
	

	
/**
* 根据Connection获取一个新的数据传输对象（创建于 2003.08.14）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
数据库传输对象的数据库类型根据Connection属性动态确定，如果无法判断，则设为DBMS.ODBC
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.08.14.
</pre>
</DL>
* @param cnn 数据库连接实例.
* @param goPrivate 私有化Connection，true代表Transaction关闭时同时释放Connection,false代表不同时释放.
* @return 数据库传输对象实例.
* @exception SQLException 数据库错误.
* @see #getTransaction(Connection,boolean,int)
*/
	//public static Transaction getTransaction(Connection cnn,boolean goPrivate)
	//	throws SQLException
	//{	DBTrans sqlca = new DBTrans(cnn);	
	//	sqlca.goPrivateConnection(goPrivate);
	//	return sqlca;
	//}
}


                       /* \\|///
                       \\  - -  //
                        (  @ @  )
**********************oOOo-(_)-oOOo************
*                                             *
*  Publish Date: 2003.10.13   Wu yongqing    *
*                                          *
*  We work because we wake               *
*                             Oooo        *
************************oooO**(   )*********
                       (   )   ) /
                        \ (   (_/
                         */

