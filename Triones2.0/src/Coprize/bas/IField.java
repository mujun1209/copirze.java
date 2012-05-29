package Coprize.bas;
                       /* \\|///
                       \\  - -  //
                        (  @ @  )
**********************oOOo-(_)-oOOo************
*                                             *
*  Publish Date: 2012.01.30   Wu yongqing    *
*                                          *
*  We work because we wake               *
*                             Oooo        *
************************oooO**(   )*********
                       (   )   ) /
                        \ (   (_/
                         */


/**
* 数据字段对象接口（创建于 2012.01.30）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
本对象主要用于定义数据实体包含的属性（或字段）类型
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
 email：wuyqb@163.com 
 电 话：021-65804142
</pre>
</DL>
* @author	吴勇庆
* @version	2012.01.30
*/

public interface IField extends IExtend
{
	

	
/**
* 获取字段标题（创建于 2012.01.30）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
一般用于在界面中显示字段的含义
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2012.01.30 - 吴勇庆.
</pre>
</DL>
* @return 字段标题.
* @see #SetCaption(String)
*/
	String GetCaption();

	
/**
* 设置字段标题（创建于 2012.01.30）.
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
创建于 2012.01.30 - 吴勇庆.
</pre>
</DL>
* @param caption 字段标题.
* @return 大于0代表设置成功.
* @see #GetCaption()
*/
	int SetCaption(String caption);
	
/**
* 获取字段名称（创建于 2012.01.30）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
与字段标题的区别是一般不用于界面显示
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2012.01.30 - 吴勇庆.
</pre>
</DL>
* @return 字段名称.
* @see #SetName(String)
*/
	String GetName();

	
/**
* 设置字段名称（创建于 2012.01.30）.
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
创建于 2012.01.30 - 吴勇庆.
</pre>
</DL>
* @param name 字段名称.
* @return 大于0代表设置成功.
* @see #GetName()
*/
	int SetName(String name);
	

	
/**
* 获取字段类型（创建于 2012.01.30）.
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
创建于 2012.01.30 - 吴勇庆.
</pre>
</DL>
* @return 字段类型.
* @see #SetType(String)
*/
	String GetType();

	
/**
* 设置字段类型（创建于 2012.01.30）.
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
创建于 2012.01.30 - 吴勇庆.
</pre>
</DL>
* @param dataType 字段类型.
* @return 大于0代表设置成功.
* @see #GetType()
*/
	int SetType(String dataType);
	

	
/**
* 获取字段的值（创建于 2012.01.30）.
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
创建于 2012.01.30 - 吴勇庆.
</pre>
</DL>
* @return 字段值.
* @see #SetValue(Object)
*/
	Object GetValue();

	
/**
* 设置字段的值（创建于 2012.01.30）.
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
创建于 2012.01.30 - 吴勇庆.
</pre>
</DL>
* @param val 新的字段值.
* @return 大于0代表设置成功.
* @see #GetValue()
*/
	int SetValue(Object val);
}
