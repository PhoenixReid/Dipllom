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
import ru.iteco.fmhandroid.R;
import ru.Page.AddEditNewsPage;
import ru.Page.AuthPage;
import ru.Page.EditNewsPage;
import ru.Page.FilterPage;
import ru.Page.NewsPage;
import ru.Page.TopMenuPage;

public class EditNewsTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void login() {
        new AuthPage().Auth("login2","password2");

        new TopMenuPage().MainMenuButton("Новости");

        new NewsPage().NewsEditClick();

        new EditNewsPage().AddNewsClick();

        String today = new SimpleDateFormat("dd", Locale.getDefault()).format(new Date());
        new AddEditNewsPage().AddNews("Зарплата", "Зарплата Аовы", "больше чем у вас", today);

        new EditNewsPage().EditNewsClick("Зарплата Аовы");
    }

    @After
    public void exit() {
        new EditNewsPage().DeleteNews(title);
        new TopMenuPage().Exit();
    }

    String tomorrowDay = new SimpleDateFormat("dd", Locale.getDefault())
            .format(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1)));
    String tomorrowData = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            .format(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1)));
    String newTitle = "Праздник в честь зп АОвы";
    String title = "Зарплата Аовы";
    String newNullTitle = "";
    String newCategory = "Праздник";
    String newDescription = "Праздник в связи с зарплатой Аовы";
    String newNullDescription = "";

    @Test
    public void editCategoryTest(){
        new AddEditNewsPage().Category(newCategory);

        onView(withId(R.id.save_button))
                .perform(scrollTo());

        new AddEditNewsPage().ClickSaveButton();

        new NewsPage().FilterClick();

        new FilterPage().FilterCategory(newCategory);

        new FilterPage().ClickFilter();

        new NewsPage().TextExists("Зарплата Аовы");
    }

    @Test
    public void editTitleTest(){
        title = newTitle;

        new AddEditNewsPage().Title(newTitle);

        new AddEditNewsPage().ClickSaveButton();

        new NewsPage().TextExists(newTitle);
    }

    @Test
    public void editNullTitleTest(){
        new AddEditNewsPage().Title(newNullTitle);

        new AddEditNewsPage().ClickSaveButton();

        new NewsPage().TextExists("Сохранить");

        new AddEditNewsPage().CancelClick();
    }

    @Test
    public void editDescriptionTest(){
        new AddEditNewsPage().Description(newDescription);

        new AddEditNewsPage().ClickSaveButton();

        new NewsPage().ClickNews("Зарплата Аовы");

        new NewsPage().TextExists(newDescription);
    }

    @Test
    public void editNullDescriptionTest(){
        new AddEditNewsPage().Description(newNullDescription);

        new AddEditNewsPage().ClickSaveButton();

        new NewsPage().TextExists("Сохранить");
    }

    @Test
    public void editDateTest(){
        new AddEditNewsPage().Date(tomorrowDay);

        new AddEditNewsPage().ClickSaveButton();

        new NewsPage().ClickNews("Зарплата Аовы");

        new NewsPage().TextExists(tomorrowData);
    }

    @Test
    public void editActivionTest(){
        new AddEditNewsPage().ActivionButton();

        new AddEditNewsPage().ClickSaveButton();

        new TopMenuPage().MainMenuButton("Новости");

        new NewsPage().TextNOExists("Зарплата Аовы");

        new NewsPage().NewsEditClick();
    }
}
