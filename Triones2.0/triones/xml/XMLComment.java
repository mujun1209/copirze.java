package triones.xml;

import triones.bas.Compare;
import triones.util.NodeListMethod;

import java.io.File;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import java.util.Date;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.DOMException;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import org.apache.xpath.XPathAPI;

/**<img src="modified.gif" width="32" height="32" border="0">XML语义操作对象.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
设计本对象的目的是简化对XML的操作方式,内部采用DOM来实现对XML的处理。
</pre>
<DT><B>使用说明：</B><DD>
<pre>

	有如下XML文档 － TAB_INFO.XML，要求将该文档中元素为COLUMNS元素下的，属性IS_NULLABLE为"YES"的COLUMN元素清除，并生成新的XML文档。

  〈?xml version="1.0" encoding="UTF-8" ?〉
- 〈TABLE〉
  〈COLUMN COLUMN_NAME="WYQ" COLUMN_SIZE="200" IS_NULLABLE="YES" ORDINAL_POSITION="5" TYPE_NAME="image" /〉
- 〈PRIMARYKEYS〉
  〈PRIMARYKEY COLUMN_NAME="ID" KEY_SEQ="1" /〉
  〈/PRIMARYKEYS〉
- 〈FOREIGNKEYS〉
  〈FOREIGNKEY COLUMN_NAME="ID" PKCOLUMN_NAME="ID" PKTABLE_NAME="TAB_LOGIN"〉WYQ〈/FOREIGNKEY〉
  〈/FOREIGNKEYS〉
- 〈COLUMNS〉
  〈COLUMN COLUMN_NAME="ID" COLUMN_SIZE="8" IS_NULLABLE="NO" ORDINAL_POSITION="1" TYPE_NAME="varchar" /〉
  〈COLUMN COLUMN_NAME="NAME" COLUMN_SIZE="50" IS_NULLABLE="NO" ORDINAL_POSITION="2" TYPE_NAME="varchar" /〉
  〈COLUMN COLUMN_NAME="SEX" COLUMN_SIZE="3" IS_NULLABLE="NO" ORDINAL_POSITION="3" TYPE_NAME="varchar" /〉
  〈COLUMN COLUMN_NAME="INTEREST" COLUMN_SIZE="200" IS_NULLABLE="YES" ORDINAL_POSITION="4" TYPE_NAME="varchar" /〉
  〈COLUMN COLUMN_NAME="PHOTO" COLUMN_SIZE="2147483647" IS_NULLABLE="YES" ORDINAL_POSITION="5" TYPE_NAME="image" /〉
  〈/COLUMNS〉
  〈/TABLE〉

	实现如下

public static void main(String args[])
{
	try
	{	XMLComment xml = new XMLComment(new File("e:\\TAB_INFO.XML"));

		Element columns = xml.getFirstElementByName("COLUMNS");
		Element[] is_nullable = xml.getElementsByAttribute(columns,"COLUMN","IS_NULLABLE","YES");

		xml.removeElements(is_nullable);

		xml.toXML(new File("e:\\TAB_INFO_FILTER.XML"));
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}

}
	执行结果如下：TAB_INFO_FILTER.XML

  〈?xml version="1.0" encoding="UTF-8" ?〉
- 〈TABLE〉
  〈COLUMN COLUMN_NAME="WYQ" COLUMN_SIZE="200" IS_NULLABLE="YES" ORDINAL_POSITION="5" TYPE_NAME="image" /〉
- 〈PRIMARYKEYS〉
  〈PRIMARYKEY COLUMN_NAME="ID" KEY_SEQ="1" /〉
  〈/PRIMARYKEYS〉
- 〈FOREIGNKEYS〉
  〈FOREIGNKEY COLUMN_NAME="ID" PKCOLUMN_NAME="ID" PKTABLE_NAME="TAB_LOGIN"〉WYQ〈/FOREIGNKEY〉
  〈/FOREIGNKEYS〉
- 〈COLUMNS〉
  〈COLUMN COLUMN_NAME="ID" COLUMN_SIZE="8" IS_NULLABLE="NO" ORDINAL_POSITION="1" TYPE_NAME="varchar" /〉
  〈COLUMN COLUMN_NAME="NAME" COLUMN_SIZE="50" IS_NULLABLE="NO" ORDINAL_POSITION="2" TYPE_NAME="varchar" /〉
  〈COLUMN COLUMN_NAME="SEX" COLUMN_SIZE="3" IS_NULLABLE="NO" ORDINAL_POSITION="3" TYPE_NAME="varchar" /〉
  〈/COLUMNS〉
  〈/TABLE〉

</pre>
<DT><B>名词解释：</B><DD>
<pre>
无
</pre>
<DT><B>注意事项：</B><DD>
<pre>
需JDK1.4（或更高版本）、org.apache.xpath包的支持
</pre>
<DT><B>展望未来：</B><DD>
<pre>
无
</pre>
<DT><B>联系方式：</B><DD>
<pre>
 email：wyq@wondersgroup.com
 电  话:021-64950118-2346
</pre>
</DL>
*@author	吴勇庆
*@version 2002.09.23
*/
public class XMLComment
{	
/**内置DOM对象（创建于 2002.06.19）.
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
创建于 2002.06.19.
</pre>
</DL>
*/
private Document _XMLDoc;
	
/**当前版本号（创建于 2002.09.23）.
<DL>
<DT><B>说明：</B><DD>
<pre>
VERSION = "2002.09.23"
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2002.09.23.
</pre>
</DL>
*/
public String VERSION = "2002.09.23";


