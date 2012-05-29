/**
 * 对象中文名（创建于 2012-5-27）.
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
 email：360144561@qq.com 
 电 话：13585880448
</pre>
</DL>
 * @author	Administrator
 * @version	2012-5-27
 */
package triones.bas;

import java.util.ArrayList;
import java.util.List;

import Coprize.bas.IField;
import Coprize.bas.IRow;
import Coprize.bas.ISheet;

public class DefaultSheet extends DefaultExtend implements ISheet {
	
	/**
	* DefaultSheet 的行 Row 的数据集（创建于 2003.03.13）.
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
	创建于 2012-5-27.
	</pre>
	</DL>
	*/
	private List<DefaultRow> _rows = new ArrayList<DefaultRow>();

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
		if (index == 0)
		{
			_rows.add((DefaultRow)row);
		}
		else
		{
			_rows.add(index, (DefaultRow)row);
		}
		return _rows.size();
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
		
		return _rows.size();
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
		if(rowIndex < 1 || columnIndex < 1)
		{
			return null;
		}
		else
		{
			return _rows.get(rowIndex).Field(columnIndex);
		}
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
	* @param index 行序号
	* @return
	* @see package.class#method
	 */
	 IRow Row(int index)
	{
		
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
	* @param index 行序号.
	* @param attrName 属性名称.
	* @param attrValue 属性值.
	* @return 小于0代表失败.
	* @see package.class#method
	*/
	public int SetRowAttritbute(int index,String attrName, Object attrValue)
	{
		if(index < 0 ||null == attrName){
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
	* @param index 行序号.
	* @param attrName 属性名称.
	* @return 小于0代表失败.
	* @see package.class#method
	*/
	public Object GetRowAttritbute(int index,String attrName)
	{
		return _rows.get(index).GetAttribute(attrName);
	}
}
