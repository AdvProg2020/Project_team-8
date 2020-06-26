package Model;


import com.google.gson.Gson;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import javax.print.DocFlavor;
import javax.print.attribute.standard.MediaName;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


public class FileHandler {
    private static Gson usersJson = new Gson();

    private static File usersFile = new File("Resources\\users.txt");


    public static void getDataFromFiles() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(usersFile.toPath().toString());
        Scanner fileReader = new Scanner(fileInputStream);
        while (fileReader.hasNextLine()) {
            ManageInfo.allUsers.add(usersJson.fromJson(fileReader.nextLine(), User.class));
        }
    }

    public static void setDataIntoFiles() throws IOException {
        createJson();
        writeFiles();
    }

    private static void createJson () {

    }

    public static void createFile() throws IOException {
    }

    private static void writeFiles() throws IOException {
        FileWriter writer = new FileWriter(usersFile);
        for (User user : ManageInfo.allUsers) {
            writer.append(usersJson.toJson(user) + "\n");
        }
        //writer.write(usersJson.toJson(ManageInfo.allUsers) + "\n");
        writer.close();
    }

}
