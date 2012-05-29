package trionesII.sql;

import java.util.ArrayList;
import java.lang.reflect.Field;
import java.util.List;
import java.sql.*;

import trionesII.bas.Factory;

/**
* <img src="important.gif" width="35" height="25" border="0">记录集（创建于 2003.03.12）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
RecordSet用于描述二维数据视图，结构上分为行和列，每行由多个相同含义的列的值构成。
RecordSet与java.sql.ResultSet有许多相似之处，不同之处在于，RecordSet不受限于数据库资源。
即当数据库资源释放后，RecordSet仍能提供数据结果。
另外，RecordSet可根据开发者所实现的Factory接口，将数据转换成开发者所期望的List和Object。
因此，RecordSet可被认为是中间数据层。
</pre>
<DT><B>使用说明：</B><DD>
<pre>
详见各方法使用说明。
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
public interface RecordSet
{	
	
/**
* 以数组形式取列名（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
列名数组中的索引号与列标识是完全不同的含义，两者的值并不一致.
</pre>
<DT><B>示例：</B><DD>
<pre>

...

FBI_USER u = new FBI_USER();
			
u.u_name = "吴勇庆";

ClassOperator com1 = new ClassOperator(u);

com1.hideFields();
OperatorMethod.showFields(com1,"u_email,u_name,u_id");	

com1.hideCndFields();				
com1.showCndField("u_name");

Transaction sqlca = DB.getTransaction();	

RecordSet lSet = sqlca.select(com1);

sqlca.close();

String[] ls_colNames = lSet.getColNames();

for(int i=0;i&lt;ls_colNames.length;i++)
	System.out.println(ls_colNames[i]);

...

执行结果如下：
-------------------------------------------

U_ID
U_NAME
U_EMAIL

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @return 列名数组.
*/
	public String[] getColNames();
/**
* 取列名（创建于 2003.04.09）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
列标识的取值范围: 1..colCount()
</pre>
<DT><B>示例1：</B><DD>
<pre>

...

FBI_USER u = new FBI_USER();
			
u.u_name = "吴勇庆";

ClassOperator com1 = new ClassOperator(u);

com1.hideFields();
OperatorMethod.showFields(com1,"u_email,u_name,u_id");	

com1.hideCndFields();				
com1.showCndField("u_name");

Transaction sqlca = DB.getTransaction();	

RecordSet lSet = sqlca.select(com1);

sqlca.close();

for(int i=1;i&lt;=lSet.colCount();i++)
	System.out.println("column[" + i + "] = " + lSet.getColName(i));

System.out.println("复合类型：" + com1.isMultiple());

...

执行结果如下：
-----------------------------------------------------

column[1] = U_ID
column[2] = U_NAME
column[3] = U_EMAIL
复合类型：false
</pre>
<DT><B>示例2：</B><DD>
<pre>

...

FBI_USER u = new FBI_USER();
			
u.u_name = "吴勇庆";

FBI_QUESTION q = new FBI_QUESTION();

ClassOperator com1 = new ClassOperator(u);
ClassOperator com2 = new ClassOperator(q);

com1.hideFields();
OperatorMethod.showFields(com1,"u_email,u_name,u_id");//只显示u_email,u_name,u_id

com1.hideCndFields();				
com1.showCndField("u_name");

com2.hideFields();
com2.showField("q_title");//只显示q_title

com2.hideCndFields();

ClassOperator com3 = ClassOperator.join(com1,com2,"u_id","u_id");

Transaction sqlca = DB.getTransaction();	

RecordSet lSet = sqlca.select(com3);

sqlca.close();

for(int i=1;i&lt;=lSet.colCount();i++)
	System.out.println("column[" + i + "] = " + lSet.getColName(i));
	
System.out.println("复合类型：" + com3.isMultiple());

...

执行结果如下：
-----------------------------------------------------

column[1] = FBI_USER_U_ID
column[2] = FBI_USER_U_NAME
column[3] = FBI_USER_U_EMAIL
column[4] = FBI_QUESTION_Q_TITLE
复合类型：true
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.04.09.
</pre>
</DL>
* @param colID 列标识.
* @return 列名.
*/
	public String getColName(int colID);
