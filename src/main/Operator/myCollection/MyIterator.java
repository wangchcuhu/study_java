package Operator.myCollection;

import java.util.Objects;
import java.util.function.Consumer;

//Implementing this interface allows an object to be the target of the "for-each loop" statement. See For-each Loop
/*
凡是实现这个接口的都具备被foreach作为目标
 */
public interface MyIterator<T>{
    MyIterator<T> iterator();
    //true if the iteration has more elements
    boolean hasNext();
    //Returns the next element in the iteration.
    T next();

    default void remove(){throw new UnsupportedOperationException("remove");}

    default void foreachRemaining(Consumer<? super T> action){
        Objects.requireNonNull(action);
        while(hasNext()){
            action.accept(next());
        }
    }

}


