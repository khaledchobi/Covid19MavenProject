package com.compilia.signin;

import com.compilia.constants.Constant;
import com.compilia.utility.ExcelUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignInTest {

    WebDriver driver;
    ExcelUtils excelUtils = new ExcelUtils();

    @BeforeMethod
    public void setup() throws Exception {
        System.setProperty("webdriver.chrome.driver", Constant.chromeDriverPath);
        driver = new ChromeDriver();
        driver.get(Constant.baseUrl);


        Reporter.log("------------ Browser Opened...");
    }

    @Test(priority = 1, groups = {"SmokeTest"})
    public void verifyLogin() throws Exception{
        Thread.sleep(5000);
        Reporter.log("-------------------------- Path: " + Constant.testDataPath);

        excelUtils.setExcelFile(Constant.testDataPath, Constant.sheetName);

        String email = excelUtils.getCellData(1,1);
        String password = excelUtils.getCellData(1,2);

        WebElement signIn = driver.findElement(By.linkText("Sign in"));
        signIn.click();

        WebElement txtBxEmail = driver.findElement(By.id("email"));
        txtBxEmail.sendKeys(email);

        Thread.sleep(2000);

        WebElement txtBxPassword =driver.findElement(By.xpath("//input[@id='passwd']"));
        txtBxPassword.sendKeys(password);

        Thread.sleep(2000);

        driver.findElement(By.id("SubmitLogin")).click();


        //excelUtils.setCellData("Pass", 1,3, Constant.testDataPath);

        driver.getCurrentUrl();
        if(driver.getCurrentUrl().equals("http://ebfs.bruteforcesolution.net/ebfs/index.php?controller=my-account")) {
            excelUtils.setCellData("Pass", 1,3, Constant.testDataPath);
            System.out.println("Pass");
        }else {
            excelUtils.setCellData("Fail", 1,3, Constant.testDataPath);
            System.out.println("Fail");
            Thread.sleep(2000);
        }


    }

    @Test(priority = 2)
    public void verifyLogin_02() throws Exception{

        Thread.sleep(5000);
        Reporter.log("-------------------------- Path: " + Constant.testDataPath);

        excelUtils.setExcelFile(Constant.testDataPath, Constant.sheetName);

        String email = excelUtils.getCellData(2,1);
        String password = excelUtils.getCellData(2,2);

        WebElement signIn = driver.findElement(By.linkText("Sign in"));
        signIn.click();

        WebElement txtBxEmail = driver.findElement(By.id("email"));
        txtBxEmail.sendKeys(email);

        Thread.sleep(2000);

        WebElement txtBxPassword =driver.findElement(By.xpath("//input[@id='passwd']"));
        txtBxPassword.sendKeys(password);

        Thread.sleep(2000);

        driver.findElement(By.id("SubmitLogin")).click();


        //excelUtils.setCellData("Pass", 2,3, Constant.testDataPath);

        String actual = driver.getTitle();
        System.out.println("The title of the page is :"+ actual);
        String expected = "My account - eBFS - the power of choice";
        if (driver.getTitle().equals(expected)){
        excelUtils.setCellData("Pass", 2,3, Constant.testDataPath);
            System.out.println("Pass");
        }else{
        excelUtils.setCellData("Fail", 2,3, Constant.testDataPath);
            System.out.println("Fail");
        }

    }


    @AfterMethod
    public void close(){
        driver.close();
    }

}