	/**
	 * 构造方法（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	直接从XML文件生成XMLComment
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//导入一个XML文件
	XMLComment xml = new XMLComment(new File("e:\\TAB_INFO.XML"));
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2002.06.19.
	</pre>
	</DL>
* @param xmlFile XML文件句柄.
* @exception ParserConfigurationException 创建DOM实例失败.
* @exception SAXException 文件解析错误.
* @exception IOException 打开文件发生错误.
* @see #XMLComment()
* @see #XMLComment(Element)
* @see #XMLComment(InputStream)
*/
	public XMLComment(File xmlFile)
		throws ParserConfigurationException,SAXException,IOException
	{	_XMLDoc = initDocumentBuilder().parse(xmlFile);

		//InputSource lSource = new InputSource(new FileInputStream(xmlFile));
		//_XMLDoc = initDocumentBuilder().parse(lSource);
		//_Encoding = lSource.getEncoding();
	}


	/**
	 * 构造方法（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	通过输入流的方式构造XMLComment.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//e为另一个DOM的元素
	XMLComment xml = new XMLComment(new ByteArrayInputStream(e.toString().getBytes("GBK")));
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2002.06.19.
	</pre>
	</DL>
* @param xmlInputStream xml格式的输入流.
* @exception ParserConfigurationException XML解析器配置错误.
* @exception SAXException XML语义错误.
* @exception IOException 输入流错误.
* @see #XMLComment()
* @see #XMLComment(Element)
* @see #XMLComment(File)
*/
   public XMLComment(InputStream xmlInputStream)
		throws ParserConfigurationException,SAXException,IOException
	{	_XMLDoc = initDocumentBuilder().parse(xmlInputStream);
		//InputSource lSource = new InputSource(xmlInputStream);
		//_XMLDoc = initDocumentBuilder().parse(lSource);
		//_Encoding = lSource.getEncoding();
	}

	/**
	 * 构造方法（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	创建一个空的XMLComment.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2002.06.19.
	</pre>
	</DL>
* @exception ParserConfigurationException XML解析器配置错误.
* @see #XMLComment(File)
* @see #XMLComment(Element)
* @see #XMLComment(InputStream)
*/
	public XMLComment()
		throws ParserConfigurationException
	{	_XMLDoc = initDocumentBuilder().newDocument();
	}
  
/**
 * 构造方法（创建于 2002.06.19）.
<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	根据已有的DOM实例创建XMLComment
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2002.06.19.
	</pre>
	</DL>
* @param doc DOM实例.
* @see #XMLComment(File)
* @see #XMLComment()
* @see #XMLComment(InputStream)
*/
public XMLComment(Document doc) 
{	_XMLDoc = doc;
}

/**
 * 构造方法（创建于 2002.06.19）.
<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	根据DOM的某个节点元素创建XML.
	采用当前系统默认的编码格式。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2002.06.19.
	修改于 2002.09.23 - 不再依赖于Element.toString()的不同实现模型.
	</pre>
	</DL>
* @param e 元素实例.
* @exception SAXException XML语义错误.
* @exception ParserConfigurationException XML解析器配置错误.
* @see #XMLComment(File)
* @see #XMLComment()
* @see #XMLComment(InputStream)
*/
public XMLComment(Element e)
	throws SAXException,ParserConfigurationException
{	//_XMLDoc = initDocumentBuilder().parse(new ByteArrayInputStream(e.toString().getBytes()));
	_XMLDoc = _XMLDoc = initDocumentBuilder().newDocument();
	Element le_root = importElement(e,true);
	_XMLDoc.appendChild(le_root);	
}


/**
 * 初始化DocumentBuilder（创建于 2002.06.19）.
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
	创建于 2002.06.19.
	</pre>
	</DL>
* @return 已配置好参数的DOM生成器.
* @exception ParserConfigurationException XML解析器配置错误.
*/
private DocumentBuilder initDocumentBuilder()
	throws ParserConfigurationException
{	DocumentBuilderFactory lDBFactory = DocumentBuilderFactory.newInstance();
	//lDBFactory.setValidating(true);
	//lDBFactory.setIgnoringComments(true);
	//lDBFactory.setIgnoringElementContentWhitespace(true);
	//lDBFactory.setNamespaceAware(boolean awareness);
	//lDBFactory.setCoalescing(boolean coalescing) 
	//lDBFactory.setExpandEntityReferences(false);
	DocumentBuilder lDBuilder = lDBFactory.newDocumentBuilder();

	return lDBuilder;
}


