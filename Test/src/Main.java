import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line = in.readLine();
        if ((line.length()%2) != 0) {
            System.out.println("False");
        }
        char[] lineArray = line.toCharArray();
        ArrayList<Character> charList = new ArrayList<>();
        for (char input : lineArray) {
            charList.add(input);
        }
        int counter = 0;
        while (!(charList.isEmpty())) {
            if (counter > 20)
                break;
            if (findMatch(charList.get(0),charList.get(1))) {
                charList.remove(0);
                charList.remove(0);
            }
            counter ++;
        }
        if (charList.size() == 0) {
            System.out.println("True");
        }
        else {
            System.out.println("False");
        }
    }

    static boolean findMatch(char x, char y) {
        if (x == '(' && y == ')')
            return true;
        else if (x == '{' && y == '}')
            return true;
        else if (x == '[' && y == ']')
            return true;
        else
            return false;
    }

}