package org.sstepanov.twenty.twenty.one;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Stream;

public class Day6 {
    private static final String DATA_FILE = "Day6.txt";
    private static final int DAYS_PART1 = 80;
    private static final int DAYS_PART2 = 256;

    public static void main(String[] args) throws Exception {
        System.out.println("1: " + part1());
        System.out.println("2: " + part2());
    }

    public static long part1() throws Exception {
        return count(DAYS_PART1);
    }

    public static long count(int days) throws Exception {
        long[] fish = new long[9];
        getLines().flatMap(line -> Stream.of(line.trim().split(",")))
                .map(Integer::valueOf)
                .forEach(value -> ++fish[value]);

        for (int i = 0; i < days; i++) {
            long newFish = fish[0];
            System.arraycopy(fish, 1, fish, 0, fish.length - 1);
            fish[8] = 0;
            fish[6] += newFish;
            fish[8] += newFish;
        }
        long sum = 0;
        for (long i : fish) {
            sum += i;
        }
        return sum;
    }

    public static long part2() throws Exception {
        return count(DAYS_PART2);
    }

    private static Stream<String> getLines() throws Exception {
        return Files.lines(Path.of(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(DATA_FILE)).toURI()));
    }
}
