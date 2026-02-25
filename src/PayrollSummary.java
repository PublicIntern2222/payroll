public class PayrollSummary {

    private int salariedCount;
    private int contractorCount;
    private int unknownCount;
    private int employeeCount;
    private double totalGross;
    private double totalNet;

    public void recordSalaried(double gross, double net) {
        salariedCount++;
        employeeCount++;
        totalGross += gross;
        totalNet += net;
    }

    public void recordContractor(double gross, double net) {
        contractorCount++;
        employeeCount++;
        totalGross += gross;
        totalNet += net;
    }

    public void recordUnknown() {
        unknownCount++;
        employeeCount++;
    }

    public int getSalariedCount() {
        return salariedCount;
    }

    public int getContractorCount() {
        return contractorCount;
    }

    public int getUnknownCount() {
        return unknownCount;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public double getTotalGross() {
        return totalGross;
    }

    public double getTotalNet() {
        return totalNet;
    }
}

