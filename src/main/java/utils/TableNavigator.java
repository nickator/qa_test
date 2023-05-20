package utils;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class TableNavigator {

    public static String getColumnIndex(String text) {
        String xpath = String.format("//table[contains(@class,'table')]//th[text()='%s']/preceding-sibling::th", text);
        return String.valueOf($$x(xpath).size() + 1);
    }

    public static String getFirstFieldValueByColumnIndex(String text) {
        String path = String.format("//table[contains(@class,'table')]//th[text()='%s']/following::td[%s]",  text, getColumnIndex(text));
        return $x(path).getText();
    }


}
