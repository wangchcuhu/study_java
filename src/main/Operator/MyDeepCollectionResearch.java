package Operator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @program: Java_study
 * @description: 容器深入研究
 * @author: liu yan
 * @create: 2021-12-27 13:38
 */
public class MyDeepCollectionResearch {

}
class StringAddress{
    private String s;

    public StringAddress(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return super.toString() + s;
    }
}

class FillingLists{
    public static void main(String[] args) {
        //Collections.nCopies填充的是同一个对象的引用
        List<StringAddress> list = new ArrayList<>(Collections.nCopies(4, new StringAddress("Hello")));
        System.out.println(list);
        Collections.fill(list, new StringAddress("World"));
        System.out.println(list);
    }
}

//享元
class Countries{
    public static final String[][] DATA = {
            //Africa
            {"1", "A"},
            {"2", "B"},
            {"3", "C"},
            {"4", "D"},
    };
}