package week9;
import java.util.Arrays;
import java.util.Scanner;

class ArraySortTester {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("num : ");
        int num = sc.nextInt();
        int[] arr = new int[num];

        for (int i=0; i<num; i++) {
            System.out.print("arr["+i+"] : ");
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        System.out.println("sorted by ascending order");
        for(int i=0; i<num; i++) {
            System.out.println("arr["+i+"]="+arr[i]);
        }

    }
}