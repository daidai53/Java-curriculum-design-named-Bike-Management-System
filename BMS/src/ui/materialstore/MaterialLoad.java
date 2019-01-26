package ui.materialstore;

import java.awt.BorderLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sql.sqlConnect;
import ui.JDateChooser;
import ui.mainUIFrame;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class MaterialLoad extends JInternalFrame implements ActionListener, MouseListener {

	private JPanel Details;
	private JTextField sNumInput;
	private JTextField timeInput;
	private JTextField startTimeInput;
	private JTextField endTimeInput;
	private JTextField staffInput;
	private JButton sNumSearch;
	private JButton selectTime;
	private JButton timeSearch;
	private JButton selectStartTime;
	private JButton selectEndTime;
	private JButton periodSearch;
	private JButton staffSearch;
	JPanel southPanel;
	JTextField selectField;
	JButton moreButton;
	static int chooseSet = 0;
	static String[] titles = new String[] { "出", "入" };
	static String[] ors = new String[] { "Wnum", "snum" };
	String[] sqls = new String[] {
			"select Wnum as 出库表编号,Mnum as 配件编号,amount as 数量,staff as 操作员工号,time as 时间 from MatWithdraw",
			"select Snum as 入库表编号,Mnum as 配件编号,amount as 数量,destroy as 销毁数,staff as 操作员工编号,time as 时间,source as 来源 from MatStore" };
	JTable resTable;
	DefaultTableModel defaultTableModel;

	/**
	 * Create the frame.
	 */
	public MaterialLoad(int set) {
		chooseSet = set;
		setTitle("配件" + titles[chooseSet] + "库信息");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		this.setClosable(true);
		this.setResizable(true);
		setBounds(100, 100, 1269, 880);
		Details = new JPanel();
		Details.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Details);
		Details.setLayout(new BorderLayout(0, 0));
		resTable = new JTable();
		defaultTableModel = new DefaultTableModel();

		JPanel seachDetails = new JPanel();
		seachDetails.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Details.add(seachDetails, BorderLayout.WEST);
		seachDetails.setLayout(new BorderLayout(0, 0));

		JLabel nullLeft = new JLabel("          ");
		seachDetails.add(nullLeft, BorderLayout.WEST);

		JLabel nullRight = new JLabel("          ");
		seachDetails.add(nullRight, BorderLayout.EAST);

		JPanel search = new JPanel();
		seachDetails.add(search, BorderLayout.CENTER);
		GridBagLayout gbl_search = new GridBagLayout();
		gbl_search.columnWidths = new int[] { 93, 0 };
		gbl_search.rowHeights = new int[] { 23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0 };
		gbl_search.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_search.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		search.setLayout(gbl_search);

		JLabel nullTop = new JLabel("          ");
		GridBagConstraints gbc_nullTop = new GridBagConstraints();
		gbc_nullTop.insets = new Insets(0, 0, 5, 0);
		gbc_nullTop.gridx = 0;
		gbc_nullTop.gridy = 0;
		search.add(nullTop, gbc_nullTop);

		JLabel sNumInfo = new JLabel("以配件" + titles[chooseSet] + "库单编号为条件");
		sNumInfo.setFont(new Font("楷体", Font.PLAIN, 14));
		GridBagConstraints gbc_sNumInfo = new GridBagConstraints();
		gbc_sNumInfo.insets = new Insets(0, 0, 5, 0);
		gbc_sNumInfo.gridx = 0;
		gbc_sNumInfo.gridy = 1;
		search.add(sNumInfo, gbc_sNumInfo);

		sNumInput = new JTextField();
		GridBagConstraints gbc_sNumInput = new GridBagConstraints();
		gbc_sNumInput.insets = new Insets(0, 0, 5, 0);
		gbc_sNumInput.fill = GridBagConstraints.BOTH;
		gbc_sNumInput.gridx = 0;
		gbc_sNumInput.gridy = 2;
		search.add(sNumInput, gbc_sNumInput);
		sNumInput.setColumns(10);

		sNumSearch = new JButton("按配件" + titles[chooseSet] + "库单编号查询");
		sNumSearch.setFont(new Font("楷体", Font.PLAIN, 14));
		GridBagConstraints gbc_sNumSearch = new GridBagConstraints();
		gbc_sNumSearch.insets = new Insets(0, 0, 5, 0);
		gbc_sNumSearch.gridx = 0;
		gbc_sNumSearch.gridy = 3;
		search.add(sNumSearch, gbc_sNumSearch);

		JLabel nullRow1 = new JLabel("          ");
		GridBagConstraints gbc_nullRow1 = new GridBagConstraints();
		gbc_nullRow1.insets = new Insets(0, 0, 5, 0);
		gbc_nullRow1.gridx = 0;
		gbc_nullRow1.gridy = 4;
		search.add(nullRow1, gbc_nullRow1);

		JLabel timeInfo = new JLabel("以配件" + titles[chooseSet] + "库时间为条件");
		timeInfo.setFont(new Font("楷体", Font.PLAIN, 14));
		GridBagConstraints gbc_timeInfo = new GridBagConstraints();
		gbc_timeInfo.insets = new Insets(0, 0, 5, 0);
		gbc_timeInfo.gridx = 0;
		gbc_timeInfo.gridy = 5;
		search.add(timeInfo, gbc_timeInfo);

		timeInput = new JTextField();
		GridBagConstraints gbc_timeInput = new GridBagConstraints();
		gbc_timeInput.insets = new Insets(0, 0, 5, 0);
		gbc_timeInput.fill = GridBagConstraints.BOTH;
		gbc_timeInput.gridx = 0;
		gbc_timeInput.gridy = 6;
		search.add(timeInput, gbc_timeInput);
		timeInput.setColumns(10);

		selectTime = new JButton("\u9009\u62E9\u65F6\u95F4");
		selectTime.setFont(new Font("楷体", Font.PLAIN, 14));
		GridBagConstraints gbc_selectTime = new GridBagConstraints();
		gbc_selectTime.anchor = GridBagConstraints.NORTH;
		gbc_selectTime.insets = new Insets(0, 0, 5, 0);
		gbc_selectTime.gridx = 0;
		gbc_selectTime.gridy = 7;
		search.add(selectTime, gbc_selectTime);

		timeSearch = new JButton("按配件" + titles[chooseSet] + "库时间查询");
		timeSearch.setFont(new Font("楷体", Font.PLAIN, 14));
		GridBagConstraints gbc_timeSearch = new GridBagConstraints();
		gbc_timeSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_timeSearch.insets = new Insets(0, 0, 5, 0);
		gbc_timeSearch.gridx = 0;
		gbc_timeSearch.gridy = 8;
		search.add(timeSearch, gbc_timeSearch);

		JLabel nullRow2 = new JLabel("          ");
		GridBagConstraints gbc_nullRow2 = new GridBagConstraints();
		gbc_nullRow2.insets = new Insets(0, 0, 5, 0);
		gbc_nullRow2.gridx = 0;
		gbc_nullRow2.gridy = 9;
		search.add(nullRow2, gbc_nullRow2);

		JLabel startTime = new JLabel("\u8D77\u59CB\u65F6\u95F4");
		startTime.setFont(new Font("楷体", Font.PLAIN, 14));
		GridBagConstraints gbc_startTime = new GridBagConstraints();
		gbc_startTime.insets = new Insets(0, 0, 5, 0);
		gbc_startTime.gridx = 0;
		gbc_startTime.gridy = 10;
		search.add(startTime, gbc_startTime);

		startTimeInput = new JTextField();
		GridBagConstraints gbc_startTimeInput = new GridBagConstraints();
		gbc_startTimeInput.insets = new Insets(0, 0, 5, 0);
		gbc_startTimeInput.fill = GridBagConstraints.BOTH;
		gbc_startTimeInput.gridx = 0;
		gbc_startTimeInput.gridy = 11;
		search.add(startTimeInput, gbc_startTimeInput);
		startTimeInput.setColumns(10);

		selectStartTime = new JButton("\u9009\u62E9\u65F6\u95F4");
		selectStartTime.setFont(new Font("楷体", Font.PLAIN, 14));
		GridBagConstraints gbc_selectStartTime = new GridBagConstraints();
		gbc_selectStartTime.insets = new Insets(0, 0, 5, 0);
		gbc_selectStartTime.gridx = 0;
		gbc_selectStartTime.gridy = 12;
		search.add(selectStartTime, gbc_selectStartTime);

		JLabel endTime = new JLabel("\u7EC8\u6B62\u65F6\u95F4");
		endTime.setFont(new Font("楷体", Font.PLAIN, 14));
		GridBagConstraints gbc_endTime = new GridBagConstraints();
		gbc_endTime.insets = new Insets(0, 0, 5, 0);
		gbc_endTime.gridx = 0;
		gbc_endTime.gridy = 13;
		search.add(endTime, gbc_endTime);

		endTimeInput = new JTextField();
		GridBagConstraints gbc_endTimeInput = new GridBagConstraints();
		gbc_endTimeInput.insets = new Insets(0, 0, 5, 0);
		gbc_endTimeInput.fill = GridBagConstraints.BOTH;
		gbc_endTimeInput.gridx = 0;
		gbc_endTimeInput.gridy = 14;
		search.add(endTimeInput, gbc_endTimeInput);
		endTimeInput.setColumns(10);

		selectEndTime = new JButton("\u9009\u62E9\u65F6\u95F4");
		selectEndTime.setFont(new Font("楷体", Font.PLAIN, 14));
		GridBagConstraints gbc_selectEndTime = new GridBagConstraints();
		gbc_selectEndTime.insets = new Insets(0, 0, 5, 0);
		gbc_selectEndTime.gridx = 0;
		gbc_selectEndTime.gridy = 15;
		search.add(selectEndTime, gbc_selectEndTime);

		periodSearch = new JButton("按" + titles[chooseSet] + "库时间段查询");
		periodSearch.setFont(new Font("楷体", Font.PLAIN, 14));
		GridBagConstraints gbc_periodSearch = new GridBagConstraints();
		gbc_periodSearch.insets = new Insets(0, 0, 5, 0);
		gbc_periodSearch.gridx = 0;
		gbc_periodSearch.gridy = 16;
		search.add(periodSearch, gbc_periodSearch);

		JLabel nullRow3 = new JLabel("          ");
		GridBagConstraints gbc_nullRow3 = new GridBagConstraints();
		gbc_nullRow3.insets = new Insets(0, 0, 5, 0);
		gbc_nullRow3.gridx = 0;
		gbc_nullRow3.gridy = 17;
		search.add(nullRow3, gbc_nullRow3);

		JLabel staffInfo = new JLabel("\u4EE5\u4E1A\u52A1\u5458\u7F16\u53F7\u4E3A\u6761\u4EF6");
		staffInfo.setFont(new Font("楷体", Font.PLAIN, 14));
		GridBagConstraints gbc_staffInfo = new GridBagConstraints();
		gbc_staffInfo.insets = new Insets(0, 0, 5, 0);
		gbc_staffInfo.gridx = 0;
		gbc_staffInfo.gridy = 18;
		search.add(staffInfo, gbc_staffInfo);

		staffInput = new JTextField();
		GridBagConstraints gbc_staffInput = new GridBagConstraints();
		gbc_staffInput.insets = new Insets(0, 0, 5, 0);
		gbc_staffInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_staffInput.gridx = 0;
		gbc_staffInput.gridy = 19;
		search.add(staffInput, gbc_staffInput);
		staffInput.setColumns(10);

		staffSearch = new JButton("\u6309\u4E1A\u52A1\u5458\u7F16\u53F7\u67E5\u8BE2");
		staffSearch.setFont(new Font("楷体", Font.PLAIN, 14));
		GridBagConstraints gbc_按业务员编号查询 = new GridBagConstraints();
		gbc_按业务员编号查询.insets = new Insets(0, 0, 5, 0);
		gbc_按业务员编号查询.gridx = 0;
		gbc_按业务员编号查询.gridy = 20;
		search.add(staffSearch, gbc_按业务员编号查询);

		JLabel nullBottom = new JLabel("          ");
		GridBagConstraints gbc_nullBottom = new GridBagConstraints();
		gbc_nullBottom.gridx = 0;
		gbc_nullBottom.gridy = 27;
		search.add(nullBottom, gbc_nullBottom);
		southPanel = new JPanel();
		selectField = new JTextField();
		selectField.setColumns(15);
		moreButton = new JButton("更多");
		southPanel.add(new JLabel("选中编号:"));
		southPanel.add(selectField);
		southPanel.add(moreButton);
		this.getContentPane().add(southPanel, BorderLayout.SOUTH);

		timeInput.setEditable(false);
		startTimeInput.setEditable(false);
		endTimeInput.setEditable(false);
		selectField.setEditable(false);

		sNumSearch.addActionListener(this);
		selectTime.addActionListener(this);
		selectStartTime.addActionListener(this);
		selectEndTime.addActionListener(this);
		periodSearch.addActionListener(this);
		staffSearch.addActionListener(this);
		moreButton.addActionListener(this);
		resTable.addMouseListener(this);

		JScrollPane scrollPane = new JScrollPane();
		Details.add(scrollPane, BorderLayout.CENTER);
		scrollPane.getViewport().add(resTable);
		this.loadTable();

		this.setVisible(true);
	}

	private void loadTable() {
		sqlConnect connect = null;
		try {
			connect = new sqlConnect();
		} catch (ClassNotFoundException | SQLException e1) {
			JOptionPane.showMessageDialog(this, "数据库连接失败");
			e1.printStackTrace();
		}
		try {
			defaultTableModel = connect.getTableModel(sqls[chooseSet] + " where staff = '-1'");
			resTable.setModel(defaultTableModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sNumSearch) {
			String snum = sNumInput.getText().trim();
			resTable.removeAll();
			String sql = sqls[chooseSet] + " where " + ors[chooseSet] + " like '%" + snum + "%'";
			sqlConnect connect = null;
			try {
				connect = new sqlConnect();
			} catch (ClassNotFoundException | SQLException e2) {
				JOptionPane.showMessageDialog(this, "数据库连接失败");
				e2.printStackTrace();
			}
			defaultTableModel = new DefaultTableModel();
			try {
				defaultTableModel = connect.getTableModel(sql);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			resTable.setModel(defaultTableModel);
			if (defaultTableModel.getRowCount() == 0) {
				JOptionPane.showMessageDialog(this, "没有查询到记录");
			}
		}
		if (e.getSource() == timeSearch) {
			if (timeInput.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "请选择时间");
			} else {
				resTable.removeAll();
				String sql = sqls[chooseSet] + " where datediff(day,time,'" + timeInput.getText().trim() + "')=0";
				sqlConnect connect = null;
				try {
					connect = new sqlConnect();
				} catch (ClassNotFoundException | SQLException e2) {
					JOptionPane.showMessageDialog(this, "数据库连接失败");
					e2.printStackTrace();
				}
				defaultTableModel = new DefaultTableModel();
				try {
					defaultTableModel = connect.getTableModel(sql);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				resTable.setModel(defaultTableModel);
				if (defaultTableModel.getRowCount() == 0) {
					JOptionPane.showMessageDialog(this, "没有查询到记录");
				}
			}
		}
		if (e.getSource() == periodSearch) {
			if (startTimeInput.getText().trim().equals("") || endTimeInput.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "请选择起始时间");
			} else {
				resTable.removeAll();
				String sql = sqls[chooseSet] + " where time between '" + startTimeInput.getText().trim() + "' and '"
						+ endTimeInput.getText().trim() + "'";
				sqlConnect connect = null;
				try {
					connect = new sqlConnect();
				} catch (ClassNotFoundException | SQLException e2) {
					JOptionPane.showMessageDialog(this, "数据库连接失败");
					e2.printStackTrace();
				}
				defaultTableModel = new DefaultTableModel();
				try {
					defaultTableModel = connect.getTableModel(sql);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				resTable.setModel(defaultTableModel);
				if (defaultTableModel.getRowCount() == 0) {
					JOptionPane.showMessageDialog(this, "没有查询到记录");
				}
			}
		}
		if (e.getSource() == staffSearch) {
			String staff = staffInput.getText().trim();

			if (staff.equals("")) {
				JOptionPane.showMessageDialog(this, "请填写员工编号");
			} else if (staff.length() != 8) {
				JOptionPane.showMessageDialog(this, "员工编号长度为8位");
			} else {
				try {
					@SuppressWarnings("unused")
					long a = Long.parseLong(staff);
					resTable.removeAll();
					String sql = sqls[chooseSet] + " where staff = '" + staff + "'";
					sqlConnect connect = new sqlConnect();
					defaultTableModel = new DefaultTableModel();
					try {
						defaultTableModel = connect.getTableModel(sql);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					resTable.setModel(defaultTableModel);
					if (defaultTableModel.getRowCount() == 0) {
						JOptionPane.showMessageDialog(this, "没有查询到记录");
					}
				} catch (Exception ee) {
					JOptionPane.showMessageDialog(this, "员工编号为纯数字");
				}

			}
		}
		if (e.getSource() == selectTime) {
			new JDateChooser(timeInput);
		}
		if (e.getSource() == selectStartTime) {
			new JDateChooser(startTimeInput);
		}
		if (e.getSource() == selectEndTime) {
			new JDateChooser(endTimeInput);
		}
		if (e.getSource() == moreButton) {
			String keyWord = selectField.getText().trim();
			if (keyWord.equals("")) {
				JOptionPane.showMessageDialog(this, "请选择一条记录");
			} else {
				if(this.chooseSet==0){
					mainUIFrame.desktop.add(new MaterialUnloadRevise(keyWord));
					this.loadTable();
				}
				if(this.chooseSet==1){
					mainUIFrame.desktop.add(new MaterialLoadRevise(keyWord));
					this.loadTable();
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int r = resTable.getSelectedRow();
		String ids = resTable.getValueAt(r, 0).toString().trim();
		selectField.setText(ids);
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
