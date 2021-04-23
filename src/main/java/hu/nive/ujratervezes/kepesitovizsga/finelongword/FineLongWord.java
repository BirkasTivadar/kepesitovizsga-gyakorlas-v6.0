package hu.nive.ujratervezes.kepesitovizsga.finelongword;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FineLongWord {


    public char[] readFineLongWordFromFileAndGetItInAnArray(String s) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                FineLongWord.class.getResourceAsStream("/" + s)))) {
            String line = reader.readLine();
            sb.append(line);
            while ((line = reader.readLine()) != null) {
                sb.append(line.substring(line.length() - 1));
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Can not read file", ioe);
        }
        return sb.toString().toCharArray();
    }
}