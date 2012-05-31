/*
 **********************************
  ,'''╭⌒╮⌒╮.',''',,',.'',,','',.  
 ╱◥██◣''o',''',,',.''.'',,',.  
｜田｜田田│ '',,',.',''',,',.''  
╬╬╬╬╬╬╬╬╬╬╬╬╬╬╬╬╬╬  \|/\|/\|/\|/\|/
			
Love life, love programming  -- mu jun

 ***********************************/
package Triones.bas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import Coprize.bas.IDataStore;
import Coprize.bas.IField;
import Coprize.bas.IRow;
import Coprize.bas.ISheet;

/**
 * 数据存储对象 IDataStore 的默认实现（创建于 2012.05.23）.
 <DL>
 <DT><B>对象概述：</B><DD>
 <pre>
 与二维表对象结构相似，主要用于存放业务数据集合
 </pre>
 <DT><B>使用说明：</B><DD>
 <pre>
 包含3个工作区：

 PRIMARY  - 正式区
 DELETED  - 回收区
 FILTERED - 隐藏区

 默认在PRIMARY

 </pre>
 <DT><B>名词解释：</B><DD>
 <pre>
 略
 </pre>
 <DT><B>注意事项：</B><DD>
 <pre>
 基于JDK1.6
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
 * @version	2012-5-27
 */
/**
 * @author mujun
 * 
 */
public class DataStore extends Extend implements IDataStore {

	/**
	 * 数据存储对象名称（创建于 2012.05.23）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于 2012.05.23.
	 * </pre>
	 * </DL>
	 */
	private String _name = "";

	/**
	 * 数据存储对象的修改状态（创建于 2012-5-27）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于  2012-5-27.
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @see Coprize.bas.IDataStore#_modifty
	 */
	private boolean _modifty;

	/**
	 * 数据存储对象的primary工作区（创建于 2012-5-27）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 主工作区sheet
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于  2012-5-27.
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @see Coprize.bas.IDataStore#_ISheets
	 */
	private Sheet _primarySheets = new Sheet();

	/**
	 * 数据存储对象的deleted工作区（创建于 2012-5-27）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	 回收工作区sheet，记录被删除的数据
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于  2012-5-27.
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @see Coprize.bas.IDataStore#_ISheets
	 */
	private Sheet _deletedSheets = new Sheet();

	/**
	 * 数据存储对象的filtered工作区（创建于 2012-5-27）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	 筛选工作区sheet，即被过滤的数据
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于  2012-5-27.
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @see Coprize.bas.IDataStore#_ISheets
	 */
	private Sheet _filteredSheets = new Sheet();

	/**
	 * DefaultStore 的列column的数据集（创建于 2012-5-27）.
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于 2012-5-27.
	 * </pre>
	 * 
	 * </DL>
	 */
	private List<Field> _columns = new ArrayList<Field>();

	/**
	 * 设置业务数据名称（创建于 2012-5-27）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于 2012-5-27.
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @param name
	 * @return
	 * @see Coprize.bas.IDataStore#SetName(java.lang.String)
	 */
	@Override
	public int SetName(String name)
	{
		this._name = name;
		this._modifty = true;
		return 1;
	}

	/**
	 * 获取业务数据名称（创建于 2012-5-27）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于 2012-5-27.
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @return
	 * @see Coprize.bas.IDataStore#GetName()
	 */
	@Override
	public String GetName()
	{
		return this._name;
	}

	/**
	 * 设置列属性（创建于 2012-5-27）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 		 内置属性：
	 * 		 NAME      -  列名
	 * 		 CAPTION   -  列标题
	 * 		 DATATYPE  -  列类型
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于 2012-5-27.
	 * </pre>
	 * 
	 * </DL>
	 * 
	* @param index 列序号.
	* @param attrName 属性名称.
	* @param attrValue 属性值.
	* @return 小于0代表失败.
	* @see #GetColumnAttribute(int,String)
	 */
	@Override
	public int SetColumnAttritbute(int index, String attrName, Object attrValue)
	{
		if(index < 1 ||index >ColumnCount()){
			return -1;
		}
		_modifty = true;
		return _columns.get(index-1).SetAttribute(attrName, attrValue);
	}

