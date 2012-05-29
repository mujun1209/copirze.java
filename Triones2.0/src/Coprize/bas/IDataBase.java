package Coprize.bas;



                       /* \\|///
                       \\  - -  //
                        (  @ @  )
**********************oOOo-(_)-oOOo************
*                                             *
*  Publish Date: 2012.05.22   Wu yongqing    *
*                                          *
*  We work because we wake               *
*                             Oooo        *
************************oooO**(   )*********
                       (   )   ) /
                        \ (   (_/
                         */


/**
* 数据集合管理器（创建于 2012.05.22）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
主要用于管理应用服务对象或界面导航对象包含的相关信息
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
* @version	2012.05.22
*/

public interface IDataBase
{
	
/**
* 设置指定标识的数据容器（创建于 2012.05.22）.
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
创建于 2012.05.22 - 吴勇庆.
</pre>
</DL>
* @param key 数据容器标识.
* @param dStore 数据容器对象.
* @return 大于0代表设置成功，小于0代表设置失败
* @see #GetDataStore(String)
*/
	int SetDataStore(String key, IDataStore dStore);


	
/**
* 获取指定标识的数据容器（创建于 2012.05.22）.
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
创建于 2012.05.22 - 吴勇庆.
</pre>
</DL>
* @param key 数据容器标识.
* @return 数据容器对象,NULL代表不存在.
* @see #SetDataStore(String,IDataStore)
*/
	IDataStore GetDataStore(String key);
	
/**
* 设置属性值（创建于 2012.05.22）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
主要用来传递单个数据值或字符串表达式
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2012.05.22 - 吴勇庆.
</pre>
</DL>
* @param key 属性标识.
* @param val 属性值.
* @return 大于0代表设置成功，小于0代表设置失败
* @see #GetProperty(String)
*/
	int SetProperty(String key, Object val);

	
/**
* 获取属性值（创建于 2012.05.22）.
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
创建于 2012.05.22 - 吴勇庆.
</pre>
</DL>
* @param key 属性标识.
* @return 属性值或字符串表达式,NULL代表不存在.
* @see #SetProperty(String,Object)
*/
	Object GetProperty(String key);
}