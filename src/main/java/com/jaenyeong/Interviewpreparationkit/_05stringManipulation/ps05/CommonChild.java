package com.jaenyeong.Interviewpreparationkit._05stringManipulation.ps05;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CommonChild {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s1 = scanner.nextLine();

        String s2 = scanner.nextLine();

        int result = commonChild(s1, s2);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

    // Complete the commonChild function below.
    static int commonChild(String s1, String s2) {
        final char[] rowCharArray = s1.toCharArray();
        final char[] colCharArray = s2.toCharArray();

        final int rowStringSize = rowCharArray.length;
        final int colStringSize = colCharArray.length;

        final int[][] substringLength = new int[rowStringSize + 1][colStringSize + 1];

        for (int row = 0; row < rowStringSize + 1; row++) {
            for (int col = 0; col < colStringSize + 1; col++) {

                if (row == 0 || col == 0) {
                    continue;
                }

                if (rowCharArray[row - 1] == colCharArray[col - 1]) {
                    substringLength[row][col] = substringLength[row - 1][col - 1] + 1;
                    continue;
                }

                substringLength[row][col] = Math.max(substringLength[row - 1][col], substringLength[row][col - 1]);
            }
        }

        return substringLength[rowStringSize][colStringSize];
    }
}
