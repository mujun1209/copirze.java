package Coprize.bas;
                      /* \\|///
                       \\  - -  //
                        (  @ @  )
**********************oOOo-(_)-oOOo************
*                                             *
*  Publish Date: 2012.02.02   Wu yongqing    *
*                                          *
*  We work because we wake               *
*                             Oooo        *
************************oooO**(   )*********
                       (   )   ) /
                        \ (   (_/
                         */


/**
* 数据对象接口（创建于 2012.02.02）.
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
 email：wuyqb@163.com 
 电 话：021-65804142
</pre>
</DL>
* @author	吴勇庆
* @version	2012.02.02
*/
public interface IDataObject extends IExtend
{
	
/**
* 获取数据对象的名称（创建于 2012.02.02）.
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
创建于 2012.02.02 - 吴勇庆.
</pre>
</DL>
* @return 数据对象名称.
* @see #SetName(String)
*/
	String GetName();
	

	
/**
* 设置数据对象的名称（创建于 2012.02.02）.
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
创建于 2012.02.02 - 吴勇庆.
</pre>
</DL>
* @param name 实体类名.
* @return 大于0代表设置成功.
* @see #GetName()
*/
	int SetName(String name);
	
/**
* 根据字段名判断字段是否存在（创建于 2012.02.02）.
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
创建于 2012.02.02 - 吴勇庆.
</pre>
</DL>
* @param fieldName 字段名称.
* @return true代表存在,false代表不存在.
*/
	boolean ExistField(String fieldName);

	
/**
* 获取字段名集合（创建于 2012.02.02）.
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
创建于 2012.02.02 - 吴勇庆.
</pre>
</DL>
* @return 字段名数组.
*/
	String[] FieldNames();
	

	
/**
* 根据字段名取字段对象（创建于 2012.02.02）.
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
创建于 2012.02.02 - 吴勇庆.
</pre>
</DL>
* @param fieldName 字段名称.
* @param isClone false代表引用该字段对象,true代表克隆一个新的字段对象.
* @return 字段对象，如果字段不存在则返回null.
*/
	IField GetField(String fieldName,boolean isClone);


	
/**
* 增加数据对象的字段（创建于 2012.02.02）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
字段类型为:varchar/int/datetime/number
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2012.02.02 - 吴勇庆.
</pre>
</DL>
* @param fieldName 字段名称.
* @param fieldType 字段类型,大小写不敏感.
* @return 大于0代表增加字段成功.
*/
	int AddField(String fieldName,String fieldType);
	

	
/**
* 获取数据对象的实体类型（创建于 2012.02.02）.
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
创建于 2012.02.02 - 吴勇庆.
</pre>
</DL>
* @return 实体类型.
* @see #SetType(Class)
*/
	Class GetType();

	
/**
* 设置数据对象的实体类型（创建于 2012.02.02）.
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
创建于 2012.02.02 - 吴勇庆.
</pre>
</DL>
* @param type 实体类型.
* @return 大于0代表设置成功.
* @see #GetType()
*/
	int SetType(Class type);
	
/**
* 获取字段的值（创建于 2012.02.02）.
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
创建于 2012.02.02 - 吴勇庆.
</pre>
</DL>
* @param fieldName 字段名称.
* @return 字段的值,如果字段不存在则返回None.
* @see #SetFieldValue(String,Object)
*/
	Object GetFieldValue(String fieldName);
	
/**
* 设置字段的值（创建于 2012.02.02）.
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
创建于 2012.02.02 - 吴勇庆.
</pre>
</DL>
* @param fieldName 字段名称.
* @param value 字段值.
* @return 大于0代表设置成功.
* @see #GetFieldValue(String)
*/
	int SetFieldValue(String fieldName,Object value);
	

	
/**
* 获取字段类型（创建于 2012.02.02）.
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
创建于 2012.02.02 - 吴勇庆.
</pre>
</DL>
* @param fieldName 字段名称.
* @return 字段类型,如果字段不存在则返回"".
* @see #SetFieldType(String,String)
*/
	String GetFieldType(String fieldName);

	
/**
* 设置字段类型（创建于 2012.02.02）.
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
创建于 2012.02.02 - 吴勇庆.
</pre>
</DL>
* @param fieldName 字段名称.
* @param fieldType 字段类型.
* @return 大于0代表设置成功.
* @see #GetFieldType(String)
*/
	int SetFieldType(String fieldName,String fieldType);
}