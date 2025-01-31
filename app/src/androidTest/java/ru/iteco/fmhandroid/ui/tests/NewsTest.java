package ru.iteco.fmhandroid.ui.tests;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.EspressoIdlingResources;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.ControlPanelPage;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.NewsSteps;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class NewsTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private final NewsSteps newsSteps = new NewsSteps();
    private final ControlPanelPage panelNews = new ControlPanelPage();
    private final AuthorizationPage authPage = new AuthorizationPage();
    private final AuthorizationSteps authSteps = new AuthorizationSteps();

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource);

        if (authPage.waitForSplashScreen(3000).checkAuthorizationIsDisplayed()) {
            authSteps.performLogin("login2", "password2");
        }
    }


    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.idlingResource);
    }

    @Description ("Успешное добавление новости с рандомными данными")
    @Test
    public void testAddRandomNews() {
        newsSteps.addRandomNews();
        panelNews.scrollNewsPage(newsSteps.getLastGeneratedTitle());
    }

    @Description ("Добавление новости с незаполненными полями")
    @Test
    public void testAddEmptyNews() {
        newsSteps.addEmptyNews();
    }


    @Description ("Удаление новости")
    @Test
    public void deleteNews() {
        newsSteps.deleteNews();
    }


    @Description ("Редактирование существующей новости")
    @Test
    public void editNews() {
        newsSteps.editNews();
    }
}
