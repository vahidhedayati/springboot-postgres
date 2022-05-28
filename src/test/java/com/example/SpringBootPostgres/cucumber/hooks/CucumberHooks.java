package com.example.SpringBootPostgres.cucumber.hooks;

import com.example.SpringBootPostgres.ui.webdriver.Browser;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static com.codeborne.selenide.Selenide.open;

/**
 * Browser not used currently
 */
public class CucumberHooks {

    @Before
    public void setUp() {
        System.out.println("Starting Browser...");
        Browser.getDriver();
        open("https://localhost:8080/");
    }

    @After
    public void tearDown() {
        Browser.closeBrowser();
    }
}
