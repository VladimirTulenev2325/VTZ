package task1;

import java.util.Arrays;
import java.util.Scanner;

public class CircularArrayPath {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of array elements: ");
        int n = sc.nextInt();
        System.out.print("Enter the length of the walk: ");
        int m = sc.nextInt();

        int[] arr = new int[n];
        Arrays.setAll(arr, i -> ++i);

        int current = 0;
        System.out.print("Received path: ");
        do {
            System.out.print(arr[current]);
            current = (current + m - 1) % n;
        } while (current != 0);
    }
}
