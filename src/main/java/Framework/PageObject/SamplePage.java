package Framework.PageObject;

import Framework.BaseClass.BaseForm;
import org.openqa.selenium.By;

public class SamplePage extends BaseForm {

    public SamplePage(){
        super(By.id("sampleHeading"),"Sample Page");
    }
}
