package pac;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StoreFront {
	
	//helper obj. for using its member functions
	static Helper helperObject=new Helper();

	public static void main(String[] args) {
		
		//we will retrieve only those values whose order status is shipped
		String orderStat="Shipped";
		
		//scanner obj. for taking id from user
		Scanner scannerObject=new Scanner(System.in);
		
		try {
			System.out.println("Enter your id");
			int userId=scannerObject.nextInt();
			if(userId<=0)throw new Exception("Enter valid id");
			
			//loading the class
			Class.forName("com.mysql.cj.jdbc.Driver");
			//establishing con. with the mysql server
			Connection con=DriverManager.getConnection(helperObject.mySqlUrl,
					helperObject.hostName,helperObject.password);
						
			//getting queries from centralised class->queries
			String query=Queries.fetchOrders(userId, orderStat);
			
			//statement ob. for executing queries
			Statement stmt=con.prepareStatement(query);
			
			//resultset includes output of the query
			ResultSet rs=stmt.executeQuery(query);
			
			int orderNumber=1;
			//if there are rows in the output
			if(rs.next()) {
				while(rs.next()) {
					int orderId=rs.getInt("orderId");
					Date orderDate=rs.getDate("orderDate");
					int orderTotal=rs.getInt("orderTotal");
					System.out.println("Order "+orderNumber+
							"-> Orderid= "+orderId+
							"	OrderDate= "+orderDate+
							"	OrderTotal= "+orderTotal);
					System.out.println();
					orderNumber++;
					Orders orderObject=new Orders(orderId, orderDate, orderTotal);
				    Orders.orderList.add(orderObject);
				}
			}
			else {
				System.out.println("There is no such order "
						+ "in shipped state for provided user");
			}
						
			//terminating all connections
			rs.close();
			stmt.close();
			con.close();
			
		}
		catch(InputMismatchException e) {
			System.out.println("Enter valid input");
		}
		catch(Exception e) {
			if(e.getMessage()==null)
			System.out.println(e);
			else System.out.println(e.getMessage()); 				
		}
	}
}
