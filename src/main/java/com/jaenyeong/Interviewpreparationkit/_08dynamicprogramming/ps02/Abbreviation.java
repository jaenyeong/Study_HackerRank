package com.jaenyeong.Interviewpreparationkit._08dynamicprogramming.ps02;

import java.io.*;
import java.util.stream.IntStream;

public class Abbreviation {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String a = bufferedReader.readLine();

                String b = bufferedReader.readLine();

                String result = abbreviation(a, b);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }

    /*
     * Complete the 'abbreviation' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING a
     *  2. STRING b
     */

    private static final String NO = "NO";
    private static final String YES = "YES";

    public static String abbreviation(String a, String b) {
        // Write your code here
        final int givenStringSize = a.length();
        final int targetSize = b.length();

        // 변경할 문자열 길이가 목표 문자열 길이보다 짧다면 당연히 완성할 수 없음
        if (givenStringSize < targetSize) {
            return NO;
        }

        // 문자열 변환 시 일치 여부를 캐싱할 DP 테이블 초기화
        final boolean[][] dpTable = new boolean[givenStringSize + 1][targetSize + 1];
        dpTable[0][0] = true;

        for (int givenArrIdx = 1; givenArrIdx < givenStringSize + 1; givenArrIdx++) {
            for (int targetArrIdx = 0; targetArrIdx < targetSize + 1; targetArrIdx++) {

                // 직전 문자열까지 변환 시 일치하면서 해당 위치 문자가 같은 경우
                if (targetArrIdx > 0
                    && dpTable[givenArrIdx - 1][targetArrIdx - 1]
                    && isEqual(a.charAt(givenArrIdx - 1), b.charAt(targetArrIdx - 1))) {

                    dpTable[givenArrIdx][targetArrIdx] = true;
                }

                // 직전 문자열까지 변환 시 일치하면서 다음 문자가 소문자인 경우
                // 소문자인 경우에만 대문자 변환 또는 삭제 가능
                if (dpTable[givenArrIdx - 1][targetArrIdx] && Character.isLowerCase(a.charAt(givenArrIdx - 1))) {
                    dpTable[givenArrIdx][targetArrIdx] = true;
                }
            }
        }

        return dpTable[givenStringSize][targetSize] ? YES : NO;
    }

    private static boolean isEqual(final char givenLetter, final char targetLetter) {
        return givenLetter == targetLetter || Character.toUpperCase(givenLetter) == targetLetter;
    }
}
