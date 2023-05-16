package Framework.PageObject;

import Framework.BaseClass.BaseForm;
import Framework.Elements.Button;
import org.openqa.selenium.By;

public class ElementsPage extends BaseForm {

    private final Button webTable = new Button(By.xpath("//li[@id='item-3']//span[@class='text'][contains(text(),'Web Tables')]"), "Web Tables Button");


    public ElementsPage() {
        super(By.xpath("//div[@class='header-text' and contains(text(),'Elements')]"), "Element Page");
    }

    public void clickWebTables() {
        webTable.clickAfterWaiting();
    }
}
