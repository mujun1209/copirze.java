package trionesII.util;

//import triones.xml.XMLComment;
//import org.w3c.dom.Element;
import java.sql.*;
import java.lang.reflect.*;
import java.util.*;

/**
* ResultSet函数库（创建于 2003.03.05）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
提供对ResultSet操作的静态方法.
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
* @version	2003.03.05
*/
public class ResultSetMethod
{	
/**
* 私有构造方法（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
用于定义抽象类.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
*/
	private ResultSetMethod(){}


/**
* 取列标识（创建于 2003.03.05）.
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
创建于 2003.03.05.
</pre>
</DL>
	@param rs	结果集
	@param colName 列名
	@return 列标识,如果不存在则返回0.
	@exception SQLException 数据库错误.
*/
	public static int findColID(ResultSet rs,String colName)
		throws SQLException
	{	ResultSetMetaData lRSMD = rs.getMetaData();
		
		for(int i=1;i<=lRSMD.getColumnCount();i++)
		{	if(colName.equalsIgnoreCase(lRSMD.getColumnName(i))) return i;
		}
		
		return 0;
		/*int li_colIndex;
		try
		{	li_colIndex = rs.findColumn(colName);
		}
		catch(SQLException e)
		{	li_colIndex = -1;
		}
		return li_colIndex;*/
		
		/*String[] ls_colNames = getColNames(rs);
				
		return ArrayMethod.findItemIgnoreCase(ls_colNames,colName) + 1;
		*/
	}

	
/**
* 在当前行中，根据列名及列名的类型取值（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
与ResultSet.getObject(String)类似。
可自动将基本类型的值转换为对应的默认对象实例。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
* @param 结果集.
* @param 列名.
* @param 列的类型.
* @return 当前行该列的值.
* @exception SQLException 数据库错误.
*/
	public static Object getObject(ResultSet rs,String col,Class colType)
		throws SQLException
	{	if(colType.equals(double.class)) return new Double(rs.getDouble(col));
		else if(colType.equals(int.class)) return new Integer(rs.getInt(col));		
		else if(colType.equals(float.class)) return new Float(rs.getFloat(col));
		else if(colType.equals(long.class)) return new Long(rs.getLong(col));
		else if(colType.equals(short.class)) return new Short(rs.getShort(col));	
		else 
		{	Object lObj = rs.getObject(col);
			
			if(lObj!=null)
			{	if(colType.equals(Double.class)) return new Double(lObj.toString());
				else if(colType.equals(Integer.class)) return new Integer(lObj.toString());		
				else if(colType.equals(Float.class)) return new Float(lObj.toString());
				else if(colType.equals(Long.class)) return new Long(lObj.toString());
				else if(colType.equals(Short.class)) return new Short(lObj.toString());						
				//else if(colType.equals(java.util.Date.class)) return new Date(
			}
			
			return lObj;
		}
	}
	
/**
* 在当前行中，根据列名及列名的类型取值（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
与ResultSet.getObject(int)类似。
可自动将基本类型的值转换为对应的默认对象实例。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
* @param 结果集.
* @param 列标识.
* @param 列的类型.
* @return 当前行该列的值.
* @exception SQLException 数据库错误.
*/
public static Object getObject(ResultSet rs,int col,Class colType)
		throws SQLException
	{	if(colType.equals(double.class)) return new Double(rs.getDouble(col));
		else if(colType.equals(int.class)) return new Integer(rs.getInt(col));		
		else if(colType.equals(float.class)) return new Float(rs.getFloat(col));
		else if(colType.equals(long.class)) return new Long(rs.getLong(col));
		else if(colType.equals(short.class)) return new Short(rs.getShort(col));	
		else 
		{	Object lObj = rs.getObject(col);
			
			if(lObj!=null)
			{	if(colType.equals(Double.class)) return new Double(lObj.toString());
				else if(colType.equals(Integer.class)) return new Integer(lObj.toString());		
				else if(colType.equals(Float.class)) return new Float(lObj.toString());
				else if(colType.equals(Long.class)) return new Long(lObj.toString());
				else if(colType.equals(Short.class)) return new Short(lObj.toString());						
				//else if(colType.equals(java.util.Date.class)) return new Date(
			}
			
			return lObj;
		}
	}
	

/**
* 获取数据集的列个数（创建于 2003.03.05）.
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
创建于 2003.03.05.
</pre>
</DL>
* @param rs 数据集.
* @return 列个数.
* @exception SQLException 数据库错误.
*/
	public static int colCount(ResultSet rs)
		throws SQLException
	{	return rs.getMetaData().getColumnCount();
	}
	
