package com.sparta.FlockApp.steps;

import io.cucumber.java.After;
import net.serenitybdd.core.Serenity;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Hooks {
    @After
    public void clearSession() {
        WebDriver driver = Serenity.getDriver();
        driver.manage().deleteAllCookies();
        ((JavascriptExecutor) driver)
                .executeScript("window.localStorage.clear(); window.sessionStorage.clear();");
    }
}