	/**
	 * 取列的属性值（创建于 2012-5-27）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于 2012-5-27.
	 * </pre>
	 * 
	 * </DL>
	 * 
	* @param index 列序号.
	* @param attrName 属性名.
	* @return 属性值.
	* @see #SetColumnAttribute(int,String,Object)
	 */
	@Override
	public Object GetColumnAttritbute(int index, String attrName)
	{
		if(index < 1 ||index >ColumnCount()){
			return null;
		}
		return _columns.get(index).GetAttribute(attrName);
	}

	/**
	 * 设置行属性（创建于 2012-5-27）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 		 内置属性：
	 * 		 ID  -  内部行标识
	 * 		 STATUS   -  行状态
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于 2012.05.23
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @param index
	 *            行序号.
	 * @param attrName
	 *            属性名称.
	 * @param attrValue
	 *            属性值.
	 * @return 小于0代表失败.
	 * @see #GetRowAttribute(int,String)
	 */
	@Override
	public int SetRowAttritbute(int index, String attrName, Object attrValue)
	{
		if(index < 1 ||index >ColumnCount()) return -1;
		_modifty = true;
		return _primarySheets.SetRowAttritbute(index, attrName, attrValue);
	}

	/**
	 * 取行的属性值（创建于 2012-5-27）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于 2012-5-27.
	 * </pre>
	 * 
	 * </DL>
	 * 
	* @param index 行序号.
	* @param attrName 属性名.
	* @return 属性值.
	* @see #SetRowAttribute(int,String,Object)
	 */
	@Override
	public Object GetRowAttritbute(int index, String attrName)
	{
		if(index < 1 ||index >ColumnCount()) return null;
		return _primarySheets.GetRowAttritbute(index, attrName);
	}

	/**
	 * 根据列名取列的序号（创建于 2012-5-27）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于 2012-5-27.
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @param colName
	 *            列名.区分大小写
	 * @return 列序号，小于0代表失败，0代表不存在.
	 * @see Coprize.bas.IDataStore#GetColumnIndex(java.lang.String)
	 */
	@Override
	public int GetColumnIndex(String colName)
	{
		for (int i = 0; i < _columns.size(); i++)
		{
			if (colName.equals(_columns.get(i).GetName()))
			{
				return i;
			}
		}
		return 0;
	}

	/**
	 * 获取列的总数（创建于 2012-5-27）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于 2012-5-27.
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @return 包含的列个数.
	 */
	@Override
	public int ColumnCount()
	{
		return _columns.size();
	}

	/**
	 * 插入新行（创建于 2012-5-27）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于 2012-5-27.
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @param index
	 *            插入行的位置，0代表插入到最后一行.
	 * @return 实际行号，从1开始，小于0代表失败.
	 * @see Coprize.bas.IDataStore#InsertRow(int)
	 */
	@Override
	public int InsertRow(int index)
	{
		if (index < 0) return -1;
		_modifty = true;
		return _primarySheets.AddRow(index, null);
	}

	/**
	 * 删除指定序号的行记录（创建于 2012-5-27）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 *将行记录从Primary区移动到Deleted区的最后一行
		本操作会改变行状态
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于 2012-5-27.
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @param index
	 *            行序号，从1开始.
	 * @return 删除后的Primary区总行数，小于0代表失败.
	 * @see Coprize.bas.IDataStore#DeleteRow(int)
	 */
	@Override
	public int DeleteRow(int index)
	{
		if (index < 1 ) return -1;
		IRow delRow = _primarySheets.Row(index);
		if(null == delRow) return -1;
		_modifty = true;
		delRow.SetAttribute("STATUS", "Deleted");
		_deletedSheets.AddRow(0, delRow);
		_primarySheets.RemoveRow(index);
		return _primarySheets.RowCount();
	}

