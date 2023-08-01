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

public class SupplierMenuGui extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SupplierMenuGui frame = new SupplierMenuGui();
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
	public SupplierMenuGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 628, 742);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel supplierMenuLabel = new JLabel("SUPPLIER MENU");
		supplierMenuLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		supplierMenuLabel.setBounds(181, 33, 254, 48);
		contentPane.add(supplierMenuLabel);
		
		JButton viewSupplyRequestsButton = new JButton("VIEW SUPPLY REQUESTS");
		viewSupplyRequestsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewSupplyGui frame = new ViewSupplyGui();
				frame.setVisible(true);
				dispose();
			}
		});
		viewSupplyRequestsButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		viewSupplyRequestsButton.setBounds(170, 181, 254, 48);
		contentPane.add(viewSupplyRequestsButton);
		
		JButton logOutButton = new JButton("LOG OUT");
		logOutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGui frame = new LoginGui();
				frame.setVisible(true);
				dispose();
			}
		});
		logOutButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		logOutButton.setBounds(170, 262, 254, 48);
		contentPane.add(logOutButton);
	}

}