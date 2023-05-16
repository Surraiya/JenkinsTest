package Framework.BrowserFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.List;

class FirefoxFactory extends BrowserFactory {

    public WebDriver createWebDriver(List<String> option) {

        FirefoxOptions options = new FirefoxOptions();
        options.addArguments(option);

        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(options);
    }
}