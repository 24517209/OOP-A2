public class AssignmentTwo {
    public static void main(String[] args) {
        AssignmentTwo assignment = new AssignmentTwo();

        // 调用各部分方法
        assignment.partThree();
        assignment.partFourA();
        assignment.partFourB();
        assignment.partFive();
        assignment.partSix();
        assignment.partSeven();
    }
    private void addVisitorsToQueue(Ride ride, String[][] visitorsData) {
    for (String[] data : visitorsData) {
        Visitor visitor = new Visitor(data[0], Integer.parseInt(data[1]), data[2], data[3], data[4]);
        ride.addVisitorToQueue(visitor);
    }
}

private void addVisitorsToHistory(Ride ride, String[][] visitorsData) {
    for (String[] data : visitorsData) {
        Visitor visitor = new Visitor(data[0], Integer.parseInt(data[1]), data[2], data[3], data[4]);
        ride.addVisitorToHistory(visitor);
    }
}


   public void partThree() {
    Ride rollerCoaster = new Ride("Roller Coaster", "Open", null, 5);

    String[][] visitorsData = {
        {"Jack", "25", "Male", "T001", "10:00 AM"},
        {"Sharon", "30", "Female", "T002", "10:10 AM"},
        {"Benny", "20", "Male", "T003", "10:15 AM"},
        {"Leo", "28", "Male", "T004", "10:20 AM"},
        {"Nehemia", "26", "Female", "T005", "10:25 AM"}
    };
    addVisitorsToQueue(rollerCoaster, visitorsData);

    System.out.println("Initial Queue:");
    rollerCoaster.printQueue();

    rollerCoaster.removeVisitorFromQueue();

    System.out.println("Updated Queue:");
    rollerCoaster.printQueue();
}

    public void partFourA() {
    Ride rollerCoaster = new Ride("Roller Coaster", "Open", null, 5);

    String[][] visitorsData = {
        {"Jack", "25", "Male", "T001", "10:00 AM"},
        {"Sharon", "30", "Female", "T002", "10:10 AM"},
        {"Benny", "20", "Male", "T003", "10:15 AM"},
        {"Leo", "28", "Male", "T004", "10:20 AM"},
        {"Nehemia", "26", "Female", "T005", "10:25 AM"}
    };
    addVisitorsToHistory(rollerCoaster, visitorsData);

    System.out.println("Number of visitors in history: " + rollerCoaster.numberOfVisitors());
    rollerCoaster.printRideHistory();
}

    public void partFourB() {
    Ride rollerCoaster = new Ride("Roller Coaster", "Open", null, 5);

    String[][] visitorsData = {
        {"Tom", "32", "Male", "T006", "10:00 AM"},
        {"Sherly", "24", "Female", "T007", "10:05 AM"},
        {"Ben", "30", "Male", "T008", "10:10 AM"},
        {"David", "28", "Male", "T009", "10:15 AM"},
        {"Alice", "27", "Female", "T010", "10:20 AM"}
    };
    addVisitorsToHistory(rollerCoaster, visitorsData);

    System.out.println("Unsorted Ride History:");
    rollerCoaster.printRideHistory();

    rollerCoaster.sortRideHistory(new VisitorComparator());

    System.out.println("Sorted Ride History:");
    rollerCoaster.printRideHistory();
}

    public void partFive() {
        Ride rollerCoaster = new Ride("Roller Coaster", "Open", new Employee("John", 35, "Male", "Operator", "EMP001"), 3);

        // 添加 10 名游客到队列
        for (int i = 1; i <= 10; i++) {
            rollerCoaster.addVisitorToQueue(new Visitor("Visitor" + i, 20 + i, "Gender" + i, "T00" + i, "10:" + i + "0 AM"));
        }

        System.out.println("Initial Queue:");
        rollerCoaster.printQueue();

        rollerCoaster.runOneCycle();

        System.out.println("Queue after one cycle:");
        rollerCoaster.printQueue();

        System.out.println("Ride History:");
        rollerCoaster.printRideHistory();
    }

    public void partSix() {
        Ride rollerCoaster = new Ride("Roller Coaster", "Open", null, 5);

        // 添加游客到历史记录
        rollerCoaster.addVisitorToHistory(new Visitor("Jack", 25, "Male", "T001", "10:00 AM"));
        rollerCoaster.addVisitorToHistory(new Visitor("Sharon", 30, "Female", "T002", "10:10 AM"));
        rollerCoaster.addVisitorToHistory(new Visitor("Benny", 20, "Male", "T003", "10:15 AM"));
        rollerCoaster.addVisitorToHistory(new Visitor("Leo", 28, "Male", "T004", "10:20 AM"));
        rollerCoaster.addVisitorToHistory(new Visitor("Nehemia", 26, "Female", "T005", "10:25 AM"));

        rollerCoaster.exportRideHistory("ride_history.csv");
    }

    public void partSeven() {
        Ride rollerCoaster = new Ride("Roller Coaster", "Open", null, 5);

        rollerCoaster.importRideHistory("ride_history.csv");

        System.out.println("Number of visitors in history: " + rollerCoaster.numberOfVisitors());
        rollerCoaster.printRideHistory();
    }
}