	/**
	 * 返回指定区的行数（创建于 2012-5-27）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于 2012-5-27.
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @param SheetName
	 *            工作区名称,"*"代表所有区的总行数. null或者"" 表示默认主区primarysheet
	 * @return -1 表示工作区的名称不存在，其他表示工作区的数据条数
	 * @see Coprize.bas.IDataStore#RowCount(java.lang.String)
	 */
	@Override
	public int RowCount(String SheetName)
	{
		if (null == SheetName || "".equals(SheetName))
		{
				return _primarySheets.RowCount();
		}
		else
			if ("*".equals(SheetName))
			{
				return _primarySheets.RowCount() + _deletedSheets.RowCount()
						+ _filteredSheets.RowCount();
			}
			else
			{
				ISheet lsheet = GetSheetByName(SheetName);
				if (lsheet != null) return lsheet.RowCount();
				else
					return -1;
			}

	}

	/**
	 * 设定指定位置单元格的值（创建于 2012-5-27）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于 2012-5-27.
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @param rowIndex
	 *            行号.
	 * @param colIndex
	 *            列号.
	 * @param val
	 *            设置的值.
	 * @return 小于0代表失败，等于0代表该值和原来的值相等.
	 * @see Coprize.bas.IDataStore#GetItemValue(int, int, java.lang.Object)
	 */
	@Override
	public int SetItemValue(int rowIndex, int colIndex, Object val)
	{
		IField lfield = _primarySheets.Item(rowIndex, colIndex);
		
		if(null == lfield) return -1;
		
		if (null != lfield.GetValue()) // 如果给item原来有值，则将原来的值保存到oldvalue
		{
			_primarySheets.Row(rowIndex).SetAttribute("STATUS", "Modified");
			lfield.SetAttribute("oldvalue", lfield.GetValue());
		}
		_modifty = true;
		return lfield.SetValue(val);
	}

	/**
	 * 取指定位置单元格的值（创建于 2012-5-27）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于 2012-5-27.
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @param rowIndex
	 *            行序号.
	 * @param colIndex
	 *            列序号.
	 * @return None代表单元格不存在,Null代表单元格为空值.
	 * @see Coprize.bas.IDataStore#SetItemValue(int, int)
	 */
	@Override
	public Object GetItemValue(int rowIndex, int colIndex)
	{
		IField lfield = _primarySheets.Item(rowIndex, colIndex);
		if(null == lfield) return null;
		return lfield.GetValue();
	}

	/**
	 * 接受数据更新（创建于 2012-5-27）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	option=true 将Primary区中的已更新数据设为正式数据
	 * 		操作为：根据行状态（New ! Modified ! Deleted ! NnChanged !）来执行操作
	 * 		将modified 的数据的oldvalue数据换成value ，全部的行状态改为 nuchanged
	 * 	option=false 将Primary区中的已更新数据恢复成原来状态
	 * 		将modified 的数据的value数据换成oldvalue ，全部的行状态改为 nuchanged
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于 2012-5-27.
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @option true代表接受更新,false代表不接受更新
	 * @return 小于0代表失败.
	 * @see Coprize.bas.IDataStore#Accept(boolean)
	 */
	@Override
	public int Accept(boolean option)
	{
		Iterator<Row> it = _primarySheets.iterator();
		while(it.hasNext())
		{
			Row lrow = it.next();
			if("Modified".equals(lrow.GetAttribute("STATUS")))  //如果行状态标识：已经改变
			{
					for(int i=1;i<lrow.FieldCount();i++)
					{
						IField lfield =lrow.Field(i);
						Object oldvalue =lfield.GetAttribute("oldvalue");
						if( null != oldvalue )
							if(!option) lfield.SetValue(oldvalue);
							else lfield.SetAttribute("oldvalue", lfield.GetValue()); //接受更新
					}
			}
			lrow.SetAttribute("STATUS", "NnChanged");
		}
		return 0;
	}

