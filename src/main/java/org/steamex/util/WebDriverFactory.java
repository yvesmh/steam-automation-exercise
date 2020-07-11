package org.steamex.util;

import org.openqa.selenium.WebDriver;

public interface WebDriverFactory {

    /**
     * Gets a selenium WebDriver. Hides implementation details about the type of driver to allow focusing
     * only on business rules. By calling this method you don't have to worry about setting properties for the location
     * of the driver.
     * @return
     */
    WebDriver getDriver();
}
