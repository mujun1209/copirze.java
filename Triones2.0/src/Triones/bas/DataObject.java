/*
 **********************************
  ,'''╭⌒╮⌒╮.',''',,',.'',,','',.  
 ╱◥██◣''o',''',,',.''.'',,',.  
｜田｜田田│ '',,',.',''',,',.''  
╬╬╬╬╬╬╬╬╬╬╬╬╬╬╬╬╬╬  \|/\|/\|/\|/\|/
			
Love life, love programming  -- mu jun

 ***********************************/

package Triones.bas;

import java.util.HashMap;
import java.util.Map;

import Coprize.bas.IDataObject;
import Coprize.bas.IField;

/**
 * <img src="new.gif" width="28" height="11" border="0">数据对象接口（创建于 2012.02.02）.
 * <DL>
 * <DT><B>对象概述：</B>
 * <DD>
 * 
 * <pre>
 * 略
 * </pre>
 * 
 * <DT><B>使用说明：</B>
 * <DD>
 * 
 * <pre>
 * 略
 * </pre>
 * 
 * <DT><B>名词解释：</B>
 * <DD>
 * 
 * <pre>
 * 略
 * </pre>
 * 
 * <DT><B>注意事项：</B>
 * <DD>
 * 
 * <pre>
 * 基于JDK1.3
 * </pre>
 * 
 * <DT><B>展望未来：</B>
 * <DD>
 * 
 * <pre>
 * 略
 * </pre>
 * 
 * <DT><B>联系方式：</B>
 * <DD>
 * 
 * <pre>
 *  email：360144561@qq.com 
 *  电 话：13585880448
 * </pre>
 * 
 * </DL>
 * 
 * @author mujun
 * @version 2012-5-9
 */
public class DataObject extends Extend implements IDataObject {

	/**
	 * 数据对象名称（创建于  2012.05.27）.
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
	 * 数据对象的字段集合（创建于  2012.05.27）.
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
	private Map<String, Field> _fields = new HashMap<String, Field>();
	
	/**
	 * 实体类型（创建于  2012.05.27）.
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
	private Class _class;

	/**
	 * 
	 * 获取数据对象的名称（创建于 2012-5-29）.
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
	 * @return 数据对象名称
	 * @see Coprize.bas.IDataObject#GetName()
	 */
	@Override
	public String GetName()
	{
		return this._name;
	}

	/**
	 * 
	 * 设置数据对象的名称（创建于 2012-5-29）.
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
	 *            实体类名.
	 * @return 大于0代表设置成功.
	 * @see Coprize.bas.IDataObject#SetName(java.lang.String)
	 */
	@Override
	public int SetName(String name)
	{
		this._name = name;
		return 1;
	}

	/**
	 * 根据字段名判断字段是否存在（创建于 2012-5-29）.
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
	 * 创建于 2012-5-29.
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @param fieldName
	 *            字段名称.
	 * @return true代表存在,false代表不存在.
	 */
	@Override
	public boolean ExistField(String fieldName)
	{

		return _fields.containsKey(fieldName);
	}

	/**
	 * 获取字段名集合（创建于 2012-5-29）.
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
	 * @return 字段名数组
	 */
	@Override
	public String[] FieldNames()
	{
		return _fields.keySet().toArray(new String[0]);
	}

	/**
	 * 根据字段名取字段对象（创建于 2012-5-29）.
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
	 * 创建于 2012-5-29.
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @param fieldName
	 *            字段名称.
	 * @param isClone
	 *            false代表引用该字段对象,true代表克隆一个新的字段对象.
	 * @return 字段对象，如果字段不存在则返回null.
	 */
	@Override
	public IField GetField(String fieldName, boolean isClone)
	{
		if (isClone)
		{
			try
			{
				return (IField) _fields.get(fieldName).CloneObject();
			}
			catch (Exception e)
			{
				return null;
			}

		}
		return _fields.get(fieldName);
	}

	/**
	 * 增加数据对象的字段（创建于 2012-5-29）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	字段类型为:varchar/int/datetime/number
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
	 * @param fieldName
	 *            字段名称.
	 * @param fieldType
	 *            字段类型,大小写不敏感.
	 * @return 大于0代表增加字段成功.
	 */
	@Override
	public int AddField(String fieldName, String fieldType)
	{
		_fields.put(fieldName, new Field(fieldName, fieldType, null));
		return 1;
	}

	/**
	 * 获取数据对象的实体类型（创建于 2012-5-29）.
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
	 * @return 实体类型.
	 * @see #SetType(Class)
	 */
	@Override
	public Class GetType()
	{
		return this._class;
	}

	/**
	 * 设置数据对象的实体类型（创建于 2012-5-29）.
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
	 * @param type
	 *            实体类型.
	 * @return 大于0代表设置成功.
	 * @see #GetType()
	 */
	@Override
	public int SetType(Class type)
	{
		this._class = type;
		return 1;
	}

	/**
	 * 获取字段的值（创建于 2012-5-29）.
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
	 * 创建于 2012-5-29.
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @param fieldName
	 *            字段名称.
	 * @return 字段的值,如果字段不存在则返回None.
	 * @see #SetFieldValue(String,Object)
	 */
	@Override
	public Object GetFieldValue(String fieldName)
	{
		if (_fields.containsKey(fieldName))
		{
			return this._fields.get(fieldName).GetValue();
		}
		return null;
	}

	/**
	 * 设置字段的值（创建于 2012-5-29）.
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
	 * @param fieldName
	 *            字段名称.
	 * @param value
	 *            字段值.
	 * @return 大于0代表设置成功. -1:表示字段名称不存在
	 * @see #GetFieldValue(String)
	 */
	@Override
	public int SetFieldValue(String fieldName, Object value)
	{
		if(_fields.containsKey(fieldName))
		{
			this._fields.get(fieldName).SetValue(value);
		}
		
		return -1;
	}

	/**
	 * 获取字段类型（创建于 2012-5-29）.
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
	 * @param fieldName
	 *            字段名称.
	 * @return 字段类型,如果字段不存在则返回"".
	 * @see #SetFieldType(String,String)
	 */
	@Override
	public String GetFieldType(String fieldName)
	{
		if(_fields.containsKey(fieldName))
		{
			return this._fields.get(fieldName).GetType();
		}
		return "";
		
	}

	/**
	 * 设置字段类型（创建于 2012-5-29）.
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
	 * @param fieldName
	 *            字段名称.
	 * @param fieldType
	 *            字段类型.
	 * @return 大于0代表设置成功. 0表示字段不存在 -1:表示字段类型错误
	 * @see #GetFieldType(String)
	 */
	@Override
	public int SetFieldType(String fieldName, String fieldType)
	{
		if(_fields.containsKey(fieldName))
		{
			return this._fields.get(fieldName).SetType(fieldType);
		}
		else
			return 0;
	}

}
