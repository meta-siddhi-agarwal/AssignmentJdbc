package pac;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Assignment4 {
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
			String query=Queries.displayParentCat();
			
			//resultset includes output of the query
			ResultSet rs=stmt.executeQuery(query);
			
			while(rs.next()) {
				System.out.println(rs.getString("Title"));
				System.out.println("No. of child are "+rs.getString("Child_Count"));
			}
			//terminating all connections
			rs.close();
			stmt.close();
			con.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
