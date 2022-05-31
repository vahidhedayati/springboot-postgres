package com.example.SpringBootPostgres.ui.webdriver;

import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.nio.file.Path;

public class Browser {

    private static WebDriver webDriver;

    public static WebDriver getDriver() {
        if (webDriver == null) {
            loadDriver();
        }
        return webDriver;
    }

    private static void setDriver(WebDriver wDriver) {
        webDriver = wDriver;
    }

    private static void loadDriver() {
        System.out.println("Attempting to set Chome driver ");

        // When:
        // WebDriverManager.chromedriver().setup()
        // is called it loads in an inbuilt chrome driver:
        // /home/{user}/.cache/selenium/chromedriver/linux64/102.0.5005.61/chromedriver
        // this was clashing with my chrome version which needed drivers for 101

        // Instead this loads in the correct chrome drivers 101 for Linux at my end
        System.setProperty("webdriver.chrome.driver", Path.of("").toAbsolutePath().toString()+ File.separator+"Drivers"+File.separator+"chromedriver");


        //WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        // This is needed for Chrome on Ubuntu
        options.addArguments("--user-data-dir=~/.config/google-chrome");
        //options.setExperimentalOption("useAutomationExtension", false);
        //options.addArguments("--remote-debugging-port=9225");
        WebDriver driver = new ChromeDriver(options);
        //WebDriver driver = new ChromeDriver();
        setDriver(driver);
        driver.manage().window().maximize();
        WebDriverRunner.setWebDriver(driver);

    }


    public static void closeBrowser() {
        if (webDriver != null) {
            getDriver().quit();
            setDriver(null);
        }
    }
}
