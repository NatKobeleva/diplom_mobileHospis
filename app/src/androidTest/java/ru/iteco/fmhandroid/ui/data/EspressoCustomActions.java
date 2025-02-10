package ru.iteco.fmhandroid.ui.data;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

public class EspressoCustomActions {

    public static Matcher<View> isDescendantOfA(final Matcher<View> parentMatcher) {
        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("is descendant of: ");
                parentMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                while (parent instanceof ViewGroup) {
                    if (parentMatcher.matches(parent)) {
                        return true;
                    }
                    parent = parent.getParent();
                }
                return false;
            }
        };
    }

    public static ViewAction waitFor(final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for " + millis + " milliseconds";
            }

            @Override
            public void perform(UiController uiController, View view) {
                uiController.loopMainThreadForAtLeast(millis);
            }
        };
    }

    public static ViewAction clickChildViewWithId(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isDisplayed(); // Проверяем, что родительский элемент видим
            }

            @Override
            public String getDescription() {
                return "Клик по дочернему элементу с id: " + id;
            }

            @Override
            public void perform(UiController uiController, View view) {
                View child = view.findViewById(id);
                if (child != null) {
                    child.performClick();
                }
            }
        };
    }

    // Метод для получения текста из TextView внутри RecyclerView
    public static ViewAction getChildTextViewWithId(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isDisplayed(); // Проверяем, что родительский элемент видим
            }

            @Override
            public String getDescription() {
                return "Получение текста из TextView с id: " + id;
            }

            @Override
            public void perform(UiController uiController, View view) {
                View child = view.findViewById(id);
                if (child != null && child instanceof TextView) {
                    ((TextView) child).getText().toString();
                }
            }
        };
    }

    // Метод для извлечения текста из ViewInteraction
    public static String getTextFromView(ViewInteraction viewInteraction) {
        final String[] text = {null};
        viewInteraction.perform(new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isDisplayed(); // Проверяем, что элемент видим
            }

            @Override
            public String getDescription() {
                return "Извлечение текста из ViewInteraction";
            }

            @Override
            public void perform(UiController uiController, View view) {
                if (view instanceof TextView) {
                    text[0] = ((TextView) view).getText().toString();
                }
            }
        });
        return text[0];
    }


}
