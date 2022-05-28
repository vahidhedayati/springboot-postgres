package com.example.SpringBootPostgres.cucumber.hooks;

import com.example.SpringBootPostgres.ui.webdriver.Browser;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.nio.file.Path;

import static com.codeborne.selenide.Selenide.open;

/**
 * Browser is used for testing employee steps - ensure site has also been started up before attempting the test
 */
public class CucumberHooks {

    @Before
    public void setUp() {
        System.out.println("Starting Browser...");
        //WebDriver driver = Browser.getDriver();
        //driver.get("http://localhost:8080/api/v1/employee");
        Browser.getDriver();
        open("http://localhost:8080/api/v1/employee");
       // System.setProperty("webdriver.chrome.driver", Path.of("").toAbsolutePath().toString()+ File.separator+"Drivers"+File.separator+"chromedriver");
        //WebDriverManager.chromedriver().setup();
       // ChromeOptions options = new ChromeOptions();
        //options.setExperimentalOption("useAutomationExtension", false);
        //options.addArguments("--remote-debugging-port=9225");
       // WebDriver driver = new ChromeDriver(options);
       // driver.get("https://www.google.co.uk");
    }

    @After
    public void tearDown() {
        Browser.closeBrowser();
    }
}
