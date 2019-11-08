package week15;

import java.util.Scanner;

class BinTreeTester {
    static Scanner sc = new Scanner(System.in);

    static class Data {
		static final int NO = 1;
		static final int NAME = 2;
		
		private Integer no;
        private String name;
        
        Integer keyCode() {
            return no;
        }
		
		public String toString() {
			return name;
		}
		
		void scanData(String guide, int sw) {
			System.out.println(guide +"할 데이터를 입력하세요 . ");
			
			if ((sw & NO) == NO) {
				System.out.print("번호 : ");
				no = sc.nextInt();
			}
			if ((sw & NAME) == NAME) {
				System.out.print("이름 : ");
				name = sc.next();
			}
		}
	}
		
	enum Menu {
		ADD("삽입"),
		REMOVE("삭제"),
		SEARCH("검색"),
		PRINT(" 출력"),
		TERMINATE("종료");
		
		private final String message;
		
		static Menu MenuAt(int idx) {
			for(Menu m : Menu.values()) {
				if (m.ordinal() == idx) {
					return m;
				}
			}
			return null;
		}
		
		Menu(String string) {
			message = string;
		}
		
		String getMessage() {
			return message;
		}
	}
		
	
	static Menu SelectMenu() {
		int key = 0;
		do {
			for (Menu m : Menu.values()) {
				System.out.printf("(%d) %s ", m.ordinal(), m.getMessage());
			}
            System.out.print(" : ");
			key = sc.nextInt();
		} while (key < Menu.ADD.ordinal() || key > Menu.TERMINATE.ordinal());
		
		return Menu.MenuAt(key);
	}
	
	public static void main(String[] args) {
		Menu menu;
		Data data;
		Data ptr;
		Data temp = new Data();
		
		BinTree<Integer, Data> tree = new BinTree<Integer, Data>();
		
		do {
			switch(menu = SelectMenu()) {
			case ADD:
				data = new Data();
                data.scanData("삽입", Data.NO | Data.NAME);
                tree.add(data.keyCode(), data);
				break;
            case REMOVE:
                temp.scanData("삭제", Data.NO);
                tree.remove(temp.keyCode());
				break;
			case SEARCH:
				temp.scanData("검색", Data.NO);
				ptr = tree.search(temp.keyCode());
				if (ptr != null) {
					System.out.println("그 번호의 이름은 "+ptr+"입니다.");
				} else {
					System.out.println("해당 데이터가 없습니다.");
				}
				break;
            case PRINT: // 모든 노드를 키 값의 오름차순으로 출력
                tree.print();
                break;
            }
		} while (menu != Menu.TERMINATE);
	}


    
}