package sw19.moring03.paint;

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
        final int expectedPoints = 6;

        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawShapesButton)).perform(click());
        onView(withText("Rectangle")).perform(click());

        assertEquals(Tool.DRAW_RECTANGLE, launchActivityRule.getActivity().getChosenTool());

        onView(withId(R.id.drawingView)).perform(swipe(10, 400, 20, 410));

        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawFillButton)).perform(click());

        assertEquals(Tool.FILL, launchActivityRule.getActivity().getChosenTool());

        onView(withId(R.id.drawingView)).perform(touchAt(15, 405));

        DrawingView view = launchActivityRule.getActivity().findViewById(R.id.drawingView);
        List<Tools> pointsToDraw = view.getObjectsToPaint();

        Tools fillTool = pointsToDraw.get(pointsToDraw.size() - 1);

        assertEquals(expectedPoints, fillTool.getPoints().size());
    }

    @Test
    public void testPartialBoxFill() {
        final int expectedPointsWholeFill = 322;
        final int expectedPointsPartialFill = 194;

        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawShapesButton)).perform(click());
        onView(withText("Rectangle")).perform(click());
        onView(withId(R.id.drawingView)).perform(swipe(10, 400, 210, 600));

        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawFillButton)).perform(click());
        onView(withId(R.id.colorChooserButton)).perform(click());
        onView(withId(R.id.redButton)).perform(click());
        onView(withId(R.id.drawingView)).perform(touchAt(60, 450));

        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawLineButton)).perform(click());
        onView(withId(R.id.colorChooserButton)).perform(click());
        onView(withId(R.id.darkGreenButton)).perform(click());
        onView(withId(R.id.drawingView)).perform(swipe(10, 502, 210, 502));

        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawFillButton)).perform(click());
        onView(withId(R.id.colorChooserButton)).perform(click());
        onView(withId(R.id.orangeButton)).perform(click());
        onView(withId(R.id.drawingView)).perform(touchAt(60, 450));

        DrawingView view = launchActivityRule.getActivity().findViewById(R.id.drawingView);
        Tools wholeBoxFill = view.getObjectsToPaint().get(1);
        assertEquals(expectedPointsWholeFill, wholeBoxFill.getPoints().size());
        Tools upperPartFill = view.getObjectsToPaint().get(3);
        assertEquals(expectedPointsPartialFill, upperPartFill.getPoints().size());
    }

    @Test
    public void testLineFill() {
        final int expectedPointsFill = 110;

        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawLineButton)).perform(click());
        onView(withId(R.id.drawingView)).perform(swipe(10, 420, 110, 420));
        onView(withId(R.id.drawingView)).perform(swipe(90, 400, 90, 500));
        onView(withId(R.id.drawingView)).perform(swipe(110, 480, 10, 480));
        onView(withId(R.id.drawingView)).perform(swipe(30, 500, 30, 400));

        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawFillButton)).perform(click());
        onView(withId(R.id.drawingView)).perform(touchAt(60, 450));

        DrawingView view = launchActivityRule.getActivity().findViewById(R.id.drawingView);
        Tools wholeBoxFill = view.getObjectsToPaint().get(view.getObjectsToPaint().size() - 1);
        assertEquals(expectedPointsFill, wholeBoxFill.getPoints().size());
    }

    @Test
    public void testTriangleFill() {
        final int expectedPointsFill = 102;

        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawLineButton)).perform(click());
        onView(withId(R.id.drawingView)).perform(swipe(10, 420, 110, 420));
        onView(withId(R.id.drawingView)).perform(swipe(90, 400, 90, 500));
        onView(withId(R.id.drawingView)).perform(swipe(110, 500, 10, 400));

        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawFillButton)).perform(click());
        onView(withId(R.id.drawingView)).perform(touchAt(80, 430));

        DrawingView view = launchActivityRule.getActivity().findViewById(R.id.drawingView);
        Tools wholeBoxFill = view.getObjectsToPaint().get(view.getObjectsToPaint().size() - 1);
        assertEquals(expectedPointsFill, wholeBoxFill.getPoints().size());
    }

    @Test
    public void testDoubleFill() {
        final int expectedPoints = 0;

        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawShapesButton)).perform(click());
        onView(withText("Rectangle")).perform(click());

        assertEquals(Tool.DRAW_RECTANGLE, launchActivityRule.getActivity().getChosenTool());

        onView(withId(R.id.drawingView)).perform(swipe(10, 400, 20, 410));

        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawFillButton)).perform(click());

        assertEquals(Tool.FILL, launchActivityRule.getActivity().getChosenTool());

        onView(withId(R.id.drawingView)).perform(touchAt(15, 405));
        onView(withId(R.id.drawingView)).perform(touchAt(15, 405));

        DrawingView view = launchActivityRule.getActivity().findViewById(R.id.drawingView);
        List<Tools> pointsToDraw = view.getObjectsToPaint();

        Tools fillTool = pointsToDraw.get(pointsToDraw.size() - 1);

        assertEquals(expectedPoints, fillTool.getPoints().size());
    }


    //@Test
    public void testOutOfMemoryFill() {
        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawFillButton)).perform(click());

        for (int i = 0; i < 20; i++) {
            onView(withId(R.id.colorChooserButton)).perform(click());
            onView(withId(i % 2 == 0 ? R.id.redButton : R.id.lightGreenButton)).perform(click());
            onView(withId(R.id.drawingView)).perform(touchAt(405, 405));
        }
    }
}
