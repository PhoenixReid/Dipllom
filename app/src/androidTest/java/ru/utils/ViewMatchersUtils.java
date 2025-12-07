package ru.utils;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.test.platform.app.InstrumentationRegistry;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ViewMatchersUtils {

    public static Matcher<View> hasDrawable(int expectedId) {
        return new TypeSafeMatcher<View>() {

            private String resourceName;

            @Override
            protected boolean matchesSafely(View target) {
                if (!(target instanceof ImageView)) {
                    return false;
                }
                ImageView imageView = (ImageView) target;
                Drawable drawable = imageView.getDrawable();
                if (drawable == null) {
                    return false;
                }

                Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
                Drawable expectedDrawable = ContextCompat.getDrawable(context, expectedId);
                if (expectedDrawable == null) {
                    return false;
                }
                
                return drawable.getConstantState().equals(expectedDrawable.getConstantState());
            }

            @Override
            public void describeTo(Description description) {
                resourceName = "with drawable from resource id: " + expectedId;
                description.appendText(resourceName);
            }
        };
    }
}