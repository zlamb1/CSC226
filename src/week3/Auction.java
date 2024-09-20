package week3;

import structures.chapter2.ArrayStack;

import java.util.Scanner;

public class Auction {
    private ArrayStack<Integer> bidHistory;
    private String highBidder;
    int highBid, maxBid;

    public Auction() {
        this.bidHistory = new ArrayStack<>();
        this.highBidder = "Nobody";
        this.highBid = 1;
        this.maxBid = 1;
    }

    public void doAuction() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("What is your name (q to quit): ");
            String name = scanner.nextLine();
            if (name.equals("q")) {
                break;
            }
            System.out.println("The current high bid is $" + highBid);
            System.out.print("What would you like to bid? ");
            int bid = scanner.nextInt();
            scanner.nextLine();
            processBid(name, bid);
            System.out.println("highBidder: " + highBidder);
            System.out.println("highBid: " + highBid);
            System.out.println("maxBid: " + maxBid);
            System.out.println("Auction toString: " + this);
        }
        System.out.println("Auction toString: " + this.toString());
        System.out.println(this.highBidder + " wins the auction with a high bid of $" + this.highBid);
    }

    @Override
    public String toString() {
        return bidHistory.toString();
    }

    private void processBid(String name, int amount) {
        bidHistory.push(amount);
        if (amount > maxBid) {
            if (maxBid > 1) {
                this.highBid = this.maxBid + 1;
            }
            maxBid = amount;
            this.highBidder = name;
        } else if (amount > highBid) {
            highBid = amount;
        }
    }
}
