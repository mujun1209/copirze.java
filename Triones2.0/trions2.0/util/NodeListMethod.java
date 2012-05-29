package trionesII.util;

import org.w3c.dom.*;
import java.util.ArrayList;

/**
* 节点列表重用函数库（创建于 2003.03.04）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
提供用于操作NodeList（与XML相关）的静态方法.
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
public class NodeListMethod 
{
/**
* 根据节点列表生成元素数组（创建于 2003.03.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
如果节点类型不为Node.ElEMENT_NODE,则被忽略。
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
* @param n 节点列表.
* @return 根据节点列表生成的元素数组.
*/
  public static Element[] toElements(NodeList n)
	{ ArrayList lArray = new ArrayList();
    
		for (int i = 0;i < n.getLength(); i++)
    	if (n.item(i).getNodeType() == n.item(i).ELEMENT_NODE)
      	lArray.add((Element)n.item(i));

    Element[] f = new Element[lArray.size()];
		
		lArray.toArray(f);
		
		return f;    
  }
}

