package triones.sql;

import triones.Debug;
import triones.bas.ReaderInputStream;
import triones.frame.RecordSet;
import triones.frame.FrameMethod;
import triones.frame.WYQRecordSet;
import triones.frame.WYQRecordSetTag;
import triones.util.ResultSetMethod;
import triones.util.StringMethod;
import triones.util.PreparedStatementMethod;
import triones.util.ArrayMethod;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Blob;
import java.sql.Clob;
import java.util.List;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;



/**
* 事务传输对象（创建于 2004.06.30）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
DBService主要用于简化数据库事务操作,并建立跨数据库平台的统一方法.
虽然JDBC2.0在很大程度上提供了跨数据库的方法接口,但各数据库厂商对该接口的支持力度较差,
尤其是BLOB、CLOB等特殊类型.另外,JDBC接口本身更适用于低层开发,不能很好满足应用程序的快速开发.
</pre>
<DT><B>使用说明：</B><DD>
<pre>
当前支持的数据库请参见trones.sql.DBMS
</pre>
<DT><B>名词解释：</B><DD>
<pre>
略
</pre>
<DT><B>注意事项：</B><DD>
<pre>
基于JDK1.3
</pre>
<DT><B>展望未来：</B><DD>
<pre>
略
</pre>
<DT><B>联系方式：</B><DD>
<pre>
 email：wyq@triok.com 
 电 话：021-65804142
</pre>
</DL>
* @author	吴勇庆
* @version	2004.06.30
*/

public class DBService
{		
/**
* 版本号（创建于 2004.06.07）.
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
创建于 2004.06.07.
</pre>
</DL>
*/
	public static final String VERSION = "2004.06.30 - wuyongqing";
	
/**
* 数据库平台类型（创建于 2004.06.07）.
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
创建于 2004.06.07.
</pre>
</DL>
*/
	protected int _DBMS;	

	
/**
* 不采用绑定模式（创建于 2004.06.07）.
<DL>
<DT><B>说明：</B><DD>
<pre>
默认值为false
对数据库操作时DBService允许采用绑定参数的模式,生成预先编译过的SQL语句,
这样做可以大大提高对数据库的存取效率.但预编译的SQL语句不方便调试.
将disableBind设为 true 可以使getSQLSyntax()返回的SQL语句显示绑定值,但会
影响程序的执行速度,因此仅建议在调试中使用（对变量类型为byte[]和InputStream不适用）
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2002.09.25.
注销于 2004.06.07.
</pre>
</DL>
*/
	protected boolean _DisableBind = false;
	
/**
* 不采用绑定模式（创建于 2004.06.07）.
<DL>
<DT><B>说明：</B><DD>
<pre>
默认值为false
对数据库操作时DBService允许采用绑定参数的模式,生成预先编译过的SQL语句,
这样做可以大大提高对数据库的存取效率.但预编译的SQL语句不方便调试.
执行disableBind(true) 可以使getSQLSyntax()返回的SQL语句显示绑定值,但会
影响程序的执行速度,因此仅建议在调试中使用（对变量类型为byte[]和InputStream不适用）
目前仅对ORACLE有效.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
* @param option true代表不绑定,false代表绑定.
* @see #isDisableBind()
*/

