package triones.frame;

import triones.bas.HashStyle;
import triones.bas.None;

import java.util.Map;

/**
* 原型字段（创建于 2003.10.10）.
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
 电  话:021-68672222-2013
</pre>
</DL>
* @author	吴勇庆
* @version	2003.10.10
*/
public class WYQFieldTag extends HashStyle implements FieldTag
{		
/**
* 构造方法（创建于 2003.10.10）.
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
创建于 2003.10.10.
</pre>
</DL>
*	@see #WYQFieldTag(String,Class)
*/
	WYQFieldTag()
	{	super();
	}
/**
* 构造方法（创建于 2003.10.10）.
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
创建于 2003.10.10.
</pre>
</DL>
* @param name 列名.
* @param type 列类型.
*/
	public WYQFieldTag(String name,Class type)
	{	super();
		init(name,type);
	}
	
/**
* 构造方法（创建于 2003.10.10）.
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
创建于 2003.10.10.
</pre>
</DL>
* @param name 列名.
*/
	public WYQFieldTag(String name)
	{	super();
		init(name,ANY);
	}


	
/**
* 初始化方法（创建于 2003.10.18）.
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
创建于 2003.10.18.
</pre>
</DL>
* @param name 字段名.
* @param type 字段类型.
*/
	void init(String name,Class type)
	{	setAttribute("name",name);
		setAttribute("type",type);
	}
	
/**
* 取列名（创建于 2003.10.10）.
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
创建于 2003.10.10.
</pre>
</DL>
* @return 列名.
*/
	
	public String getName()
	{	return (String)getAttribute("name");
	}
	

	
/**
* 取列类型（创建于 2003.10.10）.
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
创建于 2003.10.10.
</pre>
</DL>
* @return 列类型.
*/
	public Class getType()
	{	return (Class)getAttribute("type");
	}


	
/**
* 相等（创建于 2003.10.18）.
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
创建于 2003.10.18.
</pre>
</DL>
* @param o 对象.
* @return true代表相等,false代表不等.
*/
	public boolean equals(Object o)
	{	if(o instanceof FieldTag)
		{	FieldTag f = (FieldTag)o;
			return (getType().equals(f.getType())&&getName().equals(f.getName()));
		}
		
		return false;
	}	
}

