package triones.xml;

import triones.util.StringMethod;


import java.util.ArrayList;
import java.util.List;
/**
* XML文本静态方法库（创建于 2005.04.09）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
略
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
* @version	2005.04.09
*/
public class XMLMethod
{	 
/**
* 下一个节点的起始位置（创建于 2005.04.09）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
如果beginPos >= xml.length()则返回beginPos
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2005.04.09.
</pre>
</DL>
* @param xml XML文本.
* @param beginPos 当前节点起始位置.
* @return 下一节点的起始位置.
*/
  public static int nextNodeBeginAt(String xml,int beginPos)
  { String ls_node;
    char lc_char;
    int li_end=1,li_len = xml.length();
    boolean lb_hasmark;
    
    if(beginPos >= li_len) return beginPos;
    
    lc_char = xml.charAt(beginPos);
    
    switch(lc_char)    
    { case '<':		//tag
            		lb_hasmark = false;
            		while(beginPos+li_end < li_len)
                {	lc_char =  xml.charAt(beginPos+li_end);
                  if(lc_char=='"')lb_hasmark = !lb_hasmark;
            			else if(lc_char == '>'&&!lb_hasmark)	
                  { li_end ++;//下一个节点的位置
                    break;	                  
                  }
                  li_end ++;
                }break;
        
    	default:	//text		
            		while(beginPos+li_end < li_len)
                {	lc_char = xml.charAt(beginPos+li_end);
                  if(lc_char=='<')break;
                  li_end ++;
            		}break;
    }
    
    return beginPos + li_end;
  }
  
  
  public static final int NODE_CDATA = 1;
  public static final int NODE_REMARK = 2;
  public static final int NODE_TITLE = 3;
  public static final int NODE_END = 4;
  public static final int NODE_BEGIN_END = 5;
  public static final int NODE_BEGIN = 6;
  public static final int NODE_TEXT = 7;
  
/**
* 判断节点类型（创建于 2005.04.09）.
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
创建于 2005.04.09.
</pre>
</DL>
* @param node 节点XML文本.
* @return 节点类型.
*/
  public static int nodeType(String node)
  { if(node.length()>=9)
    { if(node.substring(0,9).toUpperCase().equals("<![CDATA[")) return NODE_CDATA;
    }
    if(node.startsWith("<!--")) return NODE_REMARK;
    if(node.startsWith("<?")) return NODE_TITLE;
    if(node.startsWith("</")) return NODE_END;
    if(node.startsWith("<") && node.endsWith("/>")) return NODE_BEGIN_END;
    if(node.startsWith("<") && node.endsWith(">")) return NODE_BEGIN;
    return NODE_TEXT;
  }
  

    

  
/**
* 取节点标签名称（创建于 2005.04.09）.
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
创建于 2005.04.09.
</pre>
</DL>
* @param node 节点XML文本.
* @return 节点标签名.
*/
  public static String nodeGetTag(String node)
  { int li_begin,li_end;
    String ls_name = "";
    
    switch(nodeType(node))
    {	case NODE_BEGIN:
    		li_begin = 1;
    		li_end = StringMethod.indexOfKey(node," >",li_begin);break;
    	case NODE_BEGIN_END:
    		li_begin = 1;
    		li_end = StringMethod.indexOfKey(node," /",li_begin);break;
    	case NODE_END:
    		li_begin = 2;
    		li_end = StringMethod.indexOfKey(node,">",li_begin);break;
    	case NODE_TITLE:
    		li_begin = 2;
    		li_end = StringMethod.indexOfKey(node," ?",li_begin);break;
       default: 
        li_begin = -1;li_end = -1;break;
    }
    
    if(li_end >=0 )	ls_name = node.substring(li_begin,li_end);
    
    return ls_name.trim();
  }
  

  
/**
* 取节点属性文本（创建于 2005.04.09）.
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
创建于 2005.04.09.
</pre>
</DL>
* @param node 节点XML文本.
* @return 属性文本.
*/
  static String nodeGetAttributes(String node)
  { int li_begin,li_end;
    String ls_attributes = "";

    switch(nodeType(node))
    {	case NODE_BEGIN:
    		li_begin = node.indexOf(" ");
    		li_end = node.length() - 1;break;
    	case NODE_BEGIN_END:
    		li_begin = node.indexOf(" ");
    		li_end = node.length() - 2;break;
    	case NODE_TITLE:
    		li_begin = node.indexOf(" ");
    		li_end = node.length() - 2;break;
      default:
        li_begin = -1;li_end = -1;break;
    }

    if(li_begin >= 0 && li_end > li_begin) 
      ls_attributes = node.substring(li_begin + 1,li_end);
    
    return ls_attributes;
  }

  

  
/**
* 取单一属性文本（创建于 2005.04.09）.
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
创建于 2005.04.09.
</pre>
</DL>
* @param attributes 属性文本.
* @param property 属性名.
* @return 属性文本.
*/
  static String attributesGetProperty(String attributes,String property)
  { int li_begin = -1,li_end = -1;
    String ls_propertyBegin,ls_propertyEnd,ls_value = "";
    
    if(!StringMethod.isValid(attributes)) return "";
    
    //补空格用于保证属性之间不存在右匹配
    attributes = " " + attributes;
    ls_propertyBegin = " " + property + "=\"";
    ls_propertyEnd = "\"";
    
    li_begin = attributes.indexOf(ls_propertyBegin);
    if(li_begin >= 0)
    {	li_end = attributes.indexOf(ls_propertyEnd,li_begin + ls_propertyBegin.length());
    	if(li_end >= 0)//需减去因增加空格所占的位置
    		ls_value = attributes.substring(li_begin + 1,li_end + 1);
    }
    return ls_value;
  }
  

  
/**
* 取属性值（创建于 2005.04.09）.
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
创建于 2005.04.09.
</pre>
</DL>
* @param attribute 属性文本.
* @return 属性值.
*/
  static String propertyGetValue(String attribute)
  { int li_begin = -1,li_end = -1;
    String ls_propertyBegin,ls_propertyEnd,ls_value="";
    
    if(!StringMethod.isValid(attribute)) return "";
    
    ls_propertyBegin = "=\"";    
    ls_propertyEnd = "\"";

    li_begin = attribute.indexOf(ls_propertyBegin);
    
    if(li_begin >= 0)
    {	li_begin += ls_propertyBegin.length();
    	li_end = attribute.indexOf(ls_propertyEnd,li_begin);
    	if(li_end >= 0)//需减去因增加空格所占的位置
    		ls_value = attribute.substring(li_begin,li_end);
    }
    
    return ls_value;
  }
  

  
/**
* 添加指定标签元素到文本数组（创建于 2005.04.10）.
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
创建于 2005.04.10.
</pre>
</DL>
* @param xml XML文本.
* @param int 开始位置.
* @param tagName 标签名称.
* @param elements 标签元素文本数组.
* @return >0代表标签元素之后的节点起始位置, -1代表找不到标签元素.
*/
  static int addElementByTag(String xml,int beginPos,String tagName,List elements)
  { String ls_node;
    int li_begin = -1,li_end = -1,li_pos1 = beginPos,li_pos2;

    while(li_begin < 0)//找节点起始标签
    { li_pos2 = nextNodeBeginAt(xml,li_pos1);
      if(li_pos2 > li_pos1) 
      { ls_node = xml.substring(li_pos1,li_pos2);
        switch(nodeType(ls_node))
        { case NODE_BEGIN:      
                                if(nodeGetTag(ls_node).equals(tagName)) li_begin = li_pos1;
                                break;            
          case NODE_BEGIN_END:
                                if(nodeGetTag(ls_node).equals(tagName)) 
                                { li_begin = li_pos1;
                                  li_end = li_pos2;
                                }
                                break;
        }       
        li_pos1 = li_pos2;
      }
      else break;       
    }
    
    while(li_begin >= 0 && li_end < 0)//找节点结束标签
    { li_pos2 = nextNodeBeginAt(xml,li_pos1);
      if(li_pos2 > li_pos1)
      { ls_node = xml.substring(li_pos1,li_pos2);
        switch(nodeType(ls_node))
        { case NODE_END:      
                        if(nodeGetTag(ls_node).equals(tagName)) li_end = li_pos2;
                        break;            
        }
        li_pos1 = li_pos2;
      }
      else break;
    }
    
    if(li_begin >= 0 && li_end >= 0) 
    { String ls_element = xml.substring(li_begin,li_end);      
      System.out.println(ls_element);
      elements.add(ls_element);
    }
    
    return li_end;    
  }
  

  
/**
* 根据标签取元素文本数组（创建于 2005.04.10）.
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
创建于 2005.04.10.
</pre>
</DL>
* @param xml XML文本.
* @param tagName 标签名称.
* @return 标签元素文本数组.
*/
  public static String[] getElementsByTag(String xml,String tagName)
  { List lList = new ArrayList();
    int li_pos = 0;
    
    while(li_pos >= 0) 
    { li_pos = addElementByTag(xml,li_pos,tagName,lList);
    }
    
    String[] ls_elements = new String[lList.size()]; 
    lList.toArray(ls_elements);
    
    return ls_elements;
  }


  
/**
* 取指定标签元素文本（创建于 2005.04.10）.
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
创建于 2005.04.10.
</pre>
</DL>
* @param xml XML文档格式.
* @param tagName 标签名称.
* @return 标签元素文本.
*/
  public static String getElementByTag(String xml,String tagName)
  { List lList = new ArrayList();
    int li_pos = 0;
    
    if(addElementByTag(xml,li_pos,tagName,lList) <= 0) return null;
    
    String[] ls_elements = new String[lList.size()]; 
    lList.toArray(ls_elements);
    
    return ls_elements[0];    
  }
  

  
/**
* 取元素内容（创建于 2005.04.10）.
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
创建于 2005.04.10.
</pre>
</DL>
* @param element 元素文本.
* @return 元素内容.
*/
  public static String elementGetContent(String element)
  { String ls_node,ls_tag = "";
    int li_pos1 = 0,li_pos2;
    
    //找节点起始标签
    li_pos2 = nextNodeBeginAt(element,li_pos1);
    if(li_pos2 <= li_pos1) return null;
    
    ls_node = element.substring(li_pos1,li_pos2);
    switch(nodeType(ls_node))
    { case NODE_BEGIN:      
                            ls_tag = nodeGetTag(ls_node);
                            break;            
      default:
                            return "";
    }       
    
    //找节点结束标签    
    li_pos1 = li_pos2;
    li_pos2 = element.indexOf("</" + ls_tag + ">");
        
    if(li_pos2 <= li_pos1) return "";
    return element.substring(li_pos1,li_pos2);
  }
 

  
/**
* 取元素属性值（创建于 2005.04.10）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>

...

String ls_element = "<USER_NAME expression=\"# > @\">WYQ</USER_NAME>";
println(XMLMethod.elementGetAttribute(ls_element,"expression"));

...

执行结果如下：
-------------------------------------------
# > @


</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2005.04.10.
</pre>
</DL>
* @param element 元素文本.
* @param name 属性名.
* @return 属性值.
*/
 public static String elementGetAttribute(String element,String name)
 { String ls_node,ls_val;
    int li_pos1 = 0,li_pos2;

    //找节点起始标签
    li_pos2 = nextNodeBeginAt(element,li_pos1);
    if(li_pos2 <= li_pos1) return null;
    
    ls_node = element.substring(li_pos1,li_pos2);
    switch(nodeType(ls_node))
    { case NODE_BEGIN: 
      case NODE_BEGIN_END:            
            ls_val = nodeGetAttributes(ls_node);
            ls_val = attributesGetProperty(ls_val,name);
            ls_val = propertyGetValue(ls_val);
            break;            
      default:              
            ls_val = "";break;
    }       
    
    return ls_val;
  }
  
  
/**
* 取元素属性值数组（创建于 2005.04.10）.
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
创建于 2005.04.10.
</pre>
</DL>
* @param element 元素文本.
* @param names 属性名数组.
* @return 属性值数组.
*/
  public static String[] elementGetAttributes(String element,String[] names)
  { String[] ls_vals = new String[names.length];
    for(int i=0;i<ls_vals.length;i++)
      ls_vals[i] = elementGetAttribute(element,names[i]);
      
    return ls_vals;    
  }
  
/**
* 取与标签数组对应的元素（创建于 2005.04.10）.
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
创建于 2005.04.10.
</pre>
</DL>
* @param xml XML文本.
* @param tagNames 标签数组.
* @return 元素数组.
*/
  public static String[] getElementsByTags(String xml,String[] tagNames)
  { String[] ls_vals = new String[tagNames.length];
    for(int i=0;i<tagNames.length;i++)
      ls_vals[i] = getElementByTag(xml,tagNames[i]);      
    return ls_vals;      
  }
  

  
/**
* 取与标签数组对应的元素的值（创建于 2005.04.17）.
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
创建于 2005.04.17.
</pre>
</DL>
* @param xml XML文本.
* @param tagNames 标签数组.
* @return 元素值数组.
*/
  public static String[] getElementsContentByTags(String xml,String[] tagNames)
  { String[] ls_vals = new String[tagNames.length];
    for(int i=0;i<tagNames.length;i++)
    { String ls_element = getElementByTag(xml,tagNames[i]);
      if(ls_element==null) ls_vals[i] = "";//不存在的元素
      else ls_vals[i] = elementGetContent(ls_element);      
     }
    return ls_vals;     
  }
  

  
/**
* 取与标签数组对应的属性值（创建于 2005.04.19）.
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
创建于 2005.04.19.
</pre>
</DL>
* @param xml XML文本.
* @param tagNames 标签数组.
* @param property 属性名称.
* @return 元素属性值数组.
*/
  public static String[] getElementsAttributeByTags(String xml,String[] tagNames,String property)
  { String[] ls_vals = new String[tagNames.length];
    for(int i=0;i<tagNames.length;i++)
    { String ls_element = getElementByTag(xml,tagNames[i]);
      if(ls_element==null) ls_vals[i] = "";//不存在的元素
      else ls_vals[i] = elementGetAttribute(ls_element,property);      
     }
    return ls_vals;     
  }
  
}
