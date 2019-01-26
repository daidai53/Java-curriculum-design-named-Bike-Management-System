package ui.staffUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sql.sqlConnect;
import ui.mainUIFrame;

public class SaleDdepartment extends JInternalFrame implements ActionListener,MouseListener{
	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int chooseSet;
	private JTable table;
	JTextField staffField,selectedField;
	JButton queryButton,moreButton,addButton;
	JPanel leftJPanel,containerPanel,botPanel;
	private DefaultTableModel defaultTableModel;
	static String[] titles=new String[]{"��ҵ��������Ϣ","���ۿƹ���Ա��Ϣ","�����ƻ��ƹ���Ա��Ϣ","����������Ա��Ϣ","��Ʒ�������Ա��Ϣ","��������Ա��Ϣ","��Ʒ�����Ա��Ϣ","ϵͳ����Ա��Ϣ"};
	static String[] titles2=new String[]{"��ҵ������","���ۿƹ���Ա","�����ƻ��ƹ���Ա","����������Ա","��Ʒ�������Ա","��������Ա","��Ʒ�����Ա","ϵͳ����Ա"};
	static String[] idBegin=new String[]{"17","12","11","13","14","15","16","10"};
	
	/**
	 * Create the frame.
	 */
	public SaleDdepartment(int a) {
		super(titles[a]);
		chooseSet=a;
		defaultTableModel=new DefaultTableModel();
		setSize(800,500);
		getContentPane().setLayout(null);
		this.setLayout(new BorderLayout());

	    setClosable(true);		//�������йرհ�ť
	    this.setMaximizable(true);
	    this.setResizable(true);
	    
	    staffField=new JTextField();
	    queryButton=new JButton("��ѯ");
	    moreButton=new JButton("����");
	    addButton=new JButton("����");
	    leftJPanel=new JPanel();
	    containerPanel=new JPanel();
	    leftJPanel.setLayout(new GridLayout(10, 3,10,10));
	    this.add(containerPanel,BorderLayout.WEST);
	    containerPanel.add(new JLabel("             "),BorderLayout.WEST);
	    containerPanel.add(leftJPanel,BorderLayout.CENTER);	    
	    containerPanel.add(new JLabel("             "),BorderLayout.EAST);
	    leftJPanel.add(new JLabel(""));
	    leftJPanel.add(new JLabel(""));
	    leftJPanel.add(new JLabel("Ա�����"));
	    leftJPanel.add(staffField);
	    leftJPanel.add(queryButton);
	    leftJPanel.add(moreButton);
	    leftJPanel.add(addButton);
	    botPanel=new JPanel();
	    this.add(botPanel, BorderLayout.SOUTH);
	    botPanel.add(new JLabel("��ǰѡ��Ա�����"));
	    selectedField=new JTextField();
	    selectedField.setEditable(false);
	    selectedField.setColumns(15);
	    botPanel.add(selectedField);
	    staffField.setColumns(10);
	    queryButton.setBackground(Color.white);
	    moreButton.setBackground(Color.white);
	    addButton.setBackground(Color.white);
	    queryButton.addActionListener(this);
	    moreButton.addActionListener(this);
	    addButton.addActionListener(this);
	    
	    table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSize(500,300);
		table.setModel(defaultTableModel);
		table.addMouseListener(this);
	    String sql=null;
		if(chooseSet!=7){
		sql="select staff as "+titles2[chooseSet]+"���,staff.name as "+titles2[chooseSet]+"����,sex as �Ա�,mark as ��ְ״̬,contact as ��ϵ��ʽ   "
				+ "from staff "
				+ "where staff='99999999'";
		}
		else{
		sql="select staff as "+titles2[chooseSet]+"���,staff.name as "+titles2[chooseSet]+"����,sex as �Ա�,mark as ��ְ״̬,contact as ��ϵ��ʽ   "
					+ "from staff "
					+ "where staff='99999999'";
		}
		sqlConnect sqlConnect = null;
		try {
			sqlConnect = new sqlConnect();
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(this, "���ݿ�����ʧ��");
			e.printStackTrace();
		}
		try{
			defaultTableModel=sqlConnect.getTableModel(sql);
		}catch(Exception ee){
			System.out.println("��ѯ����");
		}
		table.setModel(defaultTableModel);
	    
	    
		
	    JScrollPane jScrollPane=new JScrollPane();
	    jScrollPane.setBounds(150,50,500,300);
	    jScrollPane.getViewport().add(table,BorderLayout.CENTER);
	   
		
	    this.getContentPane().add(jScrollPane);

	}


