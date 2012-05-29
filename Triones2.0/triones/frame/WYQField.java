package triones.frame;

import triones.util.DataTypeMethod;

/**
* 字段空间原型（创建于 2003.10.16）.
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
* @version	2003.10.16
*/
public class WYQField extends WYQFieldTag implements Field
{	

/**
* 构造方法（创建于 2003.10.16）.
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
创建于 2003.10.16.
</pre>
</DL>
* @see #WYQField(String,Class,Object)
*/
	WYQField()
	{	super();
	}
	
	
/**
* 构造方法（创建于 2003.10.16）.
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
创建于 2003.10.16.
</pre>
</DL>
* @param name 字段名称.
* @param type 字段类型.
* @param val 字段值.
* @see #WYQField(String,Object)
*/
	public WYQField(String name,Class type,Object val)
	{	super();
		init(name,type,val);
	}
	
/**
* 构造方法（创建于 2003.10.16）.
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
创建于 2003.10.16.
</pre>
</DL>
* @param name 字段名称.
* @param val 字段值.
* @see #WYQField(String,Class,Object)
*/
  public WYQField(String name,Object val)
  { super();
    init(name,val.getClass(),val);    
  }
  
/**
* 初始化（创建于 2003.10.18）.
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
* @param name 字段名称.
* @param type 字段类型.
* @param val 字段值.
*/
	void init(String name,Class type,Object val)
	{	init(name,type);
		setAny(val);
	}
	

/**
* 设字段值（创建于 2003.10.16）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
不对类型进行校验
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.10.16.
</pre>
</DL>
* @param val 字段值.
* @see #setValue(Object)
*/
  public void setAny(Object val)
  { setAttribute("Value",val);
  }
  
/**
* 取字段值（创建于 2003.10.16）.
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
创建于 2003.10.16.
</pre>
</DL>
* @return 字段值.
* @see #setValue(Object)
*/
	public Object getValue()
	{	return getAttribute("Value");
	}

/**
* 设字段值（创建于 2003.10.16）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
对类型进行校验
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.10.16.
</pre>
</DL>
* @param val 字段值.
* @see #getValue()
* @see #setAny(Object)
*/
	public void setValue(Object val)
	{	if(val!=null&&!getType().equals(ANY))
		{	if(!DataTypeMethod.equals(getType(),val.getClass(),true))
				throw new ClassCastException(getName() + "'s type should be " + getType() + " , and the " + val + " 's type is " + val.getClass() + " , they are mismatched!");
		}
		setAny(val);
	}
  
}

