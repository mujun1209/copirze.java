package trionesII.sql;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.sql.SQLException;

import trionesII.util.MyMethod;
import trionesII.util.StringMethod;
import trionesII.bas.Factory;

import trionesII.Debug;

/**********测试用例调试包*********
import java.io.File;					
*******************************/


/**
*  <img src="important.gif" width="35" height="25" border="0">数据存储类（创建于 2003.03.12）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
作用与PowerBuilder中的DataStore类似
</pre>
<DT><B>使用说明：</B><DD>
<pre>
<B>用DataStore进行数据查询的方式如下：</B>

...

sqlca = DB.getTransaction();	
//以U_ID为关键字
DSRControl dw = new DataStore("select U_ID,U_NAME,U_EMAIL from FBI_USER","U_ID");
			
dw.setTrans(sqlca);
			
int rowCount = dw.retrieve();

System.out.println("sql:\n" + sqlca.getSQLSyntax());

for(int i=1;i<=rowCount;i++)
{	System.out.println("U_NAME:U_EMAIL[" + i + "] = " + dw.getItem(i,"U_NAME") + " : " + dw.getItem(i,"U_EMAIL"));
}
			
sqlca.close();

...

执行结果如下：
----------------------------------------------

sql:
select U_ID,U_NAME,U_EMAIL from FBI_USER
U_NAME:U_EMAIL[1] = 访客 : guest@beyondbit.com
U_NAME:U_EMAIL[2] = 吴顺江 : wusj@beyondbit.com
U_NAME:U_EMAIL[3] = 张登火 : zhangdh@beyondbit.com
U_NAME:U_EMAIL[4] = 吴勇庆 : wyq@beyondbit.com
U_NAME:U_EMAIL[5] = 管理员 : wusj@beyondbit.com
U_NAME:U_EMAIL[6] = 吴骏飞 : wujf@beyondbit.com

----------------------------------------------

<B>用DataStore进行数据插入的方式如下：</B>

...

sqlca = DB.getTransaction();	
//以U_ID为关键字
DSRControl dw = new DataStore("select U_ID,U_NAME,U_EMAIL from FBI_USER","U_ID");

dw.setTrans(sqlca);

dw.insertRow(1);

dw.setItem(1,"U_ID","fjq");
dw.setItem(1,"U_NAME","方建青");
dw.setItem(1,"U_EMAIL","fjq@wondersgroup.com");

if(dw.update() > 0)
{	sqlca.commit();				
	System.out.println("insert success!");
}
else
{	sqlca.rollback();
	System.out.println("insert failed!");
}

sqlca.close();

...

执行结果如下：
--------------------------------------------

insert success!
sql:
INSERT INTO FBI_USER(U_ID,U_NAME,U_EMAIL) VALUES(?,?,?)

--------------------------------------------

<B>用DataStore进行数据修改的方式如下：</B>

...

sqlca = DB.getTransaction();	
//以U_ID为关键字
DSRControl dw = new DataStore("select U_ID,U_NAME,U_EMAIL from FBI_USER","U_ID");

dw.setTrans(sqlca);

dw.insertRow(1);

dw.setItem(1,"U_ID","fjq");
dw.setItem(1,"U_NAME","方建青");
dw.setItem(1,"U_EMAIL","fangjq@wondersgroup.com");

dw.setRowStatus(1,dw.ROW_UPDATE);

if(dw.update() > 0)
{	sqlca.commit();				
	System.out.println("update success!");
}
else
{	sqlca.rollback();
	System.out.println("update failed!");
}

System.out.println("sql:\n" + sqlca.getSQLSyntax());

sqlca.close();

...

执行结果如下：
--------------------------------------------------

update success!
sql:
UPDATE FBI_USER SET U_ID = ?,U_NAME = ?,U_EMAIL = ? WHERE (U_ID = ?)

--------------------------------------------------

<B>用DataStore进行数据删除的方式如下：</B>

...

sqlca = DB.getTransaction();	
//以U_ID为关键字
DSRControl dw = new DataStore("select U_ID,U_NAME,U_EMAIL from FBI_USER","U_ID");

dw.setTrans(sqlca);

dw.insertRow(1);

dw.setItem(1,"U_ID","fjq");
dw.setItem(1,"U_NAME","方建青");
dw.setItem(1,"U_EMAIL","fangjq@wondersgroup.com");

dw.setRowStatus(1,dw.ROW_DELETE);

if(dw.update() > 0)
{	sqlca.commit();				
	System.out.println("delete success!");
}
else
{	sqlca.rollback();
	System.out.println("delete failed!");
}

System.out.println("sql:\n" + sqlca.getSQLSyntax());

sqlca.close();

...

执行结果如下：
--------------------------------------------

delete success!
sql:
DELETE FROM FBI_USER WHERE (U_ID = ?)

--------------------------------------------
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
* @version	2003.03.12
*/
public class DataStore implements DSRControl
{	

/**
* 构造方法（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
根据数据对象接口创建数据存储类.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.13.
</pre>
</DL>
* @param obj 数据对象.
*/
	public DataStore(DataObject obj)
	{	init(obj);
	}
	

	
/**
* 构造方法（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
//以U_ID为关键字
DSRControl dw = new DataStore("select U_ID,U_NAME,U_EMAIL from FBI_USER","U_ID");
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.13.
</pre>
</DL>
* @param sql 查询语句.
* @param primaryKey 关键字.
	@see #DataStore(DataObject)
*/
	public DataStore(String sql,String primaryKey)
	{	init(new DefaultDataObject(sql,primaryKey));
	}
	
/**
* 构造方法（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
采用本方法构造的数据存储对象默认情况下为只读的，即不能执行update方法。
</pre>
<DT><B>示例：</B><DD>
<pre>
DSRControl dw = new DataStore("select U_ID,U_NAME,U_EMAIL from FBI_USER");
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.13.
</pre>
</DL>
* @param sql 查询语句.
	@see #DataStore(DataObject)
*/
	public DataStore(String sql)
	{	init(new DefaultDataObject(sql,""));
	}

	
/**
* 初始化（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
用于构造方法.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.13.
</pre>
</DL>
* @param obj 数据对象.
*/
	private void init(DataObject obj)
	{	String ls_PrimaryKey = obj.getPrimaryKey();
		//创建Operator
		_Operator = new ActiveOperator();
		_Operator.setName(obj.getTable());
		_Operator.setDBName(obj.getTable());
		for(int i=1;i<=obj.colCount();i++)
		{	_Operator.addField(obj.getColName(i),obj.getColType(i),obj.getColValue(i));
		}
		
		_Operator.hideCndFields();
		OperatorMethod.hideFields(_Operator,obj.getReadOnlyKey());//设置在INSERT、UPDATE、DELETE操作时不受影响的列
		
		String ls_sql = obj.getSQLSelect();		
		if(!Syntax.isSQLSelect(ls_sql)) ls_sql = _Operator.prepareSelect(false);		
		setSQLSelect(ls_sql);//设置默认的SQLSELECT
		
		if(StringMethod.isValid(ls_PrimaryKey))//设置UPDATE、DELETE操作时的条件列
		{	_RowFactory = new RowTagFactory(_Operator,ls_PrimaryKey);		
			//OperatorMethod.showCndFields(_Operator,ls_PrimaryKey);
		}
		else
		{	_RowFactory = new RowTagFactory(_Operator);
			setReadOnly(true);
		}
	}
/**
* 数据查询语句（创建于 2003.03.13）.
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
创建于 2003.03.13.
</pre>
</DL>
*/
	private String _SQLSelect;
	

	
/**
* 取当前定义的数据查询语句（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>

...

DSRControl dw = new DataStore("select u_id,U_NAME,u_email from fbi_user","U_ID");
System.out.println(dw.getSQLSelect());

...

执行结果如下：
-------------------------------------------

select u_id,U_NAME,u_email from fbi_user

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.13.
</pre>
</DL>
* @return 查询语句.
* @see #setSQLSelect(String)
*/
	public String getSQLSelect()
	{ return _SQLSelect;
	}
	

	
/**
* 设置当前的数据查询语句（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>

...

Transaction sqlca = DB.getTransaction();	
//以U_ID为关键字
//DSRControl dw= new DataStore("select U_ID,U_NAME,U_EMAIL from FBI_USER","U_ID");
DataStore dw= new DataStore("select u_id,U_NAME,u_email from fbi_user","U_ID");
dw.setTrans(sqlca);

dw.setSQLSelect("select u_id,U_NAME,u_email from fbi_user where u_email like 'w%'");			

dw.retrieve();

for(int i=1;i<=dw.rowCount();i++)
{	System.out.println("U_NAME:U_EMAIL[" + i + "] = " + dw.getItem(i,"u_name") + " : " + dw.getItem(i,"u_email"));
}			
		
sqlca.close();

...

执行结果如下：
-------------------------------------------

U_NAME:U_EMAIL[1] = 吴顺江 : wusj@beyondbit.com
U_NAME:U_EMAIL[2] = 吴勇庆 : wyq@beyondbit.com
U_NAME:U_EMAIL[3] = 管理员 : wusj@beyondbit.com
U_NAME:U_EMAIL[4] = 吴骏飞 : wujf@beyondbit.com

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.13.
</pre>
</DL>
* @param sql 查询语句.
* @see #getSQLSelect()
*/
	public void setSQLSelect(String sql)
	{	_SQLSelect = sql;
	}

	
	
/**
* 数据库事务传输对象（创建于 2003.03.12）.
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
创建于 2003.03.12.
</pre>
</DL>
*/
	private Transaction _SQLCA;
	
	
/**
* 内置指令集（创建于 2003.03.18）.
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
创建于 2003.03.18.
</pre>
</DL>
*/
	private ActiveOperator _Operator;
	
/**
* 行记录列表（创建于 2003.03.12）.
<DL>
<DT><B>说明：</B><DD>
<pre>
列表中的元素为RowTag
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
*/
	List _Rows = new ArrayList();
	