/**
* 总行数（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>

...

	FBI_USER u = new FBI_USER();
			
	ClassOperator com1 = new ClassOperator(u);

	com1.hideFields();
	OperatorMethod.showFields(com1,"u_email,u_name,u_id");	
	
	com1.hideCndFields();				
	
	Transaction sqlca = DB.getTransaction();	
	
	RecordSet lSet = sqlca.select(com1);
	
	sqlca.close();
	
	System.out.print("\n labels:");
	for(int j=1;j&lt;=lSet.colCount();j++)
			System.out.print("\t\t\t " + lSet.getColName(j));
	System.out.print("\n ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			
	for(int i=1;i&lt;=lSet.rowCount();i++)
	{	System.out.print("\n row[" + i + "]:");
		for(int j=1;j&lt;=lSet.colCount();j++)
			System.out.print("\t\t\t " + lSet.getItem(i,j));
	}

...

执行结果如下：
-------------------------------------------

 labels:			 U_ID			 U_NAME			 U_EMAIL
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 row[1]:			 guest			 访客			 guest@beyondbit.com
 row[2]:			 wusj			 吴顺江			 wusj@beyondbit.com
 row[3]:			 zhangdh			 张登火			 zhangdh@beyondbit.com
 row[4]:			 wyq			 吴勇庆			 wyq@beyondbit.com
 row[5]:			 admin			 管理员			 wusj@beyondbit.com
 row[6]:			 Terry			 吴骏飞			 wujf@beyondbit.com

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @return 总行数.
*/
	public int rowCount();
	

	
/**
* 列个数（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>

...

FBI_USER u = new FBI_USER();
			
u.u_name = "吴勇庆";

ClassOperator com1 = new ClassOperator(u);

com1.hideFields();
OperatorMethod.showFields(com1,"u_email,u_name,u_id");	

com1.hideCndFields();				
com1.showCndField("u_name");

Transaction sqlca = DB.getTransaction();	

RecordSet lSet = sqlca.select(com1);

sqlca.close();

System.out.println("column number is " + lSet.colCount());

...

执行结果如下：
-------------------------------------------

column number is 3

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @return 列个数.
*/
	public int colCount();
	
/**
* 取指定行的值数组（创建于 2003.03.27）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>

...

FBI_USER u = new FBI_USER();
			
u.u_name = "吴勇庆";

FBI_QUESTION q = new FBI_QUESTION();

ClassOperator com1 = new ClassOperator(u);
ClassOperator com2 = new ClassOperator(q);

com1.hideFields();
OperatorMethod.showFields(com1,"u_email,u_name,u_id");	

com1.hideCndFields();				
com1.showCndField("u_name");

com2.hideFields();
com2.showField("q_title");

com2.hideCndFields();

ClassOperator com3 = ClassOperator.join(com1,com2,"u_id","u_id");

Transaction sqlca = DB.getTransaction();	

RecordSet lSet = sqlca.select(com3);

sqlca.close();

String[] ls_colNames = lSet.getColNames();
for(int j=0;j&lt;ls_colNames.length;j++)
		System.out.print("\t " + ls_colNames[j]);

System.out.print("\n ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
for(int i=1;i&lt;=lSet.rowCount();i++)
{	System.out.print("\n row[" + i + "]:");
	Object[] lo_colVals = lSet.getItemsByRow(i);
	for(int j=0;j&lt;lo_colVals.length;j++)
		System.out.print("\t\t " + lo_colVals[j]);
}

...

执行结果如下：
-------------------------------------------

	 FBI_USER_U_ID	 FBI_USER_U_NAME	 FBI_USER_U_EMAIL	 FBI_QUESTION_Q_TITLE
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 row[1]:		 wyq		 吴勇庆		 wyq@beyondbit.com		 为什么伊拉克不拥有大规模杀伤性武器？
 row[2]:		 wyq		 吴勇庆		 wyq@beyondbit.com		 为什么美国要侵略伊拉克？

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.27.
</pre>
</DL>
* @param row 行编号.
* @return 行的值数组，按默认列顺序.
*/
	public Object[] getItemsByRow(int row);
