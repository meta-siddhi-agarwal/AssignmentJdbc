package pac;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Insertion {
	//helper obj. for using its member functions
	static Helper helperObject=new Helper();

	public static void main(String[] args) {
		//five images which needs to be inserted
		String img1="https://example.com/images/laptop1.jpg";
		String img2="https://example.com/images/laptop2.jpg";
		String img3="https://example.com/images/laptop3.jpg";
		String img4="https://example.com/images/laptop4.jpg";
		String img5="https://example.com/images/laptop5.jpg.jpg";
		
		//creating list of images 
		List<String> imgList=new ArrayList<>();
		imgList.add(img1);
		imgList.add(img2);
		imgList.add(img3);
		imgList.add(img4);
		imgList.add(img5);
		
		try {
			//loading the class
			Class.forName("com.mysql.cj.jdbc.Driver");
			//establishing con. with the mysql server
			Connection con=DriverManager.getConnection(helperObject.mySqlUrl
					,helperObject.hostName
					,helperObject.password);
			//statement ob. for executing queries
			Statement stmt=con.createStatement();
			
			for(int imgListIndex=0;imgListIndex<imgList.size();imgListIndex++){
				//retrieving query from centralised class ->query
				String query=Queries.insertImg(imgList, imgListIndex);
				//adding all the queries in batch
				stmt.addBatch(query);
			}
			
			//submitting all the batch commands
			int rs[]=stmt.executeBatch();
			
			if(rs.length>=0)System.out.println("Images added successfully");
			else System.out.println("Images could not be added");	
			
			//terminating all connections
			stmt.close();
			con.close();
		}
		catch(BatchUpdateException b) {
			System.out.println("Couldn't add images");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
