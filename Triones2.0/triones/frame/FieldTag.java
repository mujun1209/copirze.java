package triones.frame;

import triones.bas.None;
import triones.bas.Style;


/**
* 字段描述接口（创建于 2005.04.05）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
用于描述字段的组成。
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
 电 话：021-65804142
</pre>
</DL>
* @author	吴勇庆
* @version	2005.04.05
*/
public interface FieldTag extends Style
{ 
/**
* 取字段名称（创建于 2003.10.23）.
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
创建于 2003.10.23.
</pre>
</DL>
* @return 字段名称.
*/
	public String getName();

	
/**
* 取字段类型（创建于 2003.10.23）.
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
创建于 2003.10.23.
</pre>
</DL>
* @return 字段类型.
*/
  
	public Class getType();
	
/**
* 任意字段类型（创建于 2003.10.21）.
<DL>
<DT><B>说明：</B><DD>
<pre>
任意字段类型不做类型校验
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.10.21.
</pre>
</DL>
*/
	public static final Class ANY = None.class;
}
