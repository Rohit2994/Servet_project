package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.protocol.x.XpluginStatementCommand;

@WebServlet("/display")
public class DisplayGroceryItems extends HttpServlet {
	Connection con;
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/grocery?","root","sql123");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Statement stmt=null;
		ResultSet rs=null;
		PrintWriter pw=resp.getWriter();
		
		String query="select * from product";
	
		
		try {
			stmt=con.createStatement();
			rs=stmt.executeQuery(query);
			pw.print("<table border='2'>");
			pw.print("<tr>");
			pw.print("<th>ITEM ID</th>");
			pw.print("<th>NAME</th>");
			pw.print("<th>STOCK</th>");
			pw.print("<th>PRICE</th>");
			pw.print("</tr>");
			
		    while(rs.next()) {
		    	pw.print("<tr>");
		    	pw.print("<td>"+rs.getInt(1)+"</td>");
		    	pw.print("<td>"+rs.getString(2)+"</td>");
		    	pw.print("<td>"+rs.getDouble(3)+"</td>");
		    	pw.print("<td>"+rs.getDouble(4)+"</td>");
                pw.print("</tr>");
		    }
		    pw.print("</table>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

}
