package com.example;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByPartialLinkText;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;




/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    final Logger logger = Logger.getLogger(getClass());
     WebDriver driver;
    @BeforeTest
    void beforetest()
    {
    PropertyConfigurator.configure("D:\\SEllinium\\Class work\\Day10\\Day10-question-2\\day_10_2\\src\\main\\java\\com\\example\\log4j.properties");
    WebDriverManager.chromedriver().setup();
    driver=new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("https://www.opentable.com/");
    logger.info("Going to opentable website");
    }
    @Test(dataProvider = "data")
    public void Test1(String place) throws IOException
    {
        
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4000));
       try{
       driver.findElement(By.xpath("//*[@id='home-page-autocomplete-input']")).sendKeys(place);
       logger.info("Get place from xlxs");
       }
       catch(Exception e)
       {
        logger.error("The erron in getting data:", e);
       }
       driver.findElement(By.xpath("//*[@id='mainContent']/header/div/span/div/div/div[2]/div[2]/button")).click();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4000));
       driver.findElement(By.xpath("//*[@id='mainContent']/div/section/div[6]/div/label[3]/span[2]")).click();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4000));
       try
       {
       File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
       String screenshotpath="D:\\SEllinium\\Class work\\Day10\\Day10-question-2\\opentablescreenshot.png";
       FileUtils.copyFile(screenshot, new File(screenshotpath));
       logger.info("screen shot taken");
    }
    catch(Exception e)
    {
        logger.warn("the error is : ",e);
    }
    // driver.findElement(By.linkText("Far & East - Four Seasons Hotel Bengaluru")).click();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4000));

       driver.findElement(By.xpath("//*[@id='mainContent']/div/div/div[2]/div/div[1]/a/img")).click();
       driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4000));
       List<String>handlers=new ArrayList<>(driver.getWindowHandles());
       driver.switchTo().window(handlers.get(1));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4000));
        WebElement e1=driver.findElement(By.xpath("//*[@id='restProfileSideBarDtpPartySizePicker']"));
        Select select1=new Select(e1);
        select1.selectByVisibleText("4 people");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4000));
        driver.findElement(By.xpath("//*[@id='restProfileSideBarDtpDayPicker-label']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4000));
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,-1000)", "");
        driver.findElement(By.xpath("//*[@id='restProfileSideBarDtpDayPicker-wrapper']/div/div/div/table/tbody/tr[5]/td[3]/button")).click();
       

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4000));
        WebElement e2=driver.findElement(By.xpath("//*[@id='restProfileSideBartimePickerDtpPicker']"));
        Select select2=new Select(e2);
        select2.selectByVisibleText("6:30 PM");
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4000));
        driver.findElement(By.xpath("//*[@id='mainContent']/div/div[2]/div[2]/div/article/div/div[5]/button")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4000));
        driver.findElement(By.xpath("//*[@id='baseApp']/div/header/div[2]/div[2]/div[1]/button")).click();

    }

    @DataProvider(name = "data")
    Object[] datapassing() throws IOException
    {
        FileInputStream fs=new FileInputStream("D:\\SEllinium\\Class work\\Day10\\Day10-question-2\\place.xlsx");
        XSSFWorkbook workbook=new XSSFWorkbook(fs);
        XSSFSheet sheet=workbook.getSheetAt(0);
        Object[]data=new Object[1];
        Row r=sheet.getRow(0);
        Cell cell=r.getCell(0);
        data[0]=cell.getStringCellValue();
        System.out.println(data[0]);
        return data;
    }
}
