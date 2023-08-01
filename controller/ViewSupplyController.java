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

import net.proteanit.sql.DbUtils;
//import view.SearchProductGUI;
import database.MyDatabase;

public class ViewSupplyController {
	
	public boolean checkResProduct(RestockProduct resproduct) throws ClassNotFoundException, SQLException
	{
		boolean available= true;
		String checkResProduct = "select rpID from restockproduct where rpID = ?";
		
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(checkResProduct);
		preparedStatement.setString(1, resproduct.getRpID());
		
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next())
			available = false;

		conn.close();
		
		return available;
	}
		
	
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
	
	public ResultSet viewEverything(RestockProduct resproduct) throws SQLException, ClassNotFoundException
	{
		String viewEverything = "select * from restockproduct";
		
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(viewEverything);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		return resultSet;
	}
	
	public void updateStatusAccept (RestockProduct resproduct) throws ClassNotFoundException, SQLException
	{
		String updateStatusAccept = "update restockproduct set rpStatus = 'Accepted' where rpID=? ";
		Connection conn = MyDatabase.doConnection();
		int rpID = Integer.parseInt(resproduct.getRpID());
		PreparedStatement preparedStatement = conn.prepareStatement(updateStatusAccept);
		preparedStatement.setInt(1, rpID);
		int resultSet = preparedStatement.executeUpdate();
		conn.close();
	}
	
	public void updateStatusDeclined (RestockProduct resproduct) throws ClassNotFoundException, SQLException
	{
		String updateStatusDeclined  = "update restockproduct set rpStatus = 'Declined' where rpID=? ";
		Connection conn = MyDatabase.doConnection();
		int rpID = Integer.parseInt(resproduct.getRpID());
		PreparedStatement preparedStatement = conn.prepareStatement(updateStatusDeclined);
		preparedStatement.setInt(1, rpID);
		int resultSet = preparedStatement.executeUpdate();
		conn.close();
	}
}