	/**
	 * 取记录集的行数（创建于 2003.03.05）.
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
	创建于 2003.03.05.
	</pre>
	</DL>
* @param rs 记录集.
* @return 行数.
* @exception SQLException 数据库错误或者ResultSet类型为TYPE_FORWARD_ONLY.
*/
	static int rowCount(ResultSet rs)
	throws SQLException
	{	//return rs.last()?rs.getRow():0;
		return rs.getFetchSize();
	}
	

	


	
/**
* 以数组形式取当前行指定列的值（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
需保证列名的有效性.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
* @param rs 数据集.
* @param cols 列名数组.
* @return 与列名数组对应的值数组.
* @exception SQLException 数据库错误.
	@see #currentRowToArray(ResultSet,int[])
*/
	public static Object[] currentRowToArray(ResultSet rs,String[] cols)
		throws SQLException
	{	Object[] lObjs = new Object[cols.length];
		
		for(int i=0;i<cols.length;i++)
		{	lObjs[i] = rs.getObject(cols[i]);
		}
		
		return lObjs;
	}
	

/**
* 以数组形式取当前行指定列的值（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
小于等于0的列序号对应的值为null.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
* @param rs 数据集.
* @param cols 列标识数组.
* @return 对应于列的值数组.
* @exception SQLException 数据库错误.
	@see #currentRowToArray(ResultSet,String[])
*/
	public static Object[] currentRowToArray(ResultSet rs,int[] cols)
		throws SQLException
	{	Object[] lObjs = new Object[cols.length];
		
		for(int i=0;i<cols.length;i++)
		{	
			lObjs[i] = (cols[i]>0?rs.getObject(cols[i]):null);
		}
		
		return lObjs;
	}	


	/**
	 * 取数据集的列名清单（创建于 2003.03.05）.
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
	创建于 2003.03.05.
	</pre>
	</DL>
* @param rs 数据集.
* @return 列名数组.
* @exception SQLException 数据库错误.
*/
	public static String[] getColNames(ResultSet rs)
		throws SQLException
	{	ResultSetMetaData lRSMD = rs.getMetaData();
		int li_max = lRSMD.getColumnCount();
		String[] ls_cols = new String[li_max];
		
		for(int i=0;i<li_max;i++)
		{	ls_cols[i] = lRSMD.getColumnName(i+1);
		}
		
		return ls_cols;
	}

	
	/**
	 * 取数据集的列的类型名清单（创建于 2003.03.05）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	列类型名中包含包名.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.05.
	</pre>
	</DL>
* @param rs 数据集.
* @return 列类型名数组.
* @exception SQLException 数据库错误.
*/	
	public static String[] getColTypeNames(ResultSet rs)
		throws SQLException
	{	ResultSetMetaData lRSMD = rs.getMetaData();
		int li_max = lRSMD.getColumnCount();
		String[] ls_cols = new String[li_max];
		
		for(int i=0;i<li_max;i++)
		{	ls_cols[i] = lRSMD.getColumnTypeName(i+1);
		}
		
		return ls_cols;
	}
	

	
/**
* 取列名数组对应的列标识（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
标识为0的表示列名不存在。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
* @param rs 数据集.
* @param colNames 列名数组.
* @return 对应列名的序号数组.
	@exception SQLException 数据库错误.
*/
	public static int[] findColIDs(ResultSet rs,String[] colNames)
		throws SQLException
	{	int[] li_colIds = new int[colNames.length];
	
		for(int i=0;i<colNames.length;i++)
		{	li_colIds[i] = findColID(rs,colNames[i]);
		}
		
		return li_colIds;
	}
	

}

