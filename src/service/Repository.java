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
	// Lists for the recent history
	private RecentList<T> recentsList;
	
    
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
        recentsList.addRecent(item); //to keep track of the history
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

    public List<T> getSortedDiscarded() {
        //using the comparable interface
        List<T> returnList = new ArrayList<>();
        List<T> copiedOriginal = list; //create a copy since items will be removed in this method
        T keep;
        T compare;
        int originalSize = copiedOriginal.size();
        for(int i = 0; i<originalSize; i++){
            //start: keep to be the first item in the list
            keep = copiedOriginal.get(0);
            for(int j=0; j < copiedOriginal.size(); j++){
                //compare keep to each item in the array
                compare = copiedOriginal.get(j);
                if(keep.compareTo(compare) < 0) {
                    keep = copiedOriginal.get(j); //store the "larger" one in keep
                }
            }
            //keep should have the current largest
            int index = copiedOriginal.indexOf(keep); //find the index of keep - which is the largest item
            returnList.add(copiedOriginal.remove(index)); //remove the largest item and store it at the end of the return list
        }
        return returnList;
    }

}
