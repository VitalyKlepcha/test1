package com.example.test1.service.impl;

import com.example.test1.exceptions.InputDataException;
import com.example.test1.service.ListWorkerService;
import com.example.test1.parametres.entityParametres;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ListWorkerServiceImpl implements ListWorkerService {
    private static final String DATABASE = "D:\\test1\\database.txt";
    private FileWorkerImpl fileWorker;
    private SortListServiceImpl sortListService;
    private static final Logger log = LogManager.getLogger();

    public void AddObject(ArrayList<entityParametres> list, entityParametres entityParametres) {
        try {
            fileWorker = new FileWorkerImpl();
            sortListService = new SortListServiceImpl();
            fileWorker.read(DATABASE, list);
            list.add(entityParametres);
            list = sortListService.Sort(list);
            fileWorker.write(DATABASE, list);
        } catch (InputDataException ex) {
            log.error(ex.getMessage());
        }


    }
}
