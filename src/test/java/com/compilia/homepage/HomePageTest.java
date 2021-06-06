package com.compilia.homepage;

import com.compilia.constants.Constant;
import com.compilia.utility.ExcelUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class HomePageTest {

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
    public void verifyFeatureProductsDisplayed() throws Exception{
        // 1.4. Featured Products should be visible to Users
        Thread.sleep(5000);
        Reporter.log("-------------------------- Path: " + Constant.testDataPath);

        List<WebElement> popularProductsName = driver.findElements(By.xpath("//ul[@id='homefeatured']/li/div[1]/div[2]/h5/a"));
        List<WebElement>  popularProducts = driver.findElements(By.xpath("//ul[@id='homefeatured']/li"));
        if(popularProductsName.size() == 8){
            System.out.println("PASSED..");
        }else{
            System.out.println("Failed..");
        }
        for(WebElement element: popularProductsName){
            System.out.println("Product Name: " + element.getText());
        }
        Actions act = new Actions(driver);
        List<WebElement>  linkQuickView = driver.findElements(By.xpath("//ul[@id='homefeatured']/li/div/div/div/a[@class = 'quick-view']/span"));
        for(int i=0; i< popularProducts.size(); i++){
            act.moveToElement(popularProducts.get(i)).build().perform();
            Thread.sleep(2000);
            System.out.println("Text Name: " + linkQuickView.get(i).getText());
        }

    }

    @Test(priority = 2)
    public void verifyNavigateToProductCategory() throws Exception{
        // 2.1. User should be able to navigate to one of the product category page by clicking Dresses/T-Shirts.

        Thread.sleep(5000);
        Reporter.log("-------------------------- Path: " + Constant.testDataPath);

        Thread.sleep(3000);
        // Menu Dresses
        driver.findElement(By.xpath("//div[@id='block_top_menu']/ul/li[2]/a")).click();

        // Dress text displayed
        driver.findElement(By.xpath("//span[@class='cat-name']")).isDisplayed();

        Thread.sleep(3000);
        // Summer Dresses
        driver.findElement(By.xpath("//input[@id='layered_category_11']")).click();

        Thread.sleep(3000);
        // Evening Dresses
        driver.findElement(By.xpath("//input[@id='layered_category_10']")).click();

        Thread.sleep(3000);
        // Casual Dresses
        driver.findElement(By.xpath("//input[@id='layered_category_9']")).click();

        Thread.sleep(3000);
        // Size
        driver.findElement(By.xpath("//input[@id='layered_id_attribute_group_2']")).click();

        Thread.sleep(3000);
        // Color Yellow
        driver.findElement(By.xpath("//input[@id='layered_id_attribute_group_16']")).click();

        Thread.sleep(3000);
        // Availability In Stock
        driver.findElement(By.xpath("//input[@id='layered_quantity_1']")).click();

        Thread.sleep(3000);
        // Menu T-Shirts
        driver.findElement(By.xpath("//div[@id='block_top_menu']/ul/li[3]/a")).click();

        // T-Shirts text displayed
        driver.findElement(By.xpath("//span[@class='cat-name']")).isDisplayed();



    }


    @AfterMethod
    public void close(){
        driver.close();
    }

}
