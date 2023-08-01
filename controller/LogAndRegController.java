package controller;
import model.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.PreparedStatement;
import database.MyDatabase;

public class LogAndRegController {
	
	public boolean isUsernameAvailable(User user) throws ClassNotFoundException, SQLException
	{
		boolean available = true;
		String checkUsername = "select Username from user where Username = ?";
		
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(checkUsername);
		preparedStatement.setString(1, user.getUsername());
		
		ResultSet resultSet = preparedStatement.executeQuery();
		if(resultSet.next())
			available = false;

		conn.close();
		
		return available;
	}
	
	public void insertUser(User user) throws ClassNotFoundException, SQLException
	{
		String insertUser = "insert into user (Username, Password, Name, PhoneNum, Address, Role) values (?, ?, ?, ?, ?, ?)";

		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(insertUser);
		preparedStatement.setString(1, user.getUsername());
		preparedStatement.setString(2, user.getPassword());
		preparedStatement.setString(3, user.getName());
		preparedStatement.setString(4, user.getPhoneNum());
		preparedStatement.setString(5, user.getAddress());
		preparedStatement.setInt(6, user.getRole());
		preparedStatement.executeUpdate();
		conn.close();
	}
	
	public boolean validateLoginData(User user) throws ClassNotFoundException, SQLException
	{
		boolean data = false;
		String validateData = "select * from user where Username = ? and Password = ?";
		
		Connection conn = MyDatabase.doConnection();
		PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(validateData);
		preparedStatement.setString(1, user.getUsername());
		preparedStatement.setString(2, user.getPassword());
		
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next())
		{
			data = true;
			
			user.setUserID(resultSet.getInt(1));
			user.setUsername(resultSet.getString(2));
			user.setPassword(resultSet.getString(3));
			user.setName(resultSet.getString(4));
			user.setPhoneNum(resultSet.getString(5));
			user.setAddress(resultSet.getString(6));
			user.setRole(resultSet.getInt(7));
		}
		
		conn.close();
		
		return data;
	}
	
}
