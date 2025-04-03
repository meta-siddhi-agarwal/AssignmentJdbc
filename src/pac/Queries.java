package pac;
import java.util.List;

//centralised class for storing all the queries
public class Queries {
	
	/**
	 * will fetch all the orders of a particular user
	 * @param userId -> denotes uniq. identifier of a user
	 * @param orderStat-> denotes for which order status we need output
	 * @return string query
	 */
	static public String fetchOrders(int userId, String orderStat) {
		String query="SELECT Orders.OrderId, OrderDate, OrderTotal "
				+ "FROM Shopper "
				+ "INNER JOIN Orders ON Shopper.ShopperId=Orders.ShopperId "
				+ "INNER JOIN OrderItem ON Orders.OrderId=OrderItem.OrderId "
				+ "AND Shopper.ShopperId= "
				+  userId
				+ " AND OrderStatus= '"+orderStat+"' "				
				+ " ORDER BY OrderDate;";
		return query;
		
	}
	
	static public String insertImg(List<String> imgList,int imgListIndex) {
		String query="INSERT INTO Image(ProductId,ImageUrl) "
				+ "VALUES"
				+ "(1,'"+imgList.get(imgListIndex)+"')";
		return query;
		
	}
	
	/**
	 * query for deleting products from table
	 * @return string query
	 */
	static public String deleteProducts() {
		String query="DELETE Products FROM Products,OrderItem,Orders "
				+ "WHERE Products.ProductId NOT IN( Products.ProductId=OrderItem.ProductId "
				+ "AND OrderItem.OrderId=Orders.OrderId "
				+ "AND OrderDate BETWEEN CURDATE()-365 AND CURDATE())";
		return query;
	}
	
	/**
	 * will display the parent cat. title along with the count of their child cat.
	 * @return string query
	 */
	static public String displayParentCat() {
		String query="SELECT Left_Table.Cat_Title AS Title,"
				+ "COUNT(Right_Table.Parent_Cat_Id) AS Child_Count "
				+ "FROM Category Left_Table "
				+ "INNER JOIN Category Right_Table "
				+ "ON Left_Table.Cat_Id=Right_Table.Parent_Cat_Id "
				+ "GROUP BY Right_Table.Parent_Cat_Id "
				+ "ORDER BY Left_Table.Cat_Title ";
		return query;		
	}
}

