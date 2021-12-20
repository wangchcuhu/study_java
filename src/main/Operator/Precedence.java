package Operator;
import java.util.*;
/**
 * @program: Java_study
 * @description:
 * @author: liu yan
 * @create: 2021-11-11 18:42
 */
public class Precedence {
    public static void main(String[] args) {
        ArrayList arrayList=new ArrayList(10);
/*
        int a=1,b=2,c=3;
        int d=c+a*3-b;
        int e=a/b;
        double f=a/b;
*/
        //编写一个计算速度的程序，他所使用的距离和时间都是常量(距离300m，时间5s)
//        double V=getV(300,2.3);
        //双精度数需要后缀,不添加f会提示错误
//        float f=1e-43f;
        //自动转化int====》double
        double A1=1.2;
        int A2=3;
        double c=A2/A1;
    }

    private static double getV(double i, double v) {
        return i/v;
    }
}