	/**
	 * 创建一个XML节点元素（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//创建一个XML实例
	XMLComment xml = new XMLComment();

	//创建一个标记为COLUMN的元素
	Element e = xml.createElement("COLUMN");
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2002.06.19.
	</pre>
	</DL>
* @param tagName 元素名称.
* @return 元素实例.
* @see #createElementNS(String,String)
*/
	public Element createElement(String tagName)
	{ return _XMLDoc.createElement(tagName);
	}


	/**
	 * 创建一个带命名空间的XML节点元素（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2002.06.19.
	</pre>
	</DL>
* @param namespaceURI 命名空间.
* @param tagName 元素名称.
* @return 元素实例.
* @exception DOMException 命名空间出现错误.
* @see #createElement(String)
*/
	public Element createElementNS(String namespaceURI,String tagName)
  	throws DOMException
	{	return _XMLDoc.createElementNS(namespaceURI,tagName);
	}

	/**
	 * 创建根节点元素（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	对于一个空的XMLComment实例，都需要创建根元素。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//创建一个XML实例
	XMLComment xml = new XMLComment();

	//创建一个标记为COLUMN的元素
	Element root = xml.createRootElement("TABLE");
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2002.06.19.
	</pre>
	</DL>
* @param tagName 根元素名.
* @return 根节点元素.
* @see #getRootElement()
*/
	public Element createRootElement(String tagName)
	{	Element le_root = createElement(tagName);
		_XMLDoc.appendChild(le_root);

		return le_root;
	}


	/**
	 * 获取根节点元素（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2002.06.19.
	</pre>
	</DL>
* @return 根节点元素.
* @see #createRootElement(String)
*/
	public Element getRootElement()
	{ return _XMLDoc.getDocumentElement();
	}


	/**
	 * 获取所有指定名称的元素（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
		//构造XMLComment实例
		XMLComment xml = new XMLComment(new File("e:\\TAB_INFO.XML"));

		//获取所有COLUMN元素
		Element[] e = xml.getElementsByName("COLUMN");

		//在屏幕上显示COLUMN_NAME
		for(int i=0;i<e.length;i++)
			System.out.println(XMLComment.getElementAttribute(e[i],"COLUMN_NAME"));
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param name 元素名称.
* @return 元素集合.
* @see #getElementsByName(Element,String)
*/
	public Element[] getElementsByName(String name)
	{	NodeList lNL_childs = _XMLDoc.getElementsByTagName(name);
		return NodeListMethod.toElements(lNL_childs);
	}


	/**
	 * 获取所有指定名称的元素（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param namespaceURI 命名空间.
* @param name 元素名称.
* @return 元素实例.
* @see #getElementsByName(String)
*/
	public Element[] getElementsByNameNS(String namespaceURI,String name)
	{	NodeList lNL_childs = _XMLDoc.getElementsByTagNameNS(namespaceURI,name);
		return NodeListMethod.toElements(lNL_childs);

	}

/**
	 * 获取符合该XPath表达式要求的元素（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	（吴俊飞友情提供）
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param strXpathExpression XPath表达式.
  @throws TransformerException XPath表达式错误.
* @return 符合表达式要求的元素.
*/

  public Element[] getElementsByXpath(String strXpathExpression)
      throws TransformerException 
	{ NodeList lNList = XPathAPI.selectNodeList(_XMLDoc,strXpathExpression);
		
    return NodeListMethod.toElements(lNList);
  }

	/**
	 * 获取元素中所有指定名称的子元素（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
		//构造XMLComment实例
		XMLComment xml = new XMLComment(new File("e:\\TAB_INFO.XML"));

		//获取COLUMNS元素
		Element[] cols = xml.getElementsByName("COLUMNS");

		//获取第一个COLUMNS元素下所有COLUMN子元素
		Element[] e = xml.getElementsByName(cols[0],"COLUMN");

		//在屏幕上显示COLUMN_NAME
		for(int i=0;i<e.length;i++)
			System.out.println(XMLComment.getElementAttribute(e[i],"COLUMN_NAME"));
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
	@param e	父元素实例.
* @param name 子元素名称.
* @return 子元素集合.
* @see #getElementsByName(String)
*/
	public static Element[] getElementsByName(Element e,String name)
	{	NodeList lNL_childs = e.getElementsByTagName(name);
		return NodeListMethod.toElements(lNL_childs);
	}


