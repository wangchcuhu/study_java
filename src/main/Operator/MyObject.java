package Operator;

/**
 * @program: Java_study
 * @description:
 * @author: liu yan
 * @create: 2021-12-06 11:04
 */
public  class MyObject {
    //(native)Returns the runtime class of this Object.
    public final Class<?> myGetClass(){return null;}
    //(native) Returns a hash code value for the object.
    public int hasCode(){return 1;}
    //Indicates whether some other object is "equal to" this one.
    public boolean equals(Object obj){return (this == obj);}
    //(native)
    protected Object clone() throws CloneNotSupportedException{
        return null;
    }
    //a representation of the object
    public String toString(){
        return myGetClass().getName()+"@"+Integer.toHexString(hasCode());
    }
    //Wakes up a single thread that is waiting on this object's monitor.
    public final void MyNotify(){return;}
    //Wakes up all threads that are waiting on this object's monitor.
    public final void MyNotifyAll(){return;}
    //Causes the current thread to wait until either another thread invokes the notify() method or the notifyAll() method for this object,
    // or a specified amount of time has elapsed.
    public final void myWait() throws InterruptedException{};


}
