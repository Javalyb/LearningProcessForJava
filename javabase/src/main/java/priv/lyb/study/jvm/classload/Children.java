package priv.lyb.study.jvm.classload;

/**
 * @author LiuYingBo 2021/1/5 下午12:21
 */
public class Children extends Parent {
    static int b = method2();

    static {
        System.out.println("(6)");
    }

    int a = method1();

    {
        System.out.println("(7)");
    }

    Children() {
        System.out.println("(8)");
    }

    static int method2() {
        System.out.println("(10)");
        return 1;
    }

    int method1() {
        System.out.println("(9)");
        return 1;
    }
}
