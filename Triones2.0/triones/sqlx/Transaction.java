package triones.sqlx;

import triones.frame.RecordSet;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
* 事务传输对象（创建于 2003.10.23）.
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
 电  话:021-68672222-2013
</pre>
</DL>
* @author	吴勇庆
* @version	2003.10.23
*/
public interface Transaction 
{ 

/**
* 提交事务（创建于 2003.10.23）.
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
* @see #rollback()
*/
	public void commit() throws SQLException;

	
/**
* 回滚事务（创建于 2003.10.23）.
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
* @see #commit()
*/
  public void rollback() throws SQLException;


/**
* 新增事务（创建于 2003.10.23）.
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
* @param com 传输因子.
* @return 新增成功的记录个数.
* @see #update(Translet)
	@see #delete(Translet)
	@see #query(Translet)
*/

	public int insert(Translet com) throws SQLException;
/**
* 修改事务（创建于 2003.10.23）.
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
* @param com 传输因子.
* @return 修改成功的记录个数.
* @see #insert(Translet)
	@see #delete(Translet)
	@see #query(Translet)
*/
	public int update(Translet com) throws SQLException;
	
/**
* 删除事务（创建于 2003.10.23）.
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
* @param com 传输因子.
* @return 删除成功的记录个数.
* @see #update(Translet)
	@see #insert(Translet)
	@see #query(Translet)
*/
	public int delete(Translet com) throws SQLException;
/**
* 查询事务（创建于 2003.10.23）.
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
* @param com 传输因子.
* @return 结果记录集.
* @see #update(Translet)
	@see #delete(Translet)
	@see #insert(Translet)
*/
	public ResultSet query(Translet com) throws SQLException;
/**
* 在数据库中查询记录（创建于 2003.03.07）.
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
创建于 2003.03.07.
</pre>
</DL>
* @param com 条件传输因子.
* @param beginIndex 从第几个记录开始，范围为1..记录总数.
* @param rows >0 向后取几条记录，<0 向前取几条记录.
* @return 记录集.
* @exception SQLException 数据库错误.
	@see #select(Translet)
*/
	public RecordSet select(Translet com,int beginIndex,int rows)	throws SQLException;
/**
* 在数据库中查询记录（创建于 2003.03.07）.
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
创建于 2003.03.07.
</pre>
</DL>
* @param com 条件传输因子.
* @return 记录集.
* @exception SQLException 数据库错误.
* @see #select(Translet,int,int)
*/
	public RecordSet select(Translet com) throws SQLException;

/**
* 查询事务的记录总数（创建于 2003.10.19）.
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
创建于 2003.10.19.
</pre>
</DL>
* @param com 传输因子.
* @return 记录总数.
*/
	public int getRowCount(Translet com) throws SQLException;
	

	
/**
* 释放事务传输对象（创建于 2003.10.23）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
事务对象被释放后,不能继续使用.
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
*/
	public void close() throws SQLException;
  
/**
 * 强制释放DBService当前占用的会话资源（创建于 2004.06.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
当使用InputStream或ResultSet作为返回值结果时,不能马上释放占用的会话资源,
在某些应用中,这样的情况一旦频繁会导致资源不够,因此建议采用本方法释放资源.
</pre>
<DT><B>示例：</B><DD>
<pre>
//SQLCA为DBService实例
SQLCA.closeStatement();
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.06.07.
</pre>
</DL>
 * @exception SQLException
 * @see #close()
 */
	public void closeStatement() throws SQLException;
}