	/**
	 * 在指定元素中获取第一个符合名称要求的子元素（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	 如果没有则返回null.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param e 父元素.
* @param name 元素名称.
* @return 子元素实例.
* @see #getFirstElementByName(String)
*/
	public static Element getFirstElementByName(Element e,String name)
	{	return (Element)(e.getElementsByTagName(name).item(0));
	}

	/**
	 *  获取第一个符合名称要求的元素（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
		如果没有则返回null.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param name 元素名称.
* @return 元素实例.
* @see #getFirstElementByName(Element,String)
*/
	public Element getFirstElementByName(String name)
	{	return (Element)(_XMLDoc.getElementsByTagName(name).item(0));
	}
	/**
	 * 获取元素中所有指定名称的子元素（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
		无
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
	@param e	父元素实例.
	@param namespaceURI 子元素命名空间.
* @param name 子元素名称.
* @return 子元素集合.
* @see #getElementsByName(String)
	@see #getElementsByName(Element,String)
*/
	public static Element[] getElementsByNameNS(Element e,String namespaceURI,String name)
	{	NodeList lNL_childs = e.getElementsByTagNameNS(namespaceURI,name);
		return NodeListMethod.toElements(lNL_childs);
	}

		/**
	 * 获取指定元素名中符合属性定义的元素集（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param name 元素名称.
* @param attrName 属性名.
* @param attrValue 属性值.
* @return 元素集.
* @see #getElementsByAttribute(Element,String,String,String)
	@see #getElementsByAttributes(String,String[],String[])
	@see #getElementsByAttributes(Element,String,String[],String[])
*/
	public Element[] getElementsByAttribute(String name,String attrName,String attrValue)
	{	return getElementsByAttribute(name,attrName,attrValue,Compare.EQUAL);
	}


	/**
	 * 获取指定元素下指定元素名称中符合属性定义的子元素集（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param e 父元素.
* @param name 元素名称.
* @param attrName 属性名.
* @param attrValue 属性值.
* @return 子元素集.
* @see #getElementsByAttribute(String,String,String)
	@see #getElementsByAttributes(String,String[],String[])
	@see #getElementsByAttributes(Element,String,String[],String[])
*/
	public static Element[] getElementsByAttribute(Element e,String name,String attrName,String attrValue)
	{	return getElementsByAttribute(e,name,attrName,attrValue,Compare.EQUAL);
	}


	/**
	 * 获取指定元素下指定元素名称中符合属性定义的子元素集（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param e 父元素.
* @param name 元素名称.
* @param attrName 属性名.
* @param attrValue 属性值.
* @param op 条件匹配符.
* @return 子元素集.
* @see #getElementsByAttribute(String,String,String)
	@see #getElementsByAttribute(String,String,String,int)
	@see #getElementsByAttribute(Element,String,String,String)
	@see #getElementsByAttributes(String,String[],String[])
	@see #getElementsByAttributes(Element,String,String[],String[])
*/
	public static Element[] getElementsByAttribute(Element e,String name,String attrName,String attrValue,int op)
	{	NodeList lNL_childs = e.getElementsByTagName(name);
		int i = lNL_childs.getLength();
		ArrayList a = new ArrayList();
		Element m;

		for(int k=0;k<i;k++)
		{	m = (Element)lNL_childs.item(k);
			if(Compare.isMatch(m.getAttribute(attrName),attrValue,op))
				a.add(m);
		}

		Element[] f = new Element[a.size()];
		a.toArray(f);

		return f;
	}

	/**
	 * 获取指定元素名称中符合属性定义的元素集（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param name 元素名称.
* @param attrName 属性名.
* @param attrValue 属性值.
* @param op 条件匹配符.
* @return 元素集.
* @see #getElementsByAttribute(String,String,String)
	@see #getElementsByAttribute(Element,String,String,String)
	@see #getElementsByAttributes(String,String[],String[])
	@see #getElementsByAttributes(Element,String,String[],String[])
	@see #getElementsByAttribute(Element,String,String,String,int)
*/
	public Element[] getElementsByAttribute(String name,String attrName,String attrValue,int op)
	{	NodeList lNL_childs = _XMLDoc.getElementsByTagName(name);
		int i = lNL_childs.getLength();
		ArrayList a = new ArrayList();
		Element m;

		for(int k=0;k<i;k++)
		{	m = (Element)lNL_childs.item(k);
			if(Compare.isMatch(m.getAttribute(attrName),attrValue,op))
				a.add(m);
		}

		Element[] f = new Element[a.size()];
		a.toArray(f);

		return f;
	}

	/**
	 * 获取指定元素名中符合属性定义的元素集（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
		属性名与属性值一一对应.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param name 元素名称.
* @param attrName 属性名列表.
* @param attrValue 属性值列表.
* @return 元素集.
* @see #getElementsByAttributes(String,String[],String[],int[])
	@see #getElementsByAttributes(Element,String,String[],String[])
	@see #getElementsByAttributes(Element,String,String[],String[],int[])
*/
	public Element[] getElementsByAttributes(String name,String[] attrName,String[] attrValue)
	{	int[] li_op = new int[attrName.length];

		for(int i=0;i<attrName.length;i++)
			li_op[i] = Compare.EQUAL;

		return getElementsByAttributes(name,attrName,attrValue,li_op);
	}

