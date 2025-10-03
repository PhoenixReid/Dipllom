package ru.iteco.fmhandroid.ui;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Story;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.Data.AuthData;
import ru.Data.NewsData;
import ru.Data.TextButtonData;
import ru.Page.AddEditNewsPage;
import ru.Page.AuthPage;
import ru.Page.EditNewsPage;
import ru.Page.FilterPage;
import ru.Page.NewsPage;
import ru.Page.TopMenuPage;

@RunWith(AllureAndroidJUnit4.class)
@Epic("Фильтр")
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
    @Story("Фильтр")
    @DisplayName("Фильтр без даты")
    public void filterNoDatatest(){
        filterPage.filterCategory(NewsData.categorySalary);

        filterPage.clickFilter();

        newsPage.textExists(NewsData.titleSalary);

        newsPage.textExists(NewsData.titleSecondSalary);

        newsPage.noText(NewsData.titleNotice);
    }

    @Test
    @Story("Фильтр")
    @DisplayName("Фильтр с датой")
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
