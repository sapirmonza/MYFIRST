package org.monza.sapir;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class MYFIRSTServlet extends HttpServlet {
	int num1=3, num2=4, num3=7,result=(num1+num2)*num3;
	String resultStr = new String ("<h1>Result of ("+num1+"+"+num2+")*"+num3+"="+result+"</h1");
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		resp.getWriter().println(resultStr);
	}
}
