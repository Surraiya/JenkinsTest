package Framework.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtil {

    private static WebDriverWait wait = new WebDriverWait(DriverUtility.getDriver(), Duration.ofSeconds(ConfigManager.getTimeUnit()));

    public static void waitForVisibilityOfElement(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitUntilVisibilityOfAllElementsLocated(By locator){
        WebElement element = DriverUtility.getDriver().findElement(locator);
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }

    public static void waitForPageToLoad(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitForAlertToBePresent(){
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public static void waitForElementToBeClickable(By locator){
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForFrameToBeAvailable(WebElement element){
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
    }

    public static void waiUnitlUrlContains(String key){
        wait.until(ExpectedConditions.urlContains(key));
    }

    public static void waitForNewWindowToBeOpened(int initialNumOfWindows){
        wait.until(ExpectedConditions.numberOfWindowsToBe(initialNumOfWindows+1));
    }
}
