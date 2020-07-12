package org.steamex.search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Parses the search results from the search bar in Steam's home page.
 * This class doesn't follow the PageObject pattern because those results
 * are generated dynamically depending on the text from the search bar
 */
public class SearchResultGameParser {

    private static final String GAME_TITLE_ELEMENT_CLASS = "match_name";

    /**
     * Parses the search results from the steam search bar
     *
     * @param searchResults the collection of result <a></a> WebElements
     * @return A collection or SearchResultGame that contains information about each game
     */
    public static List<SearchResultGame> ParseSearchResults(List<WebElement> searchResults) {

        //TODO learn java 8 streams and refactor
        List<SearchResultGame> games = new ArrayList<>();
        for (WebElement searchResultElement : searchResults) {
            games.add(ParseSingleResult(searchResultElement));
        }
        return games;
    }

    private static SearchResultGame ParseSingleResult(WebElement searchResult) {
        String title = searchResult.findElement(By.className(GAME_TITLE_ELEMENT_CLASS)).getText();
        String url = searchResult.getAttribute("href");
        return new SearchResultGame(title, url);

    }
}
