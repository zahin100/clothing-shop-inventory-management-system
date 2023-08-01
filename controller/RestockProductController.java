package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.PreparedStatement;
import database.MyDatabase;
import model.Restock;
import model.RestockProduct;
import model.User;
import model.Product;

public class RestockProductController {
	
	public boolean isUsernameAvailable(RestockProduct resProduct) throws ClassNotFoundException, SQLException
	{
		boolean available = true;
		String checkrpID = "select rpID from restockproduct where rpID = ?";
		
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(checkrpID);
		preparedStatement.setString(1, resProduct.getRpID());
		
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next())
			available = false;

		conn.close();
		
		return available;
	}
	
	public void totalPrice(RestockProduct resProduct,Product product) throws ClassNotFoundException, SQLException
	{
		Connection conn = MyDatabase.doConnection();
		  //double price=0;
		  //double price=0;
		  double totalPrice=0;
		  String getsID = "select productPrice from product where productID = ?";
		  PreparedStatement preStatement = (PreparedStatement) conn.prepareStatement(getsID);
		  preStatement.setString(1, product.getProductID());
		  ResultSet resultSet = preStatement.executeQuery();
		  while(resultSet.next())
		  {
			 double price = resultSet.getDouble(1);
			 int rpQuantity = Integer.parseInt(resProduct.getRpQuantity());
			  totalPrice = price  * rpQuantity;
			  String str="";
			  str += Double.toString(totalPrice);
			  resProduct.setRpTotalPrice(str);
		  }
		 
		   
		  
		
		conn.close();
	}
	
	
	
	public void insertRestock(RestockProduct resProduct,Product product) throws ClassNotFoundException, SQLException
	{
		Connection conn = MyDatabase.doConnection();
		  int id = 0;
		  String getID = "select RestockID from restock order by RestockID desc limit 1";
		  	
		  PreparedStatement preStatement = (PreparedStatement) conn.prepareStatement(getID);
		  ResultSet resultSet = preStatement.executeQuery();
		  if(resultSet.next())
		  id = resultSet.getInt(1);
		  int ProductID = 0;
		  String getProID = "select productID from product order by productID desc limit 1";
		  	
		  PreparedStatement preStatementt = (PreparedStatement) conn.prepareStatement(getProID);
		  ResultSet resultSets = preStatementt.executeQuery();
		  if(resultSets.next())
		  ProductID = resultSets.getInt(1);
		int rpQuantity = Integer.parseInt(resProduct.getRpQuantity());
		double rpPrice = Double.parseDouble(resProduct.getRpTotalPrice());
		
		String insertresProduct = "insert into restockproduct (rpQuantity, rptotalPrice, rpStatus,restockID,productID) values (?, ?, ?, ?, ?)";

		
		PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(insertresProduct);
		preparedStatement.setInt(1, rpQuantity);
		preparedStatement.setDouble(2, rpPrice);
		preparedStatement.setString(3, resProduct.getRpStatus());
		preparedStatement.setInt(4, id);
		preparedStatement.setInt(5, ProductID);
		preparedStatement.executeUpdate();
		conn.close();
	}
	
	 public void updateProductStock (int productID, int quantityToRestock) throws ClassNotFoundException, SQLException
	 {
		 
		 String getCurrentProductStock = "Select productStock from product where productID = ? ";
		 int currentStock = 0;
		 int newStock = 0;
		 Connection conn = MyDatabase.doConnection();
			
		 PreparedStatement preStatement = (PreparedStatement) conn.prepareStatement(getCurrentProductStock);
		 preStatement.setInt(1, productID);
		 ResultSet resultSet = preStatement.executeQuery();
	     if(resultSet.next())
			currentStock = resultSet.getInt(1);
			  
		 newStock = currentStock + quantityToRestock;
		 String updateProductStock = "update product set productStock = ? where productID =? ";
		 PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(updateProductStock);
		 preparedStatement.setInt(1, newStock);
		 preparedStatement.setInt(2, productID);
		 preparedStatement.executeUpdate();
			
		 conn.close();
		 
	 }
	
}