	/**
	 * 清空指定区域的行记录（创建于 2012-5-27）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于 2012-5-27.
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @param SheetName
	 *            工作区名称,"*"代表所有区. null 或者 ""默认在primary区
	 * @return  -1表示工作区不存在， >0 表示清除成功 具体数值表示表示成功清除了几个区域.
	 * @see Coprize.bas.IDataStore#Reset(java.lang.String)
	 */
	@Override
	public int Reset(String sheetName)
	{
		String[] ls_SheetName = {"Primary!","Deleted!","Filtered!"};
		int j=0;
		for(int i=0;i<ls_SheetName.length;i++)
		{
			if(sheetName.indexOf(ls_SheetName[i])>=0||sheetName.equals("*")) 
			{	
				GetSheetByName(ls_SheetName[i]).Clear();
				j++;
			}
		}
		return j;
		/*
//		if (null == SheetName || "".equals(SheetName)) return _primarySheets.Clear();
//		if ("*".equals(SheetName))
//			return _primarySheets.Clear() + _deletedSheets.Clear() + _filteredSheets.Clear();
//		DefaultSheet lsheet = GetSheetByName(SheetName);
//		if (lsheet != null) return lsheet.Clear();
//		return -1;*/
			
	}

	/**
	 * 判断数据存储对象是否发生过改变（创建于 2012-5-27）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于 2012-5-27.
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @return true代表已发生改变,false代表未发生改变.
	 * @see Coprize.bas.IDataStore#IsModified()
	 */
	@Override
	public boolean IsModified()
	{

		return _modifty;
	}

	/**
	 * 抛弃指定区域的行记录（创建于 2012-5-27）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 必须对指定区域进行操作，数据会被真正删除，无法恢复
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于 2012-5-27.
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @param sheet
	 *            指定工作区名称.
	 * @param index
	 *            行序号，从1开始.
	 * @return 删除后该区域的总行数，小于0代表失败.
	 * @see Coprize.bas.IDataStore#DiscardRow(java.lang.String, int)
	 */
	@Override
	public int DiscardRow(String sheet, int index)
	{
		ISheet curSheet = GetSheetByName(sheet);
		if (null == curSheet) return -1;
		_modifty = true;
		return curSheet.RemoveRow(index);
	}

	/**
	 * 移动行记录（创建于 2012-5-27）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于 2012-5-27.
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @param sourceSheet
	 *            源工作区.
	 * @param rowIndex
	 *            行开始序号.
	 * @param rowNum
	 *            行个数.
	 * @param tagetSheet
	 *            目标工作区.
	 * @param tagetIndex
	 *            目标工作区位置,0代表最后1行.
	 * @return 实际移动行数,小于0代表失败.
	 * @see Coprize.bas.IDataStore#MoveRow(java.lang.String, int, int,
	 *      java.lang.String, int)
	 */
	@Override
	public int MoveRow(String sourceSheet, int rowIndex, int rowNum,
			String tagetSheet, int tagetIndex)
	{
		int lrs = CopyRow(sourceSheet, rowIndex, rowNum, tagetSheet, tagetIndex);

		if (lrs == rowNum)
		{
			Sheet srcSheet = GetSheetByName(sourceSheet);
			int rmres = 0;
			for (int i = 0; i < rowNum; i++)
			{
				rmres += srcSheet.RemoveRow(rowIndex + i);
			}
			return rmres;
		}
		else
		{
			return lrs;
		}
	}

	/**
	 * 复制行记录（创建于 2012-5-27）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	本操作不会影响行或列的状态
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于 2012-5-27.
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @param sourceSheet
	 *            源工作区.
	 * @param rowIndex
	 *            行开始序号.
	 * @param rowNum
	 *            行个数.
	 * @param tagetSheet
	 *            目标工作区.
	 * @param tagetIndex
	 *            目标工作区位置,0代表最后1行.
	 * @return 实际复制行数,小于0代表失败.
	 * @see Coprize.bas.IDataStore#CopyRow(java.lang.String, int, int,
	 *      java.lang.String, int)
	 */
	@Override
	public int CopyRow(String sourceSheet, int rowIndex, int rowNum,
			String tagetSheet, int tagetIndex)
	{
		Sheet srcSheet = GetSheetByName(sourceSheet);
		Sheet tagSheet = GetSheetByName(tagetSheet);

		if (null == srcSheet || null == tagSheet || rowIndex < 1 || rowNum < 0) return -1;
		try
		{
			for (int i = 0; i < rowNum; i++)
			{
				tagSheet.AddRow((tagetIndex == 0 ? -1 : tagetIndex) + i,
						srcSheet.Row(rowIndex + i)); // 如果tagetIndex ==0
														// 则每次在tagSheet 的末尾加入
			}
			_modifty = true;
			return rowNum;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return -1;
		}

	}

