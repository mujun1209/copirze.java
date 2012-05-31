/*
 **********************************
  ,'''╭⌒╮⌒╮.',''',,',.'',,','',.  
 ╱◥██◣''o',''',,',.''.'',,',.  
｜田｜田田│ '',,',.',''',,',.''  
╬╬╬╬╬╬╬╬╬╬╬╬╬╬╬╬╬╬  \|/\|/\|/\|/\|/
			
Love life, love programming  -- mu jun

***********************************/
package Triones.bas;

import Coprize.bas.IField;

/**
* 数据字段对象默认实现（创建于 2012.01.30）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
本对象主要用于定义数据实体包含的属性（或字段）类型
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
* @author	Administrator
* @version	2012-5-9
*/
public class Field extends Extend implements IField {
	
	/**
	 * 字段标题（创建于  2012.05.27）.
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
	*/
	private String _caption;
	
	/**
	 * 字段名称（创建于  2012.05.27）.
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
	*/
	private String _name;
	
	/**
	 * 字段类型（创建于  2012.05.27）.
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
	*/
	private String _type;
	
	/**
	 * 字段值（创建于  2012.05.27）.
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
	*/
	private Object _value;
	
	/**
	 * 
	* 全部字段方法（创建于 2012-5-29）.
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
	* @param _caption 字段标题
	* @param _name 字段名称
	* @param _type 字段类型
	* @param _value 字段值
	* @see package.class#method
	 */
	public Field(String _caption, String _name, String _type,
			Object _value) {
		super();
		this._caption = _caption;
		this._name = _name;
		this._type = _type.toLowerCase();
		this._value = _value;
	}
	
	/**
	* 字段名称，类型，值的构造方法（创建于 2012-5-29）.
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
	* @param _name 字段名称
	* @param _type 字段类型
	* @param _value 字段值
	* @see package.class#method
	 */
	public Field(String _name, String _type,
			Object _value) {
		super();
		this._caption = "";
		this._name = _name;
		this._type = _type.toLowerCase();
		this._value = _value;
	}
	
	/**
	* 默认无参构造方法（创建于 2012-5-29）.
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
	* @see package.class#method
	 */
	public Field(){}

	/**
	* 获取字段标题（创建于 2012-5-29）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	一般用于在界面中显示字段的含义
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
	* @return 字段标题.
	* @see Coprize.bas.IField#GetCaption()
	 */
	@Override
	public String GetCaption() {
		return this._caption;
	}

	/**
	* 设置字段标题（创建于 2012.01.30）.
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
	创建于 2012.01.30 .
	</pre>
	</DL>
	* @param caption 字段标题.
	* @return 大于0代表设置成功.
	* @see #GetCaption()
	*/
	@Override
	public int SetCaption(String caption) {
		this._caption = caption;
		return 1;
	}

	/**
	* 获取字段名称（创建于 2012.01.30）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	与字段标题的区别是一般不用于界面显示
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2012.01.30 .
	</pre>
	</DL>
	* @return 字段名称.
	* @see #SetName(String)
	*/
	@Override
	public String GetName() {
		return this._name;
	}

	/**
	* 设置字段名称（创建于 2012.01.30）.
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
	创建于 2012.01.30 .
	</pre>
	</DL>
	* @param name 字段名称.
	* @return 大于0代表设置成功.
	* @see #GetName()
	*/
	@Override
	public int SetName(String name) {
		this._name = name;
		return 1;
	}

	/**
	* 获取字段类型（创建于 2012.01.30）.
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
	创建于 2012.01.30.
	</pre>
	</DL>
	* @return 字段类型.
	* @see #SetType(String)
	*/
	@Override
	public String GetType() {
		return this._type;
	}

	/**
	* 设置字段类型（创建于 2012.01.30）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	datatype 支持类型：
			int   ：整型
			datetime ：时间
			varchar ：字符
			number ：浮点数字
			
		
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2012.01.30 - 吴勇庆.
	</pre>
	</DL>
	* @param dataType 字段类型.
	* @return 大于0代表设置成功. -1 ：表示设置的字段类型不在支持的范围
	* @see #GetType()
	*/
	@Override
	public int SetType(String dataType) {
		String lowtype = dataType.toLowerCase();
		if(!"int".equals(lowtype) ||!"datetime".equals(lowtype)  ||!"varchar".equals(lowtype) || !"number".equals(lowtype))
			return -1;
		this._type = lowtype;
		return 1;
	}

	/**
	* 获取字段的值（创建于 2012.01.30）.
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
	创建于 2012.01.30 .
	</pre>
	</DL>
	* @return 字段值.
	* @see #SetValue(Object)
	*/
	@Override
	public Object GetValue() {
		return this._value;
	}

	/**
	* 设置字段的值（创建于 2012.01.30）.
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
	创建于 2012.01.30 .
	</pre>
	</DL>
	* @param val 新的字段值.
	* @return 大于0代表设置成功.
	* @see #GetValue()
	*/
	@Override
	public int SetValue(Object val) {
		this._value = val;
		return 1;
	}
	
}
