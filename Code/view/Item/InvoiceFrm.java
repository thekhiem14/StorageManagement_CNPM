// InvoiceFrm.java (hiển thị như hình ảnh mẫu)
package view.Item;

import dao.InvoiceDAO;
import model.Invoice;
import model.InvoiceItem;
import model.SubAgentStat;
import model.User;
import view.user.ManagerHomeFrm;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class InvoiceFrm extends JFrame implements ActionListener {
    private JTable tblResult;
    private JButton btnHome;
    private JButton btnBack;
    private JLabel lblSubAgent;
    private User user;
    private SubAgentStat stat;
    private Date startDate, endDate;

    public InvoiceFrm(User user, SubAgentStat stat, Date start, Date end) {
        super("Invoices of sub-agent: " + stat.getName());
        this.user = user;
        this.stat = stat;
        this.startDate = start;
        this.endDate = end;

        tblResult = new JTable() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        btnHome = new JButton("Home");
        btnHome.setBackground(new Color(200, 230, 250));
        btnHome.setFont(new Font("Arial", Font.PLAIN, 18));
        btnHome.addActionListener(this);

        btnBack = new JButton("Back");
        btnBack.setBackground(new Color(220, 230, 250));
        btnBack.setFont(new Font("Arial", Font.PLAIN, 18));
        btnBack.addActionListener(this);

        lblSubAgent = new JLabel("Invoices of sub-agent: " + stat.getName());
        lblSubAgent.setFont(new Font("Arial", Font.BOLD, 22));
        lblSubAgent.setHorizontalAlignment(SwingConstants.CENTER);

        String[] cols = {"No", "Code of Invoice", "Date", "Number of Item", "Unit price (VND)", "Item name", "VAT", "Total amount(VND)"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);

        double totalRevenue = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        List<Invoice> invoices = new InvoiceDAO().getInvoice(stat.getId(), start, end);

        for (Invoice invoice : invoices) {
            for (InvoiceItem invoiceItem : invoice.getInvoiceItems()) {
                Object[] row = new Object[] {
                    invoiceItem.getId(),
                    invoice.getId(),
                    sdf.format(invoice.getDate()),
                    invoiceItem.getQuantity(),
                    String.format("%,.0f", invoiceItem.getUnitprice()),
                    invoiceItem.getItem().getName(),
                    String.format("%.0f%%", invoiceItem.getVAT()),
                    String.format("%,.0f", invoiceItem.getAmount())
                };
                totalRevenue += invoiceItem.getAmount();
                model.addRow(row);
            }
        }
        Object[] sumRow = {"", "", "", "Total Revenue", "", "", "",  String.format("%,.0f", totalRevenue)};
        model.addRow(sumRow);

        tblResult.setModel(model);

        JTableHeader header = tblResult.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 18));
        header.setBackground(new Color(200, 230, 250));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tblResult.getColumnCount(); i++) {
            tblResult.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        tblResult.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setBackground(new Color(223, 241, 252));
                return this;
            }
        });

        tblResult.setFont(new Font("Arial", Font.PLAIN, 18));
        tblResult.setRowHeight(32);

        this.setLayout(new BorderLayout());
        this.add(lblSubAgent, BorderLayout.NORTH);
        this.add(new JScrollPane(tblResult), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new BorderLayout());
        btnPanel.setBackground(Color.WHITE);

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBackground(Color.WHITE);
        leftPanel.add(btnBack);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.add(btnHome);

        btnPanel.add(leftPanel, BorderLayout.WEST);
        btnPanel.add(rightPanel, BorderLayout.EAST);

        this.add(btnPanel, BorderLayout.SOUTH);

        this.setSize(1100, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == btnHome) {
            new ManagerHomeFrm(user).setVisible(true);
            this.dispose();
        } else if (src == btnBack) {
            new SubAgentStatFrm(user, startDate, endDate).setVisible(true);
            this.dispose();
        }
    }
}