	public void disableBind(boolean option)
	{	_DisableBind = option;
	}

	
/**
* 当前是否为绑定模式（创建于 2004.06.07）.
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
创建于 2004.06.07.
</pre>
</DL>
* @return true代表不绑定,false代表绑定.
* @see #disableBind(boolean)
*/
	public boolean isDisableBind()
	{	return _DisableBind;
	}
	
/**
* 连接对象（创建于 2004.06.07）.
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
创建于 2004.06.07.
</pre>
</DL>

*/
	protected Connection _Connection;	

/**
* 数据库会话实例（创建于 2004.06.07）.
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
创建于 2004.06.07.
</pre>
</DL>

*/
	protected Statement _Statement = null;

/**
* 预编译数据库会话实例（创建于 2004.06.07）.
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
创建于 2004.06.07.
</pre>
</DL>

*/
	protected PreparedStatement _PStatement = null;

/**
* 存储过程数据库会话实例（创建于 2004.06.07）.
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
创建于 2004.06.07.
</pre>
</DL>

*/
	protected CallableStatement _CStatement = null;
	
/**
* 存放数据库事务操作的返回结果（创建于 2004.06.07）.
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
创建于 2004.06.07.
</pre>
</DL>

*/
	protected ResultSet _ResultSet = null;

/**
* 以InputStream方式设置LOB类型值时的缓冲区大小（创建于 2004.06.07）.	
<P><DL>
<DT><B>说明：</B><DD>
<pre>
默认值为30000.根据Oracle技术白皮书显示最大为32767个字节,但实际情况小于该值.
该值越大则传输数据的速度越快
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>	
* @see #setBlobStream(String,List,InputStream)
* @see #setClobStream(String,List,InputStream)
*/
	public static int LOB_BUFFER_SIZE = 30000;
		

/**
* 提供调试的SQL表达式（创建于 2004.06.07）.
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
创建于 2004.06.07.
</pre>
</DL>

*/
	protected String _Syntax;
		
	
/**
* 只可被子类调用的构造方法（创建于 2004.06.07）.
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
创建于 2004.06.07.
</pre>
</DL>
*/
	protected DBService(){}
	
/**
 * 指定数据库连接实例的构造方法（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
标准构造方法.
</pre>
<DT><B>示例：</B><DD>
<pre>
String url = "jdbc:oracle:thin:@172.16.1.108:1521:ebdemo";
Connection cnn = DriverManager.getConnection(url,"cwstest","test");
DBService SQLCA = new DBService(cnn,DBMS.ORACLE);	
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
* @param cnn 数据库连接实例.
* @param dbms 数据库类型.
* @see #DBService(Connection)
* @see #DBService(int,String,String,String)
* @see #DBService(String,String,String,String)
*/
	public DBService(Connection cnn,int dbms)
	{	setDBMS(dbms);
		_Connection = cnn;
	}
	

/**
 * 指定数据库连接实例的构造方法（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
数据库类型将根据Connection的属性动态判断,如果判断不出则默认为ODBC.
可以用setDBMS(int)显式指定数据库类型
</pre>
<DT><B>示例：</B><DD>
<pre>
String url = "jdbc:oracle:thin:@172.16.1.108:1521:ebdemo";
Connection cnn = DriverManager.getConnection(url,"cwstest","test");
DBService SQLCA = new DBService(cnn);
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
* @param cnn 数据库连接实例.
* @exception SQLException 访问数据库类型错误.
* @see #DBService(Connection,int)
* @see #DBService(int,String,String,String)
* @see #DBService(String,String,String,String)
*/
	public DBService(Connection cnn)
	{	int li_dbms = findDBMS(cnn);
		if(li_dbms<0) li_dbms = DBMS.ODBC;
		
		setDBMS(li_dbms);
		
		_Connection = cnn;
	}
	

/**
 * 根据数据库连接参数创建包含连接实例的构造方法（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
根据设定的DBMS自动载入相应的驱动类.
要求驱动类的路径可被访问.
</pre>
<DT><B>示例：</B><DD>
<pre>
DBService SQLCA = new DBService(DBMS.ORACLE,"@172.16.1.108:1521:ebdemo","cwstest","test");
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
* @param dbms 数据库类型.
* @param datasrc 数据库的访问地址（不同于URL）.
* @param user 数据库访问用户.
* @param password 数据库访问密码.
* @exception SQLException 数据库连接失败.
* @see #DBService(Connection)
* @see #DBService(Connection,int)
* @see #DBService(String,String,String,String)
*/
	public DBService(int dbms,String datasrc,String user,String password)
		throws SQLException
	{	DriverDataSource ldds = new DriverDataSource(dbms,datasrc,user,password);
		_Connection = ldds.getConnection();
		goPrivateConnection(true);
	}
	

/**
 * 根据数据库连接参数创建包含连接实例的构造方法（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
要求驱动类的路径可被访问.
指定DBMS时可以用setDBMS(int).
</pre>
<DT><B>示例：</B><DD>
<pre>
String url = "jdbc:oracle:thin:@172.16.1.108:1521:ebdemo";
String driverName = "oracle.jdbc.driver.OracleDriver";
DBService SQLCA = new DBService(driverName,url,"cwstest","test");	
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
* @param driverName 驱动类名称.
* @param url 数据库资源路径.
* @param user 数据库访问用户.
* @param password 数据库访问密码.
* @exception SQLException 数据库连接失败.
* @exception ClassNotFoundException 未找到驱动类.
* @see #DBService(Connection)
* @see #DBService(Connection,int)
* @see #DBService(int,String,String,String)
*/
	public DBService(String driverName,String url,String user,String password)
		throws SQLException,ClassNotFoundException
	{	DriverDataSource ldds = new DriverDataSource(driverName,url,user,password);
		_Connection = ldds.getConnection();
		goPrivateConnection(true);
	}
	
/**
*将数据库传输事务对象转换成一个数据库的连接实例（创建于 2002.03.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
在实际应用中,可以采用本方法将数据库的连接传给其他类的方法调用
</pre>
<DT><B>示例：</B><DD>
<pre>
//SQLCA是DBService的一个实例
Connection cnn = SQLCA.asConnection();
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
*@return 当前的数据库连接
*/
	public Connection getConnection()
	{	return _Connection;
	}	
	
/**
* 提交一个交易事务（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
对应于SQL中的COMMIT；
当且仅当isAutoCommit()为false时适用.
</pre>
<DT><B>示例：</B><DD>
<pre>
//SQLCA是DBService的一个实例
String sql1 = "insert into employee(id,name,birth) values(?,?,?)";
String sql2 = "update employee set name = 'wu yongqing' where id = '0001'";

ArrayList lList = new ArrayList();
lList.add("0001");
lList.add("wyq");
lList.add(Timestamp.valueOf("1975-01-05"));

//如果处理失败则回滚事务
if(SQLCA.execSQL(sql1,lList) < 0)	
{	SQLCA.rollback();
	return;
}

//如果处理失败则回滚事务
if(SQLCA.execSQL(sql2,null) < 0)
{	SQLCA.rollback();
	return;
}

//如果执行成功则提交事务
SQLCA.commit();
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
* @throws SQLException 数据库错误.
* @see #rollback()
* @see #isAutoCommit()
*/
	public void commit()
		throws SQLException	
	{	_Connection.commit();
	}

/**
* 回滚一个交易事务（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
对于与SQL中的ROLLBACK；
当且仅当isAutoCommit()为false时适用.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
* @throws SQLException 数据库错误.
* @see #commit()
* @see #isAutoCommit()
*/
	public void rollback()
		throws SQLException
	{	_Connection.rollback();
		//catchSQLException(_Connection,"rollback");
	}

/**
* 设置数据库事务是否可自动提交（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
true 代表可自动提交.
false 代表不自动提交.
</pre>
<DT><B>示例：</B><DD>
<pre>
//SQLCA是DBService的一个实例
SQLCA.setAutoCommit(true);
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
* @param autoCommit	true代表自动提交,false代表非自动提交.
* @throws SQLException 数据库错误.
* @see #isAutoCommit()
*/
	public void setAutoCommit(boolean autoCommit)
		throws SQLException
	{	_Connection.setAutoCommit(autoCommit);
	}
	
/**
 * 判断当前数据库事务是否可自动提交（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
true 代表自动提交,此时commit()和rollback()无效.
false 代表不自动提交,可以使用commit()和rollback()方法.
各类数据库对自动提交模式的默认值是不同的,因此建议用setAutoCommit(boolean)显式设置.
</pre>
<DT><B>示例：</B><DD>
<pre>
boolean autoCommit = SQLCA.isAutoCommit();
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
* @return 数据库连接资源的自动提交状态.
* @exception SQLException 数据库错误.
* @see #setAutoCommit(boolean)
* @see #commit()
* @see #rollback()
*/
	public boolean isAutoCommit()
		throws SQLException
	{	return _Connection.getAutoCommit();
	}

/**
* 执行不产生结果集的SQL语句（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
可以是UPDATE、DELETE、INSERT等语句,或者是不产生结果集的其它语句.
</pre>
<DT><B>示例：</B><DD>
<pre>
//SQLCA是一个DBService对象
int li_deletedRowCount = SQLCA.execSQL("delete from empolyee");
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
*@param sql	SQL语句表达式.
*@return 执行该SQL语句后受影响的记录数,对于不产生结果集的语句则返回0.
*@throws SQLException 数据库错误.
*/
	public int execSQL(String sql)
		throws SQLException 
	{	_Syntax = sql;

		_Statement = _Connection.createStatement();
		int li_count = _Statement.executeUpdate(sql);	
		
		closeStatement();
		return li_count;
		//return _Connection.createStatement().execSQL(sql);
	}	
	

/**
* 执行带返回结果集的SQL语句（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
由于没有显示释放与数据库会话占用的资源,因此建议用closeStatement()方法强制释放资源.
</pre>
<DT><B>示例：</B><DD>
<pre>
//SQLCA是一个DBService对象
ResultSet lrs_employees = SQLCA.execQuery("select * from empolyee");
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
* @param sql SQL语句表达式,例如:"select * from empolyee".
* @return 结果集.
* @throws SQLException 数据库错误.
*/
	public ResultSet execQuery(String sql)
		throws SQLException
	{	_Syntax = sql;			
		_Statement = _Connection.createStatement();//获取未释放的Statement资源		
		return _Statement.executeQuery(sql);					
	}


/**
* 执行预编译SQL语句UPDATE,DELETE,INSERT（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
不支持LOB类型作为绑定值数组元素.
本方法执行完成后自动释放会话资源
</pre>
<DT><B>示例：</B><DD>
<pre>
//SQLCA是DBService的一个实例
String sql = "insert into employee(id,name,birth) values(?,?,?)";
List lList = new ArrayList();
lList.add("0001");
lList.add("wyq");
lList.add(Timestamp.valueOf("1975-01-05"));

//如果处理失败则回滚事务
if(SQLCA.execSQL(sql,lList) < 0)	
{	SQLCA.rollback();
	return;
}

//如果执行成功则提交事务
SQLCA.commit();
</pre>
<DT><B>示例2</B><DD>
<pre>
String sql = "update employee set name = 'wu yongqing' where id = '0001'";

//如果处理失败则回滚事务
if(SQLCA.execSQL(sql,null) < 0)
{	SQLCA.rollback();
	return;
}

//如果执行成功则提交事务
SQLCA.commit();
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
* @param preparedSQL 预编译SQL语句.
* @param bindVals 绑定值列表,null代表preparedSQL是直接可执行的SQL.
* @return 影响的记录个数.
* @throws SQLException 数据库错误.
* @see #execSQLWithReturn(String,List)
*/
	public int execSQL(String preparedSQL,List bindVals)
		throws SQLException
	{	Debug.println(preparedSQL);
    Debug.println(bindVals);
    
    if(bindVals==null) return execSQL(preparedSQL);

		//绑定值不为null时
		_Syntax = _DisableBind?DBMS.toSQL(_DBMS,preparedSQL,bindVals):preparedSQL;
	
		if(_DisableBind&&_DBMS==DBMS.ORACLE)
			return execSQL(_Syntax);	
		
		_PStatement = _Connection.prepareStatement(preparedSQL);
		PreparedStatementMethod.setObjects(_PStatement,bindVals);	
		int li_count = _PStatement.executeUpdate();	
		
		closeStatement();		
		return li_count;
	}

/**
* 执行带返回结果集的预编译SQL语句（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
不支持LOB类型作为绑定值数组元素.
由于没有显示释放与数据库会话占用的资源,因此建议用closeStatement()方法强制释放资源.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
*@param preparedSQL	预编译SQL语句.
 @param bindVals 绑定值列表,null代表preparedSQL是直接可执行的SQL.
*@return 结果集.
*@throws SQLException 数据库错误.
*@see #execSQLWithReturn(String,List)
*/
	public ResultSet execQuery(String preparedSQL,List bindVals)
		throws SQLException
	{	Debug.println(preparedSQL);
    	if(bindVals==null) return execQuery(preparedSQL);
    	Debug.println(bindVals);
		//绑定值不为null时
		
		if(_DisableBind&&_DBMS==DBMS.ORACLE)//静态SQL
			return execQuery(DBMS.toSQL(_DBMS,preparedSQL,bindVals));
		//动态SQL
		_Syntax = _DisableBind?DBMS.toSQL(_DBMS,preparedSQL,bindVals):preparedSQL;
		
		_PStatement = _Connection.prepareStatement(preparedSQL);
		//_PStatement = _Connection.prepareStatement(preparedSQL,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		PreparedStatementMethod.setObjects(_PStatement,bindVals);		

		return _PStatement.executeQuery();
	}


/**
* 执行带返回记录集的预编译SQL语句（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
不支持LOB类型作为绑定值数组元素.
本方法执行完成后自动释放会话资源.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
* @param preparedSQL 预编译SQL语句.
  @param bindVals 绑定值列表,null代表preparedSQL是直接可执行的SQL.
* @return 记录集.
* @throws SQLException 数据库错误.
* @see #execSQLWithReturn(String,List,int,int)
* @see #execQuery(String,List)
* @see #execSQL(String,List)
*/
	public RecordSet execSQLWithReturn(String preparedSQL,List bindVals)
 		throws SQLException
	{	Debug.println(preparedSQL);
    Debug.println(bindVals);
    
    _ResultSet = execQuery(preparedSQL,bindVals);

		RecordSet lRecordSet = toRecordSet(_ResultSet);

		closeStatement();
		
		return lRecordSet;	
	}
	
/**
* 执行带返回记录集的预编译SQL语句（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
不支持LOB类型作为绑定值数组元素.
本方法执行完成后自动释放会话资源.
</pre>
<DT><B>示例：</B><DD>
<pre>
无
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
* @param preparedSQL 预编译SQL语句.
* @param bindVals 绑定值列表,null代表preparedSQL是直接可执行的SQL.
* @param beginIndex 从第几个记录开始,范围为1..记录总数.
* @param rows >0 向后取几条记录,<0 向前取几条记录.
* @return 记录集.
* @throws SQLException 数据库错误.
* @see #execSQLWithReturn(String,List)
*/
	public RecordSet execSQLWithReturn(String preparedSQL,List bindVals,int beginIndex,int rows)
 		throws SQLException
	{	_ResultSet = execQuery(preparedSQL,bindVals);

		RecordSet lRecordSet = toRecordSet(_ResultSet,beginIndex,rows);

		closeStatement();
		
		return lRecordSet;	
	}

	
/**
* 在数据库中获取某个域的二进制值（创建于 2004.06.23）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
主要用于VARBINARY类型,不包括LOB类型.
需保证SELECT语句的第一个字段是二进制类型.
只有符合条件的第一条记录是有效的.
本方法执行完成后自动释放会话资源.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.23.
</pre>
</DL>
* @param preparedSQLSelect 预编译SELECT语句.
* @param bindVals 绑定值列表,null代表preparedSQL是直接可执行的SQL.
* @return 第1个读取字段的值.
* @exception SQLException 数据库异常.
* @see #setBinary(String,List,byte[])
*/
	public byte[] getBinary(String preparedSQLSelect,List bindVals)
		throws SQLException
	{   _ResultSet = execQuery(preparedSQLSelect,bindVals);

		byte[] lBinary = (_ResultSet.next()?_ResultSet.getBytes(1):null);		
		
		closeStatement();
		
		return lBinary;
	}


	
/**
* 用二进制的形式设置数据库中某个域的值（创建于 2004.06.23）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
主要用于VARBINARY类型,不包括BLOB类型.
需保证UPDATE语句的第一个字段是二进制类型.
必须用?代表二进制值的填充位置.
本方法执行完成后自动释放会话资源.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.23.
</pre>
</DL>
* @param preparedSQLUpdate 预编译UPDATE语句.
* @param bindVals 除二进制字段外的绑定值列表,null代表没有绑定值.
* @param binaryValue 二进制域的值.
* @return 执行SQL语句后受影响的记录数.
* @exception SQLException 数据库异常.
* @see #getBinary(String,List)
*/
	public int setBinary(String preparedSQLUpdate,List bindVals,byte[] binaryValue)
		throws SQLException
	{ 	List lo_vals = new ArrayList();
		
		lo_vals.add(binaryValue);
		if(bindVals!=null) lo_vals.addAll(bindVals);
		
		return execSQL(preparedSQLUpdate,lo_vals);
	}



/**
* 在数据库中获取某个BLOB域的值（创建于 2004.06.23）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
建议先详细阅读数据库厂商的技术白皮书,确认其是否已实现了Blob接口.
目前ORACLE8支持,MSS SQLSERVER2000不支持
需保证SELECT语句的第一个字段是BLOB类型.
只有符合条件的第一条记录是有效的.
本方法执行完成后自动释放会话资源.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.23.
</pre>
</DL>
* @param preparedSQLSelect 预编译SELECT语句.
* @param bindVals 绑定值列表,null代表preparedSQL是直接可执行的SQL.
* @return 第1个读取字段的值.
* @exception SQLException 数据库异常.
* @see #setBlob(String,List,byte[])
* @see #getBlobStream(String,List)
*/
	public byte[] getBlob(String preparedSQLSelect,List bindVals)
		throws SQLException
	{ 	_ResultSet = execQuery(preparedSQLSelect,bindVals);

		byte[] lBinary = null;		
		if(_ResultSet.next())
		{	Blob lBlob = _ResultSet.getBlob(1);
			if(lBlob!=null)
				lBinary = lBlob.getBytes(01,(int)lBlob.length());
		}
		
		
		closeStatement();
		
		return lBinary;
		
	}

/**
* 以流的方式获取某个BLOB域的值（创建于 2004.06.23）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
建议先详细阅读数据库厂商的技术白皮书,确认其是否已实现了Blob接口.
目前ORACLE8支持,MSS SQLSERVER2000不支持
需保证SELECT语句的第一个字段是BLOB类型.
只有符合条件的第一条记录是有效的.
本方法不会自动释放会话资源,因此在使用完InputStream后,建议用closeStatement()方法强制释放资源.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.23.
</pre>
</DL>
* @param preparedSQLSelect 预编译SELECT语句.
* @param bindVals 绑定值列表,null代表preparedSQL是直接可执行的SQL.
* @return 第1个读取字段的值.
* @exception SQLException 数据库异常.
* @see #setBlobStream(String,List,InputStream)
* @see #getBlob(String,List)
*/
	public InputStream getBlobStream(String preparedSQLSelect,List bindVals)
		throws SQLException
	{	_ResultSet = execQuery(preparedSQLSelect,bindVals);

		InputStream lStream = null;
		
		if(_ResultSet.next())
		{	Blob lBlob = _ResultSet.getBlob(1);
			if(lBlob!=null)
				lStream = lBlob.getBinaryStream();
		}		
			
		return lStream;
	}


	
/**
* 在数据库中获取某个CLOB域的值（创建于 2004.06.23）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
需保证SELECT语句的第一个字段是CLOB类型.
只有符合条件的第一条记录是有效的.
本方法执行完成后自动释放会话资源.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.23.
</pre>
</DL>
* @param preparedSQLSelect 预编译SELECT语句.
* @param bindVals 绑定值列表,null代表preparedSQL是直接可执行的SQL.
* @return 第1个读取字段的值.
* @exception SQLException 数据库异常.
* @see #setClob(String,List,char[])
* @see #getClobStream(String,List)
*/
	public char[] getClob(String preparedSQLSelect,List bindVals)
		throws SQLException
	{	_ResultSet = execQuery(preparedSQLSelect,bindVals);

		char[] lBinary = null;		
		if(_ResultSet.next())
		{	Clob lClob = _ResultSet.getClob(1);
			if(lClob!=null)
				lBinary = (lClob.getSubString(01,(int)lClob.length())).toCharArray();
		}
		
		
		closeStatement();
		
		return lBinary;
		
	}
	

	
/**
* 以流的方式获取某个CLOB域的值（创建于 2004.06.23）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
需保证SELECT语句的第一个字段是CLOB类型.
只有符合条件的第一条记录是有效的.
本方法不会自动释放会话资源,因此在使用完InputStream后,建议用closeStatement()方法强制释放资源.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.23.
</pre>
</DL>
* @param preparedSQLSelect 预编译SELECT语句.
* @param bindVals 绑定值列表,null代表preparedSQL是直接可执行的SQL.
* @return 第1个读取字段的值.
* @exception SQLException 数据库异常.
* @see #setClobStream(String,List,InputStream)
* @see #getClob(String,List)
*/
	public InputStream getClobStream(String preparedSQLSelect,List bindVals)
		throws SQLException
	{	_ResultSet = execQuery(preparedSQLSelect,bindVals);
		
		InputStream lStream = null;
		
		if(_ResultSet.next())
		{	Clob lClob = _ResultSet.getClob(1);
			if(lClob!=null)
				lStream = new ReaderInputStream(lClob.getCharacterStream());
		}		

		return lStream;
	}
	

/**
* 设置Clob类型字段的值（创建于 2003.08.11）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
此方法使用时需保证AutoCommit = false
需保证UPDATE语句的操作字段有且只有一个.
必须用?代表clob值的填充位置.
本方法执行完成后自动释放会话资源.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.08.11.
</pre>
</DL>
* @param preparedSQLUpdate 预编译UPDATE语句.
* @param bindVals 除二进制字段外的绑定值列表,null代表没有绑定值.
* @param binaryValue Clob域的值.
* @return 受影响的记录个数.
* @exception SQLException 数据库错误.
* @see #getClob(String,List)
* @see #setClobStream(String,List,InputStream)
*/
	public int setClob(String preparedSQLUpdate,List bindVals,char[] binaryValue)
		throws SQLException
	{	_Syntax = preparedSQLUpdate;//记录执行语句

		if(binaryValue==null)
 		 return setEmptyLob(preparedSQLUpdate,bindVals);
			
		switch(_DBMS)
		{	case DBMS.ORACLE: return setOracleClob(preparedSQLUpdate,bindVals,binaryValue);
			default:	return setDefaultClob(preparedSQLUpdate,bindVals,binaryValue);			
		}		
			//throw new SQLException("current DBMS = '" + DBMS.SUPPORTED_DBMS[_DBMS] + "' is unsupported in setBlob");
	
	}
	

	
/**
* 默认的设置Clob的方法（创建于 2003.08.11）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
此方法使用时需保证AutoCommit = false
需保证UPDATE语句的操作字段有且只有一个.
必须用?代表clob值的填充位置.
本方法执行完成后自动释放会话资源.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.08.11.
</pre>
</DL>
* @param preparedSQLUpdate 预编译UPDATE语句.
* @param bindVals 除二进制字段外的绑定值列表,null代表没有绑定值.
* @param binaryValue Clob域的值.
* @return 执行SQL语句后受影响的记录数.
* @exception SQLException 数据库错误.
*/
	private int setDefaultClob(String preparedSQLUpdate,List bindVals,char[] binaryValue)
		throws SQLException
  {		ByteArrayInputStream lInputStream = new ByteArrayInputStream((new String(binaryValue)).getBytes());
    
		_PStatement = _Connection.prepareStatement(preparedSQLUpdate);		
		_PStatement.setAsciiStream(1,lInputStream,binaryValue.length);
		if(bindVals!=null) PreparedStatementMethod.setObjects(_PStatement,2,bindVals);
		
		int li_count = _PStatement.executeUpdate();		
		
		closeStatement(); 
		
		return li_count;

  }
	

	
/**
* 以流形式设置Clob类型的值（创建于 2003.08.11）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
此方法使用时需保证AutoCommit = false
需保证UPDATE语句的操作字段有且只有一个.
必须用?代表clob值的填充位置.
本方法执行完成后自动释放会话资源.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.08.11.
</pre>
</DL>
* @param preparedSQLUpdate 预编译UPDATE语句.
* @param bindVals 除二进制字段外的绑定值列表,null代表没有绑定值.
* @param binaryValue Clob域的值.
* @return 受影响的记录数.
* @exception SQLException 数据库错误.
* @exception IOException 输入流错误.
* @see #getClobStream(String,List)
* @see #setClob(String,List,char[])
*/
	public int setClobStream(String preparedSQLUpdate,List bindVals,InputStream binaryValue)
		throws SQLException,IOException
	{	_Syntax = preparedSQLUpdate;//记录执行语句
		switch(_DBMS)
		{case DBMS.ORACLE: return setOracleClobStream(preparedSQLUpdate,bindVals,binaryValue);
		 default : return setDefaultClobStream(preparedSQLUpdate,bindVals,binaryValue);
		}				
	}
	

	
/**
* 默认的设置CLOB流方法（创建于 2003.08.11）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
不适合于ORACLE
此方法使用时需保证AutoCommit = false
需保证UPDATE语句的操作字段有且只有一个.
必须用?代表clob值的填充位置.
本方法不会自动释放会话资源,因此在使用完InputStream后,建议用closeStatement()方法强制释放资源.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.08.11.
</pre>
</DL>
* @param preparedSQLUpdate 预编译UPDATE语句.
* @param bindVals 除二进制字段外的绑定值列表,null代表没有绑定值.
* @param binaryValue Clob域的值.
* @return 执行SQL语句后受影响的记录数.
* @exception SQLException 数据库错误.
* @exception IOException 输入流异常.
*/
	private int setDefaultClobStream(String preparedSQLUpdate,List bindVals,InputStream binaryValue)
		throws SQLException,IOException
  	{	_PStatement = _Connection.prepareStatement(preparedSQLUpdate);		
		_PStatement.setAsciiStream(1,binaryValue,binaryValue.available());
		PreparedStatementMethod.setObjects(_PStatement,2,bindVals);

		int li_count = _PStatement.executeUpdate();			
		closeStatement(); 
		
		return li_count;
  	}
/**
* 设置数据库中某个BLOB域的值（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>	
最新说明：本方法支持ORACLE、MSSQLSERVER、MYSQL、DB2
此方法使用时需保证AutoCommit = false
需保证UPDATE语句的操作字段有且只有一个.
必须用?代表blob值的填充位置.
本方法执行完成后自动释放会话资源.
</pre>
<DT><B>示例：</B><DD>
<pre>
	无
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07
</pre>
</DL>
* @param preparedSQLUpdate 预编译UPDATE语句.
* @param bindVals 除二进制字段外的绑定值列表,null代表没有绑定值.
* @param binaryValue Blob域的值,null代表为BLOB字段赋空值.
* @return 执行SQL语句后受影响的记录数.
* @throws SQLException 数据库错误.
* @see #getBlob(String,List)
* @see #setBlobStream(String,List,InputStream)
*/
	public int setBlob(String preparedSQLUpdate,List bindVals,byte[] binaryValue)
		throws SQLException
	{	_Syntax = preparedSQLUpdate;//记录执行语句

		if(binaryValue==null)
 		 return setEmptyLob(preparedSQLUpdate,bindVals);
		
		switch(_DBMS)
		{	case DBMS.ORACLE: return setOracleBlobBySP(preparedSQLUpdate,bindVals,binaryValue);
			default:	return setDefaultBlob(preparedSQLUpdate,bindVals,binaryValue);			
		}		
		//throw new SQLException("current DBMS = '" + DBMS.SUPPORTED_DBMS[_DBMS] + "' is unsupported in setBlob");
	}

	
/**
* 设置空的LOB字段（创建于 2003.08.18）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
需保证UPDATE语句的操作字段有且只有一个.
必须用?代表lob值的填充位置.
本方法执行完成后自动释放会话资源.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.08.18.
</pre>
</DL>
* @param preparedSQLUpdate 预编译UPDATE语句.
* @param bindVals 除二进制字段外的绑定值列表,null代表没有绑定值.
* @return 执行SQL语句后受影响的记录数.
* @throws SQLException 数据库错误.
*/
	private int setEmptyLob(String preparedSQLUpdate,List bindVals)
		throws SQLException
	{	String ls_preparedSQL,ls_head,ls_tail;
		
		ls_preparedSQL = replaceBind(preparedSQLUpdate,"null");
		
		return execSQL(ls_preparedSQL,bindVals);
	}


	
/**
* 用指定文本替换预编译语句中的第一个占位符（创建于 2004.06.24）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
必须保证预编译语句至少有一个占位符
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.24.
</pre>
</DL>
* @param preparedSQL 预编译SQL语句.
* @param text 用于替换占位符的文本.
* @return 替换后的预编译SQL语句.
  @throws SQLException 没有找到占位符.
*/
	private String replaceBind(String preparedSQL,String text)
		throws SQLException
	{	int li_pos = preparedSQL.indexOf(Syntax.BIND_SYNTAX);		
		if(li_pos != -1)
		{	return preparedSQL = StringMethod.replace(preparedSQL,Syntax.BIND_SYNTAX,text);
		}
		else throw new SQLException("缺少需用文本替换的?占位符");
	}

/**
*设置ORACLE数据库中某个BLOB域的值（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
目前版本仅支持ORACLE8
此方法使用时需保证AutoCommit = false
需保证UPDATE语句的操作字段有且只有一个.
必须用?代表blob值的填充位置.
此方法使用时需保证AutoCommit = false
由于ORACLE公司没有对Blob接口实现序列化,因此采用setOracleBlobStream来实现.	
本方法执行完成后自动释放会话资源.
</pre>
<DT><B>示例：</B><DD>
<pre>
无
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
* @param preparedSQLUpdate 预编译UPDATE语句.
* @param bindVals 除二进制字段外的绑定值列表,null代表没有绑定值.
* @param binaryValue Blob域的值.
* @return 执行SQL语句后受影响的记录数
* @throws SQLException 数据库错误.
*/
	
