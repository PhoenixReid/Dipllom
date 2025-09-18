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

import ru.Page.AddEditNewsPage;
import ru.Page.AuthPage;
import ru.Page.EditNewsPage;
import ru.Page.FilterPage;
import ru.Page.NewsPage;
import ru.Page.TopMenuPage;
import ru.iteco.fmhandroid.R;

public class FilterTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void login(){
        new AuthPage().Auth("login2","password2");

        new TopMenuPage().MainMenuButton("Новости");

        new NewsPage().NewsEditClick();

        new EditNewsPage().AddNewsClick();

        String today = new SimpleDateFormat("dd", Locale.getDefault()).format(new Date());
        new AddEditNewsPage().AddNews("Зарплата", "Зарплата Аовы", "больше чем у вас", today);

        new EditNewsPage().AddNewsClick();
        String day = new SimpleDateFormat("dd", Locale.getDefault())
                .format(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(3)));
        new AddEditNewsPage().AddNews("Зарплата", "Зарплата уменьшилась на 5%", "Уменьшение зп у всех на 5%", day);

        new EditNewsPage().AddNewsClick();
        new AddEditNewsPage().AddNews("Объявление", "Объявляние о зарплате Аовы", "больше чем у вас", today);

        new NewsPage().FilterClick();
    }

    @After
    public void exit(){
        new TopMenuPage().MainMenuButton("Новости");

        new NewsPage().NewsEditClick();

        new EditNewsPage().DeleteNews("Объявляние о зарплате Аовы");

        new EditNewsPage().DeleteNews("Зарплата Аовы");

        new EditNewsPage().DeleteNews("Зарплата уменьшилась на 5%");

        new TopMenuPage().Exit();
    }
    String theDayAfterTomorrow = new SimpleDateFormat("dd", Locale.getDefault())
            .format(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(2)));
    String yesterday = new SimpleDateFormat("dd", Locale.getDefault())
            .format(new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1)));
    @Test
    public void filterNoDatatest(){
        new FilterPage().FilterCategory("Зарплата");

        new FilterPage().ClickFilter();

        new NewsPage().TextExists("Зарплата уменьшилась на 5%");

        new NewsPage().TextExists("Зарплата Аовы");

        new NewsPage().NoText("Объявляние о зарплате Аовы");
    }

    @Test
    public void filterTest(){
        new FilterPage().FilterCategory("Зарплата");

        new FilterPage().StartDate(yesterday);

        new FilterPage().EndDate(theDayAfterTomorrow);

        new FilterPage().ClickFilter();

        new NewsPage().TextExists("Зарплата Аовы");

        new NewsPage().NoText("Объявляние о зарплате Аовы");

        new NewsPage().NoText("Зарплата уменьшилась на 5%");
    }
}
