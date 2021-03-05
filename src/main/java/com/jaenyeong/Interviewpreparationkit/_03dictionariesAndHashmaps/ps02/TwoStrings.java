package com.jaenyeong.Interviewpreparationkit._03dictionariesAndHashmaps.ps02;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class TwoStrings {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s1 = scanner.nextLine();

            String s2 = scanner.nextLine();

            String result = twoStrings(s1, s2);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }

    // Complete the twoStrings function below.
    static String twoStrings(String s1, String s2) {
        final Set<Character> uniqueCharacters = new HashSet<>();

        for (char character : s1.toCharArray()) {
            uniqueCharacters.add(character);
        }

        for (char character : s2.toCharArray()) {
            if (uniqueCharacters.contains(character)) {
                return "YES";
            }
        }

        return "NO";
    }
}
