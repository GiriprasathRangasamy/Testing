package com.example;


import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
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
        ExtentSparkReporter sparkReporter=new ExtentSparkReporter("D:\\SEllinium\\Class work\\Day9\\day9-question2\\report.html");
        extent=new ExtentReports();
        extent.attachReporter(sparkReporter);
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.ixigo.com/");
    }
    @Test
    void Test1()
    {
        SoftAssert assert1=new SoftAssert();
        logger=extent.createTest("Test1","This is to test for searching");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
        driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[1]/div[1]/div/button[2]")).click();;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
        driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div/div/div/p[2]")).click();;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
        driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[2]/div/div/div[2]/input")).sendKeys("IDP-Independence");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
        driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[3]/div[1]/li/div[2]/p[1]/span[1]")).click();
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
        driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div")).click();;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
        driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[2]/div[2]/div/div/div[2]/input")).sendKeys("DEL-NEW Delhi");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
        driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[2]/div[3]/div[1]/li/div[2]/p[1]/span[1]")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
        driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[2]/div[1]/div/div/div/div/p[2]")).click();;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
        driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[2]/div[3]/div/div[1]/div[2]/div[1]/div/div/div[2]/button[28]/abbr")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
        driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[2]/div[2]/div/div[1]/div/div/p[2]")).click();;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
        driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[2]/div[3]/div/div[1]/div[2]/div[1]/div/div/div[2]/button[31]/abbr")).click();
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
        driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[3]/div[1]/div/div/div/p[2]")).click();;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
        driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[3]/div[2]/div/div[1]/div[1]/div[2]/div/button[2]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
        driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[3]/div[2]/div/div[1]/div[2]/div[2]/div/button[3]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
        driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[3]/div[2]/div/div[1]/div[5]/div/div[3]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
        driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[3]/div[2]/div/div[2]/button")).click();
        

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
        assert1.assertTrue("Tue, 30 Apr".contains(driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[2]/div[2]/div/div[1]/div/div/p[2]")).getText()));
        assert1.assertAll();
    }
    @Test
    void Test2()
    {
        SoftAssert assert2=new SoftAssert();
        logger=extent.createTest("Test2","This is to go to about us");
        driver.findElement(By.linkText("About Us")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
        List<String>handlers=new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(handlers.get(1));
        assert2.assertTrue("about".contains(driver.getCurrentUrl()));
        assert2.assertAll();
}
    @AfterMethod
    void teardown(ITestResult result) throws Exception
    {
        File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String screenShotPath="D:\\SEllinium\\Class work\\Day9\\day9-question2\\"+result.getName()+".png";
        FileUtils.copyFile(screenshot, new File(screenShotPath));
        logger.addScreenCaptureFromPath(screenShotPath);
        if(result.getStatus()==ITestResult.FAILURE)
        {
            logger.log(Status.FAIL,"Testcase"+result.getName());
            logger.log(Status.FAIL,"Reson"+result.getThrowable());
        }
        else if(result.getStatus()==ITestResult.SUCCESS)
        {
            logger.log(Status.PASS, "Test Passed:"+result.getName());
        }
        else if (result.getStatus()==ITestResult.SKIP)
        { 
            logger.log(Status.SKIP, "Test case Skipped: "+result.getName());
        }
    }
    @AfterTest
    void saveit()
    {
        extent.flush();
    }
}
