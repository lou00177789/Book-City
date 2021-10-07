package com.liu.test;

import com.liu.pojo.Cart;

import java.beans.Customizer;
import java.math.BigDecimal;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author liuliang
 * @create2021-09-2021/9/3-22:47
 * @email 2640336916@qq.com
 * @explain
 */
public class test {
    static int a_sta;//外部类非静态成员变量
    int a_notSta;//外部类非静态成员变量

    static class inner1{//成员内部类

        String b_Sta;//内部类的非静态成员变量
        static String b_notSta;//内部类的静态成员变量

        public void add(){
            //成员内部类可以正常调用外部类的静态和非静态属性
            a_sta = a_sta + 1;
//            a_notSta = a_notSta + 1;
        }
    }

    public void method(){

        final String c_fin;//外部类的final局部变量
        String c_not_Fin;//外部类的普通局部变量

        class inner2{//局部内部类

            public void add(){

                //局部内部类可正常调外部类的静态和非静态成员变量
                a_sta = a_sta + 1;
                a_notSta = a_notSta + 1;
                //局部内部类调用外部类的成员内部类的属性
//                test.this.
            }
        }
    }

    public static void main(String[] args) {
        Consumers cust = Cus::dofe;
        cust.accept("刘亮",22);


        Consumers cust1 = new Cus()::dof;
        cust1.accept("刘亮",22);
        BigDecimal bie = new BigDecimal(3);
        BigDecimal bie2 = new BigDecimal(3);
        BigDecimal add = bie.add(bie2);

/*        Consumers cust2 = new Cus()::dofe;
        cust2.accept("刘亮",22);*/

    }

    class TwoTreeDemo<T>{

    }
/*    private void qianOut(TwoTreeDemo<Integer> headNode) {
        if(headNode!=null){
            System.out.print(headNode.value+",");
            if(headNode.leftNode!=null){
                qianOut(headNode.leftNode);
            }
            if(headNode.rightNode!=null){
                qianOut(headNode.rightNode);
            }
        }
    }*/
}
interface Consumers {
    public void accept(String str,Integer in);
}
class Cus{
    public void dof(String name,Integer age){
        System.out.println(name + "今年" + age + "岁了！");
    }
    public static void dofe(String name,Integer age){
        System.out.println(name + "今年" + age + "岁了！");
    }
}