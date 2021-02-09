package com.jaenyeong.Interviewpreparationkit._02arrays.ps03;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class NewYearChaos {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] q = new int[n];

            String[] qItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int qItem = Integer.parseInt(qItems[i]);
                q[i] = qItem;
            }

            minimumBribes(q);
        }

        scanner.close();
    }

    private static final int LIMIT = 2;
    private static final String TOO_CHAOTIC = "Too chaotic";

    // Complete the minimumBribes function below.
    static void minimumBribes(int[] q) {
        int totalSwap = 0;

        for (int i = q.length - 1; i >= 0; i--) {
            final int curValue = q[i];
            final int diff = curValue - (i + 1);

            if (diff > LIMIT) {
                System.out.println(TOO_CHAOTIC);
                return;
            }

            final int checkPoint = Math.max(0, curValue - 2);
            for (int j = checkPoint; j < i; j++) {
                if (q[j] > curValue) {
                    totalSwap++;
                }
            }
        }

        System.out.println(totalSwap);
    }

    static void minimumBribes2(int[] q) {
        int totalSwap = 0;

        final List<Integer> target = Arrays.stream(q)
            .sorted()
            .boxed()
            .collect(Collectors.toCollection(LinkedList::new));

        for (int number : q) {
            final int index = target.indexOf(number);
            if (index > LIMIT) {
                System.out.println(TOO_CHAOTIC);
                return;
            }

            totalSwap += index;
            target.remove(index);
        }

        System.out.println(totalSwap);
    }
}
