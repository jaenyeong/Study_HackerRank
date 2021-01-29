package com.jaenyeong.Interviewpreparationkit._01warmupchallenges.ps02;

import java.io.*;

public class CountingValleys {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int steps = Integer.parseInt(bufferedReader.readLine().trim());

        String path = bufferedReader.readLine();

        int result = Result.countingValleys(steps, path);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

class Result {
    /*
     * Complete the 'countingValleys' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER steps
     *  2. STRING path
     */
    private static final char DOWN = 'D';

    public static int countingValleys(int steps, String path) {
        // Write your code here
        int count = 0;
        int seaLevel = 0;

        for (char movePath : path.toCharArray()) {
            if (movePath == DOWN) {
                if (seaLevel == 0) {
                    count++;
                }

                seaLevel--;
                continue;
            }

            seaLevel++;
        }

        return count;
    }
}
