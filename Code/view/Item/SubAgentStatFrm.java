package view.Item;

import dao.SubAgentStatDAO;
import model.SubAgentStat;
import model.User;
import view.user.LoginFrm;
import view.user.StatisticFrm;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SubAgentStatFrm extends JFrame implements ActionListener {
    private JTextField txtStartDate, txtEndDate;
    private JButton btnSearch, btnBack, btnSelect;
    private JTable tblResult;
    private static User user;
    private List<SubAgentStat> resultList;

    private void loadTable(List<SubAgentStat> resultList) {
        this.resultList = resultList;
        String[] columns = {"ID", "Name", "Address", "Phone", "Total Quantity", "Total Revenue"};
        Object[][] data = new Object[resultList.size()][6];
        for (int i = 0; i < resultList.size(); i++) {
            SubAgentStat sa = resultList.get(i);
            data[i][0] = sa.getId();
            data[i][1] = sa.getName();
            data[i][2] = sa.getAddress();
            data[i][3] = sa.getPhoneNumber();
            data[i][4] = sa.getTotalQuantity();
            data[i][5] = String.format("%,.0f", sa.getTotalRevenue());
        }
        tblResult.setModel(new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
    }

    public void Frontend(){
        JLabel lblTitle = new JLabel("List of Sub Agent Statistic between:");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        txtStartDate = new JTextField(10);
        txtEndDate = new JTextField(10);
        txtStartDate.setFont(new Font("Arial", Font.PLAIN, 20));
        txtEndDate.setFont(new Font("Arial", Font.PLAIN, 20));

        btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("Arial", Font.PLAIN, 18));
        btnSearch.setBackground(new Color(200, 230, 250));
        btnSearch.setFocusPainted(false);

        JPanel datePanel = new JPanel();
        datePanel.setBackground(Color.WHITE);
        datePanel.add(new JLabel("Start date"));
        datePanel.add(txtStartDate);
        datePanel.add(Box.createHorizontalStrut(30));
        datePanel.add(new JLabel("End date"));
        datePanel.add(txtEndDate);
        datePanel.add(Box.createHorizontalStrut(30));
        datePanel.add(btnSearch);

        tblResult = new JTable();
        tblResult.setFont(new Font("Arial", Font.PLAIN, 18));
        tblResult.setRowHeight(32);
        tblResult.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JTableHeader header = tblResult.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 18));
        header.setBackground(new Color(200, 230, 250));

        tblResult.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int col) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                if (isSelected) {
                    setBackground(new Color(160, 200, 250));
                } else {
                    setBackground(new Color(223, 241, 252));
                }
                setHorizontalAlignment(CENTER);
                return this;
            }
        });

        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.PLAIN, 18));
        btnBack.setBackground(new Color(200, 230, 250));
        btnBack.setFocusPainted(false);

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backPanel.setBackground(Color.WHITE);
        backPanel.add(btnBack);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.add(Box.createVerticalStrut(30));
        contentPanel.add(lblTitle);
        contentPanel.add(Box.createVerticalStrut(25));
        contentPanel.add(datePanel);
        contentPanel.add(Box.createVerticalStrut(15));
        contentPanel.add(new JScrollPane(tblResult));
        contentPanel.add(Box.createVerticalStrut(15));
        contentPanel.add(backPanel);

        this.setContentPane(contentPanel);
        this.setSize(1100, 650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        btnSearch.addActionListener(this);
        btnBack.addActionListener(this);

        tblResult.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    actionPerformed(new ActionEvent(tblResult, ActionEvent.ACTION_PERFORMED, "SELECT_ROW"));
                }
            }
        });
    }

    public SubAgentStatFrm(User user) {
        super("Sub-Agent Statistic");
        SubAgentStatFrm.user = user;

        Frontend();
    }

    public SubAgentStatFrm(User user, Date startDate, Date endDate) {
        super("Sub-Agent Statistic");
        SubAgentStatFrm.user = user;

        Frontend();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        txtStartDate.setText(sdf.format(startDate));
        txtEndDate.setText(sdf.format(endDate));

        resultList = new SubAgentStatDAO().getSaByRev(startDate, endDate);
        loadTable(resultList);
    }


    public String modifyDate(String date){
        String[] parts = date.trim().split("[-/.\\s]");
        if (parts.length != 3) return null;

        try {
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            if(day==0 || month==0 || year==0){
                JOptionPane.showMessageDialog(null, "Please enter a valid date");
                return null;
            }

            return String.format("%04d-%02d-%02d", year, month, day);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String startDate = modifyDate(txtStartDate.getText());
        String endDate = modifyDate(txtEndDate.getText());
        if (e.getSource().equals(btnSearch)) {
            try {
                Date start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
                Date end = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);

                if(start.after(end)){
                    JOptionPane.showMessageDialog(null, "Start date has to smaller than end date");
                    List<SubAgentStat> allAgents = new SubAgentStatDAO().getNoSubAgents();
                    for (SubAgentStat sa : allAgents) {
                        sa.setTotalQuantity(0);
                        sa.setTotalRevenue(0);
                    }
                    loadTable(allAgents);
                    return;
                }

                resultList = new SubAgentStatDAO().getSaByRev(start, end);
                if(resultList.isEmpty()){
                    JOptionPane.showMessageDialog(null, "No Sub-agent found");
                    loadTable(List.of());
                    return;
                }
                loadTable(resultList);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid date format");
                List<SubAgentStat> allAgents = new SubAgentStatDAO().getNoSubAgents();
                for (SubAgentStat sa : allAgents) {
                    sa.setTotalQuantity(0);
                    sa.setTotalRevenue(0);
                }

                loadTable(allAgents);
            }

        } else if (e.getSource().equals(btnBack)) {
            new StatisticFrm(user).setVisible(true);
            this.dispose();
        } else if ("SELECT_ROW".equals(e.getActionCommand())) {
            int row = tblResult.getSelectedRow();
            if (row >= 0 && resultList != null && row < resultList.size()) {
                SubAgentStat selectedStat = resultList.get(row);
                if(selectedStat.getTotalQuantity()==0){
                    JOptionPane.showMessageDialog(null, "Sub-agent has no invoice");
                    return;
                }
                try {
                    Date start = new SimpleDateFormat("dd/MM/yyyy").parse(txtStartDate.getText());
                    Date end = new SimpleDateFormat("dd/MM/yyyy").parse(txtEndDate.getText());
                    System.out.println(start+" " + end);
                    new InvoiceFrm(user, selectedStat, start, end).setVisible(true);
                    SubAgentStatFrm.this.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(SubAgentStatFrm.this, "Invalid date format");
                    List<SubAgentStat> allAgents = new SubAgentStatDAO().getNoSubAgents();
                    for (SubAgentStat sa : allAgents) {
                        sa.setTotalQuantity(0);
                        sa.setTotalRevenue(0);
                    }

                    loadTable(allAgents);
                }
            }
        }
    }
    public static void main(String[] args) {
        SubAgentStatFrm SaFrm = new SubAgentStatFrm(SubAgentStatFrm.user);
        SaFrm.setVisible(true);
    }
}