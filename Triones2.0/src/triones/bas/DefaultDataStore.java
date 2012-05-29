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
import java.util.List;

import Coprize.bas.IDataStore;
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
public class DefaultDataStore extends DefaultExtend implements IDataStore {

	/**
	* 数据存储对象名称（创建于  2012.05.23）.
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
	创建于 2012.05.23.
	</pre>
	</DL>
	* @see Coprize.bas.IDataStore#_name
	*/
	private String _name = "";
	
	/**
	* 数据存储对象的修改状态（创建于  2012-5-27）.
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
	创建于  2012-5-27.
	</pre>
	</DL>
	* @see Coprize.bas.IDataStore#_modifty 
	*/
	private boolean _modifty; 
	
	/**
	* 数据存储对象的primary工作区（创建于  2012-5-27）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	 主工作区sheet
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于  2012-5-27.
	</pre>
	</DL>
	* @see Coprize.bas.IDataStore#_ISheets 
	*/
	private DefaultSheet _primarySheets=new DefaultSheet();
	
	/**
	* 数据存储对象的deleted工作区（创建于  2012-5-27）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	 回收工作区sheet，记录被删除的数据
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于  2012-5-27.
	</pre>
	</DL>
	* @see Coprize.bas.IDataStore#_ISheets 
	*/
	private DefaultSheet _deletedSheets=new DefaultSheet();
	
	/**
	* 数据存储对象的filtered工作区（创建于  2012-5-27）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	 筛选工作区sheet，即被过滤的数据
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于  2012-5-27.
	</pre>
	</DL>
	* @see Coprize.bas.IDataStore#_ISheets 
	*/
	private DefaultSheet _filteredSheets=new DefaultSheet();
	
	/**
	* DefaultStore 的列column的数据集（创建于 2003.03.13）.
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
	private List<DefaultField> _columns = new ArrayList<DefaultField>();
	
	
	/**
	 * 设置业务数据名称（创建于 2012-5-27）.
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
	 * @param name
	 * @return
	 * @see Coprize.bas.IDataStore#SetName(java.lang.String)
	 */
	@Override
	public int SetName(String name)
	{
		this._name = name;
		return 1;
	}

	/**
	 * 获取业务数据名称（创建于 2012-5-27）.
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
	 * @return
	 * @see Coprize.bas.IDataStore#GetName()
	 */
	@Override
	public String GetName()
	{

		return this._name;
	}

	/**
	 * 设置列属性（创建于 2012.05.23）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
		 内置属性：
		 NAME      -  列名
		 CAPTION   -  列标题
		 DATATYPE  -  列类型
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
	 * @param index
	 * @param attrName
	 * @param attrValue
	 * @return
	 * @see Coprize.bas.IDataStore#SetColumnAttritbute(int, java.lang.String, java.lang.Object)
	 */
	@Override
	public int SetColumnAttritbute(int index, String attrName, Object attrValue)
	{
		 return _columns.get(index).SetAttribute(attrName, attrValue);
	}

	/**
	 * 取列的属性值（创建于 2012-5-27）.
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
	 * @param index
	 * @param attrName
	 * @return
	 * @see Coprize.bas.IDataStore#GetColumnAttritbute(int, java.lang.String)
	 */
	@Override
	public Object GetColumnAttritbute(int index, String attrName)
	{

		return _columns.get(index).GetAttribute(attrName);
	}

	/**
	* 设置行属性（创建于 2012.05.23）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
		 内置属性：
		 ID  -  内部行标识
		 STATUS   -  行状态
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2012.05.23 
	</pre>
	</DL>
	* @param index 行序号.
	* @param attrName 属性名称.
	* @param attrValue 属性值.
	* @return 小于0代表失败.
	* @see #GetRowAttribute(int,String)
	*/
	@Override
	public int SetRowAttritbute(int index, String attrName, Object attrValue)
	{
		//return  _primarySheets.Row(index).SetAttribute(attrName, attrValue);
		return  _primarySheets.SetRowAttritbute(index, attrName, attrValue);
	}

	/**
	 *  取行的属性值（创建于 2012-5-27）.
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
	 * @param index
	 * @param attrName
	 * @return
	 * @see Coprize.bas.IDataStore#GetRowAttritbute(int, java.lang.String)
	 */
	@Override
	public Object GetRowAttritbute(int index, String attrName)
	{

		return _primarySheets.GetRowAttritbute(index, attrName);
	}

	/**
	 * 根据列名取列的序号（创建于 2012-5-27）.
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
	 * @param colName 列名.
	 *@return 列序号，小于0代表失败，0代表不存在.
	 * @see Coprize.bas.IDataStore#GetColumnIndex(java.lang.String)
	 */
	@Override
	public int GetColumnIndex(String colName)
	{
		for (int i = 0; i < _columns.size(); i++)
		{
			if(colName.equals(_columns.get(i).GetName()))
			{
				return i;
			}
		}
		return 0;
	}

