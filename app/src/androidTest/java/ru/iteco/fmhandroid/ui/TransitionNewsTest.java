package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.uiautomator.UiDevice;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.uiautomator.UiDevice;


import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Story;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.Data.AuthData;
import ru.Data.TextButtonData;
import ru.Page.AboutAppPage;
import ru.Page.AuthPage;
import ru.Page.MainMenuPage;
import ru.Page.QoutePage;
import ru.Page.TopMenuPage;
import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@Epic("Проверка переходов.")
public class TransitionNewsTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void login() {
        Allure.step("Проверка авторизованного аккаунта.");
        if (authPage.checkAuth()) {

            ;
        } else {
            authPage.auth(AuthData.login, AuthData.password);
        }
        Allure.step("Выполняем переход на страницу Новости.");
        topMenuPage.mainMenuButton(TextButtonData.newsButton);
    }

    @After
    public void exit() {
        Allure.step("Выполняем выход из аккаунта.");
        topMenuPage.exit();
    }

    AuthPage authPage = new AuthPage();
    QoutePage qoutePage = new QoutePage();
    TopMenuPage topMenuPage = new TopMenuPage();
    MainMenuPage mainMenuPage = new MainMenuPage();
    AboutAppPage aboutAppPage = new AboutAppPage();

    @Test
    @DisplayName("Переход на страницу Главная.")
    @Story("Переходы со страницы Новости.")
    public void mainTest() {
        Allure.step("Выполняем переход на страницу Главная.");
        topMenuPage.mainMenuButton(TextButtonData.mainMenuButton);
        Allure.step("Проверка перехода на страницу Главная.");
        mainMenuPage.pointMain();
    }

    @Test
    @DisplayName("Переход на страницу Цитаты.")
    @Story("Переходы со страницы Новости.")
    public void quoteTest() {
        Allure.step("Выполняем переход на страницу Цитаты.");
        topMenuPage.qoute();
        Allure.step("Проверка перехода на страницу Цитаты.");
        qoutePage.qoutePoint();
    }

    @Test // данный тест должен упасть так как кнопка О приложении недоступна
    @DisplayName("Переход на страницу О приложении.")
    @Story("Переходы со страницы Новости.")
    public void aboutAppTest() {
        Allure.step("Выполняем переход на страницу О приложении.");
        topMenuPage.mainMenuButton(TextButtonData.aboutAppButton);
        Allure.step("Проверка перехода на страницу О приложении.");
        aboutAppPage.aboutAppPoint();
        Allure.step("Выходим со страницы О приложении.");
        aboutAppPage.aboutAppExit();
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }


        };
    }
}
