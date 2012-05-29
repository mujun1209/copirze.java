package trionesII.sql;

import java.util.List;
import java.util.ArrayList;
/**
* 结构可变的数据对象核心类（创建于 2003.03.13）.
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
public class AbstractDataObject implements DataObject 
{	
	/**
* 查询语句（创建于 2003.03.13）.
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
创建于 2003.03.13.
</pre>
</DL>
*/
	private String _SQLSelect = "";
	
/**
* 关键字（创建于 2003.03.13）.
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
创建于 2003.03.13.
</pre>
</DL>
*/
	private String _PrimaryKey = "";
/**
* 只读列（创建于 2003.03.13）.
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
创建于 2003.03.13.
</pre>
</DL>
*/
	private String _ReadOnlyKey = "";
/**
* 表名（创建于 2003.03.13）.
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
创建于 2003.03.13.
</pre>
</DL>
*/
	private String _Table = "";
	

/**
* 取当前定义的数据查询语句（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
返回空字符串代表没有特定的SQL语句，由使用者自己创建。
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
* @return 查询语句.
*/
	public String getSQLSelect()
	{	return _SQLSelect;
	}
	
/**
* 设置数据查询语句（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
返回空字符串代表没有特定的SQL语句，由使用者自己创建。
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
* @param sql 查询语句.
*/
	public void setSQLSelect(String sql)
	{	_SQLSelect = sql;
	}
	
/**
* 取数据结构的关键字（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
关键字用于指明与记录唯一性相关的字段。
在执行UPDATE、DELETE操作时将根据这些字段进行条件匹配。
关键字如有多个字段组成，则字段名之间用","分隔。
空字符串代表没有字段.
*代表所有字段.
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
* @return 关键字.
*/
	public String getPrimaryKey()
	{	return _PrimaryKey;
	}
/**
* 设置数据结构的关键字（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
关键字用于指明与记录唯一性相关的字段。
在执行UPDATE、DELETE操作时将根据这些字段进行条件匹配。
关键字如有多个字段组成，则字段名之间用","分隔。
空字符串代表没有字段.
*代表所有字段.
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
* @param key 关键字.
*/
	public void setPrimaryKey(String key)
	{	_PrimaryKey = key;
	}
/**
* 取数据结构中的只读字段（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
只读字段在INSERT、UPDATE操作中将被忽略。
本方法返回值为""，使用者可根据需要替换.
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
* @return 只读字段.
*/		
	public String getReadOnlyKey()
	{	return _ReadOnlyKey;
	}
/**
* 设置数据结构中的只读字段（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
只读字段在INSERT、UPDATE操作中将被忽略。
本方法返回值为""，使用者可根据需要替换.
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
* @param key 只读字段.
*/	
	public void setReadOnlyKey(String key)
	{	_ReadOnlyKey = key;
	}
/**
* 取数据结构对应的库表（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
如有多个库表组成，则表名之间用","分隔。
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
* @return 库表.
*/	
	public String getTable()
	{	return _Table;
	}
/**
* 取数据结构对应的库表（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
如有多个库表组成，则表名之间用","分隔。
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
*/	
	public void setTable(String table)
	{ _Table = table;
	}
/**
* 列名（创建于 2003.03.13）.
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
创建于 2003.03.13.
</pre>
</DL>
*/
	private List _ColNames = new ArrayList();
/**
* 列类型（创建于 2003.03.13）.
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
创建于 2003.03.13.
</pre>
</DL>
*/
	private List _ColTypes = new ArrayList();
/**
* 列初始值（创建于 2003.03.13）.
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
创建于 2003.03.13.
</pre>
</DL>
*/
	private List _ColValues = new ArrayList();

	
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
	protected AbstractDataObject(){}

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
* @param colValue 默认初始值.
* @return 列标识.
*/
	public int addColumn(String colName,Class colType,Object colValue)
	{	if(colValue!=null&&!colType.equals(colValue.getClass()))
			throw new IllegalArgumentException("colValue's type is dismatched with colType");
			
		_ColNames.add(colName);
		_ColTypes.add(colType);
		_ColValues.add(colValue);
		
		return _ColNames.size();//与列标识相等
	}

/**
* 清除列（创建于 2003.03.13）.
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
*/
	public void removeColumn(String colName)
	{	int li_index = _ColNames.indexOf(colName);
	
		_ColNames.remove(li_index);
		_ColTypes.remove(li_index);
	}
	
/**
* 字段个数（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
字段标识的取值范围：1..colCount()
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
* @return 字段个数.
*/
	public int colCount()
	{	return _ColNames.size();
	}

/**
* 取字段名（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
字段标识的取值范围：1..colCount()
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
* @param id 字段标识.
* @return 字段名.
* @see #getColType(int)
*/	
	public String getColName(int id)
	{	return (String)(_ColNames.get(id - 1));
	}
/**
* 取字段类型（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
字段标识的取值范围：1..colCount()
本方法的返回值为null代表类型不确定，使用者可根据需要覆盖.
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
* @param id 字段标识.
* @return 字段类型.
* @see #getColName(int)
*/
	public Class getColType(int id)
	{	return (Class)(_ColTypes.get(id - 1));
	}
	
/**
* 取字段初始值（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
字段标识的取值范围：1..colCount()
null代表没有初始值.
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
* @param id 字段标识.
* @return 字段默认初始值..
*/
	public Object getColValue(int id)
	{	return _ColValues.get(id - 1);
	}
/**
* 设置列的默认值（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
如果列原来类型为null，则用默认值的类型替代.
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
* @param colValue 列的默认值.
	@throws IllegalArgumentException 默认值的类型与列类型不匹配.
*/
	public void setColValue(String colName,Object colValue)
	{	int li_index = _ColNames.indexOf(colName);
				
		if(colValue!=null)
		{	Class lc_old = (Class)(_ColTypes.get(li_index));
			Class lc_new = colValue.getClass();
			
			if(lc_old==null)
				_ColTypes.set(li_index,lc_new);
			else if(!lc_old.equals(lc_new))
				throw new IllegalArgumentException("colValue's type is dismatched with colType");
		}	
		
		_ColValues.set(li_index,colValue);
	}
/**
* 设置列的类型（创建于 2003.03.13）.
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
* @param colValue 列的类型.
*/
	public void setColType(String colName,Class colType)
	{	int li_index = _ColNames.indexOf(colName);
		_ColTypes.set(li_index,colType);
	}
	
}

