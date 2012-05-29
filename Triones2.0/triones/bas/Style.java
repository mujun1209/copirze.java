package triones.bas;

/**
* 属性集（创建于 2003.10.10）.
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
略
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

public interface Style
{ 

/**
* 取指定属性名对应的值（创建于 2003.10.10）.
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
* @param name 属性名.
* @return 属性值.
* @see #setAttribute(String,Object)
*/
	public Object getAttribute(String name);
/**
* 设置指定属性名对应的值（创建于 2003.10.10）.
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
* @param name 属性名.
* @param value 属性值.
* @see #getAttribute(String)
*/
  public void setAttribute(String name,Object value);
/**
* 取所有的属性名（创建于 2003.10.10）.
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
* @return 属性名数组.
*/
  public String[] attributeNames();
  
/**
* 是否包含指定属性名（创建于 2005.04.06）.
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
创建于 2005.04.06.
</pre>
</DL>
* @param name 属性名.
* @return true代表包含该属性名.
*/
  public boolean hasAttribute(String name);
}
