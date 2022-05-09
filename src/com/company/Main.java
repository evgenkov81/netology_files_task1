package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) throws IOException {
        List<String> path = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        FileWriter out = null;
        File fileTemp = null;
        path.add("C:\\Games\\src\\main");
        path.add("C:\\Games\\src\\test");
        path.add("C:\\Games\\res\\drawables");
        path.add("C:\\Games\\res\\vectors");
        path.add("C:\\Games\\res\\icons");
        path.add("C:\\Games\\savegames");
        path.add("C:\\Games\\temp");

        try {
            for (String s : path) {
                File file = new File(s);
                if (file.mkdirs()) {
                    writeLog(temp, file, true);
                    if (s.equals("C:\\Games\\temp")) {
                        fileTemp = new File(s + "\\temp.txt");
                        if (fileTemp.createNewFile()) {
                            writeLog(temp, fileTemp, true);
                        } else {
                            writeLog(temp, fileTemp, false);
                        }
                        out = new FileWriter(s + "\\temp.txt");
                    }
                } else {
                    writeLog(temp, file, false);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (fileTemp != null) {
                if (out != null) {
                    out.write(temp.toString());
                    out.close();
                }
            } else {
                System.out.println(temp);
            }
        }
    }

    private static void writeLog(StringBuilder temp, File file, boolean b) {
        temp.append("\n")
                .append(b ? " " : "Error")
                .append(file.isDirectory() ? "Dir " : "File ")
                .append(file.getAbsolutePath())
                .append(b ? " - created" : " - not created");
    }
}

