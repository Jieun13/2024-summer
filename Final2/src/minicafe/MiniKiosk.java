//2022112454 멀티미디어소프트웨어공학 백지은
package minicafe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class MiniKiosk {
	
	private static void prnStart() {
		System.out.println("------------------------ ");
		System.out.println("1. 주문 시작");
		System.out.println("2. 정산");
		System.out.println("3. 메뉴 수정");
		System.out.println("4. 종료");
		System.out.println("------------------------ ");
		System.out.print("메뉴를 입력하세요 : ");
	}
	
	private static void prnOrderMenu() {
		System.out.println("------------------------ ");
		System.out.println("1. 주문할 메뉴 추가");
		System.out.println("2. 주문한 메뉴 삭제");
		System.out.println("3. 모두 취소");
		System.out.println("4. 주문 완료");
		System.out.println("------------------------ ");
		System.out.print("메뉴를 입력하세요 : ");
	}
	
	private static void editMenuList() {
		System.out.println("------------------------ ");
		System.out.println("1. 신메뉴 추가");
		System.out.println("2. 기존 메뉴 삭제");
		System.out.println("3. 기존 메뉴 가격 수정");
		System.out.println("4. 종료");
		System.out.println("------------------------ ");
		System.out.print("메뉴를 입력하세요 : ");
	}
	
	private static void getTotal(int totalSell) {
		Calendar now = Calendar.getInstance();
		System.out.println();
		System.out.println("*** 오늘자 매출 조회 *** ");
		System.out.println("날짜 : " + now.getTime());
		System.out.println("매출 금액 : " + totalSell + "원");
		System.out.println();
	}
	
	private static void prnCafeMenu(Menu menu){
		int cnt = 1;
		Iterator<String> it = menu.getMenuList().iterator();
		System.out.println("---------------------");
		while(it.hasNext()) {
			System.out.println(cnt + ". " + it.next().toString());
			cnt++;
		}
		System.out.println("---------------------");
	}
	
	private static void deleteAll(Order order) {
		//Iterator<HashMap.Entry<String, Integer>> it = order.getOrderList().entrySet().iterator();
		int cnt = 0;
		Iterator<String> it = order.getOrderList().keySet().iterator();
		ArrayList keyList = new ArrayList();
        
		while(it.hasNext()) {
			String key = it.next();
			keyList.addLast(key);
		}
		
		while(keyList.isEmpty()==false) {
			order.deleteOrder((String)keyList.removeFirst());
		}
		
	}
	
	private static int prnOrder(Menu menu, Order order) {
		int amount, totalPrice = 0;
		int cnt = 1;
		String menuSelect;
		
		System.out.println("주문 목록을 출력합니다.");
		System.out.println("--------------------------");
		
		Iterator<HashMap.Entry<String, Integer>> it = order.getOrderList().entrySet().iterator();
		
		while(it.hasNext()) {
			HashMap.Entry<String, Integer> key = it.next();
			System.out.println(cnt + ". " + key.getKey() + " " + key.getValue() + "잔");
			cnt++;
			
			menuSelect = key.getKey();
			amount = key.getValue();
			if(menu.getPrice(menuSelect)==0) {
				System.out.println("존재하지 않는 메뉴가 있습니다.");
			}
			else {
				totalPrice += (int)amount * (int)menu.getPrice(menuSelect);
			}
		}
		System.out.println("--------------------------");
		System.out.println("총 주문액은 " + totalPrice + "원 입니다.");
		
		return totalPrice;
	}
	
	public static void main(String[] args) {
		
		Menu menu = new Menu();
		
		
		int startSelect, mSelect, editSelect; 
		int totalSell = 0;
		
		Scanner oInDev;
		oInDev = new Scanner(System.in);
		
		
		while(true) {
			prnStart();
			startSelect = oInDev.nextInt();
			
			if(startSelect == 4) {
				System.out.println("종료합니다...");
				break; //종료
			}
			
			switch(startSelect) {
			
			case 1 : //주문 시작
				Order order = new Order();
				
				while(true) {
					
					prnOrderMenu();
					mSelect = oInDev.nextInt();
					
					if(mSelect == 4) {
						System.out.println("주문이 완료되었습니다.");
						totalSell += prnOrder(menu, order);
						break;
					}
					
					switch(mSelect) {
					
					case 1 : //메뉴 추가
						prnCafeMenu(menu);
						System.out.print("주문할 메뉴를 입력하세요 : ");
						String select = oInDev.next();
						if(menu.getPrice(select)==0) {
							System.out.println("해당 메뉴가 없습니다.");
						}
						else {
							System.out.print("수량을 입력하세요 : ");
							int amount = oInDev.nextInt();
							order.addOrder(select, amount);
						}
						break;
					case 2 : //메뉴 삭제
						System.out.print("삭제할 메뉴를 입력하세요 : ");
						String delete = oInDev.next();
						order.deleteOrder(delete);
						break;
					case 3 : //모두 취소
						deleteAll(order);
						System.out.println("모든 메뉴가 삭제되었습니다. 다시 주문해주세요.");
						break;
					default : 
						System.out.println("입력값이 잘못되었습니다.");
						break;
					}
				}
				break;
				
			case 2 : //정산
				getTotal(totalSell);
				break;
				
			case 3 : //메뉴 수정
				editMenuList();
				editSelect = oInDev.nextInt();
				
				if(editSelect == 4) {
					System.out.println("종료합니다...");
					break; //종료
				}
				
				switch(editSelect) {
				case 1 : //신메뉴 추가
					prnCafeMenu(menu);
					System.out.print("추가할 메뉴의 이름을 입력하세요 : ");
					String sNewMenu = oInDev.next();
					System.out.print("추가할 메뉴의 가격을 입력하세요 : ");
					int iNewPrice = oInDev.nextInt();
					menu.addMenu(sNewMenu, iNewPrice);
					break;
				case 2 : //기존 메뉴 삭제
					prnCafeMenu(menu);
					System.out.print("추가할 메뉴의 이름을 입력하세요 : ");
					String delMenu = oInDev.next();
					menu.deleteMenu(delMenu);
					break;
				case 3 : //기존 메뉴 가격 수정
					prnCafeMenu(menu);
					System.out.print("수정할 메뉴의 이름을 입력하세요 : ");
					sNewMenu = oInDev.next();
					System.out.print("수정할 메뉴의 가격을 입력하세요 : ");
					iNewPrice = oInDev.nextInt();
					menu.addMenu(sNewMenu, iNewPrice);
					break;
				default : 
					System.out.println("입력값이 잘못되었습니다.");
					break;
				}
				break;
				
			default : //해당없음
				System.out.println("입력값이 잘못되었습니다.");
				break;
			}
		}
		
		oInDev.close();
	}
}