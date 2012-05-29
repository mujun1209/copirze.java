package Coprize.bas;

                       /* \\|///
                       \\  - -  //
                        (  @ @  )
**********************oOOo-(_)-oOOo************
*                                             *
*  Publish Date: 2012.05.23   Wu yongqing    *
*                                          *
*  We work because we wake               *
*                             Oooo        *
************************oooO**(   )*********
                       (   )   ) /
                        \ (   (_/
                         */

/**
* 数据存储对象（创建于 2012.05.23）.
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
基于JDK1.3
</pre>
<DT><B>展望未来：</B><DD>
<pre>
略
</pre>
<DT><B>联系方式：</B><DD>
<pre>
 email：wuyqb@163.com 
 电 话：021-65804142
</pre>
</DL>
* @author	吴勇庆
* @version	2012.05.23
*/
public interface IDataStore extends Cloneable,IExtend
{

	
/**
* 设置业务数据名称（创建于 2012.05.23）.
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
创建于 2012.05.23 - 吴勇庆.
</pre>
</DL>
* @param name 业务数据名称.
* @return 小于0代表失败.
* @see #GetName()
*/
	int SetName(String name);

	
/**
* 获取业务数据名称（创建于 2012.05.23）.
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
创建于 2012.05.23 - 吴勇庆.
</pre>
</DL>
* @return 业务数据名称.
* @see #SetName(String)
*/
	String GetName();


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
创建于 2012.05.23 - 吴勇庆.
</pre>
</DL>
* @param index 列序号.
* @param attrName 属性名称.
* @param attrValue 属性值.
* @return 小于0代表失败.
* @see #GetColumnAttribute(int,String)
*/
	int SetColumnAttritbute(int index, String attrName, Object attrValue);

	
/**
* 取列的属性值（创建于 2012.05.23）.
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
创建于 2012.05.23 - 吴勇庆.
</pre>
</DL>
* @param index 列序号.
* @param attrName 属性名.
* @return 属性值.
* @see #SetColumnAttribute(int,String,Object)
*/
	Object GetColumnAttritbute(int index, String attrName);

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
创建于 2012.05.23 - 吴勇庆.
</pre>
</DL>
* @param index 行序号.
* @param attrName 属性名称.
* @param attrValue 属性值.
* @return 小于0代表失败.
* @see #GetRowAttribute(int,String)
*/
	int SetRowAttritbute(int index, String attrName, Object attrValue);

	
/**
* 取行的属性值（创建于 2012.05.23）.
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
创建于 2012.05.23 - 吴勇庆.
</pre>
</DL>
* @param index 行序号.
* @param attrName 属性名.
* @return 属性值.
* @see #SetRowAttribute(int,String,Object)
*/
	Object GetRowAttritbute(int index, String attrName);
	
/**
* 根据列名取列的序号（创建于 2012.05.23）.
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
创建于 2012.05.23 - 吴勇庆.
</pre>
</DL>
* @param colName 列名.
* @return 列序号，小于0代表失败，0代表不存在.
*/
	int GetColumnIndex(String colName);

	
/**
* 获取列的总数（创建于 2012.05.23）.
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
创建于 2012.05.23 - 吴勇庆.
</pre>
</DL>
* @return 包含的列个数.
*/
	int ColumnCount();

	
/**
* 插入新行（创建于 2012.05.23）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
在Primary区中插入新行
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2012.05.23 - 吴勇庆.
</pre>
</DL>
* @param index 插入行的位置，0代表插入到最后一行.
* @return 实际行号，从1开始，小于0代表失败.
* @see #DeleteRow(int)
*/
	int InsertRow(int index);

	
/**
* 删除指定序号的行记录（创建于 2012.05.23）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
将行记录从Primary区移动到Deleted区的最后一行
本操作会改变行状态
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2012.05.23 - 吴勇庆.
</pre>
</DL>
* @param index 行序号，从1开始.
* @return 删除后的Primary区总行数，小于0代表失败.
* @see #InsertRow(int)
*/
	int DeleteRow(int index);
	
/**
* 返回指定区的行数（创建于 2012.05.23）.
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
创建于 2012.05.23 - 吴勇庆.
</pre>
</DL>
* @param SheetName 工作区名称,"*"代表所有区的总行数.
* @return 指定工作区的行数.
*/
	int RowCount(String SheetName);

	
/**
* 设定指定位置单元格的值（创建于 2012.05.23）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
对Primary区的单元格设值
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2012.05.23 - 吴勇庆.
</pre>
</DL>
* @param rowIndex 行号.
* @param colIndex 列号.
* @param val 设置的值.
* @return 小于0代表失败，等于0代表该值和原来的值相等.
* @see #GetItemValue(int,int)
*/
	int SetItemValue(int rowIndex, int colIndex, Object val);

	
/**
* 取指定位置单元格的值（创建于 2012.05.23）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
对Primary区的单元格取值
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2012.05.23 - 吴勇庆.
</pre>
</DL>
* @param rowIndex 行序号.
* @param colIndex 列序号.
* @return None代表单元格不存在,Null代表单元格为空值.
* @see #SetItemValue(int,int,Object)
*/
	Object GetItemValue(int rowIndex, int colIndex);

	
/**
* 接受数据更新（创建于 2012.05.23）.
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
创建于 2012.05.23 - 吴勇庆.
</pre>
</DL>
* @option true代表接受更新,false代表不接受更新
* @return 小于0代表失败.
*/
	int Accept(boolean option);

	
/**
* 清空指定区域的行记录（创建于 2012.05.23）.
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
创建于 2012.05.23 - 吴勇庆.
</pre>
</DL>
* @param SheetName 工作区名称,"*"代表所有区.
* @return 小于0代表失败.
*/
	int Reset(String SheetName);

	
/**
* 判断数据存储对象是否发生过改变（创建于 2012.05.23）.
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
创建于 2012.05.23 - 吴勇庆.
</pre>
</DL>
* @return true代表已发生改变,false代表未发生改变.
*/
	boolean IsModified();

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
创建于 2012.05.23 - 吴勇庆.
</pre>
</DL>
* @param sheet 指定工作区名称.
* @param index 行序号，从1开始.
* @return 删除后该区域的总行数，小于0代表失败.
*/
	int DiscardRow(String sheet,int index);
	
/**
* 移动行记录（创建于 2012.05.23）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
本操作不会影响行或列的状态
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2012.05.23 - 吴勇庆.
</pre>
</DL>
* @param sourceSheet 源工作区.
* @param rowIndex 行开始序号.
* @param rowNum 行个数.
* @param tagetSheet 目标工作区.
* @param tagetIndex 目标工作区位置,0代表最后1行.
* @return 实际移动行数,小于0代表失败.
*/
	int MoveRow(String sourceSheet,int rowIndex, int rowNum,String tagetSheet, int tagetIndex);
	
/**
* 复制行记录（创建于 2012.05.23）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
本操作不会影响行或列的状态
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2012.05.23 - 吴勇庆.
</pre>
</DL>
* @param sourceSheet 源工作区.
* @param rowIndex 行开始序号.
* @param rowNum 行个数.
* @param tagetSheet 目标工作区.
* @param tagetIndex 目标工作区位置,0代表最后1行.
* @return 实际复制行数,小于0代表失败.
*/
	int CopyRow(String sourceSheet,int rowIndex, int rowNum,String tagetSheet, int tagetIndex);
	

	
/**
* 分享某个区域的数据作为另一个数据存储对象的指定区域（创建于 2012.05.23）.
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
创建于 2012.05.23 - 吴勇庆.
</pre>
</DL>
* @param sourceSheet 源工作区名称.
* @param dataStore 目标数据存储对象.
* @param targetSheet 目标工作区名称.
* @param copy true代表以复制方式分享,false代表引用方式分享.
* @return 小于0代表失败.
*/
	int Share(String sourceSheet,IDataStore dataStore,String targetSheet,boolean copy);

}