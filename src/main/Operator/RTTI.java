package Operator;

import ResourcePackage.RTTIResource;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @program: Java_study
 * @description: 运行状态
 * @author: liu yan
 * @create: 2021-12-22 16:55
 */
public class RTTI {
}
class A1{
    static {
        System.out.println("Loading A1");
    }
}
class A2{
    static {
        System.out.println("Loading A2");
    }
}
class A3{
    static {
        System.out.println("Loading A3");
    }
}
class SweetShop{
    public static void main(String[] args) {
        System.out.println("inside main");
        new A1();
        System.out.println("After Creating A1");
        try {
            //书上说这里会触发A2这个类的加载,但是没有触发(解决需要全限定包名)
            Class.forName("Operator.A2");
        } catch (ClassNotFoundException e) {
            System.out.println("Could find A2");
        }
//        new A2();
    }
}

//电池
interface  HasBatteries {
}
//WaterProof
interface WaterProof {

}
interface Shoots {
}
class Toy {
    Toy() {}
    Toy(int i) {}
}

class Fancy extends Toy implements HasBatteries,WaterProof,Shoots{
    Fancy(int i) {
        super(i);
    }
}
//Class.Format()和类名.class产生Class对象的不同点在于是否立即对使用类初始化
class ToyTest {
    public static void main(String[] args) {
        //Loading Class
        Class c = null;
//        try {
//            c = Class.forName("Operator.Fancy");
//        } catch (ClassNotFoundException e) {
//            System.exit(1);
//        }
        //使用类字面量替代,由于是在编译时进行检查,所以就不需要try/catch
        c = Fancy.class;
        System.out.println(c);
        //test Class of interface
        for (Class i : c.getInterfaces())
            System.out.println(i);
            //get SuperClass of FancyToy (Toy)
            Class Super = c.getSuperclass();
            Object obj = null;
            //使用Class来创建超类的实力对象(because we has get Class of SuperClass by getSuperclass())
            try {
                obj = Super.newInstance();
            } catch (InstantiationException e) {
                System.exit(1);
            } catch (IllegalAccessException e) {
                System.exit(1);
            }
            //native 生成
            System.out.println(obj.getClass());
    }
}

//Class的泛型仅仅是为了编译检查
class GenericClass {
    public static void main(String[] args) {
        Class publicClass = Integer.class;
        System.out.println(publicClass);
        publicClass =Double .class;
        System.out.println(publicClass);
        //泛型，为了编译器强制检查
        Class<Integer> integerClass =Integer.class;//假设泛型参数Integer换成Number,无法工作
        //变通版本
        Class<? extends Number> k=int.class;
        k = double.class;
//        integerClass =Double.class;错误
    }
}
class Dog111 {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
//将一种类的对象塞满List
class FilledList{
    private List list = new ArrayList();

