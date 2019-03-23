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

@RunWith(AndroidJUnit4.class)
public class MainActivityEspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testToolChooserMenuButtonVisibility() {
        onView(withId(R.id.toolChooserButton)).check(matches(isDisplayed()));
    }

    @Test
    public void testToolChooserBottomSheetVisibility() {
        // after the user clicked on the tool chooser-button a new bottom-sheet menu must appear
        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.toolChooserMenu)).check(matches(isDisplayed()));
    }
}
