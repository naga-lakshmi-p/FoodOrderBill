package foodorder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FoodOrder {

	static Scanner scanner = new Scanner(System.in);
	static Order order = new Order();
	
	public static int getServiceType() {
		int orderType = -1;

		do {
			System.out.print("Hello Welcome to Numpy Ninja restaurant!\n"
					+ "Please select the service offering from below list:\n"
					+ "[1] dine-in\r\n"
					+ "[2] take away\n"
					+ "Please enter the option - ");

			orderType = scanner.nextInt();
	
			if (orderType == 1 || orderType == 2) {
				break;
			}
	
			System.out.println("Invalid order type.");

		} while (orderType != 1 && orderType != 2);
		
		return orderType;
	}
	
	public static int getCategory() {
		int category = -1;
		
		do {
			System.out.print("Please select the category from the below list:\n"
					+ "[1] Vegetarian\r\n"
					+ "[2] Non-vegetaria\n"
					+ "Please enter the option - ");

			category = scanner.nextInt();
	
			if (category == 1 || category == 2) {
				break;
			}
	
			System.out.println("Invalid category type.");

		} while (category != 1 && category != 2);
		
		return category;
	}
	
	private static HashMap<Integer, HashMap<Integer, FoodItem>> GetMenu() {
		// Create veg menu
		HashMap<Integer, FoodItem> vegMenu = new HashMap<>();
		vegMenu.put(1, new FoodItem(1, "Paneer tikka", 120f));
		vegMenu.put(2, new FoodItem(2,"Cashew pulao", 150f));
		vegMenu.put(3, new FoodItem(3,"Veg fried rice", 130f));
		vegMenu.put(4, new FoodItem(4,"Gobi 65",100f));
		vegMenu.put(5, new FoodItem(5,"Veg.Thali",140f));
		
		// Create non-veg menu
		HashMap<Integer, FoodItem> nonvegMenu = new HashMap<>();
		nonvegMenu.put(1, new FoodItem(1, "Chicken briyan", 200f));
		nonvegMenu.put(2, new FoodItem(2, "Fish curry", 150f));
		nonvegMenu.put(3, new FoodItem(3, "Chicken 65", 120f ));
		nonvegMenu.put(4, new FoodItem(4, "Mutton gravy",220f));
		nonvegMenu.put(5, new FoodItem(5, "Chicken fried rice",180f));
		
		// Display menu for selected category
		HashMap<Integer, HashMap<Integer, FoodItem>> menu = new HashMap<>();
		menu.put(1, vegMenu);
		menu.put(2, nonvegMenu);
		
		return menu;
	}

	public static void main(String[] args) {
		int serviceType = getServiceType();
		System.out.println(serviceType);
		
		order.setOrderType(serviceType);
		
		char needMoreFood = 'N';
		do {
			int category = getCategory();
			System.out.println(category);
			
			HashMap<Integer, HashMap<Integer, FoodItem>> menu = GetMenu();
			
			// Get menu for given category and print the menu
			HashMap<Integer, FoodItem> selectedCategory = menu.get(category);
			
			System.out.println();
			
			// Loop and show requested menu items
			for(Map.Entry<Integer, FoodItem> item : selectedCategory.entrySet()) {
				FoodItem foodItem = item.getValue();
				System.out.printf("%d. %s - Rs. \"%.02f\" (per item)\n",
						foodItem.getId(), foodItem.getName(), foodItem.getPrice());
			}
			
			System.out.print("\nPlease enter the option - ");
			int selectedFoodItemId = scanner.nextInt();
			System.out.println(selectedFoodItemId);
			FoodItem selectedFoodItem = selectedCategory.get(selectedFoodItemId);
			
			System.out.printf("\nPlease enter number of “%s” you would like to order ", selectedFoodItem.getName());
			
			selectedFoodItem.setQuantity(scanner.nextInt());
			
			order.addItem(selectedFoodItem);
			
			
			System.out.print("Would you like to order one more dish ? [Y] or [N] - ");
			needMoreFood = scanner.next().charAt(0);
			System.out.println();
		} while(needMoreFood == 'Y' || needMoreFood == 'y');
		
		//System.out.println(order.getItems());
		
		System.out.println("Total cost : " + order.getOriginalTotal());
		System.out.println();
		System.out.println("GST tax : " + order.getGst());
		System.out.println();
		System.out.println("Parcel/Dine-in charge : " + order.getExtraCharges());
		System.out.println();
		System.out.println("Discount :" + order.getDiscount());
		System.out.println();
		System.out.println("Total bill amount : " + order.getTotalCharges());
		
		scanner.close();
	}
}
