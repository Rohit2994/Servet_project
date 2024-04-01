package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.StuDAO;
import Model.StuDTO;

@WebServlet(urlPatterns = {"/addstudent","/getattendance"})
public class Student extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String path=req.getServletPath();
      switch(path) {
      case "/addstudent":addStudentDetails(req,resp);
           break;
      case "/getattendance":getStudentDetails(req,resp);
           break;
      }
	}

	private void getStudentDetails(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}

	private void addStudentDetails(HttpServletRequest req, HttpServletResponse resp) {
		String name=req.getParameter("name");
		String bcode=req.getParameter("bcode");
		int sclass=Integer.parseInt(req.getParameter("class"));
		int attend=Integer.parseInt(req.getParameter("attend"));
		
		// add data to dto object
		StuDTO dto=new StuDTO();
		dto.setName(name);
		dto.setBcode(bcode);
		dto.setSclass(sclass);
		dto.setAttend(attend);
		
		// send data to dao layer
		StuDAO dao=new StuDAO();
	    int count=dao.InsertStudent(dto);
	    req.setAttribute("count",count);
	    RequestDispatcher rd=req.getRequestDispatcher("addStudent.jsp");
	    
	    try {
			rd.forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
