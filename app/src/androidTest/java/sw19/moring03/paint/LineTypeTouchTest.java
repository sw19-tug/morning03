package sw19.moring03.paint;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class LineTypeTouchTest {

    @Rule
    public ActivityTestRule<MainActivity> launchActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickOnDashedLine() {
        onView(withId(R.id.lineTypeChooserButton)).perform(click());
        onView(withId(R.id.dashedLine)).perform(click());
    }

    @Test
    public void clickOnSolidLine() {
        onView(withId(R.id.lineTypeChooserButton)).perform(click());
        onView(withId(R.id.solidLine)).perform(click());
    }

    @Test
    public void clickOnDashedDottedLine() {
        onView(withId(R.id.lineTypeChooserButton)).perform(click());
        onView(withId(R.id.dasheddotedLine)).perform(click());
    }

    @Test
    public void clickOnDottedLine() {
        onView(withId(R.id.lineTypeChooserButton)).perform(click());
        onView(withId(R.id.dotedLine)).perform(click());
    }

}
