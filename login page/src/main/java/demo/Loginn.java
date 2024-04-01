package demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/loginlink")
public class Loginn extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user=req.getParameter("name");
		String pass=req.getParameter("pass");
		
		if(user.equals("admin") && pass.equals("rc123")) {
			RequestDispatcher rd=req.getRequestDispatcher("/loginsecondlink");
			rd.forward(req, resp);
		}else {
			PrintWriter pw=resp.getWriter();
			pw.print("<h1> Invalid username & password");
			RequestDispatcher rd=req.getRequestDispatcher("index.html");
			rd.include(req, resp);
		}
	}
	

}
