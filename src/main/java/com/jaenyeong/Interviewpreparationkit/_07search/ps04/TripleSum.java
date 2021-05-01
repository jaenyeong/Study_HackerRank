package com.jaenyeong.Interviewpreparationkit._07search.ps04;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class TripleSum {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] lenaLenbLenc = scanner.nextLine().split(" ");

        int lena = Integer.parseInt(lenaLenbLenc[0]);

        int lenb = Integer.parseInt(lenaLenbLenc[1]);

        int lenc = Integer.parseInt(lenaLenbLenc[2]);

        int[] arra = new int[lena];

        String[] arraItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lena; i++) {
            int arraItem = Integer.parseInt(arraItems[i]);
            arra[i] = arraItem;
        }

        int[] arrb = new int[lenb];

        String[] arrbItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lenb; i++) {
            int arrbItem = Integer.parseInt(arrbItems[i]);
            arrb[i] = arrbItem;
        }

        int[] arrc = new int[lenc];

        String[] arrcItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lenc; i++) {
            int arrcItem = Integer.parseInt(arrcItems[i]);
            arrc[i] = arrcItem;
        }

        long ans = triplets(arra, arrb, arrc);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

    // Complete the triplets function below.
    static long triplets(int[] a, int[] b, int[] c) {
        // 브루트 포스로 풀어내면 시간 복잡도가 n^3이 될 것
        // a, c 배열의 원소가 b 배열 원소보다 작거나 같은 경우의 수를 구해서 곱을 통해 전체 경우의 수를 구함

        a = parseAndOrder(a);
        b = parseAndOrder(b);
        c = parseAndOrder(c);

        long result = 0;

        int aIdx = 0;
        int bIdx = 0;
        int cIdx = 0;

        while (bIdx < b.length) {
            while (aIdx < a.length && a[aIdx] <= b[bIdx]) {
                aIdx++;
            }
            while (cIdx < c.length && c[cIdx] <= b[bIdx]) {
                cIdx++;
            }

            result += aIdx * (long) cIdx;
            bIdx++;
        }

        return result;
    }

    private static int[] parseAndOrder(final int[] arr) {
        return Arrays.stream(arr)
            .distinct()
            .sorted()
            .toArray();
    }
}
