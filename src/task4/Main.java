package task4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String fileName = "input.txt";
        int[] nums = readNumbersFromFile(fileName);
        int minMoves = findMinMoves(nums);
        System.out.println("Minimum number of moves: " + minMoves);
    }

    private static int[] readNumbersFromFile(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            int count = 0;
            while (scanner.hasNextInt()) {
                count++;
                scanner.nextInt();
            }
            scanner.close();

            int[] nums = new int[count];
            scanner = new Scanner(file);
            for (int i = 0; i < count; i++) {
                nums[i] = scanner.nextInt();
            }
            scanner.close();

            return nums;
        } catch (FileNotFoundException e) {
            System.out.println("File not found : " + fileName);
            return new int[0];
        }
    }

    private static int findMinMoves(int[] nums) {
        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            sum += num;
            min = Math.min(min, num);
        }
        int target = sum / nums.length;

        return Math.abs(target - min) * nums.length;
    }

}
