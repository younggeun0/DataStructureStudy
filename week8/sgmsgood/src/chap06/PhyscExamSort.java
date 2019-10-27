package chap06;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class PhyscExamSort {
	static class PhyscData{
		String name;
		int height;
		double vision;
		
		//생성자
		PhyscData(String name, int height, double vision){
			this.name = name;
			this.height = height;
			this.vision = vision;
		}
		
		//신체검사 데이터를 문자열로 반환합니다.
		public String toString() {
			return name + " " + height + " " + vision;
		}
		
		//키 오름차순용 comparator
		static final Comparator<PhyscData> HEIGHT_ORDER = new HeightOrderComparator();
		
		private static class HeightOrderComparator implements Comparator<PhyscData>{
			public int compare(PhyscData d1, PhyscData d2) {
				return (d1.height > d2.height) ? 1:
					(d1.height < d2.height) ? -1 : 0;
			}
		}
		
		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			PhyscData[] x = {
					new PhyscData("이나령", 162, 0.3),
					new PhyscData("전서현", 173, 0.7),
					new PhyscData("이수민", 175, 2.0),
					new PhyscData("홍준기", 171, 1.5),
					new PhyscData("유지훈", 168, 0.4),
					new PhyscData("이호연", 174, 1.2),
					new PhyscData("김한결", 169, 0.8)
			};
			
			Arrays.sort(x, PhyscData.HEIGHT_ORDER);
			
			System.out.println("<< 신체검사 리스트 >>");
			System.out.println("이 름         키         시 력");
			System.out.println("------------------");
			for(int i = 0; i < x.length; i++) {
				System.out.printf("%-8s%3d%5.1f\n", x[i].name, x[i].height, x[i].vision);
			}
			sc.close();
		}
	}
}
