package ui.materialstore;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sql.sqlConnect;
import userVerify.userVerify;

import javax.swing.ButtonGroup;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MaterialLoadRevise extends JInternalFrame implements ActionListener,MouseListener{

	private JPanel contentPane;
	private JTextField sNumInput;
	private JTextField amountInput;
	private JTextField staffInput;
	private JTextField mNumInput;
	private JTextField dAmountInput;
	private JTextField timeInput;
	private JRadioButton produce;
	private JRadioButton back;
	private JButton save;
	private JButton cancel;
	String snum;


	/**
	 * Create the frame.
	 */
	public MaterialLoadRevise(String keyWord) {
		snum=keyWord;
		setTitle("\u914D\u4EF6\u5165\u5E93\u5355\u4FEE\u6539");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		this.setClosable(true);
		setBounds(100, 100, 869, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel sNum = new JLabel("\u914D\u4EF6\u5165\u5E93\u5355\u53F7\uFF1A");
		sNum.setBounds(65, 66, 112, 19);
		sNum.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(sNum);
		
		sNumInput = new JTextField();
		sNumInput.setEditable(false);
		sNumInput.setBounds(182, 65, 121, 21);
		contentPane.add(sNumInput);
		sNumInput.setColumns(10);
		
		JLabel mNum = new JLabel("\u914D\u4EF6\u7F16\u53F7\uFF1A");
		mNum.setBounds(494, 66, 80, 19);
		mNum.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(mNum);
		
		mNumInput = new JTextField();
		mNumInput.setBounds(619, 65, 121, 21);
		contentPane.add(mNumInput);
		mNumInput.setColumns(10);
		
		JLabel amount = new JLabel("\u914D\u4EF6\u5165\u5E93\u6570\u91CF\uFF1A");
		amount.setBounds(65, 122, 112, 19);
		amount.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(amount);
		
		amountInput = new JTextField();
		amountInput.setBounds(182, 121, 121, 21);
		amountInput.setText("");
		contentPane.add(amountInput);
		amountInput.setColumns(10);
		
		JLabel dAmount = new JLabel("\u9500\u6BC1\u91CF");
		dAmount.setBounds(494, 122, 120, 19);
		dAmount.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(dAmount);
		
		dAmountInput = new JTextField();
		dAmountInput.setBounds(619, 121, 121, 21);
		dAmountInput.setText("");
		contentPane.add(dAmountInput);
		dAmountInput.setColumns(10);
		
		JLabel source = new JLabel("\u914D\u4EF6\u6765\u6E90\uFF1A");
		source.setBounds(65, 181, 80, 19);
		source.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(source);
		
		
		 produce = new JRadioButton("\u914D\u4EF6\u8F66\u95F4\u751F\u4EA7");
		produce.setBounds(182, 177, 121, 27);
		produce.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(produce);
		
		 back = new JRadioButton("\u6210\u54C1\u8F66\u95F4\u8FD4\u56DE");
		back.setBounds(308, 177, 121, 27);
		back.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(back);
		ButtonGroup bg=new ButtonGroup();
		bg.add(produce);
		bg.add(back);
		
		JLabel time = new JLabel("\u914D\u4EF6\u5165\u5E93\u65F6\u95F4\uFF1A");
		time.setBounds(494, 181, 112, 19);
		time.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(time);
		
		timeInput = new JTextField();
		timeInput.setEditable(false);
		timeInput.setBounds(619, 180, 121, 21);
		timeInput.setText("");
		contentPane.add(timeInput);
		timeInput.setColumns(10);
		
		JLabel staff = new JLabel("\u4E1A\u52A1\u5458\u7F16\u53F7\uFF1A");
		staff.setBounds(65, 240, 96, 19);
		staff.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(staff);
		
		staffInput = new JTextField();
		staffInput.setEditable(false);
		staffInput.setBounds(182, 239, 121, 21);
		contentPane.add(staffInput);
		staffInput.setColumns(10);
		
		 save = new JButton("\u4FDD\u5B58");
		save.setBounds(286, 304, 121, 27);
		save.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(save);
		
		 cancel = new JButton("\u53D6\u6D88");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cancel.setBounds(470, 304, 120, 27);
		cancel.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(cancel);
		this.loadData();
		save.addActionListener(this);
		back.addActionListener(this);
		produce.addMouseListener(this);
		back.addMouseListener(this);
		this.setVisible(true);
	}

	private void loadData() {
		String sql="select Snum,Mnum,amount,destroy,staff,convert(nvarchar(100),time,23),source from MatStore where Snum ='"+this.snum+"'";
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
				sNumInput.setText(rSet.getString(1));
				mNumInput.setText(rSet.getString(2));
				amountInput.setText(rSet.getString(3));
				dAmountInput.setText(rSet.getString(4));
				staffInput.setText(rSet.getString(5));
				timeInput.setText(rSet.getString(6));
				if(rSet.getString(7).equals("0")){
					produce.setSelected(true);
				}else{
					back.setSelected(true);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==save){
			String snum,mnum,amount,destroy,staff,source;
			snum=this.snum;
			mnum=mNumInput.getText().trim();
			amount=amountInput.getText().trim();
			destroy=dAmountInput.getText().trim();
			staff=userVerify.getCurrentStaff().trim();
			if(produce.isSelected()){
				source="0";
			}else{
				source="1";
			}
			if (mnum.equals("") || amount.equals("") || destroy.equals("")) {
				JOptionPane.showMessageDialog(this, "请输入完整内容");
			} else {
				try {
					@SuppressWarnings("unused")
					long a = Long.parseLong(mnum);
					int b = Integer.parseInt(amount);
					int c = Integer.parseInt(destroy);
					if (mnum.length() != 8) {
						JOptionPane.showMessageDialog(this, "请输入8位编号");
					} else if (b < 0 || c < 0) {
						JOptionPane.showMessageDialog(this, "数量必须为非负数");
					} else if (b < c) {
						JOptionPane.showMessageDialog(this, "销毁数量不能大于库存量");
					} else {
						String sql="exec material_store_update '"+snum+"','"+mnum+"','"+amount+"','"+destroy+"','"+staff+"','"+source+"'";
						sqlConnect connect = new sqlConnect();
						connect.executeSql(sql);
						JOptionPane.showMessageDialog(this, "修改成功,操作员工编号:" + staff + "");
						this.dispose();
					}
				}
				catch(SQLException ee){
					JOptionPane.showMessageDialog(this, "修改失败");
				}
				catch (Exception ee) {
					JOptionPane.showMessageDialog(this, "编号由纯数字构成");
				}

			}
		}
		if(e.getSource()==cancel){
			this.dispose();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(produce.isSelected()){
			dAmountInput.setEditable(true);
			dAmountInput.setText("");
		}
		if(back.isSelected()){
			dAmountInput.setEditable(false);
			dAmountInput.setText("0");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

}
