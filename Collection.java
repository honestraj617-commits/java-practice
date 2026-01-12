import java.util.HashSet;
import java.util.Iterator;

public class Reverseword {
    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
        set.add("Java");
        set.add("phython");
        set.add("c");
        set.add("c#");
        System.out.println(set); // orders are not maintained

        HashSet<String> set2 = new HashSet<>();
        set2.add("C++");
        set2.addAll(set);
        System.out.println(set2);
        set2.remove("phython");
        System.out.println(set2);
        set2.removeAll(set);   // remove all matching element of set2
        System.out.println(set2);
        System.out.println(set2.contains("C++"));
        System.out.println(set2.size());
        set.clear();
        System.out.println(set);
        System.out.println(set.isEmpty());

        Iterator<String> it = set2.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        


        
        
    }
}
