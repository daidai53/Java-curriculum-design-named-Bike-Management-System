package ui.infoManage;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sql.sqlConnect;
import ui.mainUIFrame;
import userVerify.userVerify;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;

public class Revise_AddOrderInfo extends JInternalFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField amountInput;
	private JTextField typeInput;
	private JTextField staffInput;
	private JButton save;
	private JButton cancel;
	private JButton selectButton;
	static int chooseSet = 0;
	static String[] titleHead = new String[] { "成品订单", "销售单", "生产计划", "成品生产单", "配件生产单", "配件出库单" };
	static String[] orderNums = new String[] { "订单", "销售单", "生产计划", "成品生产单", "配件生产单", "配件出库单" };
	static String[] typeNums = new String[] { "车型", "车型", "配件", "车型", "配件", "配件" };

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("static-access")
	public Revise_AddOrderInfo(int set) {
		chooseSet = set;
		setTitle(titleHead[chooseSet] + "录入");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 642, 476);
		this.setClosable(true);
		this.setResizable(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel nullTop = new JLabel("");
		GridBagConstraints gbc_nullTop = new GridBagConstraints();
		gbc_nullTop.insets = new Insets(0, 0, 5, 5);
		gbc_nullTop.gridx = 1;
		gbc_nullTop.gridy = 0;
		contentPane.add(nullTop, gbc_nullTop);

		JLabel orderNum = new JLabel("所需" + typeNums[chooseSet] + "编号");
		orderNum.setFont(new Font("楷体", Font.PLAIN, 16));
		GridBagConstraints gbc_orderNum = new GridBagConstraints();
		gbc_orderNum.anchor = GridBagConstraints.WEST;
		gbc_orderNum.insets = new Insets(0, 0, 5, 5);
		gbc_orderNum.gridx = 1;
		gbc_orderNum.gridy = 1;
		contentPane.add(orderNum, gbc_orderNum);

		typeInput = new JTextField();
		GridBagConstraints gbc_orderNumInput = new GridBagConstraints();
		gbc_orderNumInput.insets = new Insets(0, 0, 5, 5);
		gbc_orderNumInput.fill = GridBagConstraints.BOTH;
		gbc_orderNumInput.gridx = 2;
		gbc_orderNumInput.gridy = 1;
		contentPane.add(typeInput, gbc_orderNumInput);
		typeInput.setColumns(10);

		JLabel nullMiddle = new JLabel("");
		GridBagConstraints gbc_nullMiddle = new GridBagConstraints();
		gbc_nullMiddle.gridwidth = 2;
		gbc_nullMiddle.insets = new Insets(0, 0, 5, 5);
		gbc_nullMiddle.gridx = 3;
		gbc_nullMiddle.gridy = 1;
		contentPane.add(nullMiddle, gbc_nullMiddle);

		 selectButton = new JButton("选择车型");
		 selectButton.setFont(new Font("楷体", Font.PLAIN, 16)); 
		 GridBagConstraints gbc_typeNum = new GridBagConstraints(); 
		 gbc_typeNum.anchor = GridBagConstraints.WEST; 
		 gbc_typeNum.insets = new Insets(0, 0, 5, 5);
		 gbc_typeNum.gridx = 5; gbc_typeNum.gridy = 1;
		 contentPane.add(selectButton, gbc_typeNum);
		 if(this.chooseSet==0||this.chooseSet==1||chooseSet==3){
			 typeInput.setEditable(false);
		 }
		 else{
			 selectButton.setVisible(false);
		 }
		 

		/*
		 * typeInput = new JTextField(); GridBagConstraints gbc_typeInput = new
		 * GridBagConstraints(); gbc_typeInput.gridwidth = 2;
		 * gbc_typeInput.insets = new Insets(0, 0, 5, 5); gbc_typeInput.fill =
		 * GridBagConstraints.HORIZONTAL; gbc_typeInput.gridx = 6;
		 * gbc_typeInput.gridy = 1; contentPane.add(typeInput, gbc_typeInput);
		 * typeInput.setColumns(10);
		 */

		JLabel nullRow1 = new JLabel("");
		GridBagConstraints gbc_nullRow1 = new GridBagConstraints();
		gbc_nullRow1.insets = new Insets(0, 0, 5, 5);
		gbc_nullRow1.gridx = 1;
		gbc_nullRow1.gridy = 2;
		contentPane.add(nullRow1, gbc_nullRow1);

		JLabel amount = new JLabel("所需" + typeNums[chooseSet] + "数量");
		amount.setFont(new Font("楷体", Font.PLAIN, 16));
		GridBagConstraints gbc_amount = new GridBagConstraints();
		gbc_amount.anchor = GridBagConstraints.EAST;
		gbc_amount.insets = new Insets(0, 0, 5, 5);
		gbc_amount.gridx = 1;
		gbc_amount.gridy = 3;
		contentPane.add(amount, gbc_amount);

		amountInput = new JTextField();
		GridBagConstraints gbc_amountInput = new GridBagConstraints();
		gbc_amountInput.insets = new Insets(0, 0, 5, 5);
		gbc_amountInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_amountInput.gridx = 2;
		gbc_amountInput.gridy = 3;
		contentPane.add(amountInput, gbc_amountInput);
		amountInput.setColumns(10);

		/*
		 * JLabel process = new JLabel("\u8BA2\u5355\u5B8C\u6210\u5EA6\uFF1A");
		 * process.setFont(new Font("楷体", Font.PLAIN, 16)); GridBagConstraints
		 * gbc_process = new GridBagConstraints(); gbc_process.anchor =
		 * GridBagConstraints.WEST; gbc_process.insets = new Insets(0, 0, 5, 5);
		 * gbc_process.gridx = 5; gbc_process.gridy = 3;
		 * contentPane.add(process, gbc_process);
		 */

		/*
		 * processInput = new JTextField(); GridBagConstraints gbc_processInput
		 * = new GridBagConstraints(); gbc_processInput.gridwidth = 2;
		 * gbc_processInput.insets = new Insets(0, 0, 5, 5);
		 * gbc_processInput.fill = GridBagConstraints.HORIZONTAL;
		 * gbc_processInput.gridx = 6; gbc_processInput.gridy = 3;
		 * contentPane.add(processInput, gbc_processInput);
		 * processInput.setColumns(10);
		 */

		JLabel nullRow2 = new JLabel("");
		GridBagConstraints gbc_nullRow2 = new GridBagConstraints();
		gbc_nullRow2.insets = new Insets(0, 0, 5, 5);
		gbc_nullRow2.gridx = 1;
		gbc_nullRow2.gridy = 4;
		contentPane.add(nullRow2, gbc_nullRow2);

		JLabel time = new JLabel("\u4E1A\u52A1\u5458\u7F16\u53F7\uFF1A");
		time.setFont(new Font("楷体", Font.PLAIN, 14));
		GridBagConstraints gbc_time = new GridBagConstraints();
		gbc_time.anchor = GridBagConstraints.WEST;
		gbc_time.insets = new Insets(0, 0, 5, 5);
		gbc_time.gridx = 1;
		gbc_time.gridy = 5;
		contentPane.add(time, gbc_time);

		staffInput = new JTextField();
		GridBagConstraints gbc_timeInput = new GridBagConstraints();
		gbc_timeInput.insets = new Insets(0, 0, 5, 5);
		gbc_timeInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_timeInput.gridx = 2;
		gbc_timeInput.gridy = 5;
		contentPane.add(staffInput, gbc_timeInput);
		staffInput.setColumns(10);
		staffInput.setEditable(false);
		staffInput.setText(userVerify.getCurrentStaff());

		JLabel staff = new JLabel("对应订单编号:");
		staff.setFont(new Font("楷体", Font.PLAIN, 16));
		GridBagConstraints gbc_staff = new GridBagConstraints();
		gbc_staff.anchor = GridBagConstraints.NORTHWEST;
		gbc_staff.insets = new Insets(0, 0, 5, 5);
		gbc_staff.gridx = 5;
		gbc_staff.gridy = 5;
		contentPane.add(staff, gbc_staff);

		staffInput = new JTextField();
		GridBagConstraints gbc_staffInput = new GridBagConstraints();
		gbc_staffInput.gridwidth = 2;
		gbc_staffInput.insets = new Insets(0, 0, 5, 5);
		gbc_staffInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_staffInput.gridx = 6;
		gbc_staffInput.gridy = 5;
		contentPane.add(staffInput, gbc_staffInput);
		staffInput.setColumns(10);

		JLabel nullRow3 = new JLabel("");
		GridBagConstraints gbc_nullRow3 = new GridBagConstraints();
		gbc_nullRow3.insets = new Insets(0, 0, 5, 5);
		gbc_nullRow3.gridx = 1;
		gbc_nullRow3.gridy = 6;
		contentPane.add(nullRow3, gbc_nullRow3);

		save = new JButton("\u4FDD\u5B58");
		save.setFont(new Font("楷体", Font.PLAIN, 14));
		GridBagConstraints gbc_save = new GridBagConstraints();
		gbc_save.insets = new Insets(0, 0, 5, 5);
		gbc_save.gridx = 1;
		gbc_save.gridy = 7;
		contentPane.add(save, gbc_save);

		cancel = new JButton("\u53D6\u6D88");
		cancel.setFont(new Font("楷体", Font.PLAIN, 14));
		GridBagConstraints gbc_cancel = new GridBagConstraints();
		gbc_cancel.insets = new Insets(0, 0, 5, 5);
		gbc_cancel.gridx = 3;
		gbc_cancel.gridy = 7;
		contentPane.add(cancel, gbc_cancel);

		JLabel nullButtom = new JLabel("");
		GridBagConstraints gbc_nullButtom = new GridBagConstraints();
		gbc_nullButtom.insets = new Insets(0, 0, 0, 5);
		gbc_nullButtom.gridx = 1;
		gbc_nullButtom.gridy = 8;
		contentPane.add(nullButtom, gbc_nullButtom);
		if (this.chooseSet != 1) {
			staff.setVisible(false);
			staffInput.setVisible(false);
		}
		

		save.addActionListener(this);
		cancel.addActionListener(this);
		selectButton.addActionListener(this);
	}

	@SuppressWarnings({ "static-access", "deprecation" })
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == save) {
			sqlConnect connect = null;
			try {
				connect = new sqlConnect();
			} catch (ClassNotFoundException e2) {
				JOptionPane.showMessageDialog(this, "驱动程序丢失");
				e2.printStackTrace();
			} catch (SQLException e2) {
				JOptionPane.showMessageDialog(this, "数据库连接失败");
				e2.printStackTrace();
			}
			String typeNum = null;
			String count = null;
			String staff = null;
			String onum = null;
			typeNum = typeInput.getText().trim();
			count = amountInput.getText().trim();
			staff = userVerify.getCurrentStaff();
			onum = staffInput.getText().trim();
			String res = null;
			if (typeNum.equals("")) {
				JOptionPane.showMessageDialog(this, "请填写编号");
			} else if (count.equals("")) {
				JOptionPane.showMessageDialog(this, "请填写数量");
			} else {
				try {

					res = connect.inputOrders(typeNum, count, staff, chooseSet, onum);
					JOptionPane.showMessageDialog(this,
							"" + orderNums[chooseSet] + "录入成功," + orderNums[chooseSet] + "编号为" + res + "");
				} catch (SQLException e1) {
					if (this.chooseSet == 5 || this.chooseSet == 1) {
						JOptionPane.showMessageDialog(this, "库存不足");
					}
					e1.printStackTrace();
				}
			}
		}
		if (e.getSource() == cancel) {
			this.dispose();
		}
		if(e.getSource()==selectButton){
			ProductMessage message=new ProductMessage(mainUIFrame.mainUIFrame,true);
			message.show();
			typeInput.setText(message.getReturn());
			//message.closeIt();
		}
	}
}
