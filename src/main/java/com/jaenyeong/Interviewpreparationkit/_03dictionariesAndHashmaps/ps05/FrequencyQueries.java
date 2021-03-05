package com.jaenyeong.Interviewpreparationkit._03dictionariesAndHashmaps.ps05;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class FrequencyQueries {
    private static final int INSERT = 1;
    private static final int REMOVE = 2;
    private static final int CONTAINS = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);

        bufferedWriter.write(
            ans.stream()
                .map(Object::toString)
                .collect(joining("\n"))
                + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }

    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<List<Integer>> queries) {
        final List<Integer> responseList = new ArrayList<>();
        final Map<Integer, Integer> countMap = new HashMap<>();
        int maxCount = 1;

        for (List<Integer> query : queries) {
            final int command = query.get(0);
            final int key = query.get(1);

            switch (command) {
                case INSERT:
                    countMap.put(key, countMap.getOrDefault(key, 0) + 1);

                    final int getRefreshCount = countMap.get(key);
                    if (getRefreshCount > maxCount) {
                        maxCount = getRefreshCount;
                    }
                    break;

                case REMOVE:
                    final int getCount = countMap.getOrDefault(key, 0);
                    countMap.put(key, getCount - 1);

                    if (getCount < 1) {
                        countMap.remove(key);
                    }
                    break;

                case CONTAINS:
                    if (maxCount < key) {
                        responseList.add(0);
                        break;
                    }

                    if (key == 0) {
                        responseList.add(1);
                        break;
                    }

                    responseList.add(countMap.containsValue(key) ? 1 : 0);

                    break;
                default:
                    throw new IllegalArgumentException("Invalid command");
            }
        }

        return responseList;
    }
}
