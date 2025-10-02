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
@Epic("Jтображения новостей.")
@RunWith(AllureAndroidJUnit4.class)
public class MainTest {

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
        Allure.step("Переходим на страницу Новости.");
        topMenuPage.mainMenuButton(TextButtonData.newsButton);
        Allure.step("Переходим на страницу изменения новости.");
        newsPage.newsEditClick();
        Allure.step("Переходим на страницу добавления/изменения новости.");
        editNewsPage.addNewsClick();
        Allure.step("Добавляем новость.");
        addEditNewsPage.addNews(NewsData.categorySalary, NewsData.titleSalary, NewsData.descriptionSalary, NewsData.today);
        Allure.step("Переходим на страницу Главная.");
        topMenuPage.mainMenuButton(TextButtonData.mainMenuButton);
    }

    @After
    public void exit() {
        Allure.step("Переходим на страницу Новости");
        topMenuPage.mainMenuButton(TextButtonData.newsButton);
        Allure.step("Переходим на страницу изменения новостей.");
        newsPage.newsEditClick();
        Allure.step("Удаляем новость.");
        editNewsPage.deleteNews(NewsData.titleSalary);
        Allure.step("Выполняем выход из аккаунта.");
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
        Allure.step("Проверяем наличие новости на странице Главная.");
        newsPage.textExists(NewsData.titleSalary);
        Allure.step("Скравыем новости.");
        mainMenuPage.expandButtonClick();
        Allure.step("Проверяем, что новость не видна.");
        newsPage.textNOExists(NewsData.titleSalary);
    }

    @Test
    @Story("Проверка отображения на главной страницу")
    @DisplayName("Првоерка описания новости")
    public void newsListTest() {
        Allure.step("раскрываем новость.");
       newsPage.clickNews(NewsData.titleSalary);
        Allure.step("Проверяем описание новости.");
       newsPage.textExists(NewsData.descriptionSalary);
    }


    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
