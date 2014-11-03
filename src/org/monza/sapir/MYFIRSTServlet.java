package org.monza.sapir;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class MYFIRSTServlet extends HttpServlet {
	int num1=3, num2=4, num3=7;
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().printf("Result of (%d+%d)*%d=%d",num1,num2,num3,(num1+num2)*num3);
	}
}
