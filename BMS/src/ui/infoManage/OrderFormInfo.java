package ui.infoManage;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sql.sqlConnect;
import ui.JDateChooser;
import ui.mainUIFrame;
import ui.query.QuerProcess;
import ui.query.QueryBikeProudce;
import ui.query.QueryMatProduce;
import ui.query.QueryOrderform;
import ui.query.QuerySell;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class OrderFormInfo extends JInternalFrame implements ActionListener, MouseListener {

	private JPanel Details;
	private JTextField orderNumInput;
	private JTextField timeInput;
	private JTextField timeStartInput;
	private JTextField timeEndSearch;
	private JTextField staffInput;
	private JLabel nullRow1;
	private JButton orderNumSearch;
	private JButton selectTime;
	private JButton timeSearch;
	private JButton selectStartTime;
	private JButton selectEndTime;
	private JButton periodSearch;
	private JButton staffSearch;
	JTable resTable;
	JPanel southPanel;
	JTextField selectfField;
	JButton morebButton;
	private DefaultTableModel defaultTableModel;
	static int chooseSet;
	static String[] titles = new String[] { "����", "���۵�", "�����ƻ�", "��Ʒ������", "���������" };
	static String[] loadSqls = new String[] {
			"select Onum as �������,Tnum as ���ͺ�,amount as ����,process as ����,staff as ����Ա�����,time as ʱ�� from OrderForm",
			"select SEnum as ���۵����,Tnum as ���ͺ�,amount as ����,staff as ����Ա�����,time as ʱ�� , Onum as ������� from Sell",
			"select PRnum as �����ƻ�����,Mnum as ������,amount as ��������,staff as ����Ա����� from Process",
			"select BPnum as ��Ʒ��������,Tnum as ���ͱ��,amount as ����,staff as ����Ա�����,time as ʱ�� from BikeProduce",
			"select MPnum as �����������,Mnum as ������,amount as ����,staff as ����Ա�����,time as ʱ�� from MatProduce" };
	static String[] Onums = new String[] { "Onum", "SEnum", "PRnum", "BPnum", "MPnum" };

	/**
	 * Create the frame.
	 */
	public OrderFormInfo(int set) {
		chooseSet = set;
		setTitle("" + titles[chooseSet] + "��Ϣ");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1105, 765);
		this.setClosable(true);
		this.setResizable(true);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		Details = new JPanel();
		Details.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Details);
		Details.setLayout(new BorderLayout(0, 0));

		JPanel search = new JPanel();
		Details.add(search, BorderLayout.WEST);
		search.setLayout(new BorderLayout(0, 0));

		JLabel null_left = new JLabel("");
		search.add(null_left, BorderLayout.WEST);

		JLabel null_right = new JLabel("");
		search.add(null_right, BorderLayout.EAST);

		JPanel searchDetail = new JPanel();
		searchDetail.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		search.add(searchDetail, BorderLayout.CENTER);
		GridBagLayout gbl_searchDetail = new GridBagLayout();
		gbl_searchDetail.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_searchDetail.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0 };
		gbl_searchDetail.columnWeights = new double[] { 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_searchDetail.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		searchDetail.setLayout(gbl_searchDetail);

		JLabel nullTop = new JLabel("");
		GridBagConstraints gbc_nullTop = new GridBagConstraints();
		gbc_nullTop.gridheight = 2;
		gbc_nullTop.insets = new Insets(0, 0, 5, 5);
		gbc_nullTop.gridx = 4;
		gbc_nullTop.gridy = 0;
		searchDetail.add(nullTop, gbc_nullTop);

		JLabel orderNum = new JLabel("��" + titles[chooseSet] + "���Ϊ����(����Ϊ��ѯȫ��)");
		orderNum.setFont(new Font("����", Font.PLAIN, 14));
		GridBagConstraints gbc_orderNum = new GridBagConstraints();
		gbc_orderNum.insets = new Insets(0, 0, 5, 5);
		gbc_orderNum.gridx = 4;
		gbc_orderNum.gridy = 2;
		searchDetail.add(orderNum, gbc_orderNum);

		orderNumInput = new JTextField();
		GridBagConstraints gbc_orderNumInput = new GridBagConstraints();
		gbc_orderNumInput.insets = new Insets(0, 0, 5, 5);
		gbc_orderNumInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_orderNumInput.gridx = 4;
		gbc_orderNumInput.gridy = 3;
		searchDetail.add(orderNumInput, gbc_orderNumInput);
		orderNumInput.setColumns(10);

		orderNumSearch = new JButton("��" + titles[chooseSet] + "���Ϊ������ѯ");
		orderNumSearch.setFont(new Font("����", Font.PLAIN, 14));
		GridBagConstraints gbc_orderNumSearch = new GridBagConstraints();
		gbc_orderNumSearch.insets = new Insets(0, 0, 5, 5);
		gbc_orderNumSearch.gridx = 4;
		gbc_orderNumSearch.gridy = 4;
		searchDetail.add(orderNumSearch, gbc_orderNumSearch);

		nullRow1 = new JLabel("               ");
		GridBagConstraints gbc_nullRow1 = new GridBagConstraints();
		gbc_nullRow1.insets = new Insets(0, 0, 5, 5);
		gbc_nullRow1.gridx = 4;
		gbc_nullRow1.gridy = 5;
		searchDetail.add(nullRow1, gbc_nullRow1);

		JLabel time = new JLabel("��ʱ��Ϊ������ѯ");
		time.setFont(new Font("����", Font.PLAIN, 14));
		GridBagConstraints gbc_time = new GridBagConstraints();
		gbc_time.insets = new Insets(0, 0, 5, 5);
		gbc_time.gridx = 4;
		gbc_time.gridy = 6;
		searchDetail.add(time, gbc_time);

		timeInput = new JTextField();
		GridBagConstraints gbc_timeInput = new GridBagConstraints();
		gbc_timeInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_timeInput.insets = new Insets(0, 0, 5, 5);
		gbc_timeInput.gridx = 4;
		gbc_timeInput.gridy = 7;
		searchDetail.add(timeInput, gbc_timeInput);
		timeInput.setColumns(10);

		selectTime = new JButton("ѡ��ʱ��");
		selectTime.setFont(new Font("����", Font.PLAIN, 14));
		GridBagConstraints gbc_selectTime = new GridBagConstraints();
		gbc_selectTime.insets = new Insets(0, 0, 5, 5);
		gbc_selectTime.gridx = 4;
		gbc_selectTime.gridy = 8;
		searchDetail.add(selectTime, gbc_selectTime);

		timeSearch = new JButton("��ʱ��Ϊ������ѯ");
		timeSearch.setFont(new Font("����", Font.PLAIN, 14));
		GridBagConstraints gbc_timeSearch = new GridBagConstraints();
		gbc_timeSearch.insets = new Insets(0, 0, 5, 5);
		gbc_timeSearch.gridx = 4;
		gbc_timeSearch.gridy = 9;
		searchDetail.add(timeSearch, gbc_timeSearch);

		JLabel nullRow2 = new JLabel("");
		GridBagConstraints gbc_nullRow2 = new GridBagConstraints();
		gbc_nullRow2.insets = new Insets(0, 0, 5, 5);
		gbc_nullRow2.gridx = 4;
		gbc_nullRow2.gridy = 10;
		searchDetail.add(nullRow2, gbc_nullRow2);

		JLabel InnerLeft = new JLabel("         ");
		GridBagConstraints gbc_InnerLeft = new GridBagConstraints();
		gbc_InnerLeft.insets = new Insets(0, 0, 5, 5);
		gbc_InnerLeft.gridx = 1;
		gbc_InnerLeft.gridy = 11;
		searchDetail.add(InnerLeft, gbc_InnerLeft);

		JLabel timeStart = new JLabel("��ʼʱ��");
		timeStart.setFont(new Font("����", Font.PLAIN, 14));
		GridBagConstraints gbc_timeStart = new GridBagConstraints();
		gbc_timeStart.insets = new Insets(0, 0, 5, 5);
		gbc_timeStart.gridx = 4;
		gbc_timeStart.gridy = 11;
		searchDetail.add(timeStart, gbc_timeStart);

		JLabel InnerRight = new JLabel("         ");
		GridBagConstraints gbc_InnerRight = new GridBagConstraints();
		gbc_InnerRight.insets = new Insets(0, 0, 5, 0);
		gbc_InnerRight.gridx = 6;
		gbc_InnerRight.gridy = 11;
		searchDetail.add(InnerRight, gbc_InnerRight);

		timeStartInput = new JTextField();
		GridBagConstraints gbc_timeStartInput = new GridBagConstraints();
		gbc_timeStartInput.insets = new Insets(0, 0, 5, 5);
		gbc_timeStartInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_timeStartInput.gridx = 4;
		gbc_timeStartInput.gridy = 12;
		searchDetail.add(timeStartInput, gbc_timeStartInput);
		timeStartInput.setColumns(10);

		selectStartTime = new JButton("ѡ��ʱ��");
		selectStartTime.setFont(new Font("����", Font.PLAIN, 14));
		GridBagConstraints gbc_selectStartTime = new GridBagConstraints();
		gbc_selectStartTime.insets = new Insets(0, 0, 5, 5);
		gbc_selectStartTime.gridx = 4;
		gbc_selectStartTime.gridy = 13;
		searchDetail.add(selectStartTime, gbc_selectStartTime);

		JLabel timeEnd = new JLabel("��ֹʱ��");
		timeEnd.setFont(new Font("����", Font.PLAIN, 14));
		GridBagConstraints gbc_timeEnd = new GridBagConstraints();
		gbc_timeEnd.insets = new Insets(0, 0, 5, 5);
		gbc_timeEnd.gridx = 4;
		gbc_timeEnd.gridy = 14;
		searchDetail.add(timeEnd, gbc_timeEnd);

		timeEndSearch = new JTextField();
		GridBagConstraints gbc_timeEndSearch = new GridBagConstraints();
		gbc_timeEndSearch.insets = new Insets(0, 0, 5, 5);
		gbc_timeEndSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_timeEndSearch.gridx = 4;
		gbc_timeEndSearch.gridy = 15;
		searchDetail.add(timeEndSearch, gbc_timeEndSearch);
		timeEndSearch.setColumns(10);

		selectEndTime = new JButton("ѡ��ʱ��");
		selectEndTime.setFont(new Font("����", Font.PLAIN, 14));
		GridBagConstraints gbc_selectEndTime = new GridBagConstraints();
		gbc_selectEndTime.anchor = GridBagConstraints.NORTH;
		gbc_selectEndTime.insets = new Insets(0, 0, 5, 5);
		gbc_selectEndTime.gridx = 4;
		gbc_selectEndTime.gridy = 16;
		searchDetail.add(selectEndTime, gbc_selectEndTime);

		periodSearch = new JButton("��ʱ��β�ѯ");
		periodSearch.setFont(new Font("����", Font.PLAIN, 14));
		GridBagConstraints gbc_periodSearch = new GridBagConstraints();
		gbc_periodSearch.insets = new Insets(0, 0, 5, 5);
		gbc_periodSearch.gridx = 4;
		gbc_periodSearch.gridy = 17;
		searchDetail.add(periodSearch, gbc_periodSearch);

		JLabel nullRow3 = new JLabel("             ");
		GridBagConstraints gbc_nullRow3 = new GridBagConstraints();
		gbc_nullRow3.insets = new Insets(0, 0, 5, 5);
		gbc_nullRow3.gridx = 4;
		gbc_nullRow3.gridy = 18;
		searchDetail.add(nullRow3, gbc_nullRow3);

		JLabel staff = new JLabel("��ҵ��Ա���Ϊ����");
		staff.setFont(new Font("����", Font.PLAIN, 14));
		GridBagConstraints gbc_staff = new GridBagConstraints();
		gbc_staff.insets = new Insets(0, 0, 5, 5);
		gbc_staff.gridx = 4;
		gbc_staff.gridy = 19;
		searchDetail.add(staff, gbc_staff);

		staffInput = new JTextField();
		GridBagConstraints gbc_staffInput = new GridBagConstraints();
		gbc_staffInput.insets = new Insets(0, 0, 5, 5);
		gbc_staffInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_staffInput.gridx = 4;
		gbc_staffInput.gridy = 20;
		searchDetail.add(staffInput, gbc_staffInput);
		staffInput.setColumns(10);
		timeInput.setEditable(false);
		timeStartInput.setEditable(false);
		timeEndSearch.setEditable(false);

		staffSearch = new JButton("��ҵ��Ա��Ų�ѯ");
		staffSearch.setFont(new Font("����", Font.PLAIN, 14));
		GridBagConstraints gbc_staffSearch = new GridBagConstraints();
		gbc_staffSearch.insets = new Insets(0, 0, 5, 5);
		gbc_staffSearch.gridx = 4;
		gbc_staffSearch.gridy = 21;
		searchDetail.add(staffSearch, gbc_staffSearch);

		southPanel = new JPanel();
		selectfField = new JTextField();
		selectfField.setColumns(15);
		morebButton = new JButton("����");
		southPanel.add(new JLabel("��ǰѡ��" + titles[chooseSet] + "���"));
		southPanel.add(selectfField);
		southPanel.add(morebButton);
		this.add(southPanel, BorderLayout.SOUTH);
		selectfField.setEditable(false);

		JLabel nullButtom = new JLabel("");
		GridBagConstraints gbc_nullButtom = new GridBagConstraints();
		gbc_nullButtom.gridheight = 2;
		gbc_nullButtom.insets = new Insets(0, 0, 5, 5);
		gbc_nullButtom.gridx = 4;
		gbc_nullButtom.gridy = 22;
		searchDetail.add(nullButtom, gbc_nullButtom);

		/**
		 * 
		 */
		if (chooseSet == 2) {
			timeSearch.setEnabled(false);
			periodSearch.setEnabled(false);
		}

		/**
		 * ��Ӱ�ť��Ӧ�¼�
		 */
		orderNumSearch.addActionListener(this);
		timeSearch.addActionListener(this);
		periodSearch.addActionListener(this);
		staffSearch.addActionListener(this);
		selectTime.addActionListener(this);
		selectStartTime.addActionListener(this);
		selectEndTime.addActionListener(this);
		morebButton.addActionListener(this);

		resTable = new JTable();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.getViewport().add(resTable);
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);
		scrollPane.setBackground(Color.lightGray);
		loadTable();
		resTable.addMouseListener(this);
		this.setVisible(true);
	}

	public void flush() {
		this.repaint();
	}

	void loadTable() {
		defaultTableModel = new DefaultTableModel();
		sqlConnect connect = null;
		try {
			connect = new sqlConnect();
		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(this, "��������ʧ");
			e1.printStackTrace();
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(this, "���ݿ�����ʧ��");
			e1.printStackTrace();
		}
		try {
			defaultTableModel = connect.getTableModel(loadSqls[chooseSet] + " where staff= '-1'");
		} catch (Exception e) {
			e.printStackTrace();
		}
		resTable.setModel(defaultTableModel);
	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == orderNumSearch) {
			String ordernum = orderNumInput.getText().trim();
			resTable.removeAll();
			String sql = loadSqls[chooseSet] + " where " + Onums[chooseSet] + " like '%" + ordernum + "%'";
			sqlConnect connect = null;
			try {
				connect = new sqlConnect();
			} catch (ClassNotFoundException e2) {
				JOptionPane.showMessageDialog(this, "��������ʧ");
				e2.printStackTrace();
			} catch (SQLException e2) {
				JOptionPane.showMessageDialog(this, "���ݿ�����ʧ��");
				e2.printStackTrace();
			}
			defaultTableModel = new DefaultTableModel();
			try {
				defaultTableModel = connect.getTableModel(sql);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			resTable.setModel(defaultTableModel);
			this.flush();
			if (defaultTableModel.getRowCount() == 0) {
				JOptionPane.showMessageDialog(this, "û�в�ѯ����¼");
			}
		}
		if (e.getSource() == timeSearch) {
			if (timeInput.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "��ѡ��ʱ��");
			} else {
				resTable.removeAll();
				String sql = loadSqls[chooseSet] + " where datediff(day,time,'" + timeInput.getText().trim() + "')=0";
				sqlConnect connect = null;
				try {
					connect = new sqlConnect();
				} catch (ClassNotFoundException e2) {
					JOptionPane.showMessageDialog(this, "��������ʧ");
					e2.printStackTrace();
				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(this, "���ݿ�����ʧ��");
					e2.printStackTrace();
				}
				defaultTableModel = new DefaultTableModel();
				try {
					defaultTableModel = connect.getTableModel(sql);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				resTable.setModel(defaultTableModel);
				this.flush();
				if (defaultTableModel.getRowCount() == 0) {
					JOptionPane.showMessageDialog(this, "û�в�ѯ����¼");
				}
			}
		}
		if (e.getSource() == periodSearch) {
			if (timeStartInput.getText().trim().equals("") || timeEndSearch.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "��ѡ����ʼʱ��");
			} else {
				resTable.removeAll();
				String sql = loadSqls[chooseSet] + " where time between '" + timeStartInput.getText().trim() + "' and '"
						+ timeEndSearch.getText().trim() + "'";
				sqlConnect connect = null;
				try {
					connect = new sqlConnect();
				} catch (ClassNotFoundException e2) {
					JOptionPane.showMessageDialog(this, "��������ʧ");
					e2.printStackTrace();
				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(this, "���ݿ�����ʧ��");
					e2.printStackTrace();
				}
				defaultTableModel = new DefaultTableModel();
				try {
					defaultTableModel = connect.getTableModel(sql);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				resTable.setModel(defaultTableModel);
				this.flush();
				if (defaultTableModel.getRowCount() == 0) {
					JOptionPane.showMessageDialog(this, "û�в�ѯ����¼");
				}
			}
		}
		if (e.getSource() == staffSearch) {
			String staff = staffInput.getText().trim();

			if (staff.equals("")) {
				JOptionPane.showMessageDialog(this, "����дԱ�����");
			} else if (staff.length() != 8) {
				JOptionPane.showMessageDialog(this, "Ա����ų���Ϊ8λ");
			} else {
				try {
					@SuppressWarnings("unused")
					long a = Long.parseLong(staff);
					resTable.removeAll();
					String sql = loadSqls[chooseSet] + " where staff = '" + staff + "'";
					sqlConnect connect = new sqlConnect();
					defaultTableModel = new DefaultTableModel();
					try {
						defaultTableModel = connect.getTableModel(sql);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					resTable.setModel(defaultTableModel);
					this.flush();
					if (defaultTableModel.getRowCount() == 0) {
						JOptionPane.showMessageDialog(this, "û�в�ѯ����¼");
					}
				} catch (Exception ee) {
					JOptionPane.showMessageDialog(this, "Ա�����Ϊ������");
				}

			}
		}
		if (e.getSource() == selectTime) {
			new JDateChooser(timeInput);
		}
		if (e.getSource() == selectStartTime) {
			new JDateChooser(timeStartInput);
		}
		if (e.getSource() == selectEndTime) {
			new JDateChooser(timeEndSearch);
		}
		if (e.getSource() == morebButton) {
			String keyWord = selectfField.getText().trim();
			if (keyWord.equals("")) {
				JOptionPane.showMessageDialog(this, "��ѡ��һ����¼");
			} else {
				if (this.chooseSet == 0) {
					mainUIFrame.desktop.add(new QueryOrderform(keyWord));
					this.loadTable();
				}
				if (this.chooseSet == 1) {
					mainUIFrame.desktop.add(new QuerySell(keyWord));
					this.loadTable();
				}
				if (this.chooseSet == 2) {
					mainUIFrame.desktop.add(new QuerProcess(keyWord));
					this.loadTable();
				}
				if (this.chooseSet == 3) {
					mainUIFrame.desktop.add(new QueryBikeProudce(keyWord));
					this.loadTable();
				}
				if (this.chooseSet == 4) {
					mainUIFrame.desktop.add(new QueryMatProduce(keyWord));
					this.loadTable();
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO �Զ����ɵķ������
		int r = resTable.getSelectedRow();
		String ids = resTable.getValueAt(r, 0).toString().trim();
		selectfField.setText(ids);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO �Զ����ɵķ������

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO �Զ����ɵķ������

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO �Զ����ɵķ������

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO �Զ����ɵķ������

	}
}
