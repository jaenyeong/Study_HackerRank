package com.jaenyeong.Interviewpreparationkit._06greedyAlgorithms.ps02;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class LuckBalance {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[][] contests = new int[n][2];

        for (int i = 0; i < n; i++) {
            String[] contestsRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int contestsItem = Integer.parseInt(contestsRowItems[j]);
                contests[i][j] = contestsItem;
            }
        }

        int result = luckBalance(k, contests);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

    // Complete the luckBalance function below.
    static int luckBalance(int k, int[][] contests) {
        final Queue<Integer> contestLuckList = new PriorityQueue<>(Collections.reverseOrder());
        int luckSum = 0;

        for (int[] contest : contests) {
            final int luck = contest[0];
            final int importance = contest[1];

            if (importance == 0) {
                luckSum += luck;
                continue;
            }

            contestLuckList.add(luck);
        }

        final int minusCount = contestLuckList.size() - k;

        while (!contestLuckList.isEmpty()) {
            if (minusCount >= contestLuckList.size()) {
                luckSum -= contestLuckList.poll();
                continue;
            }

            luckSum += contestLuckList.poll();
        }

        return luckSum;
    }
}
