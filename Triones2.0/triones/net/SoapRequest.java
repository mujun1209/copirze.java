package triones.net;

import java.net.URL;
import java.util.Vector;
//import org.apache.soap.rpc.Call;

/**
* SOAP客户端类（创建于 2007.11.18）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
主要用于规范SOAP客户端应用类的调用模式，简化实现过程
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
 电 话：021-65804142
</pre>
</DL>
* @author	吴勇庆
* @version	2007.11.18
*/

public class SoapRequest
{
	URL _URL;
	Vector _ParamList;
	

	
/**
* 添加远程调用的方法参数（创建于 2007.11.18）.
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
创建于 2007.11.18.
</pre>
</DL>
* @param name 参数名称.
* @param type 参数类型.
* @param val 参数值.
*/
	public void addParam(String name,Class type,Object val)
	{		
	}
	
	public void addParam(String name,String val)
	{
		addParam(name,String.class,val);
	}
	public void addParam(String name,int val)
	{
		addParam(name,Integer.class,new Integer(val));
	}
	public void addParam(String name,double val)
	{
		addParam(name,Double.class,new Double(val));
	}
	public void addParam(String name,boolean val)
	{
		addParam(name,Boolean.class,new Boolean(val));
	}
	public void addParam(String name,long val)
	{
		addParam(name,Long.class,new Long(val));
	}
	public void addParam(String name,short val)
	{
		addParam(name,Short.class,new Short(val));
	}
	public void addParam(String name,char val)
	{
		addParam(name,Character.class,new Character(val));
	}
	public void addParam(String name,float val)
	{
		addParam(name,Float.class,new Float(val));
	}
	public void addParam(String name,byte val)
	{
		addParam(name,Byte.class,new Byte(val));
	}
	/*
	public void call(String function,String );
	public String callString();
	public double callDouble();
	public int callInt();
	public Object callObject();
	public List callList();
	*/

}
