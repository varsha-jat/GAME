package Project;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;

public class PUZZ {

	private JFrame frame;
	private JTextField textField;
	String answer;
	String yourAnswer;
	String paheli;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PUZZ window = new PUZZ();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PUZZ() {
		initialize();
	}

	/*PUZZLE/img/download.jpeg
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1900, 1080);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		ImageIcon img= new ImageIcon(this.getClass().getResource("/jeremy-bishop-xaZSE0h7yIY-unsplash.jpg"));
		
		JInternalFrame internalFrame = new JInternalFrame("PAHELIYA");
		internalFrame.setBounds(375, 150, 600, 450);
		frame.getContentPane().add(internalFrame);
		internalFrame.getContentPane().setLayout(null);
		
		JButton btnNewButton_1 = new JButton("QUIT");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setBounds(400, 340, 100, 30);
		internalFrame.getContentPane().add(btnNewButton_1);
		
		try {
			Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/puzzle","root","root");
			System.out.println("connection successfull!");
			String query ="SELECT * from paheliya";
			PreparedStatement ps= conn.prepareStatement(query);
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				//System.out.println(rs.getString("paheli"));
				paheli=rs.getString("paheli");
				answer=rs.getString("answer");
			}
		}catch(Exception e1) {
			System.out.println(e1.getMessage());
		}
		
		JLabel lblNewLabel_1 = new JLabel(paheli);
		lblNewLabel_1.setBounds(40, 40, 500, 200);
		internalFrame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(220, 280, 180, 25);
		internalFrame.getContentPane().add(textField);
		textField.setColumns(10);
		
		yourAnswer=textField.getText();
		
		JButton btnNewButton = new JButton("SUBMIT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(answer.equals(yourAnswer)) {
					initialize();
				}else {
					JOptionPane.showMessageDialog(btnNewButton, "Wrong Answer");
				}
			}
			});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(260, 340, 100, 30);
		internalFrame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Type here");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(100, 282, 100, 20);
		internalFrame.getContentPane().add(lblNewLabel_2);
		internalFrame.setVisible(true);
//		 JLabel label = new JLabel("Hello, Swing!");
//	        JTextField textField = new JTextField(15);
//	        JButton button = new JButton("Click Me!");
//
//	        // Add the components to the panel
//	        Jpanel.add(label);
//	        panel.add(textField);
//	        panel.add(button);
		lblNewLabel.setIcon(img);
		lblNewLabel.setBounds(0, 0, 1380, 750);
		frame.getContentPane().add(lblNewLabel);
		
		
//		JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");
//		internalFrame.setBounds(0, 0, 1370, 749);
//		frame.getContentPane().add(internalFrame);
//		internalFrame.getContentPane().setLayout(null);
//		internalFrame.setVisible(true);
	}
}
