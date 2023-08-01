package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import model.Product;
import net.proteanit.sql.DbUtils;
import controller.ProductController;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;

public class ProductGui extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField quantityField;
	private JTextField priceField;
	private JTextField nameField;

	private ProductController productController = new ProductController();
	private Product product = new Product();
	private JTextField searchField;
	private JTextField catIDField;
	private JTextField supplierIDField;
	private JTextField prodIDField;
	private JTextField IDDeleteField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductGui frame = new ProductGui();
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
	public ProductGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 932, 566);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MANAGE PRODUCT");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(341, 20, 242, 51);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(341, 117, 556, 254);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		
		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setBounds(40, 173, 63, 20);
		contentPane.add(lblName);
		
		JLabel lblPrice = new JLabel("Price :");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrice.setBounds(40, 215, 63, 20);
		contentPane.add(lblPrice);
		
		JLabel lblQuantity = new JLabel("Quantity :");
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblQuantity.setBounds(40, 259, 80, 20);
		contentPane.add(lblQuantity);
		
		quantityField = new JTextField();
		quantityField.setBounds(170, 262, 150, 20);
		contentPane.add(quantityField);
		quantityField.setColumns(10);
		
		priceField = new JTextField();
		priceField.setColumns(10);
		priceField.setBounds(170, 218, 150, 20);
		contentPane.add(priceField);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(170, 176, 150, 20);
		contentPane.add(nameField);
		
		JButton btnFetch = new JButton("View All");
		btnFetch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					table.setModel(DbUtils.resultSetToTableModel(productController.viewAll(product)));
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnFetch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFetch.setBounds(592, 384, 132, 21);
		contentPane.add(btnFetch);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffMenuGui frame = new StaffMenuGui();
		    	frame.setVisible(true);
		    	dispose();
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExit.setBounds(119, 497, 132, 21);
		contentPane.add(btnExit);
		
		JButton btnAddProduct = new JButton("Add Product");
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean productAvailable = true;
				boolean productID_alreadyExists = true;
				
				String productName = nameField.getText();
				String productPrice = priceField.getText();
				String productQuantity = quantityField.getText();
				String supplierID = supplierIDField.getText();
				String productID = prodIDField.getText();
				String catID = catIDField.getText();
				
				product.setProductName(productName);
				product.setProductPrice(productPrice);
				product.setProductStock(productQuantity);
				product.setProductID(productID);
				product.setCatID(catID);
				product.setSupplierID(supplierID);
								
				try {
					productAvailable = productController.isProductAvailable(product);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					productID_alreadyExists = productController.checkIfProductExist(product);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	
				if(product.getProductName().equals(""))
					JOptionPane.showMessageDialog(null, "Please enter your product name.");
				
				else if(product.getProductPrice().equals(""))
					JOptionPane.showMessageDialog(null, "Please enter your product price.");
				
				else if(product.getProductStock().equals(""))
					JOptionPane.showMessageDialog(null, "Please enter your product stock.");
				
				else if(product.getProductID().equals(""))
					JOptionPane.showMessageDialog(null, "Please enter your product ID.");
				
				else if(product.getCatID().equals(""))
					JOptionPane.showMessageDialog(null, "Please enter your category ID.");
				
				else if(product.getSupplierID().equals(""))
					JOptionPane.showMessageDialog(null, "Please enter your Supplier ID.");
				
				else
				{
					if(productID_alreadyExists == false)
					{
						if(productAvailable == true)
						{
							try {
								productController.insertProduct(product);
							} catch (ClassNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						
							JOptionPane.showMessageDialog(null, "Your product insertion is successful.");
							ProductGui frame = new ProductGui();
							frame.setVisible(true);
							dispose();
							
						}
					
						else if(productAvailable == false)
						{
							JOptionPane.showMessageDialog(null, "The product you entered is already in the system. Please enter other product.");
							
						}
					}
					
					else
					{
						JOptionPane.showMessageDialog(null, "The product ID is already exists. Please enter other product ID.");
					}
				}
			}
		});
		btnAddProduct.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddProduct.setBounds(119, 398, 132, 21);
		contentPane.add(btnAddProduct);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String searchProduct = searchField.getText();
				product.setProductName(searchProduct);
				
				try {
					table.setModel(DbUtils.resultSetToTableModel(productController.findProduct(product)));
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSearch.setBounds(765, 86, 132, 21);
		contentPane.add(btnSearch);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String productID = IDDeleteField.getText();
				boolean data = true;
				
				product.setProductID(productID);
				
				if(product.getProductID().equals(""))
					JOptionPane.showMessageDialog(null, "Please enter the product ID.");
				else 
				{
					try {
						 data = productController.checkIfProductExist(product);
					} catch (ClassNotFoundException | SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					if(data == true)
					{
						try {
							productController.deleteProduct(product);
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					
						JOptionPane.showMessageDialog(null, "Your product deletion is successful.");
						ProductGui frame = new ProductGui();
						frame.setVisible(true);
						dispose();
					}
					
					else
					{
						JOptionPane.showMessageDialog(null, "Invalid product ID. Please try again.");
					}
				}
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDelete.setBounds(697, 448, 96, 21);
		contentPane.add(btnDelete);
		
		searchField = new JTextField();
		searchField.setColumns(10);
		searchField.setBounds(617, 86, 138, 20);
		contentPane.add(searchField);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateDetailsGUI frame = new updateDetailsGUI();
				frame.setVisible(true);
			}
		});
		
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnUpdate.setBounds(119, 450, 132, 21);
		contentPane.add(btnUpdate);
		
		JLabel lblSupplierID = new JLabel("SupplierID :");
		lblSupplierID.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSupplierID.setBounds(40, 351, 96, 20);
		contentPane.add(lblSupplierID);
		
		catIDField = new JTextField();
		catIDField.setEditable(false);
		catIDField.setColumns(10);
		catIDField.setBounds(276, 307, 44, 20);
		contentPane.add(catIDField);
		
		JLabel lblCategoryId = new JLabel("Category ID : ");
		lblCategoryId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCategoryId.setBounds(40, 304, 102, 20);
		contentPane.add(lblCategoryId);
		
		supplierIDField = new JTextField();
		supplierIDField.setColumns(10);
		supplierIDField.setBounds(170, 354, 150, 20);
		contentPane.add(supplierIDField);
		
		JLabel lblProductID = new JLabel("ID : ");
		lblProductID.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProductID.setBounds(40, 134, 63, 20);
		contentPane.add(lblProductID);
		
		prodIDField = new JTextField();
		prodIDField.setColumns(10);
		prodIDField.setBounds(170, 137, 150, 20);
		contentPane.add(prodIDField);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String selectedValue = comboBox.getSelectedItem().toString();
				
				if (selectedValue == "Winter")
					catIDField.setText("1");
				else if (selectedValue == "Summer")
					catIDField.setText("2");
				else if (selectedValue == "Autumn")
					catIDField.setText("3");
				else if (selectedValue == "Spring")
					catIDField.setText("4");
			}
		});
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Winter", "Summer", "Spring", "Autumn"}));
		comboBox.setBounds(170, 306, 96, 21);
		contentPane.add(comboBox);
		
		IDDeleteField = new JTextField();
		IDDeleteField.setBounds(576, 450, 96, 20);
		contentPane.add(IDDeleteField);
		IDDeleteField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Delete product by product ID:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(384, 444, 199, 29);
		contentPane.add(lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(35, 115, 296, 39);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(35, 430, 296, 39);
		contentPane.add(separator_1);
	}
}
