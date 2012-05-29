package Coprize.bas;


                       /* \\|///
                       \\  - -  //
                        (  @ @  )
**********************oOOo-(_)-oOOo************
*                                             *
*  Publish Date: 2012.05.23   Wu yongqing    *
*                                          *
*  We work because we wake               *
*                             Oooo        *
************************oooO**(   )*********
                       (   )   ) /
                        \ (   (_/
                         */


/**
* 数据行对象（创建于 2012.05.23）.
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
* @version	2012.05.23
*/

public interface IRow extends IExtend
{
	
/**
* 引用字段对象（创建于 2012.05.23）.
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
创建于 2012.05.23 - 吴勇庆.
</pre>
</DL>
* @param index 字段序号,从1开始计数.
* @return 字段对象.
*/
	IField Field(int index);
	
/**
* 获取字段数量（创建于 2012.05.23）.
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
创建于 2012.05.23 - 吴勇庆.
</pre>
</DL>
* @return 字段数量.
*/
	int FieldCount();
}