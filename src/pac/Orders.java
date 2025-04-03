package pac;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//Orders table for storing orders of a particular user
public class Orders {
	int orderId;
	Date orderDate;
	int orderTotal;
	
	//list will store all orders of a particular user
	static List<Orders> orderList=new ArrayList<>();
	
	//constructor for initializing the variables
	Orders(int orderId,Date orderDate,int orderTotal){
		this.orderId=orderId;
		this.orderDate=orderDate;
		this.orderTotal=orderTotal;		
	}
}
