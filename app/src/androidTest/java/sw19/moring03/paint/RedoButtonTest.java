package sw19.moring03.paint;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.Toolbar;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import sw19.moring03.paint.Views.DrawingView;
import sw19.moring03.paint.tools.Tools;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static sw19.moring03.paint.util.Interaction.changeValueOfStrokeWidthSeekBar;
import static sw19.moring03.paint.util.Interaction.touchAt;
import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class RedoButtonTest {

    @Rule
    public ActivityTestRule<MainActivity> launchActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testButtonVisibility() {
        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawPointButton)).perform(click());
        onView(withId(R.id.drawingView)).perform(touchAt(400, 400));
        onView(withId(R.id.undoButton)).perform(click());

        openActionBarOverflowOrOptionsMenu(launchActivityRule.getActivity());
        onView(withText("Redo")).perform(click());
    }

    @Test
    public void testRedoLastObject() {

        onView(withText("Redo")).check(doesNotExist());

        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawPointButton)).perform(click());
        onView(withId(R.id.drawingView)).perform(touchAt(400, 400));
        onView(withId(R.id.undoButton)).perform(click());

        DrawingView view = launchActivityRule.getActivity().findViewById(R.id.drawingView);
        List<Tools> pointsToDraw = view.drawingObjectManager.getObjectsToRedo();

        openActionBarOverflowOrOptionsMenu(launchActivityRule.getActivity());

        onView(withText("Redo")).perform(click());

        assertEquals(pointsToDraw.size(),0);

        onView(withText("Redo")).check(doesNotExist());
    }

    @Test
    public void testRedoStrokeWidth() {
        String expectedTitle = "25pt";

        onView(withId(R.id.redoButton)).check(doesNotExist());

        onView(withId(R.id.strokeWidthChooserButton)).perform(click());
        onView(withId(R.id.strokeWidth)).perform(changeValueOfStrokeWidthSeekBar(25));

        Espresso.pressBack();

        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawLineButton)).perform(click());

        onView(withId(R.id.drawingView)).perform(swipeLeft());
        onView(withId(R.id.undoButton)).perform(click());
        openActionBarOverflowOrOptionsMenu(launchActivityRule.getActivity());
        onView(withText("Redo")).perform(click());

        Toolbar toolbar = launchActivityRule.getActivity().findViewById(R.id.toolbar);
        ActionMenuItemView toolChooser  = toolbar.findViewById(R.id.strokeWidthChooserButton);
        String currentTitle = toolChooser.getItemData().getTitle().toString();

        assertEquals(expectedTitle, currentTitle);

        openActionBarOverflowOrOptionsMenu(launchActivityRule.getActivity());

        onView(withText("Redo")).check(doesNotExist());

    }

    @Test
    public void testUndoUndoRedo() {

        onView(withId(R.id.toolChooserButton)).perform(click());
        onView(withId(R.id.drawPointButton)).perform(click());

        onView(withId(R.id.drawingView)).perform(touchAt(10, 400));
        onView(withId(R.id.drawingView)).perform(touchAt(15, 400));
        onView(withId(R.id.drawingView)).perform(touchAt(20, 400));

        onView(withId(R.id.undoButton)).perform(click());

        DrawingView view = launchActivityRule.getActivity().findViewById(R.id.drawingView);
        List<Tools> pointsToDraw = view.drawingObjectManager.getObjectsToPaint();
        List<Tools> pointsToRedo = view.drawingObjectManager.getObjectsToRedo();

        assertEquals(2, pointsToDraw.size());
        assertEquals(1, pointsToRedo.size());

        onView(withId(R.id.undoButton)).perform(click());

        pointsToDraw = view.drawingObjectManager.getObjectsToPaint();
        pointsToRedo = view.drawingObjectManager.getObjectsToRedo();

        assertEquals(1, pointsToDraw.size());
        assertEquals(2, pointsToRedo.size());

        openActionBarOverflowOrOptionsMenu(launchActivityRule.getActivity());
        onView(withText("Redo")).perform(click());

        pointsToDraw = view.drawingObjectManager.getObjectsToPaint();
        pointsToRedo = view.drawingObjectManager.getObjectsToRedo();

        assertEquals(2, pointsToDraw.size());
        assertEquals(1, pointsToRedo.size());

    }
}
