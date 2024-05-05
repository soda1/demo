package com.soda.class1;

/**
 * 非静态内部类测试
 */
public class NonStaticInnerClassTest {

    private int i = 5;
    private final String string = "hello";
    static int g = 0;


    private int x = 0;

    public class MyInnerClass{

        final static int Constant = 8;
        private int x = 1;

        public void getOutClassVar() {
            System.out.println(i);
            System.out.println(string);

        }


        /**
         * scope test
         * @return
         */
        public void  ScopeTest(int x) {
            System.out.println(x);
            System.out.println(this.x);
            System.out.println(NonStaticInnerClassTest.this.x);
        }


        public void getOuterClassPrivateMethod() {
            priMethod();
        }

        public void print() {
            System.out.println("print");
        }
/*

        //不能创建静态方法
        public static void staticMethod() {
            System.out.println();
        }
*/
    }



    private void priMethod() {
        System.out.println("i am private");
    }

    public  void localInnerClass(Integer i) {

        int d = 3;
        class LocalClass{

            public void getLocalvar() {
                System.out.println(i);
                System.out.println(d);
                System.out.println(x);
            }
        }
        LocalClass localClass = new LocalClass();
        localClass.getLocalvar();
    }


    public static void main(String[] args) {

        NonStaticInnerClassTest nonStaticInnerClassTest = new NonStaticInnerClassTest();
        MyInnerClass myInnerClass = nonStaticInnerClassTest.new MyInnerClass();


        myInnerClass.getOutClassVar();
        myInnerClass.ScopeTest(23);
        myInnerClass.getOuterClassPrivateMethod();


        //局部内部类
        nonStaticInnerClassTest.localInnerClass(5);


        //匿名内部类
        MyInter inter = new MyInter() {

            final static  int gg = 8;
            @Override
            public void say() {
                System.out.println("hello");
                System.out.println(g);
            }


        };

        AnonymousClass anonymousClass = new AnonymousClass(){
            @Override
            public void say() {
                System.out.println("todo");
            }
        };


    }


}
interface MyInter{

    void say();
}

class AnonymousClass{

    public void say() {
        System.out.println("hello");
    }
}
