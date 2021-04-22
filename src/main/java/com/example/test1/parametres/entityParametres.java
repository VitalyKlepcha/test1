package com.example.test1.parametres;
import com.example.test1.exceptions.InputDataException;
import com.example.test1.service.ReverseService;
import com.example.test1.service.impl.ReverseServiceImpl;
import com.example.test1.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class entityParametres {
    private String firstLine;
    private String secondLine;
    //private Validator validator;
    //private ReverseServiceImpl reverseService;
    private static final Logger log = LogManager.getLogger();

    public entityParametres(String firstLine, String secondLine){
        this.firstLine = firstLine;
        this.secondLine = secondLine;
        //validator = new Validator();
        //reverseService = new ReverseServiceImpl();



    }
    public void Reverse() throws InputDataException{
        try {
            ReverseService reverseService = (text) -> {
                StringBuilder sb = new StringBuilder();
                for (int i = text.length() - 1; i >= 0; i--) {
                    sb.append(text.charAt(i));
                }
                return sb.toString();
            };
            log.info("leftInput = " + firstLine + '/' + "rightInput = " + secondLine);
            Validator validator = (String fstring, String sstring) -> {if(fstring.isEmpty() && sstring.isEmpty())
                throw new InputDataException("Incorrect data");
            else if(!fstring.isEmpty() && !sstring.isEmpty())
                throw new InputDataException("Incorrect data");
            else
                return true;};
            validator.dataValidation(firstLine, secondLine);
            this.secondLine = !this.firstLine.isEmpty() ? reverseService.reverse(this.firstLine) : this.secondLine;
            this.firstLine = !this.secondLine.isEmpty() ? reverseService.reverse(this.secondLine) : this.firstLine;

        }
        catch(InputDataException ex){
            log.error(ex.getMessage());
            throw new InputDataException(ex.getMessage());
        }
    }
    public String getFirstLine() {
        return firstLine;
    }

    public String getSecondLine() {
        return secondLine;
    }


}
