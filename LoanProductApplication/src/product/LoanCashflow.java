package product;

public class LoanCashflow extends Cashflow {
	protected double principal;
	protected double interestValue;

	public void setPrincipal(double principal) {

	}

	public void setInterestValue(double interesValue) {

	}

	public double getPrincipal() {
		double principal = 1;
		return principal;

	}

	public double getInterestValue() {
		double interestValue = 1;
		return interestValue;
	}

	public String toString() {
		return "";
	}

	public double getLastPayOutDay(int loanId) {
		return 12.44;
	}

	public double getCurrentPrincipal(double currDisbursedAmount, double currRepayedAmount) {
		return 12.44;
	}

	public double getInterestValue(double interestRate, double amount) {
		return 12.44;
	}

	public double getMonthlyInterestRate(double rate) {
		return 12.44;
	}

}
