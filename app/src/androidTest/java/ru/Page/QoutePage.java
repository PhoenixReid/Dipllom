package ru.Page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

public class QoutePage {
    public void ClickQoute(String Name){
        onView(withText(containsString(Name)))
                .check(matches(isDisplayed()));

        onView(withText(containsString(Name)))
                .perform(click());
    }

    public void  QuoteDesc(String Description){
        onView(withText(containsString(Description)))
                .check(matches(isDisplayed()));
    }

    public void QoutePoint(){
        onView(withText("Главное - жить любя")).check(matches(isDisplayed()));
    }
}
