package chap04;

import java.util.Scanner;

public class IntQueueTester {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		IntArrQueue s = new IntArrQueue(64);
		
		while(true) {
			System.out.println("현재 데이터 수 : "+s.size()+"/"+s.capacity());
			System.out.print("(1)enque (2)deque (3)peek (4)dump (0)exit");
			
			int menu = sc.nextInt();
			if (menu == 0) break;
			
			int x;
			
			switch(menu) {
			case 1:
				System.out.print("data : ");
				x = sc.nextInt();
				try {
					s.enque(x);
				} catch (IntArrQueue.OverflowIntQueueException e) {
					System.out.println("queue is full");
				}
				break;
			case 2:
				try {
					x = s.deque();
					System.out.println("dequed data is "+x);
				} catch (IntArrQueue.EmptyIntQueueException e) {
					System.out.println("queue is empty");
				}
				break;
			case 3:
				try {
					x = s.peek();
					System.out.println("peeked data is "+x);
				} catch (IntArrQueue.EmptyIntQueueException e) {
					System.out.println("queue is empty");
				}
				break;
			case 4:
				s.dump();
				break;
			}
		}
	}

}
