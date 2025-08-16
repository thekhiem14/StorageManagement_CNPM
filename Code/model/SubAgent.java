package model;

public class SubAgent {
    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private String taxNumber;

    public SubAgent() {
        super();
    }

    public SubAgent(String name, String address, String phoneNumber, String taxNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.taxNumber = taxNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }
}
