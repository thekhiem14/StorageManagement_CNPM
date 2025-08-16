package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Invoice {
    private int id;
    private Date date;
    private int numberItem;
    private double totalAmount;
    private User user;
    private SubAgent subAgent;
    private List<InvoiceItem> invoiceItems;

   public Invoice() {
       invoiceItems = new ArrayList<InvoiceItem>();
   }
   public Invoice(Date date, User user, SubAgent subAgent, List<InvoiceItem> invoiceItems) {
       this.date = date;
       this.user = user;
       this.subAgent = subAgent;
       this.invoiceItems = invoiceItems;
   }

    public int getId() {
        return id;
    }

    public void setId(int id) {
       this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
       this.date = date;
    }

    public int getNumberItem() {
       updateNumberItem();
        return numberItem;
    }

    public void updateNumberItem() {
       for(InvoiceItem invoiceItemtem : invoiceItems) {
           numberItem+=invoiceItemtem.getQuantity();
       }
    }

    public double getTotalAmount() {
       updateTotalAmount();
        return totalAmount;
    }

    public void updateTotalAmount() {
        for(InvoiceItem invoiceItem : invoiceItems) {
            totalAmount += invoiceItem.getAmount();
        }
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
       this.user = user;
    }

    public SubAgent getSubAgent() {
        return subAgent;
    }

    public void setSubAgent(SubAgent subAgent) {
       this.subAgent = subAgent;
    }

    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
       this.invoiceItems = invoiceItems;
    }

    public void addInvoiceItem(InvoiceItem invoiceItem) {
       invoiceItems.add(invoiceItem);
    }
}
