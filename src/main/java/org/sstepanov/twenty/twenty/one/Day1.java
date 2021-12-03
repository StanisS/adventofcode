package org.sstepanov.twenty.twenty.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class Day1 {

    public static final String DATA_FILE = "/Day1.txt";

    public static void main(String[] args) throws IOException {
        System.out.println("1: " + Day1.count1());
        System.out.println("2: " + Day1.count2());
    }

    public static int count1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Day1.class.getResourceAsStream(DATA_FILE))));
        int result = 0;

        String line = br.readLine();
        int tmp = Integer.parseInt(line);
        do {
            int value = Integer.parseInt(line);
            if (value > tmp) {
                result++;
            }
            tmp = value;
        } while ((line = br.readLine()) != null);
        return result;
    }

    public static int count2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Day1.class.getResourceAsStream(DATA_FILE))));
        int result = 0;
        int[] tmp = new int[3];
        int i = 0;
        int sum = 0;

        tmp[0] = Integer.parseInt(br.readLine());
        int v1 = Integer.parseInt(br.readLine());
        tmp[0] += v1;
        tmp[1] = v1;
        String line = br.readLine();

        do {
            int value = Integer.parseInt(line);
            tmp[0] += value;
            tmp[1] += value;
            tmp[2] += value;
            if (sum != 0 && tmp[i] > sum) {
                result++;
            }
            sum = tmp[i];
            tmp[i] = 0;
            if (i < 2) {
                i++;
            } else {
                i = 0;
            }
        } while ((line = br.readLine()) != null);
        return result;
    }
}
