import java.util.HashMap;

public class Transaction {
	private Payment payment;
	private HashMap<Product, Integer> products;
	private Cashier cashier;
	private Client client;
	
	public Transaction(Payment payment, HashMap<Product, Integer> products,
			Cashier cashier, Client client) {
		this.payment = payment;
		this.products = products;
		this.cashier = cashier;
		this.client = client;
	}

	@Override
	public String toString() {
		return "Transaction from client " + client.toString() + " to cashier " + cashier.toString() + " with payment " +
	payment.toString();
	}

	public Payment getPayment() {
		return payment;
	}

	public HashMap<Product, Integer> getProducts() {
		return products;
	}

	public Cashier getCashier() {
		return cashier;
	}

	public Client getClient() {
		return client;
	}
}
