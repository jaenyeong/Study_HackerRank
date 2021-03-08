package com.jaenyeong.Interviewpreparationkit._04sorting.ps04;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FraudulentActivityNotifications {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] expenditure = new int[n];

        String[] expenditureItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int expenditureItem = Integer.parseInt(expenditureItems[i]);
            expenditure[i] = expenditureItem;
        }

        int result = activityNotifications(expenditure, d);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

    // Complete the activityNotifications function below.
    static int activityNotifications(int[] expenditure, int d) {
        final int[] elementShowCounts = new int[201];
        for (int i = 0; i < d; i++) {
            elementShowCounts[expenditure[i]]++;
        }

        int alarm = 0;
        for (int i = d; i < expenditure.length; i++) {
            final int midValue = getMedian(elementShowCounts, d);

            if (expenditure[i] >= midValue) {
                alarm++;
            }

            elementShowCounts[expenditure[i - d]]--;
            elementShowCounts[expenditure[i]]++;
        }

        return alarm;
    }

    private static int getMedian(final int[] elementShowCounts, final int d) {
        // left median
        int lower = 0;
        int leftNumber = 0;
        while ((leftNumber + elementShowCounts[lower]) * 2 <= d) {
            leftNumber += elementShowCounts[lower];
            lower++;
        }

        // right median
        int upper = elementShowCounts.length - 1;
        int rightNumber = 0;
        while ((rightNumber + elementShowCounts[upper]) * 2 <= d) {
            rightNumber += elementShowCounts[upper];
            upper--;
        }

        return lower + upper;
    }
}
