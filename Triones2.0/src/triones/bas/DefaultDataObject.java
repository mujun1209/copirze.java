/*
 **********************************
  ,'''╭⌒╮⌒╮.',''',,',.'',,','',.  
 ╱◥██◣''o',''',,',.''.'',,',.  
｜田｜田田│ '',,',.',''',,',.''  
╬╬╬╬╬╬╬╬╬╬╬╬╬╬╬╬╬╬  \|/\|/\|/\|/\|/
			
Love life, love programming  -- mu jun

***********************************/


package triones.bas;

import java.util.HashMap;
import java.util.Map;

import Coprize.bas.IDataObject;
import Coprize.bas.IField;

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
public class DefaultDataObject extends DefaultExtend implements IDataObject {

	private String _name;
	private Map<String,DefaultField> _fields = new HashMap<String,DefaultField>();
	private Class _class;
	
	/**
	* 
	* 方法简单用途描述（创建于 2012-5-27）.
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
	* @return 数据对象名称
	* @see Coprize.bas.IDataObject#GetName()
	 */
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
	public boolean ExistField(String fieldName) {
		
		return _fields.containsKey(fieldName);
	}

	@Override
	public String[] FieldNames() {
		return _fields.keySet().toArray(new String[0]);
	}

	@Override
	public IField GetField(String fieldName, boolean isClone) {
		if(isClone){
			return _fields.get(fieldName).Clone();
		}
		return  _fields.get(fieldName);
	}

	@Override
	public int AddField(String fieldName, String fieldType) {
		_fields.put(fieldName, new DefaultField(fieldName,fieldType,null));
		return 1;
	}

	@Override
	public Class GetType() {
		return this._class;
	}

	@Override
	public int SetType(Class type) {
		this._class = type;
		return 1;
	}

	@Override
	public Object GetFieldValue(String fieldName) {
		return this._fields.get(fieldName).GetValue();
	}

	@Override
	public int SetFieldValue(String fieldName, Object value) {
		this._fields.get(fieldName).SetValue(value);
		return 1;
	}

	@Override
	public String GetFieldType(String fieldName) {
		
		return this._fields.get(fieldName).GetType();
	}

	@Override
	public int SetFieldType(String fieldName, String fieldType) {
		this._fields.get(fieldName).SetType(fieldType);
		return 1;
	}
	
}
