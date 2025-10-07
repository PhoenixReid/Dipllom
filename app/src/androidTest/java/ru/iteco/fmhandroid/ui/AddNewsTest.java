package ru.iteco.fmhandroid.ui;

import android.icu.text.SimpleDateFormat;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.Description;
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
import ru.Page.AddEditNewsPage;
import ru.Page.AuthPage;
import ru.Page.EditNewsPage;
import ru.Page.NewsPage;
import ru.Page.QoutePage;
import ru.Page.TopMenuPage;


@LargeTest
@Epic("Создание новости.")
@RunWith(AllureAndroidJUnit4.class)
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

//    @After
//    public void exit() {
//
//        topMenuPage.exit();
//    }


    TopMenuPage topMenuPage = new TopMenuPage();
    AuthPage authPage = new AuthPage();
    NewsPage newsPage = new NewsPage();
    EditNewsPage editNewsPage = new EditNewsPage();
    AddEditNewsPage addEditNewsPage = new AddEditNewsPage();

    @DisplayName("Создание валидной новости.")
    @Story("Создание валидной новости.")
    @Test
    public void addNewsTest(){

        addEditNewsPage.addNews(NewsData.categorySalary, NewsData.titleSalary, NewsData.descriptionSalary, NewsData.today);

        newsPage.textExists(NewsData.titleSalary);

        newsPage.clickNews(NewsData.titleSalary);

        newsPage.textExists(NewsData.descriptionSalary);

        newsPage.textExists(NewsData.todayDate);

        editNewsPage.deleteNews(NewsData.titleSalary);
    }
    @DisplayName("Создание невалидной новости.")
    @Story("Создание новости с невалидным заголовком.")
    @Test
    public void addNewsNoTitleTest(){

        addEditNewsPage.addNews(NewsData.categorySalary, NewsData.nullTitle, NewsData.descriptionSalary, NewsData.today);

        newsPage.textExists("Сохранить");

        addEditNewsPage.cancelClick();
    }

    @DisplayName("Создание невалидной новости.")
    @Story("Создание новости с невалидным заголовком.")
    @Test
    public void addNewsNoDescTest(){

        addEditNewsPage.addNews(NewsData.categorySalary, NewsData.titleSalary, NewsData.nullDescription, NewsData.today);

        newsPage.textExists("Сохранить");

        addEditNewsPage.cancelClick();
    }

    @DisplayName("Создание невалидной новости.")
    @Story("Создание новости с невалидной датой.")
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
