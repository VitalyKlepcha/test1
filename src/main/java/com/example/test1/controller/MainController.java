package com.example.test1.controller;

import com.example.test1.exceptions.InputDataException;
import com.example.test1.service.FileWorker;
import com.example.test1.parametres.entityParametres;
import com.example.test1.service.SortListService;
import com.example.test1.service.impl.SortListServiceImpl;
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
            for(entityParametres el : list)
                System.out.println(el.getFirstLine() + el.getSecondLine());
            list = sortListService.Sort(list);
            fileWorker.write(DATABASE, list);
        }
        catch(InputDataException ex){
            model.addAttribute("error", ex.getMessage());
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