package com.jaenyeong.Interviewpreparationkit._07search.ps07;

import java.io.*;

public class MakingCandies {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        long m = Long.parseLong(firstMultipleInput[0]);

        long w = Long.parseLong(firstMultipleInput[1]);

        long p = Long.parseLong(firstMultipleInput[2]);

        long n = Long.parseLong(firstMultipleInput[3]);

        long result = minimumPasses(m, w, p, n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

    /*
     * Complete the 'minimumPasses' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. LONG_INTEGER m
     *  2. LONG_INTEGER w
     *  3. LONG_INTEGER p
     *  4. LONG_INTEGER n
     */

    public static long minimumPasses(long machines, long workers, long upgradeCost, long goalCandies) {
        // Write your code here

        // 최초에 주어진 기계, 작업자 수로 목표 사탕 수를 만들 수 있는지 확인
        if ((goalCandies / machines) <= workers) {
            return 1;
        }

        long minPasses = Long.MAX_VALUE;
        long currentPasses = 0;
        long leftoverCandies = 0;

        while (true) {
            final long canBeMadeCandies = machines * workers;

            final long remainPasses = getNumberOfPasses(goalCandies - leftoverCandies, canBeMadeCandies);
            minPasses = Math.min(minPasses, currentPasses + remainPasses);

            // 원하는 사탕 수를 만들 수 있다면 최소 통과 케이스를 반환
            if (remainPasses == 1) {
                return minPasses;
            }

            // 남은 사탕의 수가 업그레이드(증가) 비용보다 적은 경우 추가 통과 케이스 연산
            if (leftoverCandies < upgradeCost) {
                final long extraPasses = getNumberOfPasses(upgradeCost - leftoverCandies, canBeMadeCandies);

                currentPasses += extraPasses;
                leftoverCandies += (extraPasses * canBeMadeCandies);

                // 원하는 사탕 수를 만들수 있다면 최소 케이스와 현재 케이스 중 작은 값을 반환
                if (leftoverCandies >= goalCandies) {
                    return Math.min(minPasses, currentPasses);
                }
            }

            // 사탕을 사용(소비)하여 기계 또는 작업자 수 증가
            leftoverCandies -= upgradeCost;

            // 곱셈은 더 적은 쪽을 증가 시켜야 결괏값이 커지기 때문에 기계와 작업자 중 더 적은 쪽을 증가
            if (machines <= workers) {
                machines++;
                continue;
            }

            workers++;
        }
    }

    private static long getNumberOfPasses(final long targetCandies, final long canBeMadeCandies) {
        final int remainder = (targetCandies % canBeMadeCandies == 0) ? 0 : 1;

        return (targetCandies / canBeMadeCandies) + remainder;
    }
}
