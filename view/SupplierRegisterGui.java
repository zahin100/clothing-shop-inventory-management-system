package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.LogAndRegController;
import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class SupplierRegisterGui extends JFrame {

	private JPanel contentPane;
	private JTextField textUsername;
	private JPasswordField passwordField;
	private JPasswordField textConfirmPassword;
	private JTextField textName;
	private JTextField textPhoneNumber;
	private JTextField textAddress;
	private LogAndRegController logRegController = new LogAndRegController();
	private User user = new User();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SupplierRegisterGui frame = new SupplierRegisterGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SupplierRegisterGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 678);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel supplierRegistration = new JLabel("SUPPLIER REGISTRATION");
		supplierRegistration.setFont(new Font("Tahoma", Font.BOLD, 22));
		supplierRegistration.setBounds(153, 11, 301, 60);
		contentPane.add(supplierRegistration);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		usernameLabel.setBounds(97, 114, 108, 35);
		contentPane.add(usernameLabel);
		
		textUsername = new JTextField();
		textUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textUsername.setBounds(195, 117, 283, 35);
		contentPane.add(textUsername);
		textUsername.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		passwordLabel.setBounds(97, 183, 108, 35);
		contentPane.add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 18));
		passwordField.setBounds(195, 183, 283, 35);
		contentPane.add(passwordField);
		
		JLabel confirmPasswordLabel = new JLabel("Confirm Password");
		confirmPasswordLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		confirmPasswordLabel.setBounds(23, 248, 170, 35);
		contentPane.add(confirmPasswordLabel);
		
		textConfirmPassword = new JPasswordField();
		textConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
		textConfirmPassword.setBounds(195, 248, 283, 35);
		contentPane.add(textConfirmPassword);
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		nameLabel.setBounds(132, 311, 64, 35);
		contentPane.add(nameLabel);
		
		textName = new JTextField();
		textName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textName.setColumns(10);
		textName.setBounds(195, 314, 283, 35);
		contentPane.add(textName);
		
		JLabel phoneNumberLabel = new JLabel("Phone Number");
		phoneNumberLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		phoneNumberLabel.setBounds(52, 381, 141, 35);
		contentPane.add(phoneNumberLabel);
		
		textPhoneNumber = new JTextField();
		textPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textPhoneNumber.setColumns(10);
		textPhoneNumber.setBounds(195, 384, 283, 35);
		contentPane.add(textPhoneNumber);
		
		JLabel addressLabel = new JLabel("Address");
		addressLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		addressLabel.setBounds(114, 467, 80, 35);
		contentPane.add(addressLabel);
		
		textAddress = new JTextField();
		textAddress.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textAddress.setColumns(10);
		textAddress.setBounds(195, 447, 283, 84);
		contentPane.add(textAddress);
		
		JButton submitButton = new JButton("SUBMIT");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean usernameAvailable = true;
				String username = textUsername.getText();
				char[] password = passwordField.getPassword();
				String Password = new String(password);
				char[] validatePassword = textConfirmPassword.getPassword();
				String confirmPassword = new String(validatePassword);
				String name = textName.getText();
				String phoneNum = textPhoneNumber.getText();
				String address = textAddress.getText();
				
				user.setRole(1);
				user.setUsername(username);
				user.setPassword(Password);
				user.setConfirmPassword(confirmPassword);
				user.setName(name);
				user.setPhoneNum(phoneNum);
				user.setAddress(address);
				
				try {
					usernameAvailable = logRegController.isUsernameAvailable(user);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(user.getUsername().equals(""))
					JOptionPane.showMessageDialog(null, "Please enter your username.");
				
				else if(user.getPassword().equals(""))
					JOptionPane.showMessageDialog(null, "Please enter your password.");
				
				else if(user.getConfirmPassword().equals(""))
					JOptionPane.showMessageDialog(null, "Please enter your password confirmation.");
				
				else if(user.getName().equals(""))
					JOptionPane.showMessageDialog(null, "Please enter your name.");
				
				else if(user.getPhoneNum().equals(""))
					JOptionPane.showMessageDialog(null, "Please enter your phone number.");
				
				else if(user.getAddress().equals(""))
					JOptionPane.showMessageDialog(null, "Please enter your address.");
				
				else 
				{
					if(usernameAvailable == true && user.getPassword().equals(user.getConfirmPassword()))
					{
						try {
							logRegController.insertUser(user);
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						JOptionPane.showMessageDialog(null, "Your registration is successful.");
						LoginGui frame = new LoginGui();
						frame.setVisible(true);
						dispose();
					}
					
					else if(usernameAvailable == false)
					{
						JOptionPane.showMessageDialog(null, "The username you entered is already taken. Please enter other username.");
					}
					
					else
					{
						JOptionPane.showMessageDialog(null, "The password does not match with the password confirmation. Please try again.");
					}
				}
			}
		});
		submitButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		submitButton.setBounds(132, 559, 135, 50);
		contentPane.add(submitButton);
		
		JButton cancelButton = new JButton("CANCEL");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChooseRegisterGui frame = new ChooseRegisterGui();
				frame.setVisible(true);
				dispose();
			}
		});
		cancelButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		cancelButton.setBounds(294, 559, 135, 50);
		contentPane.add(cancelButton);
	}
}
