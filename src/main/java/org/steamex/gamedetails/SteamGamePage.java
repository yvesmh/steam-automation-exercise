package org.steamex.gamedetails;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SteamGamePage {

    @FindBy(className = "apphub_AppName")
    private WebElement gameNameElement;

    // there are 2 divs inside div.block_container_inner with the same class name, the first one has the title,
    // genre, developer, etc. details
    @FindBy(css = ".block_content_inner > div:nth-child(1)")
    private WebElement gameDetailsElement;

    private final WebDriver webDriver;

    public SteamGamePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void navigateToGamePage(String url) {
        webDriver.get(url);
    }

    public String getGameTitle() {
        return gameNameElement.getText();
    }

    public String getNonParsedGameDetails() {
        return gameDetailsElement.getText();
    }
}
