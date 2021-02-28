package com.jaenyeong.Interviewpreparationkit._03DictionariesAndHashmaps.ps03;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SherlockAndAnagrams {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = sherlockAndAnagrams(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }

    // Complete the sherlockAndAnagrams function below.
    static int sherlockAndAnagrams(String s) {
        final Map<String, Integer> anagramsCount = new HashMap<>();

        final int stringSize = s.length();

        for (int startIdx = 0; startIdx < stringSize; startIdx++) {
            for (int endIdx = startIdx + 1; endIdx <= stringSize; endIdx++) {
                final char[] tokenOrderArray = s.substring(startIdx, endIdx).toCharArray();
                Arrays.sort(tokenOrderArray);
                final String token = String.valueOf(tokenOrderArray);

                anagramsCount.put(token, anagramsCount.getOrDefault(token, 0) + 1);
            }
        }

        int result = 0;
        for (Map.Entry<String, Integer> entry : anagramsCount.entrySet()) {
            final int currentPairCount = entry.getValue();
            // 쌍을 구하는 공식
            result += currentPairCount * (currentPairCount - 1) / 2;
        }

        return result;
    }
}
