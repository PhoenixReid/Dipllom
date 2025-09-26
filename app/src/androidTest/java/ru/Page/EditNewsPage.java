package ru.Page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import ru.iteco.fmhandroid.R;
import ru.utils.waitDisplayed;

public class EditNewsPage {
 public void addNewsClick(){
     onView(isRoot()).perform(new waitDisplayed(R.id.add_news_image_view, 5000));

     onView(withId(R.id.add_news_image_view))
             .perform(click());
 }

 public void deleteNews(String title){
     onView(allOf(withId(R.id.delete_news_item_image_view),
             withParent(hasDescendant(withText(title)))))
             .perform(click());


     onView(withText("OK"))
             .inRoot(isDialog())
             .check(matches(isDisplayed()))
             .perform(click());

 }

 public void editNewsClick(String title){
     onView(allOf(withId(R.id.edit_news_item_image_view),
             withParent(hasDescendant(withText(title)))))
             .perform(click());
 }

}
