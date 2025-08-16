package test.unit;

import dao.SubAgentStatDAO;
import model.SubAgentStat;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SubAgentDAOTest {
    SubAgentStatDAO dao  = new SubAgentStatDAO();

    @Test
    public void testSubAgentInPeriod() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = sdf.parse("2025-01-01");
        Date end = sdf.parse("2025-01-31");
        List<SubAgentStat> list = dao.getSaByRev(start, end);
        Assert.assertNotNull(list);
        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void testNoInvoiceInPeriod() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = sdf.parse("1999-01-01");
        Date end = sdf.parse("1999-01-31");
        List<SubAgentStat> list = dao.getSaByRev(start, end);
        Assert.assertNotNull(list);
        Assert.assertEquals(0, list.size());
    }
}
