package com.jaenyeong.Interviewpreparationkit._02arrays.ps01;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TwoDimensionalArrayDS {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int[][] arr = new int[6][6];

        for (int i = 0; i < 6; i++) {
            String[] arrRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 6; j++) {
                int arrItem = Integer.parseInt(arrRowItems[j]);
                arr[i][j] = arrItem;
            }
        }

        int result = hourglassSum(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

    // Complete the hourglassSum function below.
    static int hourglassSum(int[][] arr) {
        int maxSum = Integer.MIN_VALUE;

        for (int startRow = 0; startRow <= 3; startRow++) {
            for (int startCol = 0; startCol <= 3; startCol++) {

                int sum = 0;
                for (int size = 0; size < 3; size++) {
                    sum += arr[startRow][startCol + size];
                    sum += arr[startRow + 2][startCol + size];
                }
                // mid row
                sum += arr[startRow + 1][startCol + 1];

                maxSum = Math.max(maxSum, sum);
            }
        }

        return maxSum;
    }
}
