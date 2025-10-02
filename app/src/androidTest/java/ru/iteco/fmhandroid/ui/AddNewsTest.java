package ru.iteco.fmhandroid.ui;

import android.icu.text.SimpleDateFormat;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.Description;

import java.util.Date;
import java.util.Locale;

import ru.Data.AuthData;
import ru.Data.NewsData;
import ru.Data.TextButtonData;
import ru.Page.AddEditNewsPage;
import ru.Page.AuthPage;
import ru.Page.EditNewsPage;
import ru.Page.NewsPage;
import ru.Page.QoutePage;
import ru.Page.TopMenuPage;

public class AddNewsTest {
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
    }

    @After
    public void exit() {
        topMenuPage.exit();
    }


    TopMenuPage topMenuPage = new TopMenuPage();
    AuthPage authPage = new AuthPage();
    NewsPage newsPage = new NewsPage();
    EditNewsPage editNewsPage = new EditNewsPage();
    AddEditNewsPage addEditNewsPage = new AddEditNewsPage();


    @Test
    public void addNewsTest(){
        addEditNewsPage.addNews(NewsData.categorySalary, NewsData.titleSalary, NewsData.descriptionSalary, NewsData.today);

        newsPage.textExists(NewsData.titleSalary);

        newsPage.clickNews(NewsData.titleSalary);

        newsPage.textExists(NewsData.descriptionSalary);

        newsPage.textExists(NewsData.todayDate);

        editNewsPage.deleteNews(NewsData.titleSalary);
    }

    @Test
    public void addNewsNoTitleTest(){
        addEditNewsPage.addNews(NewsData.categorySalary, NewsData.nullTitle, NewsData.descriptionSalary, NewsData.today);

        newsPage.textExists("Сохранить");

        addEditNewsPage.cancelClick();
    }

    @Test
    public void addNewsNoDescTest(){
        addEditNewsPage.addNews(NewsData.categorySalary, NewsData.titleSalary, NewsData.nullDescription, NewsData.today);

        newsPage.textExists("Сохранить");

        addEditNewsPage.cancelClick();
    }

    @Test
    public void addNewsNoData(){

        addEditNewsPage.category(NewsData.categorySalary);

        addEditNewsPage.title(NewsData.titleSalary);

        addEditNewsPage.description(NewsData.descriptionSalary);

        addEditNewsPage.clickSaveButton();

        newsPage.textExists("Сохранить");

        addEditNewsPage.cancelClick();
    }

}
