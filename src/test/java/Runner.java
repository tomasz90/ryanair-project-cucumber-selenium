import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import pageobjects.TestBase;


@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/features", glue="stepdefs")
public class Runner {

    @BeforeClass
    public static void setUp(){
        TestBase.setDriver();
        System.out.println("this is executed");
    }
}
