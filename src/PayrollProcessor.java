import helper.CurrencyFormatter;

import java.util.ArrayList;
import java.util.List;

public class PayrollProcessor {

    public static List<Employee> employees = new ArrayList<>();

    public static double TAX_RATE = 0.2;

    public static void addEmployee(Employee e) {
        employees.add(e);
        AuditLogger.logEmployeeAdded(e);
    }

    public static void processPayroll() {
        double total = 0;
        PayrollSummary summary = new PayrollSummary();

        AuditLogger.logPayrollStart(employees.size());

        for (Employee e : employees) {
            ValidationService.validateEmployee(e);

            double pay = 0;

            if (e != null && "salaried".equals(e.type)) {
                double gross = e.monthlySalary;
                pay = calculateSalariedPay(e);
                summary.recordSalaried(gross, pay);
            } else if (e != null && "contractor".equals(e.type)) {
                double gross = e.hourlyRate * e.hoursWorked;
                pay = calculateContractorPay(e);
                summary.recordContractor(gross, pay);
            } else {
                System.out.println("Unknown employee type: " + (e == null ? "<null>" : e.type));
                summary.recordUnknown();
                AuditLogger.logUnknownType(e);
            }

            total += pay;
            System.out.println("Pay for " + (e == null ? "<unknown>" : e.name) + ": " + CurrencyFormatter.format(pay));
            AuditLogger.logPayComputed(e, pay);
        }

        System.out.println("Total payroll: " + CurrencyFormatter.format(total));
        printSummary(summary);
        AuditLogger.logPayrollEnd(summary);
    }

    public static double calculateSalariedPay(Employee e) {
        double tax = e.monthlySalary * TAX_RATE;
        return e.monthlySalary - tax;
    }

    public static double calculateContractorPay(Employee e) {
        return e.hourlyRate * e.hoursWorked;
    }

    private static void printSummary(PayrollSummary summary) {
        System.out.println("Payroll summary:");
        System.out.println("  salaried count: " + summary.getSalariedCount());
        System.out.println("  contractor count: " + summary.getContractorCount());
        System.out.println("  unknown count: " + summary.getUnknownCount());
        System.out.println("  total gross: " + CurrencyFormatter.format(summary.getTotalGross()));
        System.out.println("  total net: " + CurrencyFormatter.format(summary.getTotalNet()));
    }

    private static double calculateUnusedBonus(Employee e) {
        double bonus = 0.05 * (e.type.equals("salaried") ? e.monthlySalary : e.hourlyRate * e.hoursWorked);
        return bonus;
    }
}