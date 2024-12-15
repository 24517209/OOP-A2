import java.util.Objects;

public class Visitor extends Person {
    private String ticketId;
    private String visitTime;
    private String preferredThrillLevel;

    public Visitor() {
        super();
    }

    public Visitor(String name, int age, String gender, String ticketId, String visitTime, String preferredThrillLevel) {
        super(name, age, gender);
        this.ticketId = ticketId;
        this.visitTime = visitTime;
        this.preferredThrillLevel = preferredThrillLevel;
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

    public String getPreferredThrillLevel() {
        return preferredThrillLevel;
    }

    public void setPreferredThrillLevel(String preferredThrillLevel) {
        this.preferredThrillLevel = preferredThrillLevel;
    }

    
    public String toCsv() {
        return getName() + "," + getAge() + "," + getGender() + "," + ticketId + "," + visitTime + "," + preferredThrillLevel;
    }


    public static Visitor fromCsv(String csv) {
        String[] parts = csv.split(",");
        return new Visitor(parts[0], Integer.parseInt(parts[1]), parts[2], parts[3], parts[4], parts[5]);
    }


    public String toJson() {
        return String.format("{\"name\": \"%s\", \"age\": %d, \"gender\": \"%s\", \"ticketId\": \"%s\", \"visitTime\": \"%s\", \"preferredThrillLevel\": \"%s\"}",
                getName(), getAge(), getGender(), ticketId, visitTime, preferredThrillLevel);
    }

 
    public static Visitor fromJson(String json) {
        try {
            String[] parts = json.replace("{", "").replace("}", "").replace("\"", "").split(", ");
            String name = parts[0].split(": ")[1];
            int age = Integer.parseInt(parts[1].split(": ")[1]);
            String gender = parts[2].split(": ")[1];
            String ticketId = parts[3].split(": ")[1];
            String visitTime = parts[4].split(": ")[1];
            String preferredThrillLevel = parts[5].split(": ")[1];
            return new Visitor(name, age, gender, ticketId, visitTime, preferredThrillLevel);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid JSON format: " + json);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Visitor visitor = (Visitor) obj;
        return Objects.equals(getName(), visitor.getName()) && Objects.equals(ticketId, visitor.getTicketId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), ticketId);
    }

    @Override
    public String toString() {
        return super.toString() + ", Ticket ID: " + ticketId + ", Visit Time: " + visitTime + ", Preferred Thrill Level: " + preferredThrillLevel;
    }
}
