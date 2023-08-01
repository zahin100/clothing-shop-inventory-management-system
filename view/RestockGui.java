package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

import model.Product;
import model.Restock;
import model.RestockProduct;
import model.User;

import controller.RestockController;
import controller.RestockProductController;
import controller.ProductController;

public class RestockGui extends JFrame {

	private JPanel contentPane;
	private JTextField textProductID;
	private JTextField textQuantity;
	private Product product = new Product();
	private Restock restock = new Restock();
	private User user = new User();
	private RestockController restController = new RestockController();
	private ProductController Pcontrol = new ProductController();
	private RestockProductController resproController = new RestockProductController();
	private RestockProduct resProduct = new RestockProduct();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RestockGui frame = new RestockGui();
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
	public RestockGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 586, 324);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRestock = new JLabel("Restock ");
		lblRestock.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblRestock.setBounds(236, 0, 91, 46);
		contentPane.add(lblRestock);
		
		textProductID = new JTextField();
		textProductID.setBackground(new Color(250, 250, 210));
		textProductID.setBounds(275, 64, 169, 39);
		contentPane.add(textProductID);
		textProductID.setColumns(10);
		
		JButton btnSubmitPID = new JButton("Submit");
		btnSubmitPID.setBackground(new Color(224, 255, 255));
		btnSubmitPID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSubmitPID.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		        
		        boolean ProductIDAvailable = true;
		        String ProductID = textProductID.getText();
		        product.setProductID(ProductID);
		        
		        String rpQuantity = textQuantity.getText();
		        resProduct.setRpQuantity(rpQuantity);
		        
		        try {
		        	ProductIDAvailable = restController.isProductAvailable(product);
		        } catch (ClassNotFoundException | SQLException e1) {
		          // TODO Auto-generated catch block
		          e1.printStackTrace();
		        }
		  
		        if(product.getProductID().equals(""))
		          JOptionPane.showMessageDialog(null, "Please enter your product ID.");
		        
		        else if(resProduct.getRpQuantity().equals(""))
			          JOptionPane.showMessageDialog(null, "Please enter Quantity.");
		        
		        else if(ProductIDAvailable == true)
		          {
		            JOptionPane.showMessageDialog(null, "Please enter Correct product ID.");
		          }
		        
		        else
		        {
			          if(ProductIDAvailable == false)
			          {
			            JOptionPane.showMessageDialog(null, "Product ID Found!!");
			          }
			        
			        
			        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			        LocalDateTime now = LocalDateTime.now();  
			          
			        boolean IDAvailable = true;
			       
			        
			        try {
			        	resproController.totalPrice(resProduct,product);
			        	
			        } catch (ClassNotFoundException | SQLException e1) {
			          // TODO Auto-generated catch block
			          e1.printStackTrace();
			        }
			        int input = JOptionPane.showConfirmDialog(null, 
			                "The Total Price is RM" + resProduct.getRpTotalPrice() + "\nDo you want to Continue?", "Select an Option...",JOptionPane.YES_NO_OPTION);
			        if(input == 0)
			        {
			        	restock.setRestockDate(dtf.format(now));
				        resProduct.setRpStatus("pending");
				        resProduct.setRpStatus("pending");
				        
				        
				        try {
				        	IDAvailable = resproController.isUsernameAvailable(resProduct);
				        } catch (ClassNotFoundException | SQLException e1) {
				          // TODO Auto-generated catch block
				          e1.printStackTrace();
				        }
				  
				       
				          if(IDAvailable == true )
				          {
				            try {
				            	restController.insertRestock(restock);
				            	
				            } catch (ClassNotFoundException | SQLException e1) {
				              // TODO Auto-generated catch block
				              e1.printStackTrace();
				            }
				            
				            
				            try {
								resproController.insertRestock(resProduct,product);
							} catch (ClassNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				            
				            
				            try {
								resproController.updateProductStock(Integer.parseInt(product.getProductID()), Integer.parseInt(resProduct.getRpQuantity()));
							} catch (ClassNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				            
				            JOptionPane.showMessageDialog(null, "Restock is successful.");
				            StaffMenuGui frame = new StaffMenuGui();
				            frame.setVisible(true);
				            dispose();
				          }
				        
				          else if(IDAvailable == false)
				          {
				        	  
				            JOptionPane.showMessageDialog(null, "The Restock Product ID entered is already taken. Please enter another.");
				          }
				        
			        }
		        }
		      }
		    });
		btnSubmitPID.setBounds(223, 164, 138, 39);
		contentPane.add(btnSubmitPID);
		
		JLabel lblProductID = new JLabel("Product ID :");
		lblProductID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblProductID.setBounds(127, 68, 108, 27);
		contentPane.add(lblProductID);
		
		textQuantity = new JTextField();
		textQuantity.setBackground(new Color(250, 250, 210));
		textQuantity.setBounds(275, 114, 169, 39);
		contentPane.add(textQuantity);
		textQuantity.setColumns(10);
		
		JLabel lblQuantity = new JLabel("Quantity :");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblQuantity.setBounds(127, 123, 91, 17);
		contentPane.add(lblQuantity);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setBackground(new Color(224, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffMenuGui frame = new StaffMenuGui();
				frame.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(223, 215, 138, 39);
		contentPane.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(53, 49, 464, 225);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(211, 211, 211));
		panel_1.setBounds(216, 11, 128, 27);
		contentPane.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 248, 255));
		panel_2.setBounds(0, 0, 570, 285);
		contentPane.add(panel_2);
	}
}