	/**
	 *  获取列的总数（创建于 2012-5-27）.
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
	 * @return
	 * @see Coprize.bas.IDataStore#ColumnCount()
	 */
	@Override
	public int ColumnCount()
	{
		return _columns.size();
	}

	/**
	 * 插入新行（创建于 2012-5-27）.
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
	* @param index 插入行的位置，0代表插入到最后一行.
	* @return 实际行号，从1开始，小于0代表失败.
	 * @see Coprize.bas.IDataStore#InsertRow(int)
	 */
	@Override
	public int InsertRow(int index)
	{
		if (index < 0)
		{
			return index;
		}
		
		return _primarySheets.AddRow(index, null);
	}

	/**
	 *  删除指定序号的行记录（创建于 2012-5-27）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	 实际操作是将primary区的行移动到deleted 区。
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
	* @param index 行序号，从1开始.
	* @return 删除后的Primary区总行数，小于0代表失败.
	 * @see Coprize.bas.IDataStore#DeleteRow(int)
	 */
	@Override
	public int DeleteRow(int index)
	{
		if(index<1){
			return -1;
		}
		IRow delRow = _primarySheets.Row(index);
		_deletedSheets.AddRow(0, delRow);
		_primarySheets.RemoveRow(index);
		return _primarySheets.RowCount();
	}

	/**
	 * 返回指定区的行数（创建于 2012-5-27）.
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
	 * @param SheetName 工作区名称,"*"代表所有区的总行数. null或者"" 表示默认主区primarysheet 
	 * @return -1 表示工作区的名称不存在，其他表示工作区的数据条数
	 * @see Coprize.bas.IDataStore#RowCount(java.lang.String)
	 */
	@Override
	public int RowCount(String SheetName)
	{
		if(null==SheetName || "".equals(SheetName)){
			ISheet lsheet = GetSheetByName(SheetName);
			if(lsheet!=null)
				return lsheet.RowCount();
			else  return -1;
		}
		else if ("*".equals(SheetName))
		{
			return _primarySheets.RowCount() + _deletedSheets.RowCount() + _filteredSheets.RowCount();
		}
		else
		{
			ISheet lsheet = GetSheetByName(SheetName);
			if(lsheet!=null)
				return lsheet.RowCount();
			else  return -1;
		}
		
	}

	/**
	 *  设定指定位置单元格的值（创建于 2012-5-27）.
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
	* @param rowIndex 行号.
	* @param colIndex 列号.
	* @param val 设置的值.
	* @return 小于0代表失败，等于0代表该值和原来的值相等.
	 * @see Coprize.bas.IDataStore#GetItemValue(int, int, java.lang.Object)
	 */
	@Override
	public int SetItemValue(int rowIndex, int colIndex, Object val)
	{
		return _primarySheets.Item(rowIndex, colIndex).SetValue(val);
	}

	/**
	 * 取指定位置单元格的值（创建于 2012-5-27）.
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
	* @param rowIndex 行序号.
	* @param colIndex 列序号.
	* @return None代表单元格不存在,Null代表单元格为空值.
	 * @see Coprize.bas.IDataStore#SetItemValue(int, int)
	 */
	@Override
	public Object GetItemValue(int rowIndex, int colIndex)
	{
		if(rowIndex < 1 || colIndex < 1 ||colIndex > _columns.size() || rowIndex > _primarySheets.RowCount()){
			return null;
		}
		return _primarySheets.Item(rowIndex, colIndex).GetValue();
	}

	/**
	 * 接受数据更新（创建于 2012-5-27）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	option=true 将Primary区中的已更新数据设为正式数据
	option=false 将Primary区中的已更新数据恢复成原来状态
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
	* @option true代表接受更新,false代表不接受更新
	* @return 小于0代表失败.
	 * @see Coprize.bas.IDataStore#Accept(boolean)
	 */
	@Override
	public int Accept(boolean option)
	{

		return 0;
	}

	/**
	 * 清空指定区域的行记录（创建于 2012-5-27）.
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
	 * @param SheetName 工作区名称,"*"代表所有区.
	 * @return
	 * @see Coprize.bas.IDataStore#Reset(java.lang.String)
	 */
	@Override
	public int Reset(String SheetName)
	{

		return 0;
	}

	/**
	 *判断数据存储对象是否发生过改变（创建于 2012-5-27）.
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
	 * @return true代表已发生改变,false代表未发生改变.
	 * @see Coprize.bas.IDataStore#IsModified()
	 */
	@Override
	public boolean IsModified()
	{

		return _modifty;
	}

	/**
	* 抛弃指定区域的行记录（创建于 2012.05.23）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	必须对指定区域进行操作
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
	* @param sheet 指定工作区名称.
	* @param index 行序号，从1开始.
	* @return 删除后该区域的总行数，小于0代表失败.
	 * @see Coprize.bas.IDataStore#DiscardRow(java.lang.String, int)
	 */
	@Override
	public int DiscardRow(String sheet, int index)
	{
		ISheet curSheet = GetSheetByName(sheet);
		if(null == curSheet)
		{
			return -1;
		}
		if(index < 0)
		{
			return index;
		}
		else
		{
			return curSheet.RemoveRow(index);
		}
	}

