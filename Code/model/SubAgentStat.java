package model;

import java.util.Date;

public class SubAgentStat extends SubAgent {
    private double totalRevenue;
    private int totalQuantity;

    public SubAgentStat() {
        super();
    }

    public SubAgentStat(String name, String address, String phoneNumber, String taxNumber, double totalRevenue, int totalQuantity) {
        super(name, address, phoneNumber, taxNumber);
        this.totalQuantity = totalQuantity;
        this.totalRevenue = totalRevenue;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public void addTotalQuantity(int quantity) {
        this.totalQuantity += quantity;
    }

}
