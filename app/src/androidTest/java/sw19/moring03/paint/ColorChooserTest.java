package sw19.moring03.paint;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

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
public class ColorChooserTest {

    @Rule
    public ActivityTestRule<MainActivity> launchActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testStrokeWidthSliderVisible() {
        onView(withId(R.id.colorChooserButton)).perform(click());

        onView(withId(R.id.redSlider)).check(matches(isDisplayed()));
        onView(withId(R.id.greenSlider)).check(matches(isDisplayed()));
        onView(withId(R.id.blueSlider)).check(matches(isDisplayed()));
    }
}
