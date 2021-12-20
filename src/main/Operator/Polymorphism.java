package Operator;

/**
 * @program: Java_study
 * @description: 多态
 * @author: liu yan
 * @create: 2021-12-16 13:25
 */
public class Polymorphism {




    public static void main(String[] args) {
         Useful [] arr={
                 new MoreUseful(),
                 new Useful()
         };
         arr[0].g();
         arr[1].g();
        ((MoreUseful)arr[0]).k();
    }
}

class Useful {
    void g(){
        System.out.println("g");
    };
    void f(){
        System.out.println("f");
    };
}

class MoreUseful extends Useful{
    void g(){
        System.out.println("g");
    };
    void f(){
        System.out.println("f");
    };
    void k(){
        System.out.println("k");
    };
    void m(){
        System.out.println("m");
    };
}