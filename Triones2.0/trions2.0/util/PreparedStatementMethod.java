package trionesII.util;

//import trionesII.Debug;
import java.util.List;
import java.util.Arrays;
import java.io.ByteArrayInputStream;
import java.sql.*;


/**
* PreparedStatement函数库（创建于 2003.03.04）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
为PreparedStatement对象提供静态方法.
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
 电  话:021-68672222-2013
</pre>
</DL>
* @author	吴勇庆
* @version	2003.03.04
*/
public class PreparedStatementMethod
{
	/**
	 * 设置预编译对象指定数据域的值（新建于 2003.03.04）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	功能与PrepareStatement.setObject(int,Object)类似,
	区别在于对于null可以自动转为setNull模式.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.04.	
	修改于 2003.05.26 - 针对byte[]实例进行了优化，原方法只能设定最大为4K的数组长度.
	</pre>
	</DL>
* @param ps 预编译对象.
* @param index 数据域位置,从1开始.
* @param val 数据值.
* @exception SQLException 设定值类型不匹配.
* @see #setObjects(PreparedStatement,int,List)
  @see #setObjects(PreparedStatement,List)
*/
	public static void setObject(PreparedStatement ps,int index,Object val)
		throws SQLException
	{	//Debug.println("setObject(" + ps + "," + index + "," + val + ")");
		if(val!=null) 
		{	if(val.getClass().equals(java.util.Date.class))//支持java.util.Date
				ps.setObject(index,new java.sql.Timestamp(((java.util.Date)val).getTime()));
			else if(val instanceof byte[])
			{	byte[] lbs_val = (byte[])val;
				ByteArrayInputStream lBytes = new  ByteArrayInputStream(lbs_val);
				ps.setBinaryStream(index,lBytes,lbs_val.length);
			}
			else
				ps.setObject(index,val);
		}
		else ps.setNull(index,Types.VARCHAR);
		//Debug.println("setObject(" + index + "," + val + ") is ok");
	}
	

	/**
	 * 设置预编译对象指定数据域的值（新建于 2003.03.04）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	功能与PrepareStatement.setInt(int,int)相同.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.04.	
	</pre>
	</DL>
* @param ps 预编译对象.
* @param index 数据域位置,从1开始.
* @param val 数据值.
* @exception SQLException 设定值类型不匹配.
*/
	public static void setObject(PreparedStatement ps,int index,int val)
		throws SQLException
	{	ps.setInt(index,val);		
	}
	
	/**
	 * 设置预编译对象指定数据域的值（新建于 2003.03.04）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	功能与PrepareStatement.setLong(int,long)相同.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.04.	
	</pre>
	</DL>
* @param ps 预编译对象.
* @param index 数据域位置,从1开始.
* @param val 数据值.
* @exception SQLException 设定值类型不匹配.
*/
	public static void setObject(PreparedStatement ps,int index,long val)
		throws SQLException
	{	ps.setLong(index,val);		
	}
	/**
	 * 设置预编译对象指定数据域的值（新建于 2003.03.04）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	功能与PrepareStatement.setFloat(int,float)相同.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.04.	
	</pre>
	</DL>
* @param ps 预编译对象.
* @param index 数据域位置,从1开始.
* @param val 数据值.
* @exception SQLException 设定值类型不匹配.
*/
	public static void setObject(PreparedStatement ps,int index,float val)
		throws SQLException
	{	ps.setFloat(index,val);		
	}
	/**
	 * 设置预编译对象指定数据域的值（新建于 2003.03.04）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	功能与PrepareStatement.setDouble(int,double)相同.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.04.	
	</pre>
	</DL>
* @param ps 预编译对象.
* @param index 数据域位置,从1开始.
* @param val 数据值.
* @exception SQLException 设定值类型不匹配.
*/
	public static void setObject(PreparedStatement ps,int index,double val)
		throws SQLException
	{	ps.setDouble(index,val);		
	}
	/**
	 * 设置预编译对象指定数据域的值（新建于 2003.03.04）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	功能与PrepareStatement.setBoolean(int,boolean)相同.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.04.	
	</pre>
	</DL>
* @param ps 预编译对象.
* @param index 数据域位置,从1开始.
* @param val 数据值.
* @exception SQLException 设定值类型不匹配.
*/
	public static void setObject(PreparedStatement ps,int index,boolean val)
		throws SQLException
	{	ps.setBoolean(index,val);		
	}
	/**
	 * 设置预编译对象指定数据域的值（新建于 2003.03.04）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	功能与PrepareStatement.setShort(int,short)相同.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.04.	
	</pre>
	</DL>
* @param ps 预编译对象.
* @param index 数据域位置,从1开始.
* @param val 数据值.
* @exception SQLException 设定值类型不匹配.
*/
	public static void setObject(PreparedStatement ps,int index,short val)
		throws SQLException
	{	ps.setShort(index,val);		
	}
	/**
	 * 设置预编译对象指定数据域的值（新建于 2003.03.04）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	把char当作String处理.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.04.	
	</pre>
	</DL>
* @param ps 预编译对象.
* @param index 数据域位置,从1开始.
* @param val 数据值.
* @exception SQLException 设定值类型不匹配.
*/
	public static void setObject(PreparedStatement ps,int index,char val)
		throws SQLException
	{	ps.setObject(index,val + "");		
	}

