import java.util.ArrayList;

public class AssignmentTwo {
    public static void main(String[] args) {
        AssignmentTwo assignment = new AssignmentTwo();
        assignment.partThree();
        assignment.partFourA();
        assignment.partFourB();
        assignment.partFive();
        assignment.partSix();
        assignment.partSeven();
    }

    private void addVisitorsToQueue(Ride ride, String[][] visitorsData) {
        for (String[] data : visitorsData) {
            Visitor visitor = new Visitor(data[0], Integer.parseInt(data[1]), data[2], data[3], data[4], data[5]);
            ride.addVisitorToQueue(visitor);
        }
    }

    private void addVisitorsToHistory(Ride ride, String[][] visitorsData) {
        for (String[] data : visitorsData) {
            Visitor visitor = new Visitor(data[0], Integer.parseInt(data[1]), data[2], data[3], data[4], data[5]);
            ride.addVisitorToHistory(visitor);
        }
    }

    public void partThree() {
        Ride ride = new Ride("The Flash: Speed Force", ThrillLevel.MAX, "Open", null, 5);
        String[][] visitorsData = {
                {"Francisco Hansen", "22", "Male", "T001", "10:00 AM", "MAX"},
                {"Isabella Jorgensen", "25", "Female", "T002", "10:10 AM", "MILD"},
                {"Mateo Larsen", "20", "Male", "T003", "10:15 AM", "MODERATE"},
                {"Sofia Petersen", "30", "Female", "T004", "10:20 AM", "MAX"},
                {"Antonio Pedersen", "28", "Male", "T005", "10:25 AM", "MILD"}
        };
        addVisitorsToQueue(ride, visitorsData);

        System.out.println("Initial Queue:");
        ride.printQueue();

        ride.removeVisitorFromQueue();

        System.out.println("Updated Queue:");
        ride.printQueue();
    }

    public void partFourA() {
        Ride ride = new Ride("Green Lantern Coaster", ThrillLevel.MAX, "Open", null, 5);
        String[][] visitorsData = {
                {"Valentina Christensen", "32", "Female", "T006", "10:30 AM", "MODERATE"},
                {"Jennifer Madsen", "24", "Female", "T007", "10:35 AM", "MILD"},
                {"Michael Olsen", "30", "Male", "T008", "10:40 AM", "MAX"},
                {"Emily Nielsen", "28", "Female", "T009", "10:45 AM", "MILD"},
                {"William Andersen", "27", "Male", "T010", "10:50 AM", "MODERATE"}
        };
        addVisitorsToHistory(ride, visitorsData);

        System.out.println("Number of visitors in history: " + ride.numberOfVisitors());
        ride.printRideHistory();

        Visitor searchVisitor = new Visitor("Jennifer Madsen", 24, "Female", "T007", "10:35 AM", "MILD");
        ride.checkVisitorFromHistory(searchVisitor);
    }

    public void partFourB() {
        Ride ride = new Ride("Wild West Falls Adventure Ride", ThrillLevel.MODERATE, "Open", null, 5);
        String[][] visitorsData = {
                {"Jessica Petersen", "29", "Female", "T011", "11:00 AM", "MILD"},
                {"Brayden Hansen", "35", "Male", "T012", "11:10 AM", "MAX"},
                {"Jaxson Pedersen", "19", "Male", "T013", "11:15 AM", "MODERATE"},
                {"Leonardo Larsen", "22", "Male", "T014", "11:20 AM", "MAX"},
                {"Landon Christensen", "26", "Male", "T015", "11:25 AM", "MILD"}
        };
        addVisitorsToHistory(ride, visitorsData);

        System.out.println("Unsorted Ride History:");
        ride.printRideHistory();

        ride.sortRideHistory(new VisitorComparator());

        System.out.println("Sorted Ride History:");
        ride.printRideHistory();
    }

    public void partFive() {
        Employee operator = new Employee("Sam Andersen", 35, "Male", "Thrill Operator", "EMP001", 5);
        Ride ride = new Ride("The Wizard of Oz", ThrillLevel.MILD, "Open", operator, 3);
        String[][] visitorsData = {
                {"Declan Madsen", "21", "Male", "T016", "12:10 PM", "MILD"},
                {"Wyatt Olsen", "23", "Male", "T017", "12:15 PM", "MODERATE"},
                {"Abel Hansen", "24", "Male", "T018", "12:20 PM", "MAX"},
                {"Anthony Pedersen", "25", "Male", "T019", "12:25 PM", "MILD"},
                {"Jayden Larsen", "22", "Male", "T020", "12:30 PM", "MAX"}
        };
        addVisitorsToQueue(ride, visitorsData);

        System.out.println("Initial Queue:");
        ride.printQueue();

        ride.runOneCycle();

        System.out.println("Queue after one cycle:");
        ride.printQueue();

        System.out.println("Ride History:");
        ride.printRideHistory();
    }

    public void partSix() {
        Ride ride = new Ride("Flight of the Wicked Witch", ThrillLevel.MODERATE, "Open", null, 5);
        String[][] visitorsData = {
                {"Kevin Nielsen", "26", "Male", "T021", "12:35 PM", "MODERATE"},
                {"Cole Petersen", "28", "Male", "T022", "12:40 PM", "MILD"},
                {"Brandon Jorgensen", "29", "Male", "T023", "12:45 PM", "MAX"},
                {"Tyler Madsen", "30", "Male", "T024", "12:50 PM", "MODERATE"},
                {"Connor Hansen", "27", "Male", "T025", "12:55 PM", "MILD"}
        };
        addVisitorsToHistory(ride, visitorsData);

        ride.exportRideHistory("ride_history.csv", false); 
        ride.exportRideHistory("ride_history.json", true); 
        System.out.println("Ride history exported to files.");
    }

    public void partSeven() {
        Ride ride = new Ride("Action Zone", ThrillLevel.MILD, "Open", null, 5);

        ride.importRideHistory("ride_history.csv", false); 
        System.out.println("Number of visitors in history: " + ride.numberOfVisitors());
        ride.printRideHistory();

        ride.importRideHistory("ride_history.json", true); 
        System.out.println("Number of visitors in history after importing JSON: " + ride.numberOfVisitors());
        ride.printRideHistory();
    }
}
