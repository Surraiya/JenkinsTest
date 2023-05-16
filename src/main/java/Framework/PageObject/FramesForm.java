package Framework.PageObject;

import Framework.BaseClass.BaseForm;
import Framework.Elements.Label;
import Framework.Utils.BrowserUtility;
import org.openqa.selenium.By;

public class FramesForm extends BaseForm {

    private final Label upperText = new Label(By.xpath("//h1[@id='sampleHeading']"), "Upper IFrame Text");

    private final Label lowerText = new Label(By.xpath("//h1[@id='sampleHeading']"),"Child Iframe text");


    public FramesForm(){
        super(By.id("frame1Wrapper"), "Frames form");
    }

    public void switchToUpperFrame(){
        BrowserUtility.switchToFrameUsingString("frame1");
    }

    public String getUpperFrameText(){
        return upperText.getText();
    }

    public void switchBackToDefaultFrame(){
        BrowserUtility.switchBackFromFrame();
    }

    public void switchToLowerFrame(){
        BrowserUtility.switchToFrameUsingString("frame2");
    }

    public String getLowerFrameText(){
        return lowerText.getText();
    }
}
