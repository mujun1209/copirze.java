/**
* 对象中文名（创建于 2012-5-9）.
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
* @version	2012-5-9
*/
package triones.bas;

import Coprize.bas.IField;

public class DefaultField extends DefaultExtend implements IField {

	private String _caption;
	private String _name;
	private String _type;
	private Object _value;
	
	
	public DefaultField(String _caption, String _name, String _type,
			Object _value) {
		super();
		this._caption = _caption;
		this._name = _name;
		this._type = _type;
		this._value = _value;
	}
	
	public DefaultField(String _name, String _type,
			Object _value) {
		super();
		this._caption = "";
		this._name = _name;
		this._type = _type;
		this._value = _value;
	}
	
	public DefaultField(){}

	@Override
	public String GetCaption() {
		return this._caption;
	}

	@Override
	public int SetCaption(String caption) {
		this._caption = caption;
		return 1;
	}

	@Override
	public String GetName() {
		return this._name;
	}

	@Override
	public int SetName(String name) {
		this._name = name;
		return 1;
	}

	@Override
	public String GetType() {
		return this._type;
	}

	@Override
	public int SetType(String dataType) {
		if(!"int".equals(dataType) ||!"datetime".equals(dataType)  ||!"string".equals(dataType) || !"number".equals(dataType))
		{
			return -1;
		}
		this._type = dataType;
		
		return 1;
	}

	@Override
	public Object GetValue() {
		return this._value;
	}

	@Override
	public int SetValue(Object val) {
		this._value = val;
		return 1;
	}
	
	public DefaultField Clone()
	{
		 DefaultField o = null;  
	        try {  
	            o = (DefaultField) super.clone();  
	        } catch (CloneNotSupportedException e) {  
	            e.printStackTrace();  
	        }  
	        return o;  
	}
	
	
}
