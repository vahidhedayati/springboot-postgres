package com.example.SpringBootPostgres.cucumber.glue;

import com.example.SpringBootPostgres.pages.EmployeePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;

public class EmployeeSteps {

    @Given("^chrome browser is opened and employee page is loaded up$")
    public void testEmployeePageIsOpen() {

        EmployeePage employeePage = page(EmployeePage.class);
        System.out.println("Successfully opened the page......");
        employeePage.employeesMenuLink.click();
    }

    @When("^the add employee link is clicked$")
    public void addEmployeeClicked() {
        System.out.println("Successfully opened the page...");
    }
}
