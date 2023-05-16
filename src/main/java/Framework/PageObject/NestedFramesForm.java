package Framework.PageObject;

import Framework.BaseClass.BaseForm;
import Framework.Elements.Label;
import Framework.Utils.BrowserUtility;
import org.openqa.selenium.By;

public class NestedFramesForm extends BaseForm {

    private final Label parentText = new Label(By.tagName("body"), "Parent Frame Text");

    private final Label childText = new Label(By.tagName("p"), "Child Iframe text");


    public NestedFramesForm() {
        super(By.xpath("//iframe[@id ='frame1']"), "Nested Frame form");
    }

    public void switchToParentFrame() {
        BrowserUtility.switchToFrameUsingString("frame1");
    }

    public String getParentFrameText() {
        return parentText.getText();
    }

    public void switchToChildFrame() {
        BrowserUtility.switchToFrameUsingIndex(0);
    }

    public String getChildIFrameText() {
        return childText.getText();
    }

    public void switchBackToDefaultFrame() {
        BrowserUtility.switchBackFromFrame();
        BrowserUtility.switchBackToParentFrame();
    }
}
