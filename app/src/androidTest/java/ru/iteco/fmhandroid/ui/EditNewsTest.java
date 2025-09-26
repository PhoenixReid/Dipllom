package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.icu.text.SimpleDateFormat;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

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
import ru.iteco.fmhandroid.R;
import ru.Page.AddEditNewsPage;
import ru.Page.AuthPage;
import ru.Page.EditNewsPage;
import ru.Page.FilterPage;
import ru.Page.NewsPage;
import ru.Page.TopMenuPage;
import ru.iteco.fmhandroid.dto.News;

public class EditNewsTest {
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

        addEditNewsPage.addNews(NewsData.categorySalary, title, NewsData.descriptionSalary, NewsData.today);

        editNewsPage.editNewsClick(title);
    }

    @After
    public void exit() {
        editNewsPage.deleteNews(title);

        topMenuPage.exit();
    }

    TopMenuPage topMenuPage = new TopMenuPage();
    AuthPage authPage = new AuthPage();
    NewsPage newsPage = new NewsPage();
    EditNewsPage editNewsPage = new EditNewsPage();
    AddEditNewsPage addEditNewsPage = new AddEditNewsPage();
    FilterPage filterPage = new FilterPage();

    String title = NewsData.titleSalary;

    @Test
    public void editCategoryTest(){
        addEditNewsPage.category(NewsData.categoryParty);

        onView(withId(R.id.save_button))
                .perform(scrollTo());

        addEditNewsPage.clickSaveButton();

        newsPage.filterClick();

        filterPage.filterCategory(NewsData.categoryParty);

        filterPage.clickFilter();

        newsPage.textExists(title);
    }

    @Test
    public void editTitleTest(){
        title = NewsData.titleNotice;
        addEditNewsPage.title(title);

        addEditNewsPage.clickSaveButton();

        newsPage.textExists(title);

    }

    @Test
    public void editNullTitleTest(){
        addEditNewsPage.title(NewsData.nullTitle);

        addEditNewsPage.clickSaveButton();

        newsPage.textExists("Сохранить");

        addEditNewsPage.cancelClick();

    }

    @Test
    public void editDescriptionTest(){
        addEditNewsPage.description(NewsData.descriptionSecondSalary);

        addEditNewsPage.clickSaveButton();

        newsPage.clickNews(title);

        newsPage.textExists(NewsData.descriptionSecondSalary);

    }

    @Test
    public void editNullDescriptionTest(){
        addEditNewsPage.description(NewsData.nullDescription);

        addEditNewsPage.clickSaveButton();

        newsPage.textExists("Сохранить");

        addEditNewsPage.cancelClick();
    }

    @Test
    public void editDateTest(){
        addEditNewsPage.date(NewsData.tomorrowDay);

        addEditNewsPage.clickSaveButton();

        newsPage.clickNews(title);

        newsPage.textExists(NewsData.tomorrowData);
    }

    @Test
    public void editActivionTest(){
        addEditNewsPage.activionButton();

        addEditNewsPage.clickSaveButton();

        topMenuPage.mainMenuButton(TextButtonData.newsButton);

        newsPage.noText(title);

        newsPage.newsEditClick();
    }
}
