package com.example.test1.service.impl;

import com.example.test1.exceptions.InputDataException;
import com.example.test1.service.FileWorker;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import com.example.test1.parametres.entityParametres;

@Service
@Repository
public class FileWorkerImpl implements FileWorker {
    public void write(String path, ArrayList<entityParametres> list) {

        try (FileWriter writer = new FileWriter(path, false)) {
            for (entityParametres el:list) {
                writer.write(el.getFirstLine() + " " + el.getSecondLine() + "\n");
            }
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

    public void read(String path, ArrayList<entityParametres> list) throws InputDataException {
        try {
            String str;
            String[] arrays = new String[2];
            FileReader freader =new FileReader(path);
            BufferedReader reader = new BufferedReader(freader);
            while((str = reader.readLine())!= null) {
                arrays = str.split(" ");
                list.add(new entityParametres(arrays[0], arrays[1]));

            }
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
