package TestScenarios;

import Framework.PageObject.AlertForm;
import Framework.PageObject.AlertsFrameWindowPage;
import Framework.PageObject.MainPage;
import Framework.Utils.BrowserUtility;
import Framework.Utils.JsonFileReader;
import Framework.Utils.LoggerUtil;
import Framework.Utils.RandomStringsGenerator;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AlertsTestScenario extends BaseTest {

    @DataProvider(name = "AlertsTestData")
    public Object[][] testData(){
        JsonFileReader.readFilePath("src/test/resources/TestData/AlertsTestData.json");
        String clickButtonText = (String) JsonFileReader.getValue("clickButtonText");
        String confirmBoxText = (String) JsonFileReader.getValue("confirmBoxText");
        String okButtonText = (String) JsonFileReader.getValue("okButtonText");
        String promptBoxText = (String) JsonFileReader.getValue("promptBoxText");
        int nameMinLength = ((Long) JsonFileReader.getValue("nameMinLength")).intValue();
        int nameMaxLength = ((Long) JsonFileReader.getValue("nameMaxLength")).intValue();

        return new Object[][]{
                {clickButtonText, confirmBoxText, okButtonText, promptBoxText, nameMinLength, nameMaxLength}
        };
    }

    @Test(dataProvider = "AlertsTestData")
    public void TestAlerts(String clickButtonText, String confirmBoxText, String okButtonText, String promptBoxText, int nameMinLength, int nameMaxLength) {

        LoggerUtil.info("Step 1: Navigate to mainpage.");
        MainPage mainPage = new MainPage();
        mainPage.openPage();
        Assert.assertTrue(mainPage.isPageOpen(),"Main page is not open");


        LoggerUtil.info("Step 2: Click on alerts,frames and windows button. In a menu, click Alerts");
        mainPage.clickOnAlertFrameWindows();
        AlertsFrameWindowPage alertsFrameWindowPage = new AlertsFrameWindowPage();
        alertsFrameWindowPage.openPage();
        Assert.assertTrue(alertsFrameWindowPage.isPageOpen(),"Alert form has not appeard on page");
        alertsFrameWindowPage.clickAlerts();
        AlertForm alertForm = new AlertForm();
        alertForm.openPage();


        LoggerUtil.info("Step 3: Click on 'Click Button to see alert', Save Alert Text and accept alert then verify the alert text");
        alertForm.clickButtonToSeeAlert();
        String alertText = BrowserUtility.getAlertText();


        LoggerUtil.info("Step 4: Click Ok button");
        BrowserUtility.acceptAlert();
        LoggerUtil.info("Alert text is: "+ alertText+". Which is equal to: "+ clickButtonText);
        Assert.assertEquals(alertText, clickButtonText,"The text 'You clicked a button' did not appear");


        LoggerUtil.info("Step 5: click on 'On button click, confirm box will appear'. Verify the confirm box text");
        alertForm.clickConfirmBoxAlert();
        String confirmBoxAlertText = BrowserUtility.getAlertText();
        Assert.assertEquals(confirmBoxAlertText, confirmBoxText,"The text 'Do you confirm action?' did not appear");


        LoggerUtil.info("Step 6: click Ok button. Verify the result Text");
        BrowserUtility.acceptAlert();
        String okText = alertForm.getResultText();
        LoggerUtil.info("The ok text is: "+ okText +". Which is equal to: "+ okButtonText);
        Assert.assertEquals(okText, okButtonText,"The text 'You selected Ok' did not appear");


        LoggerUtil.info("Step 7: click on 'On button click, prompt box will appear' then verify the prompt text.");
        alertForm.clickPromptBoxAlert();
        String promptText = BrowserUtility.getAlertText();
        LoggerUtil.info("The ok text is: "+ promptText +". Which is equal to: "+ promptBoxText);
        Assert.assertEquals(promptText, promptBoxText,"The text 'Please enter your name' did not appear");


        LoggerUtil.info("Step 8: Enter randomly generated text and click on Ok button. Verify the appeared text is the randomly generated text");
        String randomlyGeneratedText = RandomStringsGenerator.generateRandomName(nameMinLength,nameMaxLength);
        BrowserUtility.typeTextIntoAlert(randomlyGeneratedText);
        String promptResult = alertForm.getPromptResultText();
        LoggerUtil.info("The text I entered before was: "+ randomlyGeneratedText +". Which is equal to the Result text of prompt Box: "+ promptResult);
        Assert.assertTrue(promptResult.contains(randomlyGeneratedText),"Prompt result text box is not equal to randomly generated text");
    }
}
