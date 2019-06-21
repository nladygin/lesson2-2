import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class TestLink {

    private static WebDriver driver;
    private final String serverAddress = "http://192.168.137.13";
    private final String serverUser = "user";
    private final String serverPassword = "bitnami";


    @BeforeClass
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

//    @AfterClass
//    public static void teardown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }

    @Before
    public void login() {
        driver.get(serverAddress);
        driver.findElement(By.cssSelector("#tl_login")).sendKeys(serverUser);
        driver.findElement(By.cssSelector("#tl_password")).sendKeys(serverPassword);
        driver.findElement(By.cssSelector("input[type=submit]")).click();
        driver.switchTo().frame(0);
        driver.findElement(By.cssSelector("a[accesskey=t]")).click();
    }

    @Test
    public void create() {

    }

}
