package week1.extracredit;

import javax.sound.midi.SysexMessage;
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

    public static CommercialItem inputCommericalItem(Scanner scanner) {
        System.out.print("What is the item's name? ");
        String itemDesc = scanner.nextLine();
        System.out.print("How many units are in stock? ");
        int itemsInStock = scanner.nextInt();
        scanner.nextLine();
        System.out.print("What is the item's unit price? ");
        double unitPrice = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("How many units have already been sold? ");
        int numUnitsSold = scanner.nextInt();
        scanner.nextLine();
        return new CommercialItem(itemDesc, itemsInStock, unitPrice, numUnitsSold);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numItems;
        while (true) {
            System.out.print("Enter the number of stock items you wish to input: ");
            numItems = scanner.nextInt();
            scanner.nextLine();
            if (numItems < 1) {
                System.out.println("You must enter at least 1 stock item");
            } else {
                break;
            }
        }
        RetailStore retailStore = new RetailStore(numItems);
        int counter = 1;
        while (counter <= numItems) {
            System.out.println("Enter the details for item " + counter);
            CommercialItem item = inputCommericalItem(scanner);
            retailStore.getItems()[counter - 1] = item;
            counter++;
        }
        while (true) {
            System.out.println("Current stock:");
            displayInventory(retailStore, true);
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
            scanner.nextLine();
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
