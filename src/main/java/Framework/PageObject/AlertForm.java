package Framework.PageObject;

import Framework.BaseClass.BaseForm;
import Framework.Elements.Button;
import Framework.Elements.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AlertForm extends BaseForm {

    private final Button alertButton = new Button(By.xpath("//button[@id = 'alertButton']"), "Click Button to see Alert");

    private final Button confirmBoxButton = new Button(By.id("confirmButton"), "On button click, confirm box will appear");

    private final Button promptBoxButton = new Button(By.id("promtButton"), "On button click, prompt box will appear");

    private final Label resultText = new Label(By.id("confirmResult"), "Result text of Ok Button");

    private final Label promptResultText = new Label(By.id("promptResult"), "Prompt Result Text");


    public AlertForm() {
        super(By.id("timerAlertButton"), "Alert Form");
    }

    public void clickButtonToSeeAlert() {
        alertButton.clickOnElement();
    }

    public void clickConfirmBoxAlert() {
        confirmBoxButton.clickOnElement();
    }

    public void clickPromptBoxAlert() {
        promptBoxButton.clickOnElement();
    }

    public String getResultText() {
        WebElement element = resultText.findElement();
        return element.getText();
    }

    public String getPromptResultText() {
        WebElement element = promptResultText.findElement();
        return element.getText();
    }
}
