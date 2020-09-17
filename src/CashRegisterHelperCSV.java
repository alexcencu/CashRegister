
public class CashRegisterHelperCSV extends CashRegisterHelper {
	
	protected AuditService auditService;
	protected CSVReader csvReader;
	protected CSVWriter csvWriter;
	
	
	public CashRegisterHelperCSV(CashRegister cashRegister) {
		super(cashRegister);
		auditService = AuditService.getInstance();
		csvReader = CSVReader.getInstance();
		csvWriter = CSVWriter.getInstance();
	}

	public void initializeData() {
		products = csvReader.getProducts();
		cashiers = csvReader.getCashiers();
		clients = csvReader.getClients(products);
		for (Transaction transaction : csvReader.getTransactions(clients, cashiers)) {
			cashRegister.processTransaction(transaction);
		}
	}
	
	public void clearHelper() {
		csvWriter.closeWriter();
		auditService.closeAudit();
	}

	@Override
	public void addProduct() {
		super.addProduct();
		csvWriter.writeProduct(products.getLast());
		auditService.logAction("addProduct");
	}
	
	
	@Override
	public void showProducts() {
		super.showProducts();
		auditService.logAction("showProducts");
	}

	@Override
	public void showClients() {
		super.showClients();
		auditService.logAction("showClients");
	}

	@Override
	public void addClient() {
		super.addClient();
		csvWriter.writeClient(clients.getLast());
		auditService.logAction("addClient");
	}

	@Override
	public void showCashiers() {
		super.showCashiers();
		auditService.logAction("showCashiers");
	}

	@Override
	public void addCashier() {
		super.addCashier();
		csvWriter.writeCashier(cashiers.getLast());
		auditService.logAction("addCashier");
	}

	@Override
	public void showTransactions() {
		super.showTransactions();
		auditService.logAction("showTransactions");
	}

	@Override
	public void addTransaction() {
		super.addTransaction();
		csvWriter.writeTransaction(cashRegister.getTransactions().getLast());
		auditService.logAction("addTransaction");
	}

	@Override
	public void showMoney() {
		super.showMoney();
		auditService.logAction("showMoney");
	}

	@Override
	public void clearTransactionHistory() {
		super.clearTransactionHistory();
		csvWriter.clearTransactionHistory();
		auditService.logAction("clearTransactionHistory");
	}

}
