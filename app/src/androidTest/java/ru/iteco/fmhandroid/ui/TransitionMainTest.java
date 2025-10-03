package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

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


import java.util.Date;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Story;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.Page.AboutAppPage;
import ru.Page.AuthPage;
import ru.Page.MainMenuPage;
import ru.Page.NewsPage;
import ru.Page.QoutePage;
import ru.Page.TopMenuPage;
import ru.iteco.fmhandroid.R;
import ru.utils.waitDisplayed;
import ru.Data.AuthData;
import ru.Data.TextButtonData;
@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@Epic("Проверка переходов.")
public class TransitionMainTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void login() {
        if (authPage.checkAuth()) {

            ;
        } else {

            authPage.auth(AuthData.login, AuthData.password);
        }
    }

    @After
    public void exit() {
        topMenuPage.exit();
    }

    AuthPage authPage = new AuthPage();
    TopMenuPage topMenuPage = new TopMenuPage();
    QoutePage qoutePage = new QoutePage();
    NewsPage newsPage = new NewsPage();

    @Test
    @DisplayName("Переход на страницу Цитаты.")
    @Story("Переходы со страницы Главная.")
    public void quoteTest() {
       topMenuPage.qoute();

       qoutePage.qoutePoint();
    }

    @Test
    @DisplayName("Переход на страницу Новости.")
    @Story("Переходы со страницы Главная.")
    public void newsTest() {
        topMenuPage.mainMenuButton(TextButtonData.newsButton);
        newsPage.pointNews();
    }

    @Test
    @DisplayName("Переход на страницу Новости через кнопку ВСЕ НОВОСТИ.")
    @Story("Переходы со страницы Главная.")
    public void allNewsTest() {
        new MainMenuPage().allNews();

        new NewsPage().pointNews();
    }

    @Test
    @DisplayName("Переход на страницу О приложении.")
    @Story("Переходы со страницы Главная.")
    public void aboutAppTest() {
        new TopMenuPage().mainMenuButton(TextButtonData.aboutAppButton);

        new AboutAppPage().aboutAppPoint();

        new AboutAppPage().aboutAppExit();
    }


}
