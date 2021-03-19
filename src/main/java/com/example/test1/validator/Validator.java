package com.example.test1.validator;
import com.example.test1.exceptions.InputDataException;
public class Validator {
    public boolean dataValidation(String fstring, String sstring) throws InputDataException{

        if(fstring.isEmpty() && sstring.isEmpty())
            throw new InputDataException("Incorrect data");
        else if(!fstring.isEmpty() && !sstring.isEmpty())
            throw new InputDataException("Incorrect data");
        else
            return true;
    }
}

