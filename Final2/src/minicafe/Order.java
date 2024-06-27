//2022112454 멀티미디어소프트웨어공학 백지은
package minicafe;

import java.util.HashMap;

public class Order {
	
	private HashMap<String, Integer> order;
	
	public Order() {
		order = new HashMap<String, Integer>();
	}
	
	public boolean addOrder(String menuName, Integer amount) {
		boolean bFlag = false;
		if(order.containsKey(menuName)) {
			order.remove(menuName);
			order.put(menuName, amount);
			bFlag = true;
		}
		else {
			order.put(menuName, amount);
			bFlag = true;
		}
		return bFlag;
	}
	
	public boolean deleteOrder(String menuName) {
		boolean bFlag = false;
		if(order.containsKey(menuName)) {
			order.remove(menuName);
			bFlag = true;
		}
		else {
			bFlag = false;
		}
		return bFlag;
	}
	
	public boolean editOrderAmount(String menuName, Integer editAmount) {
		boolean bFlag = false;
		if(order.containsKey(menuName)) {
			order.replace(menuName, editAmount);
			bFlag = true;
		}
		return bFlag;
	}
	
	public HashMap<String, Integer> getOrderList(){
		return order;
	}

}
