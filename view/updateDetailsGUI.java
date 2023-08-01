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

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import model.Product;
import controller.ProductController;

public class updateDetailsGUI extends JFrame {

	private JPanel contentPane;
	private JTextField IDField;
	private JTextField newNameField;
	private JTextField newPriceField;
	private JTextField newQuantityField;
	private JTextField newCatField;
	
	private ProductController productController = new ProductController();
	private Product product = new Product();
	private JTextField newSuppIDField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					updateDetailsGUI frame = new updateDetailsGUI();
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
	public updateDetailsGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 297, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblID = new JLabel("ID:");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblID.setBounds(20, 71, 30, 14);
		contentPane.add(lblID);
		
		IDField = new JTextField();
		IDField.setBounds(177, 69, 96, 20);
		contentPane.add(IDField);
		IDField.setColumns(10);
		
		JLabel lblName = new JLabel("New Product Name :");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblName.setBounds(20, 99, 122, 20);
		contentPane.add(lblName);
		
		JLabel lblPrice = new JLabel("New Price :");
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPrice.setBounds(19, 130, 96, 20);
		contentPane.add(lblPrice);
		
		JLabel lblQuantity = new JLabel("New Quantity :");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblQuantity.setBounds(19, 166, 113, 20);
		contentPane.add(lblQuantity);
		
		newNameField = new JTextField();
		newNameField.setColumns(10);
		newNameField.setBounds(177, 100, 96, 20);
		contentPane.add(newNameField);
		
		newPriceField = new JTextField();
		newPriceField.setColumns(10);
		newPriceField.setBounds(177, 131, 96, 20);
		contentPane.add(newPriceField);
		
		newQuantityField = new JTextField();
		newQuantityField.setColumns(10);
		newQuantityField.setBounds(177, 167, 96, 20);
		contentPane.add(newQuantityField);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean productDoesntExist = true;
				boolean data = true;
				String productName = newNameField.getText();
				String productPrice = newPriceField.getText();
				String productQuantity = newQuantityField.getText();
				String supplierID = newSuppIDField.getText();
				String productID = IDField.getText();
				String catID = newCatField.getText();
				
				product.setProductName(productName);
				product.setProductPrice(productPrice);
				product.setProductStock(productQuantity);
				product.setProductID(productID);
				product.setCatID(catID);
				product.setSupplierID(supplierID);
				
				
				if(product.getProductID().equals(""))
					JOptionPane.showMessageDialog(null, "Please enter your product ID.");
				
				else if(product.getProductName().equals(""))
					JOptionPane.showMessageDialog(null, "Please enter your product name.");
				
				else if(product.getProductPrice().equals(""))
					JOptionPane.showMessageDialog(null, "Please enter your product price.");
				
				else if(product.getProductStock().equals(""))
					JOptionPane.showMessageDialog(null, "Please enter your product stock.");
				
				else if(product.getCatID().equals(""))
					JOptionPane.showMessageDialog(null, "Please choose your category ID.");
				
				else if(product.getSupplierID().equals(""))
					JOptionPane.showMessageDialog(null, "Please enter your Supplier ID.");				
				else
				{
					
					try {
						productDoesntExist = productController.isProductAvailable(product);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					try {
						data = productController.checkIfProductExist(product);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					if(data == true)
					{
					
						if(productDoesntExist == true)
						{
							try {
								productController.updateProduct(product);
							} catch (ClassNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						
							JOptionPane.showMessageDialog(null, "Your product update is successful.");
							dispose();
						}
					
						else if(productDoesntExist == false)
						{
							JOptionPane.showMessageDialog(null, "The product is already found on the system. Please enter other product.");
							
						}
					}
					
					else
						JOptionPane.showMessageDialog(null, "Invalid product ID. Please try again.");
					
				}
			}
		});
		
		btnOk.setBounds(147, 266, 89, 23);
		contentPane.add(btnOk);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(43, 266, 89, 23);
		contentPane.add(btnCancel);
		
		JLabel lblNewLabel_1 = new JLabel("Update Details");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(85, 11, 123, 37);
		contentPane.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String selectedValue = comboBox.getSelectedItem().toString();
				
				if (selectedValue == "Winter")
					newCatField.setText("1");
				else if (selectedValue == "Summer")
					newCatField.setText("2");
				else if (selectedValue == "Autumn")
					newCatField.setText("3");
				else if (selectedValue == "Spring")
					newCatField.setText("4");
			}
		});
		
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Winter", "Summer", "Spring", "Autumn"}));
		comboBox.setBounds(19, 197, 96, 20);
		contentPane.add(comboBox);
		
		newCatField = new JTextField();
		newCatField.setEditable(false);
		newCatField.setBounds(177, 198, 96, 20);
		contentPane.add(newCatField);
		newCatField.setColumns(10);
		
		JLabel newSuppID = new JLabel("New Supplier ID :");
		newSuppID.setFont(new Font("Tahoma", Font.PLAIN, 13));
		newSuppID.setBounds(20, 235, 113, 20);
		contentPane.add(newSuppID);
		
		newSuppIDField = new JTextField();
		newSuppIDField.setColumns(10);
		newSuppIDField.setBounds(177, 235, 96, 20);
		contentPane.add(newSuppIDField);
	}
}
