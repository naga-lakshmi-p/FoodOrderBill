package foodorder;

import java.util.ArrayList;

public class Order {

	private static final float GST_PERCENTAGE = 6f;
	private static final int DINE_IN_CHARGES = 30;
	private static final int TAKEAWAY_CHARGES = 10;
	
	private OrderTypeEnum orderType;
	
	public OrderTypeEnum getOrderType() {
		return orderType;
	}

	public void setOrderType(OrderTypeEnum orderType) {
		this.orderType = orderType;
	}
	
	public void setOrderType(int serviceType) {
		switch (serviceType) {
			case 1: 
				this.orderType = OrderTypeEnum.DINE_IN;
				break;
			case 2:
				this.orderType = OrderTypeEnum.TAKE_AWAY;
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + serviceType);
		}
	}

	private ArrayList<FoodItem> items = new ArrayList<>();
		
	public ArrayList<FoodItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<FoodItem> items) {
		this.items = items;
	}
	
	public void addItem(FoodItem item) {
		this.items.add(item);
	}
	
	public float getOriginalTotal() {
		float total = 0;
		for( FoodItem item : items) {
			total += (item.getQuantity() * item.getPrice());
		}
		
		return total;
	}
	
	public float getGst() {
		return (GST_PERCENTAGE * getOriginalTotal())/100;
	}
	
	public float getExtraCharges() {
		return (this.orderType == OrderTypeEnum.DINE_IN) ? DINE_IN_CHARGES : TAKEAWAY_CHARGES;
	}
	
	public float getDiscount() {
		float originalTotal = getOriginalTotal();
		float discountPercentage = 0;
		
		discountPercentage = originalTotal >= 1000 ? 10f : 0;
		discountPercentage = originalTotal >= 1500 ? 15f : discountPercentage;
		float discountAmount = ((discountPercentage * originalTotal) / 100);
		return discountAmount;
	}

	public float getTotalCharges() {
		return (this.getOriginalTotal() + this.getGst() + this.getExtraCharges()) - this.getDiscount();
		
	}

}