	/**
	 * 
	 * ��ť��Ӧ�¼�
	 * 
	 * 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==queryButton){
			selectedField.setText("");
			String sql="select staff as "+titles2[chooseSet]+"���,staff.name as "+titles2[chooseSet]+"����,sex as �Ա�,mark as ��ְ״̬,contact as ��ϵ��ʽ "
					+ "from staff "
					+ "where staff like '"+idBegin[chooseSet]+"%' and staff like '%"+staffField.getText()+"%'";
			sqlConnect sqlConnect = null;
			try {
				sqlConnect = new sqlConnect();
			} catch (ClassNotFoundException | SQLException e1) {
				JOptionPane.showMessageDialog(this, "���ݿ�����ʧ��");
				e1.printStackTrace();
			}
			try{
				defaultTableModel=sqlConnect.getTableModel(sql);
			}catch(Exception ee){
				System.out.println("��ѯ����");
			}
			table.setModel(defaultTableModel);
			if(defaultTableModel.getRowCount()==0){
				JOptionPane.showMessageDialog(this, "û�и�"+titles2[chooseSet]+"");
			}
		}
		if(e.getSource()==moreButton){
			if(selectedField.getText().equals("")){
				JOptionPane.showMessageDialog(this, "����ѡ��"+titles2[chooseSet]+"���");
			}else{
				mainUIFrame.desktop.add(new Revise_AddInfo(selectedField.getText())); 
				String sql="select staff as "+titles2[chooseSet]+"���,staff.name as "+titles2[chooseSet]+"����,sex as �Ա�,mark as ��ְ״̬,contact as ��ϵ��ʽ  "
						+ "from staff "
						+ "where staff='99999999'";
				sqlConnect sqlConnect = null;
				try {
					sqlConnect = new sqlConnect();
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(this, "���ݿ�����ʧ��");
					e1.printStackTrace();
				}
				try{
					defaultTableModel=sqlConnect.getTableModel(sql);
				}catch(Exception ee){
					System.out.println("��ѯ����");
					JOptionPane.showMessageDialog(this, "��ѯ����");
				}
				table.setModel(defaultTableModel);
			}
			
		}
		if(e.getSource()==addButton){
			mainUIFrame.desktop.add(new Revise_AddInfo(""));
		}
	}

	/**
	 * 
	 * ����������ݵ�ѡ��
	 * 
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO �Զ����ɵķ������
		int r=table.getSelectedRow();
		String staffIDS=table.getValueAt(r, 0).toString();
		selectedField.setText(staffIDS);
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
	
	
	public class Revise_AddInfo extends JInternalFrame implements ActionListener{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JPanel ReviseAndAddInfo;
		private JTextField staffInput;
		private JTextField nameInput;
		private JTextField contactInput;
		private JLabel staff;
		private JLabel name;
		private JLabel sex;
		private JRadioButton male;
		private JRadioButton female;
		private JLabel contact;
		private JComponent state;
		private ButtonGroup sexButtonGroup,markButtonGroup;
		private JRadioButton work;
		private JRadioButton dimission;
		private JButton save;
		private JButton add;
		private JButton back;
		String staffID;
		private String password;


		/**
		 * Create the frame.
		 */
		public Revise_AddInfo(String staffIDs) {
			String titleHead;
			if(staffIDs.equals("")){
				titleHead="����";
			}
			else{
				titleHead="�޸�";
			}
			setTitle(titleHead+titles2[chooseSet]);
			this.staffID=staffIDs;
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setBounds(100, 100, 467, 304);
			ReviseAndAddInfo = new JPanel();
			ReviseAndAddInfo.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(ReviseAndAddInfo);
			ReviseAndAddInfo.setLayout(null);
			
			staff = new JLabel("��ź���λ:");
			staff.setFont(new Font("����", Font.PLAIN, 16));
			staff.setBounds(16, 31, 96, 19);
			ReviseAndAddInfo.add(staff);
			
			 name = new JLabel("\u5458\u5DE5\u59D3\u540D\uFF1A");
			name.setFont(new Font("����", Font.PLAIN, 16));
			name.setBounds(221, 32, 87, 16);
			ReviseAndAddInfo.add(name);
			
			 sex = new JLabel("\u5458\u5DE5\u6027\u522B\uFF1A");
			sex.setFont(new Font("����", Font.PLAIN, 16));
			sex.setBounds(16, 87, 96, 19);
			ReviseAndAddInfo.add(sex);
			
			 male = new JRadioButton("\u7537");
			male.setFont(new Font("����", Font.PLAIN, 16));
			male.setBounds(115, 86, 41, 20);
			ReviseAndAddInfo.add(male);
			
			 female = new JRadioButton("\u5973");
			female.setFont(new Font("����", Font.PLAIN, 16));
			female.setBounds(165, 86, 41, 21);
			ReviseAndAddInfo.add(female);
			sexButtonGroup=new ButtonGroup();
			sexButtonGroup.add(male);
			sexButtonGroup.add(female);
			
			 contact = new JLabel("\u8054\u7CFB\u65B9\u5F0F\uFF1A");
			contact.setFont(new Font("����", Font.PLAIN, 16));
			contact.setBounds(221, 87, 80, 19);
			ReviseAndAddInfo.add(contact);
			
			 state = new JLabel("\u5728\u804C\u60C5\u51B5\uFF1A");
			state.setFont(new Font("����", Font.PLAIN, 16));
			state.setBounds(94, 151, 87, 19);
			ReviseAndAddInfo.add(state);
			
			 markButtonGroup=new ButtonGroup();
			
			 work = new JRadioButton("\u5728\u804C");
			work.setFont(new Font("����", Font.PLAIN, 16));
			work.setBounds(180, 150, 66, 20);
			ReviseAndAddInfo.add(work);
			
			 dimission = new JRadioButton("\u79BB\u804C");
			dimission.setFont(new Font("����", Font.PLAIN, 16));
			dimission.setBounds(248, 150, 66, 20);
			ReviseAndAddInfo.add(dimission);
			
			markButtonGroup.add(work);
			markButtonGroup.add(dimission);
			
			staffInput = new JTextField();
			staffInput.setBounds(122, 31, 89, 19);
			ReviseAndAddInfo.add(staffInput);
			staffInput.setColumns(10);
			staffInput.setEditable(false);
			
			nameInput = new JTextField();
			nameInput.setText("");
			nameInput.setBounds(299, 31, 104, 19);
			ReviseAndAddInfo.add(nameInput);
			nameInput.setColumns(10);
			
			contactInput = new JTextField();
			contactInput.setBounds(299, 87, 104, 20);
			ReviseAndAddInfo.add(contactInput);
			contactInput.setColumns(10);
			
			 save = new JButton("\u4FDD\u5B58");
			save.setFont(new Font("����", Font.PLAIN, 16));
			save.setBounds(122, 204, 93, 23);
			ReviseAndAddInfo.add(save);
			
			 add = new JButton("\u65B0\u589E");
			add.setFont(new Font("����", Font.PLAIN, 16));
			add.setBounds(122, 204, 93, 23);
			ReviseAndAddInfo.add(add);
			
			
			 back = new JButton("\u8FD4\u56DE");
			back.setFont(new Font("����", Font.PLAIN, 16));
			back.setBounds(234, 204, 93, 23);
			ReviseAndAddInfo.add(back);
			save.addActionListener(this);
			add.addActionListener(this);
			back.addActionListener(this);
			if(staffIDs.equals("")){
				save.setVisible(false);
				state.setVisible(false);
				work.setVisible(false);
				dimission.setVisible(false);
			}else{
				add.setVisible(false);
			this.load();
			}
			this.setVisible(true);
		}
		private void load(){
			String sql="select * from staff where staff ='"+staffID+"'";
			sqlConnect sqlConnect = null;
			try {
				sqlConnect = new sqlConnect();
			} catch (ClassNotFoundException | SQLException e1) {
				JOptionPane.showMessageDialog(this, "���ݿ�����ʧ��");
				e1.printStackTrace();
			}
			ResultSet rSet=null;
			try {
				rSet=sqlConnect.getResultSet(sql);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Ա����Ϣ����ʧ��");
				e.printStackTrace();
			}
			try {
				while(rSet.next()){
					staffInput.setText(staffID.substring(2,8));
					nameInput.setText(rSet.getString(2));
					System.out.println(rSet.getString(3));
					if(rSet.getString(3).trim().equals("��")){
						male.setSelected(true);
					}else {
						female.setSelected(true);
					}
					if(rSet.getString(4).trim().equals("0")){
						work.setSelected(true);
					}else{
						dimission.setSelected(true);
					}
					contactInput.setText(rSet.getString(5));
					password=rSet.getString(6);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==save){
					String name=nameInput.getText().trim();
					String contact=contactInput.getText().trim();
					String sex,mark;
					if(male.isSelected()==true){
						sex="��";
					}else{
						sex="Ů";
					}
					if(work.isSelected()==true){
						mark="0";
					}else{
						mark="1";
					}
					try{
						@SuppressWarnings("unused")
						long a=Long.parseLong(contact);
						
						
						if(name.length()>10){
							JOptionPane.showMessageDialog(this, "�������ܴ���10���ַ�");
						}else if(contact.length()>11){
							JOptionPane.showMessageDialog(this, "�绰���볤��С��11");
						}else{
							String sql="exec update_staff '"+this.staffID+"','"+name+"','"+sex+"','"+contact+"','"+password+"','"+mark+"'";
							sqlConnect sqlConnect=new sqlConnect();
							try {
								sqlConnect.executeSql(sql);
								JOptionPane.showMessageDialog(this, "����ɹ�!");
								this.dispose();
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(this, "����ʧ��!");
								e1.printStackTrace();
							}
						}
					}catch(Exception ee){
						JOptionPane.showMessageDialog(this, "�绰����Ϊ������");
					}
					
					
					

			}
			if(e.getSource()==add){
				String name=nameInput.getText().trim();
				String contact=contactInput.getText().trim();
				String sex;
				if(male.isSelected()==true){
					sex="��";
				}else{
					sex="Ů";
				}
				try{
					@SuppressWarnings("unused")
					long a=Long.parseLong(contact);
					
					
					if(name.length()>10){
						JOptionPane.showMessageDialog(this, "�������ܴ���10���ַ�");
					}else if(contact.length()>11){
						JOptionPane.showMessageDialog(this, "�绰���볤��С��11");
					}else{
						String rSel=null;
						sqlConnect sqlConnect=new sqlConnect();
						try {
							rSel=sqlConnect.createStaff(idBegin[chooseSet], name, sex, contact, "0000");
							JOptionPane.showMessageDialog(this, "�����ɹ�!Ա�����Ϊ "+rSel+",��ʼ����Ϊ 0000");
							this.dispose();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(this, "����ʧ��!");
							e1.printStackTrace();
						}
					}
				}catch(Exception ee){
					JOptionPane.showMessageDialog(this, "�绰����Ϊ������");
				}
			}
			if(e.getSource()==back){
				this.dispose();
			}
		}
	}

}
