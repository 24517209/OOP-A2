public class Visitor extends Person {
    private String ticketId;
    private String visitTime;

    public Visitor() {
        super();
    }

    public Visitor(String name, int age, String gender, String ticketId, String visitTime) {
        super(name, age, gender);
        this.ticketId = ticketId;
        this.visitTime = visitTime;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(String visitTime) {
        this.visitTime = visitTime;
    }

    public String toCsv() {
        return getName() + "," + getAge() + "," + getGender() + "," + ticketId + "," + visitTime;
    }

    public static Visitor fromCsv(String csv) {
        String[] parts = csv.split(",");
        return new Visitor(parts[0], Integer.parseInt(parts[1]), parts[2], parts[3], parts[4]);
    }
}
