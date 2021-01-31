package com.jaenyeong.Interviewpreparationkit._01warmupchallenges.ps03;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class JumpingOnTheClouds {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] c = new int[n];

        String[] cItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int cItem = Integer.parseInt(cItems[i]);
            c[i] = cItem;
        }

        int result = jumpingOnClouds(c);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

    private static final int SAFE_CLOUD = 0;

    // Complete the jumpingOnClouds function below.
    static int jumpingOnClouds(final int[] clouds) {
        final int cloudLastIdx = clouds.length - 1;
        int curIdx = 0;
        int jumpCount = 0;

        while (curIdx < cloudLastIdx - 2) {
            curIdx += clouds[curIdx + 2] == SAFE_CLOUD ? 2 : 1;
            jumpCount++;
        }

        if (curIdx == cloudLastIdx - 2) {
            return jumpCount + 1;
        }

        if (clouds[cloudLastIdx] == SAFE_CLOUD) {
            return jumpCount + 1;
        }

        return jumpCount;
    }
}
