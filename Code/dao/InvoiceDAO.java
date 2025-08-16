package dao;

import model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class InvoiceDAO extends DAO{
    public InvoiceDAO() {
        super();
    }

    public List<Invoice> getInvoice(int SaID, Date sd, Date ed){
        List<Invoice> invoices = new ArrayList<Invoice>();
        String sql = """
          SELECT
            II.ID AS `invoiceItemId`,
            I.ID AS `CodeInvoice`,
            I.`Date`,
            ROUND(SUM(II.quantity * II.unitprice * (1 + ii.VAT/100))) AS`TotalAmount`,
            IT.name AS `ItemName`,
            ii.quantity,
            ii.VAT,
            ii.unitprice
          FROM tblInvoice I
          JOIN tblSubAgent A ON I.tblSubAgentID = A.ID
          JOIN tblInvoiceItem II ON I.ID = II.tblInvoiceID
          JOIN tblItem IT ON II.tblItemID = IT.ID
          WHERE A.ID =? AND I.`Date` BETWEEN ? AND ?
          GROUP BY II.ID, I.ID, I.`Date`, IT.name, ii.quantity, ii.VAT, ii.unitprice
          ORDER BY I.ID ;
""";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            java.sql.Date st = new java.sql.Date(sd.getTime());
            java.sql.Date edt = new java.sql.Date(ed.getTime());

            ps.setInt(1, SaID);
            ps.setDate(2, st);
            ps.setDate(3, edt);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Invoice invoice = new Invoice();
                invoice.setId(rs.getInt("CodeInvoice"));
                invoice.setDate(rs.getDate("Date"));

                Item item = new Item();
                item.setName(rs.getString("ItemName"));

                InvoiceItem invoiceItem = new InvoiceItem();
                invoiceItem.setId(rs.getInt("invoiceItemId"));
                invoiceItem.setQuantity(rs.getInt("quantity"));
                invoiceItem.setUnitprice(rs.getDouble("unitprice"));
                invoiceItem.setVAT(rs.getDouble("VAT"));
                invoiceItem.setAmount(rs.getDouble("TotalAmount"));
                invoiceItem.setItem(item);

                invoice.addInvoiceItem(invoiceItem);
                invoices.add(invoice);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return invoices;
    }
}
