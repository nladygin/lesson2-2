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
public class TestSuit {


    @Parameterized.Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "Test suit #1", "Test suit #1 description" },
                { "Test suit #2", "Test suit #2 description" },
        });
    }




    @Test
    public void createTestSuit(){

        logger.info("createTestSuit: " + testSuitName);

        /* create new test suit */
        driver.switchTo().defaultContent();
        driver.switchTo().frame("mainframe").switchTo().frame("workframe");
        driver.findElement(By.cssSelector("img[title='Actions']")).click();
        driver.findElement(By.cssSelector("#new_testsuite")).click();

        /* set test suit name */
        driver.findElement(By.cssSelector("input[name=container_name]")).sendKeys(testSuitName);

        /* set test suit description */
        driver.switchTo().frame(0);
        driver.findElement(By.cssSelector("body")).sendKeys(testSuitDescription);

        //save test suit
        driver.switchTo().parentFrame();
        driver.findElement(By.cssSelector("input[name=add_testsuite_button]")).click();

    }



    @Rule
    public Backbone backbone = new Backbone();

    private WebDriver driver = backbone.getDriver();
    private Logger logger = backbone.getLogger();

    private String testSuitName;
    private String testSuitDescription;

    public TestSuit(String testSuitName, String testSuitDescription) {
        this.testSuitName         = testSuitName;
        this.testSuitDescription  = testSuitDescription;
    }

}
