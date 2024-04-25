package com.example;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
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
    ExtentTest logger;
    @BeforeTest
    void beforeTest()
    {
        ExtentSparkReporter sparkReporter=new ExtentSparkReporter("D:\\SEllinium\\Class work\\Day9\\day9-question1\\report.html");
        extent=new ExtentReports();
        extent.attachReporter(sparkReporter);
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.get("https://groww.in/");
    }
    @Test
    void Test1()
    {
        SoftAssert assert1=new SoftAssert();
        logger=extent.createTest("Test1","This is test for going to calculator");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
        driver.findElement(By.linkText("Calculators")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
        assert1.assertTrue("Calculators".equals(driver.findElement(By.xpath("//*[@id='root']/div[2]/h1")).getText()));
        assert1.assertAll();
    }
    @Test(dataProvider = "data")
    void Test2(String amount)
    {
        SoftAssert assert2=new SoftAssert();
        logger=extent.createTest("Test2","This is to find specific string is present");
        driver.findElement(By.xpath("//*[@id='root']/div[2]/div[2]/a[15]/div/p[1]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
        driver.findElement(By.id("LOAN_AMOUNT")).sendKeys(amount);
        driver.findElement(By.id("RATE_OF_INTEREST")).sendKeys("8");
        driver.findElement(By.id("LOAN_TENURE")).sendKeys("25");
        assert2.assertTrue("Your Amortization Details (Yearly/Monthly)".equals(driver.findElement(By.xpath("//*[@id='root']/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/div/p")).getText()));
        assert2.assertAll();
        

    }
    @AfterMethod
    void teardown(ITestResult result) throws Exception
    {
        File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String screenShotPath="D:\\SEllinium\\Class work\\Day9\\day9-question1"+result.getName()+".png";
        FileUtils.copyFile(screenshot, new File(screenShotPath));
        logger.addScreenCaptureFromPath(screenShotPath);
        if(result.getStatus()==ITestResult.FAILURE)
        {
            logger.log(Status.FAIL,"Tesrcase"+result.getName());
            logger.log(Status.FAIL,"Reson"+result.getThrowable());
        }
        else if(result.getStatus()==ITestResult.SUCCESS)
        {
            logger.log(Status.PASS, "Test Passed:"+result.getName());
        }
        else if (result.getStatus()==ITestResult.SKIP)
        { 
            logger.log(Status.SKIP, "Test CAse Skipped: "+result.getName());
        }
    }
    @AfterTest
    void saveit()
    {
        extent.flush();
    }
    @DataProvider(name="data")
    Object[] fetchdata() throws IOException
    {
        FileInputStream fs=new FileInputStream("D:\\SEllinium\\Class work\\Day9\\day9-question1\\loanamount.xlsx");
        XSSFWorkbook workbook=new XSSFWorkbook(fs);
        XSSFSheet sheet=workbook.getSheetAt(0);
        Object[]data=new Object[1];
        Row r=sheet.getRow(0);
        Cell cell=r.getCell(0);
        data[0]=cell.getStringCellValue();
        return data;
    }
}
