package view.user;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import dao.UserDAO;
import model.User;

public class LoginFrm extends JFrame implements ActionListener{
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JButton btnLogin;

	public LoginFrm() {
		super("Login Page");

		JLabel lblTitle = new JLabel("Login Page");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 36));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

		// Label và TextField username
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Arial", Font.PLAIN, 22));
		txtUsername = new JTextField(15);
		txtUsername.setFont(new Font("Arial", Font.PLAIN, 22));

		// Label và TextField password
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 22));
		txtPassword = new JPasswordField(15);
		txtPassword.setFont(new Font("Arial", Font.PLAIN, 22));
		txtPassword.setEchoChar('*');

		// Nút login
		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Arial", Font.PLAIN, 26));
		btnLogin.setBackground(new Color(200, 230, 250));
		btnLogin.setFocusPainted(false);

		// Table layout 3 hàng 2 cột cho label và field
		JPanel pnFields = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(18, 15, 18, 15);
		gbc.anchor = GridBagConstraints.CENTER;

		gbc.gridx = 0; gbc.gridy = 0;
		pnFields.add(lblUsername, gbc);
		gbc.gridx = 1;
		pnFields.add(txtUsername, gbc);

		gbc.gridx = 0; gbc.gridy = 1;
		pnFields.add(lblPassword, gbc);
		gbc.gridx = 1;
		pnFields.add(txtPassword, gbc);

		// Nút login ở dưới, căn giữa và full chiều ngang 2 ô
		gbc.gridx = 0; gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		pnFields.add(btnLogin, gbc);

		btnLogin.addActionListener(this);

		// Layout chính
		JPanel pnMain = new JPanel();
		pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
		pnMain.add(Box.createVerticalStrut(40));
		pnMain.add(lblTitle);
		pnMain.add(Box.createVerticalStrut(30));
		pnMain.add(pnFields);

		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		this.setContentPane(pnMain);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	public void actionPerformed(ActionEvent e) {
		if((e.getSource() instanceof JButton)&&(((JButton)e.getSource()).equals(btnLogin))) {
			User user = new User();
			user.setUsername(txtUsername.getText());
			user.setPassword(txtPassword.getText());

			UserDAO ud = new UserDAO();
			if(ud.checkLogin(user)) {
				if(user.getRole().equalsIgnoreCase("manager")) {
					(new ManagerHomeFrm(user)).setVisible(true);
					this.dispose();
				}else
					JOptionPane.showMessageDialog(this, "Only manager can access!");
			}else {
				JOptionPane.showMessageDialog(this, "Incorrect username and/or password!");
			}
		}
	}
	
	public static void main(String[] args) {
		LoginFrm myFrame = new LoginFrm();
		myFrame.setVisible(true);
	}
}
