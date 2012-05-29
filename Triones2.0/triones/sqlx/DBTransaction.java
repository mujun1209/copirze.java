package triones.sqlx;

import triones.bas.ReaderInputStream;
import triones.frame.RecordSet;
import triones.sql.DBMS;
import triones.sql.DBService;
import triones.sql.Syntax;
import triones.util.PreparedStatementMethod;
import triones.util.StringMethod;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Blob;
import java.sql.Clob;

import java.util.List;
import java.util.ArrayList;

import java.io.InputStream;
import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.OutputStream;

/**
* 数据库交易传输事务对象（创建于 2003.03.07）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
&nbsp;&nbsp;&nbsp;&nbsp;DBTrans主要针对JAVA数据库编程专门设计的，目的是为了统一与数据库进行数据交换的模式，
它采用数据对象的方式存取数据，并封装了Connection、Statement、PreparedStatement、
ResultSet等对象。DBTrans是跨数据库平台的，目前经过测试的有JDBC-ODBC,ORACLE8,MSS SQLSERVER2000。
</pre>
<DT><B>使用说明：</B><DD>
<pre>
&nbsp;&nbsp;&nbsp;&nbsp;在使用时先将用于存取数据的对象转换成Translet语义，然后调用DBTrans的insert、
update、delete、select等方法实现对数据库的存取操作。

假设数据库中创建表streamexample：

CREATE TABLE "STREAMEXAMPLE"
       ("NAME" VARCHAR2(256),
       "REG_DT" DATE,
       "GIFDATA" BLOB,
       "MONEY" NUMBER(5,2)) ;
				
为了存取其中的数据，设计StreamExample类：

public class StreamExample//DBTrans类名的默认规则是与表名一致
{public String name;//DBTrans列名的默认规则是与属性名一致
 public Timestamp reg_dt;
 public BigDecimal money;
 
	public StreamExample()//虽然DBTrans支持没有构造方法的类，但建议采用下列方式
	{	name = null;
		reg_dt = null;
		money = null;
	}
}

为了演示如何使用，我们设计一个Test类：

class Test
{

public static void main(String args[]) 
{	try
	{	//连接数据库
		DBTransaction SQLCA = new DBTransaction(DBMS.ORACLE,"@172.16.1.108:1521:ebdemo","cwstest","test");
		
		//设置插入到数据库的记录
		StreamExample example = new StreamExample();		
		example.name = "wyq1";		
		example.reg_dt = new Timestamp(1975,11,5,10,10,10,10);
		example.money = new BigDecimal(101);
		
		Translet com = new SQLObject(example);//转换成Translet
		com.hideCndField("money");//查询时money不作为条件表达式出现
		com.hideCndField("reg_dt");//查询时reg_dt不作为条件表达式出现
		
		//以下语句相当于执行INSERT INTO StreamExample
		//(name,reg_dt,money) VALUES(?,?,?)
		SQLCA.insert(com);
		
		//以下语句相当于执行SELECT name,reg_dt,money 
		//FROM StreamExample WHERE name = ?
		//查询结果集是以ArrayList的方式返回，其中的元素为StreamExample类
		StreamExample result = (StreamExample)(SQLCA.select(com).toArray())[0];
		
		//打印结果
		System.out.println(result.name + "," + result.money + "," + result.reg_dt);
											
		SQLCA.close();//断开数据库
	}
	catch(Exception e)
	{System.out.println(e);
	}
	
执行Test类，结果如下：

wyq1,101,3875-12-05 10:10:10.0

</pre>
<DT><B>注意事项：</B><DD>
<pre>
1. 值得注意的是，由于采用JDBC默认的数据转换模式，因此数据对象中的变量类型建议按以下
要求设计：
		<TABLE BORDER=2>
		<TR><TH>数据库类型</TH><TH>JAVA类型</TH>
		<TR><TD>CHAR</TD><TD>java.lang.String</TD>
		<TR><TD>VARCHAR</TD><TD>java.lang.String</TD>
		<TR><TD>LONGVARCHAR</TD><TD>java.lang.String</TD>
		<TR><TD>BIT</TD><TD>boolean/java.lang.Boolean</TD>
		<TR><TD>TINYINT</TD><TD>byte/java.lang.Byte</TD>
		<TR><TD>VARBINARY</TD><TD>byte[]</TD>
		<TR><TD>SMALLINT</TD><TD>short/java.lang.Short</TD>
		<TR><TD>INTEGER</TD><TD>int/java.lang.Integer</TD>
		<TR><TD>BIGINT</TD><TD>long/java.lang.Long</TD>
		<TR><TD>REAL</TD><TD>float/java.lang.Float</TD>
		<TR><TD>DOUBLE</TD><TD>double/java.lang.Double</TD>
		<TR><TD>NUMERIC(n,m)</TD><TD>java.math.BigDecimal/double/java.lang.Double/float/java.lang.Float</TD>
		<TR><TD>NUMERIC(n,0)</TD><TD>short/java.lang.Short/int/java.lang.Integer/long/java.lang.Long</TD>
		<TR><TD>DATE</TD><TD>java.sql.Date/java.util.Date</TD>
		<TR><TD>TIME</TD><TD>java.sql.Time</TD>
		<TR><TD>TIMESTAMP</TD><TD>java.sql.Timestamp</TD>
		</TABLE>
		
2.用于存取数据的类允许没有构造方法，但如果有则必须要有一个没有参数的构造方法。
3.能被用于存取数据的类的属性当且仅当它是public的。
4.需JDK1.3(或更高版本)、JDBC驱动包支持;
5.在对ORACLE8i的应用中发现存中文时，VARCHAR2仅能存放600个汉字;如果要存更多的请采用setBlob或者disableBind模式。
</pre>
<DT><B>展望未来：</B><DD>
<pre>
自从诞生了Connector以后，DBTrans作为数据库连接提供者的作用将逐步淡化。

&nbsp;&nbsp;&nbsp;&nbsp;目前DBTrans的内置了Connector，不仅保证了前后版本的一致性，并且提供了更加丰富的连接方法。
需要强调的是，虽然DBTrans是一个完善的Connection提供者，但在分布式应用中数据库连接驱动资源的重复利用率比较低，
更适用在非分布式数据库应用。因此，对于DBTrans来说，今后主要以面向Translet的语义解释，以及统一数据库执行过
程的形象出现，与Connection有关的方法将不推荐使用。

</pre>
<DT><B>联系方式：</B><DD>
<pre>
email：wyq@triok.com
电  话:021-68672222-2013
</pre>
</DL>
*@author	吴勇庆
*@version	2003.03.07
*@see Translet
*/

public class DBTransaction extends DBService implements Transaction
{		
/**
* 版本号（创建于 2003.03.07）.
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
创建于 2003.03.07.
</pre>
</DL>
*/
	public static final String VERSION = "2003.03.07 - wuyongqing";
	

	
/**
* 只可被子类调用的构造方法（创建于 2003.03.07）.
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
创建于 2003.03.07.
</pre>
</DL>
*/
	protected DBTransaction()
	{ super();
	}
	
