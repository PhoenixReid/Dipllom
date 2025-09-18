package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import android.icu.text.SimpleDateFormat;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.util.Date;
import java.util.Locale;

import ru.Page.AddEditNewsPage;
import ru.Page.AuthPage;
import ru.Page.EditNewsPage;
import ru.Page.NewsPage;
import ru.Page.TopMenuPage;
import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NewsTest {

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

        new TopMenuPage().MainMenuButton("Новости");
    }

    @After
    public void exit() {
        new NewsPage().NewsEditClick();

        new EditNewsPage().DeleteNews("Зарплата Аовы");

        new TopMenuPage().Exit();
    }


    @Test
    public void ClickTest() {
        new NewsPage().ClickNews("Зарплата Аовы");

        new NewsPage().TextExists("больше чем у вас");
    }
}

