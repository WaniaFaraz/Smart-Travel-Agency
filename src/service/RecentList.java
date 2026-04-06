//-----------------------------------------------------------------------------
//Assignment 3
//Written by:
// 		Wania Faraz 40332781
//		Zahira Atmani 40350242
//-----------------------------------------------------------------------------


package service;

import java.util.LinkedList;
import interfaces.Identifiable;

public class RecentList<T extends Identifiable> {
    private LinkedList<T> list = new LinkedList<>();
    private final int MAX_SIZE = 10;

    public void addRecent(T item) {
        list.add(item);
        if(list.size() > MAX_SIZE) list.remove(MAX_SIZE); //KEEPS LIST AT THE MAX_SIZE
        
    }

    public void printRecent(int maxToShow) {
        int count = 1;
        for(Identifiable item:list) {
            if(count <= maxToShow) {
                System.out.println(item);
                count++;
            }
        }
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }
}
