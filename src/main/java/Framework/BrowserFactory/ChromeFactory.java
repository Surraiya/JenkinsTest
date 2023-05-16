package Framework.BrowserFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class ChromeFactory extends BrowserFactory {

    @Override
    public WebDriver createWebDriver(List<String> option) {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments(option);

        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(options);
    }
}