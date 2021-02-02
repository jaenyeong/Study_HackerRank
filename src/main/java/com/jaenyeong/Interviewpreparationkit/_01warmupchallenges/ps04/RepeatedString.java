package com.jaenyeong.Interviewpreparationkit._01warmupchallenges.ps04;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RepeatedString {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        long n = scanner.nextLong();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
        long result = repeatedString(s, n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

    // Complete the repeatedString function below.
    private static final char TARGET = 'a';

    static long repeatedString(String inputStr, long givenSize) {
        final int strSize = inputStr.length();

        if (strSize > givenSize) {
            return getCount(inputStr, givenSize);
        }

        long targetCount = getCount(inputStr, strSize);

        final long quotient = givenSize / strSize;
        targetCount = (targetCount * quotient);

        final long remainder = givenSize % strSize;
        if (remainder > 0) {
            targetCount += getCount(inputStr, remainder);
        }

        return targetCount;
    }

    private static long getCount(final String inputStr, final long givenSize) {
        long targetCount = 0;
        for (int i = 0; i < givenSize; i++) {
            if (inputStr.charAt(i) == TARGET) {
                targetCount++;
            }
        }

        return targetCount;
    }
}
