import java.util.Comparator;

public class VisitorComparator implements Comparator<Visitor> {
    @Override
    public int compare(Visitor v1, Visitor v2) {
        int ageComparison = Integer.compare(v1.getAge(), v2.getAge());
        if (ageComparison != 0) return ageComparison;

        int nameComparison = v1.getName().compareTo(v2.getName());
        if (nameComparison != 0) return nameComparison;

        return v1.getTicketId().compareTo(v2.getTicketId());
    }
}
