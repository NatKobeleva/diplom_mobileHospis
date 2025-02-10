package ru.iteco.fmhandroid.ui.steps;

import ru.iteco.fmhandroid.ui.data.DataGenerator;
import ru.iteco.fmhandroid.ui.page.ControlPanelPage;
import ru.iteco.fmhandroid.ui.page.CreateNewsPage;
import ru.iteco.fmhandroid.ui.page.FilterNewsPage;

public class NewsSteps {

    private final CreateNewsPage createNewsPage = new CreateNewsPage();
    private final ControlPanelPage controlPanel = new ControlPanelPage();
    private final FilterNewsPage filterNews = new FilterNewsPage();

    String randomPublishDate = DataGenerator.generateRandomDate();
    String randomPublishTime = DataGenerator.generateRandomTime();
    String randomDescription = DataGenerator.generateRandomText(20);
    String randomTitle = "News " + DataGenerator.generateRandomDate() + " " + randomPublishTime;
    private String lastGeneratedTitle;
    private String lastGeneratedDescription;


    public String getLastGeneratedTitle() {
        return lastGeneratedTitle;
    }

    public String getLastGeneratedDescription() {
        return lastGeneratedDescription;
    }

    public void addRandomNews() {
        lastGeneratedTitle = randomTitle;
        controlPanel
                .openControlPanelFromMain()
                .addNews();
        createNewsPage.createNews(randomTitle, randomPublishDate, randomPublishTime, randomDescription);
        createNewsPage.pressSave();
    }


    public void addEmptyNews() {
        controlPanel
                .openControlPanelFromMain()
                .addNews();
        createNewsPage.pressSave();
        createNewsPage.checkCreateErrorMessage("Fill empty fields");
    }

    public void deleteNews() {
        controlPanel.openControlPanelFromMain().deleteNewsAndVerify();
    }

    public void editNews() {
        lastGeneratedDescription = randomDescription;

        controlPanel.openControlPanelFromMain();
        controlPanel.editNews();
        createNewsPage.createDescription(randomDescription);
        createNewsPage.clickSwitcher();
        createNewsPage.pressSave();
        controlPanel.checkDescription(getLastGeneratedDescription());
    }

    public void sortingNewsByDate(){
        controlPanel.openControlPanelFromMain();
        controlPanel.pressButtonSortingNews();

    }

    public void openFilterNewsFromControlPanel(){
        controlPanel.openControlPanelFromMain();
        controlPanel.pressButtonFilterNews();
        filterNews.checkFilterIsDisplayed();
    }

    public void emptyFilterNews(){
        filterNews.clickFilterButton();
        controlPanel.checkControlPanelIsDisplayed();
    }

    public void filterNewsByCategory(){
        filterNews.selectCategory();
        filterNews.clickFilterButton();
        controlPanel.checkControlPanelIsDisplayed();
    }

}