	/**
	 * 指定数据库连接实例的构造方法（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	标准构造方法.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	String url = "jdbc:oracle:thin:@172.16.1.108:1521:ebdemo";
	Connection cnn = DriverManager.getConnection(url,"cwstest","test");
	
	DBTransaction SQLCA = new DBTransaction(cnn,DBMS.ORACLE);	
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.07.
	</pre>
	</DL>
* @param cnn 数据库连接实例.
* @param dbms 数据库类型.
	@see #DBTransaction(Connection)
	@see #DBTransaction(int,String,String,String)
	@see #DBTransaction(String,String,String,String)
*/
	public DBTransaction(Connection cnn,int dbms)
	{	super(cnn,dbms);
	}
	

	/**
	 * 指定数据库连接实例的构造方法（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	数据库类型将根据Connection的属性动态判断，如果判断不出则默认为ODBC。
	可以用setDBMS(int)指定数据库类型
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	String url = "jdbc:oracle:thin:@172.16.1.108:1521:ebdemo";
	Connection cnn = DriverManager.getConnection(url,"cwstest","test");
	
	//下面的语句相当于执行
	//DBTransaction SQLCA = new DBTransaction(DBMS.ORACLE,cnn);
	
	DBTransaction SQLCA = new DBTransaction(cnn);
	SQLCA.setDBMS(DBMS.ORACLE);
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.07.
	</pre>
	</DL>
* @param cnn 数据库连接实例.
*	@see #DBTransaction(Connection,int)
	@see #DBTransaction(int,String,String,String)
	@see #DBTransaction(String,String,String,String)
	@exception SQLException 访问数据库类型错误.
*/
	public DBTransaction(Connection cnn)
		throws SQLException
	{	super(cnn);
	}
	

	/**
	 * 根据数据库连接参数创建包含连接实例的构造方法（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	根据设定的DBMS自动载入相应的驱动类。
	要求驱动类的路径可被访问。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	DBTransaction SQLCA = new DBTransaction(DBMS.ORACLE,"@172.16.1.108:1521:ebdemo","cwstest","test");
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.07.
	</pre>
	</DL>
* @param dbms 数据库类型.
* @param datasrc 数据库的访问地址（不同于URL）.
* @param user 数据库访问用户.
* @param password 数据库访问密码.
* @exception SQLException 数据库连接失败.
* @see #DBTransaction(Connection)
	@see #DBTransaction(Connection,int)
	@see #DBTransaction(String,String,String,String)
*/
	public DBTransaction(int dbms,String datasrc,String user,String password)
		throws SQLException
	{	super(dbms,datasrc,user,password);
	}
	

	/**
	 * 根据数据库连接参数创建包含连接实例的构造方法（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	要求驱动类的路径可被访问。
	指定DBMS时可以用setDBMS(int).
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	String url = "jdbc:oracle:thin:@172.16.1.108:1521:ebdemo";
	String driverName = "oracle.jdbc.driver.OracleDriver";
	
	DBTransaction SQLCA = new DBTransaction(driverName,url,"cwstest","test");	
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.07.
	</pre>
	</DL>
* @param driverName 驱动类名称.
* @param url 数据库资源路径.
* @param user 数据库访问用户.
* @param password 数据库访问密码.

* @exception SQLException 数据库连接失败.
* @exception ClassNotFoundException 未找到驱动类.
* @see #DBTransaction(Connection)
	@see #DBTransaction(Connection,int)
	@see #DBTransaction(int,String,String,String)
*/
	public DBTransaction(String driverName,String url,String user,String password)
		throws SQLException,ClassNotFoundException
	{	super(driverName,url,user,password);
	}
	


	/**
	* 在数据库中新增记录（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	注意，应先将用于存放新增记录值的对象转换为Translet
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
		//example对应于StreamExample表，name,reg_dt,money对应于该表的列
		example.name = "wyq1";		
		example.reg_dt = new Timestamp(1975,11,5,10,10,10,10);
		example.money = new BigDecimal(101);
		
		Translet com = new SQLObject(example);//转换成Translet
		
		//以下语句相当于执行INSERT INTO StreamExample
		//(name,reg_dt,money) VALUES("wyq1",1975-11-05 10:10:10,101)
		SQLCA.insert(com);
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.07.
	</pre>
	</DL>
	* @param com 传输因子
	* @return 影响的记录个数
	* @throws SQLException
	* @see #update(Translet)
	* @see #update(Translet,Translet)
	* @see #delete(Translet)
	* @see #select(Translet)
	*/
	public int insert(Translet com)
		throws SQLException
	{	List lo_vals = new ArrayList();
		String ls_preparedSQL = com.prepareInsert(lo_vals);
		return execSQL(ls_preparedSQL,lo_vals);
	}

	/**
	* 在数据库中删除记录（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	注意，应先将用于存放删除记录值的对象转换为Translet
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//example对应于StreamExample表，name,reg_dt,money对应于该表的列
		example.name = "wyq1";		
		example.reg_dt = new Timestamp(1975,11,5,10,10,10,10);
		example.money = new BigDecimal(101);
		
		Translet com = new SQLObject(example);//转换成Translet
		com.hideCndField("money");//查询时money不作为条件表达式出现
		com.hideCndField("reg_dt");//查询时reg_dt不作为条件表达式出现
		//以下语句相当于执行DELETE FROM StreamExample WHERE name="wyq1"
		SQLCA.delete(com);
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.07.
	</pre>
	</DL>
	* @param com 传输因子
	* @return 影响的记录个数
	* @throws SQLException
	* @see #update(Translet)
	* @see #update(Translet,Translet)
	* @see #insert(Translet)
	* @see #select(Translet)
	* @see Translet
	*/
	public int delete(Translet com)
		throws SQLException
	{	List lo_vals = new ArrayList();
		String ls_preparedSQL = com.prepareDelete(lo_vals);
		return execSQL(ls_preparedSQL,lo_vals);
	}

	/**
	* 在数据库中用新的记录覆盖老的记录（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	注意，对象与数据库对象之间的命名规则取决于OperatorKernel.sqlRule。
	应保证新老记录对应于同一个表。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//oldexample,newexample对应于StreamExample表，name,reg_dt,money对应于该表的列
		
		oldexample.name = "wyq1";		
		newexample.name = "wyq2";
		
		Translet oldcom = new SQLObject(oldexample);//转换成Translet
		Translet newcom = new SQLObject(newexample);//转换成Translet
		
		oldcom.hideCndField("money");//查询时money不作为条件表达式出现
		oldcom.hideCndField("reg_dt");//查询时reg_dt不作为条件表达式出现
		newcom.hideField("money");//修改时money不作为被替换值出现
		newcom.hideField("reg_dt");//修改时reg_dt不作为被替换值出现
		//以下语句相当于执行UPDATE StreamExample SET name = "wyq2" WHERE name="wyq1"
		SQLCA.update(oldcom,newcom);
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.07.
	</pre>
	</DL>
	* @param oldCom 老传输因子.
	* @param newCom 新传输因子.
	* @return 影响的记录个数.
	* @throws SQLException 数据库错误.
	* @see #update(Translet)
	* @see #delete(Translet)
	* @see #insert(Translet)
	* @see #select(Translet)
	*/
	public int update(Translet oldCom,Translet newCom)
		throws SQLException
	{	List lo_vals = new ArrayList();
		String ls_preparedSQL = oldCom.prepareDelete(lo_vals);    
    execSQL(ls_preparedSQL,lo_vals);
    
    lo_vals = new ArrayList();
		ls_preparedSQL = newCom.prepareInsert(lo_vals);				
		return execSQL(ls_preparedSQL,lo_vals);
	}
	