		/**
	 * 获取指定元素名中符合属性定义的元素集（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
		属性名与属性值及条件匹配符一一对应.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param name 元素名称.
* @param attrName 属性名列表.
* @param attrValue 属性值列表.
	@param op	条件匹配符列表.
* @return 元素集.
* @see #getElementsByAttributes(String,String[],String[])
	@see #getElementsByAttributes(Element,String,String[],String[])
	@see #getElementsByAttributes(Element,String,String[],String[],int[])
*/
	public Element[] getElementsByAttributes(String name,String[] attrName,String[] attrValue,int[] op)
	{	NodeList lNL_childs = _XMLDoc.getElementsByTagName(name);
		int i = lNL_childs.getLength();
		ArrayList a = new ArrayList();
		Element m;

		for(int k=0;k<i;k++)
		{	m = (Element)lNL_childs.item(k);
			boolean lb_match = true;

			for(int j=0;j<attrName.length;j++)
				lb_match = (Compare.isMatch(m.getAttribute(attrName[j]),attrValue[j],op[j])&&lb_match);

			if(lb_match) a.add(m);
		}

		Element[] f = new Element[a.size()];
		a.toArray(f);

		return f;
	}
	/**
	 * 获取指定元素下指定元素名称中符合属性定义的子元素集（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	属性名与属性值一一对应.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
		//属性名称列表
		String[] p = {"IS_NULLABLE","TYPE_NAME"};
		//属性值列表
		String[] v = {"YES","image"};

		//初始化XML
		XMLComment xml = new XMLComment(new File("e:\\TAB_INFO.XML"));
		//获取所有的COLUMNS元素集
		Element[] cols = xml.getElementsByName("COLUMNS");

		//在第一个COLUMNS元素中提取符合属性定义要求的子元素集
		Element[] e = xml.getElementsByAttributes(cols[0],"COLUMN",p,v);
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param e 父元素.
* @param name 元素名称.
* @param attrName 属性名列表.
* @param attrValue 属性值列表.
* @return 子元素集.
* @see #getElementsByAttributes(String,String[],String[])
	@see #getElementsByAttributes(String,String[],String[],int[])
	@see #getElementsByAttributes(Element,String,String[],String[],int[])
*/
	public static Element[] getElementsByAttributes(Element e,String name,String[] attrName,String[] attrValue)
	{	int[] li_op = new int[attrName.length];

		for(int i=0;i<attrName.length;i++)
			li_op[i] = Compare.EQUAL;

		return getElementsByAttributes(e,name,attrName,attrValue,li_op);
	}


	/**
	 * 获取指定元素下指定元素名称中符合属性定义的子元素集（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	属性名与属性值及条件匹配符一一对应.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param e 父元素.
* @param name 元素名称.
* @param attrName 属性名列表.
* @param attrValue 属性值列表.
* @param op 条件匹配符列表.
* @return 子元素集.
* @see #getElementsByAttributes(String,String[],String[])
	@see #getElementsByAttributes(String,String[],String[],int[])
	@see #getElementsByAttributes(Element,String,String[],String[])

*/
	public static Element[] getElementsByAttributes(Element e,String name,String[] attrName,String[] attrValue,int[] op)
	{	NodeList lNL_childs = e.getElementsByTagName(name);
		int i = lNL_childs.getLength();
		ArrayList a = new ArrayList();
		Element m;

		for(int k=0;k<i;k++)
		{	m = (Element)lNL_childs.item(k);
			boolean lb_match = true;

			for(int j=0;j<attrName.length;j++)
				lb_match = (Compare.isMatch(m.getAttribute(attrName[j]),attrValue[j],op[j])&&lb_match);

			if(lb_match) a.add(m);
		}

		Element[] f = new Element[a.size()];
		a.toArray(f);

		return f;
	}
	/**
	 * 获取元素文本内容（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param e 元素实例.
* @return 元素文本内容.
* @see #setElementText(Element,String)
	@see #getElementName(Element)
*/
	public static String getElementText(Element e)
	{	NodeList lNL_childs = e.getChildNodes();
		int li_length = lNL_childs.getLength();
		String ls_text = "";

		for(int i=0;i<li_length;i++)
		 ls_text += lNL_childs.item(i).getNodeValue();

		return ls_text;
	}

	/**
	 * 设置元素文本内容（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param e 元素实例.
* @param text 文本内容.
* @see #getElementText(Element)
*/
	public void setElementText(Element e,String text)
	{	Text lText = _XMLDoc.createTextNode(text);
		e.appendChild(lText);
	}


