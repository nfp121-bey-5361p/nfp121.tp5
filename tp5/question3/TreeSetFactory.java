package question3;

import java.util.Set;
import java.util.TreeSet;

public class TreeSetFactory<T extends Comparable> implements Factory<Set<T>> {

    @Override
    public Set<T> create() {
        return new TreeSet<T>();
    }
}
