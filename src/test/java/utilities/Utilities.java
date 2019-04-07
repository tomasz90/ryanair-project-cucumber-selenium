package utilities;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import pageobjects.TestBase;

public class Utilities {
    public static void pressKey(Keys key) {
    Actions act = new Actions(TestBase.getDriver());
    act.keyDown(key).perform();
    }
}
