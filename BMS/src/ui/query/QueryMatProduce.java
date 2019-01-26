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
public class QueryMatProduce extends JInternalFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField mpNumInput;
	private JTextField mNumInput;
	private JTextField amountInput;
	private JTextField timeInput;
	private JTextField staffInput;
	private JButton cancel;
	private JButton save;
	String mpNum;


	/**
	 * Create the frame.
	 */
	public QueryMatProduce(String keyWord) {
		this.mpNum=keyWord;
		setTitle("\u914D\u4EF6\u751F\u4EA7\u5355\u4FEE\u6539");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 649, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel mpNum = new JLabel("\u914D\u4EF6\u751F\u4EA7\u5355\u53F7\uFF1A");
		mpNum.setBounds(70, 41, 112, 19);
		mpNum.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(mpNum);
		
		mpNumInput = new JTextField();
		mpNumInput.setEditable(false);
		mpNumInput.setBounds(187, 40, 90, 21);
		mpNumInput.setText("");
		contentPane.add(mpNumInput);
		mpNumInput.setColumns(10);
		
		JLabel mNum = new JLabel("\u914D\u4EF6\u7F16\u53F7\uFF1A");
		mNum.setBounds(323, 41, 80, 19);
		mNum.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(mNum);
		
		mNumInput = new JTextField();
		mNumInput.setBounds(456, 40, 90, 21);
		mNumInput.setText("");
		contentPane.add(mNumInput);
		mNumInput.setColumns(10);
		
		JLabel amount = new JLabel("\u914D\u4EF6\u751F\u4EA7\u6570\u91CF\uFF1A");
		amount.setBounds(70, 102, 112, 19);
		amount.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(amount);
		
		amountInput = new JTextField();
		amountInput.setBounds(187, 101, 90, 21);
		amountInput.setText("");
		contentPane.add(amountInput);
		amountInput.setColumns(10);
		
		JLabel label = new JLabel("\u751F\u4EA7\u5355\u5F55\u5165\u65F6\u95F4\uFF1A");
		label.setBounds(323, 102, 128, 19);
		label.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(label);
		
		timeInput = new JTextField();
		timeInput.setEditable(false);
		timeInput.setBounds(456, 101, 90, 21);
		contentPane.add(timeInput);
		timeInput.setColumns(10);
		
		JLabel time = new JLabel("\u4E1A\u52A1\u5458\u7F16\u53F7\uFF1A");
		time.setBounds(70, 163, 96, 19);
		time.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(time);
		
		staffInput = new JTextField();
		staffInput.setEditable(false);
		staffInput.setBounds(187, 162, 90, 21);
		staffInput.setText("");
		contentPane.add(staffInput);
		staffInput.setColumns(10);
		
		 save = new JButton("\u4FDD\u5B58");
		save.setBounds(187, 240, 90, 27);
		save.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(save);
		
		cancel = new JButton("\u53D6\u6D88");
		cancel.setBounds(338, 240, 90, 27);
		cancel.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(cancel);
		this.loadData();
		save.addActionListener(this);
		cancel.addActionListener(this);
		this.setVisible(true);
	}

	private void loadData() {
		String sql="select MPnum,Mnum,amount,staff,convert(nvarchar(100),time,23) from MatProduce where MPnum = '"+this.mpNum+"'";
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
				this.mpNumInput.setText(rSet.getString(1));
				this.mNumInput.setText(rSet.getString(2));
				this.amountInput.setText(rSet.getString(3));
				this.staffInput.setText(rSet.getString(4));
				this.timeInput.setText(rSet.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==save){
			String mnum,amount,staff;
			mnum=mNumInput.getText().trim();
			amount=amountInput.getText().trim();
			staff=userVerify.getCurrentStaff();
			String sql="exec material_produce_update '"+this.mpNum+"','"+mnum+"','"+amount+"','"+staff+"'";
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