	List _DeletedRows = new ArrayList();	

	
/**
* 是否为只读的数据存储对象（创建于 2003.03.17）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
只读的数据存储对象指只能进行retrieve操作，而不能进行update操作的对象实例.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.17.
</pre>
</DL>
*/
	boolean _IsReadOnly = false;
	

	
/**
* 是否为只读的数据存储对象（创建于 2003.03.17）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
只读的数据存储对象指只能进行retrieve操作，而不能进行update操作的对象实例.
在本实现中，根据初始化信息中是否包含关键字来确定是否为只读的；如果没有关键字，则为只读的.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.17.
</pre>
</DL>
* @return true代表为只读的数据存储对象.
	@see #setReadOnly(boolean)
*/
	public boolean isReadOnly()
	{	return _IsReadOnly;
	}
/**
* 设置数据存储对象的只读属性（创建于 2003.03.17）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
只读的数据存储对象指只能进行retrieve操作，而不能进行update操作的对象实例.
在本实现中，根据初始化信息中是否包含关键字来确定是否为只读的；如果没有关键字，则为只读的.
在某些情况下，可以强制指定数据存储对象的只读属性为false以便执行某些特殊的SQL指令.
或者关闭只读属性，使得其不能执行update操作.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.17.
</pre>
</DL>
* @param option true代表设为只读的数据存储对象.
	@see #setReadOnly(boolean)
*/
	public void setReadOnly(boolean option)
	{	_IsReadOnly = option;
	}
/**
* 与行记录相关的基因工厂（创建于 2003.03.12）.
<DL>
<DT><B>说明：</B><DD>
<pre>
用于在查询时将记录集中的值转换为行记录.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
*/
	Factory _RowFactory;
	

	
/**
* 设置数据库事务传输对象（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
数据库事务传输对象为数据存储对象提供数据库访问资源.
</pre>
<DT><B>示例：</B><DD>
<pre>
<DT><B>示例：</B><DD>
<pre>

...

Transaction sqlca = DB.getTransaction();	
//以U_ID为关键字
//DSRControl dw = new DataStore("select U_ID,U_NAME,U_EMAIL from FBI_USER","U_ID");
DSRControl dw = new DataStore("select u_id,U_NAME,u_email from fbi_user","U_ID");

dw.setTrans(sqlca);

int rowCount = dw.retrieve();
		
for(int i=1;i<=rowCount;i++)
{	System.out.println("U_NAME:ROWSTATUS[" + i + "] = " + dw.getItem(i,"u_name") + " : " + dw.getItem(i,"u_email"));
}	

sqlca.close();

...

执行结果如下：
-------------------------------------------

U_NAME:ROWSTATUS[1] = 访客 : guest@beyondbit.com
U_NAME:ROWSTATUS[2] = 吴顺江 : wusj@beyondbit.com
U_NAME:ROWSTATUS[3] = 张登火 : zhangdh@beyondbit.com
U_NAME:ROWSTATUS[4] = 吴勇庆 : wyq@beyondbit.com
U_NAME:ROWSTATUS[5] = 管理员 : wusj@beyondbit.com
U_NAME:ROWSTATUS[6] = 吴骏飞 : wujf@beyondbit.com

</pre>
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @param sqlca 数据库事务传输对象.
*/
	public void setTrans(Transaction sqlca)
	{ _SQLCA = sqlca;
	}
	

	
/**
* 创建行记录（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
根据构造时提供的数据源信息创建行记录.
行号的取值范围:1..RowCount().
行号等于0，代表将行添加到末尾.
</pre>
<DT><B>示例：</B><DD>
<pre>

...

Transaction sqlca = DB.getTransaction();	
//以U_ID为关键字
//DSRControl dw = new DataStore("select U_ID,U_NAME,U_EMAIL from FBI_USER","U_ID");
DSRControl dw = new DataStore("select u_id,U_NAME,u_email from fbi_user","U_ID");

dw.setTrans(sqlca);

int rowCount = dw.retrieve();
		
for(int i=1;i<=rowCount;i++)
{	System.out.println("U_NAME:ROWSTATUS[" + i + "] = " + dw.getItem(i,"u_name") + " : " + dw.getItem(i,"u_email"));
}	

dw.insertRow(2);
System.out.println("after insertRow");

for(int i=1;i<=dw.rowCount();i++)
{	System.out.println("U_NAME:ROWSTATUS[" + i + "] = " + dw.getItem(i,"u_name") + " : " + dw.getItem(i,"u_email"));
}	

sqlca.close();

...

执行结果如下：
-------------------------------------------

U_NAME:ROWSTATUS[1] = 访客 : guest@beyondbit.com
U_NAME:ROWSTATUS[2] = 吴顺江 : wusj@beyondbit.com
U_NAME:ROWSTATUS[3] = 张登火 : zhangdh@beyondbit.com
U_NAME:ROWSTATUS[4] = 吴勇庆 : wyq@beyondbit.com
U_NAME:ROWSTATUS[5] = 管理员 : wusj@beyondbit.com
U_NAME:ROWSTATUS[6] = 吴骏飞 : wujf@beyondbit.com
after insertRow
U_NAME:ROWSTATUS[1] = 访客 : guest@beyondbit.com
U_NAME:ROWSTATUS[2] = null : null
U_NAME:ROWSTATUS[3] = 吴顺江 : wusj@beyondbit.com
U_NAME:ROWSTATUS[4] = 张登火 : zhangdh@beyondbit.com
U_NAME:ROWSTATUS[5] = 吴勇庆 : wyq@beyondbit.com
U_NAME:ROWSTATUS[6] = 管理员 : wusj@beyondbit.com
U_NAME:ROWSTATUS[7] = 吴骏飞 : wujf@beyondbit.com


</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @param row 行号.
* @param rowStatus 行状态.
* @return 新增后的行个数.
* @see #deleteRow(int)
	@see #ROW_INSERT
	@see #ROW_UPDATE
	@see #ROW_DELETE
*/
	public int insertRow(int row)
	{ RowTag lRow = new RowTag(ROW_INSERT,(Operator)MyMethod.deepClone(_Operator));
		
		if(row>=1)
			_Rows.add(rowIndex(row),lRow);
		else//row=0代表最后一行
			_Rows.add(lRow);		
		
		return _Rows.size();
	}
	

	
/**
* 删除行记录（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
行号的取值范围:1..RowCount().
行号等于0，代表将删除最后一行.
本方法不影响数据库操作.
</pre>
<DT><B>示例：</B><DD>
<pre>

...

Transaction sqlca = DB.getTransaction();	
//以U_ID为关键字
//DSRControl dw = new DataStore("select U_ID,U_NAME,U_EMAIL from FBI_USER","U_ID");
DSRControl dw = new DataStore("select u_id,u_name,u_email from fbi_user","u_id");

dw.setTrans(sqlca);

int rowCount = dw.retrieve();

//System.out.println("sql:\n" + sqlca.getSQLSyntax());
System.out.println("after retrieve = " + dw.deletedCount());

for(int i=1;i<=rowCount;i++)
{	System.out.println("U_NAME:U_EMAIL[" + i + "] = " + dw.getItem(i,"u_name") + " : " + dw.getItem(i,"u_email"));
}

dw.deleteRow(6);

System.out.println("after delete = " + dw.deletedCount());

for(int i=1;i<=dw.rowCount();i++)
{	System.out.println("U_NAME:U_EMAIL[" + i + "] = " + dw.getItem(i,"u_name") + " : " + dw.getItem(i,"u_email"));
}

...

执行结果如下：
-------------------------------------------

after retrieve = 0
U_NAME:U_EMAIL[1] = 访客 : guest@beyondbit.com
U_NAME:U_EMAIL[2] = 吴顺江 : wusj@beyondbit.com
U_NAME:U_EMAIL[3] = 张登火 : zhangdh@beyondbit.com
U_NAME:U_EMAIL[4] = 吴勇庆 : wyq@beyondbit.com
U_NAME:U_EMAIL[5] = 管理员 : wusj@beyondbit.com
U_NAME:U_EMAIL[6] = 吴骏飞 : wujf@beyondbit.com
after delete = 1
U_NAME:U_EMAIL[1] = 访客 : guest@beyondbit.com
U_NAME:U_EMAIL[2] = 吴顺江 : wusj@beyondbit.com
U_NAME:U_EMAIL[3] = 张登火 : zhangdh@beyondbit.com
U_NAME:U_EMAIL[4] = 吴勇庆 : wyq@beyondbit.com
U_NAME:U_EMAIL[5] = 管理员 : wusj@beyondbit.com


</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @param row 行号.
* @return 删除后的行个数.
* @see #insertRow(int)
*/
	public int deleteRow(int row)
	{	int li_row = rowIndex((row>=1?row:_Rows.size()));//row=0代表最后一行
		RowTag lRow = (RowTag)(_Rows.get(li_row));
		
		lRow.setStatus(ROW_DELETE);		
		_DeletedRows.add(lRow);
		
		_Rows.remove(li_row);

		return _Rows.size();
	}
	

	
/**
* 行索引（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
行索引指行记录在List中的位置，与行号的定义不同。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @param row 行号.
* @return 行索引.
*/
	private int rowIndex(int row)
	{ return row - 1;
	}
/**
* 索引行（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
索引行指List中的索引记录对应的行号。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @param  行索引.
* @return 行号.
*/
	private int indexRow(int index)
	{	return index + 1;
	}
	
/**
* 设置元素值（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
元素位置由行、列来确定.
列标识的取值范围：1..colCount().
</pre>
<DT><B>示例：</B><DD>
<pre>

...

Transaction sqlca = DB.getTransaction();	
//以U_ID为关键字
//DSRControl dw= new DataStore("select U_ID,U_NAME,U_EMAIL from FBI_USER","U_ID");
DataStore dw= new DataStore("select u_id,U_NAME,u_email from fbi_user where u_name like ?","U_ID");
String[] ls_args = {"吴%"};
dw.setTrans(sqlca);

dw.retrieve(Arrays.asList(ls_args));

for(int i=1;i<=dw.rowCount();i++)
{	System.out.println("U_NAME:ROWSTATUS[" + i + "] = " + dw.getItem(i,"u_name") + " : " + dw.getItem(i,"u_email"));
}			

dw.setItem(3,2,"王虎");

System.out.println("after setItem");

for(int i=1;i<=dw.rowCount();i++)
{	System.out.println("U_NAME:ROWSTATUS[" + i + "] = " + dw.getItem(i,"u_name") + " : " + dw.getItem(i,"u_email"));
}			

sqlca.close();

...

执行结果如下：
-------------------------------------------

U_NAME:ROWSTATUS[1] = 吴顺江 : wusj@beyondbit.com
U_NAME:ROWSTATUS[2] = 吴勇庆 : wyq@beyondbit.com
U_NAME:ROWSTATUS[3] = 吴骏飞 : wujf@beyondbit.com
after setItem
U_NAME:ROWSTATUS[1] = 吴顺江 : wusj@beyondbit.com
U_NAME:ROWSTATUS[2] = 吴勇庆 : wyq@beyondbit.com
U_NAME:ROWSTATUS[3] = 王虎 : wujf@beyondbit.com

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @param row 行号.
* @param col 列标识.
* @param val 元素值.
* @see #setItem(int,String,Object)
*/
	public void setItem(int row,int col,Object val)
	{ RowTag lRow = (RowTag)_Rows.get(rowIndex(row));
		Operator lOperator = lRow.getOperator();
		
		switch(lRow.getStatus())//修改默认行状态
		{	case ROW_INITIAL:	lRow.setStatus(ROW_UPDATE);break;
			default					:	break;
		}
		
		lOperator.setFieldValue(col,val);
	}
/**
* 设置元素值（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
元素位置由行、列来确定.
</pre>
<DT><B>示例：</B><DD>
<pre>

...

Transaction sqlca = DB.getTransaction();	
//以U_ID为关键字
//DSRControl dw= new DataStore("select U_ID,U_NAME,U_EMAIL from FBI_USER","U_ID");
DataStore dw= new DataStore("select u_id,U_NAME,u_email from fbi_user where u_name like ?","U_ID");
String[] ls_args = {"吴%"};
dw.setTrans(sqlca);

dw.retrieve(Arrays.asList(ls_args));

for(int i=1;i<=dw.rowCount();i++)
{	System.out.println("U_NAME:ROWSTATUS[" + i + "] = " + dw.getItem(i,"u_name") + " : " + dw.getItem(i,"u_email"));
}			

dw.setItem(3,"u_name","王虎");

System.out.println("after setItem");

for(int i=1;i<=dw.rowCount();i++)
{	System.out.println("U_NAME:ROWSTATUS[" + i + "] = " + dw.getItem(i,"u_name") + " : " + dw.getItem(i,"u_email"));
}			

sqlca.close();

...

执行结果如下：
-------------------------------------------

U_NAME:ROWSTATUS[1] = 吴顺江 : wusj@beyondbit.com
U_NAME:ROWSTATUS[2] = 吴勇庆 : wyq@beyondbit.com
U_NAME:ROWSTATUS[3] = 吴骏飞 : wujf@beyondbit.com
after setItem
U_NAME:ROWSTATUS[1] = 吴顺江 : wusj@beyondbit.com
U_NAME:ROWSTATUS[2] = 吴勇庆 : wyq@beyondbit.com
U_NAME:ROWSTATUS[3] = 王虎 : wujf@beyondbit.com

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @param row 行号.
* @param col 列名.
* @param val 元素值.
* @see #setItem(int,int,Object)
*/
	public void setItem(int row,String col,Object val)
	{ setItem(row,getColID(col),val);
	}
	
/**
* 取元素值（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
元素位置由行、列来确定.
</pre>
<DT><B>示例：</B><DD>
<pre>

...

Transaction sqlca = DB.getTransaction();	
//以U_ID为关键字
//DSRControl dw = new DataStore("select U_ID,U_NAME,U_EMAIL from FBI_USER","U_ID");
DSRControl dw = new DataStore("select u_id,U_NAME,u_email from fbi_user","U_ID");
dw.setTrans(sqlca);

int rowCount = dw.retrieve();
		
for(int i=1;i<=rowCount;i++)
{	System.out.println("U_NAME:U_EMAIL[" + i + "] = " + dw.getItem(i,2) + " : " + dw.getItem(i,3));
}			
	
sqlca.close();

...

执行结果如下：
-------------------------------------------

U_NAME:U_EMAIL[1] = 访客 : guest@beyondbit.com
U_NAME:U_EMAIL[2] = 吴顺江 : wusj@beyondbit.com
U_NAME:U_EMAIL[3] = 张登火 : zhangdh@beyondbit.com
U_NAME:U_EMAIL[4] = 吴勇庆 : wyq@beyondbit.com
U_NAME:U_EMAIL[5] = 管理员 : wusj@beyondbit.com
U_NAME:U_EMAIL[6] = 吴骏飞 : wujf@beyondbit.com

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @param row 行号.
* @param col 列标识.
* @return 元素值.
* @see #getItem(int,String)
*/
	public Object getItem(int row,int col)
	{ RowTag lRow = (RowTag)_Rows.get(rowIndex(row));
		Operator lOperator = lRow.getOperator();
		
		return lOperator.getFieldValue(col);
	}
/**
* 取元素值（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
元素位置由行、列来确定.
</pre>
<DT><B>示例：</B><DD>
<pre>

...

Transaction sqlca = DB.getTransaction();	
//以U_ID为关键字
//DSRControl dw = new DataStore("select U_ID,U_NAME,U_EMAIL from FBI_USER","U_ID");
DSRControl dw = new DataStore("select u_id,U_NAME,u_email from fbi_user","U_ID");
dw.setTrans(sqlca);

int rowCount = dw.retrieve();
		
for(int i=1;i<=rowCount;i++)
{	System.out.println("U_NAME:U_EMAIL[" + i + "] = " + dw.getItem(i,"u_name") + " : " + dw.getItem(i,"U_EMAIL"));
}			
	
sqlca.close();

...

执行结果如下：
-------------------------------------------

U_NAME:U_EMAIL[1] = 访客 : guest@beyondbit.com
U_NAME:U_EMAIL[2] = 吴顺江 : wusj@beyondbit.com
U_NAME:U_EMAIL[3] = 张登火 : zhangdh@beyondbit.com
U_NAME:U_EMAIL[4] = 吴勇庆 : wyq@beyondbit.com
U_NAME:U_EMAIL[5] = 管理员 : wusj@beyondbit.com
U_NAME:U_EMAIL[6] = 吴骏飞 : wujf@beyondbit.com

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @param row 行号.
* @param col 列名.
* @return 元素值.
* @see #getItem(int,int)
*/
	public Object getItem(int row,String col)
	{ return getItem(row,getColID(col));
	}

	
/**
* 提交当前所有行的修改操作（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
各行记录的数据库操作方式根据行状态的值确定。
只读的数据存储对象是不能执行本方法的.
对关键字列的值进行修改将导致
</pre>
<DT><B>示例 1</B><DD>
<pre>

...

Transaction sqlca = DB.getTransaction();	
//以U_ID为关键字
//DSRControl dw= new DataStore("select U_ID,U_NAME,U_EMAIL from FBI_USER","U_ID");
DataStore dw= new DataStore("select u_id,U_NAME,u_email from fbi_user","U_ID");
dw.setTrans(sqlca);
dw.retrieve();

for(int i=1;i<=dw.rowCount();i++)
{	System.out.println("U_NAME:U_EMAIL[" + i + "] = " + dw.getItem(i,"u_name") + " : " + dw.getItem(i,"u_email"));
}			

dw.deleteRow(3);

int li_row = dw.insertRow(0);

dw.setItem(li_row,"U_NAME","蔡虹");
dw.setItem(li_row,"U_EMAIL","caihong@wondersgroup.com");
dw.setItem(li_row,"U_ID","caih");

if(dw.update()>0)
{	sqlca.commit();
	System.out.println("update success");
}
else
{	sqlca.rollback();
	System.out.println("update failure");
}
	
dw.reset();

dw.retrieve();

for(int i=1;i<=dw.rowCount();i++)
{	System.out.println("U_NAME:U_EMAIL[" + i + "] = " + dw.getItem(i,"u_name") + " : " + dw.getItem(i,"u_email"));
}					

sqlca.close();

...

执行结果如下：
-------------------------------------------

U_NAME:U_EMAIL[1] = 访客 : guest@beyondbit.com
U_NAME:U_EMAIL[2] = 吴顺江 : wusj@beyondbit.com
U_NAME:U_EMAIL[3] = 张登火 : zhangdh@beyondbit.com
U_NAME:U_EMAIL[4] = 吴勇庆 : wyq@beyondbit.com
U_NAME:U_EMAIL[5] = 管理员 : wusj@beyondbit.com
U_NAME:U_EMAIL[6] = 吴骏飞 : wujf@beyondbit.com
update success
U_NAME:U_EMAIL[1] = 访客 : guest@beyondbit.com
U_NAME:U_EMAIL[2] = 吴顺江 : wusj@beyondbit.com
U_NAME:U_EMAIL[3] = 蔡虹 : caihong@wondersgroup.com
U_NAME:U_EMAIL[4] = 吴勇庆 : wyq@beyondbit.com
U_NAME:U_EMAIL[5] = 管理员 : wusj@beyondbit.com
U_NAME:U_EMAIL[6] = 吴骏飞 : wujf@beyondbit.com

</pre>
<DT><B>示例 2</B><DD>
<pre>

...

Transaction sqlca = DB.getTransaction();	
//以U_ID为关键字
//DSRControl dw= new DataStore("select U_ID,U_NAME,U_EMAIL from FBI_USER","U_ID");
DataStore dw= new DataStore("select u_id,U_NAME,u_email from fbi_user","U_ID");
dw.setTrans(sqlca);
dw.retrieve();

for(int i=1;i<=dw.rowCount();i++)
{	System.out.println("U_NAME:U_EMAIL[" + i + "] = " + dw.getItem(i,"u_name") + " : " + dw.getItem(i,"u_email"));
}			

dw.setItem(3,"U_NAME","张登火");
dw.setItem(3,"U_EMAIL","zhangdh@beyondbit.com");
dw.setItem(3,"U_ID","zhangdh");

if(dw.update()>0)
{	sqlca.commit();
	System.out.println("update success");
}
else
{	sqlca.rollback();
	System.out.println("update failure");
}
	
dw.reset();

dw.retrieve();

for(int i=1;i<=dw.rowCount();i++)
{	System.out.println("U_NAME:U_EMAIL[" + i + "] = " + dw.getItem(i,"u_name") + " : " + dw.getItem(i,"u_email"));
}					

sqlca.close();

...

执行结果如下：
-------------------------------------------

U_NAME:U_EMAIL[1] = 访客 : guest@beyondbit.com
U_NAME:U_EMAIL[2] = 吴顺江 : wusj@beyondbit.com
U_NAME:U_EMAIL[3] = 蔡虹 : caihong@wondersgroup.com
U_NAME:U_EMAIL[4] = 吴勇庆 : wyq@beyondbit.com
U_NAME:U_EMAIL[5] = 管理员 : wusj@beyondbit.com
U_NAME:U_EMAIL[6] = 吴骏飞 : wujf@beyondbit.com
update success
U_NAME:U_EMAIL[1] = 访客 : guest@beyondbit.com
U_NAME:U_EMAIL[2] = 吴顺江 : wusj@beyondbit.com
U_NAME:U_EMAIL[3] = 张登火 : zhangdh@beyondbit.com
U_NAME:U_EMAIL[4] = 吴勇庆 : wyq@beyondbit.com
U_NAME:U_EMAIL[5] = 管理员 : wusj@beyondbit.com
U_NAME:U_EMAIL[6] = 吴骏飞 : wujf@beyondbit.com

</pre>
<DT><B>示例：</B><DD>
<pre>

...

Transaction sqlca = DB.getTransaction();	
//以U_ID为关键字
//DSRControl dw= new DataStore("select U_ID,U_NAME,U_EMAIL from FBI_USER","U_ID");
DataStore dw= new DataStore("select u_id,U_NAME,u_email from fbi_user","U_ID,u_name");
dw.setTrans(sqlca);
dw.retrieve();

for(int i=1;i<=dw.rowCount();i++)
{	System.out.println("U_NAME:U_EMAIL[" + i + "] = " + dw.getItem(i,"u_name") + " : " + dw.getItem(i,"u_email"));
}			

dw.setItem(3,"U_NAME","王虎");
dw.setItem(3,"U_EMAIL","tiger@wondersgroup.com");
dw.setItem(3,"U_ID","wangh");
dw.setRowStatus(3,dw.ROW_INITIAL);//使修改无效

dw.setRowStatus(6,dw.ROW_DELETE);//删除第6行记录

if(dw.update()>0)
{	sqlca.commit();
	System.out.println("update success");
}
else
{	sqlca.rollback();
	System.out.println("update failure");
}
	
dw.reset();

dw.retrieve();

for(int i=1;i<=dw.rowCount();i++)
{	System.out.println("U_NAME:U_EMAIL[" + i + "] = " + dw.getItem(i,"u_name") + " : " + dw.getItem(i,"u_email"));
}					

sqlca.close();

...

执行结果如下：
-------------------------------------------

U_NAME:U_EMAIL[1] = 访客 : guest@beyondbit.com
U_NAME:U_EMAIL[2] = 吴顺江 : wusj@beyondbit.com
U_NAME:U_EMAIL[3] = 张登火 : zhangdh@beyondbit.com
U_NAME:U_EMAIL[4] = 吴勇庆 : wyq@beyondbit.com
U_NAME:U_EMAIL[5] = 管理员 : wusj@beyondbit.com
U_NAME:U_EMAIL[6] = 吴骏飞 : wujf@beyondbit.com
update success
U_NAME:U_EMAIL[1] = 访客 : guest@beyondbit.com
U_NAME:U_EMAIL[2] = 吴顺江 : wusj@beyondbit.com
U_NAME:U_EMAIL[3] = 张登火 : zhangdh@beyondbit.com
U_NAME:U_EMAIL[4] = 吴勇庆 : wyq@beyondbit.com
U_NAME:U_EMAIL[5] = 管理员 : wusj@beyondbit.com

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @return 本次修改成功的行记录数.
* @exception SQLException 数据库错误或只读的数据存储对象.
*/
	public int update()
		throws SQLException
	{	if(isReadOnly()) throw new SQLException("This DSRControl is ReadOnly, so the usage of update() is rejected");
		
		int li_count = 0;		
		
		li_count += updateBuffer(_DeletedRows);
		_DeletedRows.clear();//清空删除缓存区中的数据
		
		li_count += updateBuffer(_Rows);
		
		return li_count;		
	}


	
/**
* 将缓存区数据更新到数据库（创建于 2003.05.14）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
对已更新数据还原行状态为初始状态;
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.05.14.
</pre>
</DL>
* @param buf 缓存区.
* @return 发生更新的记录数.
* @exception SQLException 数据库错误.
* @see #update()
*/
 private int updateBuffer(List buf)
	throws SQLException
 { int li_count = 0;		
		
		for(int i=0;i<buf.size();i++)
		{	RowTag lRow = (RowTag)buf.get(i);
		
			switch(lRow.getStatus())
			{	case ROW_INSERT:
					li_count += _SQLCA.insert(lRow.getOperator());break;
				case ROW_UPDATE:
					li_count += _SQLCA.update(lRow.getOperator());break;
				case ROW_DELETE:
					li_count += _SQLCA.delete(lRow.getOperator());break;
			}
			
			lRow.setStatus(ROW_INITIAL);//对已更新数据还原行状态为初始状态
		}
		
		return li_count;
 }
/**
* 从数据库提取行记录（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
根据构造时提供的数据源信息执行数据库查询操作.
执行本方法后原有行记录将仍存在，除非执行reset()方法.
</pre>
<DT><B>示例：</B><DD>
<pre>

...

Transaction sqlca = DB.getTransaction();	
//以U_ID为关键字
//DSRControl dw = new DataStore("select U_ID,U_NAME,U_EMAIL from FBI_USER","U_ID");
DSRControl dw = new DataStore("select u_id,U_NAME,u_email from fbi_user","U_ID");

dw.setTrans(sqlca);

int rowCount = dw.retrieve();
		
for(int i=1;i<=rowCount;i++)
{	System.out.println("U_NAME:ROWSTATUS[" + i + "] = " + dw.getItem(i,"u_name") + " : " + dw.getItem(i,"u_email"));
}	

sqlca.close();

...

执行结果如下：
-------------------------------------------

U_NAME:ROWSTATUS[1] = 访客 : guest@beyondbit.com
U_NAME:ROWSTATUS[2] = 吴顺江 : wusj@beyondbit.com
U_NAME:ROWSTATUS[3] = 张登火 : zhangdh@beyondbit.com
U_NAME:ROWSTATUS[4] = 吴勇庆 : wyq@beyondbit.com
U_NAME:ROWSTATUS[5] = 管理员 : wusj@beyondbit.com
U_NAME:ROWSTATUS[6] = 吴骏飞 : wujf@beyondbit.com

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @return 从数据库中获得的行记录个数.
* @exception SQLException 数据库错误.
* @see #retrieve(int,int)
*/
	public int retrieve()
		throws SQLException
	{	try
		{	_DeletedRows.clear();//清空删除缓存区中的数据
			_Rows.addAll(_SQLCA.execSQLWithReturn(_SQLSelect).toList(_RowFactory));
			return _Rows.size();
		}
		catch(IllegalAccessException e)
		{	return 0;//will not happened
		}
		catch(InstantiationException e)
		{	return 0;//will not happened
		}
	}
	
/**
* 从数据库提取行记录（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
根据构造时提供的数据源信息执行数据库查询操作.
执行本方法后原有行记录将仍存在，除非执行reset()方法.
采用本方法时应保证SQLSelect中占位符与绑定值列表之间的对应顺序.
</pre>
<DT><B>示例：</B><DD>
<pre>

...

Transaction sqlca = DB.getTransaction();	
//以U_ID为关键字
//DSRControl dw = new DataStore("select U_ID,U_NAME,U_EMAIL from FBI_USER","U_ID");
DataStore dw = new DataStore("select u_id,U_NAME,u_email from fbi_user where u_name like ?","U_ID");
String[] ls_args = {"吴%"};
dw.setTrans(sqlca);

int rowCount = dw.retrieve(Arrays.asList(ls_args));

for(int i=1;i<=rowCount;i++)
{	System.out.println("U_NAME:ROWSTATUS[" + i + "] = " + dw.getItem(i,"u_name") + " : " + dw.getItem(i,"u_email"));
}

sqlca.close();

...

执行结果如下：
-------------------------------------------

U_NAME:ROWSTATUS[1] = 吴顺江 : wusj@beyondbit.com
U_NAME:ROWSTATUS[2] = 吴勇庆 : wyq@beyondbit.com
U_NAME:ROWSTATUS[3] = 吴骏飞 : wujf@beyondbit.com

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
	@param bindVals 与SQLSelect相关的绑定值列表.
* @return 从数据库中获得的行记录个数.
* @exception SQLException 数据库错误.
* @see #retrieve(int,int)
*/
	public int retrieve(List bindVals)
		throws SQLException
	{	try
		{	_DeletedRows.clear();//清空删除缓存区中的数据
			_Rows.addAll(_SQLCA.execSQLWithReturn(_SQLSelect,bindVals).toList(_RowFactory));
			return _Rows.size();
		}
		catch(IllegalAccessException e)
		{	return 0;//will not happened
		}
		catch(InstantiationException e)
		{	return 0;//will not happened
		}
	}
	
/**
* 从数据库提取行记录（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
执行本方法后原有行记录将仍存在，除非执行reset()方法.
</pre>
<DT><B>示例：</B><DD>
<pre>

...

Transaction sqlca = DB.getTransaction();	
//以U_ID为关键字
//DSRControl dw = new DataStore("select U_ID,U_NAME,U_EMAIL from FBI_USER","U_ID");
DSRControl dw = new DataStore("select u_id,U_NAME,u_email from fbi_user","U_ID");

dw.setTrans(sqlca);

int rowCount = dw.retrieve();

for(int i=1;i<=rowCount;i++)
{	System.out.println("U_NAME:ROWSTATUS[" + i + "] = " + dw.getItem(i,"u_name") + " : " + dw.getItem(i,"u_email"));
}

dw.reset();

System.out.println("retrieve(2,3)");

dw.retrieve(2,3);

for(int i=1;i<=dw.rowCount();i++)
{	System.out.println("U_NAME:ROWSTATUS[" + i + "] = " + dw.getItem(i,"u_name") + " : " + dw.getItem(i,"u_email"));
}

sqlca.close();

...

执行结果如下：
-------------------------------------------

U_NAME:ROWSTATUS[1] = 访客 : guest@beyondbit.com
U_NAME:ROWSTATUS[2] = 吴顺江 : wusj@beyondbit.com
U_NAME:ROWSTATUS[3] = 张登火 : zhangdh@beyondbit.com
U_NAME:ROWSTATUS[4] = 吴勇庆 : wyq@beyondbit.com
U_NAME:ROWSTATUS[5] = 管理员 : wusj@beyondbit.com
U_NAME:ROWSTATUS[6] = 吴骏飞 : wujf@beyondbit.com
retrieve(2,3)
U_NAME:ROWSTATUS[1] = 吴顺江 : wusj@beyondbit.com
U_NAME:ROWSTATUS[2] = 张登火 : zhangdh@beyondbit.com
U_NAME:ROWSTATUS[3] = 吴勇庆 : wyq@beyondbit.com

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @param beginIndex 从第几个记录开始，范围为1..记录总数.
* @param rows >0 向后取几条记录，<0 向前取几条记录.
* @return 实际取得的行记录个数.
* @exception SQLException 数据库错误.
* @see #retrieve()
*/
	public int retrieve(int beginIndex,int rows)
		throws SQLException
	{	try
		{	_DeletedRows.clear();//清空删除缓存区中的数据
			if(_SQLCA.getDBMS()==DBMS.ORACLE)
				_Rows.addAll(_SQLCA.execSQLWithReturn(DBMS.toRangeSQL(DBMS.ORACLE,_SQLSelect,beginIndex,rows)).toList(_RowFactory));
			else
				_Rows.addAll(_SQLCA.execSQLWithReturn(_SQLSelect,beginIndex,rows).toList(_RowFactory));

			return _Rows.size();
		}
		catch(IllegalAccessException e)
		{	return 0;//will not happened
		}
		catch(InstantiationException e)
		{	return 0;//will not happened
		}
	}
	
/**
* 从数据库提取行记录（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
采用本方法时应保证SQLSelect中占位符与绑定值列表之间的对应顺序.
</pre>
<DT><B>示例：</B><DD>
<pre>

...

Transaction sqlca = DB.getTransaction();	
//以U_ID为关键字
//DSRControl dw= new DataStore("select U_ID,U_NAME,U_EMAIL from FBI_USER","U_ID");
DataStore dw= new DataStore("select u_id,U_NAME,u_email from fbi_user where u_name like ?","U_ID");
String[] ls_args = {"吴%"};
dw.setTrans(sqlca);

dw.retrieve(Arrays.asList(ls_args));

for(int i=1;i<=dw.rowCount();i++)
{	System.out.println("U_NAME:ROWSTATUS[" + i + "] = " + dw.getItem(i,"u_name") + " : " + dw.getItem(i,"u_email"));
}			

dw.reset();

System.out.println("retrieve(List,2,2)");

dw.retrieve(Arrays.asList(ls_args),2,2);

for(int i=1;i<=dw.rowCount();i++)
{	System.out.println("U_NAME:ROWSTATUS[" + i + "] = " + dw.getItem(i,"u_name") + " : " + dw.getItem(i,"u_email"));
}			

sqlca.close();

...

执行结果如下：
-------------------------------------------

U_NAME:ROWSTATUS[1] = 吴顺江 : wusj@beyondbit.com
U_NAME:ROWSTATUS[2] = 吴勇庆 : wyq@beyondbit.com
U_NAME:ROWSTATUS[3] = 吴骏飞 : wujf@beyondbit.com
retrieve(List,2,2)
U_NAME:ROWSTATUS[1] = 吴勇庆 : wyq@beyondbit.com
U_NAME:ROWSTATUS[2] = 吴骏飞 : wujf@beyondbit.com

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
	@param bindVals 与SQLSelect相关的绑定值列表.
* @param beginIndex 从第几个记录开始，范围为1..记录总数.
* @param rows >0 向后取几条记录，<0 向前取几条记录.
* @return 实际取得的行记录个数.
* @exception SQLException 数据库错误.
* @see #retrieve()
*/
	public int retrieve(List bindVals,int beginIndex,int rows)
		throws SQLException
	{	try
		{	_DeletedRows.clear();//清空删除缓存区中的数据
			if(_SQLCA.getDBMS()==DBMS.ORACLE)
				_Rows.addAll(_SQLCA.execSQLWithReturn(DBMS.toRangeSQL(DBMS.ORACLE,_SQLSelect,beginIndex,rows),bindVals).toList(_RowFactory));
			else
				_Rows.addAll(_SQLCA.execSQLWithReturn(_SQLSelect,bindVals,beginIndex,rows).toList(_RowFactory));
			return _Rows.size();
		}
		catch(IllegalAccessException e)
		{	return 0;//will not happened
		}
		catch(InstantiationException e)
		{	return 0;//will not happened
		}
	}
	
/**
* 清除当前所有的行记录（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
本方法不影响数据库操作.
</pre>
<DT><B>示例：</B><DD>
<pre>

...

Transaction sqlca = DB.getTransaction();	
//以U_ID为关键字
//DSRControl dw = new DataStore("select U_ID,U_NAME,U_EMAIL from FBI_USER","U_ID");
DSRControl dw = new DataStore("select u_id,U_NAME,u_email from fbi_user","U_ID");

dw.setTrans(sqlca);

int rowCount = dw.retrieve();
		
for(int i=1;i<=rowCount;i++)
{	System.out.println("U_NAME:ROWSTATUS[" + i + "] = " + dw.getItem(i,"u_name") + " : " + dw.getItem(i,"u_email"));
}	

dw.reset();
System.out.println("after reset");

for(int i=1;i<=dw.rowCount();i++)
{	System.out.println("U_NAME:ROWSTATUS[" + i + "] = " + dw.getItem(i,"u_name") + " : " + dw.getItem(i,"u_email"));
}	

sqlca.close();

...

执行结果如下：
-------------------------------------------

U_NAME:ROWSTATUS[1] = 访客 : guest@beyondbit.com
U_NAME:ROWSTATUS[2] = 吴顺江 : wusj@beyondbit.com
U_NAME:ROWSTATUS[3] = 张登火 : zhangdh@beyondbit.com
U_NAME:ROWSTATUS[4] = 吴勇庆 : wyq@beyondbit.com
U_NAME:ROWSTATUS[5] = 管理员 : wusj@beyondbit.com
U_NAME:ROWSTATUS[6] = 吴骏飞 : wujf@beyondbit.com
after reset

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @return 清除后的行个数，一般为0.
*/
	public int reset()
	{ _DeletedRows.clear();
		_Rows.clear();	
		return _Rows.size();
	}
	
/**
* 当前所有的行记录个数（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>

...

Transaction sqlca = DB.getTransaction();	
//以U_ID为关键字
//DSRControl dw = new DataStore("select U_ID,U_NAME,U_EMAIL from FBI_USER","U_ID");
DSRControl dw = new DataStore("select u_id,U_NAME,u_email from fbi_user","U_ID");

dw.setTrans(sqlca);

dw.retrieve();
		
for(int i=1;i<=dw.rowCount;i++)
{	System.out.println("U_NAME:ROWSTATUS[" + i + "] = " + dw.getItem(i,"u_name") + " : " + dw.getItem(i,"u_email"));
}	

sqlca.close();

...

执行结果如下：
-------------------------------------------

U_NAME:ROWSTATUS[1] = 访客 : guest@beyondbit.com
U_NAME:ROWSTATUS[2] = 吴顺江 : wusj@beyondbit.com
U_NAME:ROWSTATUS[3] = 张登火 : zhangdh@beyondbit.com
U_NAME:ROWSTATUS[4] = 吴勇庆 : wyq@beyondbit.com
U_NAME:ROWSTATUS[5] = 管理员 : wusj@beyondbit.com
U_NAME:ROWSTATUS[6] = 吴骏飞 : wujf@beyondbit.com

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @return 行记录个数.
*/
	public int rowCount()
	{	return _Rows.size();
	}
	

	
/**
* 删除缓存区中的记录个数（创建于 2003.05.14）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>

...

Transaction sqlca = DB.getTransaction();	
//以U_ID为关键字
//DSRControl dw = new DataStore("select U_ID,U_NAME,U_EMAIL from FBI_USER","U_ID");
DSRControl dw = new DataStore("select u_id,u_name,u_email from fbi_user","u_id");

dw.setTrans(sqlca);

int rowCount = dw.retrieve();

System.out.println("after retrieve = " + dw.deletedCount());

for(int i=1;i<=rowCount;i++)
{	System.out.println("U_NAME:U_EMAIL[" + i + "] = " + dw.getItem(i,"u_name") + " : " + dw.getItem(i,"u_email"));
}

dw.deleteRow(6);

System.out.println("after delete = " + dw.deletedCount());

...

执行结果如下：
-------------------------------------------

after retrieve = 0
U_NAME:U_EMAIL[1] = 访客 : guest@beyondbit.com
U_NAME:U_EMAIL[2] = 吴顺江 : wusj@beyondbit.com
U_NAME:U_EMAIL[3] = 张登火 : zhangdh@beyondbit.com
U_NAME:U_EMAIL[4] = 吴勇庆 : wyq@beyondbit.com
U_NAME:U_EMAIL[5] = 管理员 : wusj@beyondbit.com
U_NAME:U_EMAIL[6] = 吴骏飞 : wujf@beyondbit.com
after delete = 1

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.05.14.
</pre>
</DL>
* @return 删除缓存区中的记录个数.
* @see #deleteRow(int)
	@see #rowCount()
*/
	public int deletedCount()
	{	return _DeletedRows.size();
	}
	
/**
* 返回列个数（创建于 2003.03.18）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>

...

DSRControl dw = new DataStore("select u_id,u_name,u_email from fbi_user","u_id");
			
System.out.println("columnCount = " + dw.colCount());

...

执行结果如下：
-------------------------------------------

columnCount = 3
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.18.
</pre>
</DL>
* @return 列个数.
*/
	public int colCount()
	{	return _Operator.fieldCount();
	}
/**
* 取列的名称（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>

...

DSRControl dw = new DataStore("select u_id,U_NAME,u_email from fbi_user","U_ID");
System.out.println("ID2 's name = " + dw.getColName(2));

...

执行结果如下：
-------------------------------------------

ID2 's name = U_NAME

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @param col 列标识.
* @return 列名.
	@Exception NoSuchElementException 不存在的列.
*/
	public String getColName(int col)
	{	return _Operator.getFieldName(col);
	}
/**
* 取列的标识（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>

...

DSRControl dw = new DataStore("select u_id,u_name,u_email from fbi_user","U_ID");
System.out.println("u_id 's ID = " + dw.getColID("u_id"));

...

执行结果如下：
-------------------------------------------

u_id 's ID = 1

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @param col 列名.
* @return 列标识.
	@Exception NoSuchElementException 不存在的列.
*/
	public int getColID(String col)
	{	return _Operator.getFieldID(col);
	}
	
/**
* 取行状态（创建于 2003.03.18）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>

...

Transaction sqlca = DB.getTransaction();	
//以U_ID为关键字
//DSRControl dw = new DataStore("select U_ID,U_NAME,U_EMAIL from FBI_USER","U_ID");
DSRControl dw = new DataStore("select u_id,U_NAME,u_email from fbi_user","U_ID");
dw.setTrans(sqlca);

int rowCount = dw.retrieve();
		
for(int i=1;i<=rowCount;i++)
{	System.out.println("U_NAME:ROWSTATUS[" + i + "] = " + dw.getItem(i,"u_name") + " : " + dw.getRowStatus(i));
}	

dw.setItem(6,"u_name","方建青");
System.out.println("after setItem");

for(int i=1;i<=rowCount;i++)
{	System.out.println("U_NAME:ROWSTATUS[" + i + "] = " + dw.getItem(i,"u_name") + " : " + dw.getRowStatus(i));
}	

sqlca.close();

...

执行结果如下：
-------------------------------------------

U_NAME:ROWSTATUS[1] = 访客 : 0
U_NAME:ROWSTATUS[2] = 吴顺江 : 0
U_NAME:ROWSTATUS[3] = 张登火 : 0
U_NAME:ROWSTATUS[4] = 吴勇庆 : 0
U_NAME:ROWSTATUS[5] = 管理员 : 0
U_NAME:ROWSTATUS[6] = 吴骏飞 : 0
after setItem
U_NAME:ROWSTATUS[1] = 访客 : 0
U_NAME:ROWSTATUS[2] = 吴顺江 : 0
U_NAME:ROWSTATUS[3] = 张登火 : 0
U_NAME:ROWSTATUS[4] = 吴勇庆 : 0
U_NAME:ROWSTATUS[5] = 管理员 : 0
U_NAME:ROWSTATUS[6] = 方建青 : 2

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.18.
</pre>
</DL>
* @param row 行号.
* @return 行状态.
* @see #ROW_DELETE
	@see #ROW_INSERT
	@see #ROW_UPDATE
	@see #ROW_INITIAL
*/
	public int getRowStatus(int row)
	{	RowTag lRow = (RowTag)_Rows.get(rowIndex(row));
		return lRow.getStatus();
	}	
/**
* 设置行状态（创建于 2003.03.18）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
行状态决定了该行数据生成的SQL语句类型：
ROW_INSERT 对应于INSERT
ROW_UPDATE 对应于UPDATE
ROW_DELETE 对应于DELETE
ROW_INITIAL 不生成SQL语句

setRowStatus(row,ROW_DELETE)与deleteRow(row)虽然都起到了对记录的删除作用，但前者仍存在于的行记录集中，
而后者则不再出现在行记录集中
</pre>
<DT><B>示例：</B><DD>
<pre>

...

Transaction sqlca = DB.getTransaction();	
//以U_ID为关键字
//DSRControl dw= new DataStore("select U_ID,U_NAME,U_EMAIL from FBI_USER","U_ID");
DataStore dw= new DataStore("select u_id,U_NAME,u_email from fbi_user where u_name like ?","U_ID");
String[] ls_args = {"吴%"};
dw.setTrans(sqlca);

dw.retrieve(Arrays.asList(ls_args));

for(int i=1;i<=dw.rowCount();i++)
{	System.out.println("U_NAME:ROWSTATUS[" + i + "] = " + dw.getItem(i,"u_name") + " : " + dw.getRowStatus(i));
}			

dw.setRowStatus(3,dw.ROW_UPDATE);

System.out.println("after setItem");

for(int i=1;i<=dw.rowCount();i++)
{	System.out.println("U_NAME:ROWSTATUS[" + i + "] = " + dw.getItem(i,"u_name") + " : " + dw.getRowStatus(i));
}			

sqlca.close();

...

执行结果如下：
-------------------------------------------

U_NAME:ROWSTATUS[1] = 吴顺江 : 0
U_NAME:ROWSTATUS[2] = 吴勇庆 : 0
U_NAME:ROWSTATUS[3] = 吴骏飞 : 0
after setItem
U_NAME:ROWSTATUS[1] = 吴顺江 : 0
U_NAME:ROWSTATUS[2] = 吴勇庆 : 0
U_NAME:ROWSTATUS[3] = 吴骏飞 : 2

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.18.
</pre>
</DL>
* @param row 行号.
* @param status 行状态.
* @see #ROW_DELETE
	@see #ROW_INSERT
	@see #ROW_UPDATE
	@see #ROW_INITIAL
*/
	public void setRowStatus(int row,int status)
	{	RowTag lRow = (RowTag)_Rows.get(rowIndex(row));
		lRow.setStatus(status);
	}
	
