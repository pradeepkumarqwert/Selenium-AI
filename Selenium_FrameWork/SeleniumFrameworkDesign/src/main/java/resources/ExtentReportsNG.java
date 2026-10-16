package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentReportsNG {


    public static ExtentReports getExtentReportsObject(){
        String path = System.getProperty("user.dir")+"\\test-outputs";
        File fs = new File(path);
        if(!fs.exists()){
            fs.mkdirs();
        }

        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Revision");
        reporter.config().setDocumentTitle("Revision");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester","Pradeep");

        return extent;

    }













//    public static ExtentReports getExtentReportsObject(){
//        String pathDir = System.getProperty("user.dir")+"\\test-outputs";
//
//        File dir = new File(pathDir);
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//
//        ExtentSparkReporter reporter = new ExtentSparkReporter(pathDir+"\\index.html");
//        reporter.config().setDocumentTitle("SELENIUM LEARNING");
//        reporter.config().setReportName("STANDALONE TEST");
//
//        ExtentReports extent = new ExtentReports();
//        extent.attachReporter(reporter);
//        extent.setSystemInfo("TESTER: ", "PRADEEP KUMAR P");
//        extent.setSystemInfo("DESIGNATION: ", "ASSOCIATE SOFTWARE TEST ENGINEER");
//        extent.setSystemInfo("LEARNING TOPIC: ", "EXTENT REPORTS");
//
//        return extent;
//    }
}
