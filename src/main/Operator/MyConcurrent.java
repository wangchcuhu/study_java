package Operator;

import javax.swing.border.EtchedBorder;
import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * @program: Java_study
 * @description: 并发
 * @author: liu yan
 * @create: 2021-12-29 14:20
 */
public class MyConcurrent {
}

//多线程实际上就是CPU分化时间片且运行的时候互不干扰(多CPU就是实际意义上的并发运行)
class LiftOff implements Runnable {
    protected int countDown = 10;
    public static int counter = 0;
    private final int id = counter++;
    public LiftOff() {
    }
    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" + (countDown > 0 ? countDown : "LiftOff") + ")";
    }
    @Override
    public void run() {
        try {
        while (countDown-- > 0) {
            System.out.println(status());
//            Thread.yield();
            //休眠
                TimeUnit.MICROSECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
               System.err.println("Interrupted");
        }
    }

    public static void main(String[] args) {
//        LiftOff liftOff = new LiftOff();
//        liftOff.run();
//        System.out.println("Waiting for LiftOff");
        //转变成工作任务
//        Thread t = new Thread(new LiftOff());//创建另一个线程，等待执行命令，这里有点类似与JavaScript的Promise,在顺序执行的时候，后端的接口请求异步。
        //多线程执行run里面的任务
//        t.start();
        System.out.println("Waiting for LiftOff");

        for (int i = 0; i < 5; i++) {
            System.out.println("创建线程"+i);
            new Thread(new LiftOff()).start();
        }
    }
}

//使用Executor管理线程的生命周期
class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            //创建要执行的线程
            exec.execute(new LiftOff());//自动调用run
        }
        exec.shutdown();//一旦执行就不能再添加新的任务
    }
}
//有限的线程
class FixedThreadPool {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(5);//预先执行代价昂贵的线程分配
        for (int i = 0; i < 8; i++) {
            //创建要执行的线程
            exec.execute(new LiftOff());//自动调用run
        }
        exec.shutdown();//一旦执行就不能再添加新的任务
    }
}

class SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newSingleThreadExecutor();//单个线程排队
        for (int i = 0; i < 8; i++) {
            //创建要执行的线程
            exec.execute(new LiftOff());//自动调用run
        }
        exec.shutdown();//一旦执行就不能再添加新的任务
    }
}

//从任务中产生返回值
class TaskWithResult implements Callable<String> {
    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }
    //执行的任务
    @Override
    public String call() throws Exception {
        return "result of TaskWithResult"+id;
    }
}
//返回结果
class CallableDemo {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> list = new ArrayList<Future<String>>();
        for (int i = 0; i < 10; i++) {
            list.add(exec.submit(new TaskWithResult(i)));
        }
        for (Future<String> stringFuture : list) {
            try {
                System.out.println(stringFuture.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }finally {
                exec.shutdown();
            }
        }
    }
}
//优先级
class SimplePriorities implements Runnable{
    private int countDown = 5;
    private volatile double d;
    private int priority;

    public SimplePriorities(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return Thread.currentThread()+":"+countDown;
    }

    @Override
    public void run() {
        Thread.currentThread().setPriority(priority);
        while (true) {
            for (int i = 0; i < 10000; i++) {
                d += (Math.PI + Math.E) / (double) i;
                if (i % 1000 == 0) {
                    Thread.yield();
                }
            }
            System.out.println(this);
            if (--countDown == 0) return;
        }
    }

    public static void main(String[] args) {
        //每个线程都是拥有单独的对象，并没有线程共享资源的情况的发生
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new SimplePriorities(Thread.MIN_PRIORITY) );
        }
        exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));
        exec.shutdown();
    }
}

//后台进程
class SimpleDaemons implements Runnable{
    @Override
    public void run() {
        while (true) {
            try {
                TimeUnit.MICROSECONDS.sleep(100);
                System.out.println(Thread.currentThread()+"1111"+this);
            } catch (InterruptedException e) {
                System.out.println("sleep() interrupted");
            }
        }
    }
    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 10; i++) {
            Thread daemon = new Thread(new SimpleDaemons());
            daemon.setDaemon(true);
            daemon.start();
        }
        //非后台线程休眠,非后台线程main一旦结束，所有后台线程都将被杀死
        System.out.println("All daemon start");
//            TimeUnit.MICROSECONDS.sleep(175);

    }
}

//直接使用Thread
class SimpleThread extends Thread {
    private int i;

    public SimpleThread(int i) {
        this.i = i;
       start();
    }

    public void run() {
        System.out.println(i);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new SimpleThread(i);
        }
    }
}













