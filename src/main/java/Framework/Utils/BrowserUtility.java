package Framework.Utils;

import org.openqa.selenium.*;

import java.util.Set;

public class BrowserUtility {

    private static final WebDriver driver = DriverUtility.getDriver();

    public static void goToUrl(String url){

        LoggerUtil.info("Navigating url: " + url);
        driver.get(url);
    }

    public static void maximizeWindow(){

        LoggerUtil.info("Maximized the window.");
        driver.manage().window().maximize();
    }

    public static void closeTab(){
        LoggerUtil.info("Closed the current tab.");
        driver.close();
    }

    public static void scrollToElement(WebElement element){
        LoggerUtil.info("Driver has been scrolled to the element ");
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView(true);",element);
    }

    public static Boolean isElementPresent(By locator){
        WaitUtil.waitForVisibilityOfElement(locator);
        return driver.findElement(locator).isDisplayed();
    }

    public static Alert switchToAlert() {
        LoggerUtil.info("Alert presence has been spotted and driver has been switched to it.");
        WaitUtil.waitForAlertToBePresent();
        return driver.switchTo().alert();
    }

    public static String getAlertText() {
        WaitUtil.waitForAlertToBePresent();
        Alert alert = switchToAlert();
        String text = alert.getText();
        LoggerUtil.info("Alert says: " + text);
        return text;
    }

    public static void acceptAlert() {
        LoggerUtil.info("Accepted the alert by pressing OK");
        Alert alert = switchToAlert();
        alert.accept();
    }

    public static void typeTextIntoAlert(String text) {
        LoggerUtil.info("Typed text into alert and pressed Ok");
        Alert alert = BrowserUtility.switchToAlert();
        alert.sendKeys(text);
        alert.accept();
    }

    public static void switchToFrameUsingString(String name){
        LoggerUtil.info("A new frame has been available and driver has been switched to it.");
        driver.switchTo().frame(name);
    }

    public static void switchToFrameUsingIndex(int index) {
        LoggerUtil.info("A new frame has been available and driver has been switched to it.");
        driver.switchTo().frame(index);
    }

    /**Switch back to parent Html Page from Frame*/
    public static void switchBackFromFrame(){
        LoggerUtil.info("Driver has been switched back from child frame to parent frame");
        driver.switchTo().defaultContent();
    }

    public static void switchBackToParentFrame(){
        LoggerUtil.info("Driver has been switched back from child frame to parent frame");
        driver.switchTo().parentFrame();
    }

    public static void switchToNewWindow() {
        String parentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        for (String childWindow : allWindows) {
            if (!childWindow.equalsIgnoreCase(parentWindow)) {
                LoggerUtil.info("Driver has been switched to the new window.");
                driver.switchTo().window(childWindow);
                break;
            }
        }
    }

    public static void switchBackToParentWindow(String parentWindowHandle){
        LoggerUtil.info("Driver has been switched back to the previous window.");
        driver.switchTo().window(parentWindowHandle);
    }

    public static String currentUrl(){
        return driver.getCurrentUrl();
    }

    public static boolean isNewTabOpened(int initialNumberOfWindows) {

        // Get a set of all open window handles
        Set<String> windowHandles = driver.getWindowHandles();
        LoggerUtil.info("Window Handles size: " + windowHandles.size());

        // Check if the number of window handles has increased by 1
        if (windowHandles.size() == initialNumberOfWindows + 1) {
            LoggerUtil.info("A New window has been opened.");
            return true;
        } else {
            return false;
        }
    }

    public static int getInitialNumbOfWindows(){
        int initialNumberOfWindows = driver.getWindowHandles().size();
        LoggerUtil.info("Initially the number of windows that are open is: "+ initialNumberOfWindows);
        return initialNumberOfWindows;
    }

    public static String getWindowHandle(){
        String currentHandle = driver.getWindowHandle();
        LoggerUtil.info("The window handle is: " + currentHandle);
        return currentHandle;
    }
}
