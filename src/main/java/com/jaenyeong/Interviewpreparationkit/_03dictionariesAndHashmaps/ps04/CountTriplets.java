package com.jaenyeong.Interviewpreparationkit._03dictionariesAndHashmaps.ps04;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class CountTriplets {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Long::parseLong)
            .collect(toList());

        long ans = countTriplets(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

    // Complete the countTriplets function below.
    static long countTriplets(List<Long> arr, long r) {
        final Map<Long, Long> middleCases = new HashMap<>();
        final Map<Long, Long> lastCases = new HashMap<>();

        long result = 0;

        for (long currentValue : arr) {

            result += lastCases.getOrDefault(currentValue, 0L);

            final long nextValue = currentValue * r;

            if (middleCases.containsKey(currentValue)) {
                lastCases.put(nextValue, lastCases.getOrDefault(nextValue, 0L) + middleCases.get(currentValue));
            }

            middleCases.put(nextValue, middleCases.getOrDefault(nextValue, 0L) + 1);
        }

        return result;
    }
}
