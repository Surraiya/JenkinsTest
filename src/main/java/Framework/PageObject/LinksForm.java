package Framework.PageObject;

import Framework.BaseClass.BaseForm;
import Framework.Elements.Label;
import org.openqa.selenium.By;

public class LinksForm extends BaseForm {

    private final Label homeLink = new Label(By.id("simpleLink"), "Home Link");


    public LinksForm() {
        super(By.id("simpleLink"), "Links Form");
    }

    public void clickOnHome() {
        homeLink.clickAfterWaiting();
    }
}
