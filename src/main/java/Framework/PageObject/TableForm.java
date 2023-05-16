package Framework.PageObject;

import Framework.BaseClass.BaseForm;
import Framework.Elements.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TableForm extends BaseForm {

    private final Label tableLabel = new Label(By.className("rt-table"),"Table");


    public TableForm(){
        super(By.className("rt-table"), "Table element");
    }

    public List<String> getRowData(int rowIndex) {

        List<String> rowData = new ArrayList<>();

        WebElement table = tableLabel.findElement();

       List<WebElement> rows = table.findElements(By.cssSelector("div.rt-tr"));

        WebElement row = rows.get(rowIndex);

        List<WebElement> cells = row.findElements(By.cssSelector("div.rt-td"));

        for (WebElement cell : cells) {
            rowData.add(cell.getText());
        }

        return rowData;
    }
}
