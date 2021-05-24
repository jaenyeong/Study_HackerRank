package com.jaenyeong.Interviewpreparationkit._08dynamicprogramming.ps01;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MaxArraySum {
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

        int res = maxSubsetSum(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

    // Complete the maxSubsetSum function below.
    static int maxSubsetSum(int[] arr) {
        int max = 0;

        // 기저조건 설정
        if (arr.length < 2) {
            // 문제 조건에서 단일 원소를 배열로 취급하지 않기 때문에 0을 반환
            return max;
        }

        final int[] dpTable = new int[arr.length + 1];

        // 배열 원소의 값 가운데 음수인 원소를 위해 초깃값 0 삽입
        dpTable[0] = 0;
        dpTable[1] = arr[0];
        dpTable[2] = arr[1];

        for (int i = 3; i <= arr.length; i++) {
            final int prevMax = Math.max(dpTable[i - 2], dpTable[i - 3]);

            final int currentValue = arr[i - 1];

            dpTable[i] = (0 <= currentValue) ? Math.max(currentValue, prevMax + currentValue) : prevMax;

            max = Math.max(max, dpTable[i]);
        }

        return max;
    }
}
