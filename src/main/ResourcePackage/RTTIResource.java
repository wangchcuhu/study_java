package ResourcePackage;

/**
 * @program: Java_study
 * @description: RTTI引入文件
 * @author: liu yan
 * @create: 2021-12-24 10:48
 */
public class RTTIResource {
    public void f() {
        System.out.println("RTTIResource.f()");
    }
    public static RTTIResource getRTTIResource() {
        return C.makeRTTIResource();
    }
     //返回值
    public Object w() {
        return null;
    }
}
//C的内部隐藏了实现的细节
class C extends RTTIResource {
    private int k = 2;
     public void f() {
         System.out.println("C.f()");
     }
    public void g() {
        System.out.println("C.g()");
    }
    public Object w() {
       return k;
    }
    private void k() {
        System.out.println("C.k()");
    }
     static RTTIResource makeRTTIResource(){
        return new C();
    }
}