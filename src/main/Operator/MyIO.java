package Operator;

import nu.xom.*;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * @program: Java_study
 * @description: I/O系统
 * @author: liu yan
 * @create: 2021-12-27 15:14
 */
public class MyIO {
}
class DirList{
    public static void main(String[] args) {
        File path = new File(".");
        String[] list;
        if (args.length == 0) {
            list = path.list();
        } else list = path.list(new DirFilter(args[0]));//回调过滤文件，提供过滤的条件
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String s : list) {
            System.out.println(s);
        }

    }
}
class DirFilter implements FilenameFilter{
    private Pattern pattern;

    public DirFilter(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    @Override

    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }
}

//目录的实用工具
final class Directory {
    public static File[] local(File dir, final String regex) {
        return dir.listFiles(new FilenameFilter(){

            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(new File(name).getName()).matches();
            }

            private Pattern pattern=Pattern.compile(regex);

        });
    }
    public static File[] local(String path, final String regex) {
        return local(new File(path), regex);
    }
}
//缓冲输入文件FileReader---字符流
class BufferedInputFile{
    public static String read(String filename) throws IOException{
            BufferedReader in = new BufferedReader(new FileReader(filename));//读取文件(使用一个缓冲)
            String s;
            StringBuilder sb = new StringBuilder();//记录数据
            while ((s = in.readLine()) != null) {
                sb.append(s + "\n");
            }
        System.out.println(sb.toString());
        return sb.toString();
    }

    public static void main(String[] args) throws IOException{
            String path = System.getProperty("user.dir");
            StringReader in = new StringReader(BufferedInputFile.read(System.getProperty("user.dir") + "/src/main/Operator/MyIO.java"));
            int c;
            while ((c = in.read()) != -1) {
                System.out.println((char) c);
            }
    }
}
//格式化的内存输入
class MemoryInput{
    public static void main(String[] args) throws IOException{
        try {
        String path = System.getProperty("user.dir");
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(BufferedInputFile.read(System.getProperty("user.dir")+"/src/main/Operator/MyIO.java").getBytes()));
        while (true) {
            System.out.println((char)in.readByte());
        } } catch (EOFException e) {
            System.out.println("End of Stream");
        }
    }
}

//基本的文件输入(缓冲输入)
class BasicFileOutput {
    static String fileName = System.getProperty("user.dir") + "\\testCopy.js";
    static String fileNameCopy = System.getProperty("user.dir") + "\\test.js";
    public static void main(String[] args) throws IOException {
        //FileWriter是实际干活的人,PrintWriter和BufferedWriter是为了提高效率做的缓冲
//        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
        //简单写法，内部帮你执行好了
        PrintWriter out = new PrintWriter(fileName);
        //第一次读取的时候已经缓存，当第二次去读的时候
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileNameCopy));
        String line;
        int counter = 0;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
            out.println(line +"//"+counter++);
        }
        //实现文件的复制
        out.close();
        bufferedReader.close();
    }
}
//存储
class StoringAndRecover{
    static String fileName = System.getProperty("user.dir") + "\\outPut.txt";
    public static void main(String[] args) throws IOException{
        //写
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
        out.writeDouble(3.12212);
        out.write(12313);
        out.writeUTF("Hello World");
        out.writeDouble(3.12212);
        out.write(12313);
        out.writeUTF("Hello World");
        out.close();
        //读

    }
}
//从标准输入中读取
class Echo{
    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = stdin.readLine()) != null && s.length() != 0) {
            System.out.println("開始輸出");
            System.out.println(s);
        }
    }
}

//將System.out 轉換成PrintWriter
class ChangeOutPut {
    public static void main(String[] args) {
        //将数据写到System.out中
        PrintWriter out = new PrintWriter(System.out, true);
        out.println("Hello World");
    }
}
//转换数据
class BufferToText{
    private static final int BSIZE = 1024;
    static String fileName = System.getProperty("user.dir") + "\\outPut.txt";

    public static void main(String[] args) throws IOException{
        FileChannel fc = new FileOutputStream(fileName).getChannel();
        //写入
        fc.write(ByteBuffer.wrap("Some text".getBytes()));
        fc.close();
        //读出
        fc = new FileInputStream(fileName).getChannel();
        ByteBuffer buff = ByteBuffer.allocate(BSIZE);
        fc.read(buff);
        buff.flip();
    }
}

//对象的序列化
class Data implements Serializable {
    private int n;

    public Data(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return Integer.toString(n);
    }
}

class Worm implements Serializable {
    private static Random random = new Random(47);
    private Data[] d = {new Data(random.nextInt(10)),new Data(random.nextInt(10)),new Data(random.nextInt(10))};
    private Worm next;
    private char c;

    public Worm() {
        System.out.println("Default Constructor");
    }

