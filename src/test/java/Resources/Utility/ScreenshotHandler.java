package Resources.Utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ScreenshotHandler
 * This class is used when errors needed to be screenshotted.
 */
public class ScreenshotHandler extends TestListenerAdapter {
    String folder_path;
    WebDriver driver;

    /**
     * Constructor for ScreenshotHandler
     * Sets folder path of screenshots
     */
    public ScreenshotHandler() {
        YamlReader configurationReader = new YamlReader("src/test/resources/configuration.yaml");
        folder_path = configurationReader.getString("TEST_OUTPUT_DIRECTORY") + "/Screenshots/";

        File directory = new File(folder_path);

        if (!directory.exists())
            directory.mkdir();

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy-hh mm ss");
        String time = dateFormat.format(now);
        folder_path += "Test - " + time;
        new File(folder_path).mkdir();
    }

    /**
     * screenshotError
     * Screenshots the current page to given test output folder path.
     *
     * @param driver: Driver instance of web driver
     */
    public void screenshotError(WebDriver driver) {
        this.driver = driver;
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy-hh mm ss");
        String filename = dateFormat.format(now);
        if (!filename.equals(".png")) {
            try {
                FileHandler.copy(source, new File(folder_path + "/" + filename + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
