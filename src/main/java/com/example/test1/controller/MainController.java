package com.example.test1.controller;

import com.example.test1.exceptions.InputDataException;
import com.example.test1.service.FileWorker;
import com.example.test1.service.ReverseService;
import com.example.test1.validator.Validator;
import com.example.test1.service.impl.FileWorkerImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class MainController {
    private static final String DATABASE = "D:\\test1\\database.txt";
    private FileWorker fileWorker;
    private ReverseService reverseService;
    private static final Logger log = LogManager.getLogger();

    @Autowired
    public MainController(FileWorker fileWorker, ReverseService reverseService) {
        this.fileWorker = fileWorker;
        this.reverseService = reverseService;
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
        Validator validator = new Validator();
        try{
            fileWorker.write(DATABASE, text1, text2);
            log.info("leftInput = " + text1 + '/' + "rightInput = " + text2);
            validator.dataValidation(text1, text2);
            text2 = !text1.isEmpty() ? reverseService.reverse(text1) : text2;
            text1 = !text2.isEmpty() ? reverseService.reverse(text2) : text1;
        }
        catch(InputDataException ex){
            model.addAttribute("error", ex.getMessage());
            fileWorker.write(DATABASE, ex.getMessage());
            log.error(ex.getMessage());
        }

        model.addAttribute("leftInput", text1);
        model.addAttribute("rightInput", text2);
        return "home";
    }
    @PostMapping("/database")
    public String database(Model model){
        model.addAttribute("data", fileWorker.read(DATABASE));
        return "database";
    }
}