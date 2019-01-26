package ui.infoManage;


import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sql.sqlConnect;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JDialog;
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

@SuppressWarnings("serial")
public class ProductMessage extends JDialog implements ActionListener,MouseListener{

	private JPanel contentPane;
	private JTextField tNumInput;
	JTable resTable;
	DefaultTableModel defaultTableModel;
	private JButton search;
	JPanel southPanel;
	JTextField selectField;
	JButton selectbButton;
	String returnStr;


	/**
	 * Create the frame.
	 */
	public ProductMessage(JFrame frame,boolean b) {
		
		super(frame,b);
		setTitle("\u6210\u54C1\u5E93\u5B58\u4FE1\u606F");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1229, 863);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		resTable=new JTable();
		defaultTableModel=new DefaultTableModel();
		
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
		
		southPanel=new JPanel();
		selectField=new JTextField();
		selectField.setColumns(15);
		selectField.setEditable(false);
		selectbButton=new JButton("选择车型");
		southPanel.add(new JLabel("选中车型:"));
		southPanel.add(selectField);
		southPanel.add(selectbButton);
		this.add(southPanel, BorderLayout.SOUTH);
		
		JLabel nullBottom = new JLabel("          ");
		GridBagConstraints gbc_nullBottom = new GridBagConstraints();
		gbc_nullBottom.gridx = 0;
		gbc_nullBottom.gridy = 26;
		details.add(nullBottom, gbc_nullBottom);
		search.addActionListener(this);
		selectbButton.addActionListener(this);
		resTable.addMouseListener(this);
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		scrollPane.getViewport().add(resTable);
		this.loadTable();
	}

	/**
	 * 
	 */
	private void loadTable() {
		String sql="select Tnum as 车型编号,name as 名称,color as 颜色,sex as 男女型,size as 大小 from Product";
		sqlConnect connect = null;
		try {
			connect=new sqlConnect();
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(this, "数据库连接失败");
			e.printStackTrace();
		}
		try {
			defaultTableModel=connect.getTableModel(sql);
			resTable.setModel(defaultTableModel);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "没有查询到数据");
			e.printStackTrace();
		}
	}

	/* （非 Javadoc）
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==search){
			String keyword=tNumInput.getText().trim();
			String sql="select Tnum as 车型编号,name as 名称,color as 颜色,sex as 男女型,size as 大小 from Product where Tnum like '%"+keyword+"%'";
			sqlConnect connect = null;
			try {
				connect=new sqlConnect();
			} catch (ClassNotFoundException | SQLException ee) {
				JOptionPane.showMessageDialog(this, "数据库连接失败");
				ee.printStackTrace();
			}
			try {
				defaultTableModel=connect.getTableModel(sql);
				resTable.setModel(defaultTableModel);
			} catch (Exception ee) {
				JOptionPane.showMessageDialog(this, "没有查询到数据");
				ee.printStackTrace();
			}
		}
		if(e.getSource()==selectbButton){
			if(selectField.getText().trim().equals("")){
				JOptionPane.showMessageDialog(this, "请选择车型号");
			}
			else{
				this.returnStr=selectField.getText().trim();
				this.setVisible(false);
			}
		}
	}
	
	public String getReturn(){
		return this.returnStr;
	}
	public void closeIt(){
		this.dispose();
	}

	/* （非 Javadoc）
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根
		int row=resTable.getSelectedRow();
		String a=resTable.getValueAt(row, 0).toString();
		selectField.setText(a);
	}

	/* （非 Javadoc）
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	/* （非 Javadoc）
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	/* （非 Javadoc）
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	/* （非 Javadoc）
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

}

