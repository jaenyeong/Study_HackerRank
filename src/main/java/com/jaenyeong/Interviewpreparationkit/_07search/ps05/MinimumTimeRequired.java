package com.jaenyeong.Interviewpreparationkit._07search.ps05;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class MinimumTimeRequired {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nGoal = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nGoal[0]);

        long goal = Long.parseLong(nGoal[1]);

        long[] machines = new long[n];

        String[] machinesItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            long machinesItem = Long.parseLong(machinesItems[i]);
            machines[i] = machinesItem;
        }

        long ans = minTime(machines, goal);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

    // Complete the minTime function below.
    static long minTime(long[] machines, long goal) {
        return getMinTimeByBinarySearch(machines, goal);
    }

    private static long getMinTimeByBinarySearch(final long[] machines, final long goal) {
        Arrays.sort(machines);
        long minDay = 1;
        long maxDay = machines[machines.length - 1] * goal;

        while (minDay < maxDay) {
            final long midDay = (minDay + maxDay) >> 1;

            final long numberOfItem = produceItems(machines, midDay);

            if (numberOfItem < goal) {
                minDay = midDay + 1;
                continue;
            }

            maxDay = midDay;
        }

        return minDay;
    }

    private static long produceItems(final long[] machines, final long targetDay) {
        return Arrays.stream(machines)
            .map(produceDay -> targetDay / produceDay)
            .sum();
    }
}
