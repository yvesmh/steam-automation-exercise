package org.steamex.search;

/**
 * Represents a game from the result of performing a search on the Steam homepage.
 */
public class SearchResultGame {
    private final String title;
    private final String url;

    public SearchResultGame(String title, String url) {
        this.title = title;
        this.url = url;
    }

    /**
     * Get the game's title. E.g. "Portal 2"
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the URL to view the Game's page. E.g. "https://store.steampowered.com/app/813780/Age_of_Empires_II_Definitive_Edition/"
     *
     * @return
     */
    public String getUrl() {
        return url;
    }

}
