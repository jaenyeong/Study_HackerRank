package com.jaenyeong.Interviewpreparationkit._06greedyalgorithms.ps05;

import java.io.*;
import java.util.Arrays;
import java.util.Stack;

public class ReverseShuffleMerge {

    private static final char ALPHABET_A = 'a';

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = reverseShuffleMerge(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

    /*
     * Complete the 'reverseShuffleMerge' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String reverseShuffleMerge(String s) {
        // Write your code here

        // 결과 문자열에 추가 가능한 문자별 수를 보관하는 배열
        final int[] canBeAddLetterCount = new int[26];
        final char[] charArray = s.toCharArray();

        // 존재하는 알파벳 전체 개수 세기
        for (final char value : charArray) {
            canBeAddLetterCount[value - ALPHABET_A]++;
        }

        // merge(S) 조건 때문에 알파벳 개수를 절반으로 나누기
        for (int i = 0; i < canBeAddLetterCount.length; i++) {
            canBeAddLetterCount[i] /= 2;
        }

        // 결과 문자열에 추가하지 않고 생략 가능한 문자별 수를 보관하는 배열
        final int[] canBeSkipLetterCount = Arrays.copyOf(canBeAddLetterCount, canBeAddLetterCount.length);

        // 결과 문자열에 삽입 가능한지 확인을 위해 사용할 스택 선언
        final Stack<Character> charStack = new Stack<>();

        // reverse(S) 조건 때문에 S 문자열 마지막 문자부터 순회
        for (int i = charArray.length - 1; i >= 0; i--) {

            // 현재 문자
            final char currentLetter = charArray[i];
            // 배열에 담긴 현재 문자의 인덱스
            final int curLetterIdx = currentLetter - ALPHABET_A;

            // [1] 스택이 비어있지 않을 때
            // [2] 스택의 마지막 글자가 현재 글자보다 크고
            // [3] 현재 문자가 결과 문자열의 추가가 가능한 문자이면서
            // [4] 스택 최상단 문자가 생략 가능한 문자이면
            // 반복하면서 처리
            while (!charStack.empty()
                && charStack.peek() > currentLetter
                && canBeAddLetterCount[curLetterIdx] > 0
                && canBeSkipLetterCount[charStack.peek() - ALPHABET_A] > 0) {

                final int topLetterIdx = charStack.pop() - ALPHABET_A;

                // 스택에 있던 문자를 다시 결과 문자열의 추가할 수 있게 횟수 증가
                canBeAddLetterCount[topLetterIdx]++;
                // 스택에 있던 문자의 결과 문자열의 삽입하지 않을 수 있는 횟수 감소
                canBeSkipLetterCount[topLetterIdx]--;
            }

            // 현재 문자를 결과 문자열의 추가할 수 있으면
            if (canBeAddLetterCount[curLetterIdx] > 0) {
                // 스택에 삽입, 추가 가능 횟수 감소
                charStack.push(currentLetter);
                canBeAddLetterCount[curLetterIdx]--;
            } else {
                // 결과 문자열의 삽입하지 않을 수 있는 횟수 감소
                canBeSkipLetterCount[curLetterIdx]--;
            }
        }

        return generateResultString(charStack);
    }

    private static String generateResultString(final Stack<Character> charStack) {
        String result = "";

        // 스택이 빌 때까지 문자를 빼서 결과 문자열에 더하기
        while (!charStack.empty()) {
            result = String.format("%s%s", charStack.pop(), result);
        }

        return result;
    }
}
