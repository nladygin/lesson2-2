import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


import java.util.concurrent.TimeUnit;

import static java.util.UUID.randomUUID;

public class TestLink {

    private static WebDriver driver;
    private final String serverAddress  = "http://192.168.137.13";
    private final String serverUser     = "user";
    private final String serverPassword = "bitnami";
    private final String projectName    = "Test project #1";


    @BeforeClass
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

//    @AfterClass
//    public static void teardown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }

    @Before
    public void loginAndSelectProject() {
        /* logon */
        driver.get(serverAddress);
        driver.findElement(By.cssSelector("#tl_login")).sendKeys(serverUser);
        driver.findElement(By.cssSelector("#tl_password")).sendKeys(serverPassword);
        driver.findElement(By.cssSelector("input[type=submit]")).click();

        /* 2 projects list */
        driver.switchTo().frame("titlebar");
        driver.findElement(By.cssSelector("a[accesskey=t]")).click();

        /* 2 the project */
        driver.switchTo().parentFrame();
        driver.switchTo().frame("mainframe").switchTo().frame("treeframe");
        driver.findElements(By.xpath("//span[contains(text(),'" + projectName + "')]")).get(0).click();
    }


    @Test
    public void doJob() {

        String testSuitUID;
        String testCaseUID;


        /* create test suit */
        testSuitUID = randomUUID().toString();
        createTestSuit(testSuitUID);


        /* create test case #1 */
        testCaseUID = randomUUID().toString();
        createTestCase(testSuitUID, testCaseUID);
        selectTestSuitAndExpand(testSuitUID);
        selectTestCase(testCaseUID);

        /* create steps */
        createTestCaseStep("Step #1 action", "Step #1 expected results");
        createTestCaseStep("Step #2 action", "Step #2 expected results");
        createTestCaseStep("Step #3 action", "Step #3 expected results");

        
        /* create test case #2 */
        testCaseUID = randomUUID().toString();

        createTestCase(testSuitUID, testCaseUID);
        selectTestCase(testCaseUID);

        /* create steps */
        createTestCaseStep("Step #1 action", "Step #1 expected results");
        createTestCaseStep("Step #2 action", "Step #2 expected results");
        createTestCaseStep("Step #3 action", "Step #3 expected results");

    }



    private void createTestSuit(String UID) {

        /* create new test suit */
        driver.switchTo().defaultContent();
        driver.switchTo().frame("mainframe").switchTo().frame("workframe");
        driver.findElement(By.cssSelector("img[title='Actions']")).click();
        driver.findElement(By.cssSelector("#new_testsuite")).click();

        /* set test suit name */
        driver.findElement(By.cssSelector("input[name=container_name]")).sendKeys("Test " + UID);

        /* set test suit description */
        driver.switchTo().frame(0);
        driver.findElement(By.cssSelector("body")).sendKeys("Test suit " + UID + " description");

        //save test suit
        driver.switchTo().parentFrame();
        driver.findElement(By.cssSelector("input[name=add_testsuite_button]")).click();

    }



    private void createTestCase(String testSuitUID, String testCaseUID) {

        /* move 2 test suit into the tree */
        driver.switchTo().defaultContent();
        driver.switchTo().frame("mainframe").switchTo().frame("treeframe");
        driver.findElement(By.xpath("//span[contains(text(),'" + testSuitUID + "')]")).click();

        /* create new test case */
        driver.switchTo().defaultContent();
        driver.switchTo().frame("mainframe").switchTo().frame("workframe");
        driver.findElement(By.cssSelector("img[title='Actions']")).click();
        driver.findElement(By.cssSelector("#create_tc")).click();

        /* set test case name */
        driver.findElement(By.cssSelector("#testcase_name")).sendKeys("Test case " + testCaseUID);

        /* set test case description */
        driver.switchTo().frame(0);
        driver.findElement(By.cssSelector("body")).sendKeys("Test case " + testCaseUID + " description");

        /* set test case preconditions */
        driver.switchTo().parentFrame();
        driver.switchTo().frame(1);
        driver.findElement(By.cssSelector("body")).sendKeys("Test case " + testCaseUID + " preconditions");

        /* save test case */
        driver.switchTo().parentFrame();
        driver.findElement(By.cssSelector("#do_create_button")).click();

    }



    private void selectTestSuitAndExpand(String testSuitUID) {

        driver.switchTo().defaultContent();
        driver.switchTo().frame("mainframe").switchTo().frame("treeframe");

        /* select test suit into the tree */
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.xpath("//span[contains(text(),'" + testSuitUID + "')]"));
        action.doubleClick(element).perform();

    }



    private void selectTestCase(String testCaseUID) {

        driver.switchTo().defaultContent();
        driver.switchTo().frame("mainframe").switchTo().frame("treeframe");

        /* select test case into the tree */
        driver.findElement(By.xpath("//span[contains(text(),'" + testCaseUID + "')]")).click();

    }



    private void createTestCaseStep(String stepAction, String stepExpectedResults) {

        /* create test case steps */
        driver.switchTo().defaultContent();
        driver.switchTo().frame("mainframe").switchTo().frame("workframe");
        driver.findElement(By.cssSelector("input[name=create_step]")).click();

        /* create step */
        driver.switchTo().defaultContent();
        driver.switchTo().frame("mainframe").switchTo().frame("workframe");
        driver.switchTo().frame(0);
        driver.findElement(By.cssSelector("body")).sendKeys(stepAction);
        driver.switchTo().parentFrame();
        driver.switchTo().frame(1);
        driver.findElement(By.cssSelector("body")).sendKeys(stepExpectedResults);

        /* save step */
        driver.switchTo().parentFrame();
        driver.findElement(By.cssSelector("#do_update_step_and_exit")).click();

    }






}
