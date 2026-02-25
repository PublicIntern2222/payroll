import helper.CurrencyFormatter;

public class AuditLogger {

    public static void logEmployeeAdded(Employee employee) {
        System.out.println("[audit] employee added: " + safeName(employee));
    }

    public static void logPayrollStart(int employeeCount) {
        System.out.println("[audit] payroll run started for " + employeeCount + " employees");
    }

    public static void logPayrollEnd(PayrollSummary summary) {
        System.out.println("[audit] payroll run finished: " + summary.getEmployeeCount() + " employees processed");
    }

    public static void logUnknownType(Employee employee) {
        System.out.println("[audit] unknown employee type: " + safeType(employee));
    }

    public static void logPayComputed(Employee employee, double pay) {
        System.out.println("[audit] pay computed for " + safeName(employee) + ": " + CurrencyFormatter.format(pay));
    }

    private static String safeName(Employee employee) {
        if (employee == null || employee.name == null || employee.name.trim().isEmpty()) {
            return "<unknown>";
        }
        return employee.name;
    }

    private static String safeType(Employee employee) {
        if (employee == null || employee.type == null) {
            return "<null>";
        }
        return employee.type;
    }
}

