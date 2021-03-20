package com.jaenyeong.Interviewpreparationkit._05stringManipulation.ps04;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpecialStringAgain {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        long result = substrCount(n, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

    // Complete the substrCount function below.
    static long substrCount(int n, String s) {
        final char[] alphabets = s.toCharArray();

        final List<Frequency> frequencies = new ArrayList<>();

        char prevLetter = alphabets[0];
        int count = 1;

        // initialize alphabets frequency
        for (int i = 1; i < n; i++) {
            if (alphabets[i] == prevLetter) {
                count++;
            } else {
                frequencies.add(new Frequency(prevLetter, count));
                count = 1;
            }
            prevLetter = alphabets[i];
        }
        frequencies.add(new Frequency(prevLetter, count));

        int resultSubstring = 0;

        // counting alphabets
        for (Frequency frequency : frequencies) {
            final int frequencyCount = frequency.count;
            resultSubstring += (frequencyCount * (frequencyCount + 1)) / 2;
        }

        // counting palindrom
        for (int i = 1; i < frequencies.size() - 1; i++) {
            final Frequency prevFrequency = frequencies.get(i - 1);
            final Frequency nextFrequency = frequencies.get(i + 1);
            final int currentFrequencyCount = frequencies.get(i).count;

            if (prevFrequency.letter == nextFrequency.letter && currentFrequencyCount == 1) {
                resultSubstring += Math.min(prevFrequency.count, nextFrequency.count);
            }
        }

        return resultSubstring;
    }

    static class Frequency {
        final char letter;
        final int count;

        Frequency(final char letter, final int count) {
            this.letter = letter;
            this.count = count;
        }
    }
}
