package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import controller.LogAndRegController;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import model.User;

public class StaffRegisterGui extends JFrame {

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
					StaffRegisterGui frame = new StaffRegisterGui();
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
	public StaffRegisterGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 598, 679);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel staffRegistration = new JLabel("STAFF REGISTRATION");
		staffRegistration.setBounds(183, 31, 256, 55);
		staffRegistration.setFont(new Font("Tahoma", Font.BOLD, 22));
		contentPane.add(staffRegistration);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(102, 128, 105, 27);
		usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(usernameLabel);
		
		textUsername = new JTextField();
		textUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textUsername.setBounds(209, 127, 293, 34);
		contentPane.add(textUsername);
		textUsername.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		passwordLabel.setBounds(102, 191, 97, 27);
		contentPane.add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 18));
		passwordField.setBounds(209, 187, 293, 34);
		contentPane.add(passwordField);
		
		JLabel confirmPasswordLabel = new JLabel("Confirm Password");
		confirmPasswordLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		confirmPasswordLabel.setBounds(23, 249, 176, 27);
		contentPane.add(confirmPasswordLabel);
		
		textConfirmPassword = new JPasswordField();
		textConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
		textConfirmPassword.setBounds(209, 245, 293, 34);
		contentPane.add(textConfirmPassword);
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		nameLabel.setBounds(135, 300, 64, 27);
		contentPane.add(nameLabel);
		
		textName = new JTextField();
		textName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textName.setColumns(10);
		textName.setBounds(209, 299, 293, 34);
		contentPane.add(textName);
		
		JLabel phoneNumberLabel = new JLabel("Phone Number");
		phoneNumberLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		phoneNumberLabel.setBounds(54, 359, 145, 27);
		contentPane.add(phoneNumberLabel);
		
		textPhoneNumber = new JTextField();
		textPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textPhoneNumber.setColumns(10);
		textPhoneNumber.setBounds(209, 358, 293, 34);
		contentPane.add(textPhoneNumber);
		
		JLabel addressLabel = new JLabel("Address ");
		addressLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		addressLabel.setBounds(120, 442, 87, 27);
		contentPane.add(addressLabel);
		
		textAddress = new JTextField();
		textAddress.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textAddress.setColumns(10);
		textAddress.setBounds(209, 422, 293, 73);
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
				
				user.setRole(0);
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
		submitButton.setBounds(148, 536, 137, 49);
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
		cancelButton.setBounds(320, 536, 137, 49);
		contentPane.add(cancelButton);
	}
}