	private int setOracleBlobBySP(String preparedSQLUpdate,List bindVals,byte[] binaryValue)
		throws SQLException
	{ try
		{	return setOracleBlobStream(preparedSQLUpdate,bindVals,new ByteArrayInputStream(binaryValue));
		}
		catch(IOException e)
		{	return 0;//will not happened
		}
	}
	

	
/**
* 以存储过程形式设置ORACLE数据库的CLOB类型值（创建于 2003.08.11）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
此方法使用时需保证AutoCommit = false
需保证UPDATE语句的操作字段有且只有一个.
必须用?代表clob值的填充位置.
本方法执行完成后自动释放会话资源.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.08.11.
</pre>
</DL>
* @param preparedSQLUpdate 预编译UPDATE语句.
* @param bindVals 除二进制字段外的绑定值列表,null代表没有绑定值.
* @param binaryValue Clob域的值.
* @return 受影响的记录数.
* @exception SQLException 数据库错误.
*/
	private int setOracleClobBySP(String preparedSQLUpdate,List bindVals,char[] binaryValue)
		throws SQLException
	{ 	try
		{	return setOracleClobStream(preparedSQLUpdate,bindVals,new ByteArrayInputStream((new String(binaryValue)).getBytes("UTF-16")));
		}
		catch(IOException e)
		{	return -2;//will not happened
		}
	}
/**
*用字节数组设置数据库中某个BLOB域的值（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
目前版本仅支持ORACLE8
此方法使用时需保证AutoCommit = false
需保证UPDATE语句的操作字段有且只有一个.
必须用?代表blob值的填充位置.
由于ORACLE公司没有对Blob接口实现序列化,因此不能用于远程调用.
本方法执行完成后自动释放会话资源.
</pre>
<DT><B>示例：</B><DD>
<pre>
无
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
* @param preparedSQLUpdate 预编译UPDATE语句.
* @param bindVals 除二进制字段外的绑定值列表,null代表没有绑定值.
* @param binaryValue Blob域的值.
* @return 执行SQL语句后受影响的记录数
* @throws SQLException 数据库错误.
*/

