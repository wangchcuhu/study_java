package Operator.myCollection;

import java.util.Arrays;

/**
 * @program: Java_study
 * @description:This class provides a skeletal implementation of the Collection interface,
 * to minimize the effort required to implement this interface.
 *
 * unmodifiable collection:implementations for the iterator and size methods
 * (The iterator returned by the iterator method must implement hasNext and next.)
 *
 * modifiable collection:override this class's add method
 * and the iterator returned by the iterator method must additionally implement its remove method.
 *
 *give: a void (no argument) and Collection constructor
 *
 * @author: liu yan
 * @create: 2021-12-02 19:33
 */
public abstract class MyAbstractCollection<E> implements MyCollection{
    //Sole constructor. (For invocation by subclass constructors, typically implicit.)
    protected MyAbstractCollection(){};

    public String toString() {
        return "MyAbstractCollection{}";
    };
    public abstract MyIterator<E> iterator();

    public abstract int size();

    public boolean isEmpty() { return size()==0;}

    @Override
    public boolean contains(Object o) {
        MyIterator<E> it=iterator();
       if(o==null){
           while (it.hasNext()) if(it.next()==null) return true;
       }else{
           while (it.hasNext()) if(o.equals(it.next())) return true;
       }
       return false;
    }
    private static final int MAX_ARRAY_SIZE=Integer.MAX_VALUE;

//    @Override
//    public Object[] toArray() {
//        // Estimate size of array; be prepared to see more or fewer elements
//        Object[] r = new Object[size()];
//        MyIterator<E> it = iterator();
//        for (int i = 0; i < r.length; i++) {
//            if (! it.hasNext()) // fewer elements than expected
//                return Arrays.copyOf(r, i);
//            r[i] = it.next();
//        }
//        return it.hasNext() ? finishToArray(r, it) : r;
//    }
//
//    private static <E> E[] finishToArray(E[] r,MyIterator<?>it){
//
//    }
}
