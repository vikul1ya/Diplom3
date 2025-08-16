package ru.practicum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    private By registerButton = By.xpath(".//a[text()='Зарегистрироваться']");
    private By recoverPasswordButton = By.xpath(".//a[text()='Восстановить пароль']");
    private By loginButton = By.xpath(".//button[text()='Войти']");
    private By emailField = By.xpath("//input[@name='email']");
    private By passwordField = By.xpath("//input[@name='Пароль']");
    private By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://stellarburgers.nomoreparties.site/login");
    }

    public void clickRegisterLink() {
        driver.findElement(registerButton).click();
    }

    public void clickRecoverPasswordLink() {
        driver.findElement(recoverPasswordButton).click();
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }
}