    public Worm(int i, char x) {
        System.out.println("Default Constructor" + i);
        this.c = x;
        if (--i > 0) {
            next = new Worm(i, (char) (x + 1));
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(":");
        result.append(c);
        result.append("(");
        for (Data data : d) {
            result.append(data);
        }
        result.append(")");
        if (next != null) {
            result.append(next);
        }
        return result.toString();
    }

    public static void main(String[] args) throws IOException,ClassNotFoundException{
        Worm w = new Worm(6, 'a');
        System.out.println("w" + w);
    }
}

//寻找类
class Alien implements Serializable {
    private int a;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}
//在X.file中存一个对象
class FreezeAlien {
    public static void main(String[] args) throws IOException{
        ObjectOutput out = new ObjectOutputStream(new FileOutputStream("X.file"));
        Alien alien = new Alien();
        alien.setA(5);
        out.writeObject(alien);
    }
}
//获取X.file中的对象
class ThawAlien{
    public static void main(String[] args) throws Exception{
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(new File( "X.file")));
        Object mystery = in.readObject();
        System.out.println(mystery.getClass());
    }
}
//序列化的控制
class Blip1 implements Externalizable{
    public Blip1(){
        System.out.println("Blip1 Constructor");
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("Blip1.writeExternal");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("Blip1.readExternal");

    }
}
class Blip2 implements Externalizable{
    public static int a=6;
    public int b=8;
    public Blip2(){
        System.out.println("Blip1 Constructor");
    }
    public Blip2(int b){
        this.b=b;
        System.out.println("Blip1 Constructor");
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        //使用中间变量存储对象中的变量的值
        out.writeInt(b);
        System.out.println("Blip12.writeExternal");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        //在创建对象的时候把保存的值在付给新创建的对象
        this.b = in.readInt();
        System.out.println("Blip12.readExternal");

    }
}
//完整的读写变量
class Blips{
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("start");
        Blip1 blip1 = new Blip1();
        Blip2 blip2 = new Blip2(10);
        blip2.a=7;
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("Blips.out"));
        System.out.println("save Objects");
        o.writeObject(blip1);
        o.writeObject(blip2);
        o.close();
        //读取(对象的恢复实际上是重新创建对象)
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Blips.out"));
        System.out.println("b1 Recovering");
        blip1 = (Blip1) in.readObject();
        System.out.println("b2 Recovering");
        blip2 = (Blip2) in.readObject();
        System.out.println(blip2.b);
    }
}
//transient 瞬时序列化
class Logon implements Serializable{
    private String name;
    private transient String pwd;

    public Logon(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "Logon{" +
                "name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    public static void main(String[] args) throws Exception {
        Logon a = new Logon("Kitty", "KittyCss");
        System.out.println("logon a" + a);
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("Logon.out"));
        o.writeObject(a);
        o.close();
        TimeUnit.SECONDS.sleep(1);
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("Logon.out"));
        System.out.println("Recovering Object at " + new Date());
        a = (Logon) in.readObject();
        System.out.println(a);
    }
}

//XML
class Person8{
    private String name, age;
    public Person8(String name, String age) {
        this.name = name;
        this.age = age;
    }
    //提供xml
    public Element getXML() {
        Element person = new Element("person");
        Element name = new Element("name");
        name.appendChild(this.name);
        Element age = new Element("age");
        age.appendChild(this.age);
        person.appendChild(name);
        person.appendChild(age);
        return person;
    }
    //反序列化提供对象
    public Person8(Element person) {
        name = person.getFirstChildElement("name").getValue();
        age = person.getFirstChildElement("age").getValue();
    }

    public static void format(OutputStream os, Document doc) throws Exception {
        Serializer serializer = new Serializer(os, "ISO-8859-1");
        serializer.setIndent(4);
        serializer.setMaxLength(60);
        serializer.write(doc);
        serializer.flush();
    }

    public static void main(String[] args)throws Exception {
        List<Person8> list = Arrays.asList(new Person8("liu", "22"), new Person8("zhang", "23"));
        Element root = new Element("people");
        for (Person8 person8 : list) {
            //返回各自的xml对象(对象内部提供这个方法)
            root.appendChild(person8.getXML());
        }
        Document document = new Document(root);
        format(System.out, document);
        //将xml文档写到people.xml文件中
        format(new BufferedOutputStream(new FileOutputStream("people.xml")),document);
    }
}

//从xml文档中反序列化Person对象
class People extends ArrayList<Person8> {
    public People(String filename) throws Exception{
        //根据文件的路径创建Document对象
        Document document = new Builder().build(filename);
        Elements elements = document.getRootElement().getChildElements();
        for (int i = 0; i < elements.size(); i++) {
            add(new Person8(elements.get(i)));
        }
    }

    public static void main(String[] args) throws Exception{
//        String fileName = System.getProperty("user.dir") + "/people.xml";
        //地址有问题
//        People p = new People("people.xml");
//        System.out.println(p);
    }
}