/**
* 取记录集指定行、列单元的值（创建于 2003.03.24）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>

...

	FBI_USER u = new FBI_USER();
			
	ClassOperator com1 = new ClassOperator(u);

	com1.hideFields();
	OperatorMethod.showFields(com1,"u_email,u_name,u_id");	
	
	com1.hideCndFields();				
	
	Transaction sqlca = DB.getTransaction();	
	
	RecordSet lSet = sqlca.select(com1);
	
	sqlca.close();
	
	System.out.print("\n labels:");
	for(int j=1;j&lt;=lSet.colCount();j++)
			System.out.print("\t\t\t " + lSet.getColName(j));
	System.out.print("\n ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			
	for(int i=1;i&lt;=lSet.rowCount();i++)
	{	System.out.print("\n row[" + i + "]:");
		for(int j=1;j&lt;=lSet.colCount();j++)
			System.out.print("\t\t\t " + lSet.getItem(i,j));
	}

...

执行结果如下：
-------------------------------------------

 labels:			 U_ID			 U_NAME			 U_EMAIL
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 row[1]:			 guest			 访客			 guest@beyondbit.com
 row[2]:			 wusj			 吴顺江			 wusj@beyondbit.com
 row[3]:			 zhangdh			 张登火			 zhangdh@beyondbit.com
 row[4]:			 wyq			 吴勇庆			 wyq@beyondbit.com
 row[5]:			 admin			 管理员			 wusj@beyondbit.com
 row[6]:			 Terry			 吴骏飞			 wujf@beyondbit.com

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.24.
</pre>
</DL>
* @param row 行编号，取值范围1..rowCount().
* @param colId 列标识,取值范围1..colCount().
* @return 单元值.
* @see #getItem(int,String)
	@exception IndexOutOfBoundsException 行或列越界.
*/
	public Object getItem(int row,int colId);
	
/**
* 取记录集指定行、列单元的值（创建于 2003.03.24）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
列名对大小写不敏感.
</pre>
<DT><B>示例1：</B><DD>
<pre>

...

FBI_USER u = new FBI_USER();
			
ClassOperator com1 = new ClassOperator(u);

com1.hideFields();
OperatorMethod.showFields(com1,"u_email,u_name,u_id");	

com1.hideCndFields();				

Transaction sqlca = DB.getTransaction();	

RecordSet lSet = sqlca.select(com1);

sqlca.close();

System.out.print("\n labels:\t\t\t u_email \t\t\t u_name \t\t\t u_id");
System.out.print("\n ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
for(int i=1;i&lt;=lSet.rowCount();i++)
{	System.out.print("\n row[" + i + "]:");
	System.out.print("\t\t\t " + lSet.getItem(i,"u_email") + 
									" \t\t\t " + lSet.getItem(i,"u_name") + 
									" \t\t\t " + lSet.getItem(i,"u_id"));
}

...

执行结果如下：
-------------------------------------------

labels:			 u_email 			 u_name 			 u_id
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 row[1]:			 guest@beyondbit.com 			 访客 			 guest
 row[2]:			 wusj@beyondbit.com 			 吴顺江 			 wusj
 row[3]:			 zhangdh@beyondbit.com 			 张登火 			 zhangdh
 row[4]:			 wyq@beyondbit.com 			 吴勇庆 			 wyq
 row[5]:			 wusj@beyondbit.com 			 管理员 			 admin
 row[6]:			 wujf@beyondbit.com 			 吴骏飞 			 Terry

</pre>
<DT><B>示例2：</B><DD>
<pre>

...


FBI_USER u = new FBI_USER();

u.u_name = "吴勇庆";

FBI_QUESTION q = new FBI_QUESTION();

ClassOperator com1 = new ClassOperator(u);
ClassOperator com2 = new ClassOperator(q);

com1.hideFields();
OperatorMethod.showFields(com1,"u_email,u_name,u_id");	

com1.hideCndFields();				
com1.showCndField("u_name");

com2.hideFields();
com2.showField("q_title");

com2.hideCndFields();

ClassOperator com3 = ClassOperator.join(com1,com2,"u_id","u_id");

Transaction sqlca = DB.getTransaction();	

RecordSet lSet = sqlca.select(com3);

sqlca.close();
		
System.out.print("\n labels:\t\t u_name \t\t u_email \t\t\t\t\t u_id \t\t\t q_title");
System.out.print("\n ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
for(int i=1;i&lt;=lSet.rowCount();i++)
{	System.out.print("\n row[" + i + "]:");
	System.out.print("\t\t " + lSet.getItem(i,"fbi_user_u_name") + 
									" \t\t " + lSet.getItem(i,"FBI_USER_u_email") + 
									" \t\t " + lSet.getItem(i,"fbi_user_U_ID") +
									" \t\t " + lSet.getItem(i,"FBI_QUESTION_Q_TITLE"));
}

...

执行结果如下：
-------------------------------------------

 labels:		 u_name 		 u_email 					 u_id 			 q_title
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 row[1]:		 吴勇庆 		 wyq@beyondbit.com 		 wyq 		 为什么伊拉克不拥有大规模杀伤性武器？
 row[2]:		 吴勇庆 		 wyq@beyondbit.com 		 wyq 		 为什么美国要侵略伊拉克？

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.24.
</pre>
</DL>
* @param row 行编号，取值范围1..rowCount().
* @param colName 列名称.
* @return 单元值.
* @see #getItem(int,int)
	@exception IndexOutOfBoundsException 行或列越界.
*/
	public Object getItem(int row,String colName);
	

	
