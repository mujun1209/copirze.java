package triones.util;

import java.util.List;

/**列表公共函数库（创建于 2003.03.04）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
为方便列表对象编程而建立的公共函数库
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
* @version	2003.03.04
*/
public class ListMethod
{	
/**
* 生成对象数组（创建于 2003.03.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
与List.toArray()的不同处在于,如果list为null则返回值也为null,而不会报NullPointer错误.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.04.
</pre>
</DL>
* @param list 列表.
* @return 对象数组.
*/
	public static Object[] toArray(List list)
	{	Object[] lo_objs = null;
	
		if(list!=null)
			lo_objs = list.toArray();
				
		return lo_objs;
	}

}

