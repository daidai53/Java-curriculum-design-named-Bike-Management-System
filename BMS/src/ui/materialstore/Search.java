package ui.materialstore;

import java.awt.BorderLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sql.sqlConnect;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class Search extends JInternalFrame implements ActionListener {

	private JPanel Details;
	private JTextField mNumInput;
	JTable resTable;
	DefaultTableModel defaultTableModel;
	private JButton search;

	/**
	 * Create the frame.
	 */
	public Search() {
		setTitle("\u914D\u4EF6\u5E93\u5B58\u67E5\u8BE2");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1053, 832);
		this.setClosable(true);
		this.setResizable(true);
		resTable=new JTable();
		defaultTableModel=new DefaultTableModel();
		Details = new JPanel();
		Details.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Details);
		Details.setLayout(new BorderLayout(0, 0));
		
		JPanel searchDetails = new JPanel();
		searchDetails.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		Details.add(searchDetails, BorderLayout.WEST);
		searchDetails.setLayout(new BorderLayout(0, 0));
		
		JLabel nullLeft = new JLabel("          ");
		searchDetails.add(nullLeft, BorderLayout.WEST);
		
		JLabel nullRight = new JLabel("          ");
		searchDetails.add(nullRight, BorderLayout.EAST);
		
		JPanel details = new JPanel();
		searchDetails.add(details, BorderLayout.CENTER);
		GridBagLayout gbl_details = new GridBagLayout();
		gbl_details.columnWidths = new int[]{93, 0};
		gbl_details.rowHeights = new int[]{23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_details.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_details.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		details.setLayout(gbl_details);
		
		JLabel nullTop = new JLabel("          ");
		GridBagConstraints gbc_nullTop = new GridBagConstraints();
		gbc_nullTop.insets = new Insets(0, 0, 5, 0);
		gbc_nullTop.gridx = 0;
		gbc_nullTop.gridy = 1;
		details.add(nullTop, gbc_nullTop);
		
		JLabel mNum = new JLabel("\u914D\u4EF6\u7F16\u53F7");
		mNum.setFont(new Font("楷体", Font.PLAIN, 16));
		GridBagConstraints gbc_mNum = new GridBagConstraints();
		gbc_mNum.insets = new Insets(0, 0, 5, 0);
		gbc_mNum.gridx = 0;
		gbc_mNum.gridy = 2;
		details.add(mNum, gbc_mNum);
		
		mNumInput = new JTextField();
		GridBagConstraints gbc_mNumInput = new GridBagConstraints();
		gbc_mNumInput.insets = new Insets(0, 0, 5, 0);
		gbc_mNumInput.fill = GridBagConstraints.BOTH;
		gbc_mNumInput.gridx = 0;
		gbc_mNumInput.gridy = 3;
		details.add(mNumInput, gbc_mNumInput);
		mNumInput.setColumns(10);
		
		JLabel Gap = new JLabel("          ");
		GridBagConstraints gbc_Gap = new GridBagConstraints();
		gbc_Gap.insets = new Insets(0, 0, 5, 0);
		gbc_Gap.gridx = 0;
		gbc_Gap.gridy = 4;
		details.add(Gap, gbc_Gap);
		
		 search = new JButton("\u67E5\u8BE2");
		search.setFont(new Font("楷体", Font.PLAIN, 16));
		GridBagConstraints gbc_search = new GridBagConstraints();
		gbc_search.insets = new Insets(0, 0, 5, 0);
		gbc_search.fill = GridBagConstraints.BOTH;
		gbc_search.gridx = 0;
		gbc_search.gridy = 5;
		details.add(search, gbc_search);
		
		JLabel nullBottom = new JLabel("          ");
		GridBagConstraints gbc_nullBottom = new GridBagConstraints();
		gbc_nullBottom.gridx = 0;
		gbc_nullBottom.gridy = 26;
		details.add(nullBottom, gbc_nullBottom);
		
		search.addActionListener(this);
		
		JScrollPane scrollPane = new JScrollPane();
		Details.add(scrollPane, BorderLayout.CENTER);
		scrollPane.getViewport().add(resTable);
		this.loadTable();
		this.setVisible(true);
	}

	void loadTable(){
		String sql="select MatHouse.Mnum as 配件编号,mark as 正品标记, amount as 库存数量 ,name as 配件名称,tab as 指标 from MatHouse,Material "
				+ "where MatHouse.Mnum = '-1' and MatHouse.Mnum = Material.Mnum";
		sqlConnect connect = null;
		try {
			connect = new sqlConnect();
		} catch (ClassNotFoundException | SQLException e1) {
			JOptionPane.showMessageDialog(this, "数据库连接失败");
			e1.printStackTrace();
		}
		try {
			defaultTableModel=connect.getTableModel(sql);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		resTable.setModel(defaultTableModel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==search){
			resTable.removeAll();
			String keyWord=mNumInput.getText().trim();
			String sql="select MatHouse.Mnum as 配件编号,mark as 正品标记, amount as 库存数量,name as 配件名称,tab as 指标 "
					+ " from MatHouse,Material"
					+ " where MatHouse.Mnum like '%"+keyWord+"%' and MatHouse.Mnum = Material.Mnum";
			sqlConnect connect = null;
			try {
				connect = new sqlConnect();
			} catch (ClassNotFoundException | SQLException e2) {
				JOptionPane.showMessageDialog(this, "数据库连接失败");
				e2.printStackTrace();
			}
			try {
				defaultTableModel=connect.getTableModel(sql);
			} catch (Exception e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			resTable.setModel(defaultTableModel);
			if(defaultTableModel.getRowCount()==0){
				JOptionPane.showMessageDialog(this, "没有查询到记录");
			}
		}
	}

}