/**
* 根据列名取列标识（创建于 2003.03.24）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
列标识从1..colCount()
列名对大小写不敏感.
</pre>
<DT><B>示例：</B><DD>
<pre>

...

FBI_USER u = new FBI_USER();
			
u.u_name = "吴勇庆";

ClassOperator com1 = new ClassOperator(u);

com1.hideFields();
OperatorMethod.showFields(com1,"u_email,u_name,u_id");	

com1.hideCndFields();				
com1.showCndField("u_name");

Transaction sqlca = DB.getTransaction();	

RecordSet lSet = sqlca.select(com1);

sqlca.close();

System.out.println("the column ID of u_email is " + lSet.getColID("u_email"));

...

执行结果如下：
-------------------------------------------

the column ID of u_email is 3

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.24.
</pre>
</DL>
* @param colName 列名称.
* @return 列标识，0代表列标识不存在.
*/
	public int getColID(String colName);
	

/**
* 以列表形式导出记录集数据（创建于 2003.03.27）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
列表中的对象根据基因工厂提供的构造方式生成.
</pre>
<DT><B>示例1：</B><DD>
<pre>

...

FBI_USER u = new FBI_USER();
			
ClassOperator com1 = new ClassOperator(u);

com1.hideFields();
OperatorMethod.showFields(com1,"u_email,u_name,u_id");	

com1.hideCndFields();				

Transaction sqlca = DB.getTransaction();	

RecordSet lSet = sqlca.select(com1);

sqlca.close();

System.out.println("复合类型：" + com1.isMultiple());

List lList = lSet.toList(com1.getFactory());

for(int i=0;i&lt;lList.size();i++)
{	FBI_USER user = (FBI_USER)(lList.get(i));
	System.out.print(i + " : " + user.u_name);
	System.out.println(" 's email address is '" + user.u_email + "'");
}

...

执行结果如下：
-------------------------------------------

复合类型：false
0 : 访客 's email address is 'guest@beyondbit.com'
1 : 吴顺江 's email address is 'wusj@beyondbit.com'
2 : 张登火 's email address is 'zhangdh@beyondbit.com'
3 : 吴勇庆 's email address is 'wyq@beyondbit.com'
4 : 管理员 's email address is 'wusj@beyondbit.com'
5 : 吴骏飞 's email address is 'wujf@beyondbit.com'

</pre>
<DT><B>示例2：</B><DD>
<pre>

...

FBI_USER u = new FBI_USER();
			
u.u_name = "吴勇庆";

FBI_QUESTION q = new FBI_QUESTION();

ClassOperator com1 = new ClassOperator(u);
ClassOperator com2 = new ClassOperator(q);

com1.hideFields();
OperatorMethod.showFields(com1,"u_email,u_name,u_id");	

com1.hideCndFields();				
com1.showCndField("u_name");

com2.hideFields();
com2.showField("q_title");

com2.hideCndFields();

ClassOperator com3 = ClassOperator.join(com1,com2,"u_id","u_id");

Transaction sqlca = DB.getTransaction();	

RecordSet lSet = sqlca.select(com3);

sqlca.close();

System.out.println("复合类型：" + com3.isMultiple());

List lList = lSet.toList(com3.getFactory());

for(int i=0;i&lt;lList.size();i++)
{	Hashtable h = (Hashtable)(lList.get(i));
	System.out.print(i + " : " + ((FBI_USER)h.get(FBI_USER.class)).u_name);
	System.out.println(" 's question is '" + ((FBI_QUESTION)h.get(FBI_QUESTION.class)).q_title + "'");
}

...

执行结果如下：
-------------------------------------------

复合类型：true
0 : 吴勇庆 's question is '为什么伊拉克不拥有大规模杀伤性武器？'
1 : 吴勇庆 's question is '为什么美国要侵略伊拉克？'

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.27.
</pre>
</DL>
* @param f 基因工厂.
* @return 记录列表.
* @exception IllegalAccessException 对应类的属性值必须初始化.
* @exception InstantiationException 对应类不存在，或者是一个抽象类，或者构造函数全都有参数.
*/
	public List toList(Factory f) throws IllegalAccessException,InstantiationException;

