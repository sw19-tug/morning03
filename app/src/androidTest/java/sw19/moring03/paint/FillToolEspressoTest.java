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
        final int expectedPoints = 40;
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

        Tools lastTool = pointsToDraw.get(pointsToDraw.size() - 1);

        assertEquals(expectedPoints, lastTool.getPoints().size());

    }
}
