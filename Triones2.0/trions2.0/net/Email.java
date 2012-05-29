package trionesII.net;

import trionesII.util.*;
import java.util.*;
import javax.mail.*;
import java.io.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
* 电子邮件（创建于 2003.03.07）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
用于客户端发送电子邮件信息。
</pre>
<DT><B>使用说明：</B><DD>
<pre>
//设定邮件主题和正文内容
	Email email = new Email("testEmail","just to test");
	//添加附件
	email.appendFile("e:\\魏雪漫.htm");
	email.appendFile("e:\\shxtest.ZIP");
	//设定收件人地址，多个收件人之间用";"分隔
	email.setTo("wyq@wondersgroup.com;wuyqb@yahoo.com.cn;wuyqb@china.com");
	//发送邮件，需设定发送邮件服务器URL、发件人邮件地址、登录帐号、密码
	email.send("WONDERS_IT","wyq@wondersgroup.com","wyq","shang98");
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
* @version	2003.03.07
*/
public class Email
{		
/**
* 邮件主题（创建于 2003.03.07）.
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
创建于 2003.03.07.
</pre>
</DL>
*/
	String _Subject;
/**
* 收件人地址（创建于 2003.03.07）.
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
创建于 2003.03.07.
</pre>
</DL>
*/
	Address[] _Recipients;
/**
* 邮件主体（创建于 2003.03.07）.
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
创建于 2003.03.07.
</pre>
</DL>
*/
	Multipart _Body;
	
/**
* 邮件地址分隔符（创建于 2003.03.07）.
<DL>
<DT><B>说明：</B><DD>
<pre>
用于分隔多个收件人的地址
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.07.
</pre>
</DL>
*/
	public static char ADDRESS_APART = ';';


/**
* 构造方法（创建于 2003.03.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
只设定邮件主题。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.07.
</pre>
</DL>
* @param subject 邮件主题.
* @see #Email(String,String)
*/
	public Email(String subject)
	{	init(subject);		
	}
	
/**
* 构造方法（创建于 2003.03.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
设定邮件主题和邮件正文内容
</pre>
<DT><B>示例：</B><DD>
<pre>
//设定邮件主题和正文内容
	Email email = new Email("testEmail","just to test");
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.07.
</pre>
</DL>
* @param subject 邮件主题.
* @param text 邮件正文.
* @exception MessagingException description.
* @see #Email(String)
*/
	public Email(String subject,String text)
		throws MessagingException
	{	init(subject);
		setText(text);
	}


/**
* 初始化邮件（创建于 2003.03.07）.
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
创建于 2003.03.07.
</pre>
</DL>
* @param subject 邮件主题.
*/
	private void init(String subject)
	{	_Subject = subject;
		_Body = new MimeMultipart();	
	}

/**
* 设置邮件正文（创建于 2003.03.07）.
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
创建于 2003.03.07.
</pre>
</DL>
* @param text 邮件正文.
	@throws MessagingException 设置邮件包体错误.
*/
	public void setText(String text)
		throws MessagingException
	{	MimeBodyPart lmbp = new MimeBodyPart();
		lmbp.setText(text);
		_Body.addBodyPart(lmbp);
	}

	
/**
* 添加附件（创建于 2003.03.07）.
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
创建于 2003.03.07.
</pre>
</DL>
* @param filename 带路径的文件名.
	@throws MessagingException  设置邮件包体错误.
*/
	public void appendFile(String filename)
		throws MessagingException
	{	MimeBodyPart lmbp = new MimeBodyPart();
		FileDataSource lfds = new FileDataSource(filename);
		lmbp.setDataHandler(new DataHandler(lfds));
		
		lmbp.setFileName(FileMethod.ignoreDir(filename));
		
		_Body.addBodyPart(lmbp);		
	}
	

/**
* 设置收件人邮件地址（创建于 2003.03.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
多个邮件人的地址用“;”分隔。
</pre>
<DT><B>示例：</B><DD>
<pre>
//email为Email的一个实例
	email.setTo("wyq@wondersgroup.com;wuyqb@yahoo.com.cn;wuyqb@china.com");
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.07.
</pre>
</DL>
* @param to 收件人邮件地址.
	@throws AddressException 邮件地址不合法.
*/
	public void setTo(String to)
		throws AddressException
	{ String[] ls_to = StringMethod.toChilds(to,ADDRESS_APART);
		
		_Recipients = new Address[ls_to.length];
		for(int i=0;i<ls_to.length;i++)
			_Recipients[i] = new InternetAddress(ls_to[i]);
	}
	
	
/**
* 发送邮件（创建于 2003.03.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
	//email为Email的一个实例
	email.send("WONDERS_IT","wyq@wondersgroup.com","wyq","12345");
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.07.
</pre>
</DL>
* @param host 邮件发送服务器.
* @param from 发件人邮件地址.
* @param login 邮件服务器登录账号.
* @param password 登录密码.
* @exception MessagingException 设置邮件包体错误.
*/
	public void send(String host,String from,String login,String password)
		throws MessagingException
	{	Session session;
		MimeMessage message;		
		Properties props = System.getProperties();
		props.put("mail.smtp.host",host);
		session = Session.getInstance(props,null);

		message = new MimeMessage(session);		
		message.setFrom(new InternetAddress(from));
		message.addRecipients(Message.RecipientType.TO,_Recipients);
		message.setSubject(_Subject);
		message.setContent(_Body);
		message.setSentDate(new java.util.Date());

		Transport transport = session.getTransport("smtp");		
		transport.connect(host,login,password);		
		transport.send(message);	
	}
	
}
