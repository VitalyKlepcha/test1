package com.example.test1.threads;

import com.example.test1.service.FileWorker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ErrorsOutputRunner implements Runnable {
    private static final String ERRORDATABASE = "D:\\test1\\errorDatabase.txt";
    private FileWorker fileWorker;
    private static final Logger log = LogManager.getLogger();

    public ErrorsOutputRunner(FileWorker fileWorker) {
        this.fileWorker = fileWorker;
    }

    @Override
    public void run() {
        String message = fileWorker.read(ERRORDATABASE);
        log.info(message);
    }
}


