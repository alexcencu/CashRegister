import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
	public static void showMenu() {
        System.out.println("Menu Options:");
        System.out.println("1. Show products");
        System.out.println("2. Add product");
        System.out.println("3. Show Clients");
        System.out.println("4. Add Client");
        System.out.println("5. Show cashiers");
        System.out.println("6. Add cashier");
        System.out.println("7. Show transactions");
        System.out.println("8. Add Transaction");
        System.out.println("9. Show money");
        System.out.println("10. Clear transaction history");
        System.out.println("0. Exit");
        System.out.println("");
        System.out.print("Please select an option from 1-10\r\n");
	}
	public static void main(String[] args) {
		CashRegister cashRegister = new CashRegister();
		//CashRegisterHelper helper = new CashRegisterHelper(cashRegister);
		CashRegisterHelperCSV helper = new CashRegisterHelperCSV(cashRegister);
		helper.initializeData();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input =-1;
        while (input!=0) {
        	showMenu();
        	try {
                input = Integer.parseInt(br.readLine());
                
                if(input < 0 || input > 10) {
                    System.out.println("You have entered an invalid selection, please try again\n");
                } else {
                	switch(input){
                	case 1:
                		helper.showProducts();
                		br.readLine();
            			break;
                	case 2:
                		helper.addProduct();
                		br.readLine();
            			break;
                	case 3:
                		helper.showClients();
                		br.readLine();
            			break;
                	case 4:
                		helper.addClient();
                		br.readLine();
            			break;
                	case 5:
                		helper.showCashiers();
                		br.readLine();
            			break;
                	case 6:
                		helper.addCashier();
                		br.readLine();
            			break;
                	case 7:
                		helper.showTransactions();
                		br.readLine();
            			break;
                	case 8:
                		helper.addTransaction();
                		br.readLine();
            			break;
                	case 9:
                		helper.showMoney();
                		br.readLine();
            			break;
                	case 10:
                		helper.clearTransactionHistory();
                		br.readLine();
            			break;
                	case 0:
                		helper.clearHelper();
                		return;
            		default:
            			break;
                	}	
                }
            } catch (IOException ioe) {
                System.out.println("IO error trying to read your input!");
                System.exit(1);
            }
        }
        
    }

}
