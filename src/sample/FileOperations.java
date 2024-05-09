package sample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class FileOperations {
    private static final String INPUT_FILE_NAME = "players.txt";
    private static final String OUTPUT_FILE_NAME = "players.txt";

    public static List<Player> readFromFile() throws Exception {
        List<Player> playerList = new ArrayList();
        // var studentList = new ArrayList();
        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        // var br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String[] tokens = line.split(",");
            Player s = new Player();
            s.setName(tokens[0]);
            s.setCountry(tokens[1]);
            s.setAge(Integer.parseInt(tokens[2]));
            s.setHeight(Double.parseDouble(tokens[3]));
            s.setClub(tokens[4]);
            s.setPosition(tokens[5]);
            s.setNumber(Integer.parseInt(tokens[6]));
            s.setWeekly_salary(Double.parseDouble(tokens[7]));
            playerList.add(s);
        }
        br.close();
        return playerList;
    }

    public static void writeToFile(List<Player> playerList) throws Exception {
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        // var bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        for (Player s : playerList) {
            bw.write(s.getName() + "," + s.getCountry() + "," + s.getAge() + "," + s.getHeight() + "," + s.getClub() + "," + s.getPosition() + "," + s.getNumber() + "," + s.getWeekly_salary());
            bw.write("\n");
        }
        bw.close();
    }

    /*public static void main(String[] args) throws Exception {
        List<Player> playerList = readFromFile();
        for (Player s : playerList) {
            System.out.println(s.getName() + ", " + s.getCountry() + ", " + s.getClub() + ", " + s.getPosition() + ", " + s.getAge() + ", " + s.getNumber() + ", " + s.getHeight() + ", " + s.getWeekly_salary());
        }
        //writeToFile(studentList);

    }*/
}