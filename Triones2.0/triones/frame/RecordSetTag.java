package triones.frame;

/**
* 视图结构接口（创建于 2003.03.13）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
定义与数据库表操作相关的数据结构。
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
* @version	2003.03.13
*/
public interface RecordSetTag extends SheetTag
{	
/**
* 取列标识（创建于 2005.04.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
列标识范围：≥1
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
* @param colName 列名称.
* @return 列标识，返回值≥1成功.
*/
	public int getColID(String colName); 
  
/**
* 取列名称（创建于 2005.04.05）.
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
* @return 列名称.
*/
	public String getColName(int colID);
  
/**
* 取列类型（创建于 2005.04.05）.
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
* @param colName 列名称.
* @return 列类型.
* @see #getColType(int)
*/
  public Class getColType(String colName);

	
/**
* 取数据结构对应的库表（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
如有多个库表组成，则表名之间用","分隔。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.13.
</pre>
</DL>
* @return 库表.
*/
	public String getName();

	

}

