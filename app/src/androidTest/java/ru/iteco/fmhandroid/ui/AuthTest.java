package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Story;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.Data.AuthData;
import ru.Page.AuthPage;
import ru.Page.TopMenuPage;
import ru.Data.ErrorData;
import ru.Page.NewsPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@Epic("Проверка авторизации.")
public class AuthTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityRule = new ActivityScenarioRule<>(AppActivity.class);


    @Before
    public void setUp() {
        activityRule.getScenario().onActivity(new ActivityScenario.ActivityAction<AppActivity>() {
            @Override
            public void perform(AppActivity activity) { // ← вот здесь!
                decorView = activity.getWindow().getDecorView();
            }
        });

        if (authPage.checkAuth()) {
            topMenuPage.exit();
        }
    }

    private View decorView;
    AuthPage authPage = new AuthPage();
    TopMenuPage topMenuPage = new TopMenuPage();
    NewsPage newsPage = new NewsPage();


    @Test
    @DisplayName("Невалидный логин.")
    @Story("Вводим невалидные данные.")
    public void authUnLoginTest() {


        authPage.auth(AuthData.unLogin, AuthData.password);

        newsPage.errorText(ErrorData.authError);

        authPage.authUnSucess();
    }

    @Test
    @DisplayName("Невалидный пароль.")
    @Story("Вводим невалидные данные.")
    public void authUnPasswordTest() {

        authPage.auth(AuthData.login, AuthData.unPassword);

        newsPage.errorText(ErrorData.authError);

        authPage.authUnSucess();
    }

    @Test
    @DisplayName("Пустой логин.")
    @Story("Вводим невалидные данные.")
    public void authNullLoginTest() {

        authPage.auth(AuthData.nullLogin, AuthData.password);

        newsPage.errorText(ErrorData.authError);

        authPage.authUnSucess();
    }

    @Test
    @DisplayName("Пустой пароль.")
    @Story("Вводим невалидные данные.")
    public void authNullPasswordTest() {

        authPage.auth(AuthData.login, AuthData.nullPassword);

        newsPage.errorText(ErrorData.authError);

        authPage.authUnSucess();
    }

    @Test
    @DisplayName("Логин из пробелов.")
    @Story("Вводим невалидные данные.")
    public void authSpaceLoginTest() {

        authPage.auth(AuthData.spaseLogin, AuthData.password);

        newsPage.errorText(ErrorData.authError);

        authPage.authUnSucess();
    }

    @Test
    @DisplayName("Пароль из пробелов.")
    @Story("Вводим невалидные данные.")
    public void authSpacePasswordTest() {

        authPage.auth(AuthData.login, AuthData.spasePassword);

        newsPage.errorText(ErrorData.authError);

        authPage.authUnSucess();
    }

    @Test
    @DisplayName("Валиные данные")
    @Story("Вводим валидные данные")
    public void authTest() {

        authPage.auth(AuthData.login, AuthData.password);

        authPage.authSucess();

        topMenuPage.exit();
    }


}