	/**
	* 在数据库中修改记录（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	在实际应用中经常会碰到不修改关键字域，只修改其它域的值。
	针对这类情况，采用本方法无疑会更加方便，只需设定需要隐藏的条件列即可。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
		//example对应于StreamExample表，name,reg_dt,money对应于该表的列
		
		example.name = "wyq1";//name是StreamExample的关键字		
		example.money = 1200;
		example.reg_dt = null;
		
		Translet com = new SQLObject(example);//转换成Translet
		com.hideCndField("money");//查询时money不作为条件表达式出现
		com.hideCndField("reg_dt");//查询时reg_dt不作为条件表达式出现
		
		com.hideField("name");//修改时money不作为被替换值出现
		
		//以下语句相当于执行UPDATE StreamExample SET reg_dt = null,
		//money = 1200 WHERE name="wyq1"
		SQLCA.update(com);
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.07.
	</pre>
	</DL>
	* @param com 传输因子
	* @return 影响的记录个数	
	* @throws SQLException
	* @see #update(Translet,Translet)
	* @see #delete(Translet)
	* @see #insert(Translet)
	* @see #select(Translet)
	* @see Translet
	*/
	public int update(Translet com)
		throws SQLException
	{	List lo_vals = new ArrayList();
		String ls_preparedSQL = com.prepareUpdate(lo_vals);  
    return execSQL(ls_preparedSQL,lo_vals);
	}
/**
* 在数据库中查询记录（创建于 2003.03.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
注意，应先将作为存放查询条件的对象转换为Translet。
与ResultSet不同的是，返回的记录集不再占用数据库资源。
</pre>
<DT><B>示例：</B><DD>
<pre>
	//example对应于StreamExample表，name,reg_dt,money对应于该表的列
	
	example.name = "wyq1";//name是StreamExample的关键字		
	example.money = 1200;
	example.reg_dt = null;
	
	Translet com = new SQLObject(example);//转换成Translet
	com.hideCndField("money");//查询时money不作为条件表达式出现
			
	//以下语句相当于执行SELECT name,reg_dt,money FROM StreamExample
	//WHERE name="wyq1" AND reg_dt IS NULL
	RecordSet lRecordSet = SQLCA.select(com);
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.07.
</pre>
</DL>
* @param com 条件传输因子.
* @return 记录集.
* @throws SQLException 数据库错误.
* @see #select(Translet,int,int)
*/
	public RecordSet select(Translet com)
		throws SQLException
	{	_ResultSet = query(com);
		RecordSet lRecordSet = toRecordSet(_ResultSet);
		closeStatement();		
		return lRecordSet;		
	}

/**
* 在数据库中查询记录（创建于 2003.03.07）.
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
创建于 2003.03.07.
</pre>
</DL>
* @param com 条件传输因子.
* @param beginIndex 从第几个记录开始，范围为1..记录总数.
* @param rows >0 向后取几条记录，<0 向前取几条记录.
* @return 记录集.
* @exception SQLException 数据库错误.
	@see #select(Translet)
*/
	public RecordSet select(Translet com,int beginIndex,int rows)
		throws SQLException
	{	switch(_DBMS)
		{	case DBMS.ORACLE:_ResultSet = query(com,beginIndex,rows);break;
			default:	_ResultSet = query(com);break;								
		}
		
		RecordSet lRecordSet;
		
		switch(_DBMS)
		{	case DBMS.ORACLE:lRecordSet = toRecordSet(_ResultSet);break;
			default:	lRecordSet = toRecordSet(_ResultSet,beginIndex,rows);break;								
		}
		
		
		closeStatement();
		
		return lRecordSet;
		
	}
	


/**
* 在数据库中查询记录（创建于 2003.03.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
注意，应先将作为存放查询条件的对象转换为Translet。
如果想设计一个通用的数据存储对象则直接采用ResultSet无疑是最好的。
采用本方法最需要注意的是在ResultSet使用结束后应主动释放ResultSet占用的资源。
</pre>
<DT><B>示例：</B><DD>
<pre>
	//example对应于StreamExample表，name,reg_dt,money对应于该表的列
	
	example.name = "wyq1";//name是StreamExample的关键字		
	example.money = 1200;
	example.reg_dt = null;
	
	Translet com = new SQLObject(example);//转换成Translet
	com.hideCndField("money");//查询时money不作为条件表达式出现
			
	//以下语句相当于执行SELECT name,reg_dt,money FROM StreamExample
	//WHERE name="wyq1" AND reg_dt IS NULL
	ResultSet lResult = SQLCA.query(com);
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.07.
</pre>
</DL>
* @param com 条件传输因子.
* @return 结果集.
* @throws SQLException 数据库错误.
* @see #select(Translet)
*/
	public ResultSet query(Translet com)
		throws SQLException
	{	List lo_vals = new ArrayList();
		String ls_preparedSQL = com.prepareSelect(lo_vals);
		return execQuery(ls_preparedSQL,lo_vals);		
	}	

	
/**
* 在数据库中查询记录（创建于 2006.03.09）.
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
创建于 2006.03.09.
</pre>
</DL>
* @param com 对象结构.
* @param cnd 查询条件,不含WHERE关键字.
* @return 结果集.
*/	
	public ResultSet query(Translet com,String cnd)
		throws SQLException
	{ 	List lo_vals = new ArrayList();
		String ls_preparedSQL = com.prepareSelect(lo_vals);
		if(StringMethod.isValid(cnd)) ls_preparedSQL += (" WHERE " + cnd);
		return execQuery(ls_preparedSQL);		
	}
	
/**
* 在数据库中查询记录（创建于 2003.03.07）.
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
创建于 2003.03.07.
</pre>
</DL>
* @param com 传输因子.
* @param beginIndex 从第几个记录开始，范围为1..记录总数.
* @param rows >0 向后取几条记录，<0 向前取几条记录.
* @return 记录集.
* @exception SQLException 数据库错误.
*/
	private ResultSet query(Translet com,int beginIndex,int rows)
			throws SQLException
	{	List lo_vals = new ArrayList();
		String ls_preparedSQL = com.prepareSelect(lo_vals);
		ls_preparedSQL = DBMS.toRangeSQL(_DBMS,ls_preparedSQL,beginIndex,rows);
		
		return execQuery(ls_preparedSQL,lo_vals);		
	}	
	
/**
	*在数据库中获取某个域的二进制值（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	主要用于VARBINARY类型，不包括BLOB类型。
	&nbsp;&nbsp;&nbsp;&nbsp;虽然VARBINARY可以直接通过update,select,delete,insert等方法存取，但由于
	VARBINARY类型的数据流较大，从性能上考虑不宜一次取出。因此，建议采用本方法获取。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
		//example对应于StreamExample表，name,reg_dt,money对应于该表的列
		
		example.name = "wyq1";//name是StreamExample的关键字		
		example.money = 1200;
		example.reg_dt = null;
		
		Translet com = new SQLObject(example);//转换成Translet
		com.hideCndField("money");//查询时money不作为条件表达式出现
				
		//gifdata是一个Long Raw类型，以下语句相当于执行
		//SELECT gifdata FROM StreamExample
		//WHERE name="wyq1" AND reg_dt IS NULL
		byte[] lb_gif = SQLCA.getBinary(com,"gifdata");
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.07.
	</pre>
	</DL>
	*@param com	传输因子
	*@param binaryColName	二进制类型的域名
	*@return 用字节数组存放的值
	*@throws SQLException
	*@see #setBinary(Translet,String,byte[])
	*/
	public byte[] getBinary(Translet com,String binaryColName)
		throws SQLException
	{	List lo_vals = new ArrayList();
		String ls_preparedSQL = prepareSelect(com,binaryColName,lo_vals); 

		_ResultSet = execQuery(ls_preparedSQL,lo_vals);
		byte[] lBinary = (_ResultSet.next()?_ResultSet.getBytes(1):null);		
		
		closeStatement();
		
		return lBinary;
	}
  

  
/**
* 根据指定列，生成预编译SELECT语句（创建于 2004.09.21）.
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
创建于 2004.09.21.
</pre>
</DL>
* @param com 传输因子.
* @param binaryColName 指定列.
* @param vals 对应预编译SELECT语句中的绑定值.
* @return 预编译SELECT语句.
*/
  private static String prepareSelect(Translet com,String binaryColName,List vals)
  { String ls_preparedSQL = Syntax.toSELECT(com.getDBName(),binaryColName); 
		ls_preparedSQL += com.prepareWhere(vals);
    
    return ls_preparedSQL;
  }
	/**
	*用二进制的形式设置数据库中某个域的值（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	主要用于VARBINARY类型，不包括BLOB类型。
	&nbsp;&nbsp;&nbsp;&nbsp;虽然VARBINARY可以直接通过update,select,delete,insert等方法存取，但由于
	VARBINARY类型的数据流较大，从性能上考虑不宜一次存储多个。因此，建议采用本方法存储。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
		//example对应于StreamExample表，name,reg_dt,money对应于该表的列
		
		example.name = "wyq1";//name是StreamExample的关键字		
		example.money = 1200;
		example.reg_dt = null;
		
		Translet com = new SQLObject(example);//转换成Translet
		com.hideCndField("money");//查询时money不作为条件表达式出现
				
		//gifdata是一个Long Raw类型，以下语句相当于执行
		//UPDATE StreamExample SET gifdata = 'aaaaa'
		//WHERE name="wyq1" AND reg_dt IS NULL
		SQLCA.setBinary(com,"gifdata",new byte[]{'a','a','a','a','a'});
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.07.
	</pre>
	</DL>
	*@param com	传输因子
	*@param binaryColName	二进制域名
	*@param binaryValue 二进制域的值
	*@return 执行SQL语句后受影响的记录数
	*@throws SQLException
	*@see #getBinary(Translet,String)
	*/	
	public int setBinary(Translet com,String binaryColName,byte[] binaryValue)
		throws SQLException
	{ List lo_vals = new ArrayList();
		String ls_preparedSQL = prepareUpdate(com,binaryColName,Syntax.BIND_SYNTAX,lo_vals);
	
		_PStatement = _Connection.prepareStatement(ls_preparedSQL);		
		_PStatement.setBytes(1,binaryValue);
		PreparedStatementMethod.setObjects(_PStatement,2,lo_vals);
		
		int li_count = _PStatement.executeUpdate();		
		
		closeStatement(); 
		
		return li_count;
	}

  
/**
* 根据指定列，生成预编译UPDATE语句（创建于 2004.09.21）.
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
创建于 2004.09.21.
</pre>
</DL>
* @param com 传输因子.
* @param colName 指定列.
* @param colSyntax 指定列表达式.
* @param vals 对应预编译SELECT语句中的绑定值.
* @return 预编译UPDATE语句.
*/
	  
