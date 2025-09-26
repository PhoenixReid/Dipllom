package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.icu.text.SimpleDateFormat;

import androidx.test.InstrumentationRegistry;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import ru.Data.AuthData;
import ru.Data.NewsData;
import ru.Data.TextButtonData;
import ru.Page.AddEditNewsPage;
import ru.Page.AuthPage;
import ru.Page.EditNewsPage;
import ru.Page.FilterPage;
import ru.Page.MainMenuPage;
import ru.Page.NewsPage;
import ru.Page.TopMenuPage;
import ru.iteco.fmhandroid.R;

public class FilterTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void login(){
        if (authPage.checkAuth()) {

            ;
        } else {
            authPage.auth(AuthData.login, AuthData.password);
        }

        topMenuPage.mainMenuButton(TextButtonData.newsButton);

        newsPage.newsEditClick();

        editNewsPage.addNewsClick();

        addEditNewsPage.addNews(NewsData.categorySalary, NewsData.titleSalary, NewsData.descriptionSalary, NewsData.today);


        editNewsPage.addNewsClick();

        addEditNewsPage.addNews(NewsData.categorySalary, NewsData.titleSecondSalary, NewsData.descriptionSecondSalary, NewsData.oneDayPlThree);

        editNewsPage.addNewsClick();
        addEditNewsPage.addNews(NewsData.categoryNotice, NewsData.titleNotice, NewsData.descriptionSalary, NewsData.today);

        newsPage.filterClick();
    }

    @After
    public void exit(){
        topMenuPage.mainMenuButton(TextButtonData.newsButton);

        newsPage.newsEditClick();

        editNewsPage.deleteNews(NewsData.titleSalary);

        editNewsPage.deleteNews(NewsData.titleSecondSalary);

        editNewsPage.deleteNews(NewsData.titleNotice);

        topMenuPage.exit();
    }

    TopMenuPage topMenuPage = new TopMenuPage();
    AuthPage authPage = new AuthPage();
    NewsPage newsPage = new NewsPage();
    EditNewsPage editNewsPage = new EditNewsPage();
    AddEditNewsPage addEditNewsPage = new AddEditNewsPage();
    FilterPage filterPage = new FilterPage();

    @Test
    public void filterNoDatatest(){
        filterPage.filterCategory(NewsData.categorySalary);

        filterPage.clickFilter();

        newsPage.textExists(NewsData.titleSalary);

        newsPage.textExists(NewsData.titleSecondSalary);

        newsPage.noText(NewsData.titleNotice);
    }

    @Test
    public void filterTest(){
        filterPage.filterCategory(NewsData.categorySalary);

        filterPage.startDate(NewsData.yesterday);

        filterPage.endDate(NewsData.theDayAfterTomorrow);

        filterPage.clickFilter();

        newsPage.textExists(NewsData.titleSalary);

        newsPage.noText(NewsData.titleSecondSalary);

        newsPage.noText(NewsData.titleNotice);
    }
}
