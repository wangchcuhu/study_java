package Operator;

import java.io.*;

/**
 * @program: Java_study
 * @description: 序列化是对象可以持久保存，告诉JVM虚拟机这个类的对象我要序列化，最简单的是保存在文件当中
 * @author: liu yan
 * @create: 2021-12-21 10:58
 */
class Man implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

    public Man(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
class Person1 implements Serializable {
    private static final long serialVersionUID = 1L;

    private Man man;
    private String username;
    private transient int age;

    public Person1() {
        System.out.println("person constru");
    }

    public Person1(Man man, String username, int age) {
        this.man = man;
        this.username = username;
        this.age = age;
    }

    public Man getMan() {
        return man;
    }
    public void setMan(Man man) {
        this.man = man;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
//序列化
public class MySerializable {
    public static void main(String[] args) {
        try {
            Man man = new Man("huhx", "123456");
            Person1 person = new Person1(man, "刘力", 21);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("outPut.txt"));
            objectOutputStream.writeObject("string");
            objectOutputStream.writeObject(person);
            objectOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//反序列化
class TestSerializable{
    public static void main(String[] args) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("outPut.txt"));
            String string = (String) objectInputStream.readObject();
            Person1 person = (Person1) objectInputStream.readObject();
            objectInputStream.close();
            System.out.println(string + ", age: " + person.getAge() + ", man username: " + person.getMan().getUsername());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


