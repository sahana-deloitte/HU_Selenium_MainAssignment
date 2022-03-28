package Test;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import Page.LoginPage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class LoginTest extends LoginPage 
{
    private static Logger log = Logger.getLogger("LoginTest");

    @Test(priority = 1)
    public static void bm_login() throws InterruptedException
    {
        Thread.sleep(3000);
        WebElement e1 = LoginPage.driver.findElement(By.xpath("//*[@ng-click='manager()']"));
        e1.click();
        log.error("Bank manager Login is Successful");
        log.info("Bank Manager Login");
        System.out.println("Logged in to Bank Manager Login");
        Thread.sleep(3000);
        WebElement e2 = LoginPage.driver.findElement(By.xpath("//*[@ng-class='btnClass1']"));
        e2.click();
        log.info("Add customer method");
        Thread.sleep(3000);
    }
    @Test(priority = 2)
    public static void add_customers() throws InterruptedException, IOException
    {
        String excelPath = "C:\\Users\\sahankh\\IdeaProjects\\Main_Assignment_Serialization\\Data.xlsx";
        FileInputStream fis = new FileInputStream(excelPath);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row;
        XSSFCell cell;
        String FirstName = null;
        String LastName = null;
        int PostCode = 0;
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                cell = row.getCell(j);
                if (j == 0) {
                    FirstName = cell.getStringCellValue();
                }
                if (j == 1) {
                    LastName = cell.getStringCellValue();
                }
                if (j == 2) {
                    PostCode = (int) cell.getNumericCellValue();
                }
            }
            LoginTest.driver.findElement(By.xpath("//*[@placeholder='First Name']")).sendKeys(FirstName);
            LoginTest.driver.findElement(By.xpath("//*[@placeholder='Last Name']")).sendKeys(LastName);
            LoginTest.driver.findElement(By.xpath("//*[@placeholder='Post Code']")).sendKeys((Integer.toString(PostCode)));
            Thread.sleep(2000);
            LoginTest.driver.findElement(By.xpath("//*[@type='submit']")).click();
            driver.switchTo().alert().accept();
            LoginTest.driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/button[2]")).click();

        }

    }


    @Test(priority = 3)
    public static void open_account() throws InterruptedException, IOException
    {
        LoginTest.driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/button[2]")).click();
        Thread.sleep(2000);
        Select se = new Select(driver.findElement(By.xpath("//*[@id=\"userSelect\"]")));
        List<WebElement> l = se.getOptions();
        se.selectByIndex(l.size() - 1);
        Thread.sleep(2000);
        Select sel = new Select(driver.findElement(By.xpath("//*[@id=\"currency\"]")));
        List<WebElement> l1 = sel.getOptions();
        sel.selectByIndex(3);
        Thread.sleep(2000);
        LoginTest.driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/form/button")).click();
        Thread.sleep(4000);
        driver.switchTo().alert().accept();
    }

    @Test(priority = 4)
    public static void customer_login() throws InterruptedException, IOException
    {
        WebElement e1 = LoginPage.driver.findElement(By.xpath("//*[@ng-click='home()']"));
        e1.click();
        Thread.sleep(3000);
        WebElement e2 = LoginPage.driver.findElement(By.xpath("//*[@ng-click='customer()']"));
        e2.click();
        Thread.sleep(3000);
        Select se = new Select(driver.findElement(By.xpath("//*[@id='userSelect']")));
        List<WebElement> l = se.getOptions();
        int n = l.size() - 1;
        se.selectByIndex(n);
        Thread.sleep(3000);
        LoginPage.driver.findElement(By.xpath("/html/body/div/div/div[2]/div/form/button")).click();
        Thread.sleep(3000);
        LoginPage.driver.findElement(By.xpath("//*[@ng-click='deposit()']")).click();
        Thread.sleep(2000);
        WebElement e3 = driver.findElement(By.xpath("//*[@ng-model='amount']"));
        String excelPath = "C:\\Users\\sahankh\\IdeaProjects\\Main_Assignment_Serialization\\Data.xlsx";
        FileInputStream fis = new FileInputStream(excelPath);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(1);
        XSSFRow row;
        XSSFCell cell;
        int amount=0;
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                cell = row.getCell(j);
                if (j == 0) {
                    amount = (int) cell.getNumericCellValue();
                }
            }
        }
        e3.sendKeys(Integer.toString(amount));
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src,new File("C:\\Users\\sahankh\\IdeaProjects\\Main_Assignment_Serialization\\deposit.png"));
        Thread.sleep(3000);
    }
    @Test(priority = 5)
    public static void withdraw() throws InterruptedException, IOException
    {
        driver.findElement(By.xpath("//button[@ng-click='withdrawl()']")).click();
        Thread.sleep(2000);
        WebElement e3 = driver.findElement(By.xpath("//*[@ng-model='amount']"));
        String excelPath = "C:\\Users\\sahankh\\IdeaProjects\\Main_Assignment_Serialization\\Data.xlsx";
        FileInputStream fis = new FileInputStream(excelPath);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(2);
        XSSFRow row ;
        XSSFCell cell;
        int amount = 0;
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                cell = row.getCell(j);
                if (j == 0) {
                    amount = (int) cell.getNumericCellValue();
                }
            }
        }
        e3.sendKeys(Integer.toString(amount));
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src,new File("C:\\Users\\sahankh\\IdeaProjects\\Main_Assignment_Serialization\\withdraw.png"));
    }
    @Test(priority = 6)
    public static void Withdraw() throws InterruptedException, IOException
    {
        driver.findElement(By.xpath("//button[@ng-click='withdrawl()']")).click();
        Thread.sleep(2000);
        WebElement e3 = driver.findElement(By.xpath("//*[@ng-model='amount']"));
        String excelPath = "C:\\Users\\sahankh\\IdeaProjects\\Main_Assignment_Serialization\\Data.xlsx";
        FileInputStream fis = new FileInputStream(excelPath);
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(2);
        XSSFRow row ;
        XSSFCell cell;
        int amount = 0;
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                cell = row.getCell(j);
                if (j == 0) {
                    amount = (int) cell.getNumericCellValue();
                }
            }
        }
        e3.sendKeys(Integer.toString(amount));
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[@class='btn btn-default']")).click();
        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src,new File("C:\\Users\\sahankh\\IdeaProjects\\Main_Assignment_Serialization\\withdrawerror.png"));
    }
}

