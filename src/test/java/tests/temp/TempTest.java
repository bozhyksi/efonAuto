package tests.temp;

import flow.BaseTestMethods;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class TempTest extends BaseTestMethods {
    AtomicInteger index = new AtomicInteger(0);

    private int atom(){
        return index.getAndIncrement();
    }

    @Test(groups = {"ttt"})
    public void t1(){
        System.out.println(atom());
    }

    @Test(groups = {"ttt"})
    public void t2(){
        System.out.println(atom());
    }

    @Test(groups = {"ttt"})
    public void t3(){
        System.out.println(atom());
    }

    @Test(groups = {"ttt"})
    public void t4(){
        System.out.println(atom());
    }

    @Test(groups = {"ttt"})
    public void t5(){
        System.out.println(atom());
    }

    @Test(groups = {"ttt"})
    public void t6(){
        System.out.println(atom());
    }

    @Test(groups = {"ttt"})
    public void t7(){
        System.out.println(atom());
    }





}
