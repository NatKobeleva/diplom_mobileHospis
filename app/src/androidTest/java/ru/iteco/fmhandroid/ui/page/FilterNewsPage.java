package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataGenerator;

public class FilterNewsPage {
    public ViewInteraction filterButton = onView(withId(R.id.filter_button));
    public ViewInteraction categoryFilter = onView(withId(R.id.news_item_category_text_auto_complete_text_view));

    public void checkFilterIsDisplayed() {
        onView(withText("Filter news"))
                .check(matches(isDisplayed()));
    }

    public void clickFilterButton(){
        filterButton
                .check(matches(isDisplayed()))
                .perform(click());
    }

    public void selectCategory(){
        categoryFilter.perform(click());
        String randomCategory = DataGenerator.getRandomCategory();
        onView(withText(randomCategory))
                .inRoot(isPlatformPopup())
                .perform(click());
    }
}
