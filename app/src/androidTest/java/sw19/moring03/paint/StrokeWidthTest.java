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
public class StrokeWidthTest {

    @Rule
    public ActivityTestRule<MainActivity> launchActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testStrokeWidthSliderVisible() {
        onView(withId(R.id.strokeWidthChooserButton)).perform(click());

        onView(withId(R.id.strokeWidth)).check(matches(isDisplayed()));
    }

    @Test
    public void testStrokeWidthChange() {
        final int expectedInitialWidth = 5;
        final int expectedStrokeWidth = 15;

        assertEquals(expectedInitialWidth, launchActivityRule.getActivity().getStrokeWidth());
        onView(withId(R.id.strokeWidthChooserButton)).perform(click());
        onView(withId(R.id.strokeWidth)).perform(changeValueOfStrokeWidthSeekBar(expectedStrokeWidth));

        assertEquals(expectedStrokeWidth, launchActivityRule.getActivity().getStrokeWidth());
    }

}
