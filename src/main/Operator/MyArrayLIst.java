package Operator;
/*
说明：关于随意调整数组的list接口的实现,实现了所有的可选的list操作,允许所有的元素包括null,
此外为了实现list接口，这个类提供方法来操作数组的大小,这个数组目的是为了存储list,这个类大致
相当于Vector,但是他是不同步的
*/

import java.util.AbstractList;

/**
 * @program: Java_study
 * @description: 实现一个arraylist
 * @author: liu yan
 * @create: 2021-12-01 21:52
 */
public class MyArrayLIst<E>extends AbstractList
{

    @Override
    public Object get(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
