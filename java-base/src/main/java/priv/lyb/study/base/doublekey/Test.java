package priv.lyb.study.base.doublekey;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * @author LiuYingBo 2021/01/12 11:46
 */
@Slf4j
public class Test {
    @org.junit.jupiter.api.Test
    public void fun1() {
        //在某个范围内的整型数值的个数是有限的，而浮点数却不是
        Double a = 100d;
        Double b = 100d;
        Double c = 200d;
        Double d = 200d;
        System.out.println(a == b);
        System.out.println(c == d);

        //Double类型比较
        Double e = 0.001;
        Double f = 0.001;
        System.out.println(e.compareTo(f));

        //Double类型比较
        BigDecimal bigDecimal1 = new BigDecimal(e);
        BigDecimal bigDecimal2 = new BigDecimal(f);
        System.out.println(bigDecimal1.compareTo(bigDecimal2));

        //Double类型比较
        System.out.println(Double.doubleToLongBits(e) == Double.doubleToLongBits(f));
    }

    @org.junit.jupiter.api.Test
    public void fun2() {
        //在某个范围内的整型数值的个数是有限的，而浮点数却不是
        Double d1 = 100d;
        Double d2 = 100d;
        Double d3 = 200d;
        Double d4 = 200d;
        log.info("d1==d2[{}]", d1 == d2);
        log.info("d3==d4[{}]", d3 == d4);
    }

    @org.junit.jupiter.api.Test
    public void fun3() {
        Double d1 = 500d;
        Double d2 = 500d;
        Double d3 = 600d;
        Double d4 = 600d;
        log.info("d1==d2[{}]", d1 == d2);
        log.info("d3==d4[{}]", d3 == d4);

        //Double类型比较
        log.info("Double.doubleToLongBits(d1)==Double.doubleToLongBits(d2)[{}]",
                Double.doubleToLongBits(d1) == Double.doubleToLongBits(d2));
    }
}
