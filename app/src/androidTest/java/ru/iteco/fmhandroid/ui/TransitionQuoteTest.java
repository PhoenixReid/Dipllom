package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;

import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


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
import ru.Page.NewsPage;
import ru.Page.QoutePage;
import ru.Page.TopMenuPage;
import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@Epic("Проверка переходов.")
public class TransitionQuoteTest {
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

       topMenuPage.qoute();
    }

    @After
    public void exit() {

        topMenuPage.exit();
    }

    AuthPage authPage = new AuthPage();
    QoutePage qoutePage = new QoutePage();
    TopMenuPage topMenuPage = new TopMenuPage();
    MainMenuPage mainMenuPage = new MainMenuPage();
    AboutAppPage aboutAppPage = new AboutAppPage();
    NewsPage newsPage = new NewsPage();

    @Test
    @DisplayName("Переход на страницу Новости.")
    @Story("Переходы со страницы Цитаты.")
    public void newsTest() {
        topMenuPage.mainMenuButton(TextButtonData.newsButton);

        newsPage.pointNews();
    }

    @Test
    @DisplayName("Переход на страницу О приложении.")
    @Story("Переходы со страницы Цитаты.")
    public void aboutAppTest() {
        topMenuPage.mainMenuButton(TextButtonData.aboutAppButton);

        aboutAppPage.aboutAppPoint();

        aboutAppPage.aboutAppExit();
    }

    @Test
    @DisplayName("Переход на страницу Главная.")
    @Story("Переходы со страницы Цитаты.")
    public void mainTest() {

        topMenuPage.mainMenuButton(TextButtonData.mainMenuButton);

        mainMenuPage.pointMain();
    }


}
