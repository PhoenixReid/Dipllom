package ru.Page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import ru.iteco.fmhandroid.R;

public class AboutAppPage {
    public void AboutAppPoint(){
        onView(withText("Версия:")).check(matches(isDisplayed()));
    }

    public void AboutAppExit(){
        onView(withId(R.id.about_back_image_button))
                .perform(click());
    }
}
