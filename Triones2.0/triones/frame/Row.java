package triones.frame;

import triones.bas.None;
/**
* 原型行（创建于 2003.10.11）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
针对二维表中以行为记录的特点，设计本类。
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
* @version	2003.10.11
*/
public interface Row extends SheetTag
{	

/**
* 无值（创建于 2003.10.21）.
<DL>
<DT><B>说明：</B><DD>
<pre>
用于判断字段是否赋值,新增行时使用
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
	public static final Object NONE = new None();
/**
* 取列值（创建于 2003.10.11）.
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
创建于 2003.10.11.
</pre>
</DL>
* @param colID 列编号.
* @return 列值.
* @see #setColValue(int,Object)
*/
	public Object getColValue(int colID);

/**
* 给列赋值（创建于 2003.10.16）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
需对类型进行校验。
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
* @param colID 列编号.
* @param value 列值.
* @see #getColValue(int)
* @return -1代表失败.
*/
	public int setColValue(int colID,Object value);

/**
* 取字段属性（创建于 2005.04.05）.
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
创建于 2005.04.05.
</pre>
</DL>
* @param colID 列标识.
* @param property 列属性名.
* @return 列属性值.
* @see #setColAttribute(int,String,Object)
*/
	public Object getColAttribute(int colID,String property);

/**
* 取字段属性（创建于 2005.04.05）.
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
创建于 2005.04.05.
</pre>
</DL>
* @param colID 列标识.
* @param property 列属性名.
* @param value 列属性值.
* @see #getColAttribute(int,String)
*/
  public int setColAttribute(int colID,String property,Object value);
}

