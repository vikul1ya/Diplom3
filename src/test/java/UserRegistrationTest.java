import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.practicum.Api.User;
import ru.practicum.Api.UserApi;
import ru.practicum.DriverFactory;
import ru.practicum.Page.RegisterPage;

import static org.junit.Assert.assertTrue;
import io.qameta.allure.Description;


@RunWith(Parameterized.class)
@DisplayName("Тесты регистрации пользователя")
public class UserRegistrationTest {

    private DriverFactory driverFactory = new DriverFactory();
    private WebDriver driver;

    private final String name;
    private final String email;
    private final String password;
    private final boolean shouldFail;

    private User user;

    public UserRegistrationTest(String name, String email, String password, boolean shouldFail) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.shouldFail = shouldFail;
    }

    @Parameterized.Parameters(name = "Имя: {0}, Email: {1}, Пароль: {2}, Ожидается ошибка: {3}")
    public static Object[][] getData() {
        long timestamp = System.currentTimeMillis();
        return new Object[][]{
                {"TestUser", "valid_" + timestamp + "@yandex.ru", "1234567", false},
                {"BadUser", "bad_" + timestamp + "@yandex.ru", "12345", true}
        };
    }

    @Before
    public void setUp() {
        driverFactory.setUp();
        driver = driverFactory.getDriver();

        // Создаём пользователей (если нужно)
        user = new User(email, password, name);
    }

    @After
    public void tearDown() {
        UserApi.deleteUserRequest(user);
        driverFactory.tearDown();
    }

    @Test
    @DisplayName("Регистрация: успешная и с ошибкой для короткого пароля")
    @Description("Проверка, что система корректно обрабатывает валидные и невалидные пароли")
    public void testRegistration() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();

        registerPage.setName(name);
        registerPage.setEmail(email);
        registerPage.setPassword(password);
        registerPage.clickRegisterButton();

        if (shouldFail) {
            assertTrue("Должна отображаться ошибка 'Некорректный пароль'", registerPage.isPasswordErrorDisplayed());
        } else {
            new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10))
                    .until(d -> d.getCurrentUrl().contains("/login"));
            assertTrue("Должен быть на странице входа", driver.getCurrentUrl().contains("/login"));
        }
    }
}