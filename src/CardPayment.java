
public class CardPayment extends Payment {
	private String cardNumber;
	private String bankID;
	
	public CardPayment(int ammountPayed, String cardNumber, String bankID) {
		this.bankID = bankID;
		this.cardNumber = cardNumber;
		this.setAmmountPayed(ammountPayed);
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public String getBankID() {
		return bankID;
	}
	
	public String toString() {
		return "Payment of " + getAmmountPayed();
	}

	@Override
	public String toCSV() {
		return "card " + getAmmountPayed() + " " + cardNumber + " " + bankID;
	}
}
