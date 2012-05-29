package trionesII.sql;

import trionesII.Debug;
/**
* SQL数据对象类（创建于 2003.03.13）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
可根据查询SQL生成的数据对象类.
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
class DefaultDataObject extends AbstractDataObject implements DataObject
{	

/**
* 列名数组（创建于 2003.03.13）.
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
	private String[] _ColNames;
	
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
* @param sql 查询语句.
* @param primaryKey 关键字.
*/
	public DefaultDataObject(String sql,String primaryKey)
	{	setTable(Syntax.getSQLSelectTable(sql));
		_ColNames = Syntax.getSQLSelectFields(sql);
		//Debug.println(_ColNames);
		setSQLSelect(sql);
		setPrimaryKey(primaryKey);		
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
	{	return _ColNames.length;
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
	{	return _ColNames[id - 1];
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
	{	return null;
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
* @return 字段默认初始值.
*/
	public Object getColValue(int id)
	{	return null;
	}
	

}

