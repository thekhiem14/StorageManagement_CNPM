package model;

public class Item {
    private int id;
    private String name;
    private String description;
    private double price;
    private double VAT;

    public Item(){}

    public Item(String name, String description, double price, double VAT) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.VAT = VAT;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public double getVAT() {
        return VAT;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setVAT(double VAT) {
        this.VAT = VAT;
    }
}
