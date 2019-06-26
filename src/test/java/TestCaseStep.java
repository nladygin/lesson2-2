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
public class TestCaseStep {


    @Parameterized.Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "Test suit #1", "Test case #1", "Step action #1", "Step expected results #1" },
                { "Test suit #1", "Test case #1", "Step action #2", "Step expected results #2" },
                { "Test suit #1", "Test case #1", "Step action #3", "Step expected results #3" },
                { "Test suit #1", "Test case #2", "Step action #1", "Step expected results #1" },
                { "Test suit #1", "Test case #2", "Step action #2", "Step expected results #2" },
                { "Test suit #1", "Test case #2", "Step action #3", "Step expected results #3" },
                { "Test suit #2", "Test case #1", "Step action #1", "Step expected results #1" },
                { "Test suit #2", "Test case #1", "Step action #2", "Step expected results #2" },
                { "Test suit #2", "Test case #1", "Step action #3", "Step expected results #3" },
                { "Test suit #2", "Test case #2", "Step action #1", "Step expected results #1" },
                { "Test suit #2", "Test case #2", "Step action #2", "Step expected results #2" },
                { "Test suit #2", "Test case #2", "Step action #3", "Step expected results #3" },
        });
    }






    @Test
    public void createTestCaseStep() {

        logger.info("createTestCaseStep: " + stepAction + " for " + testCaseName);

        /* select test case */
        driver.switchTo().defaultContent();
        driver.switchTo().frame("mainframe").switchTo().frame("treeframe");
        driver.findElement(By.xpath("//span[contains(text(),'" + testSuitName + "')]//following::span[contains(text(),'" + testCaseName + "')]")).click();

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
        driver.switchTo().defaultContent();
        driver.switchTo().frame("mainframe").switchTo().frame("workframe");
        driver.findElement(By.cssSelector("#do_update_step_and_exit")).click();

        /* waiting for save */
        driver.findElement(By.cssSelector("input[name=create_step]"));
    }






    @Rule
    public Backbone backbone = new Backbone();

    private WebDriver driver = backbone.getDriver();
    private Logger logger = backbone.getLogger();

    private String testSuitName;
    private String testCaseName;
    private String stepAction;
    private String stepExpectedResults;

    public TestCaseStep(String testSuitName, String testCaseName, String stepAction, String stepExpectedResults) {
        this.testSuitName        = testSuitName;
        this.testCaseName        = testCaseName;
        this.stepAction          = stepAction;
        this.stepExpectedResults = stepExpectedResults;
    }

}
