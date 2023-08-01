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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;

public class LoginGui extends JFrame {

	private JPanel contentPane;
	private JTextField textUsername;
	private JPasswordField passwordField;
	private LogAndRegController logRegController = new LogAndRegController();
	public User user = new User();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGui frame = new LoginGui();
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
	public LoginGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 664);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel LoginUsername = new JLabel("Username");
		LoginUsername.setFont(new Font("Tahoma", Font.BOLD, 22));
		LoginUsername.setBounds(149, 134, 272, 49);
		contentPane.add(LoginUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblPassword.setBounds(149, 241, 272, 49);
		contentPane.add(lblPassword);
		
		textUsername = new JTextField();
		textUsername.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textUsername.setBounds(149, 188, 277, 42);
		contentPane.add(textUsername);
		textUsername.setColumns(10);
		
		JButton loginButton = new JButton("LOGIN");
		loginButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean data = true;
				String username = textUsername.getText();
				char[] password = passwordField.getPassword();
				String Password = new String(password);
				
				user.setUsername(username);
				user.setPassword(Password);
				
				try {
					data = logRegController.validateLoginData(user);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(user.getUsername().equals(""))
					JOptionPane.showMessageDialog(null, "Please enter your username.");
				
				else if(user.getPassword().equals(""))
					JOptionPane.showMessageDialog(null, "Please enter your password.");
				
				else
				{
					if(data == true)
					{
						if(user.getRole() == 0)
						{
							StaffMenuGui frame = new StaffMenuGui();
							frame.setVisible(true);
							dispose();
						}
						
						else
						{
							SupplierMenuGui frame = new SupplierMenuGui();
							frame.setVisible(true);
							dispose();
						}
					}
					
					else
					{
						JOptionPane.showMessageDialog(null, "Incorrect username or password.");
					}
				}
				
				
				
			}
		});
		loginButton.setBounds(168, 394, 221, 49);
		contentPane.add(loginButton);
		
		JButton registerButton = new JButton("REGISTER");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChooseRegisterGui frame = new ChooseRegisterGui();
				frame.setVisible(true);
				dispose();
			}
		});
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		registerButton.setBounds(168, 468, 221, 49);
		contentPane.add(registerButton);
		
		JButton exitButton = new JButton("EXIT");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		exitButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		exitButton.setBounds(168, 546, 221, 49);
		contentPane.add(exitButton);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 22));
		passwordField.setBounds(149, 291, 277, 42);
		contentPane.add(passwordField);
		
		JLabel projectTitle = new JLabel("CLOTHING SHOP INVENTORY MANAGEMENT SYSTEM");
		projectTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		projectTitle.setBounds(47, 69, 508, 42);
		contentPane.add(projectTitle);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(126, 361, 318, 2);
		contentPane.add(separator);
	}
}
