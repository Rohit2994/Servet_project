package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/additem")
public class AddGroceryItems extends HttpServlet{
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//fetch the values from html
		String item=req.getParameter("item");
		String stock=req.getParameter("stock");
		String price=req.getParameter("price");
		
		//parse values
		double stock1=Double.parseDouble(stock);
		double price1=Double.parseDouble(price);
		
		//declare resources
		PreparedStatement pstmt=null;
		
		//create query
		String query="insert into product(name,stock,price) values(?,?,?)";
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1,item);
			pstmt.setDouble(2, stock1);
			pstmt.setDouble(3, price1);
			int count=pstmt.executeUpdate();
			PrintWriter pw=resp.getWriter();
			pw.print("<h1>"+count+" RECORD INSERTED SUCCESSFULLY</h1>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
}

}
