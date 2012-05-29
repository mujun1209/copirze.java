package triones.frame;

import triones.bas.Style;

/**
* 记录结构接口（创建于 2005.04.05）.
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
 电 话：021-65804142
</pre>
</DL>
* @author	吴勇庆
* @version	2005.04.05
*/
public interface RecordTag extends Style
{ 
/**
* 取字段个数（创建于 2005.04.05）.
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
* @return 记录包含的字段个数.
*/
  public int fieldCount();

/**
* 取字段标识（创建于 2005.04.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
字段标识范围：≥1
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
* @return 字段标识，返回值≥1成功.
*/
	public int getFieldID(String fieldName); 
  
/**
* 取字段名称（创建于 2005.04.05）.
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
* @param fieldID 字段标识.
* @return 字段名称.
*/
	public String getFieldName(int fieldID);

  
/**
* 取字段类型（创建于 2005.04.05）.
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
* @param fieldID 字段标识.
* @return 字段类型.
* @see #getFieldType(String)
*/
  public Class getFieldType(int fieldID);

  
/**
* 取字段类型（创建于 2005.04.05）.
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
* @return 字段类型.
* @see #getFieldType(int)
*/
  public Class getFieldType(String fieldName);

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
* @param fieldID 字段标识.
* @param property 字段属性.
* @return 字段属性值.
* @see #getFieldAttribute(String,String)
*/
	public Object getFieldAttribute(int fieldID,String property);

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
* @param fieldName 字段名称.
* @param property 字段属性.
* @return 字段属性值..
* @see #getFieldAttribute(int,String)
*/
  public Object getFieldAttribute(String fieldName,String property);
/**
* 取记录名称（创建于 2005.04.05）.
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
* @return 记录名称.
*/
  public String getName();
  
/**
* 设置字段属性（创建于 2005.04.06）.
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
* @param fieldID 字段标识.
* @param property 属性名.
* @param value 属性值.
* @see #setFieldAttribute(String,String,Object)
*/
  public void setFieldAttribute(int fieldID,String property,Object value);
/**
* 设置字段属性（创建于 2005.04.06）.
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
* @param fieldName 字段名.
* @param property 属性名.
* @param value 属性值.
* @see #setFieldAttribute(int,String,Object)
*/
  public void setFieldAttribute(String fieldName,String property,Object value);
}
