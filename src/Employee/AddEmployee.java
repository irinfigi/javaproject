package Employee;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class AddEmployee extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField address;
	private JTextField email;
	private JTextField job;
	private JTextField phone;
	private JPasswordField password;
	private JComboBox departments;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddEmployee frame = new AddEmployee();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void fillComboBox() {
		try {
			String query = "select * from dept";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","HR","Irin_123");
			Statement stmt = conn.createStatement();
			PreparedStatement pst =  conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				departments.addItem(rs.getString("dname"));
			}
		}catch(Exception e) {System.out.println(e);}
	}

	/**
	 * Create the frame.
	 */
	public AddEmployee() {
		departments = new JComboBox();
		departments.setBounds(340, 399, 196, 31);
		fillComboBox();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 711, 717);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(departments);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New Employee Details");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(164, 36, 372, 84);
		lblNewLabel.setFont(new Font("Times New Roman", Font.ITALIC, 33));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name :");
		lblNewLabel_1.setBounds(179, 175, 140, 31);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Address :");
		lblNewLabel_3.setBounds(179, 299, 109, 31);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Email Id :");
		lblNewLabel_4.setBounds(179, 216, 98, 26);
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Job Post :");
		lblNewLabel_5.setBounds(179, 350, 98, 31);
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_5);
		
		name = new JTextField();
		name.setBounds(340, 175, 191, 31);
		contentPane.add(name);
		name.setColumns(10);
		
		address = new JTextField();
		address.setBounds(340, 299, 191, 31);
		contentPane.add(address);
		address.setColumns(10);
		
		email = new JTextField();
		email.setBounds(340, 216, 191, 26);
		contentPane.add(email);
		email.setColumns(10);
		
		job = new JTextField();
		job.setBounds(340, 347, 191, 31);
		contentPane.add(job);
		job.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Phone :");
		lblNewLabel_9.setBounds(179, 258, 84, 31);
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_9);
		
		phone = new JTextField();
		phone.setBounds(340, 255, 191, 31);
		contentPane.add(phone);
		phone.setColumns(10);
		
		JLabel lblNewLabel_11_1 = new JLabel("Password");
		lblNewLabel_11_1.setBounds(179, 441, 109, 31);
		lblNewLabel_11_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_11_1);
		
		password = new JPasswordField();
		password.setBounds(340, 445, 191, 28);
		contentPane.add(password);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setBounds(296, 504, 109, 34);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String pass=new String(password.getPassword());
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","HR","Irin_123");
					Statement stmt = conn.createStatement();
					
					
					String dep = departments.getSelectedItem().toString();
					System.out.println(dep);
					ResultSet r = stmt.executeQuery("select dept_id from dept where dname='"+dep+"'");
					
					r.next();
					String did = r.getString(1);
					
					String query = "select count(e_id) from emp";
					ResultSet rs = stmt.executeQuery(query);
					rs.next();
					int uid = rs.getInt(1);
					int newuid =uid+1;
					
					
					
					
					
					String sql = "insert into emp values(?,?,?,?,?,?,?,?)";
					PreparedStatement ps = conn.prepareStatement(sql);
					System.out.println(sql);
					ps.setInt(1,newuid);
					ps.setString(2,name.getText());
					ps.setString(3,email.getText());
					ps.setLong(4,Long.parseLong(phone.getText()));
					ps.setString(5,address.getText());
					ps.setString(6,job.getText());
					ps.setString(7,did);
					ps.setString(8,password.getText());
					try {
						int i = ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "Employee Added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
					}
					catch(SQLException se) {
						System.out.println(se.getLocalizedMessage());
						se.printStackTrace();
					}
					catch(Exception e2){
						System.out.println(e2);
					}
				}
				catch(Exception e2) 
				{
						System.out.println(e2);
				}
				}

		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_12 = new JLabel("Department  :");
		lblNewLabel_12.setBounds(179, 391, 140, 31);
		lblNewLabel_12.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_12);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminLogin().setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnNewButton_1.setBounds(488, 504, 109, 34);
		contentPane.add(btnNewButton_1);
		

		
	}
}
