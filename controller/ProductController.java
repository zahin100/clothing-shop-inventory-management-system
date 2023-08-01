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

import model.Product;
import net.proteanit.sql.DbUtils;
import database.MyDatabase;

public class ProductController
{
	public boolean isProductAvailable(Product product) throws ClassNotFoundException, SQLException
	{
		boolean available= true;
		String checkProduct = "select ProductName from Product where ProductName = ?";
		
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(checkProduct);
		preparedStatement.setString(1, product.getProductName());
		
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next())
			available = false;

		conn.close();
		
		return available;
	}
	
	public void insertProduct(Product product) throws ClassNotFoundException, SQLException 
	{
		String insertProduct ="insert into product (productID,productName,productPrice,productStock,supplierID,catID) values (?,?,?,?,?,?)";
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(insertProduct);
		preparedStatement.setString(1, product.getProductID());
		preparedStatement.setString(2, product.getProductName());		
		preparedStatement.setString(3, product.getProductPrice());
		preparedStatement.setString(4, product.getProductStock());
		preparedStatement.setString(5, product.getSupplierID());
		preparedStatement.setString(6, product.getCatID());
		preparedStatement.executeUpdate();
		
		conn.close();
	}	
	
	public ArrayList<Product> getProduct() throws SQLException, ClassNotFoundException
	{
		ArrayList<Product> products = new ArrayList<Product>();
				
		String sql = "select productID,productName,productPrice,productStock,supplierID,catID from product";
		
		//1. Connection 
		Connection conn = MyDatabase.doConnection();
		//2. PreparedStatement
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
				
		//3. View or insert/update
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next())
		{
			Product product = new Product();
			product.setProductName(resultSet.getString(1));
			product.setProductPrice(resultSet.getString(2));
			product.setProductStock(resultSet.getString(3));
			
			products.add(product);
			
		}
		
		//4. Must close the connection
		conn.close();
		
		return products;
	}
		
	public ResultSet findProduct(Product product) throws SQLException, ClassNotFoundException
	{
		String findProduct = "select * from Product where ProductName like CONCAT ('%',?,'%') ";
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(findProduct);
		preparedStatement.setString(1, product.getProductName());
		
		ResultSet resultSet = preparedStatement.executeQuery();
				
		return resultSet;
		
	}
	
	public ResultSet viewAll(Product product) throws SQLException, ClassNotFoundException
	{
		String viewAll = "select * from Product";
		
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(viewAll);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		return resultSet;
	}
	
	public boolean checkIfProductExist(Product product) throws ClassNotFoundException, SQLException
	{
		boolean data = false;
		String validateData = "select productID from product where productID = ?";
		
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(validateData);
		preparedStatement.setString(1, product.getProductID());
		
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next())
			data = true;
		
		
		conn.close();
		
		return data;
	}

	public void deleteProduct(Product product) throws ClassNotFoundException, SQLException
	{
		String deleteProduct = "delete from Product where productID = ?";
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(deleteProduct);
		
		preparedStatement.setString(1, product.getProductID());
		int resultSet = preparedStatement.executeUpdate();
		
		conn.close();
	}
	
	public void updateProduct(Product product) throws ClassNotFoundException, SQLException
	{
		String updateProduct = "update Product set productName = ?, productPrice = ?, productStock = ?, supplierID = ?, catID = ? where product.productID = ?";
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = conn.prepareStatement(updateProduct);
		
		int productID = Integer.parseInt(product.getProductID());
		double productPrice = Double.parseDouble(product.getProductPrice());
		int productStock = Integer.parseInt(product.getProductStock());
		int supplierID = Integer.parseInt(product.getSupplierID());
		int catID = Integer.parseInt(product.getCatID());
		
		preparedStatement.setString(1, product.getProductName());
		preparedStatement.setDouble(2, productPrice);
		preparedStatement.setInt(3, productStock);
		preparedStatement.setInt(4, supplierID);
		preparedStatement.setInt(5, catID);
		preparedStatement.setInt(6, productID);
		preparedStatement.executeUpdate();
		conn.close();
	}
	
}


