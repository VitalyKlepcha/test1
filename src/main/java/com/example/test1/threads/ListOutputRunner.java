package com.example.test1.threads;

import com.example.test1.parametres.entityParametres;

import java.util.ArrayList;

public class ListOutputRunner implements Runnable {
    private ArrayList<entityParametres> list;

    public ListOutputRunner(ArrayList<entityParametres> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (entityParametres el : list)
            System.out.println(el.getFirstLine() + el.getSecondLine());
    }
}