	/**
	 * 设置预编译对象指定数据域的值（新建于 2003.03.04）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	功能与PrepareStatement.setByte(int,byte)相同.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.04.	
	</pre>
	</DL>
* @param ps 预编译对象.
* @param index 数据域位置,从1开始.
* @param val 数据值.
* @exception SQLException 设定值类型不匹配.
*/
	public static void setObject(PreparedStatement ps,int index,byte val)
		throws SQLException
	{	ps.setByte(index,val);		
	}
	/**
	 * 设置预编译对象指定数据域的值（新建于 2003.03.04）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	功能与PrepareStatement.setBytes(int,byte[])相同.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.04.	
	</pre>
	</DL>
* @param ps 预编译对象.
* @param index 数据域位置,从1开始.
* @param val 数据值.
* @exception SQLException 设定值类型不匹配.
*/
	public static void setObject(PreparedStatement ps,int index,byte[] val)
		throws SQLException
	{	ps.setBytes(index,val);		
	}

		
/**
* 设置域编译对象的数据域（创建于 2003.03.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
数据域索引从1开始，到bindVals.size()为止。
采用本方法需保证bindVals.size()的个数不超过数据域个数.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.04.
</pre>
</DL>
* @param ps 预编译对象.
* @param bindVals 数据值数组.
	@return 设置成功的数据值个数,-1代表bindVals为null.
* @exception SQLException 设定值类型不匹配.
* @see #setObjects(PreparedStatement,int,List)
	@see #setObject(PreparedStatement,int,Object)
*/
	public static int setObjects(PreparedStatement ps,List bindVals)
		throws SQLException
	{	return setObjects(ps,1,bindVals);
	}


	
/**
* 从指定数据域开始设置域编译对象的数据域（创建于 2003.03.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
采用本方法需保证bindVals.size()的个数不超过从指定数据域索引开始的数据域个数.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.04.
</pre>
</DL>
* @param ps 预编译对象.
* @param index 数据域索引号,取值范围≥1.
* @param bindVals 数据值数组.
	@return 设置成功的数据值个数,-1代表bindVals为null.
* @exception SQLException 设定值类型不匹配.
  @see #setObjects(PreparedStatement,List)
	@see #setObject(PreparedStatement,int,Object)
*/
	public static int setObjects(PreparedStatement ps,int index,List bindVals)
		throws SQLException
	{	/*
			if(bindVals != null)//设置修改值
			for(int i = 0;i<bindVals.size();i++)
			{	setObject(ps,i+index,bindVals.get(i));
				//Debug.println("setObject(" + (i+index) + "," + bindVals[i] + ") is ok");
			}
	
		Object[] lo_bindVals = ListMethod.toArray(bindVals);
		
		return setObjects(ps,index,lo_bindVals);
		*/
		int i= -1;
	
		if(bindVals != null)//设置修改值
			for(i = 0;i<bindVals.size();i++)
			{	setObject(ps,i+index,bindVals.get(i));				
			}
			
		return i; 
	}
}

