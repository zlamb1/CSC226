package week4;

import java.util.Scanner;

public class Main {
    private static int getIntOrDefault(Scanner scanner, String prompt, int defaultInt) {
        System.out.print(prompt);
        String result = scanner.nextLine();
        try {
            return Integer.parseInt(result);
        } catch (NumberFormatException e) {
            return defaultInt;
        }
    }

    private static double getDoubleOrDefault(Scanner scanner, String prompt, double defaultDouble) {
        System.out.print(prompt);
        String result = scanner.nextLine();
        try {
            return Double.parseDouble(result);
        } catch (NumberFormatException e) {
            return defaultDouble;
        }
    }

    public static int testSingleLine(int simulationDuration, double arrivalRate, int minServiceTime, int maxServiceTime, int numServiceProviders) {
        int average = 0;
        for (int i = 0; i < 100; i++) {
            average += Supermarket.simulateSingleLine(simulationDuration, arrivalRate, minServiceTime, maxServiceTime, numServiceProviders);
        }
        return average / 100;
    }

    public static int testMultipleLines(int simulationDuration, double arrivalRate, int minServiceTime, int maxServiceTime, int numLines) {
        int average = 0;
        for (int i = 0; i < 100; i++) {
            average += Supermarket.simulateMultipleLines(simulationDuration, arrivalRate, minServiceTime, maxServiceTime, numLines);
        }
        return average / 100;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int simulationDuration = 60, minServiceTime = 2, maxServiceTime = 8;
        double arrivalRate = 0.5;
        boolean enteredParams = false;
        while (true) {
            System.out.print("Enter the simulation type (single, multiple, or q to quit): ");
            String type = scanner.nextLine();
            if (type.equals("q")) {
                break;
            }
            if (enteredParams) {
                System.out.print("Do you want to repeat with the same parameters (y/n)? ");
                if (!scanner.nextLine().equals("y")) {
                    simulationDuration = getIntOrDefault(scanner, "Enter the simulation duration: ", 60);
                    arrivalRate = getDoubleOrDefault(scanner, "Enter the arrival rate (0.0 - 1.0): ", 0.5);
                    minServiceTime = getIntOrDefault(scanner, "Enter the minimum service time: ", 2);
                    maxServiceTime = getIntOrDefault(scanner, "Enter the maximum service time: ", 8);
                }
            } else {
                simulationDuration = getIntOrDefault(scanner, "Enter the simulation duration: ", 60);
                arrivalRate = getDoubleOrDefault(scanner, "Enter the arrival rate (0.0 - 1.0): ", 0.5);
                minServiceTime = getIntOrDefault(scanner, "Enter the minimum service time: ", 2);
                maxServiceTime = getIntOrDefault(scanner, "Enter the maximum service time: ", 8);
            }
            System.out.println("Test Parameters {simulationDuration=" + simulationDuration + "s, arrivalRate=" + (arrivalRate * 100) + "%, minServiceTime=" + minServiceTime + "s, maxServiceTime=" + maxServiceTime + "s}");
            if (type.equals("single")) {
                System.out.println("Single Line (1 Provider): " + testSingleLine(simulationDuration, arrivalRate, minServiceTime, maxServiceTime, 1) + "s");
                System.out.println("Single Line (2 Providers): " + testSingleLine(simulationDuration, arrivalRate, minServiceTime, maxServiceTime, 2) + "s");
                System.out.println("Single Line (3 Providers): " + testSingleLine(simulationDuration, arrivalRate, minServiceTime, maxServiceTime, 3) + "s");
            } else if (type.equals("multiple")) {
                System.out.println("Multiple Lines (1 Line): " + testMultipleLines(simulationDuration, arrivalRate, minServiceTime, maxServiceTime, 1) + "s");
                System.out.println("Multiple Lines (2 Lines): " + testMultipleLines(simulationDuration, arrivalRate, minServiceTime, maxServiceTime, 2) + "s");
                System.out.println("Multiple Lines (3 Lines): " + testMultipleLines(simulationDuration, arrivalRate, minServiceTime, maxServiceTime, 3) + "s");
            } else {
                // TODO: handle
            }
            enteredParams = true;
        }
        System.out.println("Goodbye! :)");
    }
}