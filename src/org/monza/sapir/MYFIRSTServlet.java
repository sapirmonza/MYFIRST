package org.monza.sapir;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class MYFIRSTServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, sapir monza");
	}
}
