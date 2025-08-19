import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.practicum.Api.User;
import ru.practicum.Api.UserApi;
import ru.practicum.Page.ConstructorPage;
import ru.practicum.DriverFactory;
import ru.practicum.Page.FormRecoverPasswordPage;
import ru.practicum.Page.LoginPage;
import ru.practicum.Page.RegisterPage;

import static org.junit.Assert.assertTrue;

import io.qameta.allure.Description;


@RunWith(Parameterized.class)
@DisplayName("Тесты входа в аккаунт")
public class UserLoginTest {

    private DriverFactory driverFactory = new DriverFactory();
    private WebDriver driver;

    private final String source;
    private User user;
    private String accessToken;

    public UserLoginTest(String source) {
        this.source = source;
    }

    @Parameterized.Parameters(name = "Источник входа: {0}")
    public static Object[][] getSources() {
        return new Object[][]{
                {"main_button"},
                {"personal_account"},
                {"register_form"},
                {"recover_form"}
        };
    }

    @Before
    public void setUp() {
        driverFactory.setUp();
        driver = driverFactory.getDriver();

        String email = "test_" + System.currentTimeMillis() + "@yandex.ru";
        user = new User(email, "123456", "Test User");

        UserApi.createUser(user).assertThat().statusCode(200);

        accessToken = UserApi.login(user).extract().path("accessToken");
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            UserApi.deleteUser(accessToken);
        }
        driverFactory.tearDown();
    }

    @Test
    @DisplayName("Вход через различные кнопки")
    @Description("Пользователь переходит на /login разными путями, вводит данные и успешно входит")
    public void testLoginFromDifferentSources() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.open();

        switch (source) {
            case "main_button":
                constructorPage.clickLoginButton();
                break;
            case "personal_account":
                constructorPage.clickPersonalAccountButton();
                break;
            case "register_form":
                new RegisterPage(driver).open();
                new RegisterPage(driver).clickLoginLink();
                break;
            case "recover_form":
                new FormRecoverPasswordPage(driver).open();
                new FormRecoverPasswordPage(driver).clickLoginLink();
                break;
        }

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue("Пользователь должен успешно войти", loginPage.isPersonalAccountButtonVisible());
    }
}