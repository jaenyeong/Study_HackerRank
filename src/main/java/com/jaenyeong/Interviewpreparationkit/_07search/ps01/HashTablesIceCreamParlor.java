package com.jaenyeong.Interviewpreparationkit._07search.ps01;

import java.util.HashMap;
import java.util.Scanner;

public class HashTablesIceCreamParlor {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int money = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] cost = new int[n];

            String[] costItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int costItem = Integer.parseInt(costItems[i]);
                cost[i] = costItem;
            }

            whatFlavors(cost, money);
        }

        scanner.close();
    }

    // Complete the whatFlavors function below.
    static void whatFlavors(int[] cost, int money) {
        final HashMap<Integer, Integer> iceCreamCosts = new HashMap<>();

        for (int id = 0; id < cost.length; id++) {
            final int iceCreamCost = cost[id];

            if (iceCreamCost >= money) {
                continue;
            }

            final int difference = money - iceCreamCost;

            if (iceCreamCosts.containsKey(difference)) {
                final int searchId = id + 1;
                final int targetId = iceCreamCosts.get(difference) + 1;

                System.out.println(targetId + " " + searchId);
                return;
            }

            iceCreamCosts.put(iceCreamCost, id);
        }
    }
}
