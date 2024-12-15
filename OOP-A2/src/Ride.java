import java.io.*;
import java.util.*;

public class Ride implements RideInterface {
    private String rideName;
    private ThrillLevel thrillLevel;
    private String status;
    private Employee operator;
    private PriorityQueue<Visitor> visitorQueue;
    private LinkedList<Visitor> rideHistory;
    private int maxRider;
    private int numOfCycles;

    public Ride() {
        this.visitorQueue = new PriorityQueue<>((v1, v2) -> v1.getTicketId().compareTo(v2.getTicketId()));
        this.rideHistory = new LinkedList<>();
        this.maxRider = 1;
        this.numOfCycles = 0;
    }

    public Ride(String rideName, ThrillLevel thrillLevel, String status, Employee operator, int maxRider) {
        this();
        this.rideName = rideName;
        this.thrillLevel = thrillLevel;
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

    public ThrillLevel getThrillLevel() {
        return thrillLevel;
    }

    public void setThrillLevel(ThrillLevel thrillLevel) {
        this.thrillLevel = thrillLevel;
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

    public boolean isFull() {
        return visitorQueue.size() >= maxRider;
    }

    public void openRide() {
        if ("Closed".equalsIgnoreCase(status)) {
            status = "Open";
            System.out.println(String.format("[INFO] The ride %s is now open.", rideName));
        } else {
            System.out.println(String.format("[INFO] The ride %s is already open.", rideName));
        }
    }

    public void closeRide() {
        if ("Open".equalsIgnoreCase(status)) {
            status = "Closed";
            System.out.println(String.format("[INFO] The ride %s is now closed.", rideName));
        } else {
            System.out.println(String.format("[INFO] The ride %s is already closed.", rideName));
        }
    }

    @Override
    public void addVisitorToQueue(Visitor visitor) {
        visitorQueue.add(visitor);
        System.out.println(String.format("[INFO] Visitor added to queue: %-25s | Ticket ID: %-10s", visitor.getName(), visitor.getTicketId()));
    }

    @Override
    public void removeVisitorFromQueue() {
        if (!visitorQueue.isEmpty()) {
            Visitor removedVisitor = visitorQueue.poll();
            System.out.println(String.format("[INFO] Visitor removed from queue: %-25s", removedVisitor.getName()));
        } else {
            System.out.println("[WARNING] Queue is empty. No visitor to remove.");
        }
    }

    @Override
    public void printQueue() {
        if (visitorQueue.isEmpty()) {
            System.out.println("[INFO] Queue is empty.");
        } else {
            System.out.println("[INFO] Visitors in queue:");
            for (Visitor visitor : visitorQueue) {
                System.out.println(String.format("  - %-25s | Ticket ID: %-10s", visitor.getName(), visitor.getTicketId()));
            }
        }
    }

    @Override
    public void addVisitorToHistory(Visitor visitor) {
        rideHistory.add(visitor);
        System.out.println(String.format("[INFO] Visitor added to history: %-25s | Ticket ID: %-10s", visitor.getName(), visitor.getTicketId()));
    }

    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        boolean found = rideHistory.contains(visitor);
        String status = found ? "is" : "is not";
        System.out.println(String.format("[INFO] Visitor %s %s in ride history.", visitor.getName(), status));
        return found;
    }

    @Override
    public int numberOfVisitors() {
        return rideHistory.size();
    }

    @Override
    public void printRideHistory() {
        if (rideHistory.isEmpty()) {
            System.out.println("[INFO] Ride history is empty.");
        } else {
            System.out.println("[INFO] Visitors in ride history:");
            for (Visitor visitor : rideHistory) {
                System.out.println(String.format("  %-25s | Ticket ID: %-10s | Visit Time: %-10s | Thrill Level: %-10s",
                        visitor.getName(), visitor.getTicketId(), visitor.getVisitTime(), visitor.getPreferredThrillLevel()));
            }
        }
    }

    @Override
    public void runOneCycle() {
        if (operator == null) {
            System.out.println("[ERROR] No operator assigned. Cannot run the ride.");
            return;
        }
        if (visitorQueue.isEmpty()) {
            System.out.println("[ERROR] Queue is empty. Cannot run the ride.");
            return;
        }

        System.out.println("[INFO] Running one cycle...");
        int riders = 0;
        while (!visitorQueue.isEmpty() && riders < maxRider) {
            Visitor visitor = visitorQueue.poll();
            addVisitorToHistory(visitor);
            System.out.println(String.format("  %-25s has taken the ride.", visitor.getName()));
            riders++;
        }
        numOfCycles++;
        System.out.println(String.format("[INFO] Cycle completed. Total cycles: %d\n", numOfCycles));
    }

    public void sortRideHistory(VisitorComparator comparator) {
        rideHistory.sort(comparator);
        System.out.println("[INFO] Ride history sorted successfully.");
    }

    @Override
    public void exportRideHistory(String fileName, boolean asJson) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Visitor visitor : rideHistory) {
                writer.write(asJson ? visitor.toJson() : visitor.toCsv());
                writer.newLine();
            }
            System.out.println(String.format("[INFO] Ride history exported to file: %s", fileName));
        } catch (IOException e) {
            System.err.println(String.format("[ERROR] Error exporting ride history: %s", e.getMessage()));
        }
    }

    @Override
    public void importRideHistory(String fileName, boolean fromJson) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Visitor visitor = fromJson ? Visitor.fromJson(line) : Visitor.fromCsv(line);
                rideHistory.add(visitor);
            }
            System.out.println(String.format("[INFO] Ride history imported from file: %s", fileName));
        } catch (IOException e) {
            System.err.println(String.format("[ERROR] Error importing ride history: %s", e.getMessage()));
        }
    }
}
