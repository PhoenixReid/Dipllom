package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.Is.is;

import android.icu.text.SimpleDateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.uiautomator.UiDevice;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.util.Date;
import java.util.Locale;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Story;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.Data.AuthData;
import ru.Data.NewsData;
import ru.Data.TextButtonData;
import ru.Page.AboutAppPage;
import ru.Page.AddEditNewsPage;
import ru.Page.AuthPage;
import ru.Page.EditNewsPage;
import ru.Page.MainMenuPage;
import ru.Page.NewsPage;
import ru.Page.QoutePage;
import ru.Page.TopMenuPage;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.dto.News;

@LargeTest

@RunWith(AllureAndroidJUnit4.class)
public class MainTest {

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

        topMenuPage.mainMenuButton(TextButtonData.newsButton);

        newsPage.newsEditClick();

        editNewsPage.addNewsClick();

        addEditNewsPage.addNews(NewsData.categorySalary, NewsData.titleSalary, NewsData.descriptionSalary, NewsData.today);

        topMenuPage.mainMenuButton(TextButtonData.mainMenuButton);
    }

    @After
    public void exit() {

        topMenuPage.mainMenuButton(TextButtonData.newsButton);

        newsPage.newsEditClick();

        editNewsPage.deleteNews(NewsData.titleSalary);

        topMenuPage.exit();
    }

    AuthPage authPage = new AuthPage();
    TopMenuPage topMenuPage = new TopMenuPage();
    MainMenuPage mainMenuPage = new MainMenuPage();
    NewsPage newsPage = new NewsPage();
    EditNewsPage editNewsPage = new EditNewsPage();
    AddEditNewsPage addEditNewsPage = new AddEditNewsPage();
    @Test
    @Story("Проверка отображения на главной страницу")
    @DisplayName("Скрытие новостей")
    public void expandTest() {

        newsPage.textExists(NewsData.titleSalary);

        mainMenuPage.expandButtonClick();
        newsPage.textNOExists(NewsData.titleSalary);
    }

    @Test
    @Story("Проверка отображения на главной страницу")
    @DisplayName("Првоерка описания новости")
    public void newsListTest() {
       newsPage.clickNews(NewsData.titleSalary);
       newsPage.textExists(NewsData.descriptionSalary);
    }



}
