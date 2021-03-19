package com.example.test1.service.impl;

import com.example.test1.service.ReverseService;
import org.springframework.stereotype.Service;

@Service
public class ReverseServiceImpl implements ReverseService {
    @Override
    public String reverse(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = text.length() - 1; i >= 0; i--) {
            sb.append(text.charAt(i));
        }
        return sb.toString();
    }
}
