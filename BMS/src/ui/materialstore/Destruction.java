package ui.materialstore;

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
public class Destruction extends JInternalFrame implements ActionListener{

	private JPanel Details;
	private JTextField dNumInput;
	private JTextField mNumInput;
	private JTextField amountInput;
	private JTextField staffInput;
	private JTextField timeInput;
	private JLabel dNum;
	private JLabel mNum;
	private JButton save;
	private JButton cancel;
	private JLabel time;
	static int chooseSet=0;
	static String dnum=null;
	String staff2;



	/**
	 * Create the frame.
	 */
	public Destruction(int set,String keyWord) {
		chooseSet=set;
		dnum=keyWord;
		setTitle("\u914D\u4EF6\u6B21\u54C1\u9500\u6BC1\u5355");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 727, 415);
		Details = new JPanel();
		Details.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Details);
		Details.setLayout(null);
		
		JLabel nullTop = new JLabel("          ");
		nullTop.setBounds(157, 5, 118, 15);
		Details.add(nullTop);
		
		 dNum = new JLabel("\u914D\u4EF6\u9500\u6BC1\u5355\u53F7\uFF1A");
		dNum.setBounds(40, 91, 112, 19);
		dNum.setFont(new Font("楷体", Font.PLAIN, 16));
		Details.add(dNum);
		
		dNumInput = new JTextField();
		dNumInput.setBounds(157, 90, 118, 21);
		dNumInput.setText("");
		Details.add(dNumInput);
		dNumInput.setColumns(10);
		
		 mNum = new JLabel("\u9500\u6BC1\u914D\u4EF6\u7F16\u53F7\uFF1A");
		mNum.setBounds(417, 91, 112, 19);
		mNum.setFont(new Font("楷体", Font.PLAIN, 16));
		Details.add(mNum);
		
		mNumInput = new JTextField();
		mNumInput.setBounds(534, 90, 123, 21);
		Details.add(mNumInput);
		mNumInput.setColumns(10);
		
		JLabel nullGap1 = new JLabel("          ");
		nullGap1.setBounds(66, 116, 60, 15);
		Details.add(nullGap1);
		
		JLabel amount = new JLabel("\u914D\u4EF6\u9500\u6BC1\u6570\u91CF\uFF1A");
		amount.setBounds(40, 137, 112, 19);
		amount.setFont(new Font("楷体", Font.PLAIN, 16));
		Details.add(amount);
		
		amountInput = new JTextField();
		amountInput.setBounds(157, 136, 118, 21);
		Details.add(amountInput);
		amountInput.setColumns(10);
		
		JLabel staff = new JLabel("\u4E1A\u52A1\u5458\u7F16\u53F7\uFF1A");
		staff.setBounds(417, 137, 96, 19);
		staff.setFont(new Font("楷体", Font.PLAIN, 16));
		Details.add(staff);
		
		staffInput = new JTextField();
		staff2=userVerify.getCurrentStaff();
		staffInput.setBounds(534, 136, 123, 21);
		staffInput.setText("");
		Details.add(staffInput);
		staffInput.setColumns(10);
		
		JLabel nullGap2 = new JLabel("          ");
		nullGap2.setBounds(66, 162, 60, 15);
		Details.add(nullGap2);
		
		 time = new JLabel("\u9500\u6BC1\u65F6\u95F4\uFF1A");
		time.setBounds(40, 186, 80, 19);
		time.setFont(new Font("楷体", Font.PLAIN, 16));
		Details.add(time);
		
		timeInput = new JTextField();
		timeInput.setBounds(157, 185, 118, 21);
		timeInput.setText("");
		Details.add(timeInput);
		timeInput.setColumns(10);

		
		JLabel nullGap3 = new JLabel("          ");
		nullGap3.setBounds(186, 214, 60, 15);
		Details.add(nullGap3);
		
		JLabel nullGap4 = new JLabel("          ");
		nullGap4.setBounds(186, 266, 60, 15);
		Details.add(nullGap4);
		
		JLabel nullBottom = new JLabel("          ");
		nullBottom.setBounds(92, 298, 60, 15);
		Details.add(nullBottom);
		
		 save = new JButton("\u4FDD\u5B58");
		save.setBounds(110, 266, 118, 27);
		save.setFont(new Font("楷体", Font.PLAIN, 16));
		Details.add(save);
		
		 cancel = new JButton("\u53D6\u6D88");
		cancel.setBounds(461, 266, 112, 27);
		cancel.setFont(new Font("楷体", Font.PLAIN, 16));
		Details.add(cancel);
		
		save.addActionListener(this);
		cancel.addActionListener(this);

		this.loadData();
	}

	@SuppressWarnings("static-access")
	private void loadData() {
		if(this.chooseSet==0){
			this.mNum.setVisible(false);
			this.mNumInput.setVisible(false);
			this.dNum.setText("配件编号");
			this.time.setVisible(false);
			this.timeInput.setVisible(false);
			this.staffInput.setText(staff2);
			
		}else{
			dNumInput.setEditable(false);
			staffInput.setEditable(false);
			timeInput.setEditable(false);
			String sql="select Dnum,Mnum,amount,staff,convert(nvarchar(100),time,23) from MatDestroy where Dnum='"+this.dnum+"'";
			sqlConnect connect = null;
			try {
				connect = new sqlConnect();
			} catch (ClassNotFoundException e1) {
				JOptionPane.showMessageDialog(this, "驱动程序丢失");
				e1.printStackTrace();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(this, "数据库连接失败失");
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
					dNumInput.setText(rSet.getString(1));
					mNumInput.setText(rSet.getString(2));
					amountInput.setText(rSet.getString(3));
					staffInput.setText(rSet.getString(4));
					timeInput.setText(rSet.getString(5));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==save){
			if(this.chooseSet==0){
				String mnum,amount,returnID;
				mnum=dNumInput.getText().trim();
				amount=amountInput.getText().trim();
				sqlConnect connect = null;
				try {
					connect = new sqlConnect();
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(this, "数据库连接失败");
					e1.printStackTrace();
				}
				returnID=connect.materialDestroy(mnum, amount, staff2);
				JOptionPane.showMessageDialog(this, "销毁成功,销毁单号:"+returnID+"");
				this.dispose();
			}
			else{
				String mnum,amount,staff;
				mnum=mNumInput.getText().trim();
				amount=amountInput.getText().trim();
				staff=userVerify.getCurrentStaff();
				String sql="exec material_destroy_update '"+this.dnum+"','"+mnum+"','"+amount+"','"+staff+"'";
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
		}
		if(e.getSource()==cancel){
			this.dispose();
		}
	}

}
