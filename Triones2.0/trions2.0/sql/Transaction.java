package trionesII.sql;

import java.util.List;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.InputStream;
import java.io.IOException;

/**
* <img src="important.gif" width="35" height="25" border="0">数据库交易传输事务对象（创建于 2003.03.07）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
&nbsp;&nbsp;&nbsp;&nbsp;Transaction主要针对JAVA数据库编程专门设计的，目的是为了统一与数据库进行数据交换的模式，
它采用数据对象的方式存取数据，并封装Connection、Statement、PreparedStatement、ResultSet等对象。
</pre>
<DT><B>使用说明：</B><DD>
<pre>
&nbsp;&nbsp;&nbsp;&nbsp;在使用时先将用于存取数据的对象转换成Translet语义，然后调用Transaction的insert、
update、delete、select等方法实现对数据库的存取操作。

假设数据库中创建表streamexample：

CREATE TABLE "STREAMEXAMPLE"
       ("NAME" VARCHAR2(256),
       "REG_DT" DATE,
       "GIFDATA" BLOB,
       "MONEY" NUMBER(5,2)) ;
				
为了存取其中的数据，设计StreamExample类：

public class StreamExample//类名的默认规则是与表名一致
{public String name;//列名的默认规则是与属性名一致
 public Timestamp reg_dt;
 public BigDecimal money;
 
	public StreamExample()//默认构造方法.
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
	{	Connector cnn = new Connector(new File("DBMS.ini"));
		
		//连接数据库
		Transaction SQLCA = cnn.getTransaction();
		
		//设置插入到数据库的记录
		StreamExample example = new StreamExample();		
		example.name = "wyq1";		
		example.reg_dt = new Timestamp(1975,11,5,10,10,10,10);
		example.money = new BigDecimal(101);
		
		Translet com = new SQLObject(example);//转换成Translet
		com.hideCndField("money");//查询时money不作为条件表达式出现
		com.hideCndField("reg_dt");//查询时reg_dt不作为条件表达式出现
		
		//以下语句相当于执行
		//INSERT INTO StreamExample(name,reg_dt,money) VALUES(?,?,?)
		SQLCA.insert(com);
		
		//以下语句相当于执行
		//SELECT name,reg_dt,money FROM StreamExample WHERE name = ?
		RecordSet rs = SQLCA.select(com);
		
		//查询结果以List的方式返回，其中的元素为StreamExample类
		List ls = rs.toList(com.getFactory());
		
		StreamExample se = (StreamExample)ls.get(0);
		//打印结果
		System.out.println(se.name + "," + se.money + "," + se.reg_dt);
											
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
1. 值得注意的是，由于采用JDBC默认的数据转换模式，因此数据对象中的变量类型建议按以下要求设计：
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

2.需JDK1.3(或更高版本)、JDBC驱动包支持;
3.在对ORACLE8i的应用中发现存中文时，VARCHAR2仅能存放600个汉字;如果要存更多的请采用setBlob或者disableBind模式。
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
* @version	2003.03.07
* @see Translet
	@see RecordSet
	@see Connector
	@see DSRControl
*/
public interface Transaction
{	/**
	 * 取当前的数据库平台类型（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	返回值的枚举集合参见DBMS.
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
* @return 数据库平台类型.
* @see DBMS
*/
	public int getDBMS();
	/**
	*将数据库传输事务对象转换成一个数据库的连接实例（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	在实际应用中，可以采用本方法将数据库的连接传给其他类的方法调用
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//SQLCA是Transaction的一个实例
	Connection cnn = SQLCA.asConnection();
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.07.
	</pre>
	</DL>
	*@return 当前的数据库连接
	*/
	public Connection asConnection();
	/**
	 * 释放占用的数据库连接资源（创建于 2003.03.07）.
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
	@see #isClosed()
	@exception SQLException 数据库错误.
	*/
	public void close()throws SQLException;
	/**
	 * 强制释放DBTrans当前占用的会话资源（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	&nbsp;&nbsp;&nbsp;&nbsp;当使用InputStream作为返回值结果时，不能马上释放占用的会话资源，
在某些应用中，这样的情况一旦频繁会导致资源不够，因此建议采用本方法释放资源。
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
	 * @exception SQLException
	 * @see #getBlobStream
		 @see #getClobStream
	 */
	public void closeStatement()throws SQLException;
	/**
	* 提交一个交易事务（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	对应于SQL中的COMMIT；当且仅当isAutoCommit()为false时适用。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//SQLCA是Transaction的一个实例
	//comment1和comment2分别对应不同的处理请求，但作为同一个事务来处理
	
	//如果comment1处理失败则回滚事务
	if(SQLCA.insert(comment1) < 0)	SQLCA.rollback();
	
	//如果comment2处理失败则回滚事务
	if(SQLCA.update(comment2) < 0)  SQLCA.rollback();
	
	//如果comment1和comment2成功则提交事务
	SQLCA.commit();
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.07.
	</pre>
	</DL>
	* @throws SQLException 数据库错误.
	* @see #rollback
	* @see #setAutoCommit(boolean)
	*/
	public void commit()throws SQLException;
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
	* @param com 指令集.
	* @return 影响的记录个数.
	* @throws SQLException
	* @see #update(Translet)
	* @see #update(Translet,Translet)
	* @see #insert(Translet)
	* @see #select(Translet)
	*/
	public int delete(Translet com)throws SQLException;
/**
* 不采用绑定模式（创建于 2003.03.07）.
<DL>
<DT><B>说明：</B><DD>
<pre>
	默认值为false
	对数据库操作时Transaction允许采用绑定参数的模式，生成预先编译过的SQL语句，
	这样做可以大大提高对数据库的存取效率。但预编译的SQL语句不方便调试。
  执行disableBind(true) 可以使getSQLSyntax()返回的SQL语句显示绑定值，但会
	影响程序的执行速度，因此仅建议在调试中使用（对变量类型为byte[]和InputStream不适用）
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
* @param option true代表不绑定，false代表绑定.
* @see #isDisableBind()
*/
	public void disableBind(boolean option);
	/**
	* 执行预编译SQL语句UPDATE,DELETE,INSERT（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	不支持BLOB类型作为绑定值数组元素。
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
	* @param preparedSQL	预编译SQL语句.
	* @param bindVals	绑定值数组.
	* @return 影响的记录个数.
	* @throws SQLException 数据库错误.
	*/
	public int execSQL(String preparedSQL,List bindVals)throws SQLException;	
	
/**
* 执行不带返回结果集的SQL语句（创建于 2003.03.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
可以是UPDATE、DELETE、INSERT等语句，或者是不产生返回结果集的其它语句。
</pre>
<DT><B>示例：</B><DD>
<pre>
//SQLCA是一个Transaction对象
int li_deletedRowCount = SQLCA.execSQL("delete from empolyee");
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.07.
</pre>
</DL>
* @param sql	SQL语句表达式.
* @return	执行该SQL语句后受影响的记录数，对于不产生返回结果集的语句则返回0.
* @throws SQLException 数据库错误.
*/
	public int execSQL(String sql)throws SQLException;
/**
* 执行带返回记录集的SQL语句（创建于 2003.03.07）.
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
* @param sql SQL语句表达式.
* @param beginIndex 从第几个记录开始，范围为1..记录总数.
* @param rows >0 向后取几条记录，<0 向前取几条记录.
* @return	记录集.
* @throws SQLException 数据库错误.
*/
	public RecordSet execSQLWithReturn(String sql,int beginIndex,int rows)throws SQLException;
/**
* 执行带返回记录集的SQL语句（创建于 2003.03.07）.
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
* @param sql	SQL语句表达式.
* @return	记录集.
* @throws SQLException 数据库错误.
*/
	public RecordSet execSQLWithReturn(String sql) throws SQLException;
/**
	* 执行带返回记录集的预编译SQL语句（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	不支持BLOB类型作为绑定值数组元素。
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
	* @param preparedSQL	预编译SQL语句.
		@param bindVals	绑定值列表.
	* @param beginIndex 从第几个记录开始，范围为1..记录总数.
	* @param rows >0 向后取几条记录，<0 向前取几条记录.
	* @return 记录集.
	* @throws SQLException 数据库错误.
	*/
	public RecordSet execSQLWithReturn(String preparedSQL,List bindVals,int beginIndex,int rows)throws SQLException;
	/**
	* 执行带返回记录集的预编译SQL语句（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	不支持BLOB类型作为绑定值数组元素。
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
	*@param preparedSQL	预编译SQL语句.
	*@return 记录集.
	*@throws SQLException 数据库错误.
	*/
	public RecordSet execSQLWithReturn(String preparedSQL,List vals) throws SQLException;
/**
* 在数据库中获取某个BLOB域的值（创建于 2003.03.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
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
* @param com	指令集.
* @param binaryColName	二进制类型的域名.
* @return 二进制的字节数组.
* @throws SQLException 数据库错误.
* @see #setBlob(Translet,String,byte[])
*/
	public byte[] getBlob(Translet com,String binaryColName)throws SQLException;
/**
* 在数据库中获取某个计算域的值（创建于 2003.03.07）.
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
* @param com	指令集.
* @param computeSyntax	计算域表达式.
* @return 以对象方式存放的值.
* @throws SQLException 数据库错误.
* @see #getCompute(String)
*/
	public Object getCompute(Translet com,String computeSyntax)throws SQLException;
/**
* 在数据库中获取某个计算域的值（创建于 2003.03.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
本方法不保证返回值的类型，因此使用时需要开发者强制转换。
主要用于获取环境变量或常量值。
</pre>
<DT><B>示例：</B><DD>
<pre>
	//在ORACLE数据库中，以下语句相当于执行
	//SELECT sysdate FROM dual
	Timestamp lT_now = (Timestamp)SQLCA.getCompute("sysdate");
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.07.
</pre>
</DL>
* @param computeSyntax	计算域表达式.
* @return 以对象方式存放的值.
* @throws SQLException 数据库错误.
* @see #getCompute(Translet,String)
*/
	public Object getCompute(String computeSyntax)throws SQLException;
/**
 * 获取执行时的SQL语句（创建于 2003.03.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
主要用于调试,可在SQL请求执行后获得执行时的SQL语句.
与disableBind(boolean)配合，可决定SQL语句是以预编译SQL还是非预编译SQL形式体现。
</pre>
<DT><B>示例：</B><DD>
<pre>
SQLCA.insert(com);
System.out.println(SQLCA.getSQLSyntax());
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.07.
</pre>
</DL>
 * @return 执行时的SQL语句.
 */	
	public String getSQLSyntax();
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
* @param com 指令集.
* @return 影响的记录个数.
* @throws SQLException 数据库错误.
* @see #update(Translet)
* @see #update(Translet,Translet)
* @see #delete(Translet)
* @see #select(Translet)
*/
	public int insert(Translet com)throws SQLException;
	/**
	 * 获取当前数据库连接资源的自动提交状态（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	true 代表自动提交，此时commit和rollback无效。
	false 代表不自动提交，可以使用commit和rollback方法。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	boolean autoCommit = SQLCA.isAutoCommit();
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.07.
	</pre>
	</DL>
* @return 数据库连接资源的自动提交状态.
* @exception SQLException 数据库错误.
* @see #setAutoCommit(boolean)
*/
	public boolean isAutoCommit()throws SQLException;
/**
 * 判断当前数据库连接资源是否已释放（创建于 2003.03.07）.
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
创建于 2003.03.07.
</pre>
</DL>
* @return 数据库连接资源是否已释放.
* @exception SQLException 数据库错误.
* @see #close()
*/
	public boolean isClosed()throws SQLException;
/**
* 判断当前是否为绑定模式（创建于 2003.03.07）.
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
* @return true代表不绑定，false代表绑定.
* @see #disableBind(boolean)
*/
	public boolean isDisableBind();

/**
* 回滚一个交易事务（创建于 2003.03.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
对于与SQL中的ROLLBACK；当且仅当isAutoCommit()为false时适用。
</pre>
<DT><B>示例：</B><DD>
<pre>
//SQLCA是Transaction的一个实例
//comment1和comment2分别对应不同的处理请求，但作为同一个事务来处理

//如果comment1处理失败则回滚事务
if(SQLCA.insert(comment1) < 0)	SQLCA.rollback();

//如果comment2处理失败则回滚事务
if(SQLCA.update(comment2) < 0)  SQLCA.rollback();

//如果comment1和comment2成功则提交事务
SQLCA.commit();
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.07.
</pre>
</DL>
* @throws SQLException 数据库错误.
* @see #commit()
* @see #setAutoCommit(boolean)
*/
	public void rollback()throws SQLException;
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
* @param com 条件指令集.
* @return 记录集.
* @throws SQLException 数据库错误.
* @see #select(Translet,int,int)
*/
	public RecordSet select(Translet com)throws SQLException;
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
* @param com 条件指令集.
* @param beginIndex 从第几个记录开始，范围为1..记录总数.
* @param rows >0 向后取几条记录，<0 向前取几条记录.
* @return 记录集.
* @exception SQLException 数据库错误.
	@see #select(Translet)
*/
	public RecordSet select(Translet com,int beginIndex,int rows)throws SQLException;
/**
* 设置数据库连接时的事务自动提交的状态（创建于 2003.03.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
true 代表自动提交，此时commit和rollback无效。
false 代表不自动提交，可以使用commit和rollback方法。
各类数据库对自动提交模式的默认值是不同的。
</pre>
<DT><B>示例：</B><DD>
<pre>
//SQLCA是Transaction的一个实例
SQLCA.setAutoCommit(true);
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.07.
</pre>
</DL>
	@param	autoCommit	true代表自动提交，false代表非自动提交.	
	@throws SQLException 数据库错误.
	@see #commit()
	@see #rollback()
	@see #isAutoCommit()
*/
	public void setAutoCommit(boolean autoCommit)throws SQLException;
/**
* 设置数据库中某个BLOB域的值（创建于 2003.03.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>	
略
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
	SQLCA.setBlob(com,"myblob","aaaaa".getBytes());
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.07.
</pre>
</DL>
* @param com	指令集.
* @param binaryColName	二进制域名.
* @param binaryValue 二进制域的值.
* @return 执行SQL语句后受影响的记录数.
* @throws SQLException 数据库错误.
* @see #getBlob(Translet,String)
*/
	public int setBlob(Translet com,String binaryColName,byte[] binaryValue)throws SQLException;
/**
* 修改数据库中的记录（创建于 2003.03.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
在实际应用中经常会碰到不修改关键字域，只修改其它域的值。
针对这类情况，采用本方法无疑会更加方便，只需设定需要隐藏的列即可。
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
* @param com 指令集.
* @return 影响的记录个数.
* @throws SQLException 数据库错误.
* @see #update(Translet,Translet)
* @see #delete(Translet)
* @see #insert(Translet)
* @see #select(Translet)
*/
	public int update(Translet com)throws SQLException;
/**
	* 在数据库中用新的记录覆盖老的记录（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
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
	* @param oldCom 老指令集.
	* @param newCom 新指令集.
	* @return 影响的记录个数.
	* @throws SQLException 数据库错误.
	* @see #update(Translet)
	* @see #delete(Translet)
	* @see #insert(Translet)
	* @see #select(Translet)
	*/
	public int update(Translet oldCom,Translet newCom)throws SQLException;
	/**
	*在数据库中获取某个BLOB域的值（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	&nbsp;&nbsp;&nbsp;&nbsp;采用本方法时，由于没有显示释放与数据库会话占用的资源，因此在使用完InputStream
	后，建议用closeStatement方法强制释放资源。
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
	*@param com	指令集
	*@param binaryColName	二进制类型的域名
	*@return 二进制输入流
	*@throws SQLException
	*@see #setBlob(Translet,String,byte[])
	*@see #getBlob(Translet,String)
	*@see #setBlobStream(Translet,String,InputStream)
	*/
	public InputStream getBlobStream(Translet com,String binaryColName)	throws SQLException;
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
	*@param com	指令集
	*@param binaryColName	二进制域名
	*@param binaryValue 二进制域的值
	*@return 二进制流的写入次数
	*@throws SQLException
	*@throws IOException
	*@see #getBlobStream(Translet,String)
	*@see #setBlob(Translet,String,byte[])
	*@see #getBlob(Translet,String)
	*/
	public int setBlobStream(Translet com,String binaryColName,InputStream binaryValue)	throws SQLException,IOException;
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
* @param com 指令集.
* @param binaryColName 大文本类型的域名.
* @return 字符数组.
* @exception SQLException 数据库错误.
* @see #getClobStream(Translet,String)
*/
	public char[] getClob(Translet com,String binaryColName)	throws SQLException;
	
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
* @param com 指令集.
* @param binaryColName 大文本类型的字段名.
* @return 大文本值的流形式.
* @exception SQLException 数据库错误.
* @see #getClob(Translet,String)
*/
	public InputStream getClobStream(Translet com,String binaryColName)throws SQLException;
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
* @param com 指令集.
* @param binaryColName 大文本类型的字段名.
* @param binaryValue 大文本值.
* @return 受影响的记录个数.
* @exception SQLException 数据库错误.
* @see #setClobStream(Translet,String,InputStream)
*/
	public int setClob(Translet com,String binaryColName,char[] binaryValue)	throws SQLException;
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
* @param com 指令集.
* @param binaryColName 大文本类型字段名.
* @param binaryValue 大文本值.
* @return 受影响的记录数.
* @exception SQLException 数据库错误.
* @exception IOException 输入流错误.
* @see #setClob(Translet,String,char[])
*/
public int setClobStream(Translet com,String binaryColName,InputStream binaryValue)	throws SQLException,IOException;

	
}

