package ru.practicum.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;


public class RegisterPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By nameField = By.xpath(".//label[text()='Имя']/../..//input[@name = 'name']");
    private By emailField = By.xpath(".//label[text()='Email']/../..//input[@name = 'name']");
    private By passwordField = By.xpath(".//input[@name = 'Пароль']");
    private By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private By loginLink = By.xpath(".//a[text()='Войти']");
    private By errorPasswordMessage = By.xpath(".//p[contains(@class, 'error')]");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }

    @Step("Открыть страницу регистрации")
    public void open() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
    }

    @Step("Ввести имя: {name}")
    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Ввести email: {email}")
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввести пароль: {password}")
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажать кнопку 'Зарегистрироваться'")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Перейти по ссылке 'Войти'")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }

    @Step("Проверить, отображается ли ошибка 'Некорректный пароль'")
    public boolean isPasswordErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(errorPasswordMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}