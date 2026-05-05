package service;

// import all the required packages, exceptions and persistence files 

import interfaces.Identifiable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import exceptions.*;

public class Repository<T extends Identifiable & Comparable<? super T>> {
    //Generic list - contains all data
    private List<T> list;
    
    // 1. Add items (mirrors main List) public void add(T item); 
    // 2. ID-based lookup (throws EntityNotFoundException) public T findById(String id); 
    // 3. FILTERING: Accepts any Predicate<T> "yes/no question" public List<T> filter(Predicate<T> predicate);
    // 4. Smart sort using business natural order public List<T> getSorted();

    public Repository() {
        list = new ArrayList<>();
    }

    public Repository(List<T> list) {
        this.list = list;
    }

    public void add(T item) {
        list.add(item);
    }

    public void addAll(List<T> list) {
        for(T item:list) {
            add(item);
        }
    }

    public void remove(int index) {
        list.remove(index);
    }

    public T findById(String Id) throws EntityNotFoundException{
        for(T item:list) {
            String currentId = item.getId();
            if(currentId.equals(Id)) return item; 
        }
        throw new EntityNotFoundException("Entity not found.");
    }

    public List<T> filter(Predicate<T> predicate) {
        List<T> returnList = new ArrayList<T>();
        for(T item:list) {
            if(predicate.test(item)) returnList.add(item);
        }
        return returnList;
    }

    public List<T> getSorted() { 
        //Sorts in descending order based on compareTo
        List<T> returnList = copy(list);
        T keep, compare, temp;
        int indexOfGreatest;
        for(int i = 0; i < returnList.size(); i++) {
            keep = returnList.get(i);
            indexOfGreatest = i; //start the index at i
            for(int j = i; j < returnList.size(); j++) {
                compare = returnList.get(j);
                if(keep.compareTo(compare) < 0) {
                    //compare is the larger one
                    keep = returnList.get(j); //store the current largest value in keep
                    indexOfGreatest = j;
                }
            }
            //swap
            temp = returnList.get(i); //store, since will be replaced by the largest (keep)
            returnList.set(i, keep); //put the largest value at i
            returnList.set(indexOfGreatest, temp); //store the removed value in keep's old place  
        }
        return returnList;
    }

    private List<T> copy(List<T> list) {
        List<T> copy = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            copy.add(list.get(i));
        }
        return copy; //shallow copy - only used in sorting methods
    }
}
