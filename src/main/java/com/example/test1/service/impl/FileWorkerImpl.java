package com.example.test1.service.impl;

import com.example.test1.service.FileWorker;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
@Repository
public class FileWorkerImpl implements FileWorker {
    public void write(String path, String firstString, String secondString) {

        try (FileWriter writer = new FileWriter(path, true)) {
            writer.write("leftInput = " + firstString + '/' + "rightInput = " + secondString + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void write(String path, String message) {

        try (FileWriter writer = new FileWriter(path, true)) {
            writer.write(message + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String read(String path){
        StringBuilder sb= new StringBuilder();
        try {
            String str;
            FileReader freader =new FileReader(path);
            BufferedReader reader = new BufferedReader(freader);
            while((str = reader.readLine())!= null) {
                sb.append(str);
                sb.append("||");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
