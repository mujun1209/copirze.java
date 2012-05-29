package triones.frame;

/**
* <img src="important.gif" width="35" height="25" border="0">视图空间接口（创建于 2003.03.12）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
视图空间接口用于描述二维数据视图，结构上分为行和列，每行由多个相同含义的列的值构成。
视图空间接口与java.sql.ResultSet有许多相似之处，不同之处在于，视图空间接口不受限于数据库资源。
即当数据库资源释放后，视图空间接口仍能提供数据结果。
</pre>
<DT><B>使用说明：</B><DD>
<pre>
详见各方法使用说明。
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
* @version	2003.03.12
*/
public interface RecordSet extends Sheet,RecordSetTag
{	

/**
* 取单元值（创建于 2005.04.05）.
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
* @param row 行编号.
* @param colName 列名称.
* @return 单元值.
* @see #getItemValue(int,int)
* @see #setItemValue(int,String,Object)
*/
  public Object getItemValue(int row,String colName);


/**
* 给单元赋值（创建于 2005.04.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
值和列的数据类型的必须匹配
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
* @param row 行编号.
* @param colName 列名称.
* @param value 单元值.
* @see #getItemValue(int,String)
* @see #setItemValue(int,int,Object)
* @return -1代表失败.
*/
	public int setItemValue(int row,String colName,Object value);
/**
* 取单元值（创建于 2005.04.05）.
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
* @param row 行编号.
* @param colName 列名称.
* @param property 属性名.
* @return 属性值.
*/
  public Object getItemAttribute(int row,String colName,String property);
/**
* 给单元属性赋值（创建于 2005.04.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
值和列的数据类型的必须匹配
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
* @param row 行编号.
* @param colName 列名称.
* @param property 属性名.
* @param value 属性值.
* @return -1代表失败.
*/
	public int setItemAttribute(int row,String colName,String property,Object value);

}

