package priv.lyb.study.jdk8new.interfacefunction;

/**
 * @author LiuYingBo 2021/01/11 16:19
 */
public interface A {
    int i = 0;
    static void staticFunB(){
        System.out.println("staticFunB - " + i);
    }
    static void staticFunA(String a) {
        System.out.println("static FunA - " + a);
    }

    default void defaultFunA(String a) {
        System.out.println("default funA - " + a);
    }

    default void defaultFunB(String a) {
        System.out.println("default funB - " + a);
    }

    void fun();
}
