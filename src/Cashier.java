
public class Cashier extends Person {
	private int workShift;
	private String name;

	public String getName() {
		return name;
	}

	public int getWorkShift() {
		return workShift;
	}
	
	public Cashier(int id, int workShift, String name) {
		this.setId(id);
		this.workShift = workShift;
		this.name = name;
	}
	
	public String toString() {
		return "Cashier nr. " + getId() + ", named " + name + ", work shift " + workShift;
	}
}
