package TestScenarios;

import Framework.Utils.BrowserUtility;
import Framework.Utils.ConfigManager;
import Framework.Utils.DriverUtility;
import Framework.Utils.LoggerUtil;
import org.apache.logging.log4j.core.config.Configurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeSuite
    public void setup(){

        LoggerUtil.info("The logger configuration is done");
        Configurator.initialize(null, "log4j2.xml");

        LoggerUtil.info("Initialized the Driver");
        driver = DriverUtility.getDriver();
    }

    @BeforeMethod
    public void setting(){
        BrowserUtility.goToUrl(ConfigManager.getUrl());
        BrowserUtility.maximizeWindow();
    }

    @AfterSuite
    public void tearDown(){

        LoggerUtil.info("Driver has been quited successfully.");
        DriverUtility.cleanup();
    }
}
