package view.user;

import model.User;
import view.Item.SubAgentStatFrm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StatisticFrm extends JFrame implements ActionListener {
    private JButton btnSubAgentStat, btnBack;
    private User user;

    public StatisticFrm(User user) {
        super("Statistic");
        this.user = user;

        JLabel lblTitle = new JLabel("Statistic");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnSubAgentStat = new JButton("Statistic of Sub Agent");
        btnBack = new JButton("Back");

        Color btnColor = new Color(200, 230, 250);
        Font btnFont = new Font("Arial", Font.PLAIN, 18);

        for (JButton btn : new JButton[]{btnSubAgentStat, btnBack}) {
            btn.setBackground(btnColor);
            btn.setFont(btnFont);
            btn.setFocusPainted(false);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        Dimension maxSize = new Dimension(
                Math.max(btnSubAgentStat.getPreferredSize().width, btnBack.getPreferredSize().width) + 10,
                btnSubAgentStat.getPreferredSize().height
        );
        btnSubAgentStat.setMaximumSize(maxSize);
        btnBack.setMaximumSize(maxSize);

        btnSubAgentStat.addActionListener(this);
        btnBack.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalStrut(30));
        panel.add(lblTitle);
        panel.add(Box.createVerticalStrut(30));
        panel.add(btnSubAgentStat);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnBack);
        panel.add(Box.createVerticalStrut(30));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnSubAgentStat)) {
            new SubAgentStatFrm(user).setVisible(true);
            this.dispose();
        } else {
            new view.user.ManagerHomeFrm(user).setVisible(true);
            this.dispose();
        }
    }
}