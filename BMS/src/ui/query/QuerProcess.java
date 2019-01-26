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
public class QuerProcess extends JInternalFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField pNumInput;
	private JTextField mNumInput;
	private JTextField staffInput;
	private JTextField amountInput;
	private JButton save;
	private JButton cancel;
	String pnum;


	/**
	 * Create the frame.
	 */
	public QuerProcess(String keyWord) {
		this.pnum=keyWord;
		setTitle("\u6210\u54C1\u751F\u4EA7\u8BA1\u5212\u4FEE\u6539");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 630, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel pNum = new JLabel("\u751F\u4EA7\u8BA1\u5212\u5355\u53F7\uFF1A");
		pNum.setBounds(65, 36, 112, 19);
		pNum.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(pNum);
		
		pNumInput = new JTextField();
		pNumInput.setEditable(false);
		pNumInput.setBounds(182, 35, 88, 21);
		contentPane.add(pNumInput);
		pNumInput.setColumns(10);
		
		JLabel mNum = new JLabel("\u914D\u4EF6\u7F16\u53F7\uFF1A");
		mNum.setBounds(313, 36, 80, 19);
		mNum.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(mNum);
		
		mNumInput = new JTextField();
		mNumInput.setBounds(430, 35, 88, 21);
		contentPane.add(mNumInput);
		mNumInput.setColumns(10);
		
		JLabel staff = new JLabel("\u4E1A\u52A1\u5458\u7F16\u53F7\uFF1A");
		staff.setBounds(65, 92, 96, 19);
		staff.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(staff);
		
		staffInput = new JTextField();
		staffInput.setEditable(false);
		staffInput.setBounds(182, 91, 88, 21);
		staffInput.setText("");
		contentPane.add(staffInput);
		staffInput.setColumns(10);
		
		JLabel amount = new JLabel("\u6240\u9700\u914D\u4EF6\u6570\u91CF\uFF1A");
		amount.setBounds(313, 92, 112, 19);
		amount.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(amount);
		
		amountInput = new JTextField();
		amountInput.setBounds(430, 91, 88, 21);
		amountInput.setText("");
		contentPane.add(amountInput);
		amountInput.setColumns(10);
		
		 save = new JButton("\u4FDD\u5B58");
		save.setBounds(182, 177, 96, 27);
		save.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(save);
		
		 cancel = new JButton("\u53D6\u6D88");
		cancel.setBounds(313, 177, 96, 27);
		cancel.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(cancel);
		save.addActionListener(this);
		cancel.addActionListener(this);
		this.loadData();
		this.setVisible(true);
	}


	private void loadData() {
		String sql="select PRnum,Mnum,amount,staff from Process where PRnum = '"+this.pnum+"'";
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
				this.pNumInput.setText(rSet.getString(1));
				this.mNumInput.setText(rSet.getString(2));
				this.amountInput.setText(rSet.getString(3));
				this.staffInput.setText(rSet.getString(4));
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
			String sql="exec process_update '"+this.pnum+"','"+mnum+"','"+amount+"','"+staff+"'";
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
