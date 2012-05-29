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

import Coprize.bas.IField;
import Coprize.bas.IRow;
/**
* 数据行对象（创建于 2012-5-27）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
可扩展的数据行对象，可为行增加行状态，行属性等扩展属性.
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
class DefaultRow extends DefaultExtend implements IRow {

	List<DefaultField> _row = new ArrayList<DefaultField>();
	
	public DefaultRow()
	{
		super();
		this.SetAttribute("ID", null);
		this.SetAttribute("STATUS", null);
	}
	/**
	 * 
	* 引用字段对象（创建于 2012-5-27）.
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
	* @param index 字段序号,从1开始计数.
	* @return 字段对象.
	* @see Coprize.bas.IRow#Field(int)
	 */
	@Override
	public IField Field(int index) {
		
		return _row.get(index);
	}

	/**
	 * 
	* 获取字段数量（创建于 2012-5-27）.
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
	* @return 字段数量.
	* @see Coprize.bas.IRow#FieldCount()
	 */
	@Override
	public int FieldCount() {

		return _row.size();
	}
	
}
