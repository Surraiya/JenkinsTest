package Framework.PageObject;

import Framework.BaseClass.BaseForm;
import Framework.Elements.Button;
import org.openqa.selenium.By;

public class AlertsFrameWindowPage extends BaseForm {

    private final Button alerts = new Button(By.xpath("//span[@class='text' and text()='Alerts']/parent::li"), "Alerts Button");

    private final Button nestedFrames = new Button(By.xpath("//span[@class='text'][contains(text(),'Nested Frames')]"), "Nested Frames Button");

    private final Button frames = new Button(By.xpath("//li[@id='item-2']//span[@class='text'][contains(text(),'Frames')]"), "Frames Button");

    private final Button browserWindows = new Button(By.xpath("//li[@id='item-0']//span[@class='text'][contains(text(),'Browser Windows')]"), "Browser Windows Button");


    public AlertsFrameWindowPage() {
        super(By.xpath("//div[@class = 'left-pannel']"), "Alerts, Frames and Windows Page");
    }

    public void clickAlerts() {
        alerts.clickAfterWaiting();
    }

    public void clickNestedFrames() {
        nestedFrames.clickAfterWaiting();
    }

    public void clickFrames() {
        frames.clickAfterWaiting();
    }

    public void clickBrowserWindows() {
        browserWindows.clickAfterWaiting();
    }
}


