package tests.temp;

import flow.BaseTestMethods;

public class TempTest extends BaseTestMethods {

    public static void main(String[] args) {
        mmm1();
        mmm2();
        mmm3();
    }

    public static void mmm1() {
        try {
            throw new Exception();
        } catch (Exception e) {
            System.out.println("mmm1 failed");
            e.printStackTrace();
        }
    }

    public static void mmm2(){
        System.out.println("mmm2");
    }

    public static void mmm3(){
        System.out.println("mmm3");
    }
}
