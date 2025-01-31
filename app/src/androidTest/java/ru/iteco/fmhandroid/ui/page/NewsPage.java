package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import ru.iteco.fmhandroid.R;

public class NewsPage {

    public void checkNewsPageIsDisplayed() {
        onView(withId(R.id.container_custom_app_bar_include_on_fragment_news_list))
                .check(matches(isDisplayed()));
    }

    public void openControlPanel() {
        onView(withId(R.id.edit_news_material_button))
                .perform(click());
    }
}
