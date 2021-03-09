package com.jaenyeong.Interviewpreparationkit._04sorting.ps05;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class MergeSortCountingInversions {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] arr = new int[n];

            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }

            long result = countInversions(arr);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }

    // Complete the countInversions function below.
    static long countInversions(int[] arr) {
        final int arrSize = arr.length;

        if (arrSize <= 1) {
            return 0;
        }

        final int midIdx = arrSize >> 1;

        final int[] leftArray = Arrays.copyOfRange(arr, 0, midIdx);
        final int[] rightArray = Arrays.copyOfRange(arr, midIdx, arrSize);

        long countInversions = countInversions(leftArray) + countInversions(rightArray);

        final int arrRange = arrSize - midIdx;
        int leftArrIdx = 0;
        int rightArrIdx = 0;

        for (int i = 0; i < arrSize; i++) {
            if (leftArrIdx < midIdx && (arrRange <= rightArrIdx || leftArray[leftArrIdx] <= rightArray[rightArrIdx])) {
                arr[i] = leftArray[leftArrIdx++];
                countInversions += rightArrIdx;
                continue;
            }

            if (rightArrIdx < arrRange) {
                arr[i] = rightArray[rightArrIdx++];
            }
        }

        return countInversions;
    }
}
