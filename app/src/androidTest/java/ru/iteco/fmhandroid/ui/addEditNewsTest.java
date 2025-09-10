package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;

import static org.hamcrest.Matchers.is;


import androidx.test.InstrumentationRegistry;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.runner.AndroidJUnit4;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import ru.iteco.fmhandroid.R;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class addEditNewsTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public  void login() {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.main_menu_image_button))
                .perform(click());
        onView(withText("Новости")).perform(click());

    }

    String todayDate = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date());
    String tomorrowDate = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            .format(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1)));
    String tomorrowDay = new SimpleDateFormat("dd", Locale.getDefault())
            .format(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1)));
    @Test
    public void test1_AddNewsTest() {

        onView(withText("Зарплата Аовы")).check(doesNotExist());

        onView(withId(R.id.edit_news_material_button))
                .perform(click());

        onView(withId(R.id.add_news_image_view))
                .perform(click());

        onView(withId(R.id.news_item_category_text_auto_complete_text_view))
                .perform(click());

        onView(withText("Зарплата"))
                .inRoot(isPlatformPopup())
                .check(matches(isDisplayed()))
                .perform(click());



        // 6. Ввести заголовок
        onView(withId(R.id.news_item_title_text_input_edit_text))
                .perform(replaceText("Зарплата Аовы"));

        // 7. Установить дату публикации
        onView(withId(R.id.news_item_publish_date_text_input_edit_text))
                .perform(click());

        onView(withText("ОК"))
                .inRoot(isDialog())
                .perform(click());

        // 8. Установить время публикации
        onView(withId(R.id.news_item_publish_time_text_input_edit_text))
                .perform(click());

        onView(withText("ОК"))
                .inRoot(isDialog())
                .perform(click());

        // 9. Ввести описание
        onView(withId(R.id.news_item_description_text_input_edit_text))
                .perform(replaceText("больше чем у вас"), closeSoftKeyboard());

        onView(withId(R.id.save_button))
                .perform(click());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withText("Зарплата Аовы")).check(matches(isDisplayed()));

        onView(withText("Зарплата Аовы")).perform(click());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withText("больше чем у вас")).check(matches(isDisplayed()));
        onView(withText(todayDate)).check(matches(isDisplayed()));

    }


    @Test
    public void test2_EditHeadingNewsTest() {

        onView(withText("Зарплата Аовы")).check(matches(isDisplayed()));

        onView(withId(R.id.edit_news_material_button))
                .perform(click());

        onView(allOf(withId(R.id.edit_news_item_image_view),
                withParent(hasDescendant(withText("Зарплата Аовы")))))
                .perform(click());

        onView(withId(R.id.news_item_category_text_input_layout))
                .perform(click());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.news_item_title_text_input_edit_text))
                .perform(replaceText("Праздник в связи с зарплатой Аовы"));

        onView(withId(R.id.save_button))
                .perform(click());

        onView(withText("Праздник в связи с зарплатой Аовы")).check(matches(isDisplayed()));
    }

    @Test
    public void test3_EditDescriptioningNewsTest() {

        onView(withText("Праздник в связи с зарплатой Аовы")).check(matches(isDisplayed()));

        onView(withId(R.id.edit_news_material_button))
                .perform(click());

        onView(allOf(withId(R.id.edit_news_item_image_view),
                withParent(hasDescendant(withText("Праздник в связи с зарплатой Аовы")))))
                .perform(click());

        onView(withId(R.id.news_item_category_text_input_layout))
                .perform(click());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        // 9. Ввести описание
        onView(withId(R.id.news_item_description_text_input_edit_text))
                .perform(replaceText("Праздник в честь того, что Аовина зарплата превысила вашу"), closeSoftKeyboard());

        onView(withId(R.id.save_button))
                .perform(click());

        onView(withText("Праздник в связи с зарплатой Аовы")).check(matches(isDisplayed()));
        onView(withText("Праздник в связи с зарплатой Аовы")).perform(click());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withText("Праздник в честь того, что Аовина зарплата превысила вашу")).check(matches(isDisplayed()));
    }

    @Test
    public void test4_EditDataNewsTest() {

        onView(withText("Праздник в связи с зарплатой Аовы")).check(matches(isDisplayed()));

        onView(withId(R.id.edit_news_material_button))
                .perform(click());

        onView(allOf(withId(R.id.edit_news_item_image_view),
                withParent(hasDescendant(withText("Праздник в связи с зарплатой Аовы")))))
                .perform(click());

        onView(withId(R.id.news_item_category_text_input_layout))
                .perform(click());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 7. Установить дату публикации
        onView(withId(R.id.news_item_publish_date_text_input_edit_text))
                .perform(click());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.findObject(By.text(tomorrowDay)).click();

        onView(withText("ОК"))
                .inRoot(isDialog())
                .perform(click());



        onView(withId(R.id.save_button))
                .perform(click());

        onView(withText("Праздник в связи с зарплатой Аовы")).check(matches(isDisplayed()));
        onView(withText("Праздник в связи с зарплатой Аовы")).perform(click());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withText(tomorrowDate)).check(matches(isDisplayed()));
    }
    @Test
    public void test5_EditCategoryNewsTest() {

        onView(withText("Праздник в связи с зарплатой Аовы")).check(matches(isDisplayed()));

        onView(withId(R.id.edit_news_material_button))
                .perform(click());

        onView(allOf(withId(R.id.edit_news_item_image_view),
                withParent(hasDescendant(withText("Праздник в связи с зарплатой Аовы")))))
                .perform(click());

        onView(withId(R.id.news_item_category_text_input_layout))
                .perform(click());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withText("Объявление"))
                .inRoot(isPlatformPopup())  // важно: ищем во всплывающем окне
                .check(matches(isDisplayed()))
                .perform(click());


        onView(withId(R.id.save_button))
                .perform(click());

        onView(withId(R.id.filter_news_material_button))
                .perform(click());

        onView(withId(R.id.news_item_category_text_auto_complete_text_view))
                .perform(click());

        onView(withText("Объявление"))
                .inRoot(isPlatformPopup())
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(R.id.filter_button))
                .perform(click());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withText("Праздник в связи с зарплатой Аовы")).check(matches(isDisplayed()));
    }

    @Test
    public void test6_EditActivionNewsTest() {

        onView(withText("Праздник в связи с зарплатой Аовы")).check(matches(isDisplayed()));

        onView(withId(R.id.edit_news_material_button))
                .perform(click());

        onView(allOf(withId(R.id.edit_news_item_image_view),
                withParent(hasDescendant(withText("Праздник в связи с зарплатой Аовы")))))
                .perform(click());

        onView(withId(R.id.news_item_category_text_input_layout))
                .perform(click());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.switcher))
                .perform(click());

        onView(withId(R.id.save_button))
                .perform(click());

        onView(withText("Праздник в связи с зарплатой Аовы")).check(matches(isDisplayed()));

        onView(withId(R.id.main_menu_image_button))
                .perform(click());
        onView(withText("Новости")).perform(click());

        onView(withText("Праздник в связи с зарплатой Аовы")).check(doesNotExist());

    }

    @Test
    public void test7_DelNwsTest() {

        onView(withId(R.id.edit_news_material_button))
                .perform(click());

        onView(allOf(withId(R.id.delete_news_item_image_view),
                withParent(hasDescendant(withText("Праздник в связи с зарплатой Аовы")))))
                .perform(click());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withText("OK"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withText("Праздник в связи с зарплатой Аовы")).check(doesNotExist());

    }
}
