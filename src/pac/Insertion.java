package pac;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Insertion {
	static Helper helperObject=new Helper();

	public static void main(String[] args) {
		String img1="https://example.com/images/laptop1.jpg";
		String img2="https://example.com/images/laptop2.jpg";
		String img3="https://example.com/images/laptop3.jpg";
		String img4="https://example.com/images/laptop4.jpg";
		String img5="https://example.com/images/laptop5.jpg.jpg";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(helperObject.mySqlUrl
					,helperObject.hostName
					,helperObject.password);
			Statement stmt=con.createStatement();
			String query="INSERT INTO Image(ProductId,ImageUrl) "
					+ "VALUES"
					+ "(1, '"+img1+"'),"
					+ "(1, '"+img2+"'),"
					+ "(1, '"+img3+"'),"
					+ "(1, '"+img4+"'),"
					+ "(1, '"+img5+"')";
			boolean rs=stmt.execute(query);
			if(rs)System.out.println("Images added successfully");
			else System.out.println("Images could not be added");		
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
