package runners;

import org.testng.TestNG;
import tests.SubmitOrder;

public class standaloneTestRunner {
    public static void main(String[] args){
        TestNG testNG = new TestNG();
        testNG.setTestClasses(new Class[]{
            SubmitOrder.class
        });
    }
}
