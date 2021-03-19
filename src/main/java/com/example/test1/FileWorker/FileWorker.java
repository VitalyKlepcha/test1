package com.example.test1.FileWorker;

import java.io.*;

public class FileWorker {
    File file=new File("D:\\test1\\database.txt");
    public void write(String firstString, String secondString) {

        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write("leftInput = " + firstString + '/' + "rightInput = " + secondString + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void write(String message) {

        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(message + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String read(){
        StringBuilder sb= new StringBuilder();
        try (FileReader reader =new FileReader(file)){
            int c;
            while((c = reader.read())!= -1) {
                sb.append((char)c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
