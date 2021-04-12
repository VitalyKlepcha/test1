package com.example.test1.service.impl;

import com.example.test1.comparator.StringComparator;
import com.example.test1.service.SortListService;
import com.example.test1.parametres.entityParametres;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SortListServiceImpl implements SortListService{
    private final StringComparator stringComparator;

    public SortListServiceImpl(){
        this.stringComparator = new StringComparator();
    }

    @Override
    public ArrayList<entityParametres> Sort(ArrayList<entityParametres> list){
        for (int i = 1; i < list.size(); i++) {
            entityParametres current = list.get(i);
            int j = i - 1;
            while(j >= 0 && stringComparator.compare(current, list.get(j)) == 1) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, current);
        }
        for(entityParametres el : list)
            System.out.println(el.getFirstLine() + el.getSecondLine());
    return list;

    }
}
