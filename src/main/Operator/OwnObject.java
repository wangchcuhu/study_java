package Operator;
import java.util.*;

/**
 * @program: Java_study
 * @description: 持有对象
 * @author: liu yan
 * @create: 2021-12-17 10:17
 */
public class OwnObject {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        ArrayList<Apple> apples =new ArrayList();
        for (int i=0;i<3;i++){
//            apples.add(new Apple());
//            apples.add(new Orange());
        }
//        for (int i=0;i<apples.size();i++){
//            ((Apple)apples.get(i)).getId();
//        }
           for (Apple c:apples){
               c.getId();
           }
    }
}
class Apple{
    private static long counter;
    private final long id=counter++;
    public long getId(){
        System.out.println(id);
        return id;
    }
}
class Orange{};

//collection
class CollectionTest{
    public static void main(String[] args) {
        Collection<Integer> c =new ArrayList<Integer>();
        for (int i=0;i<10;i++){
           c.add(i) ;
        }
        for (int i:c){
            System.out.println(i);
        }
    }
}
//
//class Snow{}
//class Power extends Snow{}
//class Light extends Power{}
//class Heavy extends Power{}
//class AsListInference{
//    public static void main(String[] args) {
//        List<Snow>snow1= Arrays.asList(new Power(),new Light(),new Heavy());
//    }
//}

//Collection和Map两种容器的比较
class PrintContainers{
    static Collection fill(Collection<String> collection){
        collection.add("rat");
        collection.add("dog");
        collection.add("dog");
        collection.add("rabbit");
        return collection;
    }
    static Map fill(Map<String,String> map) {
        map.put("rat", "Fuzzy");
        map.put("rat", "Spot");
        map.put("cat", "Fuzzy");
        return map;
    }

    public static void main(String[] args) {
        System.out.println(PrintContainers.fill(new ArrayList<String>()));
        System.out.println(PrintContainers.fill(new LinkedList<String>()));
        System.out.println(PrintContainers.fill(new TreeSet<String>()));
        System.out.println(PrintContainers.fill(new HashMap<String, String>()));
        System.out.println(PrintContainers.fill(new TreeMap<String,String>()));
    }
}
//List
class ListFeature{

    public static void main(String[] args) {
        Random random = new Random(47);
        List<Integer> list = Arrays.asList(1, 2, 3, 4,5,5);
        System.out.println(list.indexOf(5));

    }
}
//stack
class Stack<T>{
    private LinkedList<T> storage = new LinkedList<T>();

    public void push(T v) {
        storage.addFirst(v);
    }
    //返回並刪除第一個元素
    public T pop() {
        return storage.removeFirst();
    }
    public T peek() {
        return storage.getFirst();
    }
    public String toString() {
        return storage.toString();
    }

    public boolean isEmpty() {
       return storage.isEmpty();
    }
    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        System.out.println(stack.toString());
        stack.pop();
        System.out.println(stack.toString());
    }
}
//set
    //一千个在0-29的随机数被存储在set中
class SetOfInteger{
    public static void main(String[] args) {
        Random random = new Random(47);
        Set<Integer> storage = new TreeSet<Integer>();
        for (int i = 0; i < 1000; i++) {
            storage.add(random.nextInt(30));
        }
        System.out.println(storage);
    }
}
//map
    //验证Random的正太随随机分布
class MapOfRandom{
    public static void main(String[] args) {
        Map<Integer, Integer> storage = new HashMap<Integer, Integer>();
        Random random = new Random(47);
        for (int i = 0; i < 100000; i++) {
            int r = random.nextInt(20);
            Integer res = storage.get(r);//常量不能爲null,但是對象可以
            storage.put(r, res == null ? 1 : res + 1);
        }
        System.out.println(storage);
    }

}
//Map<Person,<?extends Pet>>
class Pet{
    Pet(String name) {
        setName(name);
    }
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void play() {

    }

    @Override
    public String toString() {
        return name;
    }
}
class Cat extends Pet{
    Cat(String name) {
        super(name);
    }

    @Override
    public void play() {
        super.play();
        System.out.println("lazy");
    }
}
class Dog extends Pet{
    Dog(String name) {
        super(name);
    }

