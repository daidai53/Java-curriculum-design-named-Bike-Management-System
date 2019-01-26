package ui.systemMaintain;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import sql.sqlConnect;
import userVerify.userVerify;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class PasswordChange extends JInternalFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField staffInput;
	private JPasswordField oldPasswordInput;
	private JPasswordField newPasswordInput;
	private JPasswordField makeSureInput;
	String staff;
	private JButton save;


	/**
	 * Create the frame.
	 */
	public PasswordChange() {
		staff=userVerify.getCurrentStaff();
		this.setClosable(true);
		setTitle("\u4FEE\u6539\u5BC6\u7801");
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 469, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel staff = new JLabel("\u7528\u6237\u8D26\u53F7\uFF1A");
		staff.setBounds(100, 40, 80, 21);
		staff.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(staff);
		
		staffInput = new JTextField();
		staffInput.setBounds(220, 40, 104, 21);
		contentPane.add(staffInput);
		staffInput.setColumns(10);
		
		JLabel oldPassword = new JLabel("\u539F\u5BC6\u7801\uFF1A");
		oldPassword.setBounds(100, 101, 64, 21);
		oldPassword.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(oldPassword);
		
		oldPasswordInput = new JPasswordField();
		oldPasswordInput.setBounds(220, 101, 104, 21);
		contentPane.add(oldPasswordInput);
		oldPasswordInput.setColumns(10);
		
		JLabel newPassword = new JLabel("\u65B0\u5BC6\u7801\uFF1A");
		newPassword.setBounds(100, 162, 64, 21);
		newPassword.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(newPassword);
		
		newPasswordInput = new JPasswordField();
		newPasswordInput.setBounds(220, 162, 104, 21);
		contentPane.add(newPasswordInput);
		newPasswordInput.setColumns(10);
		
		JLabel makeSure = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		makeSure.setBounds(100, 223, 80, 21);
		makeSure.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(makeSure);
		
		makeSureInput = new JPasswordField();
		makeSureInput.setBounds(220, 223, 104, 21);
		contentPane.add(makeSureInput);
		makeSureInput.setColumns(10);
		
		 save = new JButton("\u4FDD\u5B58");
		save.setBounds(112, 284, 91, 27);
		save.setFont(new Font("楷体", Font.PLAIN, 16));
		contentPane.add(save);
		
		JButton cancel = new JButton("\u53D6\u6D88");
		cancel.setBounds(239, 284, 91, 27);
		cancel.setFont(new Font("楷体", Font.PLAIN, 16));
		
		staffInput.setText(this.staff);
		staffInput.setEditable(false);
		contentPane.add(cancel);
		save.addActionListener(this);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==save){
			String oldPwd,newPwd,msNewPwd;
			oldPwd=new String(oldPasswordInput.getPassword()).trim();
			newPwd=new String(newPasswordInput.getPassword()).trim();
			msNewPwd=new String(makeSureInput.getPassword()).trim();
			if(oldPwd.equals("")){
				JOptionPane.showMessageDialog(this, "请输入原密码");
			}else if(newPwd.equals("")){
				JOptionPane.showMessageDialog(this, "请输入新密码");
			}else if(msNewPwd.equals("")){
				JOptionPane.showMessageDialog(this, "请确认新密码");
			}else if(!newPwd.equals(msNewPwd)){
				JOptionPane.showMessageDialog(this, "输入的两次密码不一致");
				newPasswordInput.setText("");
				makeSureInput.setText("");
			}else{
				try {
					if(userVerify.loginCheck(this.staff, oldPwd)){
						String sql="exec password_update '"+this.staff+"','"+newPwd+"'";
						sqlConnect connect = null;
						try {
							connect = new sqlConnect();
						} catch (ClassNotFoundException | SQLException e2) {
							JOptionPane.showMessageDialog(this, "数据库连接失败");
							e2.printStackTrace();
						}
						try {
							connect.executeSql(sql);
							JOptionPane.showMessageDialog(this, "修改成功");
							this.dispose();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(this, "修改失败");
							e1.printStackTrace();
						}
					}else{
						JOptionPane.showMessageDialog(this, "原密码错误");
						oldPasswordInput.setText("");
					}
				} catch (HeadlessException | ClassNotFoundException | SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		}
	}
}
