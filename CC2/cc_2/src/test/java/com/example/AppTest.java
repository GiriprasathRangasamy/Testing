package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    WebDriver driver;
    ExtentReports extent;
    ExtentTest extenttest;
    final Logger logger = Logger.getLogger(getClass());
    @BeforeTest
    void beforetest()
    {
        ExtentSparkReporter sparkReporter=new ExtentSparkReporter("D:\\SEllinium\\Class work\\CC2\\report.html");
        extent=new ExtentReports();
        extent.attachReporter(sparkReporter);
        PropertyConfigurator.configure("D:\\SEllinium\\Class work\\CC2\\cc_2\\src\\main\\java\\com\\example\\log4j.properties");
    }
   @BeforeMethod
   void beforeMethod()
   {
    WebDriverManager.chromedriver().setup();
    driver=new ChromeDriver();
    driver.get("https://www.barnesandnoble.com/");
    driver.manage().window().maximize();
    logger.info("\n\n\n");
   }
   @Test(dataProvider = "data")
   void test1(String book)
   {
    try
    {
        extenttest=extent.createTest("Test1","Getting book by name");
        SoftAssert asserts=new SoftAssert();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4000));
        driver.findElement(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/div[1]")).click();
        driver.findElement(By.linkText("Books")).click();
        driver.findElement(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/div[2]/div/input[1]")).sendKeys(book);
        driver.findElement(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/span/button")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4000));
        logger.info("Successfully got book by name");
        asserts.assertTrue(driver.getTitle().contains("Chetan Bhagat"));
        asserts.assertAll();
    }
    catch(Exception e)
    {
        logger.error("Error in getting book by name", e);
    }
    }
    @Test
    void test2() throws InterruptedException
    {
        try{
        extenttest=extent.createTest("Test2","Adding top first audio book");
        SoftAssert asserts=new SoftAssert();
        WebElement b= driver.findElement(By.xpath("//*[@id=\"rhfCategoryFlyout_Audiobooks\"]"));
        Actions actions=new Actions(driver);
        actions.moveToElement(b).perform();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/div/ul/li[5]/div/div/div[1]/div/div[2]/div[1]/dd/a[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"addToBagForm_2940159543998\"]/input[11]")).submit();  
        logger.info("successfully adding top first audio book");
        asserts.assertTrue(driver.findElement(By.xpath("//*[@id='main-content']/header/h1/span/span")).getText().contains("Audiobooks Top 100 Bestsellers"));
        asserts.assertAll();
        }
        catch(Exception e)
        {
            logger.error("Error in adding to cart of top first audio book", e);
        }
    }
    @Test
    void test3() throws InterruptedException
    {   
        try{
        extenttest=extent.createTest("Test3","Creating membership card");
        SoftAssert asserts=new SoftAssert();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4000));
        driver.findElement(By.linkText("B&N MEMBERSHIP")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4000));
        driver.findElement(By.linkText("JOIN REWARDS")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4000));
        logger.info("Successfully membership card created");
        asserts.assertTrue(driver.findElement(By.xpath("//*[@id=\"dialog-title\"]")).getText().contains("Sign in or Create an Account"));
        asserts.assertAll();
        }
        catch(Exception e)
        {
            logger.error("Erron in creating membership card", e);
        }
   }
    @AfterMethod
    public void tearDown(ITestResult result) throws Exception
    { 
        File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String screenShotPath="D:\\SEllinium\\Class work\\CC2\\"+result.getName()+".png";
        FileUtils.copyFile(screenshot, new File(screenShotPath));
        extenttest.addScreenCaptureFromPath(screenShotPath);

        if(result.getStatus()==ITestResult.FAILURE)
        {
            extenttest.log(Status.FAIL, "Testcase Failed : "+result.getName());
            extenttest.log(Status.FAIL, "Testcase Failed Reason: "+result.getThrowable());
        }
      else if (result.getStatus()==ITestResult.SUCCESS)
        { 
            extenttest.log(Status.PASS, "Test case Passed: "+result.getName());
        }
      else if (result.getStatus()==ITestResult.SKIP)
        { 
            extenttest.log(Status.SKIP, "Test case Skipped: "+result.getName());
        }
        extent.flush();
    }
    @DataProvider(name = "data")
        public Object[] send() throws IOException
        {
            FileInputStream fs=new FileInputStream("D:\\SEllinium\\Class work\\CC2\\bookname.xlsx");
            XSSFWorkbook workbook=new XSSFWorkbook(fs);
            XSSFSheet sheet=workbook.getSheetAt(0);
            int row=sheet.getLastRowNum()+1;
            
            Object data[]=new Object[row];
            for(int i=0;i<row;i++)
            {
                Row r=sheet.getRow(i);
                data[i]=r.getCell(i).getStringCellValue();
            }
            return data;        
            
        }


}