	/**
	 * 以CDATA模式获取元素的内容（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param e 元素实例.
* @return CDATA内容.
* @see #setElementCDATA(Element,String)
*/
	public String getElementCDATA(Element e)
	{	NodeList lNL_childs = e.getChildNodes();
		int li_length = lNL_childs.getLength();
		String ls_cdata = "";

		for(int i=0;i<li_length;i++)
			if(lNL_childs.item(i).getNodeType()==Node.CDATA_SECTION_NODE)
			{	ls_cdata = ((CDATASection)lNL_childs.item(i)).getData();
				break;
			}

		return ls_cdata;
	}

	/**
	 * 以CDATA模式设置元素的内容（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param e 元素实例.
* @param cdata CDATA内容.
* @see #getElementCDATA(Element)
	@see #setElementCDATA(Element,byte[])
*/
	public void setElementCDATA(Element e,String cdata)
	{	CDATASection lCDSection = _XMLDoc.createCDATASection(cdata);
		e.appendChild(lCDSection);
	}
	
/**
	 * <img src="new.gif" width="28" height="11" border="0">以CDATA模式设置元素的内容（创建于 2002.09.18）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2002.09.18.
	</pre>	
	</DL>
* @param e 元素实例.
* @param cdata CDATA内容.
* @see #getElementCDATA(Element)
	@see #setElementCDATA(Element,String)
*/
	public void setElementCDATA(Element e,byte[] cdata)
	{	setElementCDATA(e,new String(cdata));
	}
	/**
	 * 设置指定元素的子元素（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param e 元素实例.
* @param child 子元素实例.
	@deprecated 命名不科学，由setElementChild(Element,Element)代替.
* @return 子元素实例.
*/
	public static Element setElement(Element e,Element child)
	{ return (Element)e.appendChild(child);
	}
	
	/**
	 * 设置指定元素的子元素（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param e 元素实例.
* @param child 子元素实例.
* @return 子元素实例.
*/
	public static Element setElementChild(Element e,Element child)
	{ return (Element)e.appendChild(child);
	}


	/**
	 * 将另一个XMLComment设为当前实例指定元素的子元素（创建于 2002.09.20）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	应保证父元素为当前XMLComment实例中的某个节点。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2002.09.20.
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param e 父元素.
* @param xml 作为子元素的XMLComment实例.
* @return 父元素.
*/
	public Element setElementChild(Element e,XMLComment xml)
	{	Element le_root = importXMLComment(xml);
		return setElementChild(e,le_root);
	}
	/**
	 * 设置指定元素的多个子元素（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	子元素必须是由当前XMLComment生成的。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
		XMLComment xml = new XMLComment(new File("e:\\TAB_INFO.XML"));
		XMLComment xml2 = new XMLComment();

		//取xml中"COLUMN_NAME">="NAME"的"COLUMN"元素集
		Element[] f = xml.getElementsByAttribute("COLUMN","COLUMN_NAME","NAME",Compare.EQUAL|Compare.LARGE);

		//创建根元素
		Element root = xml2.createRootElement("COLUMNS");

		//转换成xml2的元素类型
		f = xml2.importElements(f,true);

		//设置子元素列表
		xml2.setElements(root,f);
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param e 父元素.
* @param child 子元素列表.
	@deprecated 命名不合理，由setElementChilds(Element,Element[])代替.
* @return 父元素.
*/
	public static Element setElements(Element e,Element[] child)
	{	for(int i=0;i<child.length;i++)
			setElement(e,child[i]);

		return e;
	}

/**
	 * 设置指定元素的多个子元素（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	子元素必须是由当前XMLComment生成的。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
		XMLComment xml = new XMLComment(new File("e:\\TAB_INFO.XML"));
		XMLComment xml2 = new XMLComment();

		//取xml中"COLUMN_NAME">="NAME"的"COLUMN"元素集
		Element[] f = xml.getElementsByAttribute("COLUMN","COLUMN_NAME","NAME",Compare.EQUAL|Compare.LARGE);

		//创建根元素
		Element root = xml2.createRootElement("COLUMNS");

		//转换成xml2的元素类型
		f = xml2.importElements(f,true);

		//设置子元素列表
		xml2.setElementChilds(root,f);
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param e 父元素.
* @param child 子元素列表.
* @return 父元素.
*/
	public static Element setElementChilds(Element e,Element[] child)
	{	for(int i=0;i<child.length;i++)
			setElementChild(e,child[i]);

		return e;
	}

