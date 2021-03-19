package com.jaenyeong.Interviewpreparationkit._05stringManipulation.ps03;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SherlockAndTheValidString {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }

    // Complete the isValid function below.
    static String isValid(String s) {

        // collecting for alphabets frequency
        final Map<Long, Long> alphabetsFrequency = s.chars()
            .mapToObj(alphabet -> (char) alphabet)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .values()
            .stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        if (alphabetsFrequency.size() == 1) {
            return "YES";
        }

        if (alphabetsFrequency.size() > 2) {
            return "NO";
        }

        // when alphabetsFrequency size is 2

        final List<Long> frequencies = new ArrayList<>(alphabetsFrequency.keySet());

        final long firstFrequency = frequencies.get(0);
        final long secondFrequency = frequencies.get(1);

        if (firstFrequency == 1 && alphabetsFrequency.get(firstFrequency) == 1) {
            return "YES";
        }

        if (secondFrequency == 1 && alphabetsFrequency.get(secondFrequency) == 1) {
            return "YES";
        }

        if (Math.abs(firstFrequency - secondFrequency) > 1) {
            return "NO";
        }

        if (alphabetsFrequency.get(firstFrequency) > 1 && alphabetsFrequency.get(secondFrequency) > 1) {
            return "NO";
        }

        return "YES";
    }
}
