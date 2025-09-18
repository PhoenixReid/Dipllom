package ru.Page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import ru.iteco.fmhandroid.R;

public class MainMenuPage {
    public void PointMain(){
        onView(withText("ВСЕ НОВОСТИ")).check(matches(isDisplayed()));
    }

    public void AllNews(){
        onView(withText("ВСЕ НОВОСТИ")).perform(click());
    }

    public void ExpandButtonClick(){
        onView(withId(R.id.expand_material_button))
                .perform(click());
    }
}
