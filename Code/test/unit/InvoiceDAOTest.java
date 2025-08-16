package test.unit;

import dao.InvoiceDAO;
import model.Invoice;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class InvoiceDAOTest {
    InvoiceDAO dao = new InvoiceDAO();

    @Test
    public void testManyInvoicesOneDay() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2025-01-30");
        List<Invoice> list = dao.getInvoice(1, date, date);
        Assert.assertTrue(list.size() > 1);
    }

    @Test
    public void testExactlyInvoiceAtStartOrEndDate() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = sdf.parse("2025-01-01");
        Date end = sdf.parse("2025-01-01");
        List<Invoice> list1 = dao.getInvoice(1, start, start);
        List<Invoice> list2 = dao.getInvoice(1, end, end);
        Assert.assertFalse(list1.isEmpty());
        Assert.assertFalse(list2.isEmpty());
    }
}
