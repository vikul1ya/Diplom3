package ru.practicum.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By emailField = By.xpath(".//label[text()='Email']/../..//input[@name = 'name']");
    private By passwordField = By.xpath(".//input[@name = 'Пароль']");
    private By loginButton = By.xpath(".//button[text() = 'Войти']");
    private By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }

    @Step("Открыть страницу входа")
    public void open() {
        driver.get("https://stellarburgers.nomoreparties.site/login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
    }

    @Step("Ввести email: {email}")
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввести пароль")
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажать кнопку 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Выполнить вход с email и паролем")
    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }

    @Step("Проверить видимость кнопки 'Личный Кабинет'")
    public boolean isPersonalAccountButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(personalAccountButton)).isDisplayed();
    }
}