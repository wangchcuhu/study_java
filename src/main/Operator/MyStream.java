package Operator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @program: Java_study
 * @description: 流
 * @author: liu yan
 * @create: 2021-12-24 16:59
 */
public class MyStream {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        Stream<Integer> stream = list.stream();
    }
}
