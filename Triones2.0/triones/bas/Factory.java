package triones.bas;


/**
* 基因工厂接口（创建于 2003.03.07）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
基因工厂接口主要用于统一制造基因的生产过程和方法。

基因指一切可以被创建的信息实体.

基因工厂尤其适用于那些根据外部需要自动生成指定信息实体的场景.
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
* @version	2003.03.07
*/
public interface Factory
{	

	
/**
* 构造类实例（创建于 2003.03.07）.
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
创建于 2003.03.07.
</pre>
</DL>
* @param fields 属性名列表.
* @param vals 属性值列表.
* @return 对象实例.
* @exception IllegalAccessException 对应类的属性值必须初始化.
* @exception InstantiationException 对应类不存在，或者是一个抽象类，或者构造函数全都有参数.
*/
	public Object newInstance(String[] fields,Object[] vals)
		throws InstantiationException,IllegalAccessException;
	

	
/**
* 取属性名数组（创建于 2007.09.05）.
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
创建于 2007.09.05.
</pre>
</DL>
* @return 属性名数组.
*/
	public String[] getFieldNames();
	
/**
* 构造类实例（创建于 2003.03.07）.
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
创建于 2003.03.07.
</pre>
</DL>
* @param fields 属性列表.
* @param vals 属性值列表.
* @return 对象实例.
* @exception IllegalAccessException 对应类的属性值必须初始化.
* @exception InstantiationException 对应类不存在，或者是一个抽象类，或者构造函数全都有参数.
*/
//	public Object newInstance(int[] fields,Object[] vals)
//		throws InstantiationException,IllegalAccessException;
		
/**
* 根据属性名返回属性标识（创建于 2003.03.11）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
返回值-1代表属性名不存在.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.11.
</pre>
</DL>
* @param fieldNames 属性名.
* @return 属性标识列表.
*/
	//public int[] getFieldIDs(String[] fieldNames);	

}

