package chap03;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class PhysExamSearch {
	
	static class PhyscData {
		private String name;
		private int height;
		private double vision;
		
		public PhyscData(String name, int height, double vision) {
			this.name = name; this.height = height; this.vision = vision;
		}
		
		public String toString() {
			return name+" "+height+" "+vision;
		}
		
		public static final Comparator<PhyscData> HEIGHT_ORDER = new HeightOrderComparator();
	}
	
	private static class HeightOrderComparator implements Comparator<PhyscData> {
		
		@Override
		public int compare(PhyscData o1, PhyscData o2) {
			return (o1.height > o2.height) ? 1 : 
				(o1.height < o2.height) ? -1 : 0;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PhyscData[] x = {
			new PhyscData("someone1", 162, 0.3),
			new PhyscData("someone2", 168, 0.4),
			new PhyscData("someone3", 169, 1.5),
			new PhyscData("someone4", 171, 0.7),
			new PhyscData("someone5", 173, 1.2),
			new PhyscData("someone6", 175, 2.4),
		};
		
		System.out.print("who are you looking for? : ");
		int height = sc.nextInt();
		int idx = Arrays.binarySearch(x, new PhyscData("", height, 0.0), PhyscData.HEIGHT_ORDER);
		
		if (idx < 0) {
			System.out.println("none");
		} else {
			System.out.println("it's in x["+idx+"]");
			System.out.println("data is "+x[idx]);
		}
	}
	
	
}
