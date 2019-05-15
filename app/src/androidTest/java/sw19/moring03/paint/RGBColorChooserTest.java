package sw19.moring03.paint;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.Menu;
import android.view.MenuItem;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static sw19.moring03.paint.util.Interaction.changeValueOfStrokeWidthSeekBar;

@RunWith(AndroidJUnit4.class)
public class RGBColorChooserTest {

    @Rule
    public ActivityTestRule<MainActivity> launchActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testRGBSliderVisible() {
        onView(withId(R.id.colorChooserButton)).perform(click());

        onView(withId(R.id.redSlider)).check(matches(isDisplayed()));
        onView(withId(R.id.greenSlider)).check(matches(isDisplayed()));
        onView(withId(R.id.blueSlider)).check(matches(isDisplayed()));
    }

    @Test
    public void testRGBSliderChange() {
        @ColorInt final int expectedColor = Color.rgb(20, 20, 20);

        onView(withId(R.id.colorChooserButton)).perform(click());

        onView(withId(R.id.redSlider)).perform(changeValueOfStrokeWidthSeekBar(20));
        onView(withId(R.id.blueSlider)).perform(changeValueOfStrokeWidthSeekBar(20));
        onView(withId(R.id.greenSlider)).perform(changeValueOfStrokeWidthSeekBar(20));

        assertEquals(expectedColor, launchActivityRule.getActivity().getChosenColor());
    }

    @Test
    public void testColorSelected() {
        onView(withId(R.id.colorChooserButton)).perform(click());
        onView(withId(R.id.darkGreenButton)).perform(click());

        onView(withId(R.id.colorChooserButton)).check(matches(isDisplayed()));
        onView(withId(R.id.colorChooserButton)).perform(click());

        assertEquals(9, Color.red(launchActivityRule.getActivity().getColorChooserMenu().getChosenColor()));
        assertEquals(140, Color.green(launchActivityRule.getActivity().getColorChooserMenu().getChosenColor()));
        assertEquals(46, Color.blue(launchActivityRule.getActivity().getColorChooserMenu().getChosenColor()));
    }
}
