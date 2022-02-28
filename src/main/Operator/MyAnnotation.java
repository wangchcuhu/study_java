package Operator;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @program: Java_study
 * @description: 注解
 * @author: liu yan
 * @create: 2021-12-29 11:00
 */
public class MyAnnotation {
}

class Testable{
    //执行
    public void execute() {
        System.out.println("Executing ..");
    }

    @TestAnnotation
    void testExecute() {
        execute();
    }
}

@Target(ElementType.METHOD)//注解将用在什么地方
@Retention(RetentionPolicy.RUNTIME)//该注解将在哪个级别可以使用，源代码（SOURCE）,类文件（CLASS）,运行时（RUNTIME）
@interface TestAnnotation {
}

@Target(ElementType.METHOD)//注解将用在什么地方
@Retention(RetentionPolicy.RUNTIME)//该注解将在哪个级别可以使用，源代码（SOURCE）,类文件（CLASS）,运行时（RUNTIME）
@interface UseCase {
    public int id();

    public String description() default "no description";
}

class PasswordUtils {
    @UseCase(id=47,description = "Passwords must contain at least one numeric")
    public boolean validatePassword(String password) {
        return (password.matches("\\w*\\d\\w*"));
    }
    @UseCase(id=48)
    public String encryptPassword(String Password) {
        return new StringBuilder(Password).reverse().toString();
    }
    @UseCase(id=49,description = "New passwords can not equal previously used ones")
    public boolean checkFOrNewPassword(List<String> prevPassword, String password) {
        return !prevPassword.contains(password);
    }
}

//编写注解处理器
class UseCaseTracker {
    public static void trackUseCases(List<Integer> useCases, Class<?> cl) {
        for (Method m : cl.getMethods()) {
            UseCase uc = m.getAnnotation(UseCase.class);
            //处理方法上的注解
            if (uc != null) {
                System.out.println("Found Use Case:" + uc.id() + "" + uc.description());
                useCases.remove(new Integer(uc.id()));
            }
            for (Integer useCase : useCases) {
                System.out.println("Warning:Missing use case-"+useCase);
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> useCases = new ArrayList<>();
        Collections.addAll(useCases, 47, 48, 49, 50);
        trackUseCases(useCases,PasswordUtils.class);
    }
}
//注解，告诉注解处理器你需要为我生成一个数据库表

@Target(ElementType.TYPE)//注解将用在什么地方
@Retention(RetentionPolicy.RUNTIME)//该注解将在哪个级别可以使用，源代码（SOURCE）,类文件（CLASS）,运行时（RUNTIME）
@interface DBTable{
    public String name() default "";
}
@Target(ElementType.FIELD)//注解将用在什么地方
@Retention(RetentionPolicy.RUNTIME)//该注解将在哪个级别可以使用，源代码（SOURCE）,类文件（CLASS）,运行时（RUNTIME）
//修饰JavaBean域准备的注解(约束)

@interface Constraints {
    boolean primaryKey() default false;
    boolean allowNull() default false;
    boolean unique() default false;
}
//字符串
@Target(ElementType.FIELD)//注解将用在什么地方
@Retention(RetentionPolicy.RUNTIME)//该注解将在哪个级别可以使用，源代码（SOURCE）,类文件（CLASS）,运行时（RUNTIME）
@interface SQLString{
    int value() default 0;
    String name() default "";
    Constraints constraints() default @Constraints;
}
//整数
@Target(ElementType.FIELD)//注解将用在什么地方
@Retention(RetentionPolicy.RUNTIME)//该注解将在哪个级别可以使用，源代码（SOURCE）,类文件（CLASS）,运行时（RUNTIME）
@interface SQLInteger{
    String name() default "";
    Constraints constraints() default @Constraints;
}

@interface Uniqueness {
    Constraints constraints() default @Constraints(unique = true);
}
@DBTable(name = "MEMBER")
class Member{
    @SQLString(30)
    String firstName;
    @SQLString(50)
    String lastName;
    @SQLInteger
    Integer age;
    @SQLString(value = 30,constraints = @Constraints(primaryKey = true))
    String handle;
    static int memberCount;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public String getHandle() {
        return handle;
    }
}



