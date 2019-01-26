package ui.productstore;

import java.awt.BorderLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import sql.sqlConnect;

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
import java.sql.SQLException;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class StoreSearch extends JInternalFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField tNumInput;
	private JButton search;
	JTable resTable;
	DefaultTableModel defaultTableModel;


	/**
	 * Create the frame.
	 */
	public StoreSearch() {
		setTitle("\u6210\u54C1\u5E93\u5B58\u4FE1\u606F");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		resTable=new JTable();
		setBounds(100, 100, 1229, 863);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		this.setClosable(true);
		this.setResizable(true);
		
		JPanel searchDetails = new JPanel();
		searchDetails.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(searchDetails, BorderLayout.WEST);
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
		gbl_details.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_details.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		details.setLayout(gbl_details);
		
		JLabel nullTop = new JLabel("          ");
		GridBagConstraints gbc_nullTop = new GridBagConstraints();
		gbc_nullTop.gridheight = 5;
		gbc_nullTop.insets = new Insets(0, 0, 5, 0);
		gbc_nullTop.gridx = 0;
		gbc_nullTop.gridy = 0;
		details.add(nullTop, gbc_nullTop);
		
		JLabel tNum = new JLabel("\u6210\u54C1\u7F16\u53F7");
		tNum.setFont(new Font("楷体", Font.PLAIN, 16));
		GridBagConstraints gbc_tNum = new GridBagConstraints();
		gbc_tNum.insets = new Insets(0, 0, 5, 0);
		gbc_tNum.gridx = 0;
		gbc_tNum.gridy = 6;
		details.add(tNum, gbc_tNum);
		
		tNumInput = new JTextField();
		GridBagConstraints gbc_tNumInput = new GridBagConstraints();
		gbc_tNumInput.insets = new Insets(0, 0, 5, 0);
		gbc_tNumInput.fill = GridBagConstraints.BOTH;
		gbc_tNumInput.gridx = 0;
		gbc_tNumInput.gridy = 7;
		details.add(tNumInput, gbc_tNumInput);
		tNumInput.setColumns(10);
		
		JLabel nullGap = new JLabel("          ");
		GridBagConstraints gbc_nullGap = new GridBagConstraints();
		gbc_nullGap.insets = new Insets(0, 0, 5, 0);
		gbc_nullGap.gridx = 0;
		gbc_nullGap.gridy = 8;
		details.add(nullGap, gbc_nullGap);
		
		 search = new JButton("\u67E5\u8BE2");
		search.setFont(new Font("楷体", Font.PLAIN, 16));
		GridBagConstraints gbc_search = new GridBagConstraints();
		gbc_search.fill = GridBagConstraints.BOTH;
		gbc_search.insets = new Insets(0, 0, 5, 0);
		gbc_search.gridx = 0;
		gbc_search.gridy = 9;
		details.add(search, gbc_search);
		
		JLabel nullBottom = new JLabel("          ");
		GridBagConstraints gbc_nullBottom = new GridBagConstraints();
		gbc_nullBottom.gridx = 0;
		gbc_nullBottom.gridy = 26;
		details.add(nullBottom, gbc_nullBottom);
		search.addActionListener(this);
		JScrollPane jScrollPane=new JScrollPane();
		jScrollPane.getViewport().add(resTable);
		defaultTableModel=new DefaultTableModel();
		this.loadTable();
		this.getContentPane().add(jScrollPane,BorderLayout.CENTER);
	
		this.setVisible(true);
	}
	void loadTable(){
		String sql="select Tnum as 车型编号,name as 名称,amount as 库存数量,color as 颜色,sex as 男女款,size as 型号 from Product where name='-1'";
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
			e.printStackTrace();
		}
		resTable.setModel(defaultTableModel);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==search){
			resTable.removeAll();
			String keyWord=tNumInput.getText().trim();
			String sql="select Tnum as 车型编号,name as 名称,amount as 库存数量,color as 颜色,sex as 男女款,size as 型号 from Product where Tnum like '%"+keyWord+"%'";
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
