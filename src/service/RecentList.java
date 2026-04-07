//-----------------------------------------------------------------------------
//Assignment 3
//Written by:
// 		Wania Faraz 40332781
//		Zahira Atmani 40350242
//-----------------------------------------------------------------------------


package service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import interfaces.Identifiable;

public class RecentList<T extends Identifiable & Comparable<? super T>> {
    private LinkedList<T> list = new LinkedList<>();
    private final int MAX_SIZE = 10;

    public void addRecent(T item) {
        list.add(item);
        if(list.size() > MAX_SIZE) list.remove(MAX_SIZE); //KEEPS LIST AT THE MAX_SIZE
        System.out.println("Recents: " + list);
        
    }

    public void removeRecent(String ID) {
        
        int index = findById(ID);
        if(index != -1) list.remove(index); //if not in the last, doesnt matter
       
    }

    public void printRecent(int maxToShow) {
        for (int i = 0; i < maxToShow; i++) {
            System.out.println(list.get(i));
        }
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public int findById(String Id){
        int count = 0;
        for(T item:list) {
            String currentId = item.getId();
            if(currentId.equals(Id)) return count;
            count++;
        }
        return -1;
    }

    public List<T> getSorted() {
         //Sorts in descending order based on compareTo
        List<T> returnList = copy(list);
        T keep, compare, temp;
        int indexOfGreatest;
        for(int i = 0; i < list.size(); i++) {
            keep = list.get(i);
            indexOfGreatest = i; //start the index at i
            for(int j = i; j < list.size(); j++) {
                compare = list.get(j);
                if(keep.compareTo(compare) < 0) {
                    //compare is the larger one
                    keep = list.get(j); //store the current largest value in keep
                    indexOfGreatest = j;
                }
            }
            //swap
            temp = list.get(i); //store, since will be replaced by the largest (keep)
            list.set(i, keep); //put the largest value at i
            list.set(indexOfGreatest, temp); //store the removed value in keep's old place  
        }
        return returnList;
    }

    private List<T> copy(List<T> list) {
        List<T> copy = new ArrayList<>();
        for(T item:list) {
            copy.add(item);
        }
        return copy; //shallow copy - only used in sorting methods
    }

}
