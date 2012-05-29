package trionesII.sql;

import java.util.List;
import java.util.ArrayList;
/**
* 结构可变的数据对象（创建于 2003.03.13）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
提供了最简单的实现.
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
* @version	2003.03.13
*/
public class ActiveDataObject extends AbstractDataObject implements DataObject 
{	
/**
* 构造方法（创建于 2003.03.13）.
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
创建于 2003.03.13.
</pre>
</DL>
* @param table 库表.
* @param primaryKey 关键字.
*/
	public ActiveDataObject(DataObject obj)
	{	setTable(obj.getTable());
		setPrimaryKey(obj.getPrimaryKey());
		setReadOnlyKey(obj.getReadOnlyKey());
		setSQLSelect(obj.getSQLSelect());
		
		for(int i=1;i<=obj.colCount();i++)
		{	addColumn(obj.getColName(i),obj.getColType(i),obj.getColValue(i));
		}
	}
/**
* 构造方法（创建于 2003.03.13）.
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
创建于 2003.03.13.
</pre>
</DL>
* @param obj 数据对象.
*/
	public ActiveDataObject(String table,String primaryKey)
	{	setTable(table);
		setPrimaryKey(primaryKey);
	}

/**
* 增加列（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
列类型根据默认初始值的类型设定.
如果初始值为null，则列类型为任意.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.13.
</pre>
</DL>
* @param colName 列名.
* @param colValue 默认初始值.
* @return 列标识.
* @see #addColumn(String,Class,Object)
*/
	public int addColumn(String colName,Object colValue)
	{	Class lc_colType = (colValue==null?null:colValue.getClass());
	
		return addColumn(colName,lc_colType,colValue);
	}
	
/**
* 增加列（创建于 2003.03.13）.
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
创建于 2003.03.13.
</pre>
</DL>
* @param colName 列名.
* @param colType 列类型.
* @return 列标识.
* @see #addColumn(String)
*/
	public int addColumn(String colName,Class colType)
	{	return addColumn(colName,colType,null);
	}
/**
* 增加列（创建于 2003.03.13）.
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
创建于 2003.03.13.
</pre>
</DL>
* @param colName 列名.
* @param colType 列类型.
* @return 列标识.
* @see #addColumn(String,Class)
*/
	public int addColumn(String colName)
	{	return addColumn(colName,null);
	}

}

