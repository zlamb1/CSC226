package week1.stage2;

import java.util.Arrays;

public class RetailStore {
    private CommercialItem[] items;

    public RetailStore() {
        items = new CommercialItem[3];
    }

    public CommercialItem[] getItems() {
        return items;
    }

    public double orderItem(String itemName, int numUnitsToOrder) {
        for (CommercialItem item : items) {
            if (item != null && item.getItemDesc() != null && item.getItemDesc().equals(itemName)) {
                return item.order(numUnitsToOrder);
            }
        }
        return 0;
    }

    public double getTotalRevenue() {
        double total = 0;
        for (CommercialItem item : items) {
            if (item != null) {
                total += item.getRevenue();
            }
        }
        return total;
    }

    @Override
    public String toString() {
        return "RetailStore{" +
                "items=" + Arrays.toString(items) +
                '}';
    }
}
