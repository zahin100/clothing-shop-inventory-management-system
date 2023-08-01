package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.ProductController;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import model.RestockProduct;
import net.proteanit.sql.DbUtils;
import controller.ViewSupplyController;

import javax.swing.JTable;

public class ViewSupplyGui extends JFrame {

	private JPanel contentPane;
	private JTextField rpIDfield;
	private JTable table;
	
	private ViewSupplyController viewSupplyController = new ViewSupplyController();
	private RestockProduct resproduct = new RestockProduct();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewSupplyGui frame = new ViewSupplyGui();
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
	public ViewSupplyGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 732, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//main title
		JLabel lblNewLabel = new JLabel("Stock Request");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(294, 10, 203, 48);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPaneSupplyList = new JScrollPane();
		scrollPaneSupplyList.setBounds(81, 68, 569, 149);
		contentPane.add(scrollPaneSupplyList);
		
		//table
		table = new JTable();
		scrollPaneSupplyList.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
				}
			));
		
		//display all pending list
		JButton btnViewList = new JButton("View List");
		btnViewList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					table.setModel(DbUtils.resultSetToTableModel(viewSupplyController.viewEverything(resproduct)));
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnViewList.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnViewList.setBounds(536, 42, 114, 25);
		contentPane.add(btnViewList);
		
		JLabel lblRpID = new JLabel("Enter RpID");
		lblRpID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRpID.setBounds(217, 230, 70, 34);
		contentPane.add(lblRpID);
		
		rpIDfield = new JTextField();
		rpIDfield.setBounds(297, 230, 148, 34);
		contentPane.add(rpIDfield);
		rpIDfield.setColumns(10);
		
		JButton btnAcceptReq = new JButton("Accept");
		btnAcceptReq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean productAvailable = true;
				String rpID = rpIDfield.getText();
				
				resproduct.setRpID(rpID);
				
				try {
					productAvailable = viewSupplyController.checkResProduct(resproduct);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(resproduct.getRpID().equals(""))
					JOptionPane.showMessageDialog(null, "Please enter the rpID.");
				
				else
				{
					if(productAvailable == false)
					{
						String acceptrpID = rpIDfield.getText();
						resproduct.setRpID(acceptrpID);
						try {
							viewSupplyController.updateStatusAccept(resproduct);
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						JOptionPane.showMessageDialog(null, "The stock request has been accepted");
						ViewSupplyGui frame = new ViewSupplyGui();
						frame.setVisible(true);
						dispose();
						
					}
				
					else if(productAvailable == true)
					{
						JOptionPane.showMessageDialog(null, "The rpID is invalid.");
						
					}

				}
			}
		});
		btnAcceptReq.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAcceptReq.setBounds(330, 296, 91, 34);
		contentPane.add(btnAcceptReq);
		
		JButton btnDecline = new JButton("Decline");
		btnDecline.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean productsAvailable = true;
				String rpsID = rpIDfield.getText();
				
				resproduct.setRpID(rpsID);
				
				try {
					productsAvailable = viewSupplyController.checkResProduct(resproduct);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(resproduct.getRpID().equals(""))
					JOptionPane.showMessageDialog(null, "Please enter the rpID.");
				
				else
				{
					if(productsAvailable == false)
					{
						String declinerpID = rpIDfield.getText();
						resproduct.setRpID(declinerpID);
						try {
							viewSupplyController.updateStatusDeclined(resproduct);
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "The stock request has been declined");
						ViewSupplyGui frame = new ViewSupplyGui();
						frame.setVisible(true);
						dispose();
						
					}
				
					else if(productsAvailable == true)
					{
						JOptionPane.showMessageDialog(null, "The rpID is invalid.");
						
					}
				}
			}
		});
		btnDecline.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDecline.setBounds(330, 340, 91, 34);
		contentPane.add(btnDecline);
		
		//button back to menu
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SupplierMenuGui frame = new SupplierMenuGui();
		    	frame.setVisible(true);
		    	dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnBack.setBounds(559, 383, 91, 34);
		contentPane.add(btnBack);
	}
}
