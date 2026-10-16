package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentReportsClass {
    public static ExtentReports getExtentReportsNG(){

        String pathDir = System.getProperty("user.dir")+"\\test-outputs";

        File dir = new File(pathDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String reportPath = pathDir+"\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setDocumentTitle("EXTENT REPORT DEMO");
        reporter.config().setReportName("AUTOMATION TESTING RESULT");

        ExtentReports reports = new ExtentReports();
        reports.attachReporter(reporter);
        reports.setSystemInfo("TESTER", "PRADEEP KUMAR P");
        reports.setSystemInfo("DESTINATION", "ASSOCIATE SOFTWARE TEST ENGINEER");
        reports.setSystemInfo("TEAM", "DEVICE FARM");

        return reports;
    }
}
