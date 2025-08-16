import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import ru.practicum.ConstructorPage;
import ru.practicum.DriverFactory;


import static org.junit.Assert.assertEquals;

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
    @DisplayName("Переход между разделами:Булки")
    public void switchToBunsTest() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.open();

        constructorPage.clickSauces(); // кликаем на "Соусы", чтобы уйти с "Булок"
        constructorPage.clickBuns();  // возвращаемся на "Булки"

        assertEquals("Активный раздел должен быть 'Булки'", "Булки", constructorPage.getActiveTabText());
    }

    @Test
    @DisplayName("Переход между разделами:Соусы")
    public void switchToSaucesTest() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.open();

        constructorPage.clickSauces();

        assertEquals("Активный раздел должен быть 'Соусы'", "Соусы", constructorPage.getActiveTabText());
    }

    @Test
    @DisplayName("Переход между разделами:Начинки")
    public void switchToFillingsTest() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.open();

        constructorPage.clickFillings();

        assertEquals("Активный раздел должен быть 'Начинки'", "Начинки", constructorPage.getActiveTabText());
    }
}