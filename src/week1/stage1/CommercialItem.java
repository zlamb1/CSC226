package week1.stage1;

public class CommercialItem {
    private String itemDesc;
    private int unitsInStock;
    private double unitPrice;
    private int numUnitsSold;

    public CommercialItem(String itemDesc, int unitsInStock, double unitPrice, int numUnitsSold) {
        this.itemDesc = itemDesc;
        this.unitsInStock = unitsInStock;
        this.unitPrice = unitPrice;
        this.numUnitsSold = numUnitsSold;
    }

    public double order(int units) {
        if (units > this.unitsInStock) {
            return 0;
        }
        this.unitsInStock -= units;
        this.numUnitsSold += units;
        return units * this.unitPrice;
    }

    public double getRevenue() {
        return this.numUnitsSold * this.unitPrice;
    }

    @Override
    public String toString() {
        return "CommercialItem{" +
                "itemDesc='" + itemDesc + '\'' +
                ", unitsInStock=" + unitsInStock +
                ", unitPrice=" + unitPrice +
                ", numUnitsSold=" + numUnitsSold +
                '}';
    }
}
