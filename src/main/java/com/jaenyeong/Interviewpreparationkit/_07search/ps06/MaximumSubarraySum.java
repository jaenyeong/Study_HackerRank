package com.jaenyeong.Interviewpreparationkit._07search.ps06;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class MaximumSubarraySum {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                long m = Long.parseLong(firstMultipleInput[1]);

                List<Long> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Long::parseLong)
                    .collect(toList());

                long result = maximumSum(a, m);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }

    /*
     * Complete the 'maximumSum' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. LONG_INTEGER_ARRAY a
     *  2. LONG_INTEGER m
     */

    public static long maximumSum(List<Long> array, long divisor) {
        // Write your code here

        // 배열의 모든 원소는 양수(10경 이하), 배열 크기는 10만 이하, 나머지 값은 100조 이하
        long currentRemainderSum = 0;
        long resultMaxRemainderSum = 0;

        final TreeSet<Long> remainderSumSet = new TreeSet<>();

        for (long element : array) {
            currentRemainderSum = (currentRemainderSum + element) % divisor;

            // 나머지 합을 모아둔 집합에서 현재 (합 + 1) 보다 큰 값을 찾음
            final Long prefixMaxRemainderSum = remainderSumSet.ceiling(currentRemainderSum + 1);

            if (prefixMaxRemainderSum != null) {
                // 기존 합이 현재 합 보다 크다면 음수의 값이 나오기 때문에 제수 값을 더해줌
                resultMaxRemainderSum = Math.max(resultMaxRemainderSum, currentRemainderSum - prefixMaxRemainderSum + divisor);
            }

            resultMaxRemainderSum = Math.max(resultMaxRemainderSum, currentRemainderSum);

            remainderSumSet.add(currentRemainderSum);
        }

        return resultMaxRemainderSum;
    }
}
