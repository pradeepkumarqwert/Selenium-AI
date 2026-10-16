package runners;

import org.testng.TestNG;

public class standaloneTestRunner {
    public static void main(String[] args){
        TestNG testNG = new TestNG();
        testNG.setTestClasses(new Class[]{
            tests.StandaloneTest2.class
        });
    }
}
