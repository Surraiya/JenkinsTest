package Framework.PageObject;

import Framework.BaseClass.BaseForm;
import Framework.Elements.Button;
import Framework.Elements.TextField;
import org.openqa.selenium.By;

public class RegistrationForm extends BaseForm {

    private final TextField firstName = new TextField(By.xpath("//input[@id = 'firstName']"), "First Name TextField");

    private final TextField lastName = new TextField(By.xpath("//input[@id = 'lastName']"),"Last Name TextField");

    private final TextField email = new TextField(By.xpath("//input[@id = 'userEmail']"),"Email TextField");

    private final TextField age = new TextField(By.xpath("//input[@id = 'age']"),"Age TextField");

    private final TextField salary = new TextField(By.xpath("//input[@id = 'salary']"), "Salary TextField");

    private final TextField department = new TextField(By.xpath("//input[@id = 'department']"),"Department TextField");

    private final Button submit = new Button(By.xpath("//button[@id='submit']"),"Submit Button");


    public RegistrationForm(){
        super(By.className("modal-content"), "Registration Form");
    }

    public void setFirstName(String name){
        firstName.sendText(name);
    }

    public void setLastName(String name){
        lastName.sendText(name);
    }

    public void setEmail(String mail){
        email.sendText(mail);
    }

    public void setAge(String keys){
        age.sendText(keys);
    }

    public void setSalary(String keys){
        salary.sendText(keys);
    }

    public void setDepartment(String keys){
        department.sendText(keys);
    }

    public void clickSubmit(){
        submit.clickOnElement();
    }
}
