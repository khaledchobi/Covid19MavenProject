package TestMaven;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestMavenProject {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello Khaled...");
        String exePath = "/Users/khaledhasan/Desktop/Java_Github/Drivers/chromedriver";
        System.setProperty("webdriver.chrome.driver", exePath);
        WebDriver driver = new ChromeDriver();
        driver.get("http://ebfs.bruteforcesolution.net/ebfs/index.php");

        Thread.sleep(3000);

        driver.close();
    }
}