  private static String prepareUpdate(Translet com,String colName,String colSyntax,List vals)
  { String ls_preparedSQL = Syntax.toUPDATE(com.getDBName(),colName + " = " + colSyntax);
		ls_preparedSQL += com.prepareWhere(vals);
		return ls_preparedSQL;    
  }
	/**
	*在数据库中获取某个BLOB域的值（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	&nbsp;&nbsp;&nbsp;&nbsp;由于目前数据库厂商对BLOB类型的支持不广泛，使用前建议先详细阅读数据库厂商的
	技术白皮书，确认其是否已实现了Blob接口。
	目前ORACLE8支持,MSS SQLSERVER2000不支持
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
		//example对应于StreamExample表，name,reg_dt,money对应于该表的列
		
		example.name = "wyq1";//name是StreamExample的关键字		
		example.money = 1200;
		example.reg_dt = null;
		
		Translet com = new SQLObject(example);//转换成Translet
		com.hideCndField("money");//查询时money不作为条件表达式出现
				
		//myblob是一个BLOB类型，以下语句相当于执行
		//SELECT myblob FROM StreamExample
		//WHERE name="wyq1" AND reg_dt IS NULL
		byte[] lb_blob = SQLCA.getBlob(com,"myblob");
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.07.
	</pre>
	</DL>
	*@param com	传输因子
	*@param binaryColName	二进制类型的域名
	*@return 二进制的字节数组
	*@throws SQLException
	*@see #setBlob(Translet,String,byte[])
	*@see #getBlobStream(Translet,String)
	*@see #setBlobStream(Translet,String,InputStream)
	*/
	public byte[] getBlob(Translet com,String binaryColName)
		throws SQLException
	{ List lo_vals = new ArrayList();
		String ls_preparedSQL = prepareSelect(com,binaryColName,lo_vals); 
		
		_ResultSet = execQuery(ls_preparedSQL,lo_vals);		
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
* 取Clob型的字段的值（创建于 2003.08.11）.
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
创建于 2003.08.11.
</pre>
</DL>
* @param com 传输因子.
* @param binaryColName 大文本类型的域名.
* @return 字符数组.
* @exception SQLException 数据库错误.
* @see #getClobStream(Translet,String)
*/
	public char[] getClob(Translet com,String binaryColName)
		throws SQLException
	{	List lo_vals = new ArrayList();
		String ls_preparedSQL = prepareSelect(com,binaryColName,lo_vals); 
		
		_ResultSet = execQuery(ls_preparedSQL,lo_vals);		
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
* 以流的方式取Clob类型字段的值（创建于 2003.08.11）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
&nbsp;&nbsp;&nbsp;&nbsp;采用本方法时，由于没有显示释放与数据库会话占用的资源，因此在使用完InputStream
	后，建议用closeStatement方法强制释放资源。
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
* @param com 传输因子.
* @param binaryColName 大文本类型的字段名.
* @return 大文本值的流形式.
* @exception SQLException 数据库错误.
* @see #getClob(Translet,String)
	@see #closeStatement()
*/
	public InputStream getClobStream(Translet com,String binaryColName)
		throws SQLException
	{	List lo_vals = new ArrayList();
		String ls_preparedSQL = prepareSelect(com,binaryColName,lo_vals); 
		_ResultSet = execQuery(ls_preparedSQL,lo_vals);		
		
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
略
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
* @param com 传输因子.
* @param binaryColName 大文本类型的字段名.
* @param binaryValue 大文本值.
* @return 受影响的记录个数.
* @exception SQLException 数据库错误.
* @see #setClobStream(Translet,String,InputStream)
*/
	public int setClob(Translet com,String binaryColName,char[] binaryValue)
		throws SQLException
	{	if(binaryValue==null)
 		 return setEmptyLob(com,binaryColName);
			
		switch(_DBMS)
		{	case DBMS.ORACLE: return setOracleClob(com,binaryColName,binaryValue);
			default:	return setDefaultClob(com,binaryColName,binaryValue);			
		}		
			//throw new SQLException("current DBMS = '" + DBMS.SUPPORTED_DBMS[_DBMS] + "' is unsupported in setBlob");
	
	}
	

	
/**
* 默认的设置Clob的方法（创建于 2003.08.11）.
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
创建于 2003.08.11.
</pre>
</DL>
* @param com 传输因子.
* @param binaryColName 二进制域名.
* @param binaryValue 二进制域的值.
* @return 执行SQL语句后受影响的记录数.
* @exception SQLException 数据库错误.
*/
	private int setDefaultClob(Translet com,String binaryColName,char[] binaryValue)
		throws SQLException
  {	List lo_vals = new ArrayList();
		String ls_preparedSQL = prepareUpdate(com,binaryColName,Syntax.BIND_SYNTAX,lo_vals);
		
		ByteArrayInputStream lInputStream = new ByteArrayInputStream((new String(binaryValue)).getBytes());
    
		_PStatement = _Connection.prepareStatement(ls_preparedSQL);		
		_PStatement.setAsciiStream(1,lInputStream,binaryValue.length);
		PreparedStatementMethod.setObjects(_PStatement,2,lo_vals);
		
		int li_count = _PStatement.executeUpdate();		
		
		closeStatement(); 
		
		return li_count;

  }
	

	
/**
* 以流形式设置Clob类型的值（创建于 2003.08.11）.
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
创建于 2003.08.11.
</pre>
</DL>
* @param com 传输因子.
* @param binaryColName 大文本类型字段名.
* @param binaryValue 大文本值.
* @return 受影响的记录数.
* @exception SQLException 数据库错误.
* @exception IOException 输入流错误.
* @see #setClob(Translet,String,char[])
*/
	public int setClobStream(Translet com,String binaryColName,InputStream binaryValue)
		throws SQLException,IOException
	{	switch(_DBMS)
		{case DBMS.ORACLE: return setOracleClobStream(com,binaryColName,binaryValue);
		 default : return setDefaultClobStream(com,binaryColName,binaryValue);
		}				
	}
	

	
/**
* 默认的设置CLOB流方法（创建于 2003.08.11）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
不适合于ORACLE
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
* @param com 传输因子.
* @param binaryColName 大文本域名.
* @param binaryValue 大文本输入流.
* @return 执行SQL语句后受影响的记录数.
* @exception SQLException 数据库错误.
* @exception IOException 输入流异常.
*/
	private int setDefaultClobStream(Translet com,String binaryColName,InputStream binaryValue)
		throws SQLException,IOException
  {	List lo_vals = new ArrayList();
		String ls_preparedSQL = prepareUpdate(com,binaryColName,Syntax.BIND_SYNTAX,lo_vals);
		
		_PStatement = _Connection.prepareStatement(ls_preparedSQL);		
		_PStatement.setAsciiStream(1,binaryValue,binaryValue.available());
		PreparedStatementMethod.setObjects(_PStatement,2,lo_vals);
		
		_Syntax = ls_preparedSQL;//disableBind?DBMS.toSQL(_DBMS,preparedSQL,bindVals):preparedSQL;
		
		int li_count = _PStatement.executeUpdate();		
		
		closeStatement(); 
		
		return li_count;
  }
	/**
	* 设置数据库中某个BLOB域的值（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>	
	最新说明：本方法支持ORACLE、MSSQLSERVER、MYSQL、DB2
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
		//example对应于StreamExample表，name,reg_dt,money对应于该表的列
		
		example.name = "wyq1";//name是StreamExample的关键字		
		example.money = 1200;
		example.reg_dt = null;
		
		Translet com = new SQLObject(example);//转换成Translet
		com.hideCndField("money");//查询时money不作为条件表达式出现
				
		//myblob是一个BLOB类型，以下语句相当于执行
		//UPDATE StreamExample SET myblob = 'aaaaa'
		//WHERE name="wyq1" AND reg_dt IS NULL
		SQLCA.setBlob(com,"myblob",new byte[]{'a','a','a','a','a'});
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.07 - 目前版本仅支持ORACLE8，请密切注意新版对BLOB类型的支持程度
	目前仅ORACLE8支持,此方法使用时需保证AutoCommit = false,	由于ORACLE公司没有对
	Blob接口实现序列化，因此不能用于远程调用。
	
	修改于 2003.03.07 - 虞越提供的方法重新编写.
	修改于 2003.04.01 - 考虑到各类系统的兼容性，只能以牺牲性能为代价实现ORACLEBLOB.
	</pre>
	</DL>
	* @param com	传输因子.
	* @param binaryColName	二进制域名.
	* @param binaryValue 二进制域的值.
	* @return 执行SQL语句后受影响的记录数.
	* @throws SQLException 数据库错误.
	* @see #getBlob(Translet,String)
	* @see #setBlobStream(Translet,String,InputStream)
	* @see #getBlobStream(Translet,String)
	*/
	public int setBlob(Translet com,String binaryColName,byte[] binaryValue)
		throws SQLException
	{	if(binaryValue==null)
 		 return setEmptyLob(com,binaryColName);
		
		switch(_DBMS)
		{	case DBMS.ORACLE: return setOracleBlobBySP(com,binaryColName,binaryValue);
			default:	return setDefaultBlob(com,binaryColName,binaryValue);			
		}		
		//throw new SQLException("current DBMS = '" + DBMS.SUPPORTED_DBMS[_DBMS] + "' is unsupported in setBlob");
	}

	
/**
* 设置空的lob字段（创建于 2003.08.18）.
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
创建于 2003.08.18.
</pre>
</DL>
* @param com	传输因子.
* @param binaryColName	二进制域名.
* @return 执行SQL语句后受影响的记录数.
* @throws SQLException 数据库错误.
*/
	private int setEmptyLob(Translet com,String binaryColName)
		throws SQLException
	{	List lo_vals = new ArrayList();
		String ls_preparedSQL = prepareUpdate(com,binaryColName,"null",lo_vals);
		_PStatement = _Connection.prepareStatement(ls_preparedSQL);		
		PreparedStatementMethod.setObjects(_PStatement,1,lo_vals);
		
		int li_count = _PStatement.executeUpdate();
		
		closeStatement(); 
		
		return li_count;
	}
	/**
	*用字节数组设置数据库中某个BLOB域的值（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	目前版本仅支持ORACLE8
	此方法使用时需保证AutoCommit = false
	由于ORACLE公司没有对Blob接口实现序列化，因此采用setOracleBlobStream来实现。	
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.07.
	</pre>
	</DL>
	*@param com	传输因子
	*@param binaryColName	二进制域名
	*@param binaryValue 二进制域的值
	*@return 执行SQL语句后受影响的记录数
	*@throws SQLException 数据库错误.
	*/
	
	private int setOracleBlobBySP(Translet com,String binaryColName,byte[] binaryValue)
		throws SQLException
	{ try
		{	return setOracleBlobStream(com,binaryColName,new ByteArrayInputStream(binaryValue));
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
略
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
* @param com 传输因子.
* @param binaryColName 大文本类型字段名.
* @param binaryValue 大文本值.
* @return 受影响的记录数.
* @exception SQLException 数据库错误.
*/
	private int setOracleClobBySP(Translet com,String binaryColName,char[] binaryValue)
		throws SQLException
	{ try
		{	return setOracleClobStream(com,binaryColName,new ByteArrayInputStream((new String(binaryValue)).getBytes("UTF-16")));
		}
		catch(IOException e)
		{	return -2;//will not happened
		}
	}
  
  private ResultSet resetOracleBlob(Translet com,String binaryColName)
		throws SQLException
	{ List lo_vals = new ArrayList();
		String ls_preparedSQL,ls_preparedCnd;		
		
		//清除原来的BLOB变量，申请新的BLOB存放区域.
		ls_preparedCnd = com.prepareWhere(lo_vals);
		ls_preparedSQL = Syntax.toUPDATE(com.getDBName(),binaryColName + " = empty_blob()");
		ls_preparedSQL += ls_preparedCnd;
		execSQL(ls_preparedSQL,lo_vals);
		
		//锁定BLOB存放区域的位置		
		ls_preparedSQL = Syntax.toSELECT(com.getDBName(),binaryColName);
		ls_preparedSQL += (ls_preparedCnd + " for update");
		return execQuery(ls_preparedSQL,lo_vals);
  }
/**
	*用字节数组设置数据库中某个BLOB域的值（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	目前版本仅支持ORACLE8
	此方法使用时需保证AutoCommit = false
	由于ORACLE公司没有对Blob接口实现序列化，因此不能用于远程调用。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.07.
	</pre>
	</DL>
	*@param com	传输因子
	*@param binaryColName	二进制域名
	*@param binaryValue 二进制域的值
	*@return 执行SQL语句后受影响的记录数
	*@throws SQLException 数据库错误.
	*/
	
	private int setOracleBlob(Translet com,String binaryColName,byte[] binaryValue)
		throws SQLException
	{ List lo_vals = new ArrayList();
		String ls_preparedSQL,ls_preparedCnd;		
		
		/*for oracle only*/     		
		//清除原来的BLOB变量，申请新的BLOB存放区域.
		ls_preparedCnd = com.prepareWhere(lo_vals);
		ls_preparedSQL = Syntax.toUPDATE(com.getDBName(),binaryColName + " = empty_blob()");
		ls_preparedSQL += ls_preparedCnd;
		execSQL(ls_preparedSQL,lo_vals);
		
		//锁定BLOB存放区域的位置		
		ls_preparedSQL = Syntax.toSELECT(com.getDBName(),binaryColName);
		ls_preparedSQL += (ls_preparedCnd + " for update");
		_ResultSet = execQuery(ls_preparedSQL,lo_vals);
		_ResultSet.next();
		Blob lBlob = _ResultSet.getBlob(1);
		
		//设置新的BLOB值
		((oracle.sql.BLOB)lBlob).putBytes(1,binaryValue);		
		ls_preparedSQL =  Syntax.toUPDATE(com.getDBName(),binaryColName + " = " + Syntax.BIND_SYNTAX);
		ls_preparedSQL += ls_preparedCnd;
		
		_PStatement = _Connection.prepareStatement(ls_preparedSQL);		
		_PStatement.setBlob(1,lBlob);
		PreparedStatementMethod.setObjects(_PStatement,2,lo_vals);
		
		int li_count = _PStatement.executeUpdate();
		
		
		closeStatement();
		
		return li_count;
	}
	

	
/**
* 设置ORACLE的CLOB类型值（创建于 2003.08.12）.
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
创建于 2003.08.12.
</pre>
</DL>
* @param com 传输因子.
* @param binaryColName 大文本类型字段.
* @param binaryValue 大文本类型值.
* @return 受影响的记录数.
* @exception SQLException 数据库错误.
*/
	private int setOracleClob(Translet com,String binaryColName,char[] binaryValue)
		throws SQLException
	{ List lo_vals = new ArrayList();
		String ls_preparedSQL,ls_preparedCnd;		
		
		/*for oracle only*/     		
		//清除原来的BLOB变量，申请新的BLOB存放区域.
		ls_preparedCnd = com.prepareWhere(lo_vals);
		ls_preparedSQL = Syntax.toUPDATE(com.getDBName(),binaryColName + " = empty_clob()");
		ls_preparedSQL += ls_preparedCnd;
		execSQL(ls_preparedSQL,lo_vals);
		
		//锁定CLOB存放区域的位置		
		ls_preparedSQL = Syntax.toSELECT(com.getDBName(),binaryColName);
		ls_preparedSQL += (ls_preparedCnd + " for update");
		_ResultSet = execQuery(ls_preparedSQL,lo_vals);
		_ResultSet.next();
		Clob lClob = _ResultSet.getClob(1);
		
		//设置新的CLOB值
		((oracle.sql.CLOB)lClob).putString(1,new String(binaryValue));		
		ls_preparedSQL =  Syntax.toUPDATE(com.getDBName(),binaryColName + " = " + Syntax.BIND_SYNTAX);
		ls_preparedSQL += ls_preparedCnd;
		
		_PStatement = _Connection.prepareStatement(ls_preparedSQL);		
		_PStatement.setClob(1,lClob);
		PreparedStatementMethod.setObjects(_PStatement,2,lo_vals);
		
		int li_count = _PStatement.executeUpdate();
		
		
		closeStatement();
		
		return li_count;
	}
/**
	*在数据库中获取某个BLOB域的值（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	&nbsp;&nbsp;&nbsp;&nbsp;由于目前数据库厂商对BLOB类型的支持不广泛，使用前建议先详细阅读数据库厂商的
	技术白皮书，确认其是否已实现了Blob接口。
	目前ORACLE8支持,MSS SQLSERVER2000不支持
	&nbsp;&nbsp;&nbsp;&nbsp;采用本方法时，由于没有显示释放与数据库会话占用的资源，因此在使用完InputStream
	后，建议用closeStatement方法强制释放资源。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
		//example对应于StreamExample表，name,reg_dt,money对应于该表的列
		
		example.name = "wyq1";//name是StreamExample的关键字		
		example.money = 1200;
		example.reg_dt = null;
		
		Translet com = new SQLObject(example);//转换成Translet
		com.hideCndField("money");//查询时money不作为条件表达式出现
				
		//myblob是一个BLOB类型，以下语句相当于执行
		//SELECT myblob FROM StreamExample
		//WHERE name="wyq1" AND reg_dt IS NULL
		InputStream lis_file = SQLCA.getBlobStream(com,"myblob");
		byte[] lbytes = new byte[lis_file.available()]
		lis_file.read(lbytes);
		SQLCA.closeStatement();
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.07.
	</pre>
	</DL>
	*@param com	传输因子
	*@param binaryColName	二进制类型的域名
	*@return 二进制输入流
	*@throws SQLException
	*@see #setBlob(Translet,String,byte[])
	*@see #getBlob(Translet,String)
	*@see #setBlobStream(Translet,String,InputStream)
	*@see #closeStatement()
	*/
	public InputStream getBlobStream(Translet com,String binaryColName)
		throws SQLException
	{	List lo_vals = new ArrayList();
		String ls_preparedSQL = prepareSelect(com,binaryColName,lo_vals); 
		
		_ResultSet = execQuery(ls_preparedSQL,lo_vals);		
		
		InputStream lStream = null;
		
		if(_ResultSet.next())
		{	Blob lBlob = _ResultSet.getBlob(1);
			if(lBlob!=null)
				lStream = lBlob.getBinaryStream();
		}		
			
		return lStream;
	}
	
/**
	*用二进制输入流设置数据库中某个BLOB域的值（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	此方法使用时需保证AutoCommit = false
	
	对于ORACLE,影响写入速度的缓冲区大小取决于BLOB_BUFFER_SIZE
	
	适用于远程调用或写入超大二进制流
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
		//example对应于StreamExample表，name,reg_dt,money对应于该表的列
		
		example.name = "wyq1";//name是StreamExample的关键字		
		example.money = 1200;
		example.reg_dt = null;
		
		Translet com = new SQLObject(example);//转换成Translet
		com.hideCndField("money");//查询时money不作为条件表达式出现
				
		//myblob是一个BLOB类型，以下语句相当于执行
		//UPDATE StreamExample SET myblob = EMPTY_BLOB()
		//WHERE name="wyq1" AND reg_dt IS NULL
		//SELECT myblob FROM StreamExample
		//WHERE name="wyq1" AND reg_dt IS NULL FOR UPDATE
		//UPDATE StreamExample SET myblob = 'aaaaa'
		//WHERE name="wyq1" AND reg_dt IS NULL
		FileInputStream lfile = new FileInputStream("d:\\test.doc");
		SQLCA.setBlobStream(com,"myblob",lfile);
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.07.
	</pre>
	</DL>
	*@param com	传输因子
	*@param binaryColName	二进制域名
	*@param binaryValue 二进制域的值
	*@return 二进制流的写入次数
	*@throws SQLException
	*@throws IOException
	*@see #getBlobStream(Translet,String)
	*@see #setBlob(Translet,String,byte[])
	*@see #getBlob(Translet,String)
	*@see #LOB_BUFFER_SIZE
	*/
	public int setBlobStream(Translet com,String binaryColName,InputStream binaryValue)
		throws SQLException,IOException
	{	switch(_DBMS)
		{case DBMS.ORACLE: return setOracleBlobStream(com,binaryColName,binaryValue);
		 default : return setDefaultBlobStream(com,binaryColName,binaryValue);
		}				
	}
	/**
	*用二进制输入流设置数据库中某个BLOB域的值（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	目前版本仅支持ORACLE8
	
	此方法使用时需保证AutoCommit = false
	
	影响写入速度的缓冲区大小取决于BLOB_BUFFER_SIZE
	
	适用于远程调用或写入超大二进制流
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
		无
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.07.
	</pre>
	</DL>
	*@param com	传输因子
	*@param binaryColName	二进制域名
	*@param binaryValue 二进制域的值
	*@return 二进制流的写入次数
	*@throws SQLException
	*@throws IOException
	*/
	private int setOracleBlobStream(Translet com,String binaryColName,InputStream binaryValue)
		throws SQLException,IOException
	{ List lo_vals = new ArrayList();
		String ls_preparedSQL,ls_preparedCnd;
		
		/*for oracle only*/     		
		//清除原来的BLOB变量，申请新的BLOB存放区域.
		ls_preparedCnd = com.prepareWhere(lo_vals);
		ls_preparedSQL = Syntax.toUPDATE(com.getDBName(),binaryColName + " = empty_blob()");
		ls_preparedSQL += ls_preparedCnd;
		execSQL(ls_preparedSQL,lo_vals);
		
		//锁定BLOB存放区域的位置		
		ls_preparedSQL = Syntax.toSELECT(com.getDBName(),binaryColName);
		ls_preparedSQL += (ls_preparedCnd + " for update");
		_ResultSet = execQuery(ls_preparedSQL,lo_vals);
		_ResultSet.next();
		Blob lBlob = _ResultSet.getBlob(1);
		
		//设置新的BLOB值
		long ll_pos = 0;
		int li_size;
		byte[] lBuffer = new byte[LOB_BUFFER_SIZE];
		_CStatement = _Connection.prepareCall 
														("begin dbms_lob.write (" + Syntax.toBindSyntax(4) + "); end;");

		while((li_size = binaryValue.read(lBuffer)) != -1)
		{ _CStatement.setBlob (1, lBlob);
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
略
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
* @param com 传输因子.
* @param binaryColName 大文本类型字段名.
* @param binaryValue 大文本值.
* @return 受影响的记录数.
* @exception SQLException 数据库错误.
* @exception IOException 输入流错误.
*/
	private int setOracleClobStream(Translet com,String binaryColName,InputStream binaryValue)
		throws SQLException,IOException
	{ List lo_vals = new ArrayList();
		String ls_preparedSQL,ls_preparedCnd;
		
		/*for oracle only*/     		
		//清除原来的BLOB变量，申请新的BLOB存放区域.
		ls_preparedCnd = com.prepareWhere(lo_vals);
		ls_preparedSQL = Syntax.toUPDATE(com.getDBName(),binaryColName + " = empty_clob()");
		ls_preparedSQL += ls_preparedCnd;
		execSQL(ls_preparedSQL,lo_vals);
		
		//锁定CLOB存放区域的位置		
		ls_preparedSQL = Syntax.toSELECT(com.getDBName(),binaryColName);
		ls_preparedSQL += (ls_preparedCnd + " for update");
		_ResultSet = execQuery(ls_preparedSQL,lo_vals);
    _ResultSet.next();
		Clob lClob = _ResultSet.getClob(1);
		
		//设置新的BLOB值

		long ll_pos = 0;
		int li_size;
		byte[] lBuffer = new byte[LOB_BUFFER_SIZE];
		_CStatement = _Connection.prepareCall 
														("begin dbms_lob.write (" + Syntax.toBindSyntax(4) + "); end;");
		/*												
		while((li_size = binaryValue.read(lBuffer)) != -1)
		{ _CStatement.setClob (1, lClob);
      _CStatement.setLong (2, li_size);
      _CStatement.setLong (3, ll_pos + 1);				
      _CStatement.setBytes (4, lBuffer);			
			_CStatement.execute();
			ll_pos += li_size;	      
		}
		*/
		
		while((li_size = binaryValue.read(lBuffer)) != -1)
		{ String ls_buffer = new String(lBuffer,0,li_size);
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
	*在数据库中获取某个计算域的值（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	本方法不保证返回值的类型，因此使用时需要开发者强制转换。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//example对应于StreamExample表，name,reg_dt,money对应于该表的列
		
		example.name = "wyq1";//name是StreamExample的关键字		
		example.money = 1200;
		example.reg_dt = null;
		
		Translet com = new SQLObject(example);//转换成Translet
		com.hideCndField("name");//查询时name不作为条件表达式出现
		com.hideCndField("reg_dt");//查询时reg_dt不作为条件表达式出现
		
		//以下语句相当于执行
		//SELECT max(money) FROM StreamExample		
		BigDecimal lBD_maxmoney = (BigDecimal)SQLCA.getCompute(com,"max(money)");
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.07.
	</pre>
	</DL>
	*@param com	传输因子
	*@param computeSyntax	计算域表达式,例如:"max(id)"
	*@return 以对象方式存放的值
	*@throws SQLException
	*/
	public Object getCompute(Translet com,String computeSyntax)
		throws SQLException
	{ List lo_vals = new ArrayList();
		String ls_preparedSQL = prepareSelect(com,computeSyntax,lo_vals); 
		
		_ResultSet = execQuery(ls_preparedSQL,lo_vals);
		Object lObj = (_ResultSet.next()?_ResultSet.getObject(1):null);		
		
		closeStatement();
		
		return lObj;
	}
	

	/**
	 * 根据OperatorKernel定义的记录特征获取符合条件的记录个数（创建于 2002.07.29）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	相当于执行 select count(*) from ... where ...
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
		//定义查询特征
		FBI_USER u = new FBI_USER("abc","wu yongqing","12345678","A");
		//定义数据库连接资源
		DBTransaction sqlca = new DBTransaction(DBMS_MYSQL,"//triones/mysql","root","");
	
		Translet com = new SQLObject(u);
		com.hideCndFields();
		com.showCndField("U_ID");		
		
		//获取符合条件的记录个数
		int a = sqlca.getRowCount(com);
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2002.07.29.
	</pre>
	</DL>
* @param com 条件记录.
* @return 符合条件的记录个数.
* @exception SQLException 数据库出错.
*/
	public int getRowCount(Translet com)
		throws SQLException
	{	return (new Integer(getCompute(com,"count(*)")+ "")).intValue();
	}
	

	/**
	 *释放对象占有的JDBC资源（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	主要用于在垃圾收集器执行时，自动释放数据库连接资源.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	SQLCA.finalize();
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.07.
	</pre>
	</DL>
	 * @exception Throwable
	 */
	protected void finalize()
		throws Throwable
	{		super.finalize();
	}	
	


	/**
	 * 采用weblogic.jdbc.rmi.SerialOracleBlob设置blob数据（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	必须保证有weblogic.jdbc.rmi.SerialOracleBlob类
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
* @param com	传输因子
* @param binaryColName	二进制域名
* @param binaryValue 二进制域的值
* @return 执行SQL语句后受影响的记录数,-1代表执行失败。
* @throws SQLException 数据库错误.
*/
	public int setOracleBlobByWeblogic(Translet com,String binaryColName,byte[] binaryValue)
		throws SQLException
	{	List lo_vals = new ArrayList();
		String ls_preparedSQL,ls_preparedCnd;		
		
		/*for weblogic oracle only*/
			//清除原来的BLOB变量，申请新的BLOB存放区域.
		ls_preparedCnd = com.prepareWhere(lo_vals);
		ls_preparedSQL = Syntax.toUPDATE(com.getDBName(),binaryColName + " = empty_blob()");
		ls_preparedSQL += ls_preparedCnd;
		execSQL(ls_preparedSQL,lo_vals);
		
		//锁定BLOB存放区域的位置		
		ls_preparedSQL = Syntax.toSELECT(com.getDBName(),binaryColName);
		ls_preparedSQL += (ls_preparedCnd + " for update");
		_ResultSet = execQuery(ls_preparedSQL,lo_vals);
		_ResultSet.next();
		weblogic.jdbc.rmi.SerialOracleBlob lBlob = new weblogic.jdbc.rmi.SerialOracleBlob(_ResultSet.getBlob(1));
		try
		{
			//设置新的BLOB值
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
	 * 默认的设置Blob的方法（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	本方法MSSQLSERVER、MYSQL、DB2
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.07 - 虞越友情提供.
	</pre>
	</DL>
* @param com	传输因子.
* @param binaryColName	二进制域名.
* @param binaryValue 二进制域的值.
* @return 执行SQL语句后受影响的记录数.
* @throws SQLException 数据库错误.
* @see #setBlob(Translet,String,byte[])
*/
	private int setDefaultBlob(Translet com,String binaryColName,byte[] binaryValue)
		throws SQLException
  {	List lo_vals = new ArrayList();
		String ls_preparedSQL = prepareUpdate(com,binaryColName,Syntax.BIND_SYNTAX,lo_vals);
		
		ByteArrayInputStream lInputStream = new ByteArrayInputStream(binaryValue);
    
		_PStatement = _Connection.prepareStatement(ls_preparedSQL);		
		_PStatement.setBinaryStream(1,lInputStream,binaryValue.length);
		PreparedStatementMethod.setObjects(_PStatement,2,lo_vals);
		
		int li_count = _PStatement.executeUpdate();		
		
		closeStatement(); 
		
		return li_count;
  }
	
	/**
	 * 默认的设置Blob的方法（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	本方法支持MSSQLSERVER、MYSQL、DB2
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
* @param com	传输因子.
* @param binaryColName	二进制域名.
* @param binaryValue 二进制输入流.
* @return 执行SQL语句后受影响的记录数.
* @throws SQLException 数据库错误.
* @exception IOException 本异常实际不会发生.
* @see #setBlobStream(Translet,String,InputStream)
*/

	private int setDefaultBlobStream(Translet com,String binaryColName,InputStream binaryValue)
		throws SQLException,IOException
  {	List lo_vals = new ArrayList();
		String ls_preparedSQL = prepareUpdate(com,binaryColName,Syntax.BIND_SYNTAX,lo_vals);
		
		_PStatement = _Connection.prepareStatement(ls_preparedSQL);		
		_PStatement.setBinaryStream(1,binaryValue,binaryValue.available());
		PreparedStatementMethod.setObjects(_PStatement,2,lo_vals);
		
		_Syntax = ls_preparedSQL;//disableBind?DBMS.toSQL(_DBMS,preparedSQL,bindVals):preparedSQL;
		
		int li_count = _PStatement.executeUpdate();		
		
		closeStatement(); 
		
		return li_count;
  }

	

                       /* \\|///
                       \\  - -  //
                        (  @ @  )
**********************oOOo-(_)-oOOo************
*                                             *
*  Publish Date: 2003.08.28   Wu yongqing    *
*                                          *
*  We work because we wake               *
*                             Oooo        *
************************oooO**(   )*********
                       (   )   ) /
                        \ (   (_/
                         */
}