	/**
	 * 移动行记录（创建于 2012-5-27）.
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
	* @param sourceSheet 源工作区.
	* @param rowIndex 行开始序号.
	* @param rowNum 行个数.
	* @param tagetSheet 目标工作区.
	* @param tagetIndex 目标工作区位置,0代表最后1行.
	* @return 实际移动行数,小于0代表失败.
	 * @see Coprize.bas.IDataStore#MoveRow(java.lang.String, int, int, java.lang.String, int)
	 */
	@Override
	public int MoveRow(String sourceSheet, int rowIndex, int rowNum,
			String tagetSheet, int tagetIndex)
	{
		int lrs = CopyRow(sourceSheet,rowIndex,rowNum,tagetSheet,tagetIndex);
		
		if(lrs == rowNum){
			DefaultSheet srcSheet = GetSheetByName(sourceSheet);
			int rmres = 0;
			for (int i = 0; i < rowNum; i++)
			{
				rmres += srcSheet.RemoveRow(rowIndex+i);
			}
			return rmres;
		}else{
			return lrs;
		}
	}

	/**
	 * 复制行记录（创建于 2012-5-27）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	本操作不会影响行或列的状态 ? 如何理解
	如果rowNum >1  循环的序号，如果和tagetIndex 中有重复，将覆盖 tagetSheet 的数据被覆盖
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
	* @param sourceSheet 源工作区.
	* @param rowIndex 行开始序号.
	* @param rowNum 行个数.
	* @param tagetSheet 目标工作区.
	* @param tagetIndex 目标工作区位置,0代表最后1行.
	* @return 实际复制行数,小于0代表失败.
	 * @see Coprize.bas.IDataStore#CopyRow(java.lang.String, int, int, java.lang.String, int)
	 */
	@Override
	public int CopyRow(String sourceSheet, int rowIndex, int rowNum,
			String tagetSheet, int tagetIndex)
	{
		DefaultSheet srcSheet = GetSheetByName(sourceSheet);
		DefaultSheet tagSheet = GetSheetByName(tagetSheet);
		
		if(null == srcSheet || null == tagSheet ||rowIndex<1 ||rowNum < 0)
		{
			return -1;
		}
		
		try
		{
			for (int i = 0; i < rowNum; i++)
			{
				tagSheet.AddRow((tagetIndex==0?-1:tagetIndex)+i,srcSheet.Row(rowIndex + i)) ;  //如果tagetIndex ==0  则每次在tagSheet 的末尾加入 
			}
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
	创建于 2012-5-28.
	</pre>
	</DL>
	* @param sourceSheet 源工作区名称.
	* @param dataStore 目标数据存储对象.
	* @param targetSheet 目标工作区名称.
	* @param copy true代表以复制方式分享,false代表引用方式分享.
	* @return 小于0代表失败.
	* @see Coprize.bas.IDataStore#Share(java.lang.String, Coprize.bas.IDataStore, java.lang.String, boolean)
	 */
	@Override
	public int Share(String sourceSheet, IDataStore dataStore,
			String targetSheet, boolean copy)
	{
		try
		{
			DefaultSheet srcSheet = ((DefaultDataStore)dataStore).GetSheetByName(sourceSheet);
			if(copy)
			{
				SetSheetByName(targetSheet, (DefaultSheet)srcSheet.CloneObject());
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
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	 name只支持： primary  正式区
				deleted  回收区
				filtered 隐藏区
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
	* @param name
	* @return 如果没有找到name 返回 null;
	* @see package.class#method
	 */
	private DefaultSheet GetSheetByName(String name)
	{
		if("primary".equals(name.toLowerCase()))
		{
			return _primarySheets;
		}
		else if ("deleted".equals(name.toLowerCase()))
		{
			return _deletedSheets;
		}else if("filtered".equals(name.toLowerCase()))
		{
			return _filteredSheets;
		}else
		{
			return null;
		}
		
	}
	
	/**
	*内部方法： 通过sheet的名称设置sheet的值（创建于 2012-5-29）.
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
	创建于 2012-5-29.
	</pre>
	</DL>
	* @param name  工作区名称.
	* @param tagSheet 新的sheet的值
	* @return
	* @see package.class#method
	 */
	private int SetSheetByName(String name,DefaultSheet tagSheet)
	{
		if("primary".equals(name.toLowerCase()))
		{
			_primarySheets = tagSheet;
		}
		else if ("deleted".equals(name.toLowerCase()))
		{
			 _deletedSheets= tagSheet;
		}
		else if("filtered".equals(name.toLowerCase()))
		{
			 _filteredSheets= tagSheet;
		}else
		{
			return -1;
		}
		return 1;
	}
}
