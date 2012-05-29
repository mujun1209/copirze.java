package trionesII.sql;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

import trionesII.util.StringMethod;
import trionesII.util.ArrayMethod;


/**
 * 结构可定义的控制指令集（创建于 2003.03.18）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
开发人员可以根据自己的需要构造指令集的基本数据结构。
</pre>
<DT><B>使用说明：</B><DD>
<pre>
略
</pre>
<DT><B>注意事项：</B><DD>
<pre>
略
</pre>
<DT><B>展望未来：</B><DD>
<pre>
1.增加排序的强化版	
2.增加对group的强化版
3.增加对条件关系的强化版
4.增加对XML的支持
</pre>
<DT><B>联系方式：</B><DD>
<pre>
email：wyq@triok.com
电  话:021-68672222-2013
</pre>
</DL>
*@author	吴勇庆
*@version 2003.03.18
*/
public class ActiveOperator extends AbstractOperator implements Operator,Serializable,Cloneable
{
/**
	 * 构造方法（创建于 2003.03.18）.
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
	public ActiveOperator()
	{ super();
	}

/**
* 增加列（创建于 2003.03.21）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
采用本方法，数据库字段名与列名相同.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.21.
</pre>
</DL>
* @param name 列名.
* @param type 列类型.
* @param val 列值.
	@see #addField(String,Class)
	@see #addField(String,Object)
	@see #addField(String,String,Class,Object)
	@see #addField(String)
*/
	public void addField(String name,Class type,Object val)
	{	addField(name,name,type,val);		
	}
	

	
/**
* 增加列（创建于 2003.03.11）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
采用本方法，数据库字段名与列名相同.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.11.
</pre>
</DL>
* @param name 列名.
* @param type 列类型.
	@see #addField(String,Class,Object)
	@see #addField(String,Object)
	@see #addField(String,String,Class,Object)
	@see #addField(String)
*/
	public void addField(String name,Class type)
	{	addField(name,name,type,null);
	}
/**
* 增加列（创建于 2003.03.11）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
采用本方法，数据库字段名与列名相同.
列类型将根据第一次赋值确定.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.11.
</pre>
</DL>
* @param name 列名.
	@see #addField(String,Class,Object)
	@see #addField(String,Object)
	@see #addField(String,Class)
	@see #addField(String,String,Class,Object)
*/
	public void addField(String name)
	{	addField(name,name,null,null);
	}
	
/**
* 增加列（创建于 2003.03.11）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
列的类型根据值的类型确定。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.11.
</pre>
</DL>
* @param name 列名.
* @param val 列值.
	@see #addField(String,Class,Object)
	@see #addField(String,Class)
	@see #addField(String,String,Class,Object)
	@see #addField(String)
*/
	public void addField(String name,Object val)
	{	Class lClass = (val==null?String.class:val.getClass());
		addField(name,name,lClass,val);
	}
/**
* 增加列（创建于 2003.03.21）.
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
创建于 2003.03.21.
</pre>
</DL>
* @param name 列名.
* @param dbName 数据库字段名.
* @param type 列类型.
* @param val 列值.
	@return 列标识.
*/
	public int addField(String name,String dbName,Class type,Object val)
	{	return super.addField(name,dbName,type,val);
	}
/**
* 删除列（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
使用本方法可能影响原来的列标识顺序。
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
* @param fieldName 列名.
	@NoSuchElementException 不存在的列或所有的列均已清空.
* @see #removeField(int)
*/
	public void removeField(String name)
	{	super.removeField(name);
	}
	
/**
* 克隆（创建于 2003.03.17）.
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
创建于 2003.03.17.
</pre>
</DL>
* @return 克隆对象.
*/
	protected Object clone()
	{	ActiveOperator a = new ActiveOperator();
		a.setName(getName());
		a.setDBName(getDBName());
		a.setMultiple(isMultiple());
		a.addFields(getFields());
		
		return a;
	}
	

}

