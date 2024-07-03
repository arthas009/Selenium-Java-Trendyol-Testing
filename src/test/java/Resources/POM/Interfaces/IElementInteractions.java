package Resources.POM.Interfaces;

import org.openqa.selenium.By;

public interface IElementInteractions {

    void inputText(By element, String text);

    void clickElement(By element);

    void clickBodyAndElement(By element);

    boolean verifyElementExists(By element);

    void hoverElement(By element);

    String getElementText(By element);
}
