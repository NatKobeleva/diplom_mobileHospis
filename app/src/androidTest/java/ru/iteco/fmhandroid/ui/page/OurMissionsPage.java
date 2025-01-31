package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class OurMissionsPage {

    private final ViewInteraction recyclerView = onView(withId(R.id.our_mission_item_list_recycler_view));

    public void checkMissionPageIsDisplayed() {
        onView(withText("Love is all"))
                .check(matches(isDisplayed()));
    }

    public void clickMission() {
        recyclerView.perform(actionOnItemAtPosition(0, click()));
    }

}