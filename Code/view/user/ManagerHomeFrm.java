package view.user;

import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ManagerHomeFrm extends JFrame implements ActionListener {
	private JButton btnViewStat, btnLogout;
	private User user;

	public ManagerHomeFrm(User user) {
		super("Manager Home");
		this.user = user;

		JLabel lblTitle = new JLabel("Manager Home");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
		lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

		btnViewStat = new JButton("View Statistic");
		btnViewStat.setBackground(new Color(200, 230, 250));
		btnViewStat.setFont(new Font("Arial", Font.PLAIN, 18));
		btnViewStat.setFocusPainted(false);
		btnViewStat.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnViewStat.addActionListener(this);

		btnLogout = new JButton("Logout");
		btnLogout.setBackground(new Color(200, 230, 250));
		btnLogout.setFont(new Font("Arial", Font.PLAIN, 18));
		btnLogout.setFocusPainted(false);
		btnLogout.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnLogout.addActionListener(this);

		Dimension maxSize = new Dimension(
				150,
				btnViewStat.getPreferredSize().height
		);

		btnViewStat.setMaximumSize(maxSize);
		btnLogout.setMaximumSize(maxSize);

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(Box.createVerticalStrut(30));
		panel.add(lblTitle);
		panel.add(Box.createVerticalStrut(30));
		panel.add(btnViewStat);
		panel.add(Box.createVerticalStrut(20));
		panel.add(btnLogout);
		panel.add(Box.createVerticalStrut(30));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.add(panel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnViewStat)) {
			new StatisticFrm(user).setVisible(true);
			this.dispose();
		} else {
			new LoginFrm().setVisible(true);
			this.dispose();
		}
	}
}