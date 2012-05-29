package triones.frame;

import triones.bas.None;
/**
* 记录空间接口（创建于 2003.10.11）.
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
public interface Record extends RecordTag
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
* 取字段当前的值（创建于 2003.10.11）.
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
* @param fieldID 字段编号≥1.
* @return 字段文本形式的值.
* @see #getFieldValue(String)
*/
	public Object getFieldValue(int fieldID);


  
/**
* 给字段赋值（创建于 2003.10.16）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
字段值需与字段类型匹配.
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
* @param fieldID 字段编号≥1.
* @param value 字段值.
* @see #setFieldValue(String,Object)
* @return -1代表失败.
*/
	public int setFieldValue(int fieldID,Object value);


/**
* 取字段的值（创建于 2005.04.05）.
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
* @param fieldName 字段名称.
* @return 字段值.
* @see #getFieldValue(int)
*/
  public Object getFieldValue(String fieldName);

  
/**
* 给字段赋值（创建于 2005.04.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
字段值需与字段类型匹配.
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
* @param fieldName 字段名称.
* @param value 字段值.
* @see #setFieldValue(int,Object)
* @return -1代表失败.
*/
  public int setFieldValue(String fieldName,Object value);

}

