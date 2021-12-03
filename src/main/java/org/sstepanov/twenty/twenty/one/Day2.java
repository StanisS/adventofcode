package org.sstepanov.twenty.twenty.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class Day2 {
    public static final String DATA_FILE = "/Day2.txt";

    public static void main(String[] args) throws IOException {
        System.out.println("1: " + Day2.position1());
        System.out.println("2: " + Day2.position2());
    }

    public static int position1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Day2.class.getResourceAsStream(DATA_FILE))));
        int x = 0;
        int y = 0;

        String line;
        while ((line = br.readLine()) != null) {
            String[] commands = line.trim().split(" ");
            int value = Integer.parseInt(commands[1]);
            switch (commands[0]) {
                case "forward" -> x += value;
                case "down" -> y += value;
                case "up" -> y -= value;
            }
        }
        return x * y;
    }

    public static int position2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Day2.class.getResourceAsStream(DATA_FILE))));
        int x = 0;
        int y = 0;
        int aim = 0;

        String line;
        while ((line = br.readLine()) != null) {
            String[] commands = line.trim().split(" ");
            int value = Integer.parseInt(commands[1]);
            switch (commands[0]) {
                case "forward" -> {
                    y += aim * value;
                    x += value;
                }
                case "down" -> aim += value;
                case "up" -> aim -= value;
            }
        }
        return x * y;
    }
}
