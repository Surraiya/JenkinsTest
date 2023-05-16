package Framework.PageObject;

import Framework.BaseClass.BaseForm;
import Framework.Elements.Button;
import org.openqa.selenium.By;

public class BrowserWindowsForm extends BaseForm {

    private final Button newTab = new Button(By.xpath("//button[@id = 'tabButton']"), "New Tab Button");

    private final Button elements = new Button(By.xpath("//div[@class='header-text' and contains(text(),'Elements')]"), "Elements button");

    private final Button link = new Button(By.xpath("//li[@id='item-5']//span[@class='text'][contains(text(),'Links')]"), "Links button");


    public BrowserWindowsForm() {
        super(By.id("tabButtonWrapper"), "Browser Windows page");
    }

    public void clickOnNewTabButton() {
        newTab.clickOnElement();
    }

    public void clickElements() {
        elements.clickOnElement();
    }

    public void clickOnLinks() {
        link.clickAfterWaiting();
    }
}
