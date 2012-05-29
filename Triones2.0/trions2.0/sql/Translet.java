package trionesII.sql;

import java.util.List;


/**
* SQL指令集概念（创建于 2003.03.14）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
数据库指令集接口指可被用于数据库操作的SQL指令集。

它必须能够提供以下7种形式的预编译的SQL语句指令：

新增：INSERT INTO ... (...)VALUES(...)
删除（不含WHERE子句）：DELETE FROM ...
修改（不含WHERE子句）：UPDATE ... SET ...
查询（不含WHERE子句）：SELECT ... FROM ...
条件（不含SORT、GROUP子句）：WHERE ...
排序（不含GROUP子句）：ORDER BY ...
分组（含 ON HAVING子句）：GROUP BY
数据库对象：表名、触发器、或存储过程名；如果是表名，则多表之间用","分隔。

数据库指令接口主要被Transaction用作数据库事务处理。

数据库操作一般分成2个步骤：

1. 准备指令参数
2. 取预编译指令
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
* @version	2003.03.14
*/
public interface Translet
{		
/**
* 生成insert语句（创建于 2003.03.14）.
<DL>
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
创建于 2003.03.14.
</pre>
</DL>
*/
	public static final int SQL_INSERT = 1;

/**
* 生成update语句（创建于 2003.03.14）.
<DL>
<DT><B>说明：</B><DD>
<pre>
不含条件子句.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.14.
</pre>
</DL>
*/
	public static final int SQL_UPDATE = 2;
/**
* 生成delete语句（创建于 2003.03.14）.
<DL>
<DT><B>说明：</B><DD>
<pre>
不含条件子句.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.14.
</pre>
</DL>
*/
	public static final int SQL_DELETE = 3;
/**
* 生成SELECT语句（创建于 2003.03.14）.
<DL>
<DT><B>说明：</B><DD>
<pre>
不含条件子句.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.14.
</pre>
</DL>
*/
	public static final int SQL_SELECT = 4;
/**
* 生成条件子句（创建于 2003.03.14）.
<DL>
<DT><B>说明：</B><DD>
<pre>
不含排序、分组子句.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.14.
</pre>
</DL>
*/
	public static final int SQL_CONDITION = 5;
/**
* 生成排序子句（创建于 2003.03.14）.
<DL>
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
创建于 2003.03.14.
</pre>
</DL>
*/
	public static final int SQL_SORT = 6;
/**
* 生成分组子句（创建于 2003.03.14）.
<DL>
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
创建于 2003.03.14.
</pre>
</DL>
*/
	public static final int SQL_GROUP = 7;	
	
/**
* 生成与指令集有关的数据库对象（创建于 2003.03.14）.
<DL>
<DT><B>说明：</B><DD>
<pre>
表名、触发器、或存储过程名。
如果是表名，则多表之间用","分隔.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.14.
</pre>
</DL>
*/
	public static final int SQL_OBJECT = 8;	
	
	/**
	 * 取SQL指令参数（创建于 2003.03.09）.
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
	创建于 2003.03.09.
	</pre>
	</DL>
* @param op SQL指令类型.
* @param vals 用于与绑定值占位符对应的参数列表.
	@return 预编译SQL语句.
*/
	String prepareSQL(int op,List vals);

}

