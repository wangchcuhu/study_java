package Operator;

import java.util.*;

/**
 * @program: Java_study
 * @description: 数组
 * @author: liu yan
 * @create: 2021-12-27 08:05
 */
public class MyArray {
    public static void main(String[] args) {
        //数组是效率最高的存储方式(ArrayList内部实际上就是一个空的大小为10的数组Object)
        List list = new ArrayList<>();
        System.out.println(list.size());
        Object[] objectsArr = new Object[10];
        System.out.println(objectsArr);
        objectsArr[0] = "1";
        System.out.println(objectsArr[0]);
    }
}

//成员
class member {
    private static long counter;
    private final long id = counter++;
    @Override
    public String toString() {
        return "member" + " "+id;
    }
}
class ContainerComparison{
    public static void main(String[] args) {
        member[] classRoom = new member[50];
        //add member for each item
        for (int i = 0; i < classRoom.length; i++) {
            classRoom[i] = new member();
        }
        System.out.println(Arrays.toString(classRoom));
        System.out.println(classRoom[4]);
        //List
        List<member> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(new member());
        }
        System.out.println(list);
        System.out.println(list.get(4));

        int[] integers = {1, 2, 3, 4, 5, 6};
        System.out.println(Arrays.toString(integers));
        System.out.println(integers[4]);

        List<Integer> IntegerList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6
        ));
        System.out.println(IntegerList);
        System.out.println(IntegerList.get(4));
    }
}
//多维数组
class DWArray{
    public static void main(String[] args) {
        int[][] a = {{1, 2, 3}, {1, 2, 3}};
        System.out.println(Arrays.deepToString(a));
    }
}

//数组与泛型
class ArrayOfGenerics{
    public static void main(String[] args) {
        List<String>[] a;
        List[] b = new List[10];
        a = (List<String>[]) b;
        a[0] = new ArrayList<>();
        //一旦变的实际则就是在编译器中进行检查，且允许存储子类但是只能使用积累的方法和属性
//      a[1] = new ArrayList<Integer>();
    }
}

//在类或者方法内部，擦除通常会使泛型变的不可用
class ArrayOfGenericType<T>{
    T[] array;
    @SuppressWarnings("unchecked")//编译期间会有警告，因为他也不确定是否能够转型成功
    public void f(int i) {
        array = (T[]) new Object[i];
    }

    public static void main(String[] args) {
        //some good functions
        String[] A1 = new String[10];
        Arrays.fill(A1,"6");
        System.out.println(Arrays.toString(A1));
    }
}

class CopyClass{
    public static void main(String[] args) {
        int[] a = new int[10];
        Arrays.fill(a, 1);
        int[] b = new int[10];
        System.arraycopy(a,0,b,0,10);
    }
}

//实现Comparable和使用Array.sorts进行比较
class CompType implements Comparable {
    int i;
    int j;
    private static long count = 1;

    public CompType(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override

    public int compareTo(Object o) {
        return 0;
    }

    public static void main(String[] args) {
        String[] a = new String[]{"a", "b", "n", "c"};
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
        //倒叙
        Arrays.sort(a, Collections.reverseOrder());
        System.out.println(Arrays.toString(a));
        int[] b = new int[]{1, 4, 2, 9};
        Arrays.sort(b);
        System.out.println(Arrays.toString(b));
        //倒叙
    }
}



