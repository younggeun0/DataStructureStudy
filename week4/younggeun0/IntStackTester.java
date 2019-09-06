package chap04;

import java.util.Scanner;

public class IntStackTester {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		IntStack s = new IntStack(64);
		
		while(true) {
			System.out.println("현재 데이터 수 : "+s.size()+"/"+s.capacity());
			System.out.println("(1)push (2)pop (3)peek (4)dump (0)exit");
			int menu = sc.nextInt();
			if (menu == 0) break;
			
			int x;
			switch(menu) {
			case 1:
				System.out.print("data : ");
				x = sc.nextInt();
				try {
					s.push(x);
				} catch (IntStack.OverflowIntStackException e) {
					System.out.println("StackOverflow");
				}
				break;
			case 2:
				try {
					x = s.pop();
					System.out.println("poped data is "+x);
				} catch (IntStack.EmptyIntStackException e) {
					System.out.println("Stack is empty");
				}
				break;
			case 3:
				try {
					x = s.peek();
					System.out.println("peeked data is "+x);
				} catch (IntStack.EmptyIntStackException e) {
					System.out.println("Stack is empty");
				}
				break;
			case 4:
				s.dump();
				break;
			}
		}
	}

}
