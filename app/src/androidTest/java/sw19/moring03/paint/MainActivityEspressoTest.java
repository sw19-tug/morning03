package sw19.moring03.paint;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import sw19.moring03.paint.utils.Tool;

import static org.junit.Assert.assertEquals;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.fail;

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
        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.toolChooserMenu)).check(matches(isDisplayed()));
    }

    @Test
    public void testToolChooserMenuButtons() {
        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawPointButton)).check(matches(isDisplayed()));
        onView(withId(R.id.drawLineButton)).check(matches(isDisplayed()));
        onView(withId(R.id.drawFillButton)).check(matches(isDisplayed()));
        onView(withId(R.id.eraserButton)).check(matches(isDisplayed()));
        onView(withId(R.id.drawCircleButton)).check(matches(isDisplayed()));
        onView(withId(R.id.drawRectangleButton)).check(matches(isDisplayed()));
    }

    @Test
    public void testPointToolSelected() {
        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawPointButton)).perform(click());

        assertEquals(activityTestRule.getActivity().getChosenTool(), Tool.DRAW_POINT);
        try {
            onView(withId(R.id.toolChooserMenu)).check(matches(isDisplayed()));
            fail("Expected exception not thrown");
        } catch (NoMatchingViewException ignored) {
        }
    }

    @Test
    public void testLineToolSelected() {
        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawLineButton)).perform(click());

        assertEquals(activityTestRule.getActivity().getChosenTool(), Tool.DRAW_LINE);
        try {
            onView(withId(R.id.toolChooserMenu)).check(matches(isDisplayed()));
            fail("Expected exception not thrown");
        } catch (NoMatchingViewException ignored) {
        }
    }

    @Test
    public void testPathToolSelected() {
        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawPathButton)).perform(click());

        assertEquals(activityTestRule.getActivity().getChosenTool(), Tool.DRAW_PATH);
        try {
            onView(withId(R.id.toolChooserMenu)).check(matches(isDisplayed()));
            fail("Expected exception not thrown");
        } catch (NoMatchingViewException ignored) {
        }
    }

    @Test
    public void testCircleToolSelected() {
        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawCircleButton)).perform(click());

        assertEquals(activityTestRule.getActivity().getChosenTool(), Tool.DRAW_CIRCLE);
        try {
            onView(withId(R.id.toolChooserMenu)).check(matches(isDisplayed()));
            fail("Expected exception not thrown");
        } catch (NoMatchingViewException ignored) {
        }
    }

    @Test
    public void testFillToolSelected() {
        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawFillButton)).perform(click());

        assertEquals(activityTestRule.getActivity().getChosenTool(), Tool.FILL);
        try {
            onView(withId(R.id.toolChooserMenu)).check(matches(isDisplayed()));
            fail("Expected exception not thrown");
        } catch (NoMatchingViewException ignored) {
        }
    }

    @Test
    public void testEraseToolSelected() {
        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.eraserButton)).perform(click());

        assertEquals(activityTestRule.getActivity().getChosenTool(), Tool.ERASER);
        try {
            onView(withId(R.id.toolChooserMenu)).check(matches(isDisplayed()));
            fail("Expected exception not thrown");
        } catch (NoMatchingViewException ignored) {
        }
    }

    @Test
    public void testRectangleToolSelected() {
        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawRectangleButton)).perform(click());

        assertEquals(activityTestRule.getActivity().getChosenTool(), Tool.DRAW_RECTANGLE);
        try {
            onView(withId(R.id.toolChooserMenu)).check(matches(isDisplayed()));
            fail("Expected exception not thrown");
        } catch (NoMatchingViewException ignored) {
        }
    }
}