	/***************************测试用例*************
	static Connector DB = new Connector(new File("E:\\吴勇庆\\01_创作苑\\01_重用构件\\Java\\oracle.ini"));
	public static void testRetrieve()
	{	Transaction sqlca = null;
		try
		{	sqlca = DB.getTransaction();	
			sqlca.disableBind(true);
			DSRControl dw = new DataStore("select U_ID,U_NAME,U_EMAIL from FBI_USER");
			
			dw.setTrans(sqlca);
			
			dw.retrieve();
			
			System.out.println("sql:\n" + sqlca.getSQLSyntax());
			
			for(int i=1;i<=dw.rowCount();i++)
			{	System.out.println("U_NAME:U_EMAIL[" + i + "] = " + dw.getItem(i,"U_NAME") + " : " + dw.getItem(i,"U_EMAIL"));
			}
			
			sqlca.close();
		}
		catch(SQLException e)
		{	System.out.println(e + "\n" + sqlca.getSQLSyntax());
		}
	}
	public static void testInsert()
	{	Transaction sqlca = null;
		try
		{	sqlca = DB.getTransaction();	
			sqlca.disableBind(true);
			DSRControl dw = new DataStore("select U_ID,U_NAME,U_EMAIL from FBI_USER","U_ID");
			
			dw.setTrans(sqlca);
			
			dw.insertRow(1);
			
			dw.setItem(1,"U_ID","fjq");
			dw.setItem(1,"U_NAME","方建青");
			dw.setItem(1,"U_EMAIL","fjq@wondersgroup.com");
			
			if(dw.update() > 0)
			{	sqlca.commit();				
				System.out.println("insert success!");
			}
			else
			{	sqlca.rollback();
				System.out.println("insert failed!");
			}
			
			System.out.println("sql:\n" + sqlca.getSQLSyntax());
			
			sqlca.close();
		}
		catch(SQLException e)
		{	System.out.println(e + "\n" + sqlca.getSQLSyntax());
		}
	}
	public static void testUpdate()
	{	Transaction sqlca = null;
		try
		{	sqlca = DB.getTransaction();
			sqlca.disableBind(true);
			DSRControl dw = new DataStore("select U_ID,U_NAME,U_EMAIL from FBI_USER","U_ID");
			
			dw.setTrans(sqlca);
			
			dw.insertRow(1);
			
			dw.setItem(1,"U_ID","fjq");
			dw.setItem(1,"U_NAME","方建青");
			dw.setItem(1,"U_EMAIL","fangjq@wondersgroup.com");
			
			dw.setRowStatus(1,dw.ROW_UPDATE);
			
			if(dw.update() > 0)
			{	sqlca.commit();				
				System.out.println("update success!");
			}
			else
			{	sqlca.rollback();
				System.out.println("update failed!");
			}
			
			System.out.println("sql:\n" + sqlca.getSQLSyntax());
			
			sqlca.close();
		}
		catch(SQLException e)
		{	System.out.println(e + "\n" + sqlca.getSQLSyntax());
		}
	}
	
	public static void testDelete()
	{	Transaction sqlca = null;
		try
		{	sqlca = DB.getTransaction();	
			sqlca.disableBind(true);
			DSRControl dw = new DataStore("select U_ID,U_NAME,U_EMAIL from FBI_USER","U_ID");
			
			dw.setTrans(sqlca);
			
			dw.insertRow(1);
			
			dw.setItem(1,"U_ID","fjq");
			dw.setItem(1,"U_NAME","方建青");
			dw.setItem(1,"U_EMAIL","fangjq@wondersgroup.com");
			
			dw.setRowStatus(1,dw.ROW_DELETE);
			
			if(dw.update() > 0)
			{	sqlca.commit();				
				System.out.println("delete success!");
			}
			else
			{	sqlca.rollback();
				System.out.println("delete failed!");
			}
			
			System.out.println("sql:\n" + sqlca.getSQLSyntax());
			
			sqlca.close();
		}
		catch(SQLException e)
		{	System.out.println(e + "\n" + sqlca.getSQLSyntax());
		}
	}
	****************************************************************/
}

