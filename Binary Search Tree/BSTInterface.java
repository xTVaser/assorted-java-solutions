/**
 * Created by Dtylan on 2016-08-09.
 */
public interface BSTInterface<T> {

    boolean isEmpty();
    void add(T item) throws BSTException;
    boolean contains(T item);
    void delete(T item) throws BSTException;
    void removeAll();
}
