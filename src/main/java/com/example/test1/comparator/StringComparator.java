package com.example.test1.comparator;
import com.example.test1.parametres.entityParametres;
import java.util.Comparator;


public class StringComparator implements Comparator<entityParametres> {
    public int compare(entityParametres first, entityParametres second){
        int min;
        min = first.getFirstLine().length() < second.getFirstLine().length() ? first.getFirstLine().length() : second
                .getSecondLine().length();
        for(int i = 0; i < min; i++){
            if(first.getFirstLine().charAt(i) < second.getFirstLine().charAt(i))
                return 1;
            else if (first.getFirstLine().charAt(i) > second.getFirstLine().charAt(i))
                return -1;
        }
        return 0;
    }
}
