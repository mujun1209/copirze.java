package trionesII.sql;

import java.sql.SQLException;
import java.util.NoSuchElementException;

/**
*  数据存储与检索控件（创建于 2003.03.12）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
DSRCtrl的英文含义为：Data Storage and Retrieval Control
本类的设计创意来源于PowerBuilder中的DataWindow/DataStore，在此，向创造PowerBuilder的
软件大师们致以最崇高的敬意！

采用DSRControl的最大好处是可以在其基础上开发标准的JSP页面模板页面人员可以在其基础上设计导航模板，
并可以在导航模板基础上实现代码生成（相当于配置参数，可用XML作为参数的格式）
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
* @version	2003.03.12
*/
public interface DSRControl
{	

/**
* 行初始状态（创建于 2003.03.12）.
<DL>
<DT><B>说明：</B><DD>
<pre>
初始状态的行不对应于任何操作.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @see #ROW_UPDATE
	@see #ROW_DELETE
	@see #ROW_INSERT
*/
public static final int ROW_INITIAL = 0;
/**
* 行新增状态（创建于 2003.03.12）.
<DL>
<DT><B>说明：</B><DD>
<pre>
对应于INSERT语句.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @see #ROW_UPDATE
	@see #ROW_DELETE
*/
	public static final int ROW_INSERT = 1;
/**
* 行修改状态（创建于 2003.03.12）.
<DL>
<DT><B>说明：</B><DD>
<pre>
对应于UPDATE语句.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @see #ROW_INSERT
	@see #ROW_DELETE
*/
	public static final int ROW_UPDATE = 2;
/**
* 行删除状态（创建于 2003.03.12）.
<DL>
<DT><B>说明：</B><DD>
<pre>
对应于DELETE语句.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @see #ROW_UPDATE
	@see #ROW_INSERT
*/
	public static final int ROW_DELETE = 3;
	
/**
* 取当前定义的数据查询语句（创建于 2003.03.13）.
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
创建于 2003.03.13.
</pre>
</DL>
* @return 查询语句.
* @see #setSQLSelect(String)
*/
	public String getSQLSelect();
	
/**
* 设置当前的数据查询语句（创建于 2003.03.13）.
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
创建于 2003.03.13.
</pre>
</DL>
* @param sql 查询语句.
* @see #getSQLSelect()
*/
	public void setSQLSelect(String sql);
	
/**
* 设置数据库事务传输对象（创建于 2003.03.12）.
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
创建于 2003.03.12.
</pre>
</DL>
* @param sqlca 数据库事务传输对象.
*/
	public void setTrans(Transaction sqlca);
/**
* 创建行记录（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
根据构造时提供的数据源信息创建行记录.
行号的取值范围:1..RowCount().
行号等于0，代表将行添加到末尾.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @param row 行号.
* @return 新增后的行个数.
* @see #deleteRow(int)
*/
	public int insertRow(int row);
/**
* 删除行记录（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
行号的取值范围:1..RowCount().
行号等于0，代表将删除最后一行.
本方法不影响数据库操作.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @param row 行号.
* @return 删除后的行个数.
* @see #insertRow(int)
*/
	public int deleteRow(int row);
/**
* 设置元素值（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
元素位置由行、列来确定.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @param row 行号.
* @param col 列标识.
* @param val 元素值.
* @see #setItem(int,String,Object)
*/
	public void setItem(int row,int col,Object val);
/**
* 设置元素值（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
元素位置由行、列来确定.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @param row 行号.
* @param col 列名.
* @param val 元素值.
* @see #setItem(int,int,Object)
*/
	public void setItem(int row,String col,Object val);
/**
* 取元素值（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
元素位置由行、列来确定.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @param row 行号.
* @param col 列标识.
* @return 元素值.
* @see #getItem(int,String)
*/
	public Object getItem(int row,int col);
/**
* 取元素值（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
元素位置由行、列来确定.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @param row 行号.
* @param col 列名.
* @return 元素值.
* @see #getItem(int,int)
*/
	public Object getItem(int row,String col);
/**
* 提交当前所有行的修改操作（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
各行记录的数据库操作方式根据行状态的值确定。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @return 本次修改成功的行记录数.
* @exception SQLException 数据库错误.
*/
	public int update() throws SQLException;
/**
* 从数据库提取行记录（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
根据构造时提供的数据源信息执行数据库查询操作.
执行本方法后原有行记录将仍存在，除非执行reset()方法.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @return 从数据库中获得的行记录个数.
* @exception SQLException 数据库错误.
* @see #retrieve(int,int)
*/
	public int retrieve() throws SQLException;
/**
* 从数据库提取行记录（创建于 2003.03.12）.
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
创建于 2003.03.12.
</pre>
</DL>
* @param beginIndex 从第几个记录开始，范围为1..记录总数.
* @param rows >0 向后取几条记录，<0 向前取几条记录.
* @return 实际取得的行记录个数.
* @exception SQLException 数据库错误.
* @see #retrieve()
*/
	public int retrieve(int beginIndex,int rows) throws SQLException;
/**
* 清除当前所有的行记录（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
本方法不影响数据库操作.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @return 清除后的行个数，一般为0.
*/
	public int reset();
/**
* 当前所有的行记录个数（创建于 2003.03.12）.
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
创建于 2003.03.12.
</pre>
</DL>
* @return 行记录个数.
*/
	public int rowCount();
	
/**
* 删除缓存区中的记录个数（创建于 2003.05.14）.
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
创建于 2003.05.14.
</pre>
</DL>
* @return 删除缓存区中的记录个数.
* @see #deleteRow(int)
	@see #rowCount()
*/
	public int deletedCount();
/**
* 返回列个数（创建于 2003.03.18）.
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
创建于 2003.03.18.
</pre>
</DL>
* @return 列个数.
*/
	public int colCount();
/**
* 取列的名称（创建于 2003.03.12）.
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
创建于 2003.03.12.
</pre>
</DL>
* @param col 列标识.
* @return 列名.
	@exception NoSuchElementException 不存在的列.
*/
	public String getColName(int col);
	
/**
* 取列的标识（创建于 2003.03.12）.
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
创建于 2003.03.12.
</pre>
</DL>
* @param col 列名.
* @return 列标识.
	@Exception NoSuchElementException 不存在的列.
*/
	public int getColID(String col);
	
	/**
* 取行状态（创建于 2003.03.18）.
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
创建于 2003.03.18.
</pre>
</DL>
* @param row 行号.
* @return 行状态.
* @see #ROW_DELETE
	@see #ROW_INSERT
	@see #ROW_UPDATE
	@see #ROW_INITIAL
*/
	public int getRowStatus(int row);
/**
* 设置行状态（创建于 2003.03.18）.
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
创建于 2003.03.18.
</pre>
</DL>
* @param row 行号.
* @param status 行状态.
* @see #ROW_DELETE
	@see #ROW_INSERT
	@see #ROW_UPDATE
	@see #ROW_INITIAL
*/
	public void setRowStatus(int row,int status);
}

