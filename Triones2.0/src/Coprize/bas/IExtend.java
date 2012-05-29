package Coprize.bas;

                       /* \\|///
                       \\  - -  //
                        (  @ @  )
**********************oOOo-(_)-oOOo************
*                                             *
*  Publish Date: 2012.01.04   Wu yongqing    *
*                                          *
*  We work because we wake               *
*                             Oooo        *
************************oooO**(   )*********
                       (   )   ) /
                        \ (   (_/
                         */

/**
* 扩展属性接口类（创建于 2012.01.04）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
主要用于定义可扩展属性类的接口特征
</pre>
<DT><B>使用说明：</B><DD>
<pre>
在Coprize架构中，所有对象的实例都应该具备可扩展属性的能力
</pre>
<DT><B>名词解释：</B><DD>
<pre>
可扩展属性：可在程序执行过程中动态设定对象的属性及属性值
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
 电 话：13601837519
</pre>
</DL>
* @author	吴勇庆
* @version	2012.01.04
*/
public interface IExtend
{	
	
/**
* 设置属性值（创建于 2012.01.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
如果属性不存在，则创建该属性。
如果属性已存在，则设置属性值。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2012.01.04 - 吴勇庆.
</pre>
</DL>
* @param key 属性名.
* @param val 属性值.
* @return 1代表设置成功，-1代表设置失败(属性为只读属性).
* @see #GetAttribute(String)
*/
	int SetAttribute(String key,Object val);

	
/**
* 获取属性值（创建于 2012.01.04）.
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
创建于 2012.01.04 - 吴勇庆.
</pre>
</DL>
* @param key 属性名.
* @return 属性值.
* @see #SetAttribute(String,Object)
*/
	Object GetAttribute(String key);


/**
* 获取属性名列表（创建于 2012.01.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
根据条件表达式获取满足条件的属性名数组.
“*”代表取所有的属性名
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2012.01.04 - 吴勇庆.
</pre>
</DL>
* @param cndExp 条件表达式.
* @return 符合条件的属性名数组.
*/
	String[] AttributeNames(String cndExp);

	
/**
* 判断扩展属性是否存在（创建于 2012.01.04）.
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
创建于 2012.01.04 - 吴勇庆.
</pre>
</DL>
* @param key 属性名.
* @return true代表该属性存在,false代表不存在.
*/
	boolean Contain(String key);

	
/**
* 判断属性值是否可更改（创建于 2012.01.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
如果属性不存在，则可作为新增属性设置属性值，所以返回false
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2012.01.04 - 吴勇庆.
</pre>
</DL>
* @param key 属性名.
* @return true代表是只读属性,false代表是可更改属性.
*/
	boolean IsReadOnly(String key);
}