package trionesII.util;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
/*
// header - edit "lbytes/yourJavaHeader" to customize
// contents - edit "EventHandlers/Java file/onCreate" to customize
//
*/
class HttpServletResponseMethod
{ 

	public static void download(HttpServletResponse response,File file)
		 throws IOException
	{	 response.setContentType("application/download");
		 response.setHeader("Content-Disposition","attachment;filename=" + file.getName());
			
		 javax.servlet.ServletOutputStream lOutput = response.getOutputStream();
		 BufferedInputStream lInput = new BufferedInputStream(new FileInputStream(file));
			
		 byte[] lbytes = new byte[1000];
		 int li_size;
			
		 while((li_size = lInput.read(lbytes))!= -1)
		 {	lOutput.write(lbytes,0,li_size);
		 }
		
		 lOutput.flush();
		 lInput.close();
		 lOutput.close();
	}


}

