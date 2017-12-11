package ru.bstu.iitus.vt41.bli;

import java.util.ArrayList;

public class Main {

    private static final String FILE_PATH = "src/main/resources/lab_2_v2.txt";

    public static void main(String[] args) {

        ArrayList<Ball> balls = new Parser(FILE_PATH).parse();
        for (Ball anBalls : balls) {
            System.out.println(anBalls);
        }
    }
}