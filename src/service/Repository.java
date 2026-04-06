package service;

import interfaces.Identifiable;

public class Repository<T extends Identifiable & Comparable<? super T>> {
    // 1. Add items (mirrors main List) public void add(T item); 
    // 2. ID-based lookup (throws EntityNotFoundException) public T findById(String id); 
    // 3. FILTERING: Accepts any Predicate<T> "yes/no question" public List<T> filter(Predicate<T> predicate);
    // 4. Smart sort using business natural order public List<T> getSorted();

    public void add(T item) {
        
    }


}