	private int setOracleBlob(String preparedSQLUpdate,List bindVals,byte[] binaryValue)
		throws SQLException
	{ 	String ls_preparedSQL,ls_preparedCnd,ls_table,ls_binaryColName;
		
		/*for oracle only*/     		
		//清除原来的BLOB变量,申请新的BLOB存放区域.
		ls_preparedSQL = replaceBind(preparedSQLUpdate,"empty_blob()");
		execSQL(ls_preparedSQL,bindVals);
		
		/*锁定BLOB存放区域的位置*/
		ls_table = Syntax.getSQLUpdateTable(preparedSQLUpdate);
		ls_preparedCnd = Syntax.getSQLWhere(preparedSQLUpdate);
		//实际上只允许有一个字段
		ls_binaryColName = ArrayMethod.toString(Syntax.getSQLUpdateFields(preparedSQLUpdate),',');
		ls_preparedSQL = Syntax.toSELECT(ls_table,ls_binaryColName);
		ls_preparedSQL += (ls_preparedCnd + " for update");
		//Debug.println(ls_preparedSQL);
		_ResultSet = execQuery(ls_preparedSQL,bindVals);
		_ResultSet.next();
		Blob lBlob = _ResultSet.getBlob(1);
		
		//设置新的BLOB值
		((oracle.sql.BLOB)lBlob).putBytes(1,binaryValue);		
				
		_PStatement = _Connection.prepareStatement(preparedSQLUpdate);		
		_PStatement.setBlob(1,lBlob);
		PreparedStatementMethod.setObjects(_PStatement,2,bindVals);
		
		int li_count = _PStatement.executeUpdate();
		
		
		closeStatement();
		
		return li_count;
	}
	

	
/**
* 设置ORACLE的CLOB类型值（创建于 2003.08.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
此方法使用时需保证AutoCommit = false
需保证UPDATE语句的操作字段有且只有一个.
必须用?代表blob值的填充位置.
本方法执行完成后自动释放会话资源.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.08.12.
</pre>
</DL>
* @param preparedSQLUpdate 预编译UPDATE语句.
* @param bindVals 除二进制字段外的绑定值列表,null代表没有绑定值.
* @param binaryValue Clob域的值.
* @return 受影响的记录数.
* @exception SQLException 数据库错误.
*/
	private int setOracleClob(String preparedSQLUpdate,List bindVals,char[] binaryValue)
		throws SQLException
	{ 	String ls_preparedSQL,ls_preparedCnd,ls_table,ls_binaryColName;
		
		/*for oracle only*/     		
		//清除原来的CLOB变量,申请新的BLOB存放区域.
		ls_preparedSQL = replaceBind(preparedSQLUpdate,"empty_clob()");
		execSQL(ls_preparedSQL,bindVals);
		
		/*锁定CLOB存放区域的位置*/
		ls_table = Syntax.getSQLUpdateTable(preparedSQLUpdate);
		ls_preparedCnd = Syntax.getSQLWhere(preparedSQLUpdate);
		//实际上只允许有一个字段
		ls_binaryColName = ArrayMethod.toString(Syntax.getSQLUpdateFields(preparedSQLUpdate),',');
		ls_preparedSQL = Syntax.toSELECT(ls_table,ls_binaryColName);
		ls_preparedSQL += (ls_preparedCnd + " for update");
		//Debug.println(ls_preparedSQL);
		_ResultSet = execQuery(ls_preparedSQL,bindVals);
		_ResultSet.next();

		Clob lClob = _ResultSet.getClob(1);
		
		//设置新的CLOB值
		((oracle.sql.CLOB)lClob).putString(1,new String(binaryValue));		

		_PStatement = _Connection.prepareStatement(preparedSQLUpdate);	
		_PStatement.setClob(1,lClob);
		PreparedStatementMethod.setObjects(_PStatement,2,bindVals);
		
		int li_count = _PStatement.executeUpdate();		
		
		closeStatement();
		
		return li_count;
	}



/**
*用二进制输入流设置数据库中某个BLOB域的值（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
此方法使用时需保证AutoCommit = false
需保证UPDATE语句的操作字段有且只有一个.
必须用?代表blob值的填充位置.
对于ORACLE,影响写入速度的缓冲区大小取决于LOB_BUFFER_SIZE
适用于远程调用或写入超大二进制流
本方法执行完成后自动释放会话资源.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
* @param preparedSQLUpdate 预编译UPDATE语句.
* @param bindVals 除二进制字段外的绑定值列表,null代表没有绑定值.
* @param binaryValue Blob域的值.
* @return 二进制流的写入次数
* @throws SQLException
* @throws IOException
* @see #LOB_BUFFER_SIZE
* @see #getBlobStream(String,List)
* @see #setBlob(String,List,byte[])
*/
	public int setBlobStream(String preparedSQLUpdate,List bindVals,InputStream binaryValue)
		throws SQLException,IOException
	{	_Syntax = preparedSQLUpdate;//记录执行语句

		switch(_DBMS)
		{case DBMS.ORACLE: return setOracleBlobStream(preparedSQLUpdate,bindVals,binaryValue);
		 default : return setDefaultBlobStream(preparedSQLUpdate,bindVals,binaryValue);
		}				
	}
/**
*用二进制输入流设置数据库中某个BLOB域的值（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
目前版本仅支持ORACLE8
此方法使用时需保证AutoCommit = false
需保证UPDATE语句的操作字段有且只有一个.
必须用?代表blob值的填充位置.
影响写入速度的缓冲区大小取决于LOB_BUFFER_SIZE
适用于远程调用或写入超大二进制流
本方法执行完成后自动释放会话资源.
</pre>
<DT><B>示例：</B><DD>
<pre>
	无
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.24.
</pre>
</DL>
* @param preparedSQLUpdate 预编译UPDATE语句.
* @param bindVals 除二进制字段外的绑定值列表,null代表没有绑定值.
* @param binaryValue Blob域的值.
* @return 二进制流的写入次数
* @throws SQLException
* @throws IOException
*/
	private int setOracleBlobStream(String preparedSQLUpdate,List bindVals,InputStream binaryValue)
		throws SQLException,IOException
	{ 	String ls_preparedSQL,ls_preparedCnd,ls_table,ls_binaryColName;
		
		/*for oracle only*/     		
		//清除原来的BLOB变量,申请新的BLOB存放区域.
		ls_preparedSQL = replaceBind(preparedSQLUpdate,"empty_blob()");
		execSQL(ls_preparedSQL,bindVals);
		
		/*锁定BLOB存放区域的位置*/
		ls_table = Syntax.getSQLUpdateTable(preparedSQLUpdate);
		ls_preparedCnd = Syntax.getSQLWhere(preparedSQLUpdate);
		//实际上只允许有一个字段
		ls_binaryColName = ArrayMethod.toString(Syntax.getSQLUpdateFields(preparedSQLUpdate),',');
		ls_preparedSQL = Syntax.toSELECT(ls_table,ls_binaryColName);
		ls_preparedSQL += (ls_preparedCnd + " for update");
		//Debug.println(ls_preparedSQL);
		_ResultSet = execQuery(ls_preparedSQL,bindVals);
		_ResultSet.next();
		Blob lBlob = _ResultSet.getBlob(1);
		
		//设置新的BLOB值
		long ll_pos = 0;
		int li_size;
		byte[] lBuffer = new byte[LOB_BUFFER_SIZE];
		_CStatement = _Connection.prepareCall("begin dbms_lob.write (" + Syntax.toBindSyntax(4) + "); end;");

		while((li_size = binaryValue.read(lBuffer)) != -1)
		{	_CStatement.setBlob (1, lBlob);
	      	_CStatement.setLong (2, li_size);
	      	_CStatement.setLong (3, ll_pos + 1);				
	      	_CStatement.setBytes (4, lBuffer);
			_CStatement.execute();
			ll_pos += li_size;	      
		}
		
		
		closeStatement();
		
		return 1;

	}

	
/**
* 以输入流方式设置ORACLE的CLOB（创建于 2003.08.11）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
此方法使用时需保证AutoCommit = false
需保证UPDATE语句的操作字段有且只有一个.
必须用?代表clob值的填充位置.
本方法执行完成后自动释放会话资源.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.08.11.
</pre>
</DL>
* @param preparedSQLUpdate 预编译UPDATE语句.
* @param bindVals 除二进制字段外的绑定值列表,null代表没有绑定值.
* @param binaryValue Clob域的值.
* @return 受影响的记录数.
* @exception SQLException 数据库错误.
* @exception IOException 输入流错误.
*/
	private int setOracleClobStream(String preparedSQLUpdate,List bindVals,InputStream binaryValue)
		throws SQLException,IOException
	{	String ls_preparedSQL,ls_preparedCnd,ls_table,ls_binaryColName;
		
		/*for oracle only*/     		
		//清除原来的BLOB变量,申请新的BLOB存放区域.
		ls_preparedSQL = replaceBind(preparedSQLUpdate,"empty_clob()");
		execSQL(ls_preparedSQL,bindVals);
		
		/*锁定CLOB存放区域的位置*/
		ls_table = Syntax.getSQLUpdateTable(preparedSQLUpdate);
		ls_preparedCnd = Syntax.getSQLWhere(preparedSQLUpdate);
		//实际上只允许有一个字段
		ls_binaryColName = ArrayMethod.toString(Syntax.getSQLUpdateFields(preparedSQLUpdate),',');
		ls_preparedSQL = Syntax.toSELECT(ls_table,ls_binaryColName);
		ls_preparedSQL += (ls_preparedCnd + " for update");
		//Debug.println(ls_preparedSQL);
		_ResultSet = execQuery(ls_preparedSQL,bindVals);
		_ResultSet.next();

		Clob lClob = _ResultSet.getClob(1);
		
		//设置新的CLOB值
		long ll_pos = 0;
		int li_size;
		byte[] lBuffer = new byte[LOB_BUFFER_SIZE];
		_CStatement = _Connection.prepareCall("begin dbms_lob.write (" + Syntax.toBindSyntax(4) + "); end;");

		while((li_size = binaryValue.read(lBuffer)) != -1)
		{ 	String ls_buffer = new String(lBuffer,0,li_size);
			int li_length = ls_buffer.length();
			_CStatement.setClob (1, lClob);
			_CStatement.setLong (2, li_length);
			_CStatement.setLong (3, ll_pos + 1);				
			_CStatement.setString (4, ls_buffer);			
			_CStatement.execute();
			ll_pos += li_length;	      
		}
		
		closeStatement();
		
		return 1;
	}

/**
* 取计算表达式的值（创建于 2004.06.23）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
SELECT语句的第一个字段是计算表达式.
只有符合条件的第一条记录是有效的.
本方法执行完成后自动释放会话资源.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.23.
</pre>
</DL>
* @param preparedSQLSelect 预编译SELECT语句.
* @param bindVals 绑定值列表,null代表preparedSQL是直接可执行的SQL.
* @return 第1个读取字段的值,null代表值为空.
* @exception SQLException 数据库异常.
*/
	public Object getCompute(String preparedSQLSelect,List bindVals)
		throws SQLException
	{	_ResultSet = execQuery(preparedSQLSelect,bindVals);

		Object lObj = (_ResultSet.next()?_ResultSet.getObject(1):null);		
		
		closeStatement();
		
		return lObj;
	}
/**
* 取计算表达式的值（创建于 2004.06.23）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
SELECT语句的第一个字段是计算表达式.
只有符合条件的第一条记录是有效的.
本方法执行完成后自动释放会话资源.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.23.
</pre>
</DL>
* @param sqlSelect SELECT语句.
* @return 第1个读取字段的值,null代表值为空.
* @exception SQLException 数据库异常.
*/
	public Object getCompute(String sqlSelect)
		throws SQLException
	{	_ResultSet = execQuery(sqlSelect);

		Object lObj = (_ResultSet.next()?_ResultSet.getObject(1):null);		
		
		closeStatement();
		
		return lObj;
	}
	

/**
 * 强制释放DBService当前占用的会话资源（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
当使用InputStream或ResultSet作为返回值结果时,不能马上释放占用的会话资源,
在某些应用中,这样的情况一旦频繁会导致资源不够,因此建议采用本方法释放资源.
</pre>
<DT><B>示例：</B><DD>
<pre>
//SQLCA为DBService实例
SQLCA.closeStatement();
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
 * @exception SQLException
 * @see #close()
 */
	public void closeStatement()
		throws SQLException
	{	if(_ResultSet!=null)
		{	_ResultSet.close();
			_ResultSet=null;
		}
		if(_Statement!=null) 
		{	_Statement.close();
			_Statement = null;
		}
		if(_PStatement!=null) 
		{	_PStatement.close();
			_PStatement = null;
		}
		if(_CStatement!=null) 
		{	_CStatement.close();
			_CStatement = null;
		}
	}


/**
 * 释放当前占用的数据库资源（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
如果事务对象的Connection不是私有的（isPrivateConnection()的返回值为false）,则不释放Connection占有的数据库资源
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
@see #isClosed()
@see #isPrivateConnection()
@see #closeStatement()
@exception SQLException 数据库错误.
*/
	public void close()
		throws SQLException
	{	closeStatement();
		if(isPrivateConnection()&&!_Connection.isClosed()) _Connection.close();
	}


/**
 * 判断当前占用的数据库资源是否已释放（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
无
</pre>
<DT><B>示例：</B><DD>
<pre>
无
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
* @return true代表已释放,false代表未释放.
* @exception SQLException 数据库错误.
* @see #close()
*/
	public boolean isClosed()
		throws SQLException
	{	return _Connection.isClosed();
	}
	

/**
 * 设置当前数据库资源的只读属性（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
option 为 true,代表只读,即执行select等不会影响数据库状态的操作；
false代表可写,即可执行insert、update、delete等会影响数据库状态的操作.
</pre>
<DT><B>示例：</B><DD>
<pre>
无
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
* @param option 只读属性.
* @exception SQLException 数据库错误.
* @see #isReadOnly()
*/
	public void setReadOnly(boolean option)
		throws SQLException
	{	_Connection.setReadOnly(option);
	}
	

/**
 * 判断当前数据库资源的只读属性（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
无
</pre>
<DT><B>示例：</B><DD>
<pre>
无
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
* @return true代表只读,false代表可写.
* @exception SQLException 数据库错误.
* @see #setReadOnly(boolean)
*/
	public boolean isReadOnly()
		throws SQLException
	{	return _Connection.isReadOnly();
	}

/**
 * 获取最近1次数据库操作的执行语句（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
主要用于调试.
</pre>
<DT><B>示例：</B><DD>
<pre>
//SQLCA是DBService实例
System.out.println(SQLCA.getSQLSyntax());
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
 * @return 执行时的SQL表达式.
 */	
	public String getSQLSyntax()
	{	return _Syntax;
	}

/**
 * 设置数据库平台类型（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
虽然DBService在设计时是跨平台的,但涉及到诸如LOB、底层存储过程等处理时由于各数据库厂商
提供的接口不同,因此需要设定DBMS的类型.
如果设置的值不属于DBMS.XXX系列,则采用默认值DBMS.ODBC.
如果只是使用一般的SQL操作,且DisableBind = false则DBMS不起作用.
</pre>
<DT><B>示例：</B><DD>
<pre>
//初始化时用ODBC方式连接数据库
DBService SQLCA = new DBService(DBMS.ODBC,"javademo;UID=DBA;PWD=sql");
//修改DBMS,一旦使用诸如setBlob方法或当DisableBind = true 时,将按照ORACLE的处理方式进行.
SQLCA.setDBMS(DBMS.ORACLE);
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
* @param dbms 数据库类型,参见triones.sql.DBMS.
* @see #getDBMS()
*/
	public void setDBMS(int dbms)
	{	if(!DBMS.isSupportedDBMS(dbms))
 			throw new IllegalArgumentException("数据库类型: " + dbms);		
		_DBMS = dbms;
	}	

/**
 * 取当前的数据库平台类型（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
返回值含义参见triones.sql.DBMS
</pre>
<DT><B>示例：</B><DD>
<pre>
无
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
* @return 数据库平台类型.
* @see #setDBMS(int)
*/
	public int getDBMS()
	{	return _DBMS;
	}
	

/**
 * 调用数据库中的存储过程或函数（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
本方法不适用于有返回结果集的存储过程或函数.
目前只适用于ORACLE8
必须用?代表method中参数的填充位置.
本方法执行完成后自动释放会话资源.
</pre>
<DT><B>示例：</B><DD>
<pre>
无
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
* @param method 存储过程或函数名.
* @param args 参数数组.
* @exception SQLException 数据库错误或者数据库不支持本方法.
*/
	public void execCall(String method,List args)
		throws SQLException
	{	switch(_DBMS)
		{	case DBMS.ORACLE: execOracleCall(method,args);break;
		 	default :throw new SQLException("current DBMS = '" + DBMS.SUPPORTED_DBMS[_DBMS] + "' is unsupported in execCall");
		}				
	}
	

/**
 * oracle中调用数据库中的存储过程或函数（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
必须用?代表method中参数的填充位置.
本方法执行完成后自动释放会话资源.
</pre>
<DT><B>示例：</B><DD>
<pre>
无
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
* @param method 存储过程或函数名.
* @param args 参数数组.
* @exception SQLException 数据库错误.
*/
	private void execOracleCall(String method,List args)
		throws SQLException
	{	String ls_sql = "";
	
		if(args != null && args.size() > 0)//设置参数
			ls_sql = Syntax.toBindSyntax(args.size());
		
		ls_sql = "begin " + method + "(" + ls_sql + "); end;";
			
		_CStatement = _Connection.prepareCall(ls_sql);
		
		PreparedStatementMethod.setObjects(_CStatement,args);
		_CStatement.execute();
		
		closeStatement();		
	}
	
	
/**
* 是否将Connection视为私有的（创建于 2004.06.07）.
<DL>
<DT><B>说明：</B><DD>
<pre>
Connection是作为外部资源提供给DBService的,一般因由外部释放.但鉴于Connector在生成
DBService时将的Connection看作是内含的,因此提供本属性以便设置.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
* @see #goPrivateConnection(boolean)
* @see #isPrivateConnection()
*/
	protected boolean _IsPrivateConnection = false;


	
/**
* 是否将DBService中Connection的归属类型设为私有的（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
如果Connection为DBService私有的,则DBService在释放资源时会自动释放Connection.
否则,DBService只释放Connection的会话（Statement）,不释放Connection本身.
受影响的方法：close()
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
* @param option true作为DBService私有的,false作为公用的.
* @see #isPrivateConnection()
*/
	public void goPrivateConnection(boolean option)
	{	_IsPrivateConnection = option;
	}
	
/**
* 判断当前DBService中Connection的归属类型为私有的（创建于 2004.06.07）.
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
创建于 2004.06.07.
</pre>
</DL>
* @return true代表为DBService私有的,false代表为公用的.
* @see #goPrivateConnection(boolean)
*/
	public boolean isPrivateConnection()
	{	return _IsPrivateConnection;
	}

/**
 * 对象消亡时执行的方法（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
主要用于在垃圾收集器执行时,自动释放数据库连接资源.
</pre>
<DT><B>示例：</B><DD>
<pre>
SQLCA.finalize();
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
 * @exception Throwable
 */
	protected void finalize()
		throws Throwable
	{	close();
		super.finalize();
	}	
	

	
/**
* 根据数据库连接获取DBMS枚举值（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
-1代表该数据库连接的数据库类型尚未登记.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
* @param cnn 数据库连接资源.
* @return 数据库类型DBMS枚举值.
* @see DriverDataSource#findDBMS(String)
*/
	private static int findDBMS(Connection cnn)
	{	try
		{ String ls_driver = cnn.getMetaData().getDriverName();
		  return DriverDataSource.findDBMS(ls_driver);			
		}
		catch(SQLException e)
		{ return -1;
		}		
	}
	

	


/**
 * 采用weblogic.jdbc.rmi.SerialOracleBlob设置blob数据（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
必须保证有weblogic.jdbc.rmi.SerialOracleBlob类
此方法使用时需保证AutoCommit = false
需保证UPDATE语句的操作字段有且只有一个.
必须用?代表blob值的填充位置.
本方法执行完成后自动释放会话资源.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
* @param preparedSQLUpdate 预编译UPDATE语句.
* @param bindVals 除二进制字段外的绑定值列表,null代表没有绑定值.
* @param binaryValue Blob域的值.
* @return 执行SQL语句后受影响的记录数,-1代表执行失败.
* @throws SQLException 数据库错误.
*/
	private int setOracleBlobByWeblogic(String preparedSQLUpdate,List bindVals,byte[] binaryValue)
		throws SQLException
	{ 	String ls_preparedSQL,ls_preparedCnd,ls_table,ls_binaryColName;
		
		/*for oracle only*/     		
		//清除原来的BLOB变量,申请新的BLOB存放区域.
		ls_preparedSQL = replaceBind(preparedSQLUpdate,"empty_blob()");
		execSQL(ls_preparedSQL,bindVals);
		
		/*锁定BLOB存放区域的位置*/
		ls_table = Syntax.getSQLUpdateTable(preparedSQLUpdate);
		ls_preparedCnd = Syntax.getSQLWhere(preparedSQLUpdate);
		//实际上只允许有一个字段
		ls_binaryColName = ArrayMethod.toString(Syntax.getSQLUpdateFields(preparedSQLUpdate),',');
		ls_preparedSQL = Syntax.toSELECT(ls_table,ls_binaryColName);
		ls_preparedSQL += (ls_preparedCnd + " for update");
		//Debug.println(ls_preparedSQL);
		_ResultSet = execQuery(ls_preparedSQL,bindVals);
		_ResultSet.next();

		weblogic.jdbc.rmi.SerialOracleBlob lBlob = new weblogic.jdbc.rmi.SerialOracleBlob(_ResultSet.getBlob(1));

		try
		{	//设置新的BLOB值
			OutputStream lOS = lBlob.getBinaryOutputStream();
			lOS.write(binaryValue);
		    lOS.flush();
		    lOS.close();	
		}
		catch(IOException e)
		{	return -1;
		}
		closeStatement();
		
		return 1;
	}
	

/**
 * 默认的设置Blob的方法（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
此方法使用时需保证AutoCommit = false
需保证UPDATE语句的操作字段有且只有一个.
必须用?代表blob值的填充位置.
本方法适用于MSSQLSERVER、MYSQL、DB2
本方法执行完成后自动释放会话资源.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07 - 虞越友情提供.
</pre>
</DL>
* @param preparedSQLUpdate 预编译UPDATE语句.
* @param bindVals 除二进制字段外的绑定值列表,null代表没有绑定值.
* @param binaryValue Blob域的值.
* @return 执行SQL语句后受影响的记录数.
* @throws SQLException 数据库错误.
*/
	private int setDefaultBlob(String preparedSQLUpdate,List bindVals,byte[] binaryValue)
		throws SQLException
  	{	ByteArrayInputStream lInputStream = new ByteArrayInputStream(binaryValue);
    
		_PStatement = _Connection.prepareStatement(preparedSQLUpdate);		
		_PStatement.setBinaryStream(1,lInputStream,binaryValue.length);
		if(bindVals!=null) PreparedStatementMethod.setObjects(_PStatement,2,bindVals);
		
		int li_count = _PStatement.executeUpdate();		
		
		closeStatement(); 
		
		return li_count;
 	 }
	
/**
* 默认以流的形式设置Blob的方法（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
本方法支持MSSQLSERVER、MYSQL、DB2
此方法使用时需保证AutoCommit = false
需保证UPDATE语句的操作字段有且只有一个.
必须用?代表blob值的填充位置.
本方法执行完成后自动释放会话资源.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
* @param preparedSQLUpdate 预编译UPDATE语句.
* @param bindVals 除二进制字段外的绑定值列表,null代表没有绑定值.
* @param binaryValue Blob域的值.
* @return 执行SQL语句后受影响的记录数.
* @throws SQLException 数据库错误.
* @exception IOException 本异常实际不会发生.
* @see #setBlobStream(Translet,String,InputStream)
*/
	private int setDefaultBlobStream(String preparedSQLUpdate,List bindVals,InputStream binaryValue)
		throws SQLException,IOException
  	{	_PStatement = _Connection.prepareStatement(preparedSQLUpdate);		
		_PStatement.setBinaryStream(1,binaryValue,binaryValue.available());
		PreparedStatementMethod.setObjects(_PStatement,2,bindVals);

		int li_count = _PStatement.executeUpdate();			
		closeStatement(); 
		
		return li_count;
	}
	
/**
* 将ResultSet转换为RecordSet（创建于 2004.06.13）.
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
创建于 2004.06.13.
</pre>
</DL>
* @param rs 结果集.
* @return 记录集.
* @exception SQLException 数据库错误.
* @see #toRecordSet(ResultSet,int,int)
*/
	public static RecordSet toRecordSet(ResultSet rs)
		throws SQLException
	{	String[] ls_cols = ResultSetMethod.getColNames(rs);
    WYQRecordSet lRecordSet = new WYQRecordSet(new WYQRecordSetTag("",ls_cols));		
		FrameMethod.load(lRecordSet,rs);
		return lRecordSet;
	}
/**
* 将ResultSet转换为RecordSet（创建于 2004.06.13）.
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
创建于 2004.06.13.
</pre>
</DL>
* @param rs 结果集.
* @param beginIndex 从第几个记录开始,范围为1..记录总数.
* @param rows >0 向后取几条记录,<0 向前取几条记录.
* @return 记录集.
* @exception SQLException 数据库错误.
* @see #toRecordSet(ResultSet)
*/
	public static RecordSet toRecordSet(ResultSet rs,int beginIndex,int rows)
		throws SQLException
	{	String[] ls_cols = ResultSetMethod.getColNames(rs);
    WYQRecordSet lRecordSet = new WYQRecordSet(new WYQRecordSetTag("",ls_cols));		
		FrameMethod.load(lRecordSet,rs,beginIndex,rows);
		return lRecordSet;
	}
	

}


