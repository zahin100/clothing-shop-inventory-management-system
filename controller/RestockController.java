package controller;

import model.Restock;
import model.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.PreparedStatement;
import database.MyDatabase;
import model.Product;
import view.LoginGui;


public class RestockController {
  
	private int users;
	
	public void setUser(int users) {
		this.users = users;
	}
	public int getUser() {
		return users;
	}
	
	
  public boolean isRestockIdAvailable(Restock restock) throws ClassNotFoundException, SQLException
  {
    boolean available = true;
    String checkRestockID = "select restockID from restock where restockID = ?";
    
    Connection conn = MyDatabase.doConnection();
    PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(checkRestockID);
    preparedStatement.setInt(1, restock.getRestockID());
    
    ResultSet resultSet = preparedStatement.executeQuery();
    if(resultSet.next())
      available = false;

    conn.close();
    
    return available;
  }
  
  public boolean isProductAvailable(Product product) throws ClassNotFoundException, SQLException
  {
    boolean available = true;
    String checkProductID = "select ProductID from product where ProductID = ?";
    
    Connection conn = MyDatabase.doConnection();
    PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(checkProductID);
    preparedStatement.setString(1, product.getProductID());
    
    ResultSet resultSet = preparedStatement.executeQuery();
    if(resultSet.next())
      available = false;

    conn.close();
    
    return available;
  }
  
  public void insertRestock(Restock restock) throws ClassNotFoundException, SQLException
  {
	  String insertRestock = "insert into restock (restockDate) values (?)";
	  Connection conn = MyDatabase.doConnection();
	  PreparedStatement preStatements = (PreparedStatement) conn.prepareStatement(insertRestock);
	  preStatements.setString(1, restock.getRestockDate());
	  //preStatement.setInt(2, users);
	  preStatements.executeUpdate();
	

	  conn.close();
		

  }
  

}