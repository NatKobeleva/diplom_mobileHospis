package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static ru.iteco.fmhandroid.ui.data.EspressoCustomActions.clickChildViewWithId;
import static ru.iteco.fmhandroid.ui.data.EspressoCustomActions.getChildTextViewWithId;
import static ru.iteco.fmhandroid.ui.data.EspressoCustomActions.getTextFromView;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;


public class ControlPanelPage {

    private final DashboardPage dashboardPage = new DashboardPage();
    private final NewsPage newsPage = new NewsPage();

    private final ViewInteraction recyclerView = onView(withId(R.id.news_list_recycler_view));
    public ViewInteraction buttonOk = onView(withId(android.R.id.button1));
    public ViewInteraction messageDelete = onView(withId(android.R.id.message));
    public ViewInteraction buttonSortingNews = onView(withId(R.id.sort_news_material_button));
    public ViewInteraction buttonFilterNews = onView(withId(R.id.filter_news_material_button));


    public ControlPanelPage openControlPanelFromMain() {
        dashboardPage.waitForMainScreen(3000).openNewsFromMain();
        newsPage.openControlPanel();
        return this;
    }

    public void checkControlPanelIsDisplayed(){
        onView(withText("Control panel"))
                .check(matches(isDisplayed()));
    }


    public void addNews() {
        onView(allOf(withId(R.id.add_news_image_view), withContentDescription("Add news button")))
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void scrollNewsPage(String title) {
        recyclerView.perform(scrollTo(hasDescendant(withText(title))))
                .check(matches(isDisplayed()));
    }

    public void deleteNewsAndVerify() {
        ViewInteraction firstNewsTitle = recyclerView.perform(actionOnItemAtPosition(0, getChildTextViewWithId(R.id.news_item_title_text_view)));
        final String deletedNewsTitle = getTextFromView(firstNewsTitle);

        recyclerView.perform(actionOnItemAtPosition(0, clickChildViewWithId(R.id.delete_news_item_image_view)));
        messageDelete.check(matches(isDisplayed()));
        buttonOk.check(matches(isDisplayed())).perform(click());

        onView(withText(deletedNewsTitle)).check(doesNotExist());
    }

    public void editNews() {
        recyclerView.perform(actionOnItemAtPosition(1, clickChildViewWithId(R.id.edit_news_item_image_view)));
    }

    public void checkDescription(String description) {
        recyclerView.perform(actionOnItemAtPosition(1, click()));
        onView(allOf(
                withId(R.id.news_item_description_text_view),
                withText(description),
                isDisplayed()
        )).check(matches(withText(description)));
    }

    public void pressButtonSortingNews(){
        buttonSortingNews
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void pressButtonFilterNews(){
        buttonFilterNews
                .check(matches(isDisplayed()))
                .perform(click());
    }

}
