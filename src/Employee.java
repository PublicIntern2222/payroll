public class Employee {

    public String name;
    public String type; // "salaried", "contractor"

    // salaried
    public double monthlySalary;

    // contractor
    public double hourlyRate;
    public int hoursWorked;

    public Employee(String name, String type) {
        this.name = name;
        this.type = type;
    }
}