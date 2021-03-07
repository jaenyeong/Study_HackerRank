package com.jaenyeong.Interviewpreparationkit._04sorting.ps03;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class SortingComparator {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        Player[] player = new Player[n];
        Checker checker = new Checker();

        for (int i = 0; i < n; i++) {
            player[i] = new Player(scan.next(), scan.nextInt());
        }
        scan.close();

        Arrays.sort(player, checker);
        for (final Player value : player) {
            System.out.printf("%s %s\n", value.name, value.score);
        }
    }
}

class Checker implements Comparator<Player> {
    // complete this method
    public int compare(Player a, Player b) {
        if (a.score == b.score) {
            return a.name.compareTo(b.name);
        }

        return a.score < b.score ? 1 : -1;
    }
}

class Player {
    String name;
    int score;

    Player(final String name, final int score) {
        this.name = name;
        this.score = score;
    }
}
