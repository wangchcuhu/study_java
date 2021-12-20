package Operator;

/**
 * @program: Java_study
 * @description: 异常处理
 * @author: liu yan
 * @create: 2021-12-20 19:49
 */
public class MyExceptions {
}

class SimpleException extends Exception {
    public SimpleException() {
    }
    public SimpleException(String s) {
        super(s);
    }
}
class TestSimpleException{

    public void f() throws SimpleException {

        System.out.println("doSomething in f()");
        throw new SimpleException("Originated in f()");
    }
    public void g() throws SimpleException {

        System.out.println("doSomething in g()");
        throw new SimpleException("Originated in g()");
    }

    public static void main(String[] args) {
        TestSimpleException testSimpleException = new TestSimpleException();
        try {
            //内部有个异常被抛到外层来处理，提升了一个作用于，可能是没有获得足够的信息
            testSimpleException.f();
        } catch (SimpleException e) {
            e.printStackTrace(System.out);
        }
        try {
            //内部有个异常被抛到外层来处理，提升了一个作用于，可能是没有获得足够的信息
            testSimpleException.g();
        } catch (SimpleException e) {
            e.printStackTrace(System.out);
        }
    }
}
class Training{
    public static void main(String[] args) {
        try {
            System.out.println("doSomething");
            throw new Exception("Error");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            //最終都要執行的方法
            System.out.println("finally");
        }
    }
}


//定义一个对象并初始化为null,尝试调用此方法，捕获异常
class Training1{
    public static void main(String[] args) {
        try {
            Person person = null;
            //并没有抛出Exception这个类型的异常对象
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            //最終都要執行的方法
            System.out.println("finally");
        }
    }
}