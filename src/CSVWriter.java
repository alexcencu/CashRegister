import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class CSVWriter {
	 private static CSVWriter single_instance = null; 
	 private FileWriter productWriter;
	 private FileWriter cashierWriter;
	 private FileWriter clientWriter;
	 private FileWriter transactionWriter;
	 
	 private CSVWriter() {
		 try {
			 File file = new File("products.csv");
			 productWriter = new FileWriter("products.csv", true);
			 if (file.createNewFile()) {
				 productWriter.write("id,price,name\n");
			 }
			 
			 file = new File("cashiers.csv");
			 cashierWriter = new FileWriter("cashiers.csv", true);
			 if (file.createNewFile()) {
				 cashierWriter.write("id,workshift,name\n");
			 }
			 
			 file = new File("clients.csv");
			 clientWriter = new FileWriter("clients.csv", true);
			 if (file.createNewFile()) {
				 clientWriter.write("id,shopping_list\n");
			 }
			 
			 file = new File("transactions.csv");
			 transactionWriter = new FileWriter("transactions.csv", true);
			 if (file.createNewFile()) {
				 transactionWriter.write("id_client,id_cashier,shopping_list,payment\n");
			 }
		 } catch (IOException e) {
			 System.out.println("An error occurred with the class csv files.");
			 e.printStackTrace();
		 }
	 }
	 
	 public void closeWriter() {
		 try {
			productWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred with closing the product writer.");
			e.printStackTrace();
		}
		 try {
			cashierWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred with closing the cashier writer.");
			e.printStackTrace();
		}
		 try {
			clientWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred with closing the client writer.");
			e.printStackTrace();
		}
		 try {
			transactionWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred with closing the transaction writer.");
			e.printStackTrace();
		}
	 }
	  
	 public static CSVWriter getInstance() { 
		 if (single_instance == null) 
			 single_instance = new CSVWriter(); 
		 return single_instance;
	 }
	 
	 public void writeClient(Client client) {
		 try {
			clientWriter.write(client.getId() + ",");
			for (Map.Entry<Product, Integer> entry : client.getShoppingList().entrySet()) {
				clientWriter.write(entry.getKey().getName() + "-" + entry.getValue()+ " ");
			}
			clientWriter.write("\n");
		} catch (IOException e) {
			System.out.println("An error occurred with the writing of client " + client.toString());
			e.printStackTrace();
		}
	 }
	 
	public void writeCashier(Cashier cashier) {
		try {
			cashierWriter.write(cashier.getId() + "," + cashier.getWorkShift() + "," + cashier.getName() + "\n");
		} catch (IOException e) {
			System.out.println("An error occurred with the writing of cashier " + cashier.toString());
			e.printStackTrace();
		}
	}
	
	public void writeProduct(Product product) {
		 try {
			productWriter.write(product.getId() + "," + product.getPrice() + "," + product.getName() + "\n");
		} catch (IOException e) {
			System.out.println("An error occurred with the writing of product " + product.toString());
			e.printStackTrace();
		}
	}
	
	public void writeTransaction(Transaction transaction) {
		 try {
			transactionWriter.write(transaction.getClient().getId() + ",");
			transactionWriter.write(transaction.getCashier().getId() + ",");
			for (Map.Entry<Product, Integer> entry : transaction.getClient().getShoppingList().entrySet()) {
				transactionWriter.write(entry.getValue() + "-" + entry.getKey().getName() + " ");
			}
			transactionWriter.write("," + transaction.getPayment().toCSV() + "\n");
		} catch (IOException e) {
			System.out.println("An error occurred with the writing of transaction " + transaction.toString());
			e.printStackTrace();
		}
	}
	
	public void clearTransactionHistory() {
		try {
			transactionWriter.close();
			transactionWriter = new FileWriter("transactions.csv");
			transactionWriter.write("id_client,id_cashier,shopping_list,payment\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
