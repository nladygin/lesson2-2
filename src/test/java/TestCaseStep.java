import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class TestCaseStep {

    private static WebDriver driver;


    @Parameterized.Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "Step action #1", "Step expected results #1" },
                { "Step action #2", "Step expected results #2" },
                { "Step action #3", "Step expected results #3" },
        });
    }






    @Test
    private void createTestCaseStep() {

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





    private String stepAction;
    private String stepExpectedResults;

    public TestCaseStep(String stepAction, String stepExpectedResults) {
        this.stepAction = stepAction;
        this.stepExpectedResults = stepExpectedResults;
    }

}