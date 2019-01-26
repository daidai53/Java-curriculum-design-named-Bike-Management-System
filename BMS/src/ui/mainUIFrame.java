package ui;
/**
 * @author ���Sͥ 
 */

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import common.*;
import sql.sqlConnect;
import ui.infoManage.OrderFormInfo;
import ui.infoManage.Revise_AddOrderInfo;
import ui.materialstore.Destruction;
import ui.materialstore.DestructionInfo;
import ui.materialstore.MaterialLoad;
import ui.materialstore.MaterialLoadInfo;
import ui.materialstore.Search;
import ui.productstore.StoreSearch;
import ui.staffUI.SaleDdepartment;
import ui.systemMaintain.PasswordChange;
import ui.systemMaintain.StaffInfo;
import userVerify.userVerify;

public class mainUIFrame extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	//MDIDesktopPane����,���������Ӵ���
	public static MDIDesktopPane desktop=new MDIDesktopPane();


	public static mainUIFrame mainUIFrame;
	//�˵���
	JMenuBar jMenuBar=new JMenuBar();
	//������
	JToolBar jToolBar=new JToolBar(JToolBar.HORIZONTAL);
	//Menu
	JMenu staffMenu=new JMenu("���¹���");
	JMenu orderMenu=new JMenu("��������");
	JMenu sellMenu=new JMenu("���۹���");
	
	/**
	 * ��������˵������Ӳ˵� 
	 */
	JMenu produceMenu=new JMenu("��������");
	JMenu producePlanMenu=new JMenu("�����ƻ�");
	JMenu productProduceMenu=new JMenu("��Ʒ������");
	JMenu materialProduceMenu=new JMenu("���������");
	
	JMenu storeMenu=new JMenu("������");
	JMenu productStoreMenu=new JMenu("��Ʒ��");
	JMenu materialStoreMenu=new JMenu("�����");
	JMenu matOutMenu=new JMenu("�������");
	JMenu matInMenu=new JMenu("������");
	JMenu matDestroyMenu=new JMenu("�������");
	
	
	JMenu maintainMenu=new JMenu("ϵͳά��");
	JMenu helpMenu=new JMenu("����");
	JMenu quitMenu=new JMenu("�˳�ϵͳ");
	//MenuItems_staffMenu
	JMenuItem jmiEnterpriseManage=new JMenuItem("��ҵ������");
	JMenuItem jmiSalesShop=new JMenuItem("���۲���");
	JMenuItem jmiProductionPlanShop=new JMenuItem("�����ƻ�����");
	JMenuItem jmiMaterialShop=new JMenuItem("�������");
	JMenuItem jmiProductShop=new JMenuItem("��Ʒ����");
	JMenuItem jmiMaterialStore=new JMenuItem("�����");
	JMenuItem jmiProductStore=new JMenuItem("��Ʒ��");
	JMenuItem jmiSystemManager=new JMenuItem("ϵͳ����Ա��Ϣ");
	//MenuItems_orderMenu
	JMenuItem jmiOrderTypein=new JMenuItem("����¼��");
	JMenuItem jmiOrderQuery=new JMenuItem("������ѯ");
	//MenuItem_sellMenu
	JMenuItem jmiSellTypein=new JMenuItem("���۵�¼��");
	JMenuItem jmiSellQuery=new JMenuItem("���۵���ѯ");
	//MenuItem_produceMenu
	JMenuItem jmiProductionPlanTypein=new JMenuItem("�����ƻ�¼��");
	JMenuItem jmiProductProduceTypein=new JMenuItem("��Ʒ������¼��");
	JMenuItem jmiMaterialProduceTypein=new JMenuItem("���������¼��");
	JMenuItem jmiProducePlanQuery=new JMenuItem("�����ƻ���ѯ");
	JMenuItem jmiProductProduceQuery=new JMenuItem("��Ʒ��������ѯ");
	JMenuItem jmiMaterialProduceQuery=new JMenuItem("�����������ѯ");
	//MenuItem_storeMenu
	JMenuItem jmiMaterialOut=new JMenuItem("�������¼��");
	JMenuItem jmiMaterialIn=new JMenuItem("������¼��");
	JMenuItem jmiMaterialStoreQuery=new JMenuItem("�������ѯ");
	JMenuItem jmiMaterialBadDestroy=new JMenuItem("�����Ʒ����");
	JMenuItem jmiProductStoreQuery=new JMenuItem("��Ʒ����ѯ");
	JMenuItem jmiMaterialInQuery=new JMenuItem("�������ѯ");
	JMenuItem jmiMaterialOutQuery=new JMenuItem("��������ѯ");
	JMenuItem jmiMaterialDestroyQuery=new JMenuItem("������ٲ�ѯ");
	//MenuItem_maintainMenu
	JMenuItem jmiUserInfoManage=new JMenuItem("�û�����");
	JMenuItem jmiChangePassword=new JMenuItem("�����޸�");
	//MenuItem_helpMenu
	JMenuItem jmiHelp=new JMenuItem("����");
	JMenuItem jmiAbout=new JMenuItem("����");
	//MenuItem_Quit
	JMenuItem jmiZhuxiao=new JMenuItem("ע����¼");
	JMenuItem jmiQuit=new JMenuItem("�˳�ϵͳ");
	//��������ť
	JButton changePwdButton=new JButton("");
	JButton quitButton=new JButton("");
	JButton aboutButton=new JButton("");
	JButton helpButton=new JButton("");
	ImageIcon icon=new ImageIcon("./image/mainPic.jpg"),icon1=icon;
	JLabel backPanel=new JLabel();
	JLabel loginInfo=new JLabel();
	
	Font font=new Font("����", 1, 22);
	JScrollPane jScrollPane=new JScrollPane();


	private String[] titles;


	private String op;


	private int po;
	
	
	public mainUIFrame(){
		super("���г���������ϵͳ");
		this.setMenu();
		backPanel.setBounds(0, 0, 1920, 1000);
		backPanel.setIcon(icon);
		desktop.add(backPanel);
		
		
		jScrollPane.getViewport().add(desktop);
		this.setLayout(new BorderLayout());
		this.add(jScrollPane,BorderLayout.CENTER);
		jScrollPane.setBorder(BorderFactory.createEtchedBorder(1));
		this.setUndecorated(true);
		

		
		//jToolBar.setBorder(BorderFactory.createLineBorder(Color.gray));
		jToolBar.add(new JLabel("          "));
		jToolBar.add(changePwdButton);
		jToolBar.add(aboutButton);
		jToolBar.add(helpButton);
		jToolBar.add(quitButton);
		jToolBar.add(loginInfo);
		changePwdButton.setToolTipText("����޸�����");
		changePwdButton.setIcon(new ImageIcon("./image/pic1.jpg"));
		changePwdButton.setBorder(null);
		quitButton.setToolTipText("�˳�ϵͳ");
		quitButton.setIcon(new ImageIcon("./image/pic2.jpg"));
		quitButton.setBorder(null);
		aboutButton.setToolTipText("���ڱ����");
		aboutButton.setIcon(new ImageIcon("./image/pic3.jpg"));
		aboutButton.setBorder(null);
		helpButton.setToolTipText("ʹ�ð���");
		helpButton.setIcon(new ImageIcon("./image/pic4.jpg"));
		helpButton.setBorder(null);
		
		
		//jToolBar.addSeparator();
		this.add(jToolBar, BorderLayout.NORTH);
		this.setFont();
		this.addListener();

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	void setInfo(){
		 po = 0;
		 titles=new String[]{"��ҵ������","���ۿƹ���Ա","�����ƻ��ƹ���Ա","����������Ա","��Ʒ�������Ա","��������Ա","��Ʒ�����Ա","ϵͳ����Ա"};
		 op=userVerify.getCurrentStaff().substring(0, 2).trim();
		if(op.equals("17")){
			po=0;
		}
		if(op.equals("12")){
			po=1;
		}
		if(op.equals("11")){
			po=2;
		}
		if(op.equals("13")){
			po=3;
		}
		if(op.equals("14")){
			po=4;
		}
		if(op.equals("15")){
			po=5;
		}
		if(op.equals("16")){
			po=6;
		}
		if(op.equals("10")){
			po=7;
		}
		loginInfo.setText("��ǰ��¼���:"+titles[po]);
		loginInfo.setFont(font);
	}
	//��Ӳ˵�
	void setMenu(){
		//staffMenu
		staffMenu.add(jmiEnterpriseManage);
		staffMenu.add(jmiSalesShop);
		staffMenu.add(jmiProductionPlanShop);
		staffMenu.add(jmiMaterialShop);
		staffMenu.add(jmiProductShop);
		staffMenu.add(jmiMaterialStore);
		staffMenu.add(jmiMaterialStore);
		staffMenu.add(jmiProductStore);
		staffMenu.add(jmiSystemManager);
		//orderMenu
		orderMenu.add(jmiOrderTypein);
		orderMenu.add(jmiOrderQuery);
		//sellMenu
		sellMenu.add(jmiSellTypein);
		sellMenu.add(jmiSellQuery);
		//produceMenu
		produceMenu.add(producePlanMenu);
		produceMenu.add(productProduceMenu);
		produceMenu.add(materialProduceMenu);
		producePlanMenu.add(jmiProductionPlanTypein);
		productProduceMenu.add(jmiProductProduceTypein);
		materialProduceMenu.add(jmiMaterialProduceTypein);
		producePlanMenu.add(jmiProducePlanQuery);
		productProduceMenu.add(jmiProductProduceQuery);
		materialProduceMenu.add(jmiMaterialProduceQuery);
		//storeMenu
		storeMenu.add(productStoreMenu);
		storeMenu.add(materialStoreMenu);
		productStoreMenu.add(jmiProductStoreQuery);
		materialStoreMenu.add(jmiMaterialStoreQuery);
		materialStoreMenu.add(matOutMenu);
		materialStoreMenu.add(matInMenu);
		materialStoreMenu.add(matDestroyMenu);
		matOutMenu.add(jmiMaterialOut);
		matOutMenu.add(jmiMaterialOutQuery);
		matInMenu.add(jmiMaterialIn);
		matInMenu.add(jmiMaterialInQuery);
		matDestroyMenu.add(jmiMaterialBadDestroy);
		matDestroyMenu.add(jmiMaterialDestroyQuery);
		//maintainMenu
		maintainMenu.add(jmiUserInfoManage);
		maintainMenu.add(jmiChangePassword);
		//helpMenu
		helpMenu.add(jmiHelp);
		helpMenu.add(jmiAbout);
		//quitMenu
		quitMenu.add(jmiZhuxiao);
		quitMenu.add(jmiQuit);
		
		jMenuBar.add(staffMenu);
		jMenuBar.add(orderMenu);
		jMenuBar.add(sellMenu);
		jMenuBar.add(produceMenu);
		jMenuBar.add(storeMenu);
		jMenuBar.add(maintainMenu);
		jMenuBar.add(helpMenu);
		jMenuBar.add(quitMenu);
		
		this.setJMenuBar(jMenuBar);
	}
	
	//��������
	void setFont(){
		staffMenu.setFont(font);
		orderMenu.setFont(font);
		sellMenu.setFont(font);
		produceMenu.setFont(font);
		storeMenu.setFont(font);
		maintainMenu.setFont(font);
		helpMenu.setFont(font);
		 quitMenu.setFont(font);
		//MenuItems_staffMenu
		 jmiEnterpriseManage.setFont(font);
		 jmiSalesShop.setFont(font);
		 jmiProductionPlanShop.setFont(font);
		 jmiMaterialShop.setFont(font);
		 jmiProductShop.setFont(font);
		 jmiMaterialStore.setFont(font);
		 jmiProductStore.setFont(font);
		 jmiSystemManager.setFont(font);
		//MenuItems_orderMenu
		 jmiOrderTypein.setFont(font);
		 jmiOrderQuery.setFont(font);
		//MenuItem_sellMenu
		 jmiSellTypein.setFont(font);
		 jmiSellQuery.setFont(font);
		//MenuItem_produceMenu
		 producePlanMenu.setFont(font);
		 productProduceMenu.setFont(font);
		 materialProduceMenu.setFont(font);
		 jmiProductionPlanTypein.setFont(font);
		 jmiProductProduceTypein.setFont(font);
		 jmiMaterialProduceTypein.setFont(font);
		 jmiProducePlanQuery.setFont(font);
		 jmiProductProduceQuery.setFont(font);
		 jmiMaterialProduceQuery.setFont(font);
		//MenuItem_storeMenu
		 jmiMaterialOut.setFont(font);
		 jmiMaterialIn.setFont(font);
		 jmiMaterialStoreQuery.setFont(font);
		 jmiMaterialBadDestroy.setFont(font);
		 jmiProductStoreQuery.setFont(font);
		 producePlanMenu.setFont(font);
		 productStoreMenu.setFont(font);
		 materialStoreMenu.setFont(font);
		 matOutMenu.setFont(font);
		 matInMenu.setFont(font);
		 matDestroyMenu.setFont(font);
		 jmiMaterialInQuery.setFont(font);
		 jmiMaterialOutQuery.setFont(font);
		 jmiMaterialDestroyQuery.setFont(font);
		//MenuItem_maintainMenu
		 jmiUserInfoManage.setFont(font);
		 jmiChangePassword.setFont(font);
		//MenuItem_helpMenu
		 jmiHelp.setFont(font);
		 jmiAbout.setFont(font);
		 //MenuItem_quitMenu
		 jmiZhuxiao.setFont(font);
		 jmiQuit.setFont(font);
		//��������ť
		 changePwdButton.setFont(font);
		 quitButton.setFont(font);
		 aboutButton.setFont(font);
		 helpButton.setFont(font);
	}
	
	public void flush(){
		this.repaint();
		this.flush();
	}
	
	//ע���¼�
	void addListener(){
		/**
		 * staffMenu
		 */
		jmiEnterpriseManage.addActionListener(this);
		jmiSalesShop.addActionListener(this);
		jmiProductionPlanShop.addActionListener(this);
		jmiMaterialShop.addActionListener(this);
		jmiProductShop.addActionListener(this);
		jmiMaterialStore.addActionListener(this);
		jmiProductStore.addActionListener(this);
		jmiSystemManager.addActionListener(this);
		/**
		 * orderMenu
		 */
		jmiOrderQuery.addActionListener(this);
		jmiOrderTypein.addActionListener(this);
		/**
		 * sellMenu
		 */
		 jmiSellTypein.addActionListener(this);
		 jmiSellQuery.addActionListener(this);
		 /**
		  * produceMenu
		  */
		 jmiProductionPlanTypein.addActionListener(this);
		 jmiProductProduceTypein.addActionListener(this);
		 jmiMaterialProduceTypein.addActionListener(this);
		 jmiProducePlanQuery.addActionListener(this);
		 jmiProductProduceQuery.addActionListener(this);
		 jmiMaterialProduceQuery.addActionListener(this);
		 /**
		  * storeMenu
		  */
		 jmiMaterialOut.addActionListener(this);
		 jmiMaterialIn.addActionListener(this);
		 jmiMaterialStoreQuery.addActionListener(this);
		 jmiMaterialBadDestroy.addActionListener(this);
		 jmiProductStoreQuery.addActionListener(this);
		 jmiMaterialInQuery.addActionListener(this);
		 jmiMaterialOutQuery.addActionListener(this);
		 jmiMaterialDestroyQuery.addActionListener(this);
		 /**
		  * maintainMenu
		  */
		 jmiUserInfoManage.addActionListener(this);
		 jmiChangePassword.addActionListener(this);
		 /**
		  * helpMenu
		  */
		 jmiHelp.addActionListener(this);
		 jmiAbout.addActionListener(this);
		 
		//quitMenu
		jmiZhuxiao.addActionListener(this);
		jmiQuit.addActionListener(this);
		quitButton.addActionListener(this);
		changePwdButton.addActionListener(this);
		aboutButton.addActionListener(this);
		helpButton.addActionListener(this);
	}
	public static void main(String[] args) {
	 mainUIFrame=new mainUIFrame();
		mainUIFrame.setSize(1280,720);
		mainUIFrame.setLocationRelativeTo(null);
		mainUIFrame.setExtendedState(MAXIMIZED_BOTH);
		mainUIFrame.setVisible(true);
		new loginFrame(mainUIFrame, true);
		mainUIFrame.setOption(userVerify.getCurrentStaff());
		mainUIFrame.setInfo();
		mainUIFrame.checkMark();
	}
	
	/**
	 * 
	 * ���ݲ�ͬ��¼������ʾ��ͬ���湦��
	 */
	public void setOption(String op){
		
		 jmiEnterpriseManage.setEnabled(false);
		 jmiSalesShop.setEnabled(false);
		 jmiProductionPlanShop.setEnabled(false);
		 jmiMaterialShop.setEnabled(false);
		 jmiProductShop.setEnabled(false);
		 jmiMaterialStore.setEnabled(false);
		 jmiProductStore.setEnabled(false);
		 jmiSystemManager.setEnabled(false);
		//MenuItems_orderMenu.setEnabled(false);
		 jmiOrderTypein.setEnabled(false);
		 jmiOrderQuery.setEnabled(false);
		//MenuItem_sellMenu.setEnabled(false);
		 jmiSellTypein.setEnabled(false);
		 jmiSellQuery.setEnabled(false);
		//MenuItem_produceMenu.setEnabled(false);
		 jmiProductionPlanTypein.setEnabled(false);
		 jmiProductProduceTypein.setEnabled(false);
		 jmiMaterialProduceTypein.setEnabled(false);
		 jmiProducePlanQuery.setEnabled(false);
		 jmiProductProduceQuery.setEnabled(false);
		 jmiMaterialProduceQuery.setEnabled(false);
		 jmiMaterialOut.setEnabled(false);
		 jmiMaterialIn.setEnabled(false);
		 jmiMaterialStoreQuery.setEnabled(false);
		 jmiMaterialBadDestroy.setEnabled(false);
		 jmiProductStoreQuery.setEnabled(false);
		 jmiMaterialInQuery.setEnabled(false);
		 jmiMaterialOutQuery.setEnabled(false);
		 jmiMaterialDestroyQuery.setEnabled(false);

		
		
		String po=op.substring(0,2).trim();
		if(po.equals("17")){
			jmiOrderQuery.setEnabled(true);
			jmiSellQuery.setEnabled(true);
			jmiProducePlanQuery.setEnabled(true);
			jmiProductProduceQuery.setEnabled(true);
			jmiMaterialProduceQuery.setEnabled(true);
			jmiMaterialDestroyQuery.setEnabled(true);
			jmiMaterialStoreQuery.setEnabled(true);
			jmiProductStoreQuery.setEnabled(true);
			jmiMaterialOutQuery.setEnabled(true);
			jmiMaterialInQuery.setEnabled(true);
			changePwdButton.setEnabled(true);
			quitButton.setEnabled(true);
			helpButton.setEnabled(true);
			jmiUserInfoManage.setEnabled(true);
			jmiChangePassword.setEnabled(true);
			jmiHelp.setEnabled(true);
		}
		if(po.equals("10")){
			jmiEnterpriseManage.setEnabled(true);
			jmiSalesShop.setEnabled(true);
			jmiProductionPlanShop.setEnabled(true);
			jmiMaterialShop.setEnabled(true);
			jmiProductShop.setEnabled(true);
			jmiMaterialStore.setEnabled(true);
			jmiProductStore.setEnabled(true);
			jmiSystemManager.setEnabled(true);
			changePwdButton.setEnabled(true);
			quitButton.setEnabled(true);
			helpButton.setEnabled(true);
			jmiUserInfoManage.setEnabled(true);
			jmiChangePassword.setEnabled(true);
			jmiHelp.setEnabled(true);
		}
		if(po.equals("12")){
			jmiSellTypein.setEnabled(true);
			jmiSellQuery.setEnabled(true);
			jmiOrderTypein.setEnabled(true);
			jmiOrderQuery.setEnabled(true);
			changePwdButton.setEnabled(true);
			quitButton.setEnabled(true);
			helpButton.setEnabled(true);
			jmiUserInfoManage.setEnabled(true);
			jmiChangePassword.setEnabled(true);
			jmiHelp.setEnabled(true);
		}
		if(po.equals("11")){
			jmiProductionPlanTypein.setEnabled(true);
			jmiProductProduceTypein.setEnabled(true);
			jmiMaterialProduceTypein.setEnabled(true);
			jmiProducePlanQuery.setEnabled(true);
			jmiProductProduceQuery.setEnabled(true);
			jmiMaterialProduceQuery.setEnabled(true);
			changePwdButton.setEnabled(true);
			quitButton.setEnabled(true);
			helpButton.setEnabled(true);
			jmiUserInfoManage.setEnabled(true);
			jmiChangePassword.setEnabled(true);
			jmiHelp.setEnabled(true);
		}
		if(po.equals("13")){
			jmiMaterialProduceQuery.setEnabled(true);
			changePwdButton.setEnabled(true);
			quitButton.setEnabled(true);
			helpButton.setEnabled(true);
			jmiUserInfoManage.setEnabled(true);
			jmiChangePassword.setEnabled(true);
			jmiHelp.setEnabled(true);
		}
		if(po.equals("14")){
			jmiProductProduceQuery.setEnabled(true);
			jmiProducePlanQuery.setEnabled(true);
			changePwdButton.setEnabled(true);
			quitButton.setEnabled(true);
			helpButton.setEnabled(true);
			jmiUserInfoManage.setEnabled(true);
			jmiChangePassword.setEnabled(true);
			jmiHelp.setEnabled(true);
		}
		if(po.equals("15")){
			jmiMaterialOut.setEnabled(true);
			jmiMaterialIn.setEnabled(true);
			jmiMaterialStoreQuery.setEnabled(true);
			jmiMaterialBadDestroy.setEnabled(true);
			jmiMaterialInQuery.setEnabled(true);
			jmiMaterialOutQuery.setEnabled(true);
			jmiMaterialDestroyQuery.setEnabled(true);
			changePwdButton.setEnabled(true);
			quitButton.setEnabled(true);
			helpButton.setEnabled(true);
			jmiUserInfoManage.setEnabled(true);
			jmiChangePassword.setEnabled(true);
			jmiHelp.setEnabled(true);
		}
		if(po.equals("16")){
			jmiProductStoreQuery.setEnabled(true);
			changePwdButton.setEnabled(true);
			quitButton.setEnabled(true);
			helpButton.setEnabled(true);
			jmiUserInfoManage.setEnabled(true);
			jmiChangePassword.setEnabled(true);
			jmiHelp.setEnabled(true);
		}
		if(po.equals("00")){
			jmiUserInfoManage.setEnabled(false);
			jmiChangePassword.setEnabled(false);
			jmiHelp.setEnabled(false);
			changePwdButton.setEnabled(false);
			quitButton.setEnabled(false);
			helpButton.setEnabled(false);
			
		}
	}
	
	/**
	 * �����ְ
	 */
	void checkMark(){
		String sql="select mark from staff where staff = '"+userVerify.getCurrentStaff()+"'";
		sqlConnect connect = null;
		try {
			connect = new sqlConnect();
		} catch (ClassNotFoundException | SQLException e1) {
			JOptionPane.showMessageDialog(this, "���ݿ�����ʧ��");
			e1.printStackTrace();
		}
		ResultSet rSet = null;
		try {
			rSet=connect.getResultSet(sql);
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		try {
			while(rSet.next()){
				String a=rSet.getString(1).trim();
				System.out.println(a);
				if(a.equals("1")){
					this.loginInfo.setText("��ǰ��¼���:"+titles[po]+"   (��ǰ������ְ,������ʹ��Ա������)");
					this.setOption("00");
				}
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}

	//ʵ�ְ�ť����¼�
	@Override
	public void actionPerformed(ActionEvent e) {
		/**
		 * staffMenu�˵����Ӧ�¼�
		 */
		if(e.getSource()==jmiEnterpriseManage){
			desktop.add(new SaleDdepartment(0));
		}
		if(e.getSource()==jmiSalesShop){
			desktop.add(new SaleDdepartment(1));
		}
		if(e.getSource()==jmiProductionPlanShop){
			desktop.add(new SaleDdepartment(2));
		}
		if(e.getSource()==jmiMaterialShop){
			desktop.add(new SaleDdepartment(3));
		}
		if(e.getSource()==jmiProductShop){
			desktop.add(new SaleDdepartment(4));
		}
		if(e.getSource()==jmiMaterialStore){
			desktop.add(new SaleDdepartment(5));
		}
		if(e.getSource()==jmiProductStore){
			desktop.add(new SaleDdepartment(6));
		}
		if(e.getSource()==jmiSystemManager){
			desktop.add(new SaleDdepartment(7));
		}
		
		/**
		 * orderMenu�˵����Ӧ�¼�
		 */
		if(e.getSource()==jmiOrderTypein){
			desktop.add(new Revise_AddOrderInfo(0));
		}
		if(e.getSource()==jmiOrderQuery){
			desktop.add(new OrderFormInfo(0));
		}
		

		/**
		 * sellMenu
		 */
		 if(e.getSource()==jmiSellTypein)
		 {
			 desktop.add(new Revise_AddOrderInfo(1));
		 }
		 if(e.getSource()==jmiSellQuery){
			 desktop.add(new OrderFormInfo(1));
		 }
		 /**
		  * produceMenu
		  */
		 if(e.getSource()==jmiProductionPlanTypein){
			 desktop.add(new Revise_AddOrderInfo(2));
		 }
		 if(e.getSource()==jmiProductProduceTypein){
			 desktop.add(new Revise_AddOrderInfo(3));
		 }
		 if(e.getSource()==jmiMaterialProduceTypein){
			 desktop.add(new Revise_AddOrderInfo(4));
		 }
		 if(e.getSource()==jmiProducePlanQuery){
			 desktop.add(new OrderFormInfo(2));
		 }
		 if(e.getSource()==jmiProductProduceQuery){
			 desktop.add(new OrderFormInfo(3));
		 }
		 if(e.getSource()==jmiMaterialProduceQuery){
			 desktop.add(new OrderFormInfo(4));
		 }
		 /**
		  * storeMenu
		  */
		 if(e.getSource()==jmiMaterialOut){
			 desktop.add(new Revise_AddOrderInfo(5));
		 }
		 if(e.getSource()==jmiMaterialIn){
			 desktop.add(new MaterialLoadInfo());
		 }
		 if(e.getSource()==jmiMaterialStoreQuery){
			 desktop.add(new Search());
		 }
		 if(e.getSource()==jmiMaterialBadDestroy){
			 desktop.add(new Destruction(0, ""));
		 }
		 /*��Ʒ����ѯ*/
		 if(e.getSource()==jmiProductStoreQuery){
			 desktop.add(new StoreSearch());
		 }
		 if(e.getSource()==jmiMaterialInQuery){
			 desktop.add(new MaterialLoad(1));
		 }
		 if(e.getSource()==jmiMaterialOutQuery){
			 desktop.add(new MaterialLoad(0));
		 }
		 if(e.getSource()==jmiMaterialDestroyQuery){
			 desktop.add(new DestructionInfo());
		 }
		 /**
		  * maintainMenu
		  */
		 if(e.getSource()==jmiUserInfoManage){
			 desktop.add(new StaffInfo());
		 }
		 if(e.getSource()==jmiChangePassword){
			 desktop.add(new PasswordChange());
		 }
		 /**
		  * helpMenu
		  */
		 if(e.getSource()==jmiHelp){
			 desktop.add(new Help());
		 }
		 if(e.getSource()==jmiAbout){
			 JOptionPane.showMessageDialog(this, "���г���������ϵͳ(BMS) V1.0"
			 		+ "\n�����  ���Sͥ   �ⰲ�� \n");
		 }
		
		//quitMenu
		if(e.getSource()==jmiZhuxiao){
			desktop.removeAll();
			desktop.add(backPanel);
			desktop.repaint();
			new loginFrame(this, true);
			this.setOption(userVerify.getCurrentStaff());
			this.setInfo();
			this.checkMark();
		}
		if(e.getSource()==jmiQuit){
			System.exit(0);
		}
		
		if(e.getSource()==changePwdButton){
			jmiChangePassword.doClick();
		}
		if(e.getSource()==quitButton){
			 new JOptionPane();
			if(JOptionPane.showOptionDialog(this, "ȷ��Ҫ�˳���?", "��ʾ", JOptionPane.YES_NO_OPTION, 0, null, null, null)==JOptionPane.YES_OPTION){
			 	System.exit(0);
			 }
		}
		if(e.getSource()==aboutButton){
			jmiAbout.doClick();
		}
		if(e.getSource()==helpButton){
			jmiHelp.doClick();
		}
	}
	
	@SuppressWarnings("static-access")
	public void addWindow(JInternalFrame jInternalFrame){
		mainUIFrame.desktop.add(jInternalFrame);
	}

}
