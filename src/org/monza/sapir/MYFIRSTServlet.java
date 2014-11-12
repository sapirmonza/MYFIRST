package org.monza.sapir;

import java.io.IOException;
import javax.servlet.http.*;


@SuppressWarnings("serial")


public class MYFIRSTServlet extends HttpServlet {
	int num1=3, num2=4, num3=7,result=(num1+num2)*num3,base=20,exp=13,Radius = 50,angle=30,hypotenuse=50;
	double angleRadians=angle*Math.PI/180,opposit=Math.sin(angleRadians)*hypotenuse,Pie=Math.PI,Area=Pie*Math.pow(Radius, 2);
	String line1 = new String("<h1>calculation 1: Area of circle with radius"+Radius+"is:"+Area+"</h1");
	String line2 = new String("<h1>calculation 2: Length of opposite where angle B:"+opposit+"</h1");
	String line3 = new String("<h1>calculation 3: Power of 20 with exp of 13 is:"+Math.pow(base, exp)+"</h1");
	String resultStr = new String ("<h1>Result of ("+num1+"+"+num2+")*"+num3+"="+result+"</h1");
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		resp.getWriter().println(resultStr);
		resp.getWriter().println(line1);
		resp.getWriter().println(line2);
		resp.getWriter().println(line3);

	}
}
