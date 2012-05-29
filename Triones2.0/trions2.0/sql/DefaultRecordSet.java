package trionesII.sql;

import java.util.ArrayList;
import java.lang.reflect.Field;
import java.util.List;
import java.sql.*;
import trionesII.util.*;
import trionesII.bas.Factory;

/**
* <img src="new.gif" width="28" height="11" border="0">记录集合（创建于 2003.03.12）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
记录集合用于描述二维数据视图，结构上分为行和列，每行由多个相同含义的列的值构成。
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
* @version	2003.03.12
*/
class DefaultRecordSet implements RecordSet
{	
	
/**
* 列名（创建于 2003.03.12）.
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
创建于 2003.03.12.
</pre>
</DL>
*/
	private String[] _Cols;
	
/**
* 行集合（创建于 2003.03.12）.
<DL>
<DT><B>说明：</B><DD>
<pre>
每行记录都采用Object[]存放.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
*/
	private List _Rows = new ArrayList();
	

/**
* 构造方法（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
创建一个空的记录集。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @param colNames 列名.
*/
	public DefaultRecordSet(String[] colNames)
	{	_Cols = new String[colNames.length];
		ArrayMethod.copy(colNames,colNames.length,_Cols);		
	}	


	
/**
* 将数据集当前行之后的的所有数据添加到记录集的末尾（创建于 2003.01.23）.
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
创建于 2003.01.23.
</pre>
</DL>
* @param rs 数据集.
	@return 记录集总行数.
* @exception SQLException 数据集读取错误.
* @see #addItemsByRows(ResultSet,int,int)
*/
	int addItems(ResultSet rs)
		throws SQLException
	{	int[] li_cols = ResultSetMethod.findColIDs(rs,_Cols);
		while(rs.next())
		{	_Rows.add(ResultSetMethod.currentRowToArray(rs,li_cols));
		}
		return _Rows.size();
	}
	

	
/**
* 将数据集当前行之后的指定范围的所有数据添加到记录集的末尾（创建于 2003.01.23）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
记录范围如果超过数据集的最大行数，则被忽略.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.01.23.
</pre>
</DL>
* @param rs 数据集.
* @param beginIndex 数据集当前行之后的的第几行，≥1.	
* @param rows 取记录个数，≥1.
	@return 记录集总行数.
* @exception SQLException 数据集读取错误.
* @see #addItemsByRows(ResultSet)
*/
	int addItems(ResultSet rs,int beginIndex,int rows)
		throws SQLException
	{ int[] li_cols = ResultSetMethod.findColIDs(rs,_Cols);
		int li_max,li_min,li_row = 0;
		
		li_min = Math.min(beginIndex,beginIndex + rows - 1);
		li_max = Math.max(beginIndex,beginIndex + rows - 1);
		
		while(rs.next())
		{ li_row ++;
		
			if(li_row < li_min) continue;
			if(li_row > li_max) break;
			
			_Rows.add(ResultSetMethod.currentRowToArray(rs,li_cols));
		}
		
		return _Rows.size();
	}
	

/**
* 添加一行记录（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
记录将被添加到最后一行;记录值个数如果小于列个数，则以null填充.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @param values 记录值数组，null代表空行.
* @return 当前行数.
*/
	public int addRow(Object[] values)
	{	_Rows.add(ArrayMethod.copy(values,_Cols.length));
		
		return _Rows.size();
	}
	
	
/**
* 以数组形式取列名（创建于 2003.03.12）.
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
创建于 2003.03.12.
</pre>
</DL>
* @return 列名数组.
*/
	public String[] getColNames()
	{	return _Cols;
	}
	

	
/**
* 取列名（创建于 2003.04.09）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
列标识的取值范围: 1..colCount()
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.04.09.
</pre>
</DL>
* @param colID 列标识.
* @return 列名.
*/
	public String getColName(int colID)
	{	return _Cols[colID - 1];
	}

/**
* 总行数（创建于 2003.03.12）.
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
创建于 2003.03.12.
</pre>
</DL>
* @return 总行数.
*/
	public int rowCount()
	{	return _Rows.size();
	}
	

	
/**
* 列个数（创建于 2003.03.12）.
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
创建于 2003.03.12.
</pre>
</DL>
* @return 列个数.
*/
	public int colCount()
	{	return _Cols.length;
	}
	
	
/**
* 以数组的形式取指定行记录（创建于 2003.03.12）.
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
创建于 2003.03.12.
</pre>
</DL>
* @param row 行编号，取值范围1..rowCount().
* @return 值数组.
	@exception IndexOutOfBoundsException 行越界.
*/
	Object[] itemsByRow(int row)
	{	return (Object[])_Rows.get(row - 1);
	}

	
/**
* 取指定行的值数组（创建于 2003.01.27）.
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
创建于 2003.01.27.
</pre>
</DL>
* @param row 行编号.
* @return 行的值数组，按默认列顺序.
*/
	public Object[] getItemsByRow(int row)
	{	return ArrayMethod.copy((Object[])itemsByRow(row),colCount());
	}
/**
* 取记录集指定行、列单元的值（创建于 2003.01.24）.
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
创建于 2003.01.24.
</pre>
</DL>
* @param row 行编号，取值范围1..rowCount().
* @param colId 列编号,取值范围1..colCount().
* @return 单元值.
* @see #getItem(int,String)
	@exception IndexOutOfBoundsException 行或列越界.
*/
	public Object getItem(int row,int colId)
	{	return itemsByRow(row)[colId - 1];
	}
	
/**
* 取记录集指定行、列单元的值（创建于 2003.01.24）.
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
创建于 2003.01.24.
</pre>
</DL>
* @param row 行编号，取值范围1..rowCount().
* @param colName 列名称.
* @return 单元值.
* @see #getItem(int,int)
	@exception IndexOutOfBoundsException 行或列越界.
*/
	public Object getItem(int row,String colName)
	{	return getItem(row,getColID(colName));
	}
	

	
/**
* 根据列名取列编号（创建于 2003.01.24）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
列编号从1..colCount()
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.01.24.
</pre>
</DL>
* @param colName 列名称.
* @return 列编号，0代表列编号不存在.
*/
	public int getColID(String colName)
	{	return ArrayMethod.findItemIgnoreCase(_Cols,colName) + 1;
	}

	
/**
* 自动转换列名调整（创建于 2003.01.24）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
如果名称长度超过命名长度，则取到命名长度为止.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.01.24.
</pre>
</DL>
* @param colName 原始列名.
	@return 调整后的列名.
* @see #toList(Class,boolean)
*/
	private static String ruleColName(String colName)
	{	return (colName.length() > DBMS.NAMING_LENGTH?colName.substring(0,DBMS.NAMING_LENGTH):colName);
	}


/**
* 将记录集数据按设定的基因工厂创建基因对象列表（创建于 2003.01.27）.
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
创建于 2003.01.27.
</pre>
</DL>
	@param f 基因工厂.
* @return 基因对象列表.
* @exception IllegalAccessException 对应类的属性值必须初始化.
* @exception InstantiationException 对应类不存在，或者是一个抽象类，或者构造函数全都有参数.
*/
	public List toList(Factory f)
		throws IllegalAccessException,InstantiationException
	{	List lList = new ArrayList(rowCount());
		
		for(int i=1;i<=rowCount();i++)
		{	lList.add(f.newInstance(_Cols,itemsByRow(i)));
		}	
		
		return lList;
	}
/**
* 将指定行数据按设定的基因工厂创建基因对象（创建于 2003.01.27）.
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
创建于 2003.01.27.
</pre>
</DL>
	@param row 行号.
	@param f 基因工厂.
* @return 基因对象.
* @exception IllegalAccessException 对应类的属性值必须初始化.
* @exception InstantiationException 对应类不存在，或者是一个抽象类，或者构造函数全都有参数.
*/	
	public Object toObject(int row,Factory f)
		throws IllegalAccessException,InstantiationException
	{	return f.newInstance(_Cols,itemsByRow(row));
	}

}

