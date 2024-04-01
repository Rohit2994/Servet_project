package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StuDAO {
	  static Connection con;
	 
	  static{
		  try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/1ejm9?","root","sqql123");
		   } catch (ClassNotFoundException e) {
		  	// TODO Auto-generated catch block
			 e.printStackTrace();
		    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
	     }
	  
	  public int InsertStudent(StuDTO dto) {
		  PreparedStatement pstmt=null;
	      String query="insert into student_attend(name , batch_code , total_class , class_attend) values(?,?,?,?)";
	      int count=0;
	      try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getBcode());
			pstmt.setInt(3, dto.getSclass());
			pstmt.setInt(4, dto.getAttend());
			count=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
		
	  
	  }

}
