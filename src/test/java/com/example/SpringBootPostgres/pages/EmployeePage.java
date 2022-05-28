package com.example.SpringBootPostgres.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class EmployeePage {
    public SelenideElement employeesMenuLink = $("a[href='/api/v1/employee/add']");
}
