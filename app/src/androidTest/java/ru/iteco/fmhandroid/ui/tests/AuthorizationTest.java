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
import ru.iteco.fmhandroid.ui.page.DashboardPage;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AuthorizationTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    private final AuthorizationSteps authSteps = new AuthorizationSteps();
    private final AuthorizationPage authPage = new AuthorizationPage();
    private final DashboardPage dashboardPage = new DashboardPage();

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource);

        if (!authPage.waitForSplashScreen(3000).checkAuthorizationIsDisplayed()) {
            dashboardPage.logOut();
        }
    }


    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.idlingResource);
    }

    @Description("Успешная авторизация")
    @Test
    public void successfulLoginTest() {
        authSteps.performLogin("login2", "password2");
        dashboardPage.waitForMainScreen(2000).checkDashboardIsDisplayed();
    }

    @Description ("Авторизация с пустым полем Login")
    @Test
    public void emptyLoginTest() {
        authSteps.performLoginWithEmptyLogin("password2");
    }

    @Description ("Авторизация с пустым полем Password")
    @Test
    public void emptyPasswordTest() {
        authSteps.performLoginWithEmptyPassword("login2");
    }

    @Description ("Авторизация с неверным логином")
    @Test
    public void wrongLoginTest() {
        authSteps.performLoginWithWrongLogin("password2");
    }

    @Description ("Авторизация с неверным паролем")
    @Test
    public void wrongPasswordTest() {
        authSteps.performLoginWithWrongPassword("login2");
    }

    @Description ("Авторизация с неверным логином и паролем")
    @Test
    public void wrongLoginAndPassTest() {
        authSteps.performLoginWithWrongLoginAndPass();
    }

    @Description("Авторизация с пробелом перед логином и паролем")
    @Test
    public void spaceBeforeLoginAndPassword(){
        authSteps.performLogin(" login2", " password2");
        authPage.checkLoginErrorMessage("Something went wrong. Try again later.");
    }
}
