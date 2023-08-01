package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controller.ViewCostController;
import model.RestockProduct;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class ViewCostGui extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	private ViewCostController viewcostController = new ViewCostController();
	private RestockProduct resproduct = new RestockProduct();
	private JTextField textFieldTotalCost;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewCostGui frame = new ViewCostGui();
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
	public ViewCostGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 752, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMonthlyCost = new JLabel("View Total Restock Cost");
		lblMonthlyCost.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblMonthlyCost.setBounds(240, 28, 299, 41);
		contentPane.add(lblMonthlyCost);
		
		JScrollPane scrollPaneListAccept = new JScrollPane();
		scrollPaneListAccept.setBounds(98, 97, 567, 145);
		contentPane.add(scrollPaneListAccept);
		
		//table
		table = new JTable();
		scrollPaneListAccept.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
				}
			));
		
		JLabel lblTotalCost = new JLabel("Total Cost");
		lblTotalCost.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTotalCost.setBounds(229, 289, 65, 41);
		contentPane.add(lblTotalCost);
		
		//display all list
		JButton btnViewList = new JButton("View List");
		btnViewList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double totalCost=0;
				try {
					table.setModel(DbUtils.resultSetToTableModel(viewcostController.viewAll(resproduct)));
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					totalCost= viewcostController.rptotalPrice();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textFieldTotalCost.setText(Double.toString(totalCost));
			}
		});
		btnViewList.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnViewList.setBounds(570, 66, 95, 31);
		contentPane.add(btnViewList);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffMenuGui frame = new StaffMenuGui();
		    	frame.setVisible(true);
		    	dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnBack.setBounds(559, 383, 91, 34);
		contentPane.add(btnBack);
		
		textFieldTotalCost = new JTextField();
		textFieldTotalCost.setEditable(false);
		textFieldTotalCost.setBounds(304, 291, 160, 41);
		contentPane.add(textFieldTotalCost);
		textFieldTotalCost.setColumns(10);
	}
}
