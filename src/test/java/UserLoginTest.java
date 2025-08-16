import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.practicum.ConstructorPage;
import ru.practicum.DriverFactory;
import ru.practicum.FormRecoverPasswordPage;
import ru.practicum.RegisterPage;


import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
@DisplayName("Тесты входа в аккаунт")
public class UserLoginTest {

    private DriverFactory driverFactory = new DriverFactory();
    private WebDriver driver;

    private final String source;

    public UserLoginTest(String source) {
        this.source = source;
    }

    @Parameterized.Parameters
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
    }

    @After
    public void tearDown() {
        driverFactory.tearDown();
    }

    @Test
    @DisplayName("Вход через различные кнопки")
    public void testLoginFromDifferentSources() {
        ConstructorPage constructorPage = new ConstructorPage(driver);

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

        new WebDriverWait(driver, java.time.Duration.ofSeconds(10))
                .until(webDriver -> webDriver.getCurrentUrl().contains("/login"));
        assertTrue("Должен быть на странице входа", driver.getCurrentUrl().contains("/login"));
    }
}