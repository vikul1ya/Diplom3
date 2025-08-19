package ru.practicum.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

public class FormRecoverPasswordPage {
    private WebDriver driver;

    private By loginLink = By.xpath(".//a[text()='Войти']");

    public FormRecoverPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открыть форму восстановления пароля")
    public void open() {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
    }

    @Step("Нажать ссылку 'Войти'")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
}