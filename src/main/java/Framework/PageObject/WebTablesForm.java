package Framework.PageObject;

import Framework.BaseClass.BaseForm;
import Framework.Elements.Button;
import Framework.Elements.Label;
import Framework.Models.UserModel;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.util.List;

public class WebTablesForm extends BaseForm {

    private final Button add = new Button(By.id("addNewRecordButton"), "Add Button");

    private final Button delete = new Button(By.xpath("//span[@title='Delete']"),"delete Button");

    private final Label toolsQA = new Label(By.xpath("//*[@id='app']/header/a"),"Tools QA link");

    private final TableForm tableForm = new TableForm();

    private final RegistrationForm registrationForm = new RegistrationForm();

    public WebTablesForm(){
        super(By.id("addNewRecordButton"),"Web Tables Form");
    }

    public void clickOnAddButton(){
        add.clickAfterWaiting();
    }

    public int tableSize(){
        return delete.findElements().size();
    }

    @SneakyThrows
    public UserModel getRowdata(int rowIndex) {

        List<String> celldata = tableForm.getRowData(rowIndex);

        UserModel userModel = new UserModel();

        // Get all fields of UserModel using reflection
        Field[] fields = UserModel.class.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {

            // Set the field as accessible to avoid access issues
            fields[i].setAccessible(true);

            // Get the type of the field
            Class<?> fieldType = fields[i].getType();

            // Get the value from the table for the current field index
            String value = celldata.get(i);

            // Declare a variable to store the converted value
            Object convertedValue = null;

            // Use a switch statement to check the type of the field
            switch (fieldType.getSimpleName()) {
                case "int":
                    // If the field is an int, parse the value as an integer
                    if (!value.isEmpty()) {
                        convertedValue = Integer.parseInt(value);
                    }
                    break;
                case "String":
                default:
                    // If the field is a string or not defined, store the value as a string
                    convertedValue = value;
                    break;
            }

            // Set the value of the field in the UserModel object
            fields[i].set(userModel, convertedValue);
        }

        // Return the populated UserModel object
        return userModel;
    }

    public void clickOnDeleteIcon(){
        List<WebElement> elements = delete.findElements();
        int size = elements.size();
        WebElement element = elements.get(size-1);
        element.click();
    }

    public RegistrationForm getRegistrationForm(){
        return registrationForm;
    }

    public void clickOnToolsQA(){
        toolsQA.clickOnElement();
    }
}
