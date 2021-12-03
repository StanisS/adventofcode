package org.sstepanov.twenty.twenty.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.Math.pow;

public class Day3 {
    public static final String DATA_FILE = "/Day3.txt";

    public static void main(String[] args) throws IOException {
        System.out.println("1: " + diagnostic1());
        System.out.println("2: " + diagnostic2());
    }

    public static int diagnostic1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Day2.class.getResourceAsStream(DATA_FILE))));

        int gammaRate = 0;
        int epsilonRate = 0;
        String line = br.readLine().trim();
        int length = line.length();
        int[] tmp = new int[length];

        do {
            char[] chars = line.trim().toCharArray();
            for (int i = 0; i < chars.length; i++) {
                tmp[i] += chars[i] == '0' ? -1 : 1;
            }
        } while ((line = br.readLine()) != null);

        for (int i = 0; i < tmp.length; i++) {
            int gBit = tmp[i] > 0 ? 1 : 0;
            int eBit = tmp[i] > 0 ? 0 : 1;
            gammaRate += gBit * pow(2, (length - 1 - i));
            epsilonRate += eBit * pow(2, (length - 1 - i));
        }

        return gammaRate * epsilonRate;
    }

    public static int diagnostic2() throws IOException {
        List<String> data = readData();
        return mostCommon(data) * leastCommon(data);
    }

    private static List<String> readData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Day3.class.getResourceAsStream(DATA_FILE))));
        var result = new ArrayList<String>();
        String line;
        while ((line = br.readLine()) != null) {
            result.add(line.trim());
        }
        return result;
    }

    private static int mostCommon(List<String> data) {
        int i = 0;
        List<String> tmp = data;
        while (tmp.size() != 1) {
            List<String>[] split = splitArray(tmp, i++);
            tmp = split[0].size() > split[1].size() ? split[0] : split[1];
        }
        return convert(tmp.get(0));
    }

    private static int leastCommon(List<String> data) {
        int i = 0;
        List<String> tmp = data;
        while (tmp.size() != 1) {
            List<String>[] split = splitArray(tmp, i++);
            tmp = split[0].size() <= split[1].size() ? split[0] : split[1];
        }
        return convert(tmp.get(0));
    }

    private static List<String>[] splitArray(List<String> data, int i) {
        var lists = new List[2];
        lists[0] = new ArrayList<String>();
        lists[1] = new ArrayList<String>();
        data.forEach(value -> {
            if (value.charAt(i) == '0') {
                lists[0].add(value);
            } else {
                lists[1].add(value);
            }
        });
        return lists;
    }

    private static int convert(String s) {
        int result = 0;
        char[] chars = s.toCharArray();
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            result += (chars[i] - 48) * pow(2, (length - 1 - i));
        }
        return result;
    }
}
