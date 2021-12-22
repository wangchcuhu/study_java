package Operator;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

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
//向日志中输出异常
class loggingExceptions{
    public static Logger logger = Logger.getLogger("loggingExceptions");
    //向日志中打印异常
    static void logException(Exception e){
        StringWriter trace = new StringWriter();
        e.printStackTrace(new PrintWriter(trace));
        logger.severe(trace.toString());
    }

    public static void main(String[] args) {
        try {
            throw new NullPointerException();
        } catch (NullPointerException e) {
            logException(e);
        }
        try {
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

    }
}

//getMessage类似于普通对象上的toString
class MyException1 extends Exception {
    private int x;
    MyException1() {
        super();
    }
    MyException1(String msg) {
        super(msg);
    }
    MyException1(String msg,int x) {
        super(msg);
        this.x = x;
    }
    public int val(){
        return x;
    }

    @Override
    public String getMessage() {
        return "Detail Message"+x+ super.getMessage();
    }

    public static void main(String[] args) {
        try {
            throw new MyException1("f1", 999);

        } catch (MyException1  e) {
            e.printStackTrace(System.out);
        }
        try {
            throw new MyException1("g1");
        } catch (MyException1 myException1) {
            //实际上会先输出警告类名+再调用getMessage输出到控制台，然后输出错误的行
            myException1.printStackTrace();
        }
    }
}
//打印发生异常是栈中方法的调用顺序
class WhoCalled{
    static void f(){
        try {
            throw new Exception();
        } catch (Exception e) {
            for (StackTraceElement ste : e.getStackTrace()) {
                System.out.println(ste.getMethodName());
            }
        }
    }
    static void g() {
        f();
    }
    static void h() {
        f();
    }

    public static void main(String[] args) {
        g();
        h();
    }
}
//RuntimeException
class NeverCaught{
    static void f() {
            throw new RuntimeException("From f()");
    }

    public void g() {
        f();
    }

    public static void main(String[] args) {
        NeverCaught neverCaught = new NeverCaught();
        neverCaught.g();
    }
}













