package pac;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class StoreFront {

	public static void main(String[] args) {
		String host="jdbc:mysql://localhost:3306/";
		String dbName="StoreFont";
		String mySqlUrl=host+dbName;
		String hostName="root";
		String password="root";
		String orderStat="Shipped";
		Scanner scannerObject=new Scanner(System.in);
		
		try {
			System.out.println("Enter your id");
			int userId=scannerObject.nextInt();
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(mySqlUrl,hostName,password);
			Statement stmt=con.createStatement();
			String query="SELECT Orders.OrderId, OrderDate, OrderTotal "
					+ "FROM Shopper "
					+ "INNER JOIN Orders ON Shopper.ShopperId=Orders.ShopperId "
					+ "INNER JOIN OrderItem ON Orders.OrderId=OrderItem.OrderId "
					+ "AND Shopper.ShopperId= "
					+  userId
					+ " AND OrderStatus= '"+orderStat+"' "
					
					+ " ORDER BY OrderDate;";
			ResultSet rs=stmt.executeQuery(query);
			int orderNumber=1;
			while(rs.next()) {
				int orderId=rs.getInt("orderId");
				Date orderdate=rs.getDate("orderDate");
				int orderTotal=rs.getInt("orderTotal");
				System.out.println("Order "+orderNumber+
						"-> Orderid= "+orderId+
						"	OrderDate= "+orderdate+
						"	OrderTotal= "+orderTotal);
				System.out.println();
				orderNumber++;
			}			
			rs.close();
			stmt.close();
			con.close();
			
		}
		catch(Exception e) {
			System.out.println(e);
		}

	}

}
