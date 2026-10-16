package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import resources.ExtentReportsNG;

import java.io.IOException;

public class ListenersNG extends BaseTest implements ITestListener {
    ExtentReports extent = ExtentReportsNG.getExtentReportsObject();
    ExtentTest tests;
    ThreadLocal<ExtentTest> tl = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        tests = extent.createTest(result.getMethod().getMethodName());
        tl.set(tests);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        tl.get().log(Status.PASS, "TEST PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        tl.get().fail(result.getThrowable());

        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String filePath;
        try {
            filePath = getScreenshot(result.getMethod().getMethodName(), driver);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        tl.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