	/**
	 * 将其他实例中产生的元素转换成本实例的元素（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	subtree为true代表将元素中的子节点树也转换;false代表只转换元素本身,不包含子节点树.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
		XMLComment xml = new XMLComment(new File("e:\\TAB_INFO.XML"));
		XMLComment xml2 = new XMLComment();

		//取xml中"COLUMN_NAME"等于"NAME"的"COLUMN"元素集
		Element[] f = xml.getElementsByAttribute("COLUMN","COLUMN_NAME","NAME");

		//转换成xml2的元素类型
		Element e = xml2.importElement(f[0],true);
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param e 待转换的元素实例.
* @param subtree 是否转换子节点.
* @return 经转换后的元素实例.
* @see #importElements(Element[],boolean)
*/
	public Element importElement(Element e,boolean subtree)
	{	return (Element)_XMLDoc.importNode(e,subtree);
	}

	/**
	 * 将其他实例中产生的元素转换成本实例的元素（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	subtree为true代表将元素中的子节点树也转换;false代表只转换元素本身,不包含子节点树.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
		XMLComment xml = new XMLComment(new File("e:\\TAB_INFO.XML"));
		XMLComment xml2 = new XMLComment();

		//取xml中"COLUMN_NAME">="NAME"的"COLUMN"元素集
		Element[] f = xml.getElementsByAttribute("COLUMN","COLUMN_NAME","NAME",Compare.EQUAL|Compare.LARGE);

		//转换成xml2的元素类型
		f = xml2.importElements(f,true);
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param e 待转换的元素列表.
* @param subtree 是否转换子节点.
* @return 经转换后的元素列表.
* @see #importElement(Element,boolean)
*/
	public Element[] importElements(Element[] e,boolean subtree)
	{	Element[] f = new Element[e.length];

		for(int i=0;i<e.length;i++)
			f[i] = (Element)_XMLDoc.importNode(e[i],subtree);

		return f;
	}
	

	/**
	 * 导入XMLComment，使之转换成本实例的元素（创建于 2002.09.20）.
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
	创建于 2002.09.20.
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param xml XMLComment实例.
* @return 以xml的根节点作为当前实例的元素.
*/
	public Element importXMLComment(XMLComment xml)
	{	Element le_root = xml.getRootElement();
		return importElement(le_root,true);
	}
	/**
	 * 获取元素名称（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param e 元素实例.
* @return 元素名称.
* @see #getElementText(Element)
*/
	public static String getElementName(Element e)
	{	return e.getNodeName();
	}
	

	/**
	 * <img src="new.gif" width="28" height="11" border="0">根据当前XMLComment生成Document实例（创建于 2002.09.23）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	主要用于和Document互相转换.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2002.09.23.
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @return 与当前XMLComment内容一致的Document实例.
	@see #XMLComment(Document)
*/
	public Document toDocument()
	{	return _XMLDoc;
	}
	/**
	 * 获取元素属性值（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	相当于Element.getAttribute(String).
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//获取elt的属性COLUMN_NAME的值.
	String str = XMLComment.getElementAttribute(elt,"COLUMN_NAME");
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param e 元素实例.
* @param attrName 属性名.
* @return 属性值.
	@see #setElementAttribute(Element,String,String)
*/
	public static String getElementAttribute(Element e,String attrName)
	{	return e.getAttribute(attrName);
	}


	/**
	 * 设置元素属性值（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	如果该属性不存在,则增加该属性;如果存在,则替换成新值.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param e 元素实例.
* @param attrName 属性名.
* @param attrValue 属性值.
* @see #getElementAttribute(Element,String)
*/
	public static void setElementAttribute(Element e,String attrName,String attrValue)
	{ e.setAttribute(attrName,attrValue);
	}


	/**
	 * 删除指定元素的属性（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	参见 Element.removeAttribute(String name)
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param e 元素实例.
* @param attrName 属性名.
* @see #removeElementsAttribute(Element[],String)
	@see #removeElementAttributes(Element,String[])
	@see #removeElementsAttributes(Element[],String[])
*/
	public static void removeElementAttribute(Element e,String attrName)
	{	e.removeAttribute(attrName);
	}
	/**
	 * 删除指定元素的多个属性（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param e 元素实例.
* @param attrName 属性名列表.
* @see #removeElementAttribute(Element,String)
	@see #removeElementAttributes(Element,String[])
	@see #removeElementsAttributes(Element[],String[])
*/
	public static void removeElementAttributes(Element e,String[] attrName)
	{	for(int i=0;i<attrName.length;i++)
			e.removeAttribute(attrName[i]);
	}
	/**
	 * 删除多个元素的属性（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param e 元素实例列表.
* @param attrName 属性名.
* @see #removeElementAttribute(Element,String)
	@see #removeElementAttributes(Element,String[])
	@see #removeElementsAttributes(Element[],String[])
*/
	public static void removeElementsAttribute(Element[] e,String attrName)
	{	for(int i=0;i<e.length;i++)
			e[i].removeAttribute(attrName);
	}
	/**
	 * 删除多个元素的多个属性（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param e 元素实例列表.
* @param attrName 属性名列表.
* @see #removeElementsAttribute(Element[],String)
	@see #removeElementAttributes(Element,String[])
	@see #removeElementAttribute(Element,String)
*/
	public static void removeElementsAttributes(Element[] e,String[] attrName)
	{	for(int i=0;i<e.length;i++)
			removeElementAttributes(e[i],attrName);
	}
	/**
	 * 清除指定列表的元素（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param e 元素列表.
*/
	public static void removeElements(Element[] e)
	{	for(int i=0;i<e.length;i++)
		{	Node n = e[i].getParentNode();
			n.removeChild(e[i]);
		}
	}