	/**
	 * 分享某个区域的数据作为另一个数据存储对象的指定区域（创建于 2012-5-28）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于 2012-5-28.
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @param sourceSheet
	 *            源工作区名称.
	 * @param dataStore
	 *            目标数据存储对象.
	 * @param targetSheet
	 *            目标工作区名称.
	 * @param copy
	 *            true代表以复制方式分享,false代表引用方式分享.
	 * @return 小于0代表失败.
	 * @see Coprize.bas.IDataStore#Share(java.lang.String,
	 *      Coprize.bas.IDataStore, java.lang.String, boolean)
	 */
	@Override
	public int Share(String sourceSheet, IDataStore dataStore,
			String targetSheet, boolean copy)
	{
		try
		{
			Sheet srcSheet = ((DataStore) dataStore)
					.GetSheetByName(sourceSheet);
			if (copy)
			{
				SetSheetByName(targetSheet,
						(Sheet) srcSheet.CloneObject());
			}
			else
			{
				SetSheetByName(targetSheet, srcSheet);
			}
		}
		catch (Exception e)
		{
			return -1;
		}

		return 0;
	}

	/**
	 * 内部方法：通过名字获得 sheet（创建于 2012-5-28）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	 name只支持： primary  正式区
	 * 				deleted  回收区
	 * 				filtered 隐藏区
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于 2012-5-28.
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @param name
	 * @return 如果没有找到name 返回 null;
	 * @see package.class#method
	 */
	private Sheet GetSheetByName(String name)
	{
		if ("primary".equals(name.toLowerCase())) return _primarySheets;
		if ("deleted".equals(name.toLowerCase())) return _deletedSheets;
		if ("filtered".equals(name.toLowerCase())) return _filteredSheets;
		return null;

	}

	/**
	 * 内部方法： 通过sheet的名称设置sheet的值（创建于 2012-5-29）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于 2012-5-29.
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @param name
	 *            工作区名称.
	 * @param tagSheet
	 *            新的sheet的值
	 * @return
	 * @see package.class#method
	 */
	private int SetSheetByName(String name, Sheet tagSheet)
	{
		if ("primary".equals(name.toLowerCase()))
		{
			_primarySheets = tagSheet;
		}
		else
			if ("deleted".equals(name.toLowerCase()))
			{
				_deletedSheets = tagSheet;
			}
			else
				if ("filtered".equals(name.toLowerCase()))
				{
					_filteredSheets = tagSheet;
				}
				else
				{
					return -1;
				}
		return 1;
	}

	/**
	 * <img src="new.gif" width="28" height="11" border="0">设置单元格属性（创建于
	 * 2012-5-30）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于 2012-5-30.
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @param row
	 *            行序号.
	 * @param col
	 *            列序号.
	 * @param attrName
	 *            属性名称.
	 * @param attrValue
	 *            属性值.
	 * @return 小于0代表失败.
	 * @see #GetItemAttribute(int,int,String)
	 */
	@Override
	public int SetItemAttritbute(int row, int col, String attrName,
			Object attrValue)
	{
		IField lfield = _primarySheets.Item(row, col);
		if(null == lfield) return -1;
		_modifty = true;
		return lfield.SetAttribute(attrName, attrValue);
	}

	/**
	 * <img src="new.gif" width="28" height="11" border="0">取单元格属性值（创建于
	 * 2012-5-30）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于 2012-5-30.
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @param row
	 *            行序号.
	 * @param col
	 *            列序号.
	 * @param attrName
	 *            属性名.
	 * @return 属性值.
	 * @see #SetItemAttribute(int,int,String,Object)
	 */
	@Override
	public Object GetItemAttritbute(int row, int col, String attrName)
	{
		IField lfield = _primarySheets.Item(row, col);
		if(null == lfield) return -1;
		_modifty = true;
		return lfield.GetAttribute(attrName);
	}
}
