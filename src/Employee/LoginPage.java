package Employee;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class LoginPage extends JFrame {

	private JPanel contentPane;
	private JTextField email;
	private JPasswordField password;
	protected Window frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
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
	public LoginPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 936, 893);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 128));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("USERNAME :");
		lblNewLabel.setBounds(553, 262, 100, 33);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 13));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("   PASSWORD :");
		lblNewLabel_1.setBounds(553, 385, 100, 23);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		contentPane.add(lblNewLabel_1);
		
		email = new JTextField();
		email.setBounds(785, 268, 137, 19);
		contentPane.add(email);
		email.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("   LOGIN PAGE");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2.setBounds(666, 169, 112, 23);
		contentPane.add(lblNewLabel_2);
		
		password = new JPasswordField();
		password.setBounds(785, 387, 137, 19);
		contentPane.add(password);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","HR","Irin_123");
					Statement stmt = conn.createStatement();
					String query = "select * from admin where a_email='"+email.getText()+"' and a_pwd = '"+password.getText()+"' ";
					ResultSet rs = stmt.executeQuery(query);
					if(rs.next()) {
						this.setVisible(false);
						JOptionPane.showMessageDialog(null, "Login Successful");
						AdminLogin al = new AdminLogin();
						al.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Login Unsuccessful");
					}
				}catch(Exception e1) {
					System.out.println(e1);
				}
			}

			private void setVisible(boolean b) {
				// TODO Auto-generated method stub
				
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnNewButton.setBounds(568, 520, 85, 33);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EmployeFrontPage().setVisible(true);
				
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnNewButton_1.setBounds(796, 520, 85, 33);
		contentPane.add(btnNewButton_1);
		
		
	}
}
