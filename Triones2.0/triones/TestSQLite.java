import SQLite.Database;
import SQLite.Exception;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.DriverManager;

import java.io.File;
import java.io.UnsupportedEncodingException;

import triones.util.StringMethod;

/**
* 测试SQLite（创建于 2006.08.16）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
略
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
* @version	2006.08.16
*/
public class TestSQLite 
{

/**
* Short concise description（创建于 2006.08.16）.
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
创建于 2006.08.16.
</pre>
</DL>
* @param args 第0个参数为数据库存储路径.
*/
	public static void main(String[] args) 
	{
		String lsDB = args[0];
		String lsUrl = toURL(lsDB);
		
		println("-------" + lsUrl + " [" + Database.version() + "]----------------");
		
		try 
		{
			new File(lsDB).delete();
			
			initEnv();
			
			createTable(lsUrl);
			insertData(lsUrl);	
			selectData(lsUrl);
			
		} 
		catch(java.lang.Exception e) 
		{
			// TODO 自动生成 catch 块
			e.printStackTrace();
		} 
		println("-----------------" + new java.util.Date() + "-----------------");
	}

/**
* 初始化环境变量（创建于 2006.08.16）.
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
创建于 2006.08.16.
</pre>
</DL>
*/
	public static void initEnv()
		throws ClassNotFoundException
	{
		Class.forName("SQLite.JDBCDriver");//Class.forName("org.sqlite.JDBC");//
	}


/**
* 根据数据所在位置创建URL（创建于 2006.08.16）.
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
创建于 2006.08.16.
</pre>
</DL>
* @param path 数据库存放路径.
* @return 数据库访问url.
*/
	public static String toURL(String path)
	{
		return "jdbc:sqlite:/" + StringMethod.replaceAll(path,"\\","/");//"" + file;//	
	}
/**
* 建表（创建于 2006.08.16）.
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
创建于 2006.08.16.
</pre>
</DL>
* @param path 数据库访问url.
*/
	public static void createTable(String url)
		throws SQLException
	{
		String ls_sql = "CREATE TABLE CustomerInfo("	+
        				"ID VARCHAR(16) PRIMARY KEY NOT NULL,"	+
        				"Name VARCHAR(16) NOT NULL,"	+
        				"IDCard VARCHAR(16),"	+
        				"grade VARCHAR(16) ,"	+
        				"Sex VARCHAR(1) ,"	+
				        "Birthday VARCHAR(16),"	+
				        "Company VARCHAR(64),"	+
				        "Charac VARCHAR(32),"	+
				        "Income NUMBER(10,2),"	+
				        "Family VARCHAR(64),"	+
				        "Health VARCHAR(64),"	+
				        "Mobile VARCHAR(16),"	+
				        "DayPhone VARCHAR(16),"	+
				        "NightPhone VARCHAR(16),"	+
				        "Fax VARCHAR(16),"	+
				        "Address VARCHAR(64),"	+
				        "PostalCode VARCHAR(8),"	+
				        "Email VARCHAR(32),"	+
				        "Homepage VARCHAR(64),"	+
				        "Remark VARCHAR(255));";

	
		Connection lConn = DriverManager.getConnection(url);
		Statement lStatement = lConn.createStatement();
		lStatement.execute(ls_sql);
		lStatement.close();
		lConn.close();
	}

	
/**
* 编码模式（创建于 2006.08.16）.
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
创建于 2006.08.16.
</pre>
</DL>
*/
	static String[] _Encoding = {"utf-8","iso-8859-1","gbk","gb2312","Shift-JIS","ASCII","US-ASCII","BIG5","EUC-JP","utf-16","utf-16be","utf-16le","UNICODE"};
/**
* 新增数据（创建于 2006.08.16）.
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
创建于 2006.08.16.
</pre>
</DL>
* @param path 数据库url.
*/
	
	public static void insertData(String url)
		throws SQLException,UnsupportedEncodingException
	{
		String ls_sql = "insert into CustomerInfo values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		Connection lCnn = DriverManager.getConnection(url);
		PreparedStatement lStatement =lCnn.prepareStatement(ls_sql);
		
		String[] ls_val = {"汤锡峰","高飞","于东","肖海勇","纪凯军","万红生","郭磊","殷珏莲","殷继豪","刘丹丹","夏磊","杨庆","费志明"};
		String lCodec = "GBK";
		
		//ls_val = StringMethod.encode(ls_val,lCodec,lCodec);
		
		for(int i=0;i<_Encoding.length;i++)
		{				
			String lstr = StringMethod.encode(ls_val[i],lCodec,"iso-8859-1");//ls_val;//	

			lStatement.setObject(1,_Encoding[i]);
			lStatement.setObject(2,lstr);//setBytes(2,lbytes);//
			lStatement.setObject(3,"a");
			lStatement.setObject(4,"b");
			lStatement.setObject(5,"c");
			lStatement.setObject(6,"d");
			lStatement.setObject(7,"e");
			lStatement.setObject(8,"f");
			lStatement.setObject(9,Double.valueOf("10.5"));
			lStatement.setObject(10,"g");
			lStatement.setObject(11,"h");
			lStatement.setObject(12,"i");
			lStatement.setObject(13,"j");
			lStatement.setObject(14,"k");
			lStatement.setObject(15,"l");
			lStatement.setObject(16,"m");
			lStatement.setObject(17,"n");
			lStatement.setObject(18,"o");
			lStatement.setObject(19,"p");
			lStatement.setObject(20,"q");
			lStatement.execute();
		}
		lStatement.close();
		lCnn.close();
	}
	

	
/**
* 数据查询（创建于 2006.08.16）.
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
创建于 2006.08.16.
</pre>
</DL>
* @param url 数据库访问路径.
* @exception SQLException description.
* @exception UnsupportedEncodingException description.
*/
	public static void selectData(String url) 
		throws SQLException,UnsupportedEncodingException
	{
		Connection lCnn = DriverManager.getConnection(url);
		
		String lsql ="select ID,Name from CustomerInfo";
		PreparedStatement lStatement =lCnn.prepareStatement(lsql);		
		ResultSet lResult = lStatement.executeQuery();
	    	    
		System.out.println("--------------" + lsql + "-------------------------------");
			
		while(lResult.next())
		{
			String lsEncoding = lResult.getString("ID");
			String lsFromVal = lResult.getString("name");
			String lsToVal= StringMethod.encode(lsFromVal,"utf-8","gbk");
			
			println(lsEncoding + " = [" + lsFromVal + "]->[" + lsToVal + "]");
			
		}
		lResult.close();
		lStatement.close();
		lCnn.close();
	}

	
/**
* 行打印输出（创建于 2006.08.16）.
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
创建于 2006.08.16.
</pre>
</DL>
* @param o 对象;
*/
	
	public static void println(Object o)
	{
		System.out.println("" + o);
	}

}
