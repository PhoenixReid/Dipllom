package ru.iteco.fmhandroid.ui;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Step;
import io.qameta.allure.kotlin.Story;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.Data.AuthData;
import ru.Page.AuthPage;
import ru.Page.TopMenuPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@Epic("Проверка авторизации.")
public class AuthTest {

    @Rule
    public ActivityScenarioRule<AppActivity> activityRule = new ActivityScenarioRule<>(AppActivity.class);


    @Before
    public void setUp() {
        Allure.step("Проверка авторизированного аккаунта.");
        if (authPage.checkAuth()) {
            topMenuPage.exit();
        }

    }

    private View decorView;
    AuthPage authPage = new AuthPage();
    TopMenuPage topMenuPage = new TopMenuPage();

    @Test
    @DisplayName("Невалидный логин.")
    @Story("Вводим невалидные данные.")
    public void authUnLoginTest() {
        Allure.step("Вводим невалидный логин и валидный пароль.");
        authPage.auth(AuthData.unLogin, AuthData.password);
        Allure.step("Проверка: авторизация завершается с ошибкой.");
        authPage.authUnSucess();
    }

    @Test
    @DisplayName("Невалидный пароль.")
    @Story("Вводим невалидные данные.")
    public void authUnPasswordTest() {
        Allure.step("Вводим валидный логин и невалидный пароль.");
        authPage.auth(AuthData.login, AuthData.unPassword);
        Allure.step("Проверка: авторизация завершается с ошибкой.");
        authPage.authUnSucess();
    }

    @Test
    @DisplayName("Пустой логин.")
    @Story("Вводим невалидные данные.")
    public void authNullLoginTest() {
        Allure.step("Вводим валидный пароль.");
        authPage.auth(AuthData.nullLogin, AuthData.password);
        Allure.step("Проверка: авторизация завершается с ошибкой.");
        authPage.authUnSucess();
    }

    @Test
    @DisplayName("Пустой пароль.")
    @Story("Вводим невалидные данные.")
    public void authNullPasswordTest() {
        Allure.step("Вводим валидный логин.");
        authPage.auth(AuthData.login, AuthData.nullPassword);
        Allure.step("Проверка: авторизация завершается с ошибкой.");
        authPage.authUnSucess();
    }

    @Test
    @DisplayName("Логин из пробелов.")
    @Story("Вводим невалидные данные.")
    public void authSpaceLoginTest() {
        Allure.step("Вводим логин из пробелов и валидный пароль.");
        authPage.auth(AuthData.spaseLogin, AuthData.password);
        Allure.step("Проверка: авторизация завершается с ошибкой.");
        authPage.authUnSucess();
    }

    @Test
    @DisplayName("Пароль из пробелов.")
    @Story("Вводим невалидные данные.")
    public void authSpacePasswordTest() {
        Allure.step("Вводим валидный логин и пароль из пробелов.");
        authPage.auth(AuthData.login, AuthData.spasePassword);
        Allure.step("Проверка: авторизация завершается с ошибкой.");
        authPage.authUnSucess();
    }

    @Test
    @DisplayName("Валиные данные")
    @Story("Вводим валидные данные")
    public void authTest() {
        Allure.step("Вводим валидный логин и валидный пароль.");
        authPage.auth(AuthData.login, AuthData.password);
        Allure.step("Выполняем успешную авторизацию с валидными учётными данными.");
        authPage.authSucess();
        Allure.step("Проверяем, что пользователь успешно вышел.");
        topMenuPage.exit();
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
