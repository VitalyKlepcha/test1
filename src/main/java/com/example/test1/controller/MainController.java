package com.example.test1.controller;

import com.example.test1.exceptions.InputDataException;
import com.example.test1.service.FileWorker;
import com.example.test1.parametres.entityParametres;
import com.example.test1.service.SortListService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;



@Controller
public class MainController {
    private static final String DATABASE = "D:\\test1\\database.txt";
    private static final String ERRORDATABASE = "D:\\test1\\errorDatabase.txt";
    private FileWorker fileWorker;
    private SortListService sortListService;
    private entityParametres entityParametres;
    private static final Logger log = LogManager.getLogger();
    private ArrayList<entityParametres> list;

    @Autowired
    public MainController(FileWorker fileWorker, SortListService sortListService) {
        this.fileWorker = fileWorker;
        this.sortListService = sortListService;
    }

    @GetMapping({"/", "/main"})
    public String greeting(Model model) {
        model.addAttribute("title","Главная страница");
        return "home";
    }

    @PostMapping("/main")
    public String postGreeting(
            @RequestParam(required = false) String text1,
            @RequestParam(required = false) String text2,
            Model model
    ) {
        list = new ArrayList<>();
        //sortListService = new SortListServiceImpl();
        try {
            fileWorker.read(DATABASE, list);
        }
        catch(InputDataException ex){
            model.addAttribute("error", ex.getMessage());
        }
        try{
            entityParametres = new entityParametres(text1, text2);
            entityParametres.Reverse();
            list.add(entityParametres);
            Thread listOutputThread = new Thread(new ListOutputRunner(list));
            listOutputThread.start();
            list = sortListService.Sort(list);
            fileWorker.write(DATABASE, list);
        }
        catch(InputDataException ex){
            model.addAttribute("error", ex.getMessage());
            fileWorker.write(ERRORDATABASE, ex.getMessage());
            Thread errorOutputThread = new Thread(new ErrorsOutputRunner(fileWorker));
            errorOutputThread.start();
        }

        model.addAttribute("leftInput", entityParametres.getFirstLine());
        model.addAttribute("rightInput", entityParametres.getSecondLine());
        return "home";
    }
    @PostMapping("/database")
    public String database(Model model){
        model.addAttribute("data", fileWorker.read(DATABASE));
        return "database";
    }
}

class ListOutputRunner implements Runnable{
    private ArrayList<entityParametres> list;
    ListOutputRunner(ArrayList<entityParametres> list){
        this.list = list;
    }

    @Override
    public void run(){
        for(entityParametres el : list)
            System.out.println(el.getFirstLine() + el.getSecondLine());
    }
}

class ErrorsOutputRunner implements Runnable{
    private static final String ERRORDATABASE = "D:\\test1\\errorDatabase.txt";
    private FileWorker fileWorker;
    private static final Logger log = LogManager.getLogger();

    public ErrorsOutputRunner(FileWorker fileWorker) {
        this.fileWorker = fileWorker;
    }

    @Override
    public void run(){
        String message = fileWorker.read(ERRORDATABASE);
        log.info(message);
    }
}