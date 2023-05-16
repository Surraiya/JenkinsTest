package Framework.Utils;

import Framework.BrowserFactory.BrowserFactory;
import org.openqa.selenium.WebDriver;

public class DriverUtility {

    private static WebDriver driver = null;

    private DriverUtility(){}

    public static WebDriver getDriver(){

        if (driver == null) {
            LoggerUtil.info("Driver is: "+ driver);
            BrowserFactory factory = BrowserFactory.getFactory(ConfigManager.getBrowserFromSystemProperty());
            driver = factory.createWebDriver(ConfigManager.getOptions());
        }
        LoggerUtil.info("Driver is now: " + driver);
        return driver;
    }

    public static void cleanup() {

        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
