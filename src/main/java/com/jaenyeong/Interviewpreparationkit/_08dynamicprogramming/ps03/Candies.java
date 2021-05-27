package com.jaenyeong.Interviewpreparationkit._08dynamicprogramming.ps03;

import java.io.*;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Candies {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = IntStream.range(0, n).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        long result = candies(n, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

    /*
     * Complete the 'candies' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER_ARRAY arr
     */

    public static long candies(int n, List<Integer> arr) {
        // Write your code here
        long sum = 0;

        final int[] dpAsc = new int[n];
        dpAsc[0] = 1;
        final int[] dpDesc = new int[n];
        dpDesc[n - 1] = 1;

        // 앞에서부터 순차적으로 비교
        for (int i = 1; i < n; i++) {
            final int prev = arr.get(i - 1);
            final int current = arr.get(i);

            dpAsc[i] = (prev < current) ? (dpAsc[i - 1] + 1) : 1;
        }

        // 뒤에서부터 순차적으로 비교
        for (int i = n - 2; i >= 0; i--) {
            final int next = arr.get(i + 1);
            final int current = arr.get(i);

            dpDesc[i] = (next < current) ? (dpDesc[i + 1] + 1) : 1;
        }

        // 결과에서 최댓값 합산
        for (int i = 0; i < n; i++) {
            sum += Math.max(dpAsc[i], dpDesc[i]);
        }

        return sum;
    }
}
