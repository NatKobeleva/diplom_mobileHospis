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
import ru.iteco.fmhandroid.ui.page.AboutPage;
import ru.iteco.fmhandroid.ui.page.AuthorizationPage;
import ru.iteco.fmhandroid.ui.page.DashboardPage;
import ru.iteco.fmhandroid.ui.page.NewsPage;
import ru.iteco.fmhandroid.ui.page.OurMissionsPage;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)

public class DashboardTests {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private final AuthorizationPage authPage = new AuthorizationPage();
    private final DashboardPage dashboardPage = new DashboardPage();
    private final OurMissionsPage missionPage = new OurMissionsPage();
    private final AuthorizationSteps authSteps = new AuthorizationSteps();
    private final NewsPage newsPage = new NewsPage();
    private final AboutPage aboutPage = new AboutPage();


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

    @Description("Выход из аккаунта")
    @Test
    public void successfulLogOut() {
        dashboardPage.logOut();
        authPage.checkAuthorizationIsDisplayed();
    }

    @Description ("Открытие страницы с цитатами")
    @Test
    public void openOurMission() {
        dashboardPage.openOurMission();
        missionPage.checkMissionPageIsDisplayed();
        missionPage.clickMission();
    }

    @Description ("Открытие навигационного меню")
    @Test
    public void checkNavigationMenu() {
        dashboardPage.openNavigationMenu();
        dashboardPage.checkMenuIsDisplayed();
    }

    @Description ("Открытие страницы Новости из меню")
    @Test
    public void openNewsFromMenu() {
        dashboardPage.openNewsFromMenu();
        newsPage.checkNewsPageIsDisplayed();
    }

    @Description ("Открытие страницы About с главной страницы")
    @Test
    public void openAboutFromMain() {
        dashboardPage.openAboutFromMenu();
        aboutPage.checkAboutIsDisplayed();
    }

    @Description ("Открытие страницы About со страницы Новости")
    @Test
    public void openAboutFromNews() {
        dashboardPage.openNewsFromMain();
        dashboardPage.openAboutFromMenu();
        aboutPage.checkAboutIsDisplayed();
    }

}
