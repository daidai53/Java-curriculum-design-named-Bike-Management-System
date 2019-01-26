package ui.materialstore;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sql.sqlConnect;
import userVerify.userVerify;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class MaterialUnloadRevise extends JInternalFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField wNumInput;
	private JTextField mNumInput;
	private JTextField amountInput;
	private JTextField staffInput;
	private JTextField timeInput;
	String wnum;
	private JButton save;
	private JButton cancel;


	/**
	 * Create the frame.
	 */
	public MaterialUnloadRevise(String keyWord) {
		wnum=keyWord;
		setTitle("\u914D\u4EF6\u51FA\u5E93\u5355\u4FEE\u6539");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		this.setClosable(true);
		setBounds(100, 100, 664, 384);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel wNum = new JLabel("\u914D\u4EF6\u51FA\u5E93\u5355\u7F16\u53F7\uFF1A");
		wNum.setBounds(65, 66, 128, 19);
		wNum.setFont(new Font("楷体", Font.PLAIN, 16));
		GridBagConstraints gbc_wNum = new GridBagConstraints();
		gbc_wNum.anchor = GridBagConstraints.WEST;
		gbc_wNum.insets = new Insets(0, 0, 5, 5);
		gbc_wNum.gridx = 2;
		gbc_wNum.gridy = 2;
		contentPane.add(wNum, gbc_wNum);
		
		wNumInput = new JTextField();
		wNumInput.setEditable(false);
		wNumInput.setBounds(198, 65, 112, 21);
		GridBagConstraints gbc_wNumInput = new GridBagConstraints();
		gbc_wNumInput.insets = new Insets(0, 0, 5, 5);
		gbc_wNumInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_wNumInput.gridx = 3;
		gbc_wNumInput.gridy = 2;
		contentPane.add(wNumInput, gbc_wNumInput);
		wNumInput.setColumns(10);
		
		JLabel mNum = new JLabel("\u914D\u4EF6\u7F16\u53F7\uFF1A");
		mNum.setBounds(340, 66, 80, 19);
		mNum.setFont(new Font("楷体", Font.PLAIN, 16));
		GridBagConstraints gbc_mNum = new GridBagConstraints();
		gbc_mNum.anchor = GridBagConstraints.WEST;
		gbc_mNum.insets = new Insets(0, 0, 5, 5);
		gbc_mNum.gridx = 6;
		gbc_mNum.gridy = 2;
		contentPane.add(mNum, gbc_mNum);
		
		mNumInput = new JTextField();
		mNumInput.setBounds(444, 66, 112, 21);
		mNumInput.setText("");
		GridBagConstraints gbc_mNumInput = new GridBagConstraints();
		gbc_mNumInput.insets = new Insets(0, 0, 5, 0);
		gbc_mNumInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_mNumInput.gridx = 7;
		gbc_mNumInput.gridy = 2;
		contentPane.add(mNumInput, gbc_mNumInput);
		mNumInput.setColumns(10);
		
		JLabel amount = new JLabel("\u914D\u4EF6\u51FA\u5E93\u6570\u91CF\uFF1A");
		amount.setBounds(65, 122, 112, 19);
		amount.setFont(new Font("楷体", Font.PLAIN, 16));
		GridBagConstraints gbc_amount = new GridBagConstraints();
		gbc_amount.anchor = GridBagConstraints.WEST;
		gbc_amount.insets = new Insets(0, 0, 5, 5);
		gbc_amount.gridx = 2;
		gbc_amount.gridy = 4;
		contentPane.add(amount, gbc_amount);
		
		amountInput = new JTextField();
		amountInput.setBounds(198, 121, 112, 21);
		GridBagConstraints gbc_amountInput = new GridBagConstraints();
		gbc_amountInput.insets = new Insets(0, 0, 5, 5);
		gbc_amountInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_amountInput.gridx = 3;
		gbc_amountInput.gridy = 4;
		contentPane.add(amountInput, gbc_amountInput);
		amountInput.setColumns(10);
		
		JLabel staff = new JLabel("\u4E1A\u52A1\u5458\u7F16\u53F7\uFF1A");
		staff.setBounds(340, 122, 96, 19);
		staff.setFont(new Font("楷体", Font.PLAIN, 16));
		GridBagConstraints gbc_staff = new GridBagConstraints();
		gbc_staff.anchor = GridBagConstraints.WEST;
		gbc_staff.insets = new Insets(0, 0, 5, 5);
		gbc_staff.gridx = 6;
		gbc_staff.gridy = 4;
		contentPane.add(staff, gbc_staff);
		
		staffInput = new JTextField();
		staffInput.setEditable(false);
		staffInput.setBounds(444, 122, 112, 21);
		GridBagConstraints gbc_staffInput = new GridBagConstraints();
		gbc_staffInput.insets = new Insets(0, 0, 5, 0);
		gbc_staffInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_staffInput.gridx = 7;
		gbc_staffInput.gridy = 4;
		contentPane.add(staffInput, gbc_staffInput);
		staffInput.setColumns(10);
		
		JLabel time = new JLabel("\u914D\u4EF6\u51FA\u5E93\u65F6\u95F4\uFF1A");
		time.setBounds(65, 178, 112, 19);
		time.setFont(new Font("楷体", Font.PLAIN, 16));
		GridBagConstraints gbc_time = new GridBagConstraints();
		gbc_time.anchor = GridBagConstraints.WEST;
		gbc_time.insets = new Insets(0, 0, 5, 5);
		gbc_time.gridx = 2;
		gbc_time.gridy = 6;
		contentPane.add(time, gbc_time);
		
		timeInput = new JTextField();
		timeInput.setEditable(false);
		timeInput.setBounds(198, 177, 112, 21);
		GridBagConstraints gbc_timeInput = new GridBagConstraints();
		gbc_timeInput.insets = new Insets(0, 0, 5, 5);
		gbc_timeInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_timeInput.gridx = 3;
		gbc_timeInput.gridy = 6;
		contentPane.add(timeInput, gbc_timeInput);
		timeInput.setColumns(10);
		
		 save = new JButton("\u4FDD\u5B58");
		save.setBounds(175, 233, 96, 27);
		save.setFont(new Font("楷体", Font.PLAIN, 16));
		GridBagConstraints gbc_save = new GridBagConstraints();
		gbc_save.insets = new Insets(0, 0, 0, 5);
		gbc_save.gridx = 3;
		gbc_save.gridy = 8;
		contentPane.add(save, gbc_save);
		
		 cancel = new JButton("\u53D6\u6D88");
		cancel.setBounds(357, 233, 96, 27);
		cancel.setFont(new Font("楷体", Font.PLAIN, 16));
		GridBagConstraints gbc_cancel = new GridBagConstraints();
		gbc_cancel.fill = GridBagConstraints.BOTH;
		gbc_cancel.insets = new Insets(0, 0, 0, 5);
		gbc_cancel.gridx = 6;
		gbc_cancel.gridy = 8;
		contentPane.add(cancel, gbc_cancel);
		this.loadData();
		
		save.addActionListener(this);
		cancel.addActionListener(this);
		
		this.setVisible(true);
	}

	private void loadData() {
		String sql="select Wnum,Mnum,amount,staff,convert(nvarchar(100),time,23) from MatWithdraw where Wnum = '"+this.wnum+"'";
		sqlConnect connect = null;
		try {
			connect = new sqlConnect();
		} catch (ClassNotFoundException | SQLException e1) {
			JOptionPane.showMessageDialog(this, "数据库连接失败");
			e1.printStackTrace();
		}
		ResultSet resultSet = null;
		try {
			resultSet=connect.getResultSet(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			while(resultSet.next()){
				this.wNumInput.setText(resultSet.getString(1));
				this.mNumInput.setText(resultSet.getString(2));
				this.amountInput.setText(resultSet.getString(3));
				this.staffInput.setText(resultSet.getString(4));
				this.timeInput.setText(resultSet.getString(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==save){
			String wnum,mnum,amount,staff;
			wnum=this.wnum;
			mnum=mNumInput.getText().trim();
			amount=amountInput.getText().trim();
			staff=userVerify.getCurrentStaff().trim();
			String sql="exec material_withdraw_update '"+wnum+"','"+mnum+"','"+amount+"','"+staff+"'";
			sqlConnect connect = null;
			try {
				connect = new sqlConnect();
			} catch (ClassNotFoundException | SQLException e2) {
				JOptionPane.showMessageDialog(this, "数据库连接失败");
				e2.printStackTrace();
			}
			try {
				connect.executeSql(sql);
				JOptionPane.showMessageDialog(this, "修改成功,操作员工编号:"+staff+"");
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
