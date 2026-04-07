import java.util.List;
import java.util.ArrayList;

public class tester {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(3);
        list.add(7);
        list.add(4);
        list.add(6);
        list.add(9);
        list.add(0);
        list.add(2);
        System.out.println(list); //index 2: 7, index 3: 4
        int keep, compare, indexOfGreatest, temp;
        for(int i = 0; i < list.size(); i++) {
            keep = list.get(i);
            indexOfGreatest = i; //start the index at i
            for(int j = i; j < list.size(); j++) {
                compare = list.get(j);
                if(keep < compare ) {
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
            //swap
       
            System.out.println("final: " + list);

    }
}