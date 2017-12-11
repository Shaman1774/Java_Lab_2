package ru.bstu.iitus.vt41.bli;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private BufferedReader reader;

    public Parser(String filePath) {
        try {
            reader = Files.newBufferedReader(Paths.get(filePath));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ArrayList<Ball> parse() {

        Pattern patternType = Pattern.compile(".*\"value\":\"([^<]*)\".*");
        Pattern patternMaterial = Pattern.compile(".*\"material\":\"([^<]*)\".*");
        Pattern patternWeight = Pattern.compile(".*\"weight\":\"(\\S+)\".*");

        ArrayList<Ball> balls = new ArrayList<>();
        Ball ball = new Ball();

        try {
            while (reader.ready()) {
                String buf = reader.readLine();
                Matcher matcherType = patternType.matcher(buf);
                Matcher matcherMaterial = patternMaterial.matcher(buf);
                Matcher matcherWeight = patternWeight.matcher(buf);
                if (matcherType.matches()) {
                    ball.setType(matcherType.group(1));
                }
                if (matcherMaterial.matches()) {
                    ball.setMaterial(matcherMaterial.group(1));
                }
                if (matcherWeight.matches()) {
                    ball.setWeight(Float.parseFloat(matcherWeight.group(1).replace(',', '.')));
                }
                if (ball.getType() != null && ball.getMaterial() != null && ball.getWeight() != 0) {
                    balls.add(ball);
                    ball = new Ball();
                }
            }
            reader.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return balls;
    }
}
