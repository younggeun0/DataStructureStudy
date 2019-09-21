package chap02;

import java.util.Scanner;

public class PhysicalExamination {
	static final int VMAX = 21;

	static class PhyscData {
		String name;
		int height;
		double vision;

		PhyscData(String name, int height, double vision) {
			this.name = name;
			this.height = height;
			this.vision = vision;
		}
	}

	static double aveHeight(PhyscData[] dat) {
		double sum = 0;

		for (int i = 0; i < dat.length; i++) {
			sum += dat[i].height;
		}
		return sum / dat.length;
	}

	static void distVision(PhyscData[] dat, int[] dist) {
		int i = 0;

		dist[i] = 0;

		for (i = 0; i < dat.length; i++) {
			if (dat[i].vision >= 0.0 && dat[i].vision <= VMAX / 10.0) {
				dist[(int) (dat[i].vision * 10)]++;
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		PhyscData[] x = {
			new PhyscData ("諛뺥쁽洹�", 162, 0.3), 
			new PhyscData ("�븿吏꾩븘", 173, 0.7), 
			new PhyscData ("理쒖쑄誘�", 175, 2.0), 
			new PhyscData ("�솉�뿰�쓽", 171, 1.5), 
			new PhyscData ("�씠�닔吏�", 168, 0.4), 
			new PhyscData ("源��쁺以�", 174, 1.2), 
			new PhyscData ("諛뺤슜洹�", 169, 0.8)
		};
		
		int[] vdist  = new int[VMAX];
		
		System.out.println("** �떊泥닿��궗 由ъ뒪�듃 **");
		System.out.println("�씠由�            �궎      �떆�젰");
		System.out.println("-----------------------------");
		for(int i = 0; i < x.length; i++) {
			System.out.printf("%-8s%3d%5.1f\n", x[i].name, x[i].height, x[i].vision);
		}
		System.out.printf("\n�룊洹� �궎: %5.1fcm\n", aveHeight(x));
		
		distVision(x, vdist);
		
		System.out.println("\n �떆�젰 遺꾪룷");
		for(int i = 0; i < VMAX; i++) {
			System.out.printf("%3.1f~: %2d紐�\n", i / 10.0, vdist[i]);
		}
	}
}
