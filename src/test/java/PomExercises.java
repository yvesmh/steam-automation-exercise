import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.steamex.search.SearchResultGame;
import org.steamex.search.SearchResultGameParser;
import org.steamex.gamedetails.SteamGamePage;
import org.steamex.homepage.SteamHomePage;
import org.steamex.seleniumutils.WebDriverFactory;
import org.testng.annotations.*;

import java.util.List;

public class PomExercises {

    private WebDriver _webDriver;

    @BeforeClass
    public void setUp() {
        _webDriver = new WebDriverFactory().getDriver();
    }

    @AfterClass
    public void tearDown() {
        _webDriver.quit();
    }

    @Test
    public void searchResultsContainSearchedItems() {
        final String searchedGame = "Portal";
        List<WebElement> webElementResults = searchForGame(searchedGame);

        List<SearchResultGame> games = SearchResultGameParser
                .ParseSearchResults(webElementResults);
        System.out.println("Verifying games contain searched input");
        for (SearchResultGame game : games) {
            System.out.println("Asserting game [" + game.getTitle() + "] contains [" + searchedGame + "]");
            assert game.getTitle().contains(searchedGame);
        }

    }

    @Test
    public void searchResultNavigatesToCorrectGamePage() {

        final String searchedGame = "Portal 2";
        List<WebElement> webElementResults = searchForGame(searchedGame);

        SearchResultGame firstResult = SearchResultGameParser
                .ParseSearchResults(webElementResults)
                .get(0);

        SteamGamePage firstResultPage = PageFactory.initElements(_webDriver, SteamGamePage.class);
        firstResultPage.navigateToGamePage(firstResult.getUrl());

        String gameTitle = firstResultPage.getGameTitle();

        System.out.println("First search result's title: " + firstResult.getTitle());
        System.out.println("Game's page title: " + firstResult.getTitle());

        assert gameTitle.equals(firstResult.getTitle());

    }

    @Test
    public void gameDetailsMatchExpected() {

        final String ageOfEmpiresTitle = "Age of Empires II";
        List<WebElement> webElementResults = searchForGame(ageOfEmpiresTitle);

        SearchResultGame ageOfEmpires = SearchResultGameParser
                .ParseSearchResults(webElementResults)
                .get(0);

        SteamGamePage firstResultPage = PageFactory.initElements(_webDriver, SteamGamePage.class);
        firstResultPage.navigateToGamePage(ageOfEmpires.getUrl());

        String nonParsedDetails = firstResultPage.getNonParsedGameDetails();

        // game details are not in a selenium-friendly DOM. For now verify non-parsed data
        String expected = "TITLE: Age of Empires II: Definitive Edition\n" +
                "GENRE: Strategy\n" +
                "DEVELOPER: Forgotten Empires, Tantalus Media, Wicked Witch\n" +
                "PUBLISHER: Xbox Game Studios\n" +
                "FRANCHISE: Age of Empires\n" +
                "RELEASE DATE: Nov 14, 2019";

        System.out.println(nonParsedDetails);

        assert expected.equals(nonParsedDetails);

    }

    private List<WebElement> searchForGame(String gameName) {
        SteamHomePage homePage = PageFactory.initElements(_webDriver, SteamHomePage.class);
        homePage.navigateToHomePage();

        return homePage
                .searchForGame(gameName);
    }

}
