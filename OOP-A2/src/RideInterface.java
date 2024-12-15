public interface RideInterface {
    void addVisitorToQueue(Visitor visitor);

    void removeVisitorFromQueue();

    void printQueue();

    void addVisitorToHistory(Visitor visitor);

    boolean checkVisitorFromHistory(Visitor visitor);

    int numberOfVisitors();

    void printRideHistory();

    void runOneCycle();

    void sortRideHistory(VisitorComparator comparator);

    void exportRideHistory(String fileName, boolean asJson);

    void importRideHistory(String fileName, boolean fromJson);
}
