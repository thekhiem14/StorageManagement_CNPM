package model;

import java.util.Date;

public class InvoiceItem {
    private int id;
    private int quantity;
    private double unitprice;
    private double VAT;
    private Item item;
    private double amount;

    public InvoiceItem(){}

    public InvoiceItem(double VAT, Item item, int quantity) {
        this.VAT = VAT;
        this.item = item;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }

    public double getVAT() {
        return VAT;
    }

    public void setVAT(double VAT) {
        this.VAT = VAT;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public double getAmount() {
//        System.out.println(amount);
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
