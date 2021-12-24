package org.sstepanov.twenty.twenty.one;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class Day7 {
    private static final String DATA_FILE = "Day7.txt";

    public static void main(String[] args) throws Exception {
        System.out.println("1: " + part1());
        System.out.println("2: " + part2());
    }

    public static long part1() throws Exception {
        Map<Integer, Integer> count = new TreeMap<>();
        var aRightValue = new AtomicLong();
        var aRightNumber = new AtomicInteger();
        getLines().flatMap(line -> Stream.of(line.trim().split(",")))
                .map(Integer::valueOf)
                .forEach(value -> count.compute(value, (k, v) -> {
                    aRightValue.addAndGet(k);
                    aRightNumber.incrementAndGet();
                    if (v == null) {
                        return 1;
                    }
                    return ++v;
                }));

        long right = aRightValue.get();
        int rightNumber = aRightNumber.get();
        long left = 0;
        int leftNumber = 0;
        int previous = 0;
        long sum = right;

        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            Integer position = entry.getKey();
            Integer number = entry.getValue();
            int delta = (position - previous);
            previous = position;
            left += delta * leftNumber;
            leftNumber += number;
            right -= delta * rightNumber;
            rightNumber -= number;
//            System.out.printf("position = %d; number = %d; sum = %d; left = %d; right = %d%n", position, number, left + right, left, right);
            if ((right + left) > sum) {
                return sum;
            }
            sum = right + left;
        }
        return sum;
    }

    public static int part2() throws Exception {
        Map<Integer, Integer> count = new TreeMap<>();
        var aSum = new AtomicLong();
        var aNumber = new AtomicInteger();
        getLines().flatMap(line -> Stream.of(line.trim().split(",")))
                .map(Integer::valueOf)
                .forEach(value -> count.compute(value, (k, v) -> {
                    aSum.addAndGet(k);
                    aNumber.incrementAndGet();
                    if (v == null) {
                        return 1;
                    }
                    return ++v;
                }));

//        TODO
        int ceil = (int) Math.ceil(aSum.get() / (float) aNumber.get());
        int floor = (int) Math.floor(aSum.get() / (float) aNumber.get());

        int resultCeil = getResult(count, ceil);
        int resultFloor = getResult(count, floor);

        return Math.min(resultCeil, resultFloor);
    }

    private static int getResult(Map<Integer, Integer> count, int round) {
        int result = 0;
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            Integer value = entry.getKey();
            Integer number = entry.getValue();
            int sum = 0;
            for (int i = 1; i < Math.abs(round - value) + 1; i++) {
                sum += i;
            }
            result += sum * number;
        }
        return result;
    }

    private static Stream<String> getLines() throws Exception {
        return Files.lines(Path.of(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(DATA_FILE)).toURI()));
    }
}
