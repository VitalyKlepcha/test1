package com.example.test1.service;

import com.example.test1.exceptions.InputDataException;
import com.example.test1.parametres.entityParametres;

import java.util.ArrayList;

public interface FileWorker {
    void write(String path,  ArrayList<entityParametres> list);
    void write(String path, String message);
    String read(String path);
    void read(String path, ArrayList<entityParametres> list)throws InputDataException;
}
