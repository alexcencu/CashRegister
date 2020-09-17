
public abstract class Payment {
	private int ammountPayed;

	public int getAmmountPayed() {
		return ammountPayed;
	}

	public void setAmmountPayed(int ammountPayed) {
		this.ammountPayed = ammountPayed;
	}
	
	//Added for step 2 to facilitate csv writing
	public abstract String toCSV();
}
