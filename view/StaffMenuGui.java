package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StaffMenuGui extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffMenuGui frame = new StaffMenuGui();
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
	public StaffMenuGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 679, 714);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel staffMenuLabel = new JLabel("STAFF MENU");
		staffMenuLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		staffMenuLabel.setBounds(240, 11, 188, 80);
		contentPane.add(staffMenuLabel);
		
		JButton restockButton = new JButton("RESTOCK ITEM");
		restockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RestockGui frame = new RestockGui();
				frame.setVisible(true);
				dispose();
			}
		});
		restockButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		restockButton.setBounds(215, 118, 238, 47);
		contentPane.add(restockButton);
		
		JButton viewMonthlyCostButton = new JButton("VIEW MONTHLY COST");
		viewMonthlyCostButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewCostGui frame = new ViewCostGui();
				frame.setVisible(true);
				dispose();
			}
		});
		viewMonthlyCostButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		viewMonthlyCostButton.setBounds(215, 258, 238, 47);
		contentPane.add(viewMonthlyCostButton);
		
		JButton addNewItemButton = new JButton("MANAGE PRODUCTS");
		addNewItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductGui frame = new ProductGui();
				frame.setVisible(true);
				dispose();
			}
		});
		addNewItemButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		addNewItemButton.setBounds(215, 186, 238, 47);
		contentPane.add(addNewItemButton);
		
		JButton logOutButton = new JButton("LOG OUT");
		logOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGui frame = new LoginGui();
				frame.setVisible(true);
				dispose();
			}
		});
		logOutButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		logOutButton.setBounds(215, 337, 238, 47);
		contentPane.add(logOutButton);
	}
}