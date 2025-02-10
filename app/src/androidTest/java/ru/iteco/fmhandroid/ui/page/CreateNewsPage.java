package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.data.DataGenerator;

public class CreateNewsPage {

    public ViewInteraction category = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    public ViewInteraction title = onView(withId(R.id.news_item_title_text_input_edit_text));
    public ViewInteraction time = onView(withId(R.id.news_item_publish_time_text_input_edit_text));
    public ViewInteraction date = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
    public ViewInteraction description = onView(withId(R.id.news_item_description_text_input_edit_text));
    public ViewInteraction buttonSave = onView(withId(R.id.save_button));
    public ViewInteraction switcher = onView(withId(R.id.switcher));

    public void createCategory() {
        category.perform(click());
        String randomCategory = DataGenerator.getRandomCategory();
        onView(withText(randomCategory))
                .inRoot(isPlatformPopup())
                .perform(click());
    }

    public void createTitle(String publishTitle) {
        title.perform(replaceText(publishTitle), closeSoftKeyboard());
    }

    public void createDate(String publishDate) {
        date.perform(replaceText(publishDate), closeSoftKeyboard());
    }

    public void createTime(String publishTime) {
        time.perform(replaceText(publishTime), closeSoftKeyboard());
    }

    public void createDescription(String publishDescription) {
        description.perform(replaceText(publishDescription), closeSoftKeyboard());
    }

    public void createNews(String publishTitle, String publishDate, String publishTime, String publishDescription) {
        category.perform(click());
        String randomCategory = DataGenerator.getRandomCategory();
        onView(withText(randomCategory))
                .inRoot(isPlatformPopup())
                .perform(click());

        title.perform(replaceText(publishTitle), closeSoftKeyboard());
        date.perform(replaceText(publishDate), closeSoftKeyboard());
        time.perform(replaceText(publishTime), closeSoftKeyboard());
        description.perform(replaceText(publishDescription), closeSoftKeyboard());
    }

    public void clickSwitcher() {
        switcher.check(matches(isDisplayed())).perform(click());
    }

    public void pressSave() {
        buttonSave.check(matches(isDisplayed())).perform(scrollTo()).perform(click());
    }


    public void checkCreateErrorMessage(String errorMessage) {
        onView(withText(errorMessage))
                .inRoot(withDecorView(is(ViewMatchers.isDisplayed())))
                .check(matches(isDisplayed()));
    }
}