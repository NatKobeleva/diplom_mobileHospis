package ru.iteco.fmhandroid.ui.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.is;

import android.widget.EditText;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.EspressoCustomActions;

public class AuthorizationPage {

    public ViewInteraction enterButton = onView(withId(R.id.enter_button));

    public AuthorizationPage waitForSplashScreen(long millis) {
        onView(isRoot()).perform(EspressoCustomActions.waitFor(millis));
        return this;
    }

    public boolean checkAuthorizationIsDisplayed() {
        try {
            onView(withText("Authorization")).check(matches(isDisplayed()));
            return true;
        } catch (NoMatchingViewException e) {
            return false;
        }
    }

    public AuthorizationPage enterLogin(String login) {
        onView(allOf(
                EspressoCustomActions.isDescendantOfA(withId(R.id.login_text_input_layout)),
                isAssignableFrom(EditText.class)))
                .check(matches(isDisplayed()))
                .perform(replaceText(login), closeSoftKeyboard());
        return this;
    }

    public AuthorizationPage enterPassword(String password) {
        onView(allOf(
                EspressoCustomActions.isDescendantOfA(withId(R.id.password_text_input_layout)),
                isAssignableFrom(EditText.class)))
                .check(matches(isDisplayed()))
                .perform(replaceText(password), closeSoftKeyboard());
        return this;
    }

    public AuthorizationPage clickLoginButton() {
        enterButton
                .check(matches(isDisplayed()))
                .perform(click());
        return this;
    }

    public void checkLoginErrorMessage(String errorMessage) {
        onView(withText(errorMessage))
                .inRoot(withDecorView(is(ViewMatchers.isDisplayed())))
                .check(matches(isDisplayed()));
    }
}

