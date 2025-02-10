package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.EspressoCustomActions;

public class DashboardPage {

    public ViewInteraction allNews = onView(withText("All news"));
    public ViewInteraction profileButton = onView(withId(R.id.authorization_image_button));
    public ViewInteraction logoutButton = onView(withText("Log out"));
    public ViewInteraction ourMission = onView(withId(R.id.our_mission_image_button));
    public ViewInteraction mainMenu = onView(withId(R.id.main_menu_image_button));

    public DashboardPage waitForMainScreen(long millis) {
        onView(isRoot()).perform(EspressoCustomActions.waitFor(millis));
        return this;
    }


    public void checkDashboardIsDisplayed() {
        onView(withId(R.id.container_custom_app_bar_include_on_fragment_main))
                .check(matches(isDisplayed()));
    }


    public void logOut() {
        profileButton.check(matches(isDisplayed())).perform(click());
        logoutButton.check(matches(isDisplayed())).perform(click());
    }

    public void openNewsFromMain() {
        allNews.check(matches(isDisplayed())).perform(click());
    }

    public void openOurMission() {
        ourMission.check(matches(isDisplayed())).perform(click());
    }

    public void openNavigationMenu(){
        mainMenu.check(matches(isDisplayed())).perform(click());
    }

    public void checkMenuIsDisplayed(){
        onView(withText("Main")).check(matches(isDisplayed()));
        onView(withText("News")).check(matches(isDisplayed()));
        onView(withText("About")).check(matches(isDisplayed()));
    }

    public void openNewsFromMenu(){
        mainMenu.check(matches(isDisplayed())).perform(click());
        onView(withText("News")).check(matches(isDisplayed())).perform(click());
    }

    public void openAboutFromMenu(){
        mainMenu.check(matches(isDisplayed())).perform(click());
        onView(withText("About")).check(matches(isDisplayed())).perform(click());
    }
}
