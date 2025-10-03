package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

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
import ru.iteco.fmhandroid.R;

@Epic("Изменение новости ")
@RunWith(AllureAndroidJUnit4.class)
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
    @Story("Изменение на валидные данные")
    @DisplayName("ИЗменение категории")
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
    @Story("Изменение на валидные данные")
    @DisplayName("Изменение заголовка")
    public void editTitleTest(){
        title = NewsData.titleNotice;
        addEditNewsPage.title(title);

        addEditNewsPage.clickSaveButton();

        newsPage.textExists(title);

    }

    @Test
    @Story("Изменение на невалидные данные")
    @DisplayName("Изменение заголовка на пустое значение")
    public void editNullTitleTest(){
        addEditNewsPage.title(NewsData.nullTitle);

        addEditNewsPage.clickSaveButton();

        newsPage.textExists("Сохранить");

        addEditNewsPage.cancelClick();

    }

    @Test
    @Story("Изменение на валидные данные")
    @DisplayName("Изменение описания")
    public void editDescriptionTest(){
        addEditNewsPage.description(NewsData.descriptionSecondSalary);

        addEditNewsPage.clickSaveButton();

        newsPage.clickNews(title);

        newsPage.textExists(NewsData.descriptionSecondSalary);

    }

    @Test
    @Story("Изменение на невалидные данные")
    @DisplayName("Изменение описания на пустое значение")
    public void editNullDescriptionTest(){
        addEditNewsPage.description(NewsData.nullDescription);

        addEditNewsPage.clickSaveButton();

        newsPage.textExists("Сохранить");

        addEditNewsPage.cancelClick();
    }

    @Test
    @Story("Изменение на валидные данные")
    @DisplayName("Изменение даты")
    public void editDateTest(){
        addEditNewsPage.date(NewsData.tomorrowDay);

        addEditNewsPage.clickSaveButton();

        newsPage.clickNews(title);

        newsPage.textExists(NewsData.tomorrowData);
    }

    @Test
    @Story("Изменение на валидные данные")
    @DisplayName("Изменение кнопки активно")
    public void editActivionTest(){
        addEditNewsPage.activionButton();

        addEditNewsPage.clickSaveButton();

        topMenuPage.mainMenuButton(TextButtonData.newsButton);

        newsPage.noText(title);

        newsPage.newsEditClick();
    }
}
