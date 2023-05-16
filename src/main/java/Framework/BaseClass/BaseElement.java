package Framework.BaseClass;

import Framework.Utils.BrowserUtility;
import Framework.Utils.DriverUtility;
import Framework.Utils.LoggerUtil;
import Framework.Utils.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public abstract class BaseElement {

    protected By uniqueLocator;
    protected String elementName;
    private WebDriver driver = DriverUtility.getDriver();

    protected BaseElement(By locator, String elementName){
        this.uniqueLocator = locator;
        this.elementName = elementName;
    }

    public WebElement findElement(){
        LoggerUtil.info("Found: " + elementName);
        return driver.findElement(uniqueLocator);
    }

    public WebElement findElementWithWait(){
        LoggerUtil.info("Found " + elementName + "after waiting");
        WaitUtil.waitForVisibilityOfElement(uniqueLocator);
        return driver.findElement(uniqueLocator);
    }

    public List<WebElement> findElements(){
        LoggerUtil.info("Found: " + elementName);
        return driver.findElements(uniqueLocator);
    }

    public String getText(){
        LoggerUtil.info("Got text from: "+ elementName);
        return driver.findElement(uniqueLocator).getText();
    }

    public void clickOnElement(){
        LoggerUtil.info("Clicked on " + elementName);
        findElement().click();
    }

    public void clickAfterWaiting(){
        LoggerUtil.info("Clicked on "+ elementName + "after waiting for it");
        WebElement element = findElement();
        BrowserUtility.scrollToElement(element); //As another element, an iframe may cover the element. That's why it's better to scroll to the element
        WaitUtil.waitForElementToBeClickable(uniqueLocator); //and wait for the element to be clickable.
        element.click();
    }

    public Boolean isElementDisplayed(){
        LoggerUtil.info(elementName + "is displayed.");
        return findElement().isDisplayed();
    }

    public void clickWhenElementIsDisplayed(){
        LoggerUtil.info("Clicked when " + elementName + "is displayed.");
        WebElement element = findElement();
        if(element.isDisplayed()){
            element.click();
        }
    }

    public void clickWhenElementIsCovered(){
        LoggerUtil.info("Clicked on " + elementName + "when it is covered by other element");
        WebElement element = findElement();
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
    }
}
