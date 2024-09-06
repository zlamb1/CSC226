package week1.stage2;

public class Main {
    public static void main(String[] args) {
        RetailStore retailStore = new RetailStore();
        retailStore.getItems()[0] = new CommercialItem("A basket of apples", 3, 10.0, 0);
        retailStore.getItems()[1] = new CommercialItem("A jar of pickles", 13, 3.0, 0);
        retailStore.getItems()[2] = new CommercialItem("A bag of oranges", 7, 5.0, 0);
        System.out.println(retailStore);
        for (CommercialItem item : retailStore.getItems()) {
            int unitsInStock = item.getUnitsInStock();
            int numUnitsToBuy = (int)Math.floor(Math.random() * unitsInStock);
            System.out.println("Buying " + numUnitsToBuy + " units of " + item);
            retailStore.orderItem(item.getItemDesc(), numUnitsToBuy);
            System.out.println(item);
            System.out.println("Revenue: " + item.getRevenue());
        }
        System.out.println("Total Revenue: " + retailStore.getTotalRevenue());
    }
}
