package TestCases;

import Drivers.DriverFactory;
import Drivers.DriverHolder;
import com.github.javafaker.Faker;
import util.Utility;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.configuration.Theme;
import common.MyScreenRecorder;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import static util.Utility.startHtmlReport;


@Listeners(listeners.Listener.class)
public class TestBase {
 protected static WebDriver driver;
    // extend report
    protected static ExtentSparkReporter htmlReporter;
    protected static ExtentReports extent;
    protected static ExtentTest test;
    // define Suite Elements
    static Properties prop;
    static FileInputStream readProperty;
    private static String PROJECT_NAME = null;
    private static String PROJECT_URL = null;
    static Faker faker = new Faker();

    @BeforeSuite
    public void defineSuiteElements() throws IOException {
        // initialize the HtmlReporter
        htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/testReport.html");

        //initialize ExtentReports and attach the HtmlReporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        setProjectDetails();
        // initialize test
        test = extent.createTest(PROJECT_NAME + " Test Automation Project");
        //configuration items to change the look and fee add content, manage tests etc
        htmlReporter.config().setDocumentTitle(PROJECT_NAME + " Test Automation Report");
        htmlReporter.config().setReportName(PROJECT_NAME + " Test Report");
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
//        Utility.deleteFilesInFolder("src/test/resources/Screenshots");
//        Utility.deleteFilesInFolder("recordings");

    }

    private void setProjectDetails() throws IOException {
        // TODO: Step1: define object of properties file
        readProperty = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/properties/environment.properties");
        prop = new Properties();
        prop.load(readProperty);

        // define project name from properties file
        PROJECT_NAME = prop.getProperty("projectName");
        PROJECT_URL = prop.getProperty("url");
    }
    @Parameters("browser")
    @BeforeTest
    public void setupDriver(@Optional("chrome") String browser) throws Exception {
        // start recording
        MyScreenRecorder.startRecording("sprint1");
        driver = DriverFactory.getNewInstance(browser);
        DriverHolder.setDriver(driver);
        //set implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7000));

        driver.get(PROJECT_URL);
    }

    @AfterTest
    public void quit() throws IOException {
//      driver.quit();
      //Thread.currentThread().interrupt();
    }

    @AfterSuite
    public void tearDown() throws IOException {
        extent.flush();
        //start html report after test end
        startHtmlReport(System.getProperty("user.dir"), "/testReport.html");
    }

    @AfterMethod
    public void getResult(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, result.getName() + " failed with the following error: " + result.getThrowable());
            Reporter.log("Failed to perform " + result.getName(), 10, true);
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, result.getName());
            Reporter.log("Successfully perform " + result.getName(), 10, true);
        } else {
            test.log(Status.SKIP, result.getName());
            Reporter.log("Skip " + result.getName(), 10, true);
        }
        startHtmlReport("C:\\Users\\islam\\OneDrive\\Desktop\\Projects\\SwagLaps", "testReport.html");

    }
}