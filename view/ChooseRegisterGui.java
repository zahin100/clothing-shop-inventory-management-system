package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChooseRegisterGui extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChooseRegisterGui frame = new ChooseRegisterGui();
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
	public ChooseRegisterGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 742, 644);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel registerLabel = new JLabel("Please choose whether you want to register as staff or supplier");
		registerLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		registerLabel.setBounds(95, 62, 543, 79);
		contentPane.add(registerLabel);
		
		JButton registerAsStaff = new JButton("STAFF");
		registerAsStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StaffRegisterGui frame = new StaffRegisterGui();
				frame.setVisible(true);
				dispose();
			}
		});
		registerAsStaff.setFont(new Font("Tahoma", Font.BOLD, 22));
		registerAsStaff.setBounds(197, 170, 288, 79);
		contentPane.add(registerAsStaff);
		
		JButton registerAsSupplier = new JButton("SUPPLIER");
		registerAsSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SupplierRegisterGui frame = new SupplierRegisterGui();
				frame.setVisible(true);
				dispose();
			}
		});
		registerAsSupplier.setFont(new Font("Tahoma", Font.BOLD, 22));
		registerAsSupplier.setBounds(197, 289, 288, 84);
		contentPane.add(registerAsSupplier);
		
		JButton backToLogin = new JButton("BACK");
		backToLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGui frame = new LoginGui();
				frame.setVisible(true);
				dispose();
			}
		});
		backToLogin.setFont(new Font("Tahoma", Font.BOLD, 22));
		backToLogin.setBounds(197, 415, 288, 84);
		contentPane.add(backToLogin);
	}
}
