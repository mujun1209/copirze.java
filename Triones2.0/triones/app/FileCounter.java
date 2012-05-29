package triones.app;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

/**
* <img src="new.gif" width="28" height="11" border="0">我的计数器（创建于 2002.12.13）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
计数值会存放到指定的文件里。
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
* @version	2002.12.13
*/
public class FileCounter implements Counter
{	
	
/**
* 文件路径（创建于 2002.12.13）.
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
创建于 2002.12.13.
</pre>
</DL>
@see 	#setPath(String)
@see	#getPath()
*/
	private String _FilePath;	
	
/**
* 当前的计数值（创建于 2002.12.13）.
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
创建于 2002.12.13.
</pre>
</DL>
* @see #current()
	@see #next()
*/
	private long _Current;
	
/**
* 文件是否被锁定（创建于 2002.12.13）.
<DL>
<DT><B>说明：</B><DD>
<pre>
为了减少应写文件而带来的性能下降，采用空闲写入，不等待的策略。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2002.12.13.
</pre>
</DL>
* @see #save()
*/
	boolean _FileLocked = false;
	

	
/**
* 构造方法（创建于 2002.12.13）.
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
创建于 2002.12.13.
</pre>
</DL>
* @param filePath 计数器文件路径.
*/
	public FileCounter(String filePath)
	{	setPath(filePath + ".count");
		load();
	}
	

	
/**
* 构造方法（创建于 2002.12.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
采用默认的文件名（不含路径的类名）
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2002.12.13.
</pre>
</DL>
* @see #FileCounter(String)
*/
	public FileCounter()
	{	setPath("FileCounter");
		load();
	}
	
/**
* 设定计数器文件路径（创建于 2002.12.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
如果该文件路径员，原来不存在，则会新建一个。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2002.12.13.
</pre>
</DL>
* @param filePath 文件路径.
* @see #getPath()
*/
	public void setPath(String filePath)
	{ _FilePath = filePath;
	}
	
/**
* 获取当前设定的计数文件路径（创建于 2002.12.13）.
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
创建于 2002.12.13.
</pre>
</DL>
* @return 当前设定的计数文件路径.
* @see #setPath(String)
*/
	public String getPath()
	{ return _FilePath;
	}
		
/**
* 取当前的计数值（创建于 2002.12.13）.
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
创建于 2002.12.13.
</pre>
</DL>
* @return 当前的计数值.
* @see #next()
*/
	public long current()
	{	return _Current;
	}

/**
* 计数器累加（创建于 2002.12.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
计数器累加，并将根据存储策略存放到计数文件中。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2002.12.13.
</pre>
</DL>
* @return 累加后的计数值.
* @see #current()
*/
	public long next()
	{	_Current ++;
		save();
		return _Current;
	}
	
/**
* 计数器清0（创建于 2002.12.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
并不直接影响文件中的数值
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2002.12.13.
</pre>
</DL>
*/
	public void reset()
	{	_Current = 0;
	}
	
/**
* 载入文件中的数值（创建于 2002.12.13）.
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
创建于 2002.12.13.
</pre>
</DL>
*/
	public void load()
	{	try
		{	String ls_text = readFile(_FilePath);
			_Current = Integer.parseInt(ls_text);		
		}
		catch(Exception e)
		{ _Current = 0;
			save();
		}		
	}
	

	
/**
* 保存当前计数器的值到文件（创建于 2002.12.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
本方法一般不直接使用，除非强制保存。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2002.12.13.
</pre>
</DL>
* @return 保存是否成功.
* @see #next()
*/
	public boolean save()
	{	try
		{	if(_FileLocked) return false;
		
			_FileLocked = true;
			writeFile(_FilePath,_Current + "");
			_FileLocked = false;
			
			return true;
		}
		catch(Exception e)
		{	System.out.println("FileCounter can't write the login number to " + _FilePath);
			return false;
		}
	}
	
/**
* 读文件（创建于 2002.12.13）.
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
创建于 2002.12.13.
</pre>
</DL>
* @param filePath 文件路径.
* @return 文件的首行信息.
* @exception FileNotFoundException 文件路径不存在.
* @exception IOException 文件读写错误.
* @see #writeFile(String,String)
*/
	private static String readFile(String filePath) 
		throws FileNotFoundException,IOException
	{ BufferedReader lBReader = new BufferedReader(new FileReader(filePath));
		String ls_text = lBReader.readLine(); 
		lBReader.close();
		return ls_text; 
	} 

/**
* 将信息写入文件的首行（创建于 2002.12.13）.
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
创建于 2002.12.13.
</pre>
</DL>
* @param filePath 文件路径.
* @param text 信息.
* @exception FileNotFoundException 文件路径不存在.
* @exception IOException 文件读写错误.
* @see package.class
*/
	private static void writeFile(String filePath,String text) 
		throws FileNotFoundException,IOException
	{	PrintWriter lPWriter = new PrintWriter(new FileOutputStream(filePath)); 
		lPWriter.println(text); 
		lPWriter.close(); 		
	} 
	


	
/**
* 当计数器资源被释放时，将当前计数值自动写入文件（创建于 2002.12.13）.
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
创建于 2002.12.13.
</pre>
</DL>
*/
	protected void finalize()
	{	save();
	}
	
}

