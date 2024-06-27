//2022112454 멀티미디어소프트웨어공학 백지은
package minicafe;

import java.util.HashMap;
import java.util.Set;

public class Menu {
	
	private HashMap<String, Integer> menu;
	
	public Menu() {
		menu = new HashMap<String, Integer>();
		menu.put("water", 1000);
		menu.put("Americano", 1500);
		menu.put("latte", 2500);
		menu.put("Cookie", 3000);
	}
	
	public boolean addMenu(String menuName, Integer price) {
		boolean bFlag;
		if(menu.containsKey(menuName)) {
			bFlag = false;
		}
		else{
			menu.put(menuName, price);
			bFlag = true;
		}
		return bFlag;
	}
	
	public boolean deleteMenu(String menuName) {
		boolean bFlag;
		if(menu.containsKey(menuName)) {
			menu.remove(menuName);
			bFlag = true;
		}
		else{
			bFlag = false;
		}
		return bFlag;
	}
	
	public boolean editMenuPrice(String menuName, Integer editPrice) {
		boolean bFlag;
		if(menu.containsKey(menuName)) {
			menu.replace(menuName, editPrice);
			bFlag = true;
		}
		else {
			bFlag = false;
		}
		return bFlag;
	}
	
	public int getPrice(String menuName) {
		int price;
		try {
			price = (int)menu.get(menuName);
		}
		catch (NullPointerException ex) {
			System.out.println("해당 메뉴가 없습니다.");
			price = 0;
		}
		return price;
	}

	public Set<String> getMenuList(){
		return menu.keySet();
	}

}
