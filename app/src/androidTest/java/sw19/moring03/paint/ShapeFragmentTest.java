package sw19.moring03.paint;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import sw19.moring03.paint.utils.Tool;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class ShapeFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testFragmentVisibility() {
        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawShapesButton)).perform(click());
        onView(withText(R.string.shapeChooserFragment)).check(matches(isDisplayed()));
    }

    @Test
    public void testShapeTypesVisibility() {
        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawShapesButton)).perform(click());
        onView(withText("Circle")).check(matches(isDisplayed()));
        onView(withText("Rectangle")).check(matches(isDisplayed()));
        onView(withText("Oval")).check(matches(isDisplayed()));
    }

    @Test
    public void testCircleToolSelected() {
        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawShapesButton)).perform(click());
        onView(withText("Circle")).perform(click());

        assertEquals(activityTestRule.getActivity().getChosenTool(), Tool.DRAW_CIRCLE);
        try {
            onView(withText(R.string.shapeChooserFragment)).check(matches(isDisplayed()));
            fail("Expected exception not thrown");
        } catch (NoMatchingViewException ignored) {
        }
    }

    @Test
    public void testRectangleToolSelected() {
        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawShapesButton)).perform(click());
        onView(withText("Rectangle")).perform(click());

        assertEquals(activityTestRule.getActivity().getChosenTool(), Tool.DRAW_RECTANGLE);
        try {
            onView(withText(R.string.shapeChooserFragment)).check(matches(isDisplayed()));
            fail("Expected exception not thrown");
        } catch (NoMatchingViewException ignored) {
        }
    }

    @Test
    public void testOvalToolSelected() {
        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawShapesButton)).perform(click());
        onView(withText("Oval")).perform(click());

        assertEquals(activityTestRule.getActivity().getChosenTool(), Tool.DRAW_OVAL);
        try {
            onView(withText(R.string.shapeChooserFragment)).check(matches(isDisplayed()));
            fail("Expected exception not thrown");
        } catch (NoMatchingViewException ignored) {
        }
    }
}