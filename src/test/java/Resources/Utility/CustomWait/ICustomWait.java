package Resources.Utility.CustomWait;

import org.openqa.selenium.By;

public interface ICustomWait {

    boolean waitUntilElementIsVisible(By element);

    boolean waitUntilElementIsVisibleAndClickable(By element);

    boolean waitUntilElementIsInvisible(By element);
}
