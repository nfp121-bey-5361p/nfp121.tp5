package question1;

import java.util.*;

public class Ensemble<T> extends AbstractSet<T> {

    protected java.util.Vector<T> table = new java.util.Vector<T>();

    public int size() {
        return table.size();
    }

    public Iterator<T> iterator() {
        return table.iterator();
    }

    /**
     * Ajoute un element a la collection
     * @param t element a ajoute a l'esemble
     * @return resultat de l'ajout (succes ou echou)
     */
    public boolean add(T t) {
        if (t == null || this.contains(t)) // this.contains() is inherited from AbstractCollection
            return false;
        return table.add(t); // when this.add(...) is called, it would recursively call the add method of Ensemble indefinetely leading to a StackOverflowError
    }

    public Ensemble<T> union(Ensemble<? extends T> e) {
        if (e == null)
            throw new IllegalArgumentException("ensemble null");
        Ensemble<T> nvEnsemble = new Ensemble<T>();
        nvEnsemble.addAll(this);
        nvEnsemble.addAll(e);
        return nvEnsemble;
    }

    public Ensemble<T> inter(Ensemble<? extends T> e) {
        if (e == null)
            throw new IllegalArgumentException("ensemble null");        
        Ensemble<T> nvEnsemble = new Ensemble<T>();
        nvEnsemble.addAll(this);
        nvEnsemble.retainAll(e);
        return nvEnsemble;
    }

    public Ensemble<T> diff(Ensemble<? extends T> e) {
        if (e == null)
            throw new IllegalArgumentException("ensemble null");
        Ensemble<T> nvEnsemble = new Ensemble<T>();
        nvEnsemble.addAll(this);
        nvEnsemble.removeAll(e);
        return nvEnsemble;
    }

    Ensemble<T> diffSym(Ensemble<? extends T> e) {
        if (e == null)
            throw new IllegalArgumentException("ensemble null");
        return this.union(e).diff(this.inter(e));
    }
    
}
