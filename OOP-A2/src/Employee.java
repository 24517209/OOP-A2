public class Employee extends Person {
    private String position;
    private String employeeId;

    public Employee() {
        super();
    }

    public Employee(String name, int age, String gender, String position, String employeeId) {
        super(name, age, gender);
        this.position = position;
        this.employeeId = employeeId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
    