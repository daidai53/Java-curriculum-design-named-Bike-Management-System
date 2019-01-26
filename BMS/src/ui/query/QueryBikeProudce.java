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
public class QueryBikeProudce extends JInternalFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField bpNumInput;
	private JTextField tNumInput;
	private JTextField amountInput;
	private JTextField timeInput;
	private JTextField staffInput;
	String bpNum;
	private JButton save;
	private JButton cancel;


	/**
	 * Create the frame.
	 */
	public QueryBikeProudce(String keyWord) {
		this.bpNum=keyWord;
		setTitle("\u6210\u54C1\u751F\u4EA7\u5355\u4FEE\u6539");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 650, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel bpNum = new JLabel("\u6210\u54C1\u751F\u4EA7\u5355\u53F7\uFF1A");
		bpNum.setBounds(65, 36, 112, 19);
		bpNum.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(bpNum);
		
		bpNumInput = new JTextField();
		bpNumInput.setEditable(false);
		bpNumInput.setBounds(182, 35, 104, 21);
		contentPane.add(bpNumInput);
		bpNumInput.setColumns(10);
		
		JLabel tNum = new JLabel("\u8F66\u578B\u7F16\u53F7\uFF1A");
		tNum.setBounds(321, 36, 80, 19);
		tNum.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(tNum);
		
		tNumInput = new JTextField();
		tNumInput.setBounds(454, 35, 104, 21);
		tNumInput.setText("");
		contentPane.add(tNumInput);
		tNumInput.setColumns(10);
		
		JLabel amount = new JLabel("\u6210\u54C1\u751F\u4EA7\u6570\u91CF\uFF1A");
		amount.setBounds(65, 92, 112, 19);
		amount.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(amount);
		
		amountInput = new JTextField();
		amountInput.setBounds(182, 91, 104, 21);
		amountInput.setText("");
		contentPane.add(amountInput);
		amountInput.setColumns(10);
		
		JLabel time = new JLabel("\u751F\u4EA7\u5355\u5F55\u5165\u65F6\u95F4\uFF1A");
		time.setBounds(321, 92, 128, 19);
		time.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(time);
		
		timeInput = new JTextField();
		timeInput.setEditable(false);
		timeInput.setBounds(454, 91, 104, 21);
		timeInput.setText("");
		contentPane.add(timeInput);
		timeInput.setColumns(10);
		
		JLabel staff = new JLabel("\u4E1A\u52A1\u5458\u7F16\u53F7\uFF1A");
		staff.setBounds(65, 148, 96, 19);
		staff.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(staff);
		
		staffInput = new JTextField();
		staffInput.setEditable(false);
		staffInput.setBounds(182, 147, 104, 21);
		staffInput.setText("");
		contentPane.add(staffInput);
		staffInput.setColumns(10);
		
		 save = new JButton("\u4FDD\u5B58");
		save.setBounds(190, 243, 96, 27);
		save.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(save);
		
		 cancel = new JButton("\u53D6\u6D88");
		cancel.setBounds(353, 243, 96, 27);
		cancel.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(cancel);
		this.loadData();
		save.addActionListener(this);
		cancel.addActionListener(this);
		this.setVisible(true);
	}

	private void loadData() {
		String sql="select BPnum,Tnum,amount,staff,convert(nvarchar(100),time,23) from BikeProduce where BPnum= '"+this.bpNum+"'";
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
				this.bpNumInput.setText(rSet.getString(1));
				this.tNumInput.setText(rSet.getString(2));
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
			String tnum,amount,staff;
			tnum=tNumInput.getText().trim();
			amount=amountInput.getText().trim();
			staff=userVerify.getCurrentStaff();
			String sql="exec bike_produce_update '"+this.bpNum+"','"+tnum+"','"+amount+"','"+staff+"'";
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
