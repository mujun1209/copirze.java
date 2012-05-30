/*
 **********************************
  ,'''╭⌒╮⌒╮.',''',,',.'',,','',.  
 ╱◥██◣''o',''',,',.''.'',,',.  
｜田｜田田│ '',,',.',''',,',.''  
╬╬╬╬╬╬╬╬╬╬╬╬╬╬╬╬╬╬  \|/\|/\|/\|/\|/
			
Love life, love programming  -- mu jun

 ***********************************/

package triones.bas;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import Coprize.bas.IExtend;

/**
 * 扩展属性默认实现类（创建于 2012.01.04）.
 * <DL>
 * <DT><B>对象概述：</B>
 * <DD>
 * 
 * <pre>
 * 主要用于定义可扩展属性类的接口特征
 * </pre>
 * 
 * <DT><B>使用说明：</B>
 * <DD>
 * 
 * <pre>
 * 	在Coprize架构中，所有对象的实例都应该具备可扩展属性的能力
 * </pre>
 * 
 * <DT><B>名词解释：</B>
 * <DD>
 * 
 * <pre>
 * 	可扩展属性：可在程序执行过程中动态设定对象的属性及属性值
 * </pre>
 * 
 * <DT><B>注意事项：</B>
 * <DD>
 * 
 * <pre>
 * 	基于JDK1.3
 * </pre>
 * 
 * <DT><B>展望未来：</B>
 * <DD>
 * 
 * <pre>
 * 略
 * </pre>
 * 
 * <DT><B>联系方式：</B>
 * <DD>
 * 
 * <pre>
 * 	 email：360144561@qq.com
 * 	 电 话：13585880448
 * </pre>
 * 
 * </DL>
 * 
 * @author 穆俊
 * @version 2012.05.06
 */
public class DefaultExtend implements IExtend {
	
	/**
	 * 扩展属性名称和值的数据集合（创建于  2012.05.27）.
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
	创建于 2012.05.23.
	</pre>
	</DL>
	*/
	private HashMap<String, Object> _attributes = new HashMap<String, Object>();
	
	/**
	 * 保存扩展属性的名称和特性的数组（创建于  2012.05.27）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	 如果扩展属性有readonly的特性，或者其他的特性，该数组会记录下来 attrname:readonly
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2012.05.23.
	</pre>
	</DL>
	*/
	private ArrayList<String> keyExpr = new ArrayList<String>();

	/**
	 * 
	 * 扩展属性类--设置属性的默认实现（创建于 2012-5-8）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 		key :支持keyname:expr 的格式 系统默认支持的expr 只有：readonly 表示该属性是只读属性不支持修改。
	 * 			expr 可以自定义，方便AttributeNames(String cndExp)获取扩展属性。
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	 	只读扩展属性的设置：SetAttribute("name:readonly", "myname"); 名字为name的扩展属性为只读，值为：myname
	 * 	 	普通扩展属性的设置：SetAttribute("name", "myname");
	 * 	 	自定义expr扩展属性的设置：SetAttribute("name:myatt", "myatt");
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于 2012-5-8.
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @param key
	 *            扩展属性的名称
	 * @param val
	 *            扩展属性的值
	 * @return 0:表示key为空，设置失败，1：设置成功，-1:属性不可写
	 * @see coprize.bas.IExtend#SetAttribute(java.lang.String, java.lang.Object)
	 */
	@Override
	public int SetAttribute(String key, Object val)
	{
		if (key == null || "".equals(key))
		{
			return 0;
		}

		String[] keyArr = key.split(":");
		if (keyExpr.contains(keyArr[0].trim() + ":readonly"))
		{
			return -1;
		}

		this._attributes.put(key.trim(), val);

		keyExpr.add(key.trim());
		return 1;
	}

	/**
	 * 获取属性值（创建于 2012.01.04）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于 2012.01.04 .
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @param key
	 *            属性名.
	 * @return 属性值.
	 * @see #SetAttribute(String,Object)
	 */
	@Override
	public Object GetAttribute(String key)
	{
		return this._attributes.get(key.split(":")[0].trim());
	}

	/**
	 * 
	 * 通过expr表达式获取扩展属性名称数组（创建于 2012-5-8）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	expr：支持的格式有
	 * 		"!=value,value2,value3..." :表示扩展属性名称不为value && value2 && value3...的全部属性名称;
	 * 		"*" 或者 null :表示全部的属性
	 * 		"^=value,value2,value3..." :表示以value、value2、value3... 开头的属性名称
	 * 		"$=value,value2,value3..." :表示以value、value2、value3...结束的属性名称
	 * 		"*=value,value2,value3..." :表示以包含了value、value2、value3...的属性名称
	 * 		":readonly" :表示只读属性
	 * 		":expr" :表示设置扩展属性时表达式为expr的属性
	 * 		且："=" 表达式可以和 ":"表达式共用如下：
	 * 		"!=value:readonly" :表示名称不为value 的只读属性。其他的类推 ,注意 ":"表达式必须放在末尾
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于 2012-5-8.
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @param cndExp 属性名称表达式
	 * @return 符合表达式的属性名称数组
	 * @see coprize.bas.IExtend#AttributeNames(java.lang.String)
	 */
	@Override
	public String[] AttributeNames(String cndExp)
	{
		ArrayList<String> resNames = new ArrayList<String>();
		String[] eqExpr = cndExp.split("="); // 等于表达式的数组
		String[] selExpr = cndExp.split(":"); // 特性表达式数组

		System.out.println("等号表达式length：" + eqExpr.length + "特性表达式："
				+ selExpr.length);

		if (selExpr.length > 1 && eqExpr.length > 1) // 两种表达式都有
		{
			resNames = getSelExpr(keyExpr, selExpr[1]);
			resNames = getEqExpr(resNames, selExpr[0]); // 如果两种表达是都存在的时候，切割
														// ":"时必然将
														// "="表达式切割到selExpr[0]中
		}
		else
			if (selExpr.length > 1) // 只有":"表达式
			{
				resNames = getSelExpr(keyExpr, selExpr[1]);
			}
			else
				if (eqExpr.length > 1) // 只有等号表达式，或者查询全部
				{
					resNames = getEqExpr(keyExpr, cndExp);
				}
				else
				{
					return _attributes.keySet().toArray(new String[0]);
				}

		return resNames.toArray(new String[0]);
	}

