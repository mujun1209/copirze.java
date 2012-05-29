package triones.util;

import java.io.*;

/**
* 输入流对象函数库（创建于 2003.03.04）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
提供对输入流操作的静态方法。
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
public class InputStreamMethod	
{	

/**
* 将二进制流存到字节数组（创建于 2003.03.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
读取字符的个数取决于InputStream.available().
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
* @param inStream 二进制流.
* @return 字节数组.
* @exception java.io.IOException 读字符错误.
*/
	public static byte[] getBytes(InputStream inStream)
		throws java.io.IOException
	{	byte[] lBytes = new byte[inStream.available()];
		
		inStream.read(lBytes);
		
		return lBytes;
	}
	
}

