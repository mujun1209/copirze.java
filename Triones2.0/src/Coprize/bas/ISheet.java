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
 email：wuyqb@163.com 
 电 话：021-65804142
</pre>
</DL>
* @author	吴勇庆
* @version	2012.05.23
*/

public interface ISheet extends IExtend
{
	/** 增加一个数据行（创建于2011.12.13）
	 <example>
	 <code>
	 略
	 </code>
	 <p>说明</p>
	 <code>
	 增加一个数据行
	 </code>
	 <p>日志</p>
	 <code>
	 创建于2011.12.13 – 钱文豪
	 </code>
	 </example>
	 @param row 行对象
	 @return 
	 int - 数据行序号(小于0则说明新增失败).
	 
	*/


	
/**
* 增加数据行（创建于 2012.05.23）.
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
* @param index 新增行插入位置,0代表最后1行.
* @param row 数据行对象.
* @return 插入后实际的行序号.
* @see #RemoveRow(int)
*/
	int AddRow(int index,IRow row);
	
/**
* 删除数据行（创建于 2012.05.23）.
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
* @param index 数据行号.
* @return 删除后的总行数，小于0代表失败.
* @see #AddRow(int,IRow)
*/
	int RemoveRow(int index);


	/** 获取单元格对象（创建于2012.05.22）
	 <example>
	 <code>
	 略
	 </code>
	 <p>说明</p>
	 <code>
	 略
	 </code>
	 <p>日志</p>
	 <code>
	 创建于2012.05.22 – 钱文豪
	 </code>
	 </example>
	 @param rowIndex 行号
	 @param columnName 列名
	 @return 
	 IField - 单元格对象.
	 
	*/

	
/**
* 引用单元格对象（创建于 2012.05.23）.
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
* @param rowIndex 行号,从1开始.
* @param columnIndex 列号，从1开始.
* @return 单元格对象.
*/

	IField Item(int rowIndex, int columnIndex);
	
/**
* 总行数（创建于 2012.05.23）.
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
* @return 二维表中的数据行数.
*/
	int RowCount();
}