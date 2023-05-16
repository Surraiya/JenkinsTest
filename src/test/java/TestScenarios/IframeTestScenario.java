package TestScenarios;

import Framework.PageObject.AlertsFrameWindowPage;
import Framework.PageObject.FramesForm;
import Framework.PageObject.MainPage;
import Framework.PageObject.NestedFramesForm;
import Framework.Utils.JsonFileReader;
import Framework.Utils.LoggerUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class IframeTestScenario extends BaseTest {

    @DataProvider(name = "IFrameTestData")
    public Object[][] testData(){
        JsonFileReader.readFilePath("src/test/resources/TestData/IFrameTestData.json");
        String nestedFramesParentMsg = (String) JsonFileReader.getValue("NestedFramesParentMsg");
        String nestedFramesChildMsg = (String) JsonFileReader.getValue("NestedFramesChildMsg");

        return new Object[][]{
                {nestedFramesParentMsg,nestedFramesChildMsg}
        };
    }

    @Test(dataProvider = "IFrameTestData")
    public void testIframes(String nestedFramesParentMsg,String nestedFramesChildMsg){

        LoggerUtil.info("Step 1: Navigate to Main page");
        MainPage mainPage = new MainPage();
        mainPage.openPage();
        Assert.assertTrue(mainPage.isPageOpen(),"Main page is not open");


        LoggerUtil.info("Step 2: Click on Alerts, Frames and Window page. In a left menu click nested Frames button and a new page will be opened and there are messages in that page.");
        mainPage.clickOnAlertFrameWindows();
        AlertsFrameWindowPage alertsFrameWindowPage = new AlertsFrameWindowPage();
        alertsFrameWindowPage.openPage();
        alertsFrameWindowPage.clickNestedFrames();
        NestedFramesForm nestedFramesForm = new NestedFramesForm();
        nestedFramesForm.openPage();
        Assert.assertTrue(nestedFramesForm.isPageOpen(),"Page with nested form is not open");

        //In Nested Frames, There are messages present
        //Switch to parent frame and save the parent Frame message
        nestedFramesForm.switchToParentFrame();
        String parentFrameText = nestedFramesForm.getParentFrameText();
        LoggerUtil.info("The parent frame message is: "+parentFrameText+". Which is equal to: "+nestedFramesParentMsg);
        Assert.assertEquals(parentFrameText, nestedFramesParentMsg,"The parent frame message is not 'Parent Frame'");

        //Switch to child frame and save the child frame text
        nestedFramesForm.switchToChildFrame();
        String childFrameText = nestedFramesForm.getChildIFrameText();
        LoggerUtil.info("Child Iframe message is: "+childFrameText);
        Assert.assertEquals(childFrameText,nestedFramesChildMsg,"Child Iframe message is not 'Child Iframe'");


        LoggerUtil.info("Step 3: Select Frames option in a left menu");
        nestedFramesForm.switchBackToDefaultFrame();
        alertsFrameWindowPage.clickFrames();
        FramesForm framesForm = new FramesForm();
        framesForm.openPage();

        //Switch to upper frame and save the upper frame text
        framesForm.switchToUpperFrame();
        String uppertext = framesForm.getUpperFrameText();

        framesForm.switchBackToDefaultFrame();

        //Switch to lower frame and save the lower frame text
        framesForm.switchToLowerFrame();
        String lowertext = framesForm.getLowerFrameText();

        LoggerUtil.info("The upper frame text is: "+ uppertext+". Which is equal to the lower frame text: "+lowertext);
        Assert.assertEquals(uppertext, lowertext,"Upper text and lower text is not same.");
    }
}
