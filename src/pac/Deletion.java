package pac;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Deletion {
	static Helper helperObject=new Helper();
	
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(helperObject.mySqlUrl
					,helperObject.hostName
					,helperObject.password);
			Statement stmt=con.createStatement();
			String query="DELETE FROM Products,OrderItem"
					+ "WHERE NOT Products.ProductId=OrderItem.ProductId"
					+ "AND OrderDate<CURDATE()-365";
			int rs=stmt.executeUpdate(query);
			if(rs>0) System.out.println(rs+" rows deleted successfully");
			else System.out.println("No such data exists for deletion");
		}
		catch(Exception e) {
			System.out.println(e);
		}
		

	}

}
