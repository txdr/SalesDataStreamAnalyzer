public class Sale {

    private int saleId;
    private String employeeName;
    private String region;
    private double amount;

    public Sale(int saleId, String employeeName, String region, double amount) {
        this.saleId = saleId;
        this.employeeName = employeeName;
        this.region = region;
        this.amount = amount;
    }

    // Accessor Methods
    public int getSaleId() {
        return saleId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getRegion() {
        return region;
    }

    public double getAmount() {
        return amount;
    }

    // Override toString()
    @Override
    public String toString() {
        return saleId + " | " +  employeeName + " | " + region + " | " + String.format("%.2f", amount);
    }

}
