package pac;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Deletion {
	//helper obj. for using its member functions
	static Helper helperObject=new Helper();
	
	public static void main(String[] args) {
		try {
			//loading the class
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(helperObject.mySqlUrl
					,helperObject.hostName
					,helperObject.password);
			
			//statement ob. for executing queries
			Statement stmt=con.createStatement();
			
			//getting queries from centralised class->queries
			String query=Queries.deleteProducts();
			
			//getting resultset in int  variable
			int rs=stmt.executeUpdate(query);
			
			//if there are rows in the output, it means, data is deleted
			if(rs>0) System.out.println(rs+" rows deleted successfully");
			else System.out.println("No such data exists for deletion");
			
			//terminating all connections
			stmt.close();
			con.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
