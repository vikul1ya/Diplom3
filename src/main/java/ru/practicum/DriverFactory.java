package ru.practicum;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverFactory {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        ChromeOptions options = new ChromeOptions();

        // Обязательные аргументы для стабильной работы
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");

        // Выбор браузера: явно указываем путь к .exe
        if ("yandex".equals(browser)) {
            // Путь к Яндекс Браузеру
            options.setBinary("C:\\Users\\vika\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        } else {
            // Путь к Google Chrome
            options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
        }

        // Создаём драйвер
        driver = new ChromeDriver(options);

        // Настройки
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

        // Открываем сайт
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}