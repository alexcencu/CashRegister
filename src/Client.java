import java.util.HashMap;
import java.util.Map;


public class Client extends Person {
	private HashMap<Product, Integer> shoppingList;
	
	public Client(int id) {
		this.setId(id);
		shoppingList = new HashMap<Product, Integer>();
	}

	public HashMap<Product, Integer> getShoppingList() {
		return shoppingList;
	}
	
	public void addProduct(Product product, int count) {
		if (shoppingList.containsKey(product)) {
			shoppingList.replace(product, (new Integer(count)) + shoppingList.get(product));
		} else {
			shoppingList.put(product, new Integer(count));
		}
	}
	
	public void removeProduct(Product product, int count) {
		if (!shoppingList.containsKey(product)) {
			return;
		}
		
		if (shoppingList.get(product) <= count) {
			shoppingList.remove(product);
		} else {
			shoppingList.replace(product, shoppingList.get(product) - (new Integer(count)));
		}
	}
	
	public int getPrice() {
		int price = 0;
		for (Map.Entry<Product, Integer> entry : shoppingList.entrySet()) {
			price += entry.getKey().getPrice() * entry.getValue();
		}
		return price;
	}
	
	public String toString() {
		String str = "Client nr. " + getId() + ", shopping list ";
		for (Map.Entry<Product, Integer> entry : shoppingList.entrySet()) {
			str += entry.getValue() + " of " + entry.getKey().getName() + " ";
		}
		return str;
	}
}
