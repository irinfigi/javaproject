package Employee;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;

public class AdminLogin extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogin frame = new AdminLogin();
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
	public AdminLogin() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1114, 833);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("EMPLOYEE DASHBOARD");
		lblNewLabel.setBackground(new Color(255, 255, 128));
		lblNewLabel.setBounds(519, 151, 395, 70);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 33));
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Add Employee");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddEmployee ae = new AddEmployee();
				ae.setVisible(true);
			}
		});
		btnNewButton.setBounds(282, 271, 170, 58);
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("View Employees");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewEmployee ve = new ViewEmployee();
				ve.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(905, 272, 185, 57);
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Remove Employee");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveEmployee re = new RemoveEmployee();
				re.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(282, 428, 199, 58);
		btnNewButton_2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_4 = new JButton("EXIT");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginPage().setVisible(true);
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_4.setBounds(693, 565, 85, 58);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_3 = new JButton("Assign Projects");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AssignProjects().setVisible(true);
			}
		});
		btnNewButton_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton_3.setBounds(905, 438, 170, 49);
		contentPane.add(btnNewButton_3);
	}
}
