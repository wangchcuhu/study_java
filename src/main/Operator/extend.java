package Operator;

/**
 * @program: Java_study
 * @description: 继承
 * @author: liu yan
 * @create: 2021-12-01 20:37
 */
public class extend {

}

class Art{
    Art(){
        System.out.println("Art Constructor");
    }
}

class Drawing extends Art{
    Drawing(){
        System.out.println("Drawing Constructor");
    }
    void get(){
        System.out.println("Drawing");
        return;
    };
}

class Cartoon extends Drawing{
    Cartoon(){
        System.out.println("Cartoon Constructor");
    }
    public static void main(String[] args) {
        Cartoon cartoon = new Cartoon();
        cartoon.get();
    }
}
