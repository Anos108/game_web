package org.example.demo2;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

public class ScoreManager {

    private static final Path SCORE_FILE = Paths.get( "data", "score.txt");

    private static void ensureFile() throws IOException {
        if (!Files.exists(SCORE_FILE.getParent())) {
            Files.createDirectories(SCORE_FILE.getParent());
            System.out.println("loi 1");
        }
        if (!Files.exists(SCORE_FILE)) {
            Files.write(SCORE_FILE, List.of("0", "0", "0"), StandardCharsets.UTF_8);
            System.out.println("loi 2");
        }
    }

    private static String readLine(int index) {
        try {
            ensureFile();
            List<String> lines = Files.readAllLines(SCORE_FILE, StandardCharsets.UTF_8);
            if (index < lines.size()) return lines.get(index).trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0"; // fallback nếu lỗi
    }
    private static void writeScoreAt(int index, int score) {
        try {
            ensureFile();
            List<String> lines = Files.readAllLines(SCORE_FILE, StandardCharsets.UTF_8);

            // Nếu file ít dòng → thêm đủ
            while (lines.size() < 3) lines.add("0");

            lines.set(index, Integer.toString(score));
            Files.write(SCORE_FILE, lines, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
        }
    }

    public static int readEasy()    { return Integer.parseInt(readLine(0)); }
    public static int readMedium()  { return Integer.parseInt(readLine(1)); }
    public static int readHard()    { return Integer.parseInt(readLine(2)); }

    public static void writeEasy(int newScore)   { writeScoreAt(0, newScore); }
    public static void writeMedium(int newScore) { writeScoreAt(1, newScore); }
    public static void writeHard(int newScore)   { writeScoreAt(2, newScore); }



}
