package triones.frame;

import triones.bas.Style;

/**
* 原型数据存储对象（创建于 2003.10.11）.
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
* @version	2003.10.11
*/
public interface Store extends RecordSet
{ 

/**
* 当前被删除行的个数（创建于 2003.10.11）.
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
创建于 2003.10.11.
</pre>
</DL>
* @return 被删除的行个数.
* @see #rowCount()
*/
	public int deletedCount();	

	
/**
* 还原行（创建于 2003.10.17）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
true:如果行记录为修改行，则恢复原始值；
false:如果行记录为新增行，则将其设为原始行；
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.10.17.
</pre>
</DL>
* @param row 行编号.
	@return true代表成功，false代表失败。
*/
	public boolean refreshRow(int row);

	
/**
* 清除行（创建于 2003.10.17）.
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
创建于 2003.10.17.
</pre>
</DL>
* @param row 行编号.
*/
	public void discardRow(int row);

}