/***********IMPLEMENTS Connection*************/
/*
	Statement createStatement()
	{	return _Connection.createStatement();
	}
	Statement createStatement(int resultSetType,int resultSetConcurrency) 
	{	return _Connection.createStatement(resultSetType,resultSetConcurrency);
	}
  Statement createStatement(int resultSetType,int resultSetConcurrency,int resultSetHoldability)
	{	return _Connection.createStatement(resultSetType,resultSetConcurrency,resultSetHoldability);
	}
	PreparedStatement prepareStatement(String sql)
	{	return _Connection.prepareStatement(sql);
	}
	PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) 
	{	return _Connection.prepareStatement(sql,autoGeneratedKeys);
	}
	PreparedStatement prepareStatement(String sql, int[] columnIndexes)
	{	return _Connection.prepareStatement(sql,columnIndexes);
	}
	CallableStatement prepareCall(String sql)
	{	return _Connection.prepareCall(sql);
	}
	
	String nativeSQL(String sql)
	{	return _Connection.nativeSQL(sql);
	}
	
	DatabaseMetaData getMetaData()
	{	return _Connection.getMetaData();
	}
	
	void setCatalog(String catalog)
	{	_Connection.setCatalog(catalog);
	}
	String getCatalog()
	{	return _Connection.getCatalog();
	}
	void setDBServiceIsolation(int level) 
	{	_Connection.setDBServiceIsolation(level);
	}
	int getDBServiceIsolation()
	{	return _Connection.getDBServiceIsolation();
	}
	
	SQLWarning getWarnings()
	{	return _Connection.getWarnings();
	}
	void clearWarnings()
	{	_Connection.clearWarnings();
	}

	*/
