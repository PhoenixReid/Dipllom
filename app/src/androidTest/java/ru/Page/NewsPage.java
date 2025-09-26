package ru.Page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

import ru.iteco.fmhandroid.R;
import ru.utils.waitDisplayed;

public class NewsPage {
    public void pointNews(){
        onView(withId(R.id.news_list_swipe_refresh))
                .check(matches(isDisplayed()));
    }

    public void clickNews(String Name){
        onView(withText(Name)).perform(click());
    }

    public void newsEditClick(){
        onView(isRoot()).perform(new waitDisplayed(R.id.edit_news_material_button, 5000));
        onView(withId(R.id.edit_news_material_button))
                .perform(click());
    }

    public void textExists(String text){
        onView(withText(text)).check(matches(isDisplayed()));
    }

    public void textNOExists(String text){
        onView(withText(text)).check(matches(not(isDisplayed())));;
    }

    public void noText(String text) {
        onView(withText(text)).check(doesNotExist());
    }

    public void filterClick(){
        onView(isRoot()).perform(new waitDisplayed(R.id.filter_news_material_button, 5000));
        onView(withId(R.id.filter_news_material_button))
                .perform(click());
    }
}
