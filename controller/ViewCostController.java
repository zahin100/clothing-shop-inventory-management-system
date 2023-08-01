package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;

import model.RestockProduct;
import model.Product;

import net.proteanit.sql.DbUtils;
//import view.SearchProductGUI;
import database.MyDatabase;

public class ViewCostController {
	
	public ArrayList<RestockProduct> getRestockproduct() throws SQLException, ClassNotFoundException
	{
		ArrayList<RestockProduct> Restockproducts = new ArrayList<RestockProduct>();
				
		String sql = "select rpID, rpQuantity, rpTotalPrice,rpStatus,restockID,productID from product";
		
		//1. Connection 
		Connection conn = MyDatabase.doConnection();
		//2. PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
				
		//3. View or insert/update
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next())
		{
			RestockProduct product = new RestockProduct();
			product.setRpID(resultSet.getString(1));
			
			Restockproducts.add(product);
			
		}
		
		//4. Must close the connection
		conn.close();
		
		return Restockproducts;
	}
	
	public ResultSet viewAll(RestockProduct resproduct) throws SQLException, ClassNotFoundException
	{
		String viewAll = "select * from restockproduct where rpStatus= 'Accepted' ";
		
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(viewAll);

		ResultSet resultSet = preparedStatement.executeQuery();
		
		return resultSet;
	}

	public double rptotalPrice() throws ClassNotFoundException, SQLException
	  {
		double totalCost=0;
	    String rptotalPrice = "select SUM(rpTotalPrice) FROM restockProduct where rpStatus = 'Accepted'";
	    Connection conn = MyDatabase.doConnection();
	    
	    PreparedStatement preparedStatement = conn.prepareStatement(rptotalPrice);
	    ResultSet resultSet = preparedStatement.executeQuery();
	    if (resultSet.next())
	    	totalCost= resultSet.getDouble(1);
		conn.close();
		
		return totalCost;
	  }
}
