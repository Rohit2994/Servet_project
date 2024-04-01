package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/purchaseitem")
public class PurchaseGroceryItems extends HttpServlet {
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
		String itemid=req.getParameter("itemid");
		String qty=req.getParameter("qty");
		
		
		//parse values
		int id=Integer.parseInt(itemid);
	    double qty1=Double.parseDouble(qty);
		
		//declare resources
		PreparedStatement pstmt=null;
		ResultSet rs;
		
		//create query
		 String query="select name,stock,price from product where id=?";
	     String query1="update product set stock=? where id=?";
	     

			try {
				pstmt=con.prepareStatement(query);
				pstmt.setInt(1, id);
				rs=pstmt.executeQuery();
				 PrintWriter pw=resp.getWriter();
	            if(rs.next()){
	            	String name=rs.getString(1);
	            	Double stock=rs.getDouble(2);
	            	Double price=rs.getDouble(3);
	            	if(qty1<=stock){
	                    double total=price*qty1;
	                    pw.print("<h1>TOTAL AMOUNT IS "+ total +"</h1>");
	                    pstmt=con.prepareStatement(query1);
	                    pstmt.setDouble(1, stock-qty1);
	                    pstmt.setInt(2,id);
	                    pstmt.executeUpdate();
	            	    pw.print("<h1>Stocks Updated Successfully");
	            }else {
	            	pw.print("<h1> OUT OF STOCK </h1>");
	            }
	            	
	            }else {
	            	pw.print("<h1>item not found</h1>");
	            }
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
}
}