	/**
	 * 生成XML文件（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param xmlFile XML文件句柄.
* @exception TransformerConfigurationException 创建文件格式生成器失败.
* @exception TransformerException 文件格式转换失败.
	@see #toXML(File,String)
*/
	public void toXML(File xmlFile)
		throws TransformerConfigurationException,TransformerException
	{	toXML(xmlFile,_Encoding);
	}
	/**
	 * 生成XML文件（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param xmlFile XML文件句柄.
	@param encoding 编码格式.
* @exception TransformerConfigurationException 创建文件格式生成器失败.
* @exception TransformerException 文件格式转换失败.
	@see #toXML(File)
*/
	public void toXML(File xmlFile,String encoding)
		throws TransformerConfigurationException,TransformerException
	{	TransformerFactory lTFfactory = TransformerFactory.newInstance();
		Transformer lTFormer = lTFfactory.newTransformer();
		//设置编码格式
		lTFormer.setOutputProperty("encoding",encoding);
		// 将DOM对象转化为DOMSource类对象，该对象表现为转化成别的表达形式的信息容器。
		DOMSource lDSource = new DOMSource(_XMLDoc);
		// 获得一个StreamResult类对象，该对象是DOM文档转化成的其他形式的文档的容器，可以是XML文件，文本文件，HTML文件。这里为一个XML文件。

		StreamResult lSResult = new StreamResult(xmlFile);
		lTFormer.transform(lDSource,lSResult);

		/*String ls_prefix = "<?xml version=\"1.0\" encoding=\"";
		String ls_suffix = "\"?>\n\r";

		try
		{	FileOutputStream lFOStream = new FileOutputStream(xmlFile);

			lFOStream.write((ls_prefix + _Encoding + ls_suffix).getBytes());
			lFOStream.write(toString().getBytes());
			lFOStream.close();
		}
		catch(Exception e)
		{
		}*/
	}

	/**
	 * 转换为字符串（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @return 字符串.
*/
	public String toString()		
	{	//return (_XMLDoc.getDocumentElement()).toString();
		String ls_xml = "";
		try
		{	TransformerFactory lTFfactory = TransformerFactory.newInstance();
			Transformer lTFormer = lTFfactory.newTransformer();
			//设置编码格式
			lTFormer.setOutputProperty("encoding",_Encoding);
			// 将DOM对象转化为DOMSource类对象，该对象表现为转化成别的表达形式的信息容器。
			DOMSource lDSource = new DOMSource(_XMLDoc);
			// 获得一个StreamResult类对象，该对象是DOM文档转化成的其他形式的文档的容器，可以是XML文件，文本文件，HTML文件。这里为一个XML文件。
			ByteArrayOutputStream lStream = new ByteArrayOutputStream();
			StreamResult lSResult = new StreamResult(lStream);
			lTFormer.transform(lDSource,lSResult);
			ls_xml = lStream.toString();
		}
		catch(Exception e)
		{	String ls_title = "<?xml version=\"1.0\" encoding=\"" + getEncoding() + "\"?>";
			ls_xml = ls_title + (_XMLDoc.getDocumentElement());
		}
		/*
		catch(TransformerConfigurationException e)
		{	throw new RuntimeException("TransformerConfigurationException:" + e.toString());
		}
		catch(TransformerException e)
		{	throw new RuntimeException("TransformerException:" + e.toString());
		}*/
		
		return ls_xml;
	}

/**编码格式Encoding（创建于 2002.06.19）.
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
创建于 2002.06.19.
</pre>
</DL>
*/
static String _Encoding = "GBK";


	/**
	 * 设置生成XML时的默认编码格式（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	即 〈?xml version="1.0" encoding="UTF-8" ?〉中的 encoding的值。
	默认为GBK.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @param encoding 编码格式.
* @see #getEncoding()
*/
	public static void setEncoding(String encoding)
	{	_Encoding = encoding;
	}

	/**
	 * 取当前默认的XML编码格式（创建于 2002.06.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	即 〈?xml version="1.0" encoding="UTF-8" ?〉中的 encoding的值。
	目前支持UTF-8和GBK.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
<pre>
创建于 2002.06.19.
</pre>
	</DL>
* @return 编码格式.
* @see #setEncoding(String)
*/
	public static String getEncoding()
	{	return _Encoding;
	}

}

