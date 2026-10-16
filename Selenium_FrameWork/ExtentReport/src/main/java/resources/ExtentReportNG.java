package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {

    public static ExtentReports getExtentReport(){
        String path = System.getProperty("user.dir")+"\\test-outputs\\extentReport.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setDocumentTitle("INTERVIEW REPORT");
        reporter.config().setReportName("AUTOMATION TEST REPORT");

        ExtentReports extent = new ExtentReports();
        extent.setSystemInfo("TESTER", "PRADEEP KUMAR P");
        extent.setSystemInfo("TEAM", "DEVICE FARM");
        extent.setSystemInfo("DESTINATION", "ASSOCIATE SOFTWARE TEST ENGINEER");

        return extent;
    }
}
