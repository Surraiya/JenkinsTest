package TestScenarios;

import Framework.Models.UserModel;
import Framework.PageObject.ElementsPage;
import Framework.PageObject.MainPage;
import Framework.PageObject.RegistrationForm;
import Framework.PageObject.WebTablesForm;
import Framework.Utils.JsonFileReader;
import Framework.Utils.LoggerUtil;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TablesTestScenario extends BaseTest{

    @DataProvider(name = "users")
    public static Object[][] userDataProvider() throws IllegalAccessException {
        // Get the list of users from the test data file
        JsonFileReader.readFilePath("src/test/resources/TestData/TablesTestData.json");
        List<UserModel> users = getUsers();

        // Get the number of fields in the UserModel class
        int numFields = UserModel.class.getDeclaredFields().length;

        // Create a two-dimensional array to hold the user data, with one row for each user
        Object[][] data = new Object[users.size()][numFields];

        for (int i = 0; i < users.size(); i++) {
            UserModel user = users.get(i);
            // Get all declared fields in the UserModel class
            Field[] fields = UserModel.class.getDeclaredFields();
            for (int j = 0; j < fields.length; j++) {
                // Make each field accessible so that its value can be retrieved
                fields[j].setAccessible(true);
                // Get the value of the field from the user object
                Object fieldValue = fields[j].get(user);
                // Set the value in the corresponding row and column in the data array
                data[i][j] = fieldValue;
            }
        }

        return data;
    }

    public static List<UserModel> getUsers() {
        List<UserModel> users = new ArrayList<>();
        List<JSONObject> jsonList = JsonFileReader.getJsonObjectList();

        for (JSONObject userJson : jsonList) {

            String firstname = (String) userJson.get("FirstName");
            String lastname = (String) userJson.get("LastName");
            String email = (String) userJson.get("Email");
            int age = ((Long) userJson.get("Age")).intValue();
            int salary = ((Long) userJson.get("Salary")).intValue();
            String department = (String) userJson.get("Department");

            UserModel userModel = new UserModel(firstname, lastname, age, email, salary, department);
            users.add(userModel);
        }

        return users;
    }

    @Test(dataProvider = "users")
    public void testTables(String firstName, String lastName, int age, String email, int salary, String department) {

        LoggerUtil.info("Step 1. Navigate to mainPage. Verify that main page is open");
        MainPage mainPage = new MainPage();
        mainPage.openPage();
        Assert.assertTrue(mainPage.isPageOpen(),"Main page is not open");


        LoggerUtil.info("Step 2. Click on elements button. In the menu, click on Web Tables Button. Verify that page with web tables is open");
        mainPage.clickOnElements();
        ElementsPage elementsPage = new ElementsPage();
        elementsPage.openPage();
        elementsPage.clickWebTables();
        WebTablesForm webTablesForm = new WebTablesForm();
        webTablesForm.openPage();
        Assert.assertTrue(webTablesForm.isPageOpen(),"Web Tables Form is not open");


        LoggerUtil.info("Step 3. Click on Add Button. Verify that Registration form has appeared on the page.");
        //Save the number of records of the table as previous table records
        int previousTableRecords = webTablesForm.tableSize();
        webTablesForm.clickOnAddButton();
        RegistrationForm registrationForm = webTablesForm.getRegistrationForm();
        registrationForm.openPage();
        Assert.assertTrue(registrationForm.isPageOpen(),"Regestration Form has not openned");


        LoggerUtil.info("Step 4. Enter data for user data from table and click submit Button. Verify that user data has appeared in a table");
        //Enter user data
        registrationForm.setFirstName(firstName);
        registrationForm.setLastName(lastName);
        registrationForm.setEmail(email);
        registrationForm.setAge(String.valueOf(age));
        registrationForm.setSalary(String.valueOf(salary));
        registrationForm.setDepartment(department);
        registrationForm.clickSubmit();

        //Verify that number of records in table has changed
        int currentTableRecords = webTablesForm.tableSize();
        LoggerUtil.info("Current Table size:"+currentTableRecords);
        LoggerUtil.info("Previous table size:"+previousTableRecords);
        LoggerUtil.info("Previous table records were: "+ previousTableRecords+" Current Table records: "+currentTableRecords);
        Assert.assertEquals(currentTableRecords,previousTableRecords+1,"Number of records in table has not changed");

        //Verify that user data has appeared in Table
        UserModel userdata = new UserModel(firstName, lastName, age, email, salary, department);
        LoggerUtil.info("User Data from test data: "+ userdata);
        UserModel lastrowData = webTablesForm.getRowdata(currentTableRecords);
        LoggerUtil.info("Row data: "+ lastrowData);
        Assert.assertEquals(lastrowData, userdata,"Last Row data and user list is same");


        LoggerUtil.info("Step 5. Click Delete button in a row which contains user data. Verify that table size has changed");
        webTablesForm.clickOnDeleteIcon();

        //Verify that table size has changed
        int tableRecordsAfterDelete = webTablesForm.tableSize();
        Assert.assertNotEquals(currentTableRecords, tableRecordsAfterDelete,"Table record has not changed");

        //Get the last row of table data after delete to verify that last row data is not the user data which proves that user data has been deleted
        LoggerUtil.info("User data has been deleted. As after delete total row is: "+ webTablesForm.tableSize());
        UserModel lastrowDataAfterDelete = webTablesForm.getRowdata(webTablesForm.tableSize());
        Assert.assertNotEquals(lastrowData, lastrowDataAfterDelete,"User data has not been deleted");
        webTablesForm.clickOnToolsQA();
    }
}
