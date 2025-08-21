import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import ru.practicum.page.ConstructorPage;
import ru.practicum.DriverFactory;
import static org.junit.Assert.assertEquals;
import io.qameta.allure.Description;


@DisplayName("Навигация по разделам конструктора")
public class ConstructorNavigationTest {

    private DriverFactory driverFactory = new DriverFactory();
    private WebDriver driver;

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
    @DisplayName("Переход в раздел 'Булки'")
    @Description("Проверка, что вкладка 'Булки' становится активной")
    public void switchToBunsTest() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.open();

        constructorPage.clickSauces();
        constructorPage.clickBuns();

        assertEquals("Активный раздел должен быть 'Булки'", "Булки", constructorPage.getActiveTabText());
    }

    @Test
    @DisplayName("Переход в раздел 'Соусы'")
    @Description("Проверка, что вкладка 'Соусы' становится активной")
    public void switchToSaucesTest() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.open();

        constructorPage.clickSauces();

        assertEquals("Активный раздел должен быть 'Соусы'", "Соусы", constructorPage.getActiveTabText());
    }

    @Test
    @DisplayName("Переход в раздел 'Начинки'")
    @Description("Проверка, что вкладка 'Начинки' становится активной")
    public void switchToFillingsTest() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.open();

        constructorPage.clickFillings();

        assertEquals("Активный раздел должен быть 'Начинки'", "Начинки", constructorPage.getActiveTabText());
    }
}