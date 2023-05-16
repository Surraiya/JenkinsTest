package TestScenarios;

import Framework.PageObject.*;
import Framework.Utils.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HandlesTestScenario extends BaseTest{

    @DataProvider(name = "HandlesTestData")
    public Object[][] testData(){

        JsonFileReader.readFilePath("src/test/resources/TestData/HandlesTestData.json");
        String newPageKey = (String) JsonFileReader.getValue("newPageKey");

        return new Object[][]{
                {newPageKey}
        };
    }

    @Test(dataProvider = "HandlesTestData")
    public void testHandles(String newPageKey){

        LoggerUtil.info("Step 1: Navigate to Main page");
        MainPage mainPage = new MainPage();
        mainPage.openPage();
        Assert.assertTrue(mainPage.isPageOpen(),"Main page is not open");


        LoggerUtil.info("Step 2: Click on Alerts, Frames and Window page.In the left menu, click browser windows");
        mainPage.clickOnAlertFrameWindows();
        AlertsFrameWindowPage alertsFrameWindowPage = new AlertsFrameWindowPage();
        alertsFrameWindowPage.openPage();
        alertsFrameWindowPage.clickBrowserWindows();
        BrowserWindowsForm browserWindowsForm = new BrowserWindowsForm();
        browserWindowsForm.openPage();
        Assert.assertTrue(browserWindowsForm.isPageOpen(),"Browser Windows page is not open.");


        LoggerUtil.info("Step 3: Click on New Tab.");
        //Save the initial Number of windows opened
        int initalNumOfWindows = BrowserUtility.getInitialNumbOfWindows();
        browserWindowsForm.clickOnNewTabButton();
        WaitUtil.waitForNewWindowToBeOpened(initalNumOfWindows);
        //Save the browser Windows Page's Window handle before switching the tab
        String browserPageWindowHandle = BrowserUtility.getWindowHandle();
        BrowserUtility.switchToNewWindow();
        Assert.assertTrue(BrowserUtility.isNewTabOpened(initalNumOfWindows),"New window has not been opened");
        WaitUtil.waiUnitlUrlContains(newPageKey);
        SamplePage samplePage = new SamplePage();
        samplePage.openPage();
        LoggerUtil.info("New tab with sample page has been opened.");
        Assert.assertTrue(samplePage.getPageUrl().contains(newPageKey),"New tab with sample page has not been opened.");


        LoggerUtil.info("Step 4: Close current tab.");
        BrowserUtility.closeTab();
        //Switch back to browser windows page
        BrowserUtility.switchBackToParentWindow(browserPageWindowHandle);


        LoggerUtil.info("Step 5: In the left menu, click elements then click link button");
        Assert.assertTrue(browserWindowsForm.isPageOpen(),"Browser Window page is not open");
        browserWindowsForm.clickElements();
        browserWindowsForm.clickOnLinks();
        LinksForm linksForm = new LinksForm();
        linksForm.openPage();
        Assert.assertTrue(linksForm.isPageOpen(),"Page with Links form is not open.");


        LoggerUtil.info("Step 6: Click on Home link and new tab with main page will be open.");
        //Save the initial Number of windows opened
        int currentNumOfWindows = BrowserUtility.getInitialNumbOfWindows();
        //Save the links form Page Window Handle
        String linksWindowHandle = BrowserUtility.getWindowHandle();
        linksForm.clickOnHome();
        WaitUtil.waitForNewWindowToBeOpened(currentNumOfWindows);
        BrowserUtility.switchToNewWindow();
        LoggerUtil.info("Current tabs number:"+currentNumOfWindows);
        Assert.assertTrue(BrowserUtility.isNewTabOpened(currentNumOfWindows),"New window has not been opened");
        mainPage.openPage();
        LoggerUtil.info("New tab with main page is open.");
        Assert.assertEquals(mainPage.getPageUrl(), ConfigManager.getUrl(), "currentUrl is not the main page url");


        LoggerUtil.info("Step 7: Resume to previous tab and page with Links form will be open");
        BrowserUtility.switchBackToParentWindow(linksWindowHandle);
        LoggerUtil.info("Previous Links form page is open.");
        Assert.assertTrue(linksForm.isPageOpen(),"Page with Links form is not open.");
    }
}
