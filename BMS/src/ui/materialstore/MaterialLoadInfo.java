package ui.materialstore;

import javax.swing.JFrame;
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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class MaterialLoadInfo extends JInternalFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JTextField mNumInput;
	private JTextField amountInput;
	private JTextField aAmountInput;
	private JTextField staffInput;
	private JRadioButton add;
	private JRadioButton backReturn;
	private JButton save;
	private JButton cancel;
	String staff;

	/**
	 * Create the frame.
	 */
	public MaterialLoadInfo() {
		setTitle("\u914D\u4EF6\u5165\u5E93\u5355\u5F55\u5165");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 711, 306);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		staff = userVerify.getCurrentStaff().trim();

		JLabel mNum = new JLabel("\u914D\u4EF6\u7F16\u53F7\uFF1A");
		mNum.setBounds(65, 35, 80, 19);
		mNum.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(mNum);

		mNumInput = new JTextField();
		mNumInput.setBounds(182, 35, 96, 21);
		mNumInput.setText("");
		contentPane.add(mNumInput);
		mNumInput.setColumns(10);

		JLabel amount = new JLabel("\u914D\u4EF6\u5165\u5E93\u6570\u91CF\uFF1A");
		amount.setBounds(363, 34, 112, 21);
		amount.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(amount);

		amountInput = new JTextField();
		amountInput.setBounds(507, 35, 102, 21);
		amountInput.setText("");
		contentPane.add(amountInput);
		amountInput.setColumns(10);

		JLabel dAmount = new JLabel("销毁量");
		dAmount.setBounds(363, 91, 120, 19);
		dAmount.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(dAmount);

		aAmountInput = new JTextField();
		aAmountInput.setBounds(507, 91, 96, 21);
		aAmountInput.setText("");
		contentPane.add(aAmountInput);
		aAmountInput.setColumns(10);

		JLabel staff1 = new JLabel("\u4E1A\u52A1\u5458\u7F16\u53F7\uFF1A");
		staff1.setBounds(65, 90, 96, 21);
		staff1.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(staff1);

		staffInput = new JTextField();
		staffInput.setText(staff);
		staffInput.setEditable(false);
		staffInput.setBounds(182, 92, 102, 21);
		contentPane.add(staffInput);
		staffInput.setColumns(10);

		save = new JButton("\u4FDD\u5B58");
		save.setBounds(214, 193, 91, 27);
		save.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(save);

		cancel = new JButton("\u53D6\u6D88 ");
		cancel.setBounds(379, 193, 96, 27);
		cancel.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(cancel);

		JLabel source = new JLabel("\u6765\u6E90\uFF1A");
		source.setFont(new Font("楷体", Font.PLAIN, 16));
		source.setBounds(65, 149, 54, 15);
		contentPane.add(source);

		ButtonGroup bg = new ButtonGroup();

		add = new JRadioButton("\u914D\u4EF6\u8F66\u95F4\u751F\u4EA7");
		add.setFont(new Font("楷体", Font.PLAIN, 16));
		add.setBounds(184, 146, 121, 23);
		contentPane.add(add);

		backReturn = new JRadioButton("\u6210\u54C1\u8F66\u95F4\u9000\u56DE");
		backReturn.setFont(new Font("楷体", Font.PLAIN, 16));
		backReturn.setBounds(354, 146, 121, 23);
		contentPane.add(backReturn);

		save.addActionListener(this);
		cancel.addActionListener(this);
		add.addMouseListener(this);
		backReturn.addMouseListener(this);

		bg.add(add);
		bg.add(backReturn);
	}

	/**
	 * 
	 * panbie
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == save) {
			String mnum, amount, destroy, source, returnID;
			mnum = mNumInput.getText().trim();
			amount = amountInput.getText().trim();
			destroy = aAmountInput.getText().trim();
			if (add.isSelected()) {
				source = "0";
			} else {
				source = "1";
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

						sqlConnect connect = new sqlConnect();
						returnID = connect.materialInsert(mnum, amount, destroy, staff, source);
						JOptionPane.showMessageDialog(this, "创建成功,配件入库单号:" + returnID + "");
					}
				} catch (Exception ee) {
					JOptionPane.showMessageDialog(this, "编号由纯数字构成");
				}
			}
		}
		if (e.getSource() == cancel) {
			this.dispose();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根
		if (add.isSelected()) {
			aAmountInput.setEditable(true);
			aAmountInput.setText("");
		} else {
			aAmountInput.setEditable(false);
			aAmountInput.setText("0");
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