    public List f(int i,Class c) {
        for (int j = 0; j < i; j++) {
            try {
                list.add(c.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    public static void main(String[] args) {
        FilledList filledList = new FilledList();
        List list=filledList.f(15, Dog111.class);//里面的类并没有初始化
        System.out.println(list);
    }
}
//将一种类的对象塞满List
class FilledList1<T>{
    private List<T> list = new ArrayList<T>();
    private Class<T> c;

    FilledList1(Class<T> type) {
        this.c=type;
    }
    public List f(int i) {
        for (int j = 0; j < i; j++) {
            try {
                list.add(c.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
    public static void main(String[] args) {
        FilledList1<Dog111> filledList = new FilledList1<Dog111>(Dog111.class);
        List list=filledList.f(15 );//里面的类并没有初始化
        System.out.println(list);
    }
}
//超类的newInstance是Object
class SuperClass{
    public static void main(String[] args) {
        Class<Fancy> fancyClass = Fancy.class;
        Class<? super Fancy> ToyClass = fancyClass.getSuperclass();
        try {
            Toy toy = (Toy) ToyClass.newInstance();//这返回的是Object ,需要强转
            System.out.println(ToyClass.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
//对象是不是某个特定类型的实例(超类也是)
class isInstance{
    public static void main(String[] args) {
        Fancy fancy = new Fancy(1);
        if (fancy instanceof Fancy) {
            System.out.println("Fancy");
        }
        if (fancy instanceof Toy) {
            System.out.println("Toy");
        }
    }
}
//工厂设计模式
interface Factory1 <T>{
    T creat();
}


class Part {
    public String toString() {
        return getClass().getSimpleName();
    }

    //创建一个存储的地方(Class)
    static List<Factory1<? extends Part>> partFactories = new ArrayList<Factory1<? extends Part>>();
    //初始化的时候调用
    static {
        partFactories.add(new FuelFilter.Factory());//存自己实现的工厂对象，里面是我们这个对象的实例
    }
}
//中间过渡状态
class Filter extends Part   {
}

class FuelFilter extends Filter {
    //在开始的时候每个工厂里面都可以返回自身的实力
    public static class Factory implements Factory1<FuelFilter> {
        @Override
        public FuelFilter creat() {
            return new FuelFilter();
        }
    }
}

//反射(可以通过Class对象获取Constructor和Method)
class ShowMethods {
    private static String usage="usage:\n"+"ShowMethods A1.class.name world\n"+"AAAA\n";
    private Pattern pattern = Pattern.compile("\\w+\\.");

    public static void main(String[] args) {
        if (args.length<1) {
            System.exit(0);
        }
        int lines = 0;
        try {
            //find Class of input
            Class<?> c = Class.forName(args[0]);
            Method[] methods = c.getMethods();
            Constructor[] ctors = c.getConstructors();
        } catch (Exception e) {

        }
    }
}

//代理(用接口可以轻松实现代理)
interface Interface {
    void doSomething();
}

class RealObject implements Interface {

    @Override
    public void doSomething() {
        System.out.println("RealObject");
    }
}
//用接口可以轻松实现代理
class ProxyRealObject implements Interface {
    private RealObject realObject;
    ProxyRealObject(RealObject realObject) {
        this.realObject = realObject;
    }
    @Override
    public void doSomething() {
        System.out.println("额外的动作");
        realObject.doSomething();
    }
}
class useTest{
    public static void consumer(Interface interlace){
        interlace.doSomething();
    }
    public static void main(String[] args) {
     consumer(new RealObject());
     consumer(new ProxyRealObject(new RealObject()));
    }
}

//空对象的思想
class Person3 {
    private String name;
    Person3() {
    }
    Person3(String name) {
        this.name = name;
    }
    public static  class NullPerson extends Person3{
        NullPerson() {
            super();
        }
        NullPerson(String name) {
            super(name);
        }
    }

    public static final Person3 Null = new NullPerson();
}
//接口的解耦合并不是万能的
interface A4{
    void f();
}

class C4 implements A4 {
    @Override
    public void f() {
        System.out.println("f");
    }
    void g() {
        System.out.println("g");
    }
}

class Test4 {
    public static void main(String[] args) {
        A4 c = new C4();
        c.f();
        c.getClass().getName();
        if (c instanceof C4) {
            ((C4) c).g();
        }
    }
}

//接口与类型信息
class Test5 {
    public static void main(String[] args) {
        RTTIResource c = RTTIResource.getRTTIResource();
        c.f();
        //在知道方法名的情况下可以强行调用(反射的原理，已经获取了Class)
        try {
            callHiddenMethods(c, "g");
            callHiddenMethods(c, "f");
            callHiddenMethods(c, "w");
            //运用反射的原理甚至连私有的方法和属性都可以调用
            callHiddenMethods(c, "k");
            System.out.println(c.w());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void callHiddenMethods(Object o,String s)throws Exception {
        Method g = o.getClass().getDeclaredMethod(s);
        g.setAccessible(true);
        g.invoke(o);
    }
}




