package triones.frame;


/**
* 二维表格存储空间接口（创建于 2005.04.05）.
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
 电 话：021-65804142
</pre>
</DL>
* @author	吴勇庆
* @version	2005.04.05
*/
public interface Sheet extends SheetTag
{

  
/**
* 行记录个数（创建于 2005.04.05）.
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
创建于 2005.04.05.
</pre>
</DL>
* @return 行数.
*/
  public int rowCount();

  
/**
* 插入新行（创建于 2005.04.05）.
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
创建于 2005.04.05.
</pre>
</DL>
* @param row 插入的行编号,0代表插入到最后.
* @return >0,代表新行的实际位置.
* @see #deleteRow(int)
*/
	public int insertRow(int row);

  
/**
* 删除指定行记录（创建于 2005.04.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
行记录的编号范围: ≥1
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2005.04.05.
</pre>
</DL>
* @param row 行编号.
* @see #insertRow(int)
*/
	public void deleteRow(int row);

  
/**
* 清空所有的行记录（创建于 2005.04.05）.
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
创建于 2005.04.05.
</pre>
</DL>
*/
	public void reset();
  
/**
* 取单元值（创建于 2005.04.05）.
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
创建于 2005.04.05.
</pre>
</DL>
* @param row 行编号.
* @param colID 列编号.
* @return 单元值.
* @see #setItemValue(int,int,Object)
*/
	public Object getItemValue(int row,int colID);


/**
* 给单元赋值（创建于 2005.04.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
值和列的数据类型的必须匹配
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2005.04.05.
</pre>
</DL>
* @param row 行编号.
* @param colID 列编号.
* @param value 单元值.
* @see #getItemValue(int,int)
* @return -1代表失败.
*/
  public int setItemValue(int row,int colID,Object value);
/**
* 给单元属性赋值（创建于 2005.04.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
值和列的数据类型的必须匹配
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2005.04.05.
</pre>
</DL>
* @param row 行编号.
* @param colID 列编号.
* @param property 属性名.
* @param value 属性值.
* @return -1代表失败.
* @see #getItemAttribute(int,int,String)
*/
	public int setItemAttribute(int row,int colID,String property,Object value);
/**
* 取单元属性值（创建于 2005.04.05）.
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
创建于 2005.04.05.
</pre>
</DL>
* @param row 行编号.
* @param colID 列编号.
* @param property 属性名.
* @return 属性值.
* @see #setItemAttribute(int,int,String,Object)
*/
  public Object getItemAttribute(int row,int colID,String property);
  
}

