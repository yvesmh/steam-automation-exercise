package org.steamex.homepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class SteamHomePage {

    @FindBy(id = "store_nav_search_term")
    private WebElement searchGameInput;

    private static final String SEARCH_RESULTS_PARENT_ID = "search_suggestion_contents";
    private static final String HOME_PAGE_URL = "https://store.steampowered.com/";
    private static final long SEARCH_TIMEOUT_SECONDS = 5;

    private final WebDriver driver;

    public SteamHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToHomePage() {
        driver.get(HOME_PAGE_URL);
    }

    public List<WebElement> searchForGame(String gameName) {

        searchGameInput.sendKeys(gameName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(SEARCH_TIMEOUT_SECONDS).toMillis());

        // use visibilityOfElement instead of presenceOfElement because that div always exists,
        // once results are retrieved it simply changes from display:none to  display:block
        WebElement resultsParent = wait
                .until(visibilityOfElementLocated(By.id(SEARCH_RESULTS_PARENT_ID)));

        // all game are <a href="game_url"></a> children elements within the search_suggestion_contents div
        return resultsParent.findElements(By.tagName("a"));
    }

}
