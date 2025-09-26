package ru.Page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.containsString;

import java.security.PublicKey;

import ru.Data.TextButtonData;
import ru.iteco.fmhandroid.R;
import ru.utils.waitDisplayed;

public class TopMenuPage {
    public void exit(){
        onView(isRoot()).perform(new waitDisplayed(R.id.authorization_image_button, 5000));

        onView(withId(R.id.authorization_image_button))
                .perform(click());

        onView(withText(containsString(TextButtonData.exitButton)))
                .perform(click());
    }

    public void qoute(){
        onView(isRoot()).perform(new waitDisplayed(R.id.our_mission_image_button, 5000));

        onView(withId(R.id.our_mission_image_button))
                .perform(click());
    }

    public void mainMenuButton(String Name){
        onView(isRoot()).perform(new waitDisplayed(R.id.main_menu_image_button, 5000));
        onView(withId(R.id.main_menu_image_button))
                .perform(click());
        onView(withText(Name)).perform(click());
    }
}
