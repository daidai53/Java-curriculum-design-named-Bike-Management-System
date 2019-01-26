package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.glass.events.KeyEvent;

import userVerify.userVerify;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPasswordField;

/**
 * @author 李豐庭 登录页
 */
public class loginFrame extends JDialog implements ActionListener, WindowListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private JButton cancelButton;

	/**
	 * Create the frame.
	 */
	public loginFrame(JFrame jf, boolean model) {
		super(jf, model);
		setFont(new Font("楷体", Font.PLAIN, 17));
		setTitle("\u767B\u9646");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 449, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);

		JLabel label = new JLabel("\u7528\u6237\u540D\uFF1A");
		label.setFont(new Font("楷体", Font.PLAIN, 17));
		label.setBounds(77, 35, 82, 28);
		contentPane.add(label);

		textField = new JTextField();
		textField.setBounds(187, 35, 134, 28);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel label_1 = new JLabel("\u5BC6\u7801\uFF1A");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("楷体", Font.PLAIN, 17));
		label_1.setBounds(77, 88, 82, 28);
		contentPane.add(label_1);

		loginButton = new JButton("\u767B\u9646");
		loginButton.setFont(new Font("楷体", Font.PLAIN, 17));
		loginButton.setBounds(98, 150, 93, 23);
		contentPane.add(loginButton);

		cancelButton = new JButton("\u53D6\u6D88");
		cancelButton.setFont(new Font("楷体", Font.PLAIN, 17));
		cancelButton.setBounds(240, 150, 93, 23);
		contentPane.add(cancelButton);

		passwordField = new JPasswordField();
		passwordField.setBounds(187, 87, 134, 28);
		contentPane.add(passwordField);

		passwordField.addKeyListener(this);

		this.addWindowListener(this);
		loginButton.addActionListener(this);
		cancelButton.addActionListener(this);
		this.setVisible(true);

	}

	/*登录按钮事件监听*/
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
			String staff = textField.getText().trim();
			String password = new String(passwordField.getPassword()).trim();
			//判断登录名输入是否为空
			if (staff.equals("")) {
				JOptionPane.showMessageDialog(this, "用户名不能为空");
			} else if (password.equals("")) {
				JOptionPane.showMessageDialog(this, "密码不能为空");
			} else {
				try {
					if (userVerify.loginCheck(staff, password)) {
						JOptionPane.showMessageDialog(this, "登录成功");
						if (password.equals("0000")) {
							JOptionPane.showMessageDialog(this, "当前密码为[初始密码],请及时修改!");
						}
						this.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(this, "登录验证失败,请重新输入");
						passwordField.setText("");
					}
				} catch (HeadlessException | ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(this, "数据库连接失败");
					e1.printStackTrace();
				}
			}
		}
		if (e.getSource() == cancelButton) {
			System.exit(0);
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void keyTyped(java.awt.event.KeyEvent e) {
		// TODO 自动生成的方法存根

	}

	
	/*监听密码框的回车键*/
	@Override
	public void keyPressed(java.awt.event.KeyEvent e) {
		// TODO 自动生成的方法存根
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			loginButton.doClick();
		}
	}

	@Override
	public void keyReleased(java.awt.event.KeyEvent e) {
		// TODO 自动生成的方法存根

	}

}
