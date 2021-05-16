package com.jaenyeong.Interviewpreparationkit._06greedyalgorithms.ps04;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class MaxMin {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int k = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            int arrItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            arr[i] = arrItem;
        }

        int result = maxMin(k, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

    // Complete the maxMin function below.
    static int maxMin(int k, int[] arr) {
        Arrays.sort(arr);

        int resultMin = Integer.MAX_VALUE;

        for (int startIdx = 0; startIdx <= arr.length - k; startIdx++) {
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;

            for (int i = 0; i < k; i++) {
                max = Math.max(max, arr[startIdx + i]);
                min = Math.min(min, arr[startIdx + i]);
            }

            resultMin = Math.min(resultMin, max - min);
        }

        return resultMin;
    }
}
