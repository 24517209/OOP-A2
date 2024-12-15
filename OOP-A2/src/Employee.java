public class Employee extends Person {
    private String position;
    private String employeeId;
    private int skillLevel;

    public Employee() {
        super();
    }

    public Employee(String name, int age, String gender, String position, String employeeId, int skillLevel) {
        super(name, age, gender);
        this.position = position;
        this.employeeId = employeeId;
        this.skillLevel = skillLevel;
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

    public int getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }

    public void performDuties() {
        switch (position.toLowerCase()) {
            case "thrill operator":
                System.out.println("[INFO] Ensures all rides are operated safely and efficiently.");
                break;
            case "safety officer":
                System.out.println("[INFO] Conducts safety checks and ensures compliance with park safety protocols.");
                break;
            case "maintenance":
                System.out.println("[INFO] Maintains and repairs rides to ensure they function correctly.");
                break;
            default:
                System.out.println("[INFO] Performs general duties in the park.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Position: %-15s | Employee ID: %-10s | Skill Level: %-3d", position, employeeId, skillLevel);
    }
}
