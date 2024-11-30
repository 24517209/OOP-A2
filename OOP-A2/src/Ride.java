import java.io.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Ride implements RideInterface {
    private String rideName;
    private String status;
    private Employee operator;
    private Queue<Visitor> visitorQueue;
    private LinkedList<Visitor> rideHistory;
    private int maxRider;
    private int numOfCycles;

    public Ride() {
        this.visitorQueue = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
        this.maxRider = 1;
        this.numOfCycles = 0;
    }

    public Ride(String rideName, String status, Employee operator, int maxRider) {
        this();
        this.rideName = rideName;
        this.status = status;
        this.operator = operator;
        this.maxRider = Math.max(maxRider, 1);
    }

    public String getRideName() {
        return rideName;
    }

    public void setRideName(String rideName) {
        this.rideName = rideName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Employee getOperator() {
        return operator;
    }

    public void setOperator(Employee operator) {
        this.operator = operator;
    }

    public int getMaxRider() {
        return maxRider;
    }

    public void setMaxRider(int maxRider) {
        this.maxRider = Math.max(maxRider, 1);
    }

    public int getNumOfCycles() {
        return numOfCycles;
    }

    @Override
    public void addVisitorToQueue(Visitor visitor) {
        visitorQueue.add(visitor);
        System.out.println("Visitor added to queue: " + visitor.getName());
    }

    @Override
    public void removeVisitorFromQueue() {
        if (!visitorQueue.isEmpty()) {
            Visitor removedVisitor = visitorQueue.poll();
            System.out.println("Visitor removed from queue: " + removedVisitor.getName());
        } else {
            System.out.println("Queue is empty. No visitor to remove.");
        }
    }

    @Override
    public void printQueue() {
        if (visitorQueue.isEmpty()) {
            System.out.println("The queue is empty.");
        } else {
            System.out.println("Visitors in queue:");
            for (Visitor visitor : visitorQueue) {
                System.out.println("- " + visitor.getName());
            }
        }
    }

    @Override
    public void addVisitorToHistory(Visitor visitor) {
        rideHistory.add(visitor);
        System.out.println("Visitor added to history: " + visitor.getName());
    }

    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        boolean found = rideHistory.contains(visitor);
        System.out.println("Visitor " + visitor.getName() + (found ? " is " : " is not ") + "in ride history.");
        return found;
    }

    @Override
    public int numberOfVisitors() {
        return rideHistory.size();
    }

    @Override
    public void printRideHistory() {
        if (rideHistory.isEmpty()) {
            System.out.println("Ride history is empty.");
        } else {
            System.out.println("Visitors in ride history:");
            Iterator<Visitor> iterator = rideHistory.iterator();
            while (iterator.hasNext()) {
                Visitor visitor = iterator.next();
                System.out.println("- " + visitor.getName());
            }
        }
    }

    @Override
    public void runOneCycle() {
        if (operator == null) {
            System.out.println("No operator assigned. Cannot run the ride.");
            return;
        }
        if (visitorQueue.isEmpty()) {
            System.out.println("Queue is empty. Cannot run the ride.");
            return;
        }

        System.out.println("Running one cycle...");
        int riders = 0;
        while (!visitorQueue.isEmpty() && riders < maxRider) {
            Visitor visitor = visitorQueue.poll();
            addVisitorToHistory(visitor);
            System.out.println("Visitor " + visitor.getName() + " has taken the ride.");
            riders++;
        }
        numOfCycles++;
        System.out.println("Cycle completed. Total cycles: " + numOfCycles);
    }

    public void sortRideHistory(VisitorComparator comparator) {
        Collections.sort(rideHistory, comparator);
        System.out.println("Ride history sorted successfully.");
    }

    // Part 6: 导出历史记录到文件
    public void exportRideHistory(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Visitor visitor : rideHistory) {
                writer.write(visitor.toCsv());
                writer.newLine();
            }
            System.out.println("Ride history exported to file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error exporting ride history: " + e.getMessage());
        }
    }

    // Part 7: 从文件导入历史记录
    public void importRideHistory(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Visitor visitor = Visitor.fromCsv(line);
                rideHistory.add(visitor);
            }
            System.out.println("Ride history imported from file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error importing ride history: " + e.getMessage());
        }
    }
}
