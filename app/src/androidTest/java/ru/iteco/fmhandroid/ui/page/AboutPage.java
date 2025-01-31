package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import ru.iteco.fmhandroid.R;

public class AboutPage {

    public void checkAboutIsDisplayed() {
        onView(withId(R.id.container_custom_app_bar_include_on_fragment_about))
                .check(matches(isDisplayed()));
    }
}
