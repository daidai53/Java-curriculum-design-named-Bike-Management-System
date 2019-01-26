package ui.query;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sql.sqlConnect;
import userVerify.userVerify;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class QueryOrderform extends JInternalFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField oNumInput;
	private JTextField amountInput;
	private JTextField timeInput;
	private JTextField tNumInput;
	private JTextField processInput;
	private JTextField staffInput;
	String Onum;
	private JButton save;
	private JButton cancel;


	/**
	 * Create the frame.
	 */
	public QueryOrderform(String keyWord) {
		
		Onum=keyWord;
		setTitle("\u4FEE\u6539\u8BA2\u5355\u4FE1\u606F");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 352);
		this.setClosable(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		oNumInput = new JTextField();
		oNumInput.setEditable(false);
		oNumInput.setBounds(212, 36, 105, 21);
		contentPane.add(oNumInput);
		oNumInput.setColumns(10);
		
		JLabel tNum = new JLabel("\u6240\u9700\u8F66\u578B\u7F16\u53F7\uFF1A");
		tNum.setBounds(359, 36, 112, 19);
		tNum.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(tNum);
		
		tNumInput = new JTextField();
		tNumInput.setBounds(481, 35, 96, 21);
		contentPane.add(tNumInput);
		tNumInput.setColumns(10);
		
		JLabel amount = new JLabel("\u6240\u9700\u8F66\u578B\u6570\u91CF\uFF1A");
		amount.setBounds(90, 94, 112, 19);
		amount.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(amount);
		
		amountInput = new JTextField();
		amountInput.setBounds(212, 94, 105, 21);
		contentPane.add(amountInput);
		amountInput.setColumns(10);
		
		JLabel process = new JLabel("\u8BA2\u5355\u5B8C\u6210\u5EA6\uFF1A");
		process.setBounds(359, 94, 96, 19);
		process.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(process);
		
		processInput = new JTextField();
		processInput.setBounds(481, 94, 96, 21);
		processInput.setEditable(false);
		contentPane.add(processInput);
		processInput.setColumns(10);
		
		JLabel time = new JLabel("\u8BA2\u5355\u5F55\u5165\u65F6\u95F4\uFF1A");
		time.setBounds(90, 153, 112, 19);
		time.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(time);
		
		timeInput = new JTextField();
		timeInput.setEditable(false);
		timeInput.setBounds(212, 153, 105, 21);
		contentPane.add(timeInput);
		timeInput.setColumns(10);
		
		JLabel staff = new JLabel("\u4E1A\u52A1\u5458\u7F16\u53F7\uFF1A");
		staff.setBounds(359, 153, 96, 19);
		staff.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(staff);
		
		staffInput = new JTextField();
		staffInput.setEditable(false);
		staffInput.setBounds(481, 153, 96, 21);
		contentPane.add(staffInput);
		staffInput.setColumns(10);
		
		 save = new JButton("\u4FDD\u5B58");
		save.setBounds(212, 212, 112, 27);
		save.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(save);
		
		 cancel = new JButton("\u53D6\u6D88");
		cancel.setBounds(376, 212, 112, 27);
		cancel.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(cancel);
		
		this.loadData();
		save.addActionListener(this);
		cancel.addActionListener(this);
		
		JLabel oNum = new JLabel("\u8BA2\u5355\u7F16\u53F7\uFF1A");
		oNum.setFont(new Font("楷体", Font.PLAIN, 16));
		oNum.setBounds(90, 38, 96, 15);
		contentPane.add(oNum);
	}


	private void loadData() {
		String sql="select Onum,Tnum,amount,process,staff,convert(varchar(100),time,23) from OrderForm where Onum = '"+this.Onum+"'";
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
				this.oNumInput.setText(rSet.getString(1));
				this.tNumInput.setText(rSet.getString(2));
				this.amountInput.setText(rSet.getString(3));
				this.processInput.setText(rSet.getString(4));
				this.timeInput.setText(rSet.getString(6));
				this.staffInput.setText(rSet.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==save){
			String Tnum,amount,staff;
			Tnum=tNumInput.getText().trim();
			amount=amountInput.getText().trim();
			staff=userVerify.getCurrentStaff();
			String sql="exec order_update '"+this.Onum+"','"+Tnum+"','"+amount+"','"+staff+"'";
			sqlConnect connect = null;
			try {
				connect = new sqlConnect();
			} catch (ClassNotFoundException | SQLException e2) {
				JOptionPane.showMessageDialog(this, "数据库连接失败");
				e2.printStackTrace();
			}
			try {
				connect.executeSql(sql);
				JOptionPane.showMessageDialog(this, "保存成功,操作员工编号:"+staff+"");
				this.dispose();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource()==cancel){
			this.dispose();
		}
	}
}
