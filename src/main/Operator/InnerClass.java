package Operator;

/**
 * @program: Java_study
 * @description: 内部类
 * @author: liu yan
 * @create: 2021-12-16 19:38
 */
public class InnerClass {
    //类名加this获取对象的引用
    InnerClass getThis(){
        return InnerClass.this;
    };
    //内部類有權限對於外圍對象的所偶成員的所有成員
    private class A{
        public int getI(){
            return i;
        };
    };
    public A getA(){
        return new A();
    };
    private int i=1;
    public static void main(String[] args) {
//        System.out.println(i);对于类的内部访问必须通过对象，除了static这种。
        InnerClass innerClass=new InnerClass();
        System.out.println(innerClass.getThis().getA().getI());
    }
}
//接口一定要是可访问的，不然没办法创建引用
interface B{
    String getLabel();
};
//方法内部的私有类
class Parcels{

    public B getAClass(){
         class A implements B {
         private String label= "111";
         public String getLabel(){
             return label;
         };
        };
        return  new A();
    }
    public static void main(String[] args) {
          Parcels parcels =new Parcels();
          B b=parcels.getAClass();
        System.out.println(b.getLabel());
    }
}
//创建匿名类，并结继承自接口k，然后向上转型
interface K{
   void A();
   void B();
}
class Parcel1{
    public K getKClass(){
        return new K () {
            @Override
            public void A() {
                System.out.println("A");
            }
            @Override
            public void B() {
                System.out.println("B");
            }
        };
    }
    public static void main(String[] args) {
        K k =(new Parcel1()).getKClass();
        k.A();
        k.B();
    }
}

