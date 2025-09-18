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

import ru.Page.AddEditNewsPage;
import ru.Page.AuthPage;
import ru.Page.EditNewsPage;
import ru.Page.NewsPage;
import ru.Page.TopMenuPage;

public class AddNewsTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void login() {
        new AuthPage().Auth("login2","password2");

        new TopMenuPage().MainMenuButton("Новости");

        new NewsPage().NewsEditClick();

        new EditNewsPage().AddNewsClick();
    }

    @After
    public void exit() {

        new TopMenuPage().Exit();
    }

    String category = "Зарплата";
    String title = "Зарплата Аовы";
    String todayDate = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date());

    String today = new SimpleDateFormat("dd", Locale.getDefault()).format(new Date());
    String description = "больше чем у вас";
    @Test
    public void addNewsTest(){
        new AddEditNewsPage().AddNews(category, title, description, today);

        new NewsPage().TextExists(title);

        new NewsPage().ClickNews(title);

        new NewsPage().TextExists(description);

        new NewsPage().TextExists(todayDate);

        new EditNewsPage().DeleteNews(title);
    }

}