//
///**
// * 执行成批更新SQL语句（创建于 2004.06.07）.
//<P><DL>
//<DT><B>说明：</B><DD>
//<pre>
//与数组的顺序有关,从0开始.
//</pre>
//<DT><B>示例：</B><DD>
//<pre>
//String[] SQL = {"INSERT INTO WE_TEST(COL_VARCHAR,COL_NUMBER) VALUES('ABC',1)",
//				"INSERT INTO WE_TEST(COL_VARCHAR,COL_NUMBER) VALUES('DEF',2)",
//				"UPDATE WE_TEST SET COL_VARCHAR = NULL"
//				};
//
////SQLCA为DBService实例
//SQLCA.execUpdateBatch(SQL);	
//</pre>
//<DT><B>日志：</B><DD>
//<pre>
//创建于 2004.06.07.
//</pre>
//</DL>
//* @param sqls 以数组方式保存的批次更新SQL语句.
//* @return 执行每个SQL语句后受影响的记录数.
//* @exception SQLException 数据库错误.
//*/
//	private int[] execUpdateBatch(String[] sqls)
//		throws SQLException
//	{	_Statement = _Connection.createStatement();
//		int[] li_counts;
//		
//		_Syntax = "";
//		
//		for(int i = 0;i<sqls.length;i++)
//		{ _Syntax += sqls[i] + ";";
//			_Statement.addBatch(sqls[i]);
//		}
//  	
//		li_counts = _Statement.executeBatch();
//		
//		closeStatement();
//		
//		return li_counts;
//	}

///**
//* 执行含伪指令的SQL语句（创建于 2003.02.18）.
//<P><DL>
//<DT><B>说明：</B><DD>
//<pre>
//根据当前DBService的DBMS将伪指令转换成标准SQL并执行.
//</pre>
//<DT><B>示例：</B><DD>
//<pre>
//略
//</pre>
//<DT><B>日志：</B><DD>
//<pre>
//创建于 2003.02.18.
//</pre>
//</DL>
//* @param sql 伪指令SQL.
//* @return 执行该SQL语句后受影响的记录数,对于不产生结果集的语句则返回0.
//* @throws SQLException 数据库错误.
//*/
//	private int execPseudoSQL(String sql)
//		throws SQLException 
//	{	return execSQL(DBMS.toRealSQL(_DBMS,sql));
//		//return _Connection.createStatement().execSQL(sql);
//	}	
