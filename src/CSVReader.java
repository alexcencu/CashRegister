import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class CSVReader {
	 private static CSVReader single_instance = null;
	  
	 private CSVReader() {
		 try {
			 File file = new File("products.csv");
			 if (file.createNewFile()) {
				 FileWriter productWriter = new FileWriter("products.csv");
				 productWriter.write("id,price,name\n");
				 productWriter.close();
			 }
			 
			 file = new File("cashiers.csv");
			 if (file.createNewFile()) {
				 FileWriter cashierWriter = new FileWriter("cashiers.csv");
				 cashierWriter.write("id,workshift,name\n");
				 cashierWriter.close();
			 }
			 
			 file = new File("clients.csv");
			 if (file.createNewFile()) {
				 FileWriter clientWriter = new FileWriter("clients.csv");
				 clientWriter.write("id,shopping_list\n");
				 clientWriter.close();
			 }
			 
			 file = new File("transactions.csv");
			 if (file.createNewFile()) {
				 FileWriter transactionWriter = new FileWriter("transactions.csv");
				 transactionWriter.write("id_client,id_cashier,shopping_list,payment\n");
				 transactionWriter.close();
			 }
		 } catch (IOException e) {
			 System.out.println("An error occurred with the class csv files.");
			 e.printStackTrace();
		 }
	 }
	 
	 
	 public static CSVReader getInstance() { 
		 if (single_instance == null) 
			 single_instance = new CSVReader(); 
		 return single_instance;
	 }
	 
		private Cashier getCashierById(Integer id, LinkedList<Cashier> cashiers) {
			for (Cashier cashier : cashiers) {
				if (cashier.getId() == id) {
					return cashier;
				}
			}
			return null;
		}
		
		private Client getClientById(Integer id, LinkedList<Client> clients) {
			for (Client client : clients) {
				if (client.getId() == id) {
					return client;
				}
			}
			return null;
		}
		
		private Product getProductById(Integer id, LinkedList<Product> products) {
			for (Product product : products) {
				if (product.getId() == id) {
					return product;
				}
			}
			return null;
		}
	 
	 public LinkedList<Product> getProducts() {
		LinkedList<Product> products = new LinkedList<Product>();
		 try {
			FileInputStream fis = new FileInputStream("products.csv");
			Scanner sc = new Scanner(fis);
			//Skip over csv header
			sc.nextLine();
			
			while (sc.hasNextLine()) {
				String[] fields = sc.nextLine().split(",");
				products.add(new Product(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), fields[2]));
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred with reading the products from the csv file.");
			e.printStackTrace();
		}
		return products;
	 }

	 public LinkedList<Cashier> getCashiers() {
		LinkedList<Cashier> cashiers = new LinkedList<Cashier>();
		 try {
			FileInputStream fis = new FileInputStream("cashiers.csv");
			Scanner sc = new Scanner(fis);
			//Skip over csv header
			sc.nextLine();
			
			while (sc.hasNextLine()) {
				String[] fields = sc.nextLine().split(",");
				cashiers.add(new Cashier(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), fields[2]));
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred with reading the cashiers from the csv file.");
			e.printStackTrace();
		}
		return cashiers;
	 }

	 public LinkedList<Client> getClients(LinkedList<Product> products) {
			LinkedList<Client> clients = new LinkedList<Client>();
			 try {
				FileInputStream fis = new FileInputStream("clients.csv");
				Scanner sc = new Scanner(fis);
				//Skip over csv header
				sc.nextLine();
				
				while (sc.hasNextLine()) {
					String[] fields = sc.nextLine().split(",");
					
					clients.add(new Client(Integer.parseInt(fields[0])));
					String[] items = fields[1].split(" ");
					for (String item : items) {
						System.out.println(item);
						if (!(item != null && !item.trim().isEmpty())) {
							break;
						}
						item = item.trim();
						String[] values = item.split("-");
						clients.getLast().addProduct(getProductById(Integer.parseInt(values[0]), products), Integer.parseInt(values[1]));
					}
				}
				sc.close();
			} catch (FileNotFoundException e) {
				System.out.println("An error occurred with reading the cashiers from the csv file.");
				e.printStackTrace();
			}
			return clients;
		 }
	 
	 public LinkedList<Transaction> getTransactions(LinkedList<Client> clients, LinkedList<Cashier> cashiers) {
		 LinkedList<Transaction> transactions = new LinkedList<Transaction>();
		 try {
			FileInputStream fis = new FileInputStream("transactions.csv");
			Scanner sc = new Scanner(fis);
			//Skip over csv header
			sc.nextLine();
			
			while (sc.hasNextLine()) {
				String[] fields = sc.nextLine().split(",");
				Client client = getClientById(Integer.parseInt(fields[0]), clients);
				Cashier cashier = getCashierById(Integer.parseInt(fields[1]), cashiers);
				Payment payment;
				
				String[] items = fields[3].split(" ");
				
				if (items[0].equals("card")) {
					payment = new CardPayment(Integer.parseInt(items[1]), items[2], items[3]);
				} else {
					payment = new CashPayment(Integer.parseInt(items[1]), Integer.parseInt(items[2]));
				}
				transactions.add(new Transaction(payment, client.getShoppingList(), cashier, client));
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred with reading the transactions from the csv file.");
			e.printStackTrace();
		}
		return transactions;
	 }
}