	/**
	 * 
	 * 私有方法 从list中筛选 等号表达 结果 （创建于 2012-5-8）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于 2012-5-8.
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @param srclist
	 *            需要筛选的list
	 * @param expr
	 *            表达式
	 * @return 筛选结果
	 * @see package.class#method
	 */
	@SuppressWarnings("unused")
	private ArrayList<String> getEqExpr(ArrayList<String> srclist, String expr)
	{
		String[] eqExpr = expr.split("="); // 等于表达式的数组
		ArrayList<String> resNames = new ArrayList<String>();
		if (srclist == null || srclist.size() == 0)
		{
			return srclist == null ? new ArrayList<String>() : srclist;
		}
		if (eqExpr.length > 1)
		{
			for (String srcStr : srclist)
			{
				srcStr = srcStr.split(":")[0];
				if ("!".equals(eqExpr[0])) // 不包含value的名称
				{
					boolean matchexpr = false;
					for (String exp : eqExpr[1].split(","))
					{
						if (srcStr.equals(exp))
						{
							matchexpr = true;
						}
					}
					if (!matchexpr)
					{
						resNames.add(srcStr);
					}

				}
				else
					if ("*".equals(eqExpr[0]))
					{
						for (String exp : eqExpr[1].split(","))
						{
							if (srcStr.indexOf(exp) > -1)
							{
								resNames.add(srcStr);
							}
						}

					}
					else
						if ("^".equals(eqExpr[0]))
						{

							for (String exp : eqExpr[1].split(","))
							{
								if (srcStr.startsWith(exp))
								{
									resNames.add(srcStr);
								}
							}

						}
						else
							if ("$".equals(eqExpr[0]))
							{
								for (String exp : eqExpr[1].split(","))
								{
									if (srcStr.endsWith(exp))
									{
										resNames.add(srcStr);
									}
								}

							}
			}
		}
		else
		{

			return srclist;

		}

		return resNames;
	}

	/**
	 * 私有方法 从list中筛选 特性表达 结果 （创建于 2012-5-8）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于 2012-5-8.
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @param srclist
	 *            需要筛选的list
	 * @param expr
	 *            特性名称
	 * @return 筛选结果
	 * @see package.class#method
	 */
	@SuppressWarnings("unused")
	private ArrayList<String> getSelExpr(ArrayList<String> srclist, String expr)
	{
		ArrayList<String> resNames = new ArrayList<String>();

		for (String srcStr : srclist)
		{
			String[] keyArr = srcStr.split(":");
			if (keyArr.length > 1 && expr.equals(keyArr[1]))
			{
				resNames.add(keyArr[0]);
			}
		}

		return resNames;
	}

	/**
	 * 判断扩展属性是否存在（创建于 2012.01.04）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于 2012.01.04 - 吴勇庆.
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @param key
	 *            属性名.
	 * @return true代表该属性存在,false代表不存在.
	 */
	@Override
	public boolean Contain(String key)
	{
		return _attributes.containsKey(key.trim());
	}

	/**
	 * 判断扩展属性是否存在（创建于 2012.01.04）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于 2012.01.04 - 吴勇庆.
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @param key
	 *            属性名.
	 * @return true代表该属性存在,false代表不存在.
	 */
	@Override
	public boolean IsReadOnly(String key)
	{
		for (String keyStr : keyExpr)
		{
			if ((key + ":readonly").equals(keyStr))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * 提供对象的深度克隆方法 创建于 2012-5-29）.
	 * <P>
	 * <DL>
	 * <DT><B>说明：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	将本身深度克隆返回，通过二进制序列化的方式实现
	 * </pre>
	 * 
	 * <DT><B>示例：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 略
	 * </pre>
	 * 
	 * <DT><B>日志：</B>
	 * <DD>
	 * 
	 * <pre>
	 * 	创建于 2012-5-29.
	 * </pre>
	 * 
	 * </DL>
	 * 
	 * @return 克隆后的复制对象
	 * @throws Exception
	 * @see package.class#method
	 */
	public Object CloneObject() throws Exception
	{
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(byteOut);
		out.writeObject(this);
		ByteArrayInputStream byteIn = new ByteArrayInputStream(
				byteOut.toByteArray());
		ObjectInputStream in = new ObjectInputStream(byteIn);
		return in.readObject();
	}

}
