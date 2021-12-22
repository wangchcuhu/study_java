package Operator;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.text.Format;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: Java_study
 * @description: 字符串实际上是常量，由数组存储的char组装，所以不可变，传递的引用都是复制
 * @author: liu yan
 * @create: 2021-12-22 08:34
 */
public class MyString {
    public static String upCase(String s) {
        System.out.println("s "+s.hashCode());
        //验证传递的是引用的复制
        return s.toUpperCase();
    }

    public static void main(String[] args) {
        String q = "howdy";
        System.out.println(q);
        //普通的函数传递的还是同一个地址，是字符串内部返回的是一个复制
        String qq = upCase(q);
        System.out.println(q);
        System.out.println(qq);
        //验证传递的是引用的复制
        System.out.println("q "+q.hashCode());
        System.out.println("qq "+qq.hashCode());
    }
}
//+操作符的重写
class Concatenation{
    public static void main(String[] args) {
        String mango = "mango";
        String s = "abc" + "mango" + "def" + 47;
        System.out.println(s);
    }
}
//使用StringBuilder来凭借字符串
class UsingStringBuilder{
    public static Random random = new Random(47);
    public String toString() {
        StringBuilder res = new StringBuilder("[");
        for (int i = 0; i < 25; i++) {
            res.append(random.nextInt(100));
            res.append(". ");
        }
        res.delete(res.length() - 2, res.length());
        res.append("]");
        return res.toString();
    }

    public static void main(String[] args) {
        UsingStringBuilder usingStringBuilder = new UsingStringBuilder();
        System.out.println(usingStringBuilder);
    }
}





//当你在List中存了一些对象并且打印了整个list,实际上list的调用toString时循环调用了每个对象的toString
class InfiniteRecursion {
    @Override
    public String toString() {
        return "InfiniteRecursion address:"+super.hashCode();
    }

    public static void main(String[] args) {
        List<InfiniteRecursion> list = new ArrayList<InfiniteRecursion>();
        for (int i = 0; i < 25; i++) {
            list.add(new InfiniteRecursion());
        }
        System.out.println(list);
    }
}

//将内容输出到错误的地点
class Turtle{
    private String name;
    private Formatter f;

    public Turtle(String name, Formatter f) {
        this.name = name;
        this.f = f;
    }

    public void move(int x, int y) {
        f.format("%d The Turtle is at (%d,%d)\n",name,x,y);
    }

    public static void main(String[] args) {
        PrintStream printStream = System.out;
        Turtle turtle = new Turtle("Tommy", new Formatter(printStream));
        turtle.move(1,1);
    }
}
//String.format输出格式化的字符串或者信息
class MyFormatterString{
    public String f1(int x,int y,String name ){
        return String.format("(%d,%d) is the location of %s", x, y, name);
    }
    public static void main(String[] args) {
        MyFormatterString v = new MyFormatterString();
        System.out.println(v.f1(1, 1, v.getClass().toString()));

    }
}

//一些简单的正则表达式
class IntegerMatch {
    public static void main(String[] args) {
        //可能有一个-开头，后跟着一个或者多个数字
        String m = "-?\\d+";
        System.out.println("-123".matches(m));
        //可能有一个-或者+开头，后跟着一个或者多个数字(+在正则中具有特殊的含义，所以需要变成普通字符加上\\)
        String m1 = "(-|\\+)?\\d+";
        System.out.println("+123".matches(m1));
    }
}

//给你一个字符串，然后输出里面的字符
class Finding{
    public static void f(String s) {
        Matcher m = Pattern.compile("\\w").matcher(s);
        while (m.find()) {
            System.out.println(m.group() + "1");
//            i++;
        }
    }
    public static void main(String[] args) {
        f("Hello World");
    }
}
//将一个字符串按照给定的字符串阶段
class Pattern1{
    public static void main(String[] args) {
        Pattern p = Pattern.compile("!!");
        //按照！！将字符串断句
        String s = "Hello!! World!!";
        String[] res= p.split(s);
        for (String item : res) {
            System.out.println(item);
        }
    }
}
class replacePattern{
    static void f() {
        String s = "Hello!! World!!";
        Matcher m = Pattern.compile("").matcher(s);
        while (m.find()) {
            System.out.println(m.group());
        }
    }
    public static void main(String[] args) {
        f();
    }
}

//扫描输入
class SimpleRead {
    public static BufferedReader input = new BufferedReader(new StringReader("Sir Robin of Camelot" +
            "\n22 1.61803"));

    public static void main(String[] args) {
        try {
            System.out.println("What is your name");
            String name = input.readLine();
            System.out.println("How old are you?,What is your favourite double");
            System.out.println("input:<age><double>");
            String numbers = input.readLine();
            String[] str = numbers.split(" ");
            Integer age = Integer.valueOf(str[0]);
            Double favorite = Double.valueOf(str[1]);
            System.out.format("Hi %s.\n", name);
            System.out.format("Hi %d.\n", age);
            System.out.format("Hi %f.\n", favorite);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class BetterRead {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(SimpleRead.input);
        System.out.println("What is your name");
        String name = scanner.nextLine();
        System.out.println("How old are you?,What is your favourite double");
        System.out.println("input:<age><double>");
        Integer age = scanner.nextInt();
        Double favorite = scanner.nextDouble();
        System.out.format("Hi %s.\n", name);
        System.out.format("Hi %d.\n", age);
        System.out.format("Hi %f.\n", favorite);
    }
}

//界定符
class ScannerDelimiter{
    public static void main(String[] args) {
        Scanner scanner = new Scanner("12,12,12,2,321,3,1");
        scanner.useDelimiter("\\s*,\\s*");
        while (scanner.hasNextInt()) {
            System.out.println(scanner.nextInt());
        }
    }
}









