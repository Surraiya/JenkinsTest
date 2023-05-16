package Framework.Elements;

import Framework.BaseClass.BaseElement;
import Framework.Utils.LoggerUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TextField extends BaseElement {

    public TextField(By locator, String name){
        super(locator, name);
    }

    public void sendText(String keys){
        LoggerUtil.info(elementName + "has been set to "+keys);
        WebElement element = findElement();
        element.click();
        element.clear();
        element.sendKeys(keys);
    }
}
