package sw19.moring03.paint;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import sw19.moring03.paint.Views.DrawingView;
import sw19.moring03.paint.tools.Tools;
import sw19.moring03.paint.utils.Tool;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static sw19.moring03.paint.util.Interaction.swipe;
import static sw19.moring03.paint.util.Interaction.touchAt;

@RunWith(AndroidJUnit4.class)
public class FillToolEspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> launchActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testFillRectangle() {
        final int expectedPoints = 12;

        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawShapesButton)).perform(click());
        onView(withText("Rectangle")).perform(click());

        assertEquals(Tool.DRAW_RECTANGLE, launchActivityRule.getActivity().getChosenTool());

        onView(withId(R.id.drawingView)).perform(swipe(400, 400, 410, 410));

        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawFillButton)).perform(click());

        assertEquals(Tool.FILL, launchActivityRule.getActivity().getChosenTool());

        onView(withId(R.id.drawingView)).perform(touchAt(405, 405));

        DrawingView view = launchActivityRule.getActivity().findViewById(R.id.drawingView);
        List<Tools> pointsToDraw = view.getObjectsToPaint();

        Tools fillTool = pointsToDraw.get(pointsToDraw.size() - 1);

        assertEquals(expectedPoints, fillTool.getPoints().size());
    }

    @Test
    public void testPartialBoxFill() {
        final int expectedPointsWholeFill = 644;
        final int expectedPointsPartialFill = 500;

        // draw black box
        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawShapesButton)).perform(click());
        onView(withText("Rectangle")).perform(click());
        onView(withId(R.id.drawingView)).perform(swipe(400, 400, 600, 600));

        // fill inside of box red
        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawFillButton)).perform(click());
        onView(withId(R.id.colorChooserButton)).perform(click());
        onView(withId(R.id.redButton)).perform(click());
        onView(withId(R.id.drawingView)).perform(touchAt(450, 450));

        // draw vertical green line through box
        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawLineButton)).perform(click());
        onView(withId(R.id.colorChooserButton)).perform(click());
        onView(withId(R.id.darkGreenButton)).perform(click());
        onView(withId(R.id.drawingView)).perform(swipe(400, 500, 600, 500));

        // fill area above green line orange
        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawFillButton)).perform(click());
        onView(withId(R.id.colorChooserButton)).perform(click());
        onView(withId(R.id.orangeButton)).perform(click());
        onView(withId(R.id.drawingView)).perform(touchAt(450, 450));

        // the first fill should fill the whole box (644 points needed) while the second fill should only fill
        // the upper part of the box (500 points needed)
        DrawingView view = launchActivityRule.getActivity().findViewById(R.id.drawingView);
        Tools wholeBoxFill = view.getObjectsToPaint().get(1);
        assertEquals(expectedPointsWholeFill, wholeBoxFill.getPoints().size());
        Tools upperPartFill = view.getObjectsToPaint().get(3);
        assertEquals(expectedPointsPartialFill, upperPartFill.getPoints().size());
    }
}
