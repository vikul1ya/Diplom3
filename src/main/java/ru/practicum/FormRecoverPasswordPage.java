package ru.practicum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormRecoverPasswordPage {

    private WebDriver driver;

    private By loginLink = By.xpath(".//a[text()='Войти']");

    public FormRecoverPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
    }

    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
}