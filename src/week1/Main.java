package week1;

public class Main {
    public static void main(String[] args) {
        CommercialItem commercialItem = new CommercialItem("A basket of apples", 3, 10.0, 0);
        System.out.println(commercialItem);
        System.out.println("Revenue: " + commercialItem.getRevenue());
        commercialItem.order(2);
        System.out.println(commercialItem);
        System.out.println("Revenue: " + commercialItem.getRevenue());
    }
}
