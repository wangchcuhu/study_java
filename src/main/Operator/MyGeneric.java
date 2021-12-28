package Operator;

import java.util.*;

/**
 * @program: Java_study
 * @description: 泛型
 * @author: liu yan
 * @create: 2021-12-24 11:23
 */
class Person5 {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int k() {
        return 1;
    }
}

//一个持有的容器(这样就只能持有Person的容器所以可以改写成Object)
public class MyGeneric {
    private Object person;

    MyGeneric(Person p) {
        this.person = p;
    }
    //持有所有的类型，但是这样使用的时候就需要清楚的知道存储的类型，向下转型使用
    MyGeneric(Object o) {
        this.person = o;
    }
}

//多态的目是动态的告诉编译器你想要放的类型
class Holder<T extends Person5> {
    private T t;
    Holder(T t) {
        this.t = t;
    }
    public int f() {
        return t.k();
    }

    public static void main(String[] args) {
        Holder<Person5> holder = new Holder<Person5>(new Person5());
        System.out.println(holder.f());
    }
}
//同时持有两个对象，一般来说为了安全区起见我们的成员一般是私有的，只有调用我们的
//方法按照指定的方式改变值才是安全的,或者是final
class Holder1<A,B>{
    public final A first;
    public final B last;

    Holder1(A a, B b) {
      this.first=a;
      this.last=b;
    }

    //验证final的元素是不是真的不可以修
    void testFinal(A b){
//        this.first = b;  不可以final一旦赋值就不可以改变了
    }
    @Override
    public String toString() {
        return "Holder1{" +
                "first=" + first +
                ", last=" + last +
                '}';
    }

    public static void main(String[] args) {
        Holder1<String, Integer> holder1 = new Holder1<>("mm", 1);
        System.out.println(holder1.toString());
    }
}
//通过泛型实现自己的内部链式存储机制
class LinkedStack<T>{
    //节点(自己的数据以及下一个节点的引用)
    class Node<U>{
        U item;
        Node<U> next;
        Node() {
            item = null;
            next = null;
        }
        Node(U item,Node<U> next) {
            this.item = item;
            this.next = next;
        }
        boolean end() {
            return item == null && next == null;
        }
    }
    //存
    public void push(T item) {
        top = new Node<T>(item, top);
    }
    //顶点
    private Node<T> top = new Node<T>();
    public T pop() {
        T result = top.item;
        if (!top.end()) {
            top = top.next;
        }

        return result;
    }

    public static void main(String[] args) {
        LinkedStack<Character> linkedStack = new LinkedStack<Character>();
        String s = "Hello World";
        for (Character c : s.toCharArray()) {
            linkedStack.push(c);
        }
        System.out.println(linkedStack.pop());

    }
}
//泛型方法
class Generic{
    public static <T> T f(T t) {
        System.out.println(t.getClass().getName());
        return t;
    }

    public static void main(String[] args) {
        f(1);
        f("1");
        f('s');
        f(true);
        f(1.2);
    }
}
//类型推断
class New{
    public static <k,v> Map<k,v> map() {
        return new HashMap<>();
    }
    public static <T>List<T>list() {
        return new ArrayList<T>();
    }

    public static void main(String[] args) {
        New c = new New();
        Map<String,Integer> map= c.map();
        System.out.println(map.getClass().getName());
        List<String> list = c.list();
        System.out.println(list.getClass().getName());

    }
}
//可变参数和泛型方法
class GenericArgs{
    public static <T> List<T> list(T ...args) {
        List<T> list = new ArrayList<T>();
        for (T arg : args) {
            System.out.println(arg);
            list.add(arg);
        }
        return list;
    }

    public static void main(String[] args) {
        list("A", "B", "c");
    }
}

//实现一个set的实用工具
class Sets {
    //合并两个set并且返回全向的set结合hashSet实现
    public static <T> Set<T> union(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<T>(a);
        result.addAll(b);
        return result;
    }

    public static void main(String[] args) {
//        Set<String> a = new HashSet<String>(Arrays.asList("1", "2"));
//        Set<String> b = new HashSet<String>(Arrays.asList("3", "2"));
        //都可以，这就是java的强大之处，只关注于Class可以运行
        Set<String> a = new TreeSet<String>(Arrays.asList("1", "2"));
        Set<String> b = new TreeSet<String>(Arrays.asList("3", "2"));
        Set<String> c=union(a, b);
        System.out.println(c);
        System.out.println(c.getClass().getName());
    }
}

//擦除行为的神秘之处
class Test6 {
    public static void main(String[] args) {
        Class c1 = new ArrayList<Integer>().getClass();
        Class c2 = new ArrayList<String>().getClass();
        if (c1 == c2) {
            System.out.println("true");
        }
    }
}
//擦拭
class A5<T>{
    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}

class B5<T> extends A5<T> {
}

class B6 extends A5 {
}
//不可以
//class B7 extends A5<?> {
//}
class Test1{
    public static void main(String[] args) {
        B6 b6 = new B6();
        b6.setT(1);
    }
}
//擦拭
class Generic1<T> {
}
class ArrayOfGeneric1{
    static Generic1<Integer>[] generic;

    public static void main(String[] args) {
        generic = new Generic1[100];
    }
}
//通配符
class Fruit1{

}

class Apple1 extends Fruit1 {

}

class Test9 {
//    List<Fruit1> list = new ArrayList<Apple1>();不可以我们转型的是容器而不是范型
          List<? extends Fruit1> list = new ArrayList<Apple1>();//会丢掉向里面传任何对象的能力0
//        list.add(new Apple1());
}
