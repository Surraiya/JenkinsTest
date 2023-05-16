package Framework.PageObject;

import Framework.BaseClass.BaseForm;
import Framework.Elements.Button;
import org.openqa.selenium.By;

public class MainPage extends BaseForm {

    private final Button alertsFramesWindows = new Button(By.xpath("//div[@class='card-body' and contains(h5,'Alerts, Frame & Windows')]"), "Alerts, Frames and Windows");

    private final Button elements = new Button(By.xpath("//div[@class='card-body' and contains(h5,'Elements')]"),"Elements button");


    public MainPage(){
        super(By.xpath("//div[@class='card-body' and contains(h5,'Elements')]"), "Main Page");
    }

    public void clickOnAlertFrameWindows(){
        alertsFramesWindows.clickAfterWaiting();
    }

    public void clickOnElements(){
        elements.clickAfterWaiting();
    }
}
