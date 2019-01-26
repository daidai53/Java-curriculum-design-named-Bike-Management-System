package ui.materialstore;

import java.awt.BorderLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sql.sqlConnect;
import ui.JDateChooser;
import ui.mainUIFrame;

import javax.swing.JScrollPane;
import javax.swing.JTable;
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

import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class DestructionInfo extends JInternalFrame implements ActionListener,MouseListener{

	private JPanel Details;
	private JTextField dNumInput;
	private JTextField timeInput;
	private JTextField startTimeInput;
	private JTextField endTimeInput;
	private JTextField staffInput;
	JTable resTable;
	DefaultTableModel defaultTableModel;
	private JButton dNumSearch;
	private JButton timeSelect;
	private JButton timeSearch;
	private JButton startTimeSelect;
	private JButton endTimeSelect;
	private JButton periodSearch;
	private JButton staffSearch;
	JPanel southPanel;
	JTextField selectField;
	JButton moreButton;


	/**
	 * Create the frame.
	 */
	public DestructionInfo() {
		setTitle("\u914D\u4EF6\u9500\u6BC1\u5355\u4FE1\u606F");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		this.setClosable(true);
		this.setResizable(true);
		setBounds(100, 100, 1123, 831);
		Details = new JPanel();
		Details.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Details);
		Details.setLayout(new BorderLayout(0, 0));
		resTable=new JTable();
		defaultTableModel=new DefaultTableModel();
		
		southPanel=new JPanel();
		selectField=new JTextField();
		moreButton=new JButton("更多");
		selectField.setColumns(15);
		southPanel.add(new JLabel("当前选择的编号:"));
		southPanel.add(selectField);
		southPanel.add(moreButton);
		this.getContentPane().add(southPanel, BorderLayout.SOUTH);
		
		JPanel searchDetails = new JPanel();
		searchDetails.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Details.add(searchDetails, BorderLayout.WEST);
		searchDetails.setLayout(new BorderLayout(0, 0));
		
		JLabel nullLeft = new JLabel("          ");
		searchDetails.add(nullLeft, BorderLayout.WEST);
		
		JLabel nullRight = new JLabel("          ");
		searchDetails.add(nullRight, BorderLayout.EAST);
		
		JPanel searchInfo = new JPanel();
		searchDetails.add(searchInfo, BorderLayout.CENTER);
		GridBagLayout gbl_searchInfo = new GridBagLayout();
		gbl_searchInfo.columnWidths = new int[]{93, 0};
		gbl_searchInfo.rowHeights = new int[]{23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_searchInfo.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_searchInfo.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		searchInfo.setLayout(gbl_searchInfo);
		
		JLabel nullTop = new JLabel("          ");
		GridBagConstraints gbc_nullTop = new GridBagConstraints();
		gbc_nullTop.gridheight = 2;
		gbc_nullTop.anchor = GridBagConstraints.NORTH;
		gbc_nullTop.insets = new Insets(0, 0, 5, 0);
		gbc_nullTop.gridx = 0;
		gbc_nullTop.gridy = 0;
		searchInfo.add(nullTop, gbc_nullTop);
		
		JLabel dNumInfo = new JLabel("\u4EE5\u9500\u6BC1\u5355\u7F16\u53F7\u4E3A\u6761\u4EF6");
		dNumInfo.setFont(new Font("楷体", Font.PLAIN, 14));
		GridBagConstraints gbc_dNumInfo = new GridBagConstraints();
		gbc_dNumInfo.insets = new Insets(0, 0, 5, 0);
		gbc_dNumInfo.gridx = 0;
		gbc_dNumInfo.gridy = 2;
		searchInfo.add(dNumInfo, gbc_dNumInfo);
		
		dNumInput = new JTextField();
		dNumInput.setText("");
		GridBagConstraints gbc_dNumInput = new GridBagConstraints();
		gbc_dNumInput.insets = new Insets(0, 0, 5, 0);
		gbc_dNumInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_dNumInput.gridx = 0;
		gbc_dNumInput.gridy = 3;
		searchInfo.add(dNumInput, gbc_dNumInput);
		dNumInput.setColumns(10);
		
		 dNumSearch = new JButton("\u6309\u9500\u6BC1\u5355\u7F16\u53F7\u67E5\u8BE2");
		dNumSearch.setFont(new Font("楷体", Font.PLAIN, 14));
		GridBagConstraints gbc_dNumSearch = new GridBagConstraints();
		gbc_dNumSearch.insets = new Insets(0, 0, 5, 0);
		gbc_dNumSearch.gridx = 0;
		gbc_dNumSearch.gridy = 4;
		searchInfo.add(dNumSearch, gbc_dNumSearch);
		
		JLabel nullRow1 = new JLabel("          ");
		GridBagConstraints gbc_nullRow1 = new GridBagConstraints();
		gbc_nullRow1.insets = new Insets(0, 0, 5, 0);
		gbc_nullRow1.gridx = 0;
		gbc_nullRow1.gridy = 5;
		searchInfo.add(nullRow1, gbc_nullRow1);
		
		JLabel timeInfo = new JLabel("\u4EE5\u65F6\u95F4\u4E3A\u6761\u4EF6");
		timeInfo.setFont(new Font("楷体", Font.PLAIN, 14));
		GridBagConstraints gbc_timeInfo = new GridBagConstraints();
		gbc_timeInfo.insets = new Insets(0, 0, 5, 0);
		gbc_timeInfo.gridx = 0;
		gbc_timeInfo.gridy = 6;
		searchInfo.add(timeInfo, gbc_timeInfo);
		
		timeInput = new JTextField();
		timeInput.setEditable(false);
		GridBagConstraints gbc_timeInput = new GridBagConstraints();
		gbc_timeInput.insets = new Insets(0, 0, 5, 0);
		gbc_timeInput.fill = GridBagConstraints.BOTH;
		gbc_timeInput.gridx = 0;
		gbc_timeInput.gridy = 7;
		searchInfo.add(timeInput, gbc_timeInput);
		timeInput.setColumns(10);
		
		 timeSelect = new JButton("\u9009\u62E9\u65F6\u95F4");
		timeSelect.setFont(new Font("楷体", Font.PLAIN, 14));
		GridBagConstraints gbc_timeSelect = new GridBagConstraints();
		gbc_timeSelect.insets = new Insets(0, 0, 5, 0);
		gbc_timeSelect.gridx = 0;
		gbc_timeSelect.gridy = 8;
		searchInfo.add(timeSelect, gbc_timeSelect);
		
		 timeSearch = new JButton("\u6309\u65F6\u95F4\u67E5\u8BE2");
		timeSearch.setFont(new Font("楷体", Font.PLAIN, 14));
		GridBagConstraints gbc_timeSearch = new GridBagConstraints();
		gbc_timeSearch.fill = GridBagConstraints.BOTH;
		gbc_timeSearch.insets = new Insets(0, 0, 5, 0);
		gbc_timeSearch.gridx = 0;
		gbc_timeSearch.gridy = 9;
		searchInfo.add(timeSearch, gbc_timeSearch);
		
		JLabel nullRow2 = new JLabel("          ");
		GridBagConstraints gbc_nullRow2 = new GridBagConstraints();
		gbc_nullRow2.insets = new Insets(0, 0, 5, 0);
		gbc_nullRow2.gridx = 0;
		gbc_nullRow2.gridy = 10;
		searchInfo.add(nullRow2, gbc_nullRow2);
		
		JLabel startTime = new JLabel("\u8D77\u59CB\u65F6\u95F4");
		startTime.setFont(new Font("楷体", Font.PLAIN, 14));
		GridBagConstraints gbc_startTime = new GridBagConstraints();
		gbc_startTime.insets = new Insets(0, 0, 5, 0);
		gbc_startTime.gridx = 0;
		gbc_startTime.gridy = 11;
		searchInfo.add(startTime, gbc_startTime);
		
		startTimeInput = new JTextField();
		startTimeInput.setEditable(false);
		GridBagConstraints gbc_startTimeInput = new GridBagConstraints();
		gbc_startTimeInput.insets = new Insets(0, 0, 5, 0);
		gbc_startTimeInput.fill = GridBagConstraints.BOTH;
		gbc_startTimeInput.gridx = 0;
		gbc_startTimeInput.gridy = 12;
		searchInfo.add(startTimeInput, gbc_startTimeInput);
		startTimeInput.setColumns(10);
		
		 startTimeSelect = new JButton("\u9009\u62E9\u65F6\u95F4");
		startTimeSelect.setFont(new Font("楷体", Font.PLAIN, 14));
		GridBagConstraints gbc_startTimeSelect = new GridBagConstraints();
		gbc_startTimeSelect.insets = new Insets(0, 0, 5, 0);
		gbc_startTimeSelect.gridx = 0;
		gbc_startTimeSelect.gridy = 13;
		searchInfo.add(startTimeSelect, gbc_startTimeSelect);
		
		JLabel endTime = new JLabel("\u7EC8\u6B62\u65F6\u95F4");
		endTime.setFont(new Font("楷体", Font.PLAIN, 14));
		GridBagConstraints gbc_endTime = new GridBagConstraints();
		gbc_endTime.insets = new Insets(0, 0, 5, 0);
		gbc_endTime.gridx = 0;
		gbc_endTime.gridy = 14;
		searchInfo.add(endTime, gbc_endTime);
		
		endTimeInput = new JTextField();
		endTimeInput.setEditable(false);
		GridBagConstraints gbc_endTimeInput = new GridBagConstraints();
		gbc_endTimeInput.insets = new Insets(0, 0, 5, 0);
		gbc_endTimeInput.fill = GridBagConstraints.HORIZONTAL;
		gbc_endTimeInput.gridx = 0;
		gbc_endTimeInput.gridy = 15;
		searchInfo.add(endTimeInput, gbc_endTimeInput);
		endTimeInput.setColumns(10);
		
		 endTimeSelect = new JButton("\u9009\u62E9\u65F6\u95F4");
		endTimeSelect.setFont(new Font("楷体", Font.PLAIN, 14));
		GridBagConstraints gbc_endTimeSelect = new GridBagConstraints();
		gbc_endTimeSelect.insets = new Insets(0, 0, 5, 0);
		gbc_endTimeSelect.gridx = 0;
		gbc_endTimeSelect.gridy = 16;
		searchInfo.add(endTimeSelect, gbc_endTimeSelect);
		
		 periodSearch = new JButton("\u6309\u65F6\u95F4\u6BB5\u67E5\u8BE2");
		periodSearch.setFont(new Font("楷体", Font.PLAIN, 14));
		GridBagConstraints gbc_periodSearch = new GridBagConstraints();
		gbc_periodSearch.fill = GridBagConstraints.BOTH;
		gbc_periodSearch.insets = new Insets(0, 0, 5, 0);
		gbc_periodSearch.gridx = 0;
		gbc_periodSearch.gridy = 17;
		searchInfo.add(periodSearch, gbc_periodSearch);
		
		JLabel nullRow3 = new JLabel("          ");
		GridBagConstraints gbc_nullRow3 = new GridBagConstraints();
		gbc_nullRow3.insets = new Insets(0, 0, 5, 0);
		gbc_nullRow3.gridx = 0;
		gbc_nullRow3.gridy = 18;
		searchInfo.add(nullRow3, gbc_nullRow3);
		
		JLabel staffInfo = new JLabel("\u4EE5\u4E1A\u52A1\u5458\u7F16\u53F7\u4E3A\u6761\u4EF6");
		staffInfo.setFont(new Font("楷体", Font.PLAIN, 14));
		GridBagConstraints gbc_staffInfo = new GridBagConstraints();
		gbc_staffInfo.insets = new Insets(0, 0, 5, 0);
		gbc_staffInfo.gridx = 0;
		gbc_staffInfo.gridy = 19;
		searchInfo.add(staffInfo, gbc_staffInfo);
		
		staffInput = new JTextField();
		GridBagConstraints gbc_staffInput = new GridBagConstraints();
		gbc_staffInput.insets = new Insets(0, 0, 5, 0);
		gbc_staffInput.fill = GridBagConstraints.BOTH;
		gbc_staffInput.gridx = 0;
		gbc_staffInput.gridy = 20;
		searchInfo.add(staffInput, gbc_staffInput);
		staffInput.setColumns(10);
		
		 staffSearch = new JButton("\u6309\u4E1A\u52A1\u5458\u7F16\u53F7\u67E5\u8BE2");
		staffSearch.setFont(new Font("楷体", Font.PLAIN, 14));
		GridBagConstraints gbc_staffSearch = new GridBagConstraints();
		gbc_staffSearch.fill = GridBagConstraints.BOTH;
		gbc_staffSearch.insets = new Insets(0, 0, 5, 0);
		gbc_staffSearch.gridx = 0;
		gbc_staffSearch.gridy = 21;
		searchInfo.add(staffSearch, gbc_staffSearch);
		
		JLabel nullBottom = new JLabel("          ");
		GridBagConstraints gbc_nullBottom = new GridBagConstraints();
		gbc_nullBottom.gridx = 0;
		gbc_nullBottom.gridy = 25;
		searchInfo.add(nullBottom, gbc_nullBottom);
		selectField.setEditable(false);
		
		dNumSearch.addActionListener(this);
		timeSearch.addActionListener(this);
		periodSearch.addActionListener(this);
		staffSearch.addActionListener(this);
		timeSelect.addActionListener(this);
		startTimeSelect.addActionListener(this);
		endTimeSelect.addActionListener(this);
		moreButton.addActionListener(this);
		
		JScrollPane scrollPane = new JScrollPane();
		Details.add(scrollPane, BorderLayout.CENTER);
		scrollPane.getViewport().add(resTable);
		resTable.addMouseListener(this);
		this.loadTable();
		this.setVisible(true);
	}

	private void loadTable() {
		String sql="select Dnum as 销毁单编号,Mnum as 配件编号,amount as 销毁数量,staff as 操作员工编号,time as 时间  from MatDestroy where Mnum='-1'";
		sqlConnect connect = null;
		try {
			connect = new sqlConnect();
		} catch (ClassNotFoundException | SQLException e1) {
			JOptionPane.showMessageDialog(this, "数据库连接失败");
			e1.printStackTrace();
		}
		try {
			defaultTableModel=connect.getTableModel(sql);
			resTable.setModel(defaultTableModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==dNumSearch){
			String dnum=dNumInput.getText().trim();
			resTable.removeAll();
			String sql="select Dnum as 销毁单编号,Mnum as 配件编号,amount as 销毁数量,staff as 操作员工编号,time as 时间  from MatDestroy"+" where dnum like '%"+dnum+"%'";
			sqlConnect connect = null;
			try {
				connect = new sqlConnect();
			} catch (ClassNotFoundException | SQLException e2) {
				JOptionPane.showMessageDialog(this, "数据库连接失败");
				e2.printStackTrace();
			}
			defaultTableModel=new DefaultTableModel();
			try {
				defaultTableModel=connect.getTableModel(sql);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			resTable.setModel(defaultTableModel);
			if(defaultTableModel.getRowCount()==0){
				JOptionPane.showMessageDialog(this, "没有查询到记录");
			}
		}
		if(e.getSource()==timeSearch){
			if(timeInput.getText().trim().equals("")){
				JOptionPane.showMessageDialog(this, "请选择时间");
			}else{
				resTable.removeAll();
				String sql="select Dnum as 销毁单编号,Mnum as 配件编号,amount as 销毁数量,staff as 操作员工编号,time as 时间  from MatDestroy"+" where datediff(day,time,'"+timeInput.getText().trim()+"')=0";
				sqlConnect connect = null;
				try {
					connect = new sqlConnect();
				} catch (ClassNotFoundException | SQLException e2) {
					JOptionPane.showMessageDialog(this, "数据库连接失败");
					e2.printStackTrace();
				}
				 defaultTableModel=new DefaultTableModel();
				try {
					defaultTableModel=connect.getTableModel(sql);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				resTable.setModel(defaultTableModel);
				if(defaultTableModel.getRowCount()==0){
					JOptionPane.showMessageDialog(this, "没有查询到记录");
				}
			}
		}
		if(e.getSource()==periodSearch){
			if(startTimeInput.getText().trim().equals("")||endTimeInput.getText().trim().equals("")){
				JOptionPane.showMessageDialog(this, "请选择起始时间");
			}else{
				resTable.removeAll();
				String sql="select Dnum as 销毁单编号,Mnum as 配件编号,amount as 销毁数量,staff as 操作员工编号,time as 时间  from MatDestroy"+" where time between '"+startTimeInput.getText().trim()+"' and '"+endTimeInput.getText().trim()+"'";
				sqlConnect connect = null;
				try {
					connect = new sqlConnect();
				} catch (ClassNotFoundException | SQLException e2) {
					JOptionPane.showMessageDialog(this, "数据库连接失败");
					e2.printStackTrace();
				}
				 defaultTableModel=new DefaultTableModel();
				try {
					defaultTableModel=connect.getTableModel(sql);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				resTable.setModel(defaultTableModel);
				if(defaultTableModel.getRowCount()==0){
					JOptionPane.showMessageDialog(this, "没有查询到记录");
				}
			}
		}
		if(e.getSource()==staffSearch){
			String staff=staffInput.getText().trim();

			if(staff.equals("")){
				JOptionPane.showMessageDialog(this, "请填写员工编号");
			}else if(staff.length()!=8){
				JOptionPane.showMessageDialog(this, "员工编号长度为8位");
			}else{
				try{
					@SuppressWarnings("unused")
					long a=Long.parseLong(staff);
					resTable.removeAll();
					String sql="select Dnum as 销毁单编号,Mnum as 配件编号,amount as 销毁数量,staff as 操作员工编号,time as 时间  from MatDestroy"+" where staff = '"+staff+"'";
					sqlConnect connect=new sqlConnect();
					 defaultTableModel=new DefaultTableModel();
					try {
						defaultTableModel=connect.getTableModel(sql);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					resTable.setModel(defaultTableModel);
					if(defaultTableModel.getRowCount()==0){
						JOptionPane.showMessageDialog(this, "没有查询到记录");
					}
				}catch(Exception ee){
					JOptionPane.showMessageDialog(this, "员工编号为纯数字");
				}

			}
		}
		if(e.getSource()==timeSelect){
			new JDateChooser(timeInput);
		}
		if(e.getSource()==startTimeSelect){
			new JDateChooser(startTimeInput);
		}
		if(e.getSource()==endTimeSelect){
			new JDateChooser(endTimeInput);
		}
		if(e.getSource()==moreButton){
			String keyWord=selectField.getText().trim();
			if(keyWord.equals("")){
				JOptionPane.showMessageDialog(this, "请选择一条记录");
			}else{
				mainUIFrame.desktop.add(new Destruction(1, keyWord));
				this.loadTable();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
				int r=resTable.getSelectedRow();
				String ids=resTable.getValueAt(r, 0).toString().trim();
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
