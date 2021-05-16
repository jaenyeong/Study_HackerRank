package com.jaenyeong.Interviewpreparationkit._06greedyalgorithms.ps03;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class GreedyFlorist {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] c = new int[n];

        String[] cItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int cItem = Integer.parseInt(cItems[i]);
            c[i] = cItem;
        }

        int minimumCost = getMinimumCost(k, c);

        bufferedWriter.write(String.valueOf(minimumCost));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

    // Complete the getMinimumCost function below.
    static int getMinimumCost(int k, int[] c) {
        c = Arrays.stream(c)
            .boxed()
            .sorted(Comparator.reverseOrder())
            .mapToInt(i -> i)
            .toArray();

        int minTotalCost = 0;
        int orderOfBuy = 0;
        int turn = 0;

        for (final int flowerCost : c) {
            turn = orderOfBuy / k;
            minTotalCost += (turn + 1) * flowerCost;
            orderOfBuy++;
        }

        return minTotalCost;
    }
}
