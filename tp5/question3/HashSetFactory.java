package question3;

import java.util.Set;
import java.util.HashSet;

public class HashSetFactory<T extends Comparable> implements Factory<Set<T>> {
    
    @Override
    public Set<T> create() {
        return new HashSet<T>();
    }
}