package com.example.test1.validator;
import com.example.test1.exceptions.InputDataException;
public interface Validator {
    public boolean dataValidation(String fstring, String sstring) throws InputDataException;
}

