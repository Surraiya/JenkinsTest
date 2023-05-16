package Framework.BaseClass;

import Framework.Utils.BrowserUtility;
import Framework.Utils.LoggerUtil;
import Framework.Utils.WaitUtil;
import org.openqa.selenium.By;

public abstract class BaseForm {

    protected By uniqueLocator;
    protected String name;

    protected BaseForm(By locator, String name){
        this.uniqueLocator = locator;
        this.name = name;
    }

    public void openPage(){
        LoggerUtil.info("Open" + name);
        WaitUtil.waitForPageToLoad(uniqueLocator);
    }

    public Boolean isPageOpen() {
        LoggerUtil.info("Yes, " + name + " is open.");
        boolean isPresent = BrowserUtility.isElementPresent(uniqueLocator);
        if (isPresent) {
            return true;
        } else {
            return false;
        }
    }

    public String getPageUrl(){
        WaitUtil.waitForVisibilityOfElement(uniqueLocator);
        String url = BrowserUtility.currentUrl();
        LoggerUtil.info("The current url of " + name + "is: " + url);
        return url;
    }
}