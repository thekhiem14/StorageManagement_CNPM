package dao;

import model.SubAgentStat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class SubAgentStatDAO extends DAO{
    public SubAgentStatDAO() {
        super();
    }

    public List<SubAgentStat> getSaByRev(Date start, Date end) {
        List<SubAgentStat> subAgentStats = new ArrayList<>();
        String sql = """
        SELECT sa.ID,
            sa.name,
            sa.address,
            sa.phonenumber,
            sa.taxNumber,
            ROUND(SUM(ii.quantity * ii.unitprice * (1 + ii.VAT / 100))) AS TotalRevenue, 
            SUM(ii.quantity) AS TotalQuantity                                            
        FROM tblSubAgent sa
        Left join  tblInvoice i ON sa.ID = i.tblSubAgentID and i.date BETWEEN ? AND ?
        left JOIN tblInvoiceItem ii ON i.ID = ii.tblInvoiceID
        group by sa.ID, sa.name, sa.address, sa.phonenumber, sa.taxNumber
        ORDER BY TotalRevenue desc
    """;

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            java.sql.Date sqlStart = new java.sql.Date(start.getTime());
            java.sql.Date sqlEnd = new java.sql.Date(end.getTime());
            ps.setDate(1, sqlStart);
            ps.setDate(2, sqlEnd);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SubAgentStat subAgentStat = new SubAgentStat();
                subAgentStat.setId(rs.getInt("ID"));
                subAgentStat.setName(rs.getString("name"));
                subAgentStat.setAddress(rs.getString("address"));
                subAgentStat.setPhoneNumber(rs.getString("phonenumber"));
                subAgentStat.setTaxNumber(rs.getString("taxNumber"));
                subAgentStat.setTotalRevenue(rs.getDouble("TotalRevenue"));
                subAgentStat.setTotalQuantity(rs.getInt("TotalQuantity"));
                subAgentStats.add(subAgentStat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return subAgentStats;
    }
    public List<SubAgentStat> getNoSubAgents() {
        List<SubAgentStat> list = new ArrayList<>();
        try {
            String sql = "SELECT ID, name, address, phonenumber, taxNumber FROM tblSubAgent";
            PreparedStatement pr = DAO.con.prepareStatement(sql);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                SubAgentStat sa = new SubAgentStat();
                sa.setId(rs.getInt("ID"));
                sa.setName(rs.getString("name"));
                sa.setAddress(rs.getString("address"));
                sa.setPhoneNumber(rs.getString("phonenumber"));
                sa.setTaxNumber(rs.getString("taxNumber"));
                sa.setTotalQuantity(0);
                sa.setTotalRevenue(0);
                list.add(sa);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
