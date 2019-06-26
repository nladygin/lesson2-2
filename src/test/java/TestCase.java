import org.apache.logging.log4j.Logger;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;


@RunWith(Parameterized.class)
public class TestCase {


    @Parameterized.Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "Test suit #1", "Test case #1", "Test case #1 description", "Test case #1 preconditions" },
                { "Test suit #1", "Test case #2", "Test case #2 description", "Test case #2 preconditions" },
                { "Test suit #2", "Test case #1", "Test case #1 description", "Test case #1 preconditions" },
                { "Test suit #2", "Test case #2", "Test case #2 description", "Test case #2 preconditions" },
        });
    }





    @Test
    public void createTestCase() {

        logger.info("createTestCase: " + testCaseName + " for " + testSuitName);

        /* move 2 test suit into the tree */
        driver.switchTo().defaultContent();
        driver.switchTo().frame("mainframe").switchTo().frame("treeframe");
        driver.findElement(By.xpath("//span[contains(text(),'" + testSuitName + "')]")).click();

        /* create new test case */
        driver.switchTo().defaultContent();
        driver.switchTo().frame("mainframe").switchTo().frame("workframe");
        driver.findElement(By.cssSelector("img[title='Actions']")).click();
        driver.findElement(By.cssSelector("#create_tc")).click();

        /* set test case name */
        driver.findElement(By.cssSelector("#testcase_name")).sendKeys(testCaseName);

        /* set test case description */
        driver.switchTo().frame(0);
        driver.findElement(By.cssSelector("body")).sendKeys(testCaseDescription);

        /* set test case preconditions */
        driver.switchTo().parentFrame();
        driver.switchTo().frame(1);
        driver.findElement(By.cssSelector("body")).sendKeys(testCasePreconditions);

        /* save test case */
        driver.switchTo().parentFrame();
        driver.findElement(By.cssSelector("#do_create_button")).click();

    }







    @Rule
    public Backbone backbone = new Backbone();

    private WebDriver driver = backbone.getDriver();
    private Logger logger = backbone.getLogger();

    private String testSuitName;
    private String testCaseName;
    private String testCaseDescription;
    private String testCasePreconditions;


    public TestCase(String testSuitName, String testCaseName, String testCaseDescription, String testCasePreconditions) {
        this.testSuitName           = testSuitName;
        this.testCaseName          = testCaseName;
        this.testCaseDescription   = testCaseDescription;
        this.testCasePreconditions = testCasePreconditions;
    }


}
