package com.example.test1.service;

public interface FileWorker {
    void write(String path, String firstString, String secondString);
    void write(String path, String message);
    String read(String path);
}
