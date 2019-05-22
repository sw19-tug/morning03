package sw19.moring03.paint;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static sw19.moring03.paint.util.Interaction.touchAt;

@RunWith(AndroidJUnit4.class)
public class TextInsertFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testFragmentVisibility() {
        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawTextButton)).perform(click());
        onView(withId(R.id.drawingView)).perform(touchAt(450, 450));

        onView(withText(R.string.insertTextFragment)).check(matches(isDisplayed()));
    }

    @Test
    public void testInsertFieldVisibility() {
        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawTextButton)).perform(click());
        onView(withId(R.id.drawingView)).perform(touchAt(450, 450));

        onView(withId(R.id.editText)).check(matches(isDisplayed()));
    }

    @Test
    public void testSimpleTextInsert() {
        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawTextButton)).perform(click());
        onView(withId(R.id.drawingView)).perform(touchAt(450, 450));

        onView(withId(R.id.editText)).perform(typeText("Hello World"));
        onView(withId(R.id.editText)).check(matches(withText("Hello World")));
    }
    @Test
    public void testTextInsertCancel () {
        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawTextButton)).perform(click());

        onView(withId(R.id.drawingView)).perform(touchAt(450, 450));
        onView(withId(R.id.editText)).perform(click());
        onView(withText(R.string.insertTextFragment)).perform(pressBack());
        onView(withText(R.string.insertTextFragment)).perform(pressBack());
        onView(withId(R.id.strokeWidthChooserButton)).check(matches(isDisplayed()));
    }
}