package triones.bas;

import java.io.InputStream;
import java.io.IOException;
import java.io.Reader;


/**
* Reader输入流（创建于 2003.08.14）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
&nbsp;&nbsp;&nbsp;&nbsp;Reader是一种字符流，其功能类似于以InputStream为基础的字节流；ReaderInputStream主要用于将Reader
转换为InputStream,与java.io.InputStreamReader一起，两者共同建立起了Reader和InputStream两大家族之间转换的桥梁。
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
基于JDK1.4
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
* @version	2003.08.14
*/
public class ReaderInputStream extends InputStream
{ 
/**
* 内置Reader（创建于 2003.08.14）.
<DL>
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
创建于 2003.08.14.
</pre>
</DL>
*/
	private Reader _Reader;


	
/**
* 构造方法，将Reader构造成InputStream（创建于 2003.08.14）.
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
创建于 2003.08.14.
</pre>
</DL>
* @param r 阅读器.
*/
	public ReaderInputStream(Reader r)
	{ _Reader = r;
	}


	
/**
* 可获得的字节数（创建于 2003.08.14）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
由于单个字符对应于字节数的不确定性，因此本方法只返回0；
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.08.14.
</pre>
</DL>
* @return 可获得的字节数，只返回0.
*/
	public int available()
	{ return 0;
	}
	

	
/**
* 关闭输入流（创建于 2003.08.14）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
本方法用于释放输入流所占的资源。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.08.14.
</pre>
</DL>
* @exception IOException 输入流错误.
*/
	public void close()
		throws IOException
	{ _Reader.close();
	}
	

	
/**
* 定位输入流的回滚点（创建于 2003.08.14）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
当输入流读取字节后，其指针会移位到下次可读取的字节位置。
回滚点主要用于设定可以回退的指针位置。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.08.14.
</pre>
</DL>
* @param readlimit 限制读取的字节数;当读取的字节数超过限制个数而未进行回滚操作时，回滚点的设置可能失效.
* @see #markSupported()
	@see #reset()
*/
	
	public void mark(int readlimit)
	{ try
		{	_Reader.mark(readlimit);
		}
		catch(IOException e)
		{ e.printStackTrace();
		}
	}
	

	
/**
* 判断输入流是否支持设定回滚点（创建于 2003.08.14）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
并非所有的输入流都支持回滚操作。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.08.14.
</pre>
</DL>
* @return true代表支持，false代表不支持.
* @see #mark(int)
*/
	public boolean markSupported()
	{	return _Reader.markSupported();		
	}
	

	
/**
* 读取字节对应的整数（创建于 2003.08.14）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
本方法实际的读取单位是字符。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.08.14.
</pre>
</DL>
* @return 字符对应的整数，-1代表输入流指针已到达末尾.
* @exception IOException 输入流错误.
* @see #read(byte[])
	@see #read(byte[],int,int)
*/
	public int read()
		throws IOException
	{ return _Reader.read();
	}

	
/**
* 读取字符到指定字节数组中（创建于 2003.08.14）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
理论上每次读取的字节数应达到字节数组的长度，但由于字符对应字节数的不确定性，可能会小于字节数组长度。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.08.14.
</pre>
</DL>
* @param b 字节数组.
* @return 实际读取的字节数，-1代表输入流指针已到达末尾，0代表为读取任何字节.
* @exception IOException 输入流错误.
* @see #read()
	@see #read(byte[],int,int)
*/
	public int read(byte[] b)
		throws IOException
	{ return read(b,0,b.length);
	}
	

	
/**
* 读取字符到指定字节数组中（创建于 2003.08.14）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
理论上每次读取的字节数应达到指定的长度，但由于字符对应字节数的不确定性，可能会小于该长度。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.08.14.
</pre>
</DL>
* @param b 字节数组.
* @param off 将读到的字节插入到字节数组的起始位置.
* @param len 读取的字节个数.
* @return 实际读取的字节数，-1代表输入流指针已到达末尾，0代表为读取任何字节.
* @exception IOException 输入流错误.
* @see #read()
	@see #read(byte[])
*/
	public int read(byte[] b, int off, int len)  
		throws IOException
	{ int c;
		int i = 0;

		while((c = _Reader.read()) > -1)
		{	String s = ((char)c) + "";
			byte[] b1 = s.getBytes();
			
			if((i + b1.length)<len)
				for(int j=0;j<b1.length;j++,i++)
				{	b[off + i]=b1[j];
				}
			else
			{	_Reader.reset();
				break;			
			}
			_Reader.mark(1);
		}
		
		if(i==0&&c==-1) return -1;
		
		return i;
	}

	
/**
* 重新定位到回滚点（创建于 2003.08.14）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
如果没有设定回滚点，则指针直接回滚到输入流的起始位置。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.08.14.
</pre>
</DL>
* @exception IOException 输入流错误.
* @see #mark(int)
*/
	public void reset()
		throws IOException
	{	_Reader.reset();
	}

	
/**
* 跳过指定个数的字节（创建于 2003.08.14）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
本方法实际是跳过指定个数的字符。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.08.14.
</pre>
</DL>
* @param n 需要跳过的字符个数，不允许为负数.
* @return 实际跳过的字符个数.
* @exception IOException 输入流错误.
*/
	public long skip(long n)
	  throws IOException
	{	return _Reader.skip(n);
	}

}