    @Override
    public void play() {
        super.play();
        System.out.println("Lively");
    }
}
class Person{
    Person(String name) {
        setName(name);
    }
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
class MapOfList{
    public static void main(String[] args) {
        Map<Person, List<? extends Pet>> storage = new HashMap<Person, List<? extends Pet>>();
        storage.put(new Person("小雷"), Arrays.asList(new Cat("cat1"), new Dog("dog1")));
        storage.put(new Person("小点"), Arrays.asList(new Cat("cat2"), new Dog("dog2")));
        //返回键
        System.out.println(storage.keySet());
        //返回值
        System.out.println(storage.values());
        //返回键-值
        for (Person person : storage.keySet()) {
            System.out.println((person.getName() +"="+ storage.get(person).toString()));
        }
        System.out.println(storage);
    }
}
//Queue
class QueueDemo{
    public static void printQ(Queue queue){
        while (queue.peek() != null) {
            System.out.println(queue.remove());
//            System.out.println();
        }
    }
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<Integer>();
        Random random = new Random(47);
        for (int i = 0; i < 10; i++) {
            queue.offer(random.nextInt(i+10));
        }
        System.out.println(queue);
        Queue<Character> qc = new LinkedList<Character>();
        for (char c : "Brontosaurus".toCharArray()) {
            qc.offer(c);
        }
        System.out.println(qc);
    }
}
//PriorityQueue(優先隊列)
class PriorityQueueDemo{
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        Random random = new Random(47);
        for (int i = 0; i < 10; i++) {
            queue.offer(random.nextInt(i+10));
        }
//        QueueDemo.printQ(queue);
        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 6, 7, 1, 2, 4);
        PriorityQueue<Integer> queue1 = new PriorityQueue<Integer>(ints);
//        QueueDemo.printQ(queue1);//自動排列(字典正序)
        queue1 = new PriorityQueue<>(ints.size(), Collections.reverseOrder());
        queue1.addAll(ints);
//        QueueDemo.printQ(queue1);//自動排列(字典倒敘)
        //將Hello World排序
        String res = "HELLO WORLD";
        String[] res1=res.split("");
        List<String> list= Arrays.asList(res1);
        PriorityQueue<String> queue2 = new PriorityQueue<String>(list);
//        QueueDemo.printQ(queue2);//自動排列(字典正序)
        queue2 = new PriorityQueue<>(list.size(), Collections.reverseOrder());
        queue2.addAll(list);
//        QueueDemo.printQ(queue2);//自動排列(字典倒敘)
        //去除多餘的數字
        Set<String> storage = new TreeSet<String>(queue2);//queue2本身是是實現了collection，所以TreeSet可以接收queue2
        System.out.println(storage);
    }
}

//一個簡單繼承自Object的簡單類，是否能夠添加到PriorityQueue有序队列中
class A{

}
class TestQueue{
    public static void main(String[] args) {
        //不可以Operator.A cannot be cast to java.lang.Comparable
//        PriorityQueue<A> priorityQueue = new PriorityQueue<A>(
//            Arrays.asList(new A(), new A())
//        );
//        System.out.println(priorityQueue);
    }
}
class PetP{
    public int id(){
        return hashCode();//直接调用自生的方法不需要this,但是调用内部的类似Object的时候需要用到PetP.this.hasCode()
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
class  InferenceOfIterator{
    public static void display(Iterator<PetP> iterator) {
        while (iterator.hasNext()) {
            PetP p = iterator.next();
            System.out.println(p.id());
        }
    }
    public static void display(Collection<PetP> collection) {
        for (PetP p : collection) {
            System.out.println(p.id());
        }
    }

    public static void main(String[] args) {
        List<PetP> storage = Arrays.asList(new PetP(),new PetP());
        InferenceOfIterator.display(storage);
        InferenceOfIterator.display(storage);
    }
}
//iterator
class MyIterator implements Iterator<String>{
    protected String[] words =("Hello World").split("");
    private int index = 0;
    //这里参照书上的写法不可以被foreach访问
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < words.length;
            }
            @Override
            public String next() {
                return words[index++];
            }
        };
    }

    @Override
    public boolean hasNext() {
        return index < words.length;
    }

    @Override
    public String next() {
        return words[index++];
    }

    public static void main(String[] args) {
        MyIterator myIterator = new MyIterator();
        for (MyIterator it = myIterator; it.hasNext(); ) {
            System.out.println(it.next());
        }
    }
}
//iterator 迭代器的多种行为
class ReversibleArrayList<T> extends ArrayList<T>{
    ReversibleArrayList(Collection<T> T) {
        super(T);
    }
    public Iterator<T> reserved(){
        return new Iterator<T>() {
            private int index = size()-1;
            @Override
            public boolean hasNext() {
                return index>-1;
            }

            @Override
            public T next() {
                return get(index--);
            }
        };
    }

    public static void main(String[] args) {
        ReversibleArrayList<String> storage = new ReversibleArrayList(Arrays.asList("1", "2", "3", "4", "5", "6"));
        for (String s : storage) {
            System.out.println(s);
        }
        System.out.println();
        //实现倒叙输出
        for (Iterator<String> it = storage.reserved(); it.hasNext(); ) {
            String s = it.next();
            System.out.println(s);
        }
    }
}




















