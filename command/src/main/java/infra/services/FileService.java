package infra.services;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileService {
    public static List<String> read(String filePath) {
        try {
            final File file = new File(filePath);
            final Scanner fileLines = new Scanner(file);

            List<String> linesList = new ArrayList<String>();
            while(fileLines.hasNextLine()) {
                linesList.add(fileLines.nextLine());
            }

            fileLines.close();
            return linesList;
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean write(String text, boolean append, String filePath) {
        try {
            FileWriter fileWriter = new FileWriter(filePath, append);
            fileWriter.write(text);
            fileWriter.close();

            return true;
        } catch (Exception e) {
            return false;
        }
    }    
}