import java.util.LinkedList;

public class CashRegister {
	private LinkedList<Transaction> transactions;
	private int totalMoney;
	
	public CashRegister() {
		transactions = new LinkedList<Transaction>();
		totalMoney = 0;
	}
	
	public void processTransaction(Transaction transaction) {
		transactions.add(transaction);
		totalMoney = getTotalMoney() + transaction.getPayment().getAmmountPayed();
	}
	
	public void clearTransactionHistory() {
		transactions.clear();
	}
	
	public LinkedList<Transaction> getTransactions() {
		return transactions;
	}

	public int getTotalMoney() {
		return totalMoney;
	}
}
