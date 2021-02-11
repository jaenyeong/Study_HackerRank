package com.jaenyeong.Interviewpreparationkit._02arrays.ps04;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MinimumSwaps2 {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = minimumSwaps(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

    // Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {
        final int limit = arr.length - 1;

        int swapCount = 0;

        for (int curIdx = 0; curIdx < limit; curIdx++) {
            final int expectedValue = curIdx + 1;
            if (arr[curIdx] == expectedValue) {
                continue;
            }

            for (int nextIdx = curIdx + 1; nextIdx < arr.length; nextIdx++) {
                if (arr[nextIdx] != expectedValue) {
                    continue;
                }

                int swap = arr[curIdx];
                arr[curIdx] = arr[nextIdx];
                arr[nextIdx] = swap;
                swapCount++;
                break;
            }
        }

        return swapCount;
    }
}
