package org.sstepanov.twenty.twenty.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Day4 {
    private static final String DATA_FILE = "/Day4.txt";
    private static final String SPLITTER = " +";
    public static final String REPLACE = "*";

    public static void main(String[] args) throws IOException {
        System.out.println("1: " + part1());
        System.out.println("2: " + part2());
    }

    public static int part1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Day4.class.getResourceAsStream(DATA_FILE))));

        String numbers = br.readLine();
        Map<String, List<int[]>> index = new HashMap<>();
        List<String[][]> bingoBoards = readBoards(br, index);

        int i = numbers.indexOf(',');
        int prev = 0;
        int bingo = -1;
        String number = null;

        while (i != -1 && bingo == -1) {
            number = numbers.substring(prev, i);
            List<int[]> positionsOnBoards = index.get(number);
            bingo = changeAndCheck(bingoBoards, positionsOnBoards);
            prev = i + 1;
            i = numbers.indexOf(',', prev);
        }

        int sum = sunOnBoard(bingoBoards.get(bingo));
        return Integer.parseInt(number) * sum;
    }

    public static int part2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Day4.class.getResourceAsStream(DATA_FILE))));

        String numbers = br.readLine();
        Map<String, List<int[]>> index = new HashMap<>();
        List<String[][]> bingoBoards = readBoards(br, index);

        int i = numbers.indexOf(',');
        int prev = 0;
        List<Integer> bingo = new ArrayList<>();
        String number = null;
        Set<Integer> skipBoard = new HashSet<>();

        while (i != -1 && skipBoard.size() < bingoBoards.size()) {
            number = numbers.substring(prev, i);
            List<int[]> positionsOnBoards = index.get(number);
            bingo = changeAndCheck(bingoBoards, positionsOnBoards, skipBoard);
            prev = i + 1;
            i = numbers.indexOf(',', prev);
        }

        int sum = sunOnBoard(bingoBoards.get(bingo.get(bingo.size()-1)));
        return Integer.parseInt(number) * sum;
    }

    private static int sunOnBoard(String[][] board) {
        int result = 0;
        for (String[] row : board) {
            for (String column : row) {
                if (!column.equals(REPLACE)) {
                    result += Integer.parseInt(column);
                }
            }
        }
        return result;
    }

    private static int changeAndCheck(List<String[][]> bingoBoards, List<int[]> positionsOnBoards) {
        for (int[] position : positionsOnBoards) {
            String[][] board = bingoBoards.get(position[0]);
            board[position[1]][position[2]] = REPLACE;
            if (checkRow(board, position[1]) || checkColumn(board, position[2])) {
                return position[0];
            }
        }
        return -1;
    }

    private static List<Integer> changeAndCheck(List<String[][]> bingoBoards, List<int[]> positionsOnBoards, Set<Integer> skipBoard) {
        List<Integer> bingo = new ArrayList<>();
        for (int[] position : positionsOnBoards) {
            if (!skipBoard.contains(position[0])) {
                String[][] board = bingoBoards.get(position[0]);
                board[position[1]][position[2]] = REPLACE;
                if (checkRow(board, position[1]) || checkColumn(board, position[2])) {
                    skipBoard.add(position[0]);
                    bingo.add(position[0]);
                }
            }
        }
        return bingo;
    }

    private static boolean checkRow(String[][] board, int i) {
        for (String value : board[i]) {
            if (!value.equals(REPLACE)) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkColumn(String[][] board, int j) {
        for (String[] strings : board) {
            if (!strings[j].equals(REPLACE)) {
                return false;
            }
        }
        return true;
    }

    private static List<String[][]> readBoards(BufferedReader br, Map<String, List<int[]>> index) throws IOException {
        List<String[][]> result = new ArrayList<>();
        int n = 0;
        while (br.readLine() != null) {
            result.add(readOneBoard(br, index, n++));
        }
        return result;
    }

    private static String[][] readOneBoard(BufferedReader br, Map<String, List<int[]>> index, int boardNumber) throws IOException {
//    Bingo is played on a set of boards each consisting of a 5x5 grid of numbers.
        String[][] tmp = new String[5][5];
        for (int i = 0; i < 5; i++) {
            tmp[i] = indexing(br.readLine().trim(), index, boardNumber, i);
        }
        return tmp;
    }

    private static String[] indexing(String line, Map<String, List<int[]>> map, int n, int l) {
        String[] values = line.trim().split(SPLITTER);
        for (int i = 0; i < values.length; i++) {
            map.computeIfAbsent(values[i], k -> new ArrayList<>())
                    .add(new int[]{n, l, i});
        }
        return values;
    }
}
