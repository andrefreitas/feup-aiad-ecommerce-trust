/*
 * This class is responsible to load data from the .json file
 */
package data;

import com.google.gson.*;
import java.util.Map;
import java.util.ArrayList;
import java.io.*;

public class Parser {

    public ArrayList<Map<String, String[]>[]> users;
    public Map<String, String[]> categories;
    public String fileName;

    public Parser(String fileName) {
        this.fileName = fileName;
    }

    public void parse() {
        try {
            File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
            String s = new String(data, "UTF-8");
            JsonParser gParser = new JsonParser();
            JsonElement parseTree = gParser.parse(s);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

    public static void main(String args[]) {
        Parser p = new Parser("data.json");
        System.out.println("Starting to parse");
        p.parse();
        System.out.println("Parsed");
    }

}
