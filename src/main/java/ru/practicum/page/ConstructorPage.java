package ru.practicum.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;

public class ConstructorPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");
    private By bunsSection = By.xpath(".//span[text()='Булки']/..");
    private By saucesSection = By.xpath(".//span[text()='Соусы']/..");
    private By fillingsSection = By.xpath(".//span[text()='Начинки']/..");
    private By activeSection = By.xpath(".//div[contains(@class, 'tab_type_current')]");

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
    }

    @Step("Открыть главную страницу")
    public void open() {
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Step("Нажать кнопку 'Войти в аккаунт'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Перейти в Личный Кабинет")
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    @Step("Перейти в раздел 'Булки'")
    public void clickBuns() {
        driver.findElement(bunsSection).click();
        wait.until(ExpectedConditions.textToBe(activeSection, "Булки")); // ожидание изменения активного раздела
    }

    @Step("Перейти в раздел 'Соусы'")
    public void clickSauces() {
        driver.findElement(saucesSection).click();
        wait.until(ExpectedConditions.textToBe(activeSection, "Соусы")); // ожидание изменения активного раздела
    }

    @Step("Перейти в раздел 'Начинки'")
    public void clickFillings() {
        driver.findElement(fillingsSection).click();
    }

    @Step("Получить текст активной вкладки")
    public String getActiveTabText() {
        WebElement active = wait.until(ExpectedConditions.visibilityOfElementLocated(activeSection));
        return active.getText();
    }
}