/**
* 将指定行数据按设定的基因工厂创建基因对象（创建于 2003.03.27）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例1：</B><DD>
<pre>

...

FBI_USER u = new FBI_USER();
			
ClassOperator com1 = new ClassOperator(u);

com1.hideFields();
OperatorMethod.showFields(com1,"u_email,u_name,u_id");	

com1.hideCndFields();				

Transaction sqlca = DB.getTransaction();	

RecordSet lSet = sqlca.select(com1);

sqlca.close();

System.out.println("复合类型：" + com1.isMultiple());

for(int i=1;i&lt;=lSet.rowCount();i++)
{	FBI_USER user = (FBI_USER)(lSet.toObject(i,com1.getFactory()));
	System.out.print(i + " : " + user.u_name);
	System.out.println(" 's email address is '" + user.u_email + "'");
}

...

执行结果如下：
-------------------------------------------

复合类型：false
1 : 访客 's email address is 'guest@beyondbit.com'
2 : 吴顺江 's email address is 'wusj@beyondbit.com'
3 : 张登火 's email address is 'zhangdh@beyondbit.com'
4 : 吴勇庆 's email address is 'wyq@beyondbit.com'
5 : 管理员 's email address is 'wusj@beyondbit.com'
6 : 吴骏飞 's email address is 'wujf@beyondbit.com'

</pre>
<DT><B>示例2：</B><DD>
<pre>

...

FBI_USER u = new FBI_USER();
						
u.u_name = "吴勇庆";

FBI_QUESTION q = new FBI_QUESTION();

ClassOperator com1 = new ClassOperator(u);
ClassOperator com2 = new ClassOperator(q);

com1.hideFields();
OperatorMethod.showFields(com1,"u_email,u_name,u_id");	

com1.hideCndFields();				
com1.showCndField("u_name");

com2.hideFields();
com2.showField("q_title");

com2.hideCndFields();

ClassOperator com3 = ClassOperator.join(com1,com2,"u_id","u_id");

Transaction sqlca = DB.getTransaction();	

RecordSet lSet = sqlca.select(com3);

sqlca.close();

System.out.println("复合类型：" + com3.isMultiple());

for(int i=1;i&lt;=lSet.rowCount();i++)
{	Hashtable h = (Hashtable)(lSet.toObject(i,com3.getFactory()));
	System.out.print(i + " : " + ((FBI_USER)h.get(FBI_USER.class)).u_name);
	System.out.println(" 's question is '" + ((FBI_QUESTION)h.get(FBI_QUESTION.class)).q_title + "'");
}

...

执行结果如下：
-------------------------------------------

复合类型：true
1 : 吴勇庆 's question is '为什么伊拉克不拥有大规模杀伤性武器？'
2 : 吴勇庆 's question is '为什么美国要侵略伊拉克？'

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.27.
</pre>
</DL>
	@param row 行号.
	@param f 基因工厂.
* @return 基因对象.
* @exception IllegalAccessException 对应类的属性值必须初始化.
* @exception InstantiationException 对应类不存在，或者是一个抽象类，或者构造函数全都有参数.
*/
	public Object toObject(int row,Factory f) throws IllegalAccessException,InstantiationException;
}

