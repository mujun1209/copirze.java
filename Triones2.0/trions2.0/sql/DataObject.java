package trionesII.sql;



/**
* 数据对象（创建于 2003.03.13）.
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
public interface DataObject
{	
/**
* 取当前定义的数据查询语句（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
返回空字符串代表没有特定的SQL语句，由使用者自己创建。
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
* @return 查询语句.
*/
	public String getSQLSelect();
/**
* 取数据结构的关键字（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
关键字用于指明与记录唯一性相关的字段。
在执行UPDATE、DELETE操作时将根据这些字段进行条件匹配。
关键字如有多个字段组成，则字段名之间用","分隔。
空字符串代表没有字段.
*代表所有字段.
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
* @return 关键字.
*/
	public String getPrimaryKey();

	
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
	public String getTable();

	
/**
* 取数据结构中的只读字段（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
只读字段在INSERT、UPDATE操作中将被忽略。
如有多个只读字段，则字段名之间用","分隔。
空字符串代表没有字段.
*代表所有字段.
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
* @return 只读字段.
*/	
	public String getReadOnlyKey();
	
/**
* 取字段名（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
字段标识的取值范围：1..colCount()
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
* @param id 字段标识.
* @return 字段名.
* @see #getColType(int)
*/
	public String getColName(int id);
/**
* 取字段类型（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
字段标识的取值范围：1..colCount()
null代表类型不确定.
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
* @param id 字段标识.
* @return 字段类型.
* @see #getColName(int)
*/
	public Class getColType(int id);
/**
* 取字段初始值（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
字段标识的取值范围：1..colCount()
null代表没有初始值.
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
* @param id 字段标识.
* @return 字段默认初始值.
*/
	public Object getColValue(int id);
	
/**
* 字段个数（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
字段标识的取值范围：1..colCount()
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
* @return 字段个数.
*/
	public int colCount();
}

