/*
 **********************************
  ,'''╭⌒╮⌒╮.',''',,',.'',,','',.  
 ╱◥██◣''o',''',,',.''.'',,',.  
｜田｜田田│ '',,',.',''',,',.''  
╬╬╬╬╬╬╬╬╬╬╬╬╬╬╬╬╬╬  \|/\|/\|/\|/\|/
			
Love life, love programming  -- mu jun

 ***********************************/
package triones.bas;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Coprize.bas.IField;
import Coprize.bas.IRow;
import Coprize.bas.ISheet;

/**
* 二维表数据对象（创建于 2012.05.23）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
可对二维表的行和单元格进行增删改查操作.
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
 email：360144561@qq.com 
 电 话：13585880448
</pre>
</DL>
* @author	mujun
* @version	2012.05.23
*/
public class DefaultSheet extends DefaultExtend implements ISheet {
	
	/**
	* DefaultSheet 的行 Row 的数据集（创建于 2003.03.13）.
	<DL>
	<DT><B>说明：</B><DD>
	<pre>
	默认实现是LinkedList ,考虑到数据的插入等操作比较频繁
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2012-5-27.
	</pre>
	</DL>
	*/
	private List<DefaultRow> _rows = new LinkedList<DefaultRow>();

	/**
	* 无参构造方法（创建于 2012-5-30）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	 默认添加一个空的行，确保行号是从1开始计数
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2012-5-30.
	</pre>
	</DL>
	* @see package.class#method
	 */
	public DefaultSheet()
	{
		_rows.add(0,null);
	}
	
	/**
	*  增加数据行 DefaultSheet 实现（创建于 2012-5-27）.
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
	创建于 2012-5-27.
	</pre>
	</DL>
	* @param index 新增行插入位置,0代表最后1行.
	* @param row 数据行对象.
	* @return 插入后实际的行序号.
	* @see #RemoveRow(int)
	 */
	@Override
	public int AddRow(int index, IRow row)
	{
		DefaultRow irow = (DefaultRow)row;
		irow.SetAttribute("STATUS", "new");
		if (index == 0 || index >RowCount() )
		{
			_rows.add(RowCount(),irow);
			return RowCount();
		}
		else
		{
			_rows.add(index, irow);
			return index;
		}
		
	}

	/**
	* 删除数据行 DefaultSheet 实现（创建于 2012.05.23）.
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
	创建于 2012.05.23 .
	</pre>
	</DL>
	* @param index 数据行号.
	* @return 删除后的总行数，小于0代表失败.
	* @see #AddRow(int,IRow)
	*/
	@Override
	public int RemoveRow(int index)
	{
		try
		{
			_rows.remove(index);
		}
		catch (Exception e)
		{
			return -1;
		}
		
		return RowCount();
	}

	/**
	* 引用单元格对象 DefaultSheet 实现（创建于 2012.05.23）.
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
	创建于 2012.05.23 .
	</pre>
	</DL>
	* @param rowIndex 行号,从1开始.
	* @param columnIndex 列号，从1开始.
	* @return 单元格对象.如果
	*/
	@Override
	public IField Item(int rowIndex, int columnIndex)
	{
		if(rowIndex < 1 || columnIndex < 1 || rowIndex >RowCount() || columnIndex >  _rows.get(rowIndex).FieldCount())
		{
			return null;
		}
		return _rows.get(rowIndex).Field(columnIndex-1);
	}

	/**
	* 总行数 DefaultSheet 实现（创建于 2012.05.23）.
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
	创建于 2012.05.27 .
	</pre>
	</DL>
	* @return 二维表中的数据行数.
	*/
	@Override
	public int RowCount()
	{
		return _rows.size();
	}

	/**
	 * 
	* 引用行对象 （创建于 2012-5-27）.
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
	创建于 2012-5-27.
	</pre>
	</DL>
	* @param index 行序号 从1开始计数
	* @return 行对象的引用 如果序号不存返回null
	* @see package.class#method
	 */
	 IRow Row(int index)
	{
		if(index < 1 || index > RowCount() )
		{
			return null;
		}
		return _rows.get(index);
	}

	/**
	* 设置行属性（创建于 2012-5-28）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	 序号index 小于零或者attrName 为null 将设置失败
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2012-5-28.
	</pre>
	</DL>
	* @param index 行序号  .
	* @param attrName 属性名称.
	* @param attrValue 属性值.
	* @return 小于0代表失败.
	* @see package.class#method
	*/
	public int SetRowAttritbute(int index,String attrName, Object attrValue)
	{
		if(index < 1 || index > RowCount() ||null == attrName){
			return -1;
		}
		return _rows.get(index).SetAttribute(attrName, attrValue);
	}
	
	/**
	* 获取行属性（创建于 2012-5-28）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	 序号index 小于零或者attrName 为null 将设置失败
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2012-5-28.
	</pre>
	</DL>
	* @param index 行序号.序号从1开始计数
	* @param attrName 属性名称.
	* @return 行号不存在，或者属性不存在返回 null
	* @see package.class#method
	*/
	public Object GetRowAttritbute(int index,String attrName)
	{
		if(index <1 ||index >RowCount()){
			return null;
		}
		return _rows.get(index).GetAttribute(attrName);
	}

	/**
	* 清空sheet区域数据（创建于 2012-5-30）.
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
	创建于 2012-5-30.
	</pre>
	</DL>
	* @return
	* @see package.class#method
	 */
	public int Clear()
	{
		_rows.clear();
		_rows.add(0, null);
		return 1;
	}
}
