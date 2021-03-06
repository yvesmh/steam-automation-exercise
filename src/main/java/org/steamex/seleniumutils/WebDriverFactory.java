package org.steamex.seleniumutils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory {

    private static final Logger logger = LogManager.getLogger(WebDriverFactory.class);

    /**
     * Gets a selenium WebDriver. Hides implementation details about the type of driver to allow focusing
     * only on application code. By calling this method you don't have to worry about setting properties for
     * the location of the driver.
     * Right now this will always return a Chrome Driver, will add Firefox later if needed
     *
     * @return A concrete implementation of WebDriver. For now it always returns ChromeDriver
     */
    public WebDriver getDriver() {
        String chromeDriverPath = getChromeDriverPath();
        logger.debug("Chrome Driver Path: " + chromeDriverPath);
        System.setProperty("webdriver.chrome.driver", getChromeDriverPath());
        logger.debug("Property webdriver.chrome.driver has been set successfully");
        return new ChromeDriver();
    }

    /**
     * Gets the path to the Chrome Driver.
     * Both are located in the same folder with the same name, however windows has the .exe extension while
     * Mac has no extension
     *
     * @return Path to the Chrome Driver
     */
    private static String getChromeDriverPath() {
        String baseDriverPath = "src/test/resources/chromedriver";

        if (!isWindows()) return baseDriverPath;

        return baseDriverPath + ".exe";
    }

    private static String getOsName() {
        return System.getProperty("os.name");
    }

    private static boolean isWindows() {
        return getOsName().startsWith("Windows");
    }

}
