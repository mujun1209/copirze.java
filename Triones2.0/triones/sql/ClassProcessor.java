package triones.sql;

import triones.Debug;
import triones.sqlx.ClassTransactor;
import triones.sqlx.DBStore;
import triones.sqlx.Transaction;
import triones.sqlx.TransactorMethod;
import triones.frame.RecordSetTag;
import triones.frame.RecordSet;
import triones.frame.FrameMethod;

import java.sql.SQLException;
import java.util.List;

/**
* XML数据处理对象（创建于 2005.04.16）.
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
* @version	2005.04.16
*/
public class ClassProcessor
{ 
  
/**
* 数据存储对象（创建于 2005.04.16）.
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
创建于 2005.04.16.
</pre>
</DL>
*/
	Rule _Rule;
 

/**
* 构造方法（创建于 2005.04.16）.
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
创建于 2005.04.16.
</pre>
</DL>
* @param rule 数据名称规则.
*/
  public ClassProcessor(Rule rule)
  { _Rule = rule;
  }

  
/**
* 构造方法（创建于 2005.05.23）.
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
创建于 2005.05.23.
</pre>
</DL>
*/
  public ClassProcessor()
  { _Rule = new WYQRule();
  }
  

/**
* 数据传输（创建于 2005.04.16）.
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
创建于 2005.04.16.
</pre>
</DL>
* @param sqlca 数据传输对象.
*/
  Transaction _SQLCA;
/**
* 设置数据传输对象（创建于 2005.04.16）.
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
创建于 2005.04.16.
</pre>
</DL>
* @param sqlca 数据传输对象.
*/
  public void setTransaction(Transaction sqlca)
  { _SQLCA = sqlca;
  }
  

  
/**
* 新增数据（创建于 2005.04.16）.
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
创建于 2005.04.16.
</pre>
</DL>
* @param o 值对象.
* @return 新增的记录个数.
* @exception SQLException 数据库错误.
*/
  public int insert(Object o)
    throws SQLException
  { ClassTransactor lTransactor = getClassTransactor(o,_ZeroAsNullVal);
    return _SQLCA.insert(lTransactor);
  }
  
  
/**
* 删除数据（创建于 2005.04.16）.
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
创建于 2005.04.16.
</pre>
</DL>
* @param o 条件对象.
* @return 删除记录数.
* @exception SQLException 数据库错误.
*/
  public int delete(Object o)
    throws SQLException
  { ClassTransactor lTransactor = getClassTransactor(o,_ZeroAsNullCnd);
    lTransactor.hideCndFields();
    TransactorMethod.showCndFields(lTransactor,_ModeCnd);
    return _SQLCA.delete(lTransactor);
  }


  
/**
* 更改数据（创建于 2005.04.16）.
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
创建于 2005.04.16.
</pre>
</DL>
* @param oldObj 被修改的数据.
* @param newObj 修改后的数据.
* @return 修改的记录数.
* @exception SQLException 数据库错误.
*/
  public int update(Object oldObj,Object newObj)
    throws SQLException
  { ClassTransactor lTransOld = getClassTransactor(oldObj,_ZeroAsNullCnd);
    ClassTransactor lTransNew = getClassTransactor(newObj,_ZeroAsNullVal);
    lTransOld.hideCndFields();
    TransactorMethod.showCndFields(lTransOld,_ModeCnd);
    lTransNew.hideCndFields();
    lTransNew.hideFields();
    TransactorMethod.showFields(lTransNew,_ModeVal);
    lTransNew.appendCndTransactor(Syntax.CNDRELATION_AND,lTransOld);
    return _SQLCA.update(lTransNew);
  }
  

  
/**
* 查询数据（创建于 2005.04.16）.
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
创建于 2005.04.16.
</pre>
</DL>
* @param o 条件对象.
* @return 值列表.
* @exception SQLException 数据库错误.
* @exception IllegalAccessException 对应类的属性值必须初始化.
* @exception InstantiationException 对应类不存在，或者是一个抽象类，或者构造函数全都有参数.
*/
  	public List select(Object o)
    	throws SQLException,IllegalAccessException,InstantiationException
	{	ClassTransactor lTransactor = getClassTransactor(o,_ZeroAsNullCnd);
    	lTransactor.hideCndFields();
    	TransactorMethod.showCndFields(lTransactor,_ModeCnd);
    	return FrameMethod.toList(_SQLCA.select(lTransactor),lTransactor.getFactory());
  	}

		
/**
* 查询数据（创建于 2006.03.09）.
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
创建于 2006.03.09.
</pre>
</DL>
* @param cls 对象结构.
* @param cnd 查询条件，不含WHERE关键字.
* @return 对象列表.
* @exception SQLException 数据库错误.
* @exception IllegalAccessException 对应类的属性值必须初始化.
* @exception InstantiationException 对应类不存在，或者是一个抽象类，或者构造函数全都有参数.
*/		
	public List select(Class cls,String cnd)
		throws SQLException,IllegalAccessException,InstantiationException
	{ 	ClassTransactor lTransactor = getClassTransactor(cls);
    	lTransactor.hideCndFields();
		lTransactor.setSQLCondition(Syntax.CNDRELATION_AND,cnd);
    	return FrameMethod.toList(_SQLCA.select(lTransactor),lTransactor.getFactory());
	}
  
/**
* 处理值模式（创建于 2006.02.23）.
<DL>
<DT><B>说明：</B><DD>
<pre>
默认为有效值
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2006.02.23.
</pre>
</DL>
*/
	private int _ModeVal = TransactorMethod.VALUE_IS_VALID;

	
/**
* 设置处理值模式（创建于 2006.02.23）.
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
创建于 2006.02.23.
</pre>
</DL>
* @param mode 模式.
  @see #getModeVal()
* @see TransactorMethod
*/
	public void setModeVal(int mode)
	{	_ModeVal = mode;
	}

	
/**
* 取处理值模式（创建于 2006.02.23）.
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
创建于 2006.02.23.
</pre>
</DL>
* @return 模式.
* @see #setModeVal(int)
* @see TransactorMethod
*/
	public int getModeVal()
	{	return _ModeVal;
	}
/**
* 条件值模式（创建于 2006.02.23）.
<DL>
<DT><B>说明：</B><DD>
<pre>
默认值为VALUE_NOT_NULL
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2006.02.23.
</pre>
</DL>
*/
	private int _ModeCnd = TransactorMethod.VALUE_NOT_NULL;
/**
* 设置条件值模式（创建于 2006.02.23）.
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
创建于 2006.02.23.
</pre>
</DL>
* @param mode 模式.
  @see #getModeCnd()
* @see TransactorMethod
*/
	public void setModeCnd(int mode)
	{	_ModeCnd = mode;
	}
/**
* 取条件值匹配策略（创建于 2006.02.23）.
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
创建于 2006.02.23.
</pre>
</DL>
* @return 模式.
* @see #setModeCnd(int)
* @see TransactorMethod
*/
	public int getModeCnd()
	{	return _ModeCnd;
	}

/**
* 设置处理值0为NULL（创建于 2005.05.30）.
<DL>
<DT><B>说明：</B><DD>
<pre>
当使用int/double/short等基础类型时，无法表示NULL时，
可设置本属性为true,将0看成NULL
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2005.05.30.
</pre>
</DL>
*/
	private boolean _ZeroAsNullVal = false;
/**
* 设置条件值0为NULL（创建于 2005.05.30）.
<DL>
<DT><B>说明：</B><DD>
<pre>
当使用int/double/short等基础类型时，无法表示NULL时，
可设置本属性为true,将0看成NULL
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2005.05.30.
</pre>
</DL>
*/
	private boolean _ZeroAsNullCnd = false;
  
/**
* 设置处理值0为NULL（创建于 2005.05.30）.
<DL>
<DT><B>说明：</B><DD>
<pre>
当使用int/double/short等基础类型时，无法表示NULL时，
可设置本属性为true,将0看成NULL
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2005.05.30.
</pre>
</DL>
* @param option true代表使0作为null.
* @see #isZeroAsNullVal()
*/
  	public void zeroAsNullVal(boolean option)
	{	_ZeroAsNullVal = option;
  	}
	
/**
* 设置条件值0为NULL（创建于 2005.05.30）.
<DL>
<DT><B>说明：</B><DD>
<pre>
当使用int/double/short等基础类型时，无法表示NULL时，
可设置本属性为true,将0看成NULL
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2005.05.30.
</pre>
</DL>
* @param option true代表使0作为null.
* @see #isZeroAsNullCnd()
*/
	public void zeroAsNullCnd(boolean option)
	{ 	_ZeroAsNullCnd = option;
  	}	
 
  
/**
* 判断处理值是否当前为0作为null状态（创建于 2005.05.30）.
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
创建于 2005.05.30.
</pre>
</DL>
* @return true代表将0视为null状态.
* @see #zeroAsNullVal(boolean)
*/
  	public boolean isZeroAsNullVal()
	{ 	return _ZeroAsNullVal;
  	}
  
/**
* 判断条件值是否当前为0作为null状态（创建于 2005.05.30）.
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
创建于 2005.05.30.
</pre>
</DL>
* @return true代表将0视为null状态.
* @see #zeroAsNullCnd(boolean)
*/
  	public boolean isZeroAsNullCnd()
	{ 	return _ZeroAsNullCnd;
  	}
  
/**
* Transactor工厂（创建于 2005.05.30）.
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
创建于 2005.05.30.
</pre>
</DL>
* @param o 对象实例.
* @param zeroAsNull 0作为NULL模式.
* @return 对象Transactor.
*/
  	ClassTransactor getClassTransactor(Object o,boolean zeroAsNull)
	{	ClassTransactor lTransactor = new ClassTransactor(o,_Rule,zeroAsNull);
    	return lTransactor;
  	}
/**
* Transactor工厂（创建于 2005.05.30）.
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
创建于 2005.05.30.
</pre>
</DL>
* @param cls 对象实例.
* @return 对象Transactor.
*/
  	ClassTransactor getClassTransactor(Class cls)
	{	ClassTransactor lTransactor = new ClassTransactor(cls,_Rule);
    	return lTransactor;
  	}		
}
