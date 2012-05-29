package triones;

import java.util.*;
import java.io.*;


                       /* \\|///
                       \\  - -  //
                        (  @ @  )
**********************oOOo-(_)-oOOo************
*                                             *
*  Publish Date: 2004.06.11   Wu yongqing    *
*                                          *
*  We work because we wake               *
*                             Oooo        *
************************oooO**(   )*********
                       (   )   ) /
                        \ (   (_/
                         */
import triones.sql.*;
import triones.util.*;
import triones.frame.*;
import triones.xml.*;
import triones.bas.MyException;

/**
* �����ࣨ������ 2003.01.05��.
<DL>
<DT><B>���������</B><DD>
<pre>
������Ҫ�ṩ����trionesII�ڲ����Եķ�����
</pre>
<DT><B>ʹ��˵����</B><DD>
<pre>
��
</pre>
<DT><B>���ʽ��ͣ�</B><DD>
<pre>
��
</pre>
<DT><B>ע�����</B><DD>
<pre>
����JDK1.3
</pre>
<DT><B>չ��δ����</B><DD>
<pre>
��
</pre>
<DT><B>��ϵ��ʽ��</B><DD>
<pre>
 email��wyq@triok.com 
 ��  ��:021-68672222-2013
</pre>
</DL>
* @author	������
* @version	2003.01.05
*/
public class Debug
{	
/**
* �Ƿ�򿪵������ԣ������� 2003.01.05��.
<DL>
<DT><B>˵����</B><DD>
<pre>
��������Ϊtrue���������ʱ�Ὣ���õ��ĵ������ݴ�ӡ������
</pre>
<DT><B>ʾ����</B><DD>
<pre>
��
</pre>
<DT><B>��־��</B><DD>
<pre>
������ 2003.01.05
</pre>
</DL>
*/
	public static boolean IFDebug = true;
	
/**
* triones�İ汾��Ϣ�������� 2003.01.05��.
<DL>
<DT><B>˵����</B><DD>
<pre>
����triones�İ汾�š��������ڡ����ߵ���Ϣ��
��ǰ�汾��Ϣ��3.0����ʽ�棩 2004.02.06 - Wu YongQing
</pre>
<DT><B>ʾ����</B><DD>
<pre>
��
</pre>
<DT><B>��־��</B><DD>
<pre>
������ 2003.02.28
</pre>
</DL>
*/
	public static String VERSION = "3.02����ʽ�棩 2006.02.23 - Wu YongQing";

	
/**
* ��ӡ������Ϣ�������� 2003.01.05��.
<P><DL>
<DT><B>˵����</B><DD>
<pre>
�����ڶ������toString()������ʵ�֡�
</pre>
<DT><B>ʾ����</B><DD>
<pre>
��
</pre>
<DT><B>��־��</B><DD>
<pre>
������ 2003.01.05
</pre>
</DL>
* @param obj ����ʵ��.
*/
	public static void println(Object obj)
	{	if(IFDebug) System.out.println("<DebugObject>" + obj + "</DebugObject>");
	}
/**
* ��ӡ�б��еĶ�����Ϣ�������� 2003.01.05��.
<P><DL>
<DT><B>˵����</B><DD>
<pre>
�����ڶ������toString()������ʵ�֡�
</pre>
<DT><B>ʾ����</B><DD>
<pre>
��
</pre>
<DT><B>��־��</B><DD>
<pre>
������ 2003.01.05
</pre>
</DL>
* @param objs �б�ʵ��.
*/
	public static void println(List objs)
	{	if(IFDebug) System.out.println("<DebugList>Number of List or Array is " + objs.size());
	
		for(int i=0;i<objs.size();i++)
			println(objs.get(i));
		
		if(IFDebug) System.out.println("</DebugList>");
	}
	
/**
* ��ӡ�����еĶ�����Ϣ�������� 2003.01.05��.
<P><DL>
<DT><B>˵����</B><DD>
<pre>
�����ڶ������toString()������ʵ�֡�
</pre>
<DT><B>ʾ����</B><DD>
<pre>
��
</pre>
<DT><B>��־��</B><DD>
<pre>
������ 2003.01.05
</pre>
</DL>
* @param objs ��������.
*/
	public static void println(Object[] objs)
	{	println(Arrays.asList(objs));
	}


/**
* ��ӡ������������ 2004.06.11��.
<P><DL>
<DT><B>˵����</B><DD>
<pre>
��
</pre>
<DT><B>ʾ����</B><DD>
<pre>
��
</pre>
<DT><B>��־��</B><DD>
<pre>
������ 2004.06.11.
</pre>
</DL>
* @param i ����.
*/
	public static void println(long i)
	{	if(IFDebug) System.out.println("<Debuglong>" + i + "</Debuglong>");
	}



  
/**
* ��ӡRecordSet�������� 2005.04.17��.
<P><DL>
<DT><B>˵����</B><DD>
<pre>
��
</pre>
<DT><B>ʾ����</B><DD>
<pre>
��
</pre>
<DT><B>��־��</B><DD>
<pre>
������ 2005.04.17.
</pre>
</DL>
* @param rs ��¼������.
*/
	public static void println(RecordSet rs)
	{ 	if(IFDebug) 
		{ System.out.print("rowcount:" + rs.rowCount());
		  System.out.println("\n---------------------------------------------------");
		  for(int k=1;k<=rs.colCount();k++)
		    System.out.print(rs.getColName(k));
		  System.out.println("\n---------------------------------------------------");
		  for(int i=1;i<=rs.rowCount();i++)
		  { for(int j=1;j<=rs.colCount();j++)
		      System.out.print(rs.getItemValue(i,j));
		    System.out.println("\n--------------------------------------------------");
		  }
		}
	}
  

  
/**
* ��ӡ��¼�������� 2005.04.17��.
<P><DL>
<DT><B>˵����</B><DD>
<pre>
��
</pre>
<DT><B>ʾ����</B><DD>
<pre>
��
</pre>
<DT><B>��־��</B><DD>
<pre>
������ 2005.04.17.
</pre>
</DL>
* @param rc ��¼����.
*/
	public static void println(Record rc)
	{ 	if(IFDebug) 
		{ System.out.println("\n--------------------RECORD-----------------");
		  
		  for(int k=1;k<=rc.fieldCount();k++)
		    System.out.println(rc.getFieldName(k) + "=" + rc.getFieldValue(k));
		  
		  System.out.println("\n--------------------RECORD-----------------");
		
		}
	}


		
/**
* ����Ezx��ݼ����ı��루������ 2006.09.04��.
<P><DL>
<DT><B>˵����</B><DD>
<pre>
��
</pre>
<DT><B>ʾ����</B><DD>
<pre>
��
</pre>
<DT><B>��־��</B><DD>
<pre>
������ 2006.09.04.
</pre>
</DL>
*/
 	public static void buildEzxShortcut()
		throws FileNotFoundException,UnsupportedEncodingException,IOException
	{ 	String[] lComments = {"�ƶ�����","�ƶ��ִ�","˾������","���մ���","�Ѱ��"};

		FileOutputStream lFile = new FileOutputStream("e:\\EzxShortcut");

		for(int i=0;i<lComments.length;i++)
		{
			String lLine = lComments[i] + "=[";
			lFile.write(lLine.getBytes());
			lLine = toEzxShortcutLabel(lComments[i]);
			lFile.write(lLine.getBytes());
			lLine = "]\r\n";
			lFile.write(lLine.getBytes());			
		}
		
		lFile.close();		
	}	

	
/**
* EZX��ݼ�ת�루������ 2006.09.04��.
<P><DL>
<DT><B>˵����</B><DD>
<pre>
��
</pre>
<DT><B>ʾ����</B><DD>
<pre>
��
</pre>
<DT><B>��־��</B><DD>
<pre>
������ 2006.09.04.
</pre>
</DL>
* @param name ��ݼ���������.
* @return Ezx��ݼ���ʾ�ı�.
* @exception UnsupportedEncodingException ��֧�ֵı����ʽ.
*/
	static String toEzxShortcutLabel(String name) 
		throws UnsupportedEncodingException 
	{
		String lLabel = StringMethod.encode(name,"utf-8","iso-8859-1");
		return StringMethod.encode(lLabel,"utf-8","gbk");
	}
/**
* �����������򣨴����� 2004.06.24��.
<P><DL>
<DT><B>˵����</B><DD>
<pre>
��
</pre>
<DT><B>ʾ����</B><DD>
<pre>
��
</pre>
<DT><B>��־��</B><DD>
<pre>
������ 2004.06.24.
</pre>
</DL>
* @param args ��������.
*/
	public static void main(String[] args)
	{	try
		{	
			String a = "���ӻ�����";
			//TestAES.run("1234567890123456","������");
			//println(StringMethod.encode(a,"utf-8","gbk"));
			buildEzxShortcut();
		
		}
		catch(Exception e)
		{	//println(e);
      		MyException.printStackTrace(e);
		}
		
	}
}

