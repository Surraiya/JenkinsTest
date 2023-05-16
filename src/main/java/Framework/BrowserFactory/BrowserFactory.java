package Framework.BrowserFactory;

import org.openqa.selenium.WebDriver;

import java.util.List;

public abstract class BrowserFactory {

    public abstract WebDriver createWebDriver(List<String> options);

    public static BrowserFactory getFactory(String browser) {

        return switch (browser.toLowerCase()) {
            case "chrome" -> new ChromeFactory();
            case "firefox" -> new FirefoxFactory();
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }
}

