package ui.systemMaintain;

import javax.swing.ButtonGroup;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sql.sqlConnect;
import userVerify.userVerify;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class StaffInfo extends JInternalFrame {

	private JPanel contentPane;
	private JTextField staffInput;
	private JTextField nameInput;
	private JRadioButton male;
	private JTextField contactInput;
	private JTextField passwordInput;
	private JRadioButton famale;
	String staff;


	/**
	 * Create the frame.
	 */
	public StaffInfo() {
		staff=userVerify.getCurrentStaff();
		setTitle("\u7528\u6237\u4FE1\u606F");
		this.setClosable(true);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 585, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel staff = new JLabel("\u7528\u6237\u7F16\u53F7\uFF1A");
		staff.setBounds(65, 36, 80, 19);
		staff.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(staff);
		
		staffInput = new JTextField();
		staffInput.setEditable(false);
		staffInput.setBounds(150, 35, 97, 21);
		contentPane.add(staffInput);
		staffInput.setColumns(10);
		
		JLabel name = new JLabel("\u7528\u6237\u59D3\u540D\uFF1A");
		name.setBounds(312, 36, 80, 19);
		name.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(name);
		
		nameInput = new JTextField();
		nameInput.setEditable(false);
		nameInput.setBounds(397, 35, 97, 21);
		contentPane.add(nameInput);
		nameInput.setColumns(10);
		
		contactInput = new JTextField();
		contactInput.setEditable(false);
		contactInput.setBounds(397, 96, 97, 21);
		contentPane.add(contactInput);
		contactInput.setColumns(10);
		
		JPanel sexPanel = new JPanel();
		sexPanel.setBounds(65, 87, 182, 37);
		contentPane.add(sexPanel);
		
		JLabel sex = new JLabel("\u7528\u6237\u6027\u522B\uFF1A");
		sexPanel.add(sex);
		sex.setFont(new Font("楷体", Font.PLAIN, 16));
		
		male = new JRadioButton("\u7537");
		sexPanel.add(male);
		male.setFont(new Font("楷体", Font.PLAIN, 16));
		
		 famale = new JRadioButton("\u5973");
		sexPanel.add(famale);
		famale.setFont(new Font("楷体", Font.PLAIN, 16));
		ButtonGroup bg=new ButtonGroup();
		bg.add(male);
		bg.add(famale);
		male.setEnabled(false);
		famale.setEnabled(false);
		
		JLabel contact = new JLabel("\u8054\u7CFB\u65B9\u5F0F\uFF1A");
		contact.setBounds(312, 96, 80, 19);
		contact.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(contact);
		
		JLabel password = new JLabel("\u5BC6\u7801\uFF1A");
		password.setBounds(65, 160, 48, 19);
		password.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(password);
		
		passwordInput = new JTextField();
		passwordInput.setEditable(false);
		passwordInput.setBounds(150, 159, 97, 21);
		contentPane.add(passwordInput);
		passwordInput.setColumns(10);
		this.loadData();
	}

	private void loadData() {
		String sql="select * from Staff where staff='"+this.staff+"'";
		sqlConnect connect = null;
		try {
			connect = new sqlConnect();
		} catch (ClassNotFoundException | SQLException e1) {
			JOptionPane.showMessageDialog(this, "数据库连接失败");
			e1.printStackTrace();
		}
		ResultSet rSet = null;
		try {
			rSet=connect.getResultSet(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			while(rSet.next()){
				this.staffInput.setText(this.staff);
				this.nameInput.setText(rSet.getString(2));
				this.contactInput.setText(rSet.getString(5));
				this.passwordInput.setText(rSet.getString(6));
				if(rSet.getString(3).trim().equals("男")){
					male.setSelected(true);
				}else{
					famale.setSelected(true);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
