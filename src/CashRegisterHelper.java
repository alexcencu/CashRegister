import java.util.LinkedList;
import java.util.Scanner;

public class CashRegisterHelper implements CashRegisterHelperInt {

	protected CashRegister cashRegister;
	protected LinkedList<Client> clients;
	protected LinkedList<Product> products;
	protected LinkedList<Cashier> cashiers;
	protected Scanner sc;
	
	public CashRegisterHelper(CashRegister cashRegister) {
		this.cashRegister = cashRegister;
		clients = new LinkedList<Client>();
		products = new LinkedList<Product>();
		cashiers = new LinkedList<Cashier>();
		sc = new Scanner(System.in);
	}
	
	
	protected Cashier getCashierById(Integer id) {
		for (Cashier cashier : cashiers) {
			if (cashier.getId() == id) {
				return cashier;
			}
		}
		return null;
	}
	
	protected Client getClientById(Integer id) {
		for (Client client : clients) {
			if (client.getId() == id) {
				return client;
			}
		}
		return null;
	}
	
	protected Product getProductById(Integer id) {
		for (Product product : products) {
			if (product.getId() == id) {
				return product;
			}
		}
		return null;
	}
	
	
	public void initializeData() {
		// TODO add data
	}
	
	public void clearHelper() {
		sc.close();
	}
	
	@Override
	public void showProducts() {
		System.out.println("List of products:");
		for (Product product : products) {
			System.out.println(product);
		}
		System.out.println("Press Enter to Continue...");
	}

	@Override
	public void addProduct() {
		System.out.println("Enter the id of the product:");
		String id = sc.next();
		System.out.println("Enter the price of the product:");
		String price = sc.next();
		System.out.println("Enter the name of the product:");
		String name = sc.next();
		Product prod = new Product(Integer.parseInt(id),Integer.parseInt(price),name);
		products.add(prod);
		System.out.println("Press Enter to Continue...");
	}

	@Override
	public void showClients() {
		System.out.println("List of clients:");
		for (Client client : clients) {
			System.out.println(client);
		}
		System.out.println("Press Enter to Continue...");
	}

	@Override
	public void addClient() {
		System.out.println("Enter the id of the client:");
		String id = sc.next();
		Client newClient = new Client(Integer.parseInt(id));
		System.out.println("You will now enter the ids of the products, enter -1 to stop:");
		while (true) {
			Integer productId = Integer.parseInt(sc.next());
			if (productId == -1) {
				break;
			}
			newClient.addProduct(getProductById(productId), 1);
		}
		clients.add(newClient);
		System.out.println("Press Enter to Continue...");
	}

	@Override
	public void showCashiers() {
		System.out.println("List of cashiers:");
		for (Cashier cashier : cashiers) {
			System.out.println(cashier);
		}
		System.out.println("Press Enter to Continue...");
	}

	@Override
	public void addCashier() {
		System.out.println("Enter the id of the cashier:");
		String id = sc.next();
		System.out.println("Enter the workshift of the cashier:");
		String workshift = sc.next();
		System.out.println("Enter the name of the cashier:");
		String name = sc.next();
		Cashier cashier = new Cashier(Integer.parseInt(id), Integer.parseInt(workshift), name);
		cashiers.add(cashier);
		System.out.println("Press Enter to Continue...");
	}

	@Override
	public void showTransactions() {
		System.out.println("List of transactions: ");
		for (Transaction transaction : cashRegister.getTransactions()) {
			System.out.println(transaction);
		}
		System.out.println("Press Enter to Continue...");
	}

	@Override
	public void addTransaction() {		
		System.out.println("Enter the id of the cashier:");
		Integer cashierId = Integer.parseInt(sc.next());
		Cashier cashier = getCashierById(cashierId);
		
		System.out.println("Enter the id of the client:");
		Integer clientId = Integer.parseInt(sc.next());
		Client client = getClientById(clientId);
		
		int ammountToPay = client.getPrice();
		
		System.out.println("You have to pay " + ammountToPay + " do you want to pay by cash (1) or card (2)?");
		Integer option = Integer.parseInt(sc.next());
		
		Payment payment;
		
		if (option == 1) {
			System.out.println("Enter how much you will pay:");
			Integer ammountPayed = Integer.parseInt(sc.next());
			payment = new CashPayment(ammountPayed, ammountPayed - ammountToPay);
		} else {
			System.out.println("Enter the card number:");
			String cardNumber = sc.next();
			System.out.println("Enter the bank id:");
			String bankId = sc.next();
			payment = new CardPayment(ammountToPay, cardNumber, bankId);
		}
		
		Transaction transaction = new Transaction(payment, client.getShoppingList(), cashier, client);
		cashRegister.processTransaction(transaction);
		System.out.println("Press Enter to Continue...");	
	}

	@Override
	public void showMoney() {
		System.out.println("Money in the cash register: ");
		System.out.println(cashRegister.getTotalMoney());
		System.out.println("Press Enter to Continue...");	
	}

	@Override
	public void clearTransactionHistory() {
		System.out.println("Clearing transactions");
		cashRegister.clearTransactionHistory();
		System.out.println("Press Enter to Continue...");
	}

}
