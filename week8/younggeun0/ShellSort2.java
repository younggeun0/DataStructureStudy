package week8;

import java.util.Scanner;

class ShellSort2 {
    static void shellSort(int[] a, int n) {
        int h;
        // 증분값(h) 선택, h가 서로 배수가 되면 요소가 충분히 섞여 효율적인 정렬을 기대할 수 없음
        // 이를 위해 h를 3배한 값에 1을 더한 수열로 사용, 
        // 그리고 h 초깃값이 너무 크면 효과가 없어 배열의 요솟수를 9로 나눈 값을 넘지 않도록 함
        for(h = 1; h<n/9; h=h*3+1);

        for (; h > 0; h/=3) {
            for (int i=h; i<n; i++) {
                int j;
                int tmp = a[i];
                for(j = i-h; j>=0 && a[j] > tmp; j-= h) {
                    a[j+h] = a[j];
                }
                a[j+h] = tmp;
            }
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("shell sort");
        System.out.print("element num : ");
        int n = sc.nextInt();
        int[] a = new int[n];

        for(int i=0; i<n; i++) {
            System.out.print("a["+i+"] : ");
            a[i] = sc.nextInt();
        }

        shellSort(a, n);

        System.out.println("ascending sort");
        for(int i=0; i<n; i++) {
            System.out.println("a["+i+"] = "+a[i]);
        }
    }
}