package week4;

import structures.chapter4.CircularLinkedQ;
import structures.chapter4.IQueue;

import java.util.ArrayList;
import java.util.LinkedList;

public class Supermarket {
    public static int simulateSingleLine(int simulationDuration, double arrivalRate, int minServiceTime, int maxServiceTime, int numServiceProviders) {
        ArrayList<ServiceProvider> providers = new ArrayList<>();
        for (int i = 0; i < numServiceProviders; i++) {
            providers.add(new ServiceProvider(minServiceTime, maxServiceTime));
        }

        IQueue<Integer> line = new CircularLinkedQ<>();
        int elapsedTime = 0, totalCustomerDuration = 0, numCustomersServed = 0;
        while (elapsedTime < simulationDuration) {
            if (Math.random() < arrivalRate) {
                line.enqueue(elapsedTime);
            }
            for (ServiceProvider provider : providers) {
                if (line.isEmpty()) {
                    break;
                }
                if (provider.doWork()) {
                    totalCustomerDuration += elapsedTime - line.dequeue();
                    numCustomersServed++;
                }
            }
            elapsedTime++;
        }

        return totalCustomerDuration / numCustomersServed;
    }

    public static int simulateMultipleLines(int simulationDuration, double arrivalRate, int minServiceTime, int maxServiceTime, int numLines) {
        ArrayList<ServiceProvider> providers = new ArrayList<>();
        ArrayList<IQueue<Integer>> lines = new ArrayList<>();
        for (int i = 0; i < numLines; i++) {
            providers.add(new ServiceProvider(minServiceTime, maxServiceTime));
            lines.add(new CircularLinkedQ<>());
        }

        int elapsedTime = 0, totalCustomerDuration = 0, numCustomersServed = 0;
        while (elapsedTime < simulationDuration) {
            if (Math.random() < arrivalRate) {
                IQueue<Integer> shortestLine = null;
                for (int i = 0; i < numLines; i++) {
                    IQueue<Integer> line = lines.get(i);
                    if (shortestLine == null || shortestLine.getSize() > line.getSize()) {
                        shortestLine = line;
                    }
                }
                assert shortestLine != null;
                shortestLine.enqueue(elapsedTime);
            }
            for (int i = 0; i < lines.size(); i++) {
                ServiceProvider provider = providers.get(i);
                IQueue<Integer> line = lines.get(i);
                if (!line.isEmpty()) {
                    if (provider.doWork()) {
                        totalCustomerDuration += elapsedTime - line.dequeue();
                        numCustomersServed++;
                    }
                }
            }
            elapsedTime++;
        }

        return totalCustomerDuration / numCustomersServed;
    }
}
