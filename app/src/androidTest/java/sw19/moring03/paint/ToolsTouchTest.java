package sw19.moring03.paint;

import android.support.annotation.ColorInt;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import sw19.moring03.paint.Views.DrawingView;
import sw19.moring03.paint.tools.Tools;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static sw19.moring03.paint.util.Interaction.changeValueOfStrokeWidthSeekBar;
import static sw19.moring03.paint.util.Interaction.touchAt;

@RunWith(AndroidJUnit4.class)
public class ToolsTouchTest {

    @Rule
    public ActivityTestRule<MainActivity> launchActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testColorSavedInTool() {
        @ColorInt int black = launchActivityRule.getActivity().getResources().getColor(R.color.black);
        @ColorInt int yellow = launchActivityRule.getActivity().getResources().getColor(R.color.yellow);

        assertEquals(black, launchActivityRule.getActivity().getChosenColor());

        onView(withId(R.id.colorChooserButton)).perform(click());
        onView(withId(R.id.yellowButton)).perform(click());

        assertEquals(yellow, launchActivityRule.getActivity().getChosenColor());

        onView(withId(R.id.drawingView)).perform(touchAt(400, 400));

        DrawingView view = launchActivityRule.getActivity().findViewById(R.id.drawingView);
        List<Tools> pointsToDraw = view.getObjectsToPaint();

        Tools lastTool = pointsToDraw.get(pointsToDraw.size() - 1);

        assertEquals(yellow, lastTool.getColor());
    }

    @Test
    public void testDifferentColorsForTools() {
        @ColorInt int black = launchActivityRule.getActivity().getResources().getColor(R.color.black);
        @ColorInt int yellow = launchActivityRule.getActivity().getResources().getColor(R.color.yellow);

        assertEquals(black, launchActivityRule.getActivity().getChosenColor());

        onView(withId(R.id.drawingView)).perform(touchAt(400, 400));

        onView(withId(R.id.colorChooserButton)).perform(click());
        onView(withId(R.id.yellowButton)).perform(click());

        assertEquals(yellow, launchActivityRule.getActivity().getChosenColor());

        onView(withId(R.id.drawingView)).perform(touchAt(450, 450));

        DrawingView view = launchActivityRule.getActivity().findViewById(R.id.drawingView);
        List<Tools> pointsToDraw = view.getObjectsToPaint();

        Tools firstTool = pointsToDraw.get(0);
        Tools secondTool = pointsToDraw.get(pointsToDraw.size() - 1);

        assertEquals(black, firstTool.getColor());
        assertEquals(yellow, secondTool.getColor());
    }

    @Test
    public void testDifferentStrokeWidthForTools() {
        assertEquals(5, launchActivityRule.getActivity().getStrokeWidth());

        onView(withId(R.id.drawingView)).perform(touchAt(400, 400));

        onView(withId(R.id.strokeWidthChooserButton)).perform(click());
        onView(withId(R.id.strokeWidth)).perform(changeValueOfStrokeWidthSeekBar(15));
        onView(withId(R.id.strokeWidth)).perform(ViewActions.pressBack());

        assertEquals(15, launchActivityRule.getActivity().getStrokeWidth());

        onView(withId(R.id.drawingView)).perform(touchAt(450, 450));

        DrawingView view = launchActivityRule.getActivity().findViewById(R.id.drawingView);
        List<Tools> pointsToDraw = view.getObjectsToPaint();

        Tools firstTool = pointsToDraw.get(0);
        Tools secondTool = pointsToDraw.get(pointsToDraw.size() - 1);

        assertEquals(5, firstTool.getStrokeWidth());
        assertEquals(15, secondTool.getStrokeWidth());
    }

}
