import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.practicum.DriverFactory;
import ru.practicum.RegisterPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
@DisplayName("Тесты регистрации пользователя")
public class UserRegistrationTest {

    private DriverFactory driverFactory = new DriverFactory();
    private WebDriver driver;

    private final String name;
    private final String email;
    private final String password;
    private final boolean shouldFail;
    private final String expectedError;

    public UserRegistrationTest(String name, String email, String password, boolean shouldFail, String expectedError) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.shouldFail = shouldFail;
        this.expectedError = expectedError;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"TestUser", "testuser@example.com", "123456", false, null},
                {"BadUser", "baduser@example.com", "12345", true, "Некорректный пароль"}
        };
    }

    @Before
    public void setUp() {
        driverFactory.setUp();
        driver = driverFactory.getDriver();
    }

    @After
    public void tearDown() {
        driverFactory.tearDown();
    }

    @Test
    @DisplayName("Регистрация: успешная и с ошибкой для короткого пароля")
    public void testRegistration() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();

        registerPage.setName(name);
        registerPage.setEmail(email);
        registerPage.setPassword(password);
        registerPage.clickRegisterButton();

        if (shouldFail) {
            assertTrue("Ошибка о пароле должна отображаться", registerPage.isPasswordErrorDisplayed());
        } else {
            new WebDriverWait(driver, java.time.Duration.ofSeconds(10))
                    .until(webDriver -> webDriver.getCurrentUrl().contains("/login"));
            assertTrue("Должен перейти на страницу входа", driver.getCurrentUrl().contains("/login"));
        }
    }
}