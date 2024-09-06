package week1.stage4;

import java.util.Scanner;

public class Main {
    public static void displayInventory(RetailStore retailStore, boolean omitNoStockItems) {
        if (retailStore != null) {
            int counter = 1;
            for (CommercialItem item : retailStore.getItems()) {
                if (item != null && (!omitNoStockItems || item.getUnitsInStock() > 0)) {
                    System.out.println(counter + ") " + item);
                    counter++;
                }
            }
        }
    }

    public static void main(String[] args) {
        RetailStore retailStore = new RetailStore();
        retailStore.getItems()[0] = new CommercialItem("Jacket", 50, 59.96, 0);
        retailStore.getItems()[1] = new CommercialItem("Jeans", 40, 34.95, 0);
        retailStore.getItems()[2] = new CommercialItem("Shirt", 60, 24.95, 0);
        while (true) {
            System.out.println("Current stock:");
            displayInventory(retailStore, true);
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the name of the item to order (or 'q' to quit): ");
            String itemDesc = scanner.nextLine();
            if (itemDesc.equals("q")) {
                break;
            }
            CommercialItem foundItem = null;
            for (CommercialItem item : retailStore.getItems()) {
                if (item != null && item.getItemDesc() != null && item.getItemDesc().equals(itemDesc)) {
                    foundItem = item;
                }
            }
            if (foundItem == null) {
                System.out.println("Could not find an item with the name: '" + itemDesc + "'");
                continue;
            }
            if (foundItem.getUnitsInStock() <= 0) {
                System.out.println("That item is out of stock. Sorry!");
                continue;
            }
            System.out.print("Enter the number of units to order: ");
            int numberOfUnits = scanner.nextInt();
            if (numberOfUnits < 1 || numberOfUnits > foundItem.getUnitsInStock()) {
                System.out.println("Invalid number of units entered: " + numberOfUnits);
                System.out.println("The number of units ordered must be between 1 - " + foundItem.getUnitsInStock());
                continue;
            }
            double totalCharge = retailStore.orderItem(itemDesc, numberOfUnits);
            System.out.println("Thank you for your purchase! The total charge is: " + totalCharge);
        }
        System.out.println("The total revenue is: " + retailStore.getTotalRevenue());
        System.out.println("Current stock:");
        displayInventory(retailStore, false);
    }
}
