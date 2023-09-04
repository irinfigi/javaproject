package Employee;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class AssignProjects extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AssignProjects frame = new AssignProjects();
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
	public AssignProjects() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1130, 825);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("   Assign Projects");
		lblNewLabel.setBackground(new Color(255, 255, 128));
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 45));
		lblNewLabel.setBounds(611, 47, 371, 60);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Employee Name :");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(611, 269, 206, 52);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(" Project Name :");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(611, 387, 184, 42);
		contentPane.add(lblNewLabel_2);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setBounds(827, 289, 147, 21);
		contentPane.add(comboBox);
		

		
		final JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(827, 402, 147, 21);
		contentPane.add(comboBox_1);
		
		try
		{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","HR","Irin_123");
            PreparedStatement stmt = conn.prepareStatement("SELECT e_name from emp");
            
            ResultSet rs= stmt.executeQuery();
            comboBox.addItem("");
            
            while(rs.next())
            {
            	String en= rs.getString("e_name");
            	comboBox.addItem(en);
            }
            rs.close();
            
            PreparedStatement stmt1 = conn.prepareStatement("SELECT pname from projs");
            
            ResultSet rs1= stmt1.executeQuery();
            comboBox_1.addItem("");
            
            while(rs1.next())
            {
            	String pn= rs1.getString("pname");
            	comboBox_1.addItem(pn);
            }
            rs1.close();       	
		
		
            
            
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
				{
					Class.forName("oracle.jdbc.driver.OracleDriver");
		            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","HR","Irin_123");
		 
				
				String enam,pnam,e1 = null,p = null;
				enam=comboBox.getSelectedItem().toString();
				pnam=comboBox_1.getSelectedItem().toString();
				PreparedStatement stmt2 = conn.prepareStatement("SELECT e_id from emp where e_name=?");
	            stmt2.setString(1,enam);
	            
	            ResultSet rs2= stmt2.executeQuery();
	            
				if(rs2.next())
				{
					e1=rs2.getString(1).toString();				
				}
				
				PreparedStatement stmt3 = conn.prepareStatement("SELECT pid from projs where pname=?");
	            stmt3.setString(1,pnam);
	            
	            ResultSet rs3= stmt3.executeQuery();
	            

				if(rs3.next())
				{
					p=rs3.getString(1).toString();				
				}
				
				System.out.println(e1+"  "+p);
				
				int x=Integer.parseInt(e1);
				
				PreparedStatement stmt4 = conn.prepareStatement("insert into pro_emp(pid,empid) values(?,?)");
				
				stmt4.setString(1, p);
				stmt4.setLong(2, x);
				int rs4=stmt2.executeUpdate();
				
				System.out.println("");
				
				if(rs4>0)
				{
					JOptionPane.showMessageDialog(null, "Employee Added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "something went wrong", "error", JOptionPane.INFORMATION_MESSAGE);
				}
				
				
				
				}
				catch(Exception e3)
				{
					e3.printStackTrace();
				}			
				
			
				
				
			}
				
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		btnNewButton.setBounds(941, 532, 110, 42);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new AdminLogin().setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		btnNewButton_1.setBounds(565, 536, 116, 39);
		contentPane.add(btnNewButton_1);
		
		}
		catch(Exception e2)
		{
			e2.printStackTrace();
		}
		
	}
}
