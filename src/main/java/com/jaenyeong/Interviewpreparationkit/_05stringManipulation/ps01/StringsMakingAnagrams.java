package com.jaenyeong.Interviewpreparationkit._05stringManipulation.ps01;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class StringsMakingAnagrams {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String a = scanner.nextLine();

        String b = scanner.nextLine();

        int res = makeAnagram(a, b);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

    // Complete the makeAnagram function below.
    static int makeAnagram(String a, String b) {
        final int[] alphabets = new int[26];

        for (char alphabet : a.toCharArray()) {
            alphabets[alphabet - 'a']++;
        }

        for (char alphabet : b.toCharArray()) {
            alphabets[alphabet - 'a']--;
        }

        int commonAlphabetCount = 0;
        for (int count : alphabets) {
            commonAlphabetCount += Math.abs(count);
        }

        return commonAlphabetCount;
    }
}
