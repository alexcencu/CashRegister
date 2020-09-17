
public class CashPayment extends Payment {
	private int change;
	
	public CashPayment(int ammountPayed, int change) {
		this.setAmmountPayed(ammountPayed);
		this.change = change;
	}

	public int getChange() {
		return change;
	}
	
	public String toString() {
		return "Payment of " + getAmmountPayed() + " with change " + change;
	}

	@Override
	public String toCSV() {
		return "cash " + getAmmountPayed() + " " + change;
	